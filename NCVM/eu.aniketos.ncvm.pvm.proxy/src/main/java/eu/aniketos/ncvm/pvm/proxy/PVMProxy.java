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

package eu.aniketos.ncvm.pvm.proxy;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import eu.aniketos.components.verification.propertyverification.PropertyVerificationResult;
import eu.aniketos.components.verification.propertyverification.PropertyVerificationResultImpl;
import eu.aniketos.components.verification.propertyverification.PropertyVerificationService;
import eu.aniketos.ncvm.pvm.client.PropertyVerificationServiceClient;
import eu.aniketos.ncvm.pvm.client.PropertyVerificationServicePortType;
import eu.aniketos.ncvm.userinterface.proxy.INCVMProxy;

public class PVMProxy implements PropertyVerificationService, INCVMProxy {
	private URL wsdlURL = PropertyVerificationServiceClient.WSDL_LOCATION;
	private final static int timeout = 5 * 60 * 1000; // in milliseconds
	private final static String username = "Guest";
	private final static String password = "Guest";

	@Override
	public void setURL(String wsdlURL) {
		try {
			this.wsdlURL = new URL(wsdlURL);
		} catch (MalformedURLException e) {
			System.out.println("Failed to set PVM URL to " + wsdlURL);
		}
	}

	@Override
	public PropertyVerificationResult verifyTechnicalTrustProperties(String AgreementTemplate, String ServiceImplementationUrl) {
		QName SERVICE_NAME = new QName("http://propertyverification.verification.components.aniketos.eu/", "PropertyVerificationService");

		PropertyVerificationServicePortType servicePort = null;
		PropertyVerificationServiceClient service = new PropertyVerificationServiceClient(wsdlURL, SERVICE_NAME);
		PropertyVerificationResult result = null;
		eu.aniketos.ncvm.pvm.client.PropertyVerificationResult resultWS = null;

		try {
			servicePort = service.getPropertyVerificationServicePort();

			// Authenticate using BASIC HTTP username and password
			Map<String, Object> requestContext = ((BindingProvider)servicePort).getRequestContext();
			requestContext.put(BindingProvider.USERNAME_PROPERTY, username);
			requestContext.put(BindingProvider.PASSWORD_PROPERTY, password);
			requestContext.put("javax.xml.ws.client.receiveTimeout", Integer.toString(timeout));
		}
		catch (Exception exception) {
			System.out.println("PVM Proxy exception: " + exception.getMessage());
		}

		if (servicePort != null) {
			resultWS = servicePort.verifyTechnicalTrustProperties(AgreementTemplate, ServiceImplementationUrl);
			result = new PropertyVerificationResultImpl(resultWS.getVerificationResult(), resultWS.getVerificationExplaination().getValue());
		}

		return result;
	}

	@Override
	public PropertyVerificationResult verifyWSDL(String inputSuite,
			String inputSchema, String inputAlgorithm, int inputKeyLength,
			String outputSuite, String outputSchema, String outputAlgorithm,
			int outputKeyLength, String ServiceImplementationUrl) {

		QName SERVICE_NAME = new QName("http://propertyverification.verification.components.aniketos.eu/", "PropertyVerificationService");

		PropertyVerificationServicePortType servicePort = null;
		PropertyVerificationServiceClient service = new PropertyVerificationServiceClient(wsdlURL, SERVICE_NAME);
		PropertyVerificationResult result = null;
		eu.aniketos.ncvm.pvm.client.PropertyVerificationResult resultWS = null;

		try {
			servicePort = service.getPropertyVerificationServicePort();

			// Authenticate using BASIC HTTP username and password
			Map<String, Object> requestContext = ((BindingProvider)servicePort).getRequestContext();
			requestContext.put(BindingProvider.USERNAME_PROPERTY, username);
			requestContext.put(BindingProvider.PASSWORD_PROPERTY, password);
			requestContext.put("javax.xml.ws.client.receiveTimeout", Integer.toString(timeout));
		}
		catch (Exception exception) {
			System.out.println("PVM Proxy exception: " + exception.getMessage());
		}

		if (servicePort != null) {
			resultWS = servicePort.verifyWSDL(inputSuite, inputSchema, inputAlgorithm, inputKeyLength, outputSuite, outputSchema, outputAlgorithm, outputKeyLength, ServiceImplementationUrl);
			result = new PropertyVerificationResultImpl(resultWS.getVerificationResult(), resultWS.getVerificationExplaination().getValue());
		}
		
		return result;
	}
}
