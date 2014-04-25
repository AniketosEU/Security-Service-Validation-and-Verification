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

package eu.aniketos.ncvm.userinterface.proxy;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.jws.WebParam;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import eu.aniketos.ncvm.userinterface.INCVMFeedback;
import eu.aniketos.ncvm.userinterface.client.INCVMFeedbackClient;
import eu.aniketos.ncvm.userinterface.client.INCVMFeedbackPortType;

public class NCVMFeedbackProxy implements INCVMFeedback, INCVMProxy {
	private URL wsdlURL = INCVMFeedbackClient.WSDL_LOCATION;
	private final static int timeout = 1 * 60 * 1000; // in milliseconds
	private final static String username = "Guest";
	private final static String password = "Guest";

	@Override
	public void setURL(String wsdlURL) {
		try {
			this.wsdlURL = new URL(wsdlURL);
		} catch (MalformedURLException e) {
			System.out.println("Failed to set NCVM Feedback URL to " + wsdlURL);
		}
	}

	@Override
	public void logLine(@WebParam(name = "text") String text) {
		QName SERVICE_NAME = new QName("http://userinterface.ncvm.aniketos.eu/", "INCVMFeedback");
		INCVMFeedbackPortType servicePort = null;

		INCVMFeedbackClient service = new INCVMFeedbackClient(wsdlURL, SERVICE_NAME);

		try {
			servicePort = service.getINCVMFeedbackPort();

			// Authenticate using BASIC HTTP username and password
			Map<String, Object> requestContext = ((BindingProvider)servicePort).getRequestContext();
			requestContext.put(BindingProvider.USERNAME_PROPERTY, username);
			requestContext.put(BindingProvider.PASSWORD_PROPERTY, password);
			requestContext.put("javax.xml.ws.client.receiveTimeout", Integer.toString(timeout));
		}
		catch (Exception exception) {
			System.out.println("NCVM Feedback Proxy exception: " + exception.getMessage());
		}

		if (servicePort != null) {
			servicePort.logLine(text);
		}
	}
}
