/**
Copyright (c) 2014, SEARCH-LAB Ltd. (http://www.search-lab.hu)
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of SEARCH-LAB Ltd. nor the names of its contributors 
      may be used to endorse or promote products derived from this software 
      without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL SEARCH-LAB LTD. BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
**/

package eu.aniketos.csstm.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.bind.JAXBException;


public class CSSTMUtils {

	public static String httpGet(String url, String username, String password)
	{
		String res = "";
		try {
			String ln;
			URL uri = new URL(url);
			HttpURLConnection con = (HttpURLConnection) uri.openConnection();
			con.setRequestMethod("GET");
			
			if (username != null) {
				String authdata = Base64.encodeBytes(new String(username + ":"
						+ password).getBytes());
				con.setRequestProperty("Authorization", "Basic " + authdata);
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while ((ln = br.readLine()) != null) {
				res+=ln;
			}
			br.close();
			con.disconnect();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return res;
	}


	/**
	 * Function for handling TR <-> SVRS connections. 
	 * 
	 * @return A Live HttpsURLConnection object, to be used in further
	 *         operations.
	 * @param uri
	 *            An URL to open a connection to.
	 * @throws MalformedURLException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	public static HttpsURLConnection Connect(String uri) throws MalformedURLException,
			NoSuchAlgorithmException, KeyManagementException, IOException,
			GeneralSecurityException {
		HttpsURLConnection connection = null;
		URL url = new URL(uri);

		// This is to accept all certificates, even untrusted or unverified ones
		javax.net.ssl.TrustManager[] trustAll = new javax.net.ssl.TrustManager[] { new javax.net.ssl.X509TrustManager() {

			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(
					java.security.cert.X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(
					java.security.cert.X509Certificate[] certs, String authType) {
			}
		} };
		final javax.net.ssl.SSLContext sslContext = javax.net.ssl.SSLContext
				.getInstance("SSL");
		sslContext.init(null, trustAll, new java.security.SecureRandom());
		// java.security.Security.addProvider(new
		// com.sun.net.ssl.internal.ssl.Provider());
		HttpsURLConnection
				.setDefaultSSLSocketFactory((javax.net.ssl.SSLSocketFactory) sslContext
						.getSocketFactory());
		HttpsURLConnection
				.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {

					public boolean verify(String hostname,
							javax.net.ssl.SSLSession session) {
						return true;
					}
				});
		connection = (HttpsURLConnection) url.openConnection();
		if (connection == null) {
			throw new java.security.GeneralSecurityException();
		}
		connection.setConnectTimeout(20000);
		connection.setReadTimeout(5000);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type", "application/xml");

		return connection;
	}
}
