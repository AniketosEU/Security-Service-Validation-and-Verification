/**
 * Copyright 2014 Liverpool John Moores University <http://www.ljmu.ac.uk/cmp/>
 * Aniketos Project FP7-ICT-257930 <http://www.aniketos.eu>
 * David Llewellyn-Jones <D.Llewellyn-Jones@ljmu.ac.uk>
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either 
 * version 3 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package eu.aniketos.ncvm.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.cxf.binding.Binding;
import org.apache.cxf.binding.soap.interceptor.SoapHeaderInterceptor;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.Conduit;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.apache.log4j.Logger;
import org.osgi.framework.BundleContext;

import arlut.csd.crypto.MD5Crypt;

/**
 * CXF Interceptor that provides HTTP Basic Authentication validation.
 * For use with Aniketos DOSGi Web Services
 * 
 * Based on the concepts outline here:
 *    http://chrisdail.com/2008/03/31/apache-cxf-with-http-basic-authentication
 *
 * Requires log4j (although this can be commented out to print output on stdout)
 * Requires org.apache.cxf
 * 
 * The class initialiser will try to load in details from the file configure/users.xts
 * If the file doesn't exist, authentication will not be used
 * 
 * Can use the following for additional maven dependencies
 * 
 * 	<dependency>
 *		<groupId>log4j</groupId>
 *		<artifactId>log4j</artifactId>
 *		<version>1.2.17</version>
 *	</dependency>
 *	<dependency>
 *		<groupId>org.apache.cxf</groupId>
 *		<artifactId>cxf-rt-frontend-jaxws</artifactId>
 *		<version>${cxf.version}</version>
 *	</dependency>
 *
 * @author CDail
 * @author David Llewellyn-Jones, Liverpool John Moores University
 */
public class BasicAuthAuthorizationInterceptor extends SoapHeaderInterceptor {

	
	/**
	 * Enumerates the various authorisation levels
	 * Both NONE and FAIL are liable to generate HTTP error responses depending on the value of blockPage
	 */
	public enum AuthLevel {
		/**
		 * User is not authorised (guest).
		 */
		NONE,
		/**
		 * Authorisation failed.
		 */
		FAIL,
		/**
		 * Standard level of authentication.
		 */
		NORMAL,
		/**
		 * Extended or full authentication.
		 */
		FULL
	}
	
	/**
	 * Returns the authentication level depending on the HTTP BASIC authentication
	 * credentials provided by the client.
	 * This method is intended to be called by the CXF-DOSGi-called implementation method
	 * 
	 * @return the authorisation level
	 */
	public static AuthLevel getAuthLevel () {
		AuthLevel authLevel = AuthLevel.NONE;

		// Collect the message details, so we can extract the authentication level
		Message message = org.apache.cxf.phase.PhaseInterceptorChain.getCurrentMessage(); 
		
		if (message != null) {
			// Determine the authentication level, which was recorded by the interceptor
			if (message.containsKey("authentication")) {
			authLevel = (AuthLevel) message.get("authentication");
			}
		}
		
		/*
		// Output all of the message properties
		for (String key : message.keySet()) {
			System.out.println("Key: " + key);
		}
		*/
		
		return authLevel;
	}
	
	/**
	 * Logger class for logging requests
	 */
	protected Logger log = Logger.getLogger(getClass());

	/**
	 * Specifies whether authentication should be used.
	 * true - authentication is required
	 * false - no authenatication needed
	 */
	private boolean authenticationActive;
	
	/**
	 * Specifies whether a HTTP 401 Unauthorised/403 Forbidden message should be returned to unauthenticated users
	 * true - return 401/403 where authentication is missing/fails
	 * false - continue to page in all cases
	 */
	private boolean blockPage;

	/**
	 * Map of users and their passwords hashed using the Apache MD5 algorithm.
	 * These represent normal authenticated users.
	 */
	private Map<String,String> usersNormal;

	/**
	 * Map of users and their passwords hashed using the Apache MD5 algorithm.
	 * These represent users authenticated with elevated privileges
	 */
	private Map<String,String> usersFull;
	
	/**
	 * Class constructor sets up the initial username and password list
	 */
	public BasicAuthAuthorizationInterceptor() {
		authenticationActive = true;
		blockPage = true;
		usersNormal = new Hashtable<String,String>();
		usersNormal.clear();
		usersFull = new Hashtable<String,String>();
		usersFull.clear();

		// Password uses the Apache MD5 hashing algorithm
		//addUser(usersFull, "Aniketos", "$apr1$BnkINIkJ$j66rMhBdb7plJ4q472ohd/");
		try {
			LoadUserFilesDefault();
		} catch (IOException e) {
			authenticationActive = false;
		}
		if (authenticationActive == false) {
			System.out.println("Service is public: No HTTP authentication in use.");
		}
	}

	/**
	 * Log a message if logging is enabled
	 * @param message the string to log
	 */
	private void Log(String message) {
		if (log.isDebugEnabled()) {
			log.debug(message);
		}
		System.out.println(message);
	}

	/**
	 * Add a single user to the authentication list
	 * Use httpasswd to generate password hashes.
	 * For info about the hashing algorithm, see https://httpd.apache.org/docs/2.2/misc/password_encryptions.html
	 * @param users the list of users to add the user to
	 * @param user the user to add
	 * @param password an Apache MD5-hash of the uer's password
	 */
	public void addUser(Map<String,String> users, String user, String password) {
		users.put(user, password);
	}

	/**
	 * Load the username and passwords from the files in Apache hash format
	 * For info about the hashing algorithm, see https://httpd.apache.org/docs/2.2/misc/password_encryptions.html
	 * @throws IOException
	 */
	public void LoadUserFilesDefault() throws IOException {
		LoadUserFile(usersNormal, "configure/users-normal.txt");
		LoadUserFile(usersFull, "configure/users-full.txt");

		// If there are no registered users, turn off the authentication
		if ((usersNormal.size() == 0) && (usersFull.size() == 0)) {
			authenticationActive = false;
		}
	}

	/**
	 * Load the username and passwords from a given file in Apache hash format
	 * For info about the hashing algorithm, see https://httpd.apache.org/docs/2.2/misc/password_encryptions.html
	 * @param users the list of users to store the result to
	 * @param file the file to load the user info from
	 * @throws IOException
	 */
	public void LoadUserFile(Map<String,String> users, String file) throws IOException {
		BundleContext context = Activator.getContext();
		// Load data from the configuration file if there is one
		System.out.println("Reading users from: " + file);
		URL configURL = context.getBundle().getEntry(file);
		if (configURL != null) {
			users.clear();
			BufferedReader input = new BufferedReader(new InputStreamReader(configURL.openStream()));
			try {
				// Read in each user
				String userPass = "";
				while (userPass != null) {
					userPass = input.readLine();
					if ((userPass != null) && (userPass.startsWith("#") == false)) {
						int separator = userPass.indexOf(':');
						if (separator >= 0) {
							String user = userPass.substring(0, separator);
							String pass = userPass.substring(separator + 1);
							// Add the user to the list of authorised users
							addUser(users, user, pass);
						}
					}
				}
			}
			finally {
				input.close();
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.apache.cxf.binding.soap.interceptor.SoapHeaderInterceptor#handleMessage(org.apache.cxf.message.Message)
	 */
	@Override public void handleMessage(Message message) throws Fault {
		// Store the authentication level so it can be extracted by the CXF-DOSGi-called implementation method
		message.put("authentication", AuthLevel.NONE);

		// If authentication is turned off we don't have to do anything
		if (authenticationActive) {
			// This is set by CXF
			AuthorizationPolicy policy = message.get(AuthorizationPolicy.class);
	
			// If the policy is not set, the user did not specify credentials
			// A 401 is sent to the client to indicate that authentication is required
			if (policy == null) {
				Log("User attempted to log in with no credentials");
				// Store the authentication level so it can be extracted by the CXF-DOSGi-called implementation method
				message.put("authentication", AuthLevel.NONE);
				if (blockPage) {
					// Block access
					sendErrorResponse(message, HttpURLConnection.HTTP_UNAUTHORIZED);
					Log("HTTP 401 Unauthorized returned");
				}
			}
			else {
				// Verify the password
				Log("Authenticating user: " + policy.getUserName() + " (" + this.getClass().getPackage() + ")");

				// Check the full users
				String realPasswordCrypt = usersFull.get(policy.getUserName());
				if (realPasswordCrypt == null || !MD5Crypt.verifyPassword(policy.getPassword(), realPasswordCrypt)) {
					// Check the normal users
					realPasswordCrypt = usersNormal.get(policy.getUserName());
					if (realPasswordCrypt == null || !MD5Crypt.verifyPassword(policy.getPassword(), realPasswordCrypt)) {
						Log("Invalid username or password for user: " + policy.getUserName());
						// Store the authentication level so it can be extracted by the CXF-DOSGi-called implementation method
						message.put("authentication", AuthLevel.FAIL);
						if (blockPage) {
							// Block access
							sendErrorResponse(message, HttpURLConnection.HTTP_FORBIDDEN);
							Log("HTTP 403 Forbidden returned");
						}
					}
					else {
						// Store the authentication level so it can be extracted by the CXF-DOSGi-called implementation method
						message.put("authentication", AuthLevel.NORMAL);
					}
				}
				else {
					// Store the authentication level so it can be extracted by the CXF-DOSGi-called implementation method
					message.put("authentication", AuthLevel.FULL);
				}
			}
		}
	}

	/**
	 * Send an error response if the user failed to gain access
	 * @param message error message to send
	 * @param responseCode error code to return
	 */
	private void sendErrorResponse(Message message, int responseCode) {
		Message outMessage = getOutMessage(message);
		outMessage.put(Message.RESPONSE_CODE, responseCode);

		// Set the response headers
		@SuppressWarnings("unchecked")
		Map<String, List<String>> responseHeaders = (Map<String, List<String>>)message.get(Message.PROTOCOL_HEADERS);
		if (responseHeaders != null) {
			responseHeaders.put("WWW-Authenticate", Arrays.asList(new String[]{"Basic realm=realm"}));
			responseHeaders.put("Content-Length", Arrays.asList(new String[]{"0"}));
		}
		message.getInterceptorChain().abort();
		try {
			getConduit(message).prepare(outMessage);
			close(outMessage);
		} catch (IOException e) {
			Log(e.getMessage());
		}
	}

	/**
	 * Get the message to output
	 * @param inMessage the input message
	 * @return
	 */
	private Message getOutMessage(Message inMessage) {
		Exchange exchange = inMessage.getExchange();
		Message outMessage = exchange.getOutMessage();
		if (outMessage == null) {
			Endpoint endpoint = exchange.get(Endpoint.class);
			Binding binding = endpoint.getBinding();
			outMessage = binding.createMessage();
			exchange.setOutMessage(outMessage);
		}
		outMessage.putAll(inMessage);
		return outMessage;
	}

	/**
	 * Get the conduit for returning the error message
	 * @param inMessage the message to return
	 * @return the conduit to return the message on
	 * @throws IOException
	 */
	private Conduit getConduit(Message inMessage) throws IOException {
		Exchange exchange = inMessage.getExchange();
		EndpointReferenceType target = exchange.get(EndpointReferenceType.class);
		Conduit conduit = exchange.getDestination().getBackChannel(inMessage, null, target);
		exchange.setConduit(conduit);
		return conduit;
	}

	/**
	 * Send the message to the output stream
	 * @param outMessage the message to output
	 * @throws IOException
	 */
	private void close(Message outMessage) throws IOException {
		OutputStream os = outMessage.getContent(OutputStream.class);
		os.flush();
		os.close();
	}
}