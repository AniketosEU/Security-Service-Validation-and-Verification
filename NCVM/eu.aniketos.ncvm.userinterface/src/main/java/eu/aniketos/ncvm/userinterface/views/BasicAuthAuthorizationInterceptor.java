package eu.aniketos.ncvm.userinterface.views;

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

import eu.aniketos.ncvm.userinterface.Activator;

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
/**
 * @author flypig
 *
 */
public class BasicAuthAuthorizationInterceptor extends SoapHeaderInterceptor {

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
	 * Map of users and their passwords hashed using the Apache MD5 algorithm.
	 * Initially this is just set to the default Aniketos password.
	 */
	private Map<String,String> users;

	/**
	 * Class constructor sets up the initial username and password list
	 */
	public BasicAuthAuthorizationInterceptor() {
		authenticationActive = true;
		users = new Hashtable<String,String>();
		users.clear();
		// Password uses the Apache MD5 hashing algorithm
		//addUser("Aniketos", "$apr1$BnkINIkJ$j66rMhBdb7plJ4q472ohd/");
		try {
			LoadUserFileDefault();
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
	 * Replace the current set of users with a new list
	 * @param users the map of users and their Apache MD5-hashed passwords to replace the current list with
	 */
	public void setUsers(Map<String, String> users) {
		this.users = users;
	}

	/**
	 * Add a single user to the authentication list
	 * Use httpasswd to generate password hashes.
	 * For info about the hashing algorithm, see https://httpd.apache.org/docs/2.2/misc/password_encryptions.html
	 * @param user the user to add
	 * @param password an Apache MD5-hash of the uer's password
	 */
	public void addUser(String user, String password) {
		this.users.put(user, password);
	}

	/**
	 * Load the username and passwords from a file in Apache hash format
	 * For info about the hashing algorithm, see https://httpd.apache.org/docs/2.2/misc/password_encryptions.html
	 * @throws IOException
	 */
	public void LoadUserFileDefault() throws IOException {
		LoadUserFile("configure/users.txt");
	}

	/**
	 * Load the username and passwords from a given file in Apache hash format
	 * For info about the hashing algorithm, see https://httpd.apache.org/docs/2.2/misc/password_encryptions.html
	 * @param file the file to load the user info from
	 * @throws IOException
	 */
	public void LoadUserFile(String file) throws IOException {
		BundleContext context = Activator.getContext();
		// Load data from the configuration file if there is one
		System.out.println("Reading users from: " + file);
		URL configURL = context.getBundle().getEntry(file);
		if (configURL != null) {
			users.clear();
			BufferedReader input = new BufferedReader(new InputStreamReader(configURL.openStream()));
			try {
				// Read in each user
				int users = 0;
				String userPass = "";
				while (userPass != null) {
					userPass = input.readLine();
					if (userPass != null) {
						int separator = userPass.indexOf(':');
						if (separator >= 0) {
							String user = userPass.substring(0, separator);
							String pass = userPass.substring(separator + 1);
							// Add the user to the list of authorised users
							addUser(user, pass);
							users++;
						}
					}
				}
				if (users == 0) {
					authenticationActive = false;
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
		// If authentication is turned off we don't have to do anything
		if (authenticationActive) {
			// This is set by CXF
			AuthorizationPolicy policy = message.get(AuthorizationPolicy.class);
	
			// If the policy is not set, the user did not specify credentials
			// A 401 is sent to the client to indicate that authentication is required
			if (policy == null) {
				Log("User attempted to log in with no credentials");
				sendErrorResponse(message, HttpURLConnection.HTTP_UNAUTHORIZED);
			}
			else {
				// Verify the password
				Log("Authenticating user: " + policy.getUserName() + " (" + this.getClass().getPackage() + ")");
				String realPasswordCrypt = users.get(policy.getUserName());
				if (realPasswordCrypt == null || !MD5Crypt.verifyPassword(policy.getPassword(), realPasswordCrypt)) {
					Log("Invalid username or password for user: " + policy.getUserName());
					sendErrorResponse(message, HttpURLConnection.HTTP_FORBIDDEN);
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