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

package eu.aniketos.ncvm.proxy;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import eu.aniketos.data.ICompositionPlan;
import eu.aniketos.data.IConsumerPolicy;
import eu.aniketos.data.ISecurityProperty;
import eu.aniketos.data.SPState;
import eu.aniketos.ncvm.INestedCompositionVerification;
import eu.aniketos.ncvm.IVerificationResult;
import eu.aniketos.ncvm.client.ArrayOfString;
import eu.aniketos.ncvm.client.INestedCompositionVerificationClient;
import eu.aniketos.ncvm.client.INestedCompositionVerificationPortType;
import eu.aniketos.ncvm.client.ObjectFactory;
import eu.aniketos.ncvm.client.ArrayOfISecurityProperty;
import eu.aniketos.ncvm.impl.VerificationResult;
import eu.aniketos.ncvm.userinterface.proxy.INCVMProxy;

public class NCVMProxy implements INestedCompositionVerification, INCVMProxy{
	private URL wsdlURL = INestedCompositionVerificationClient.WSDL_LOCATION;
	private final static int timeout = 15 * 60 * 1000; // in milliseconds
	private final static String username = "Guest";
	private final static String password = "Guest";

	@Override
	public void setURL(String wsdlURL) {
		try {
			this.wsdlURL = new URL(wsdlURL);
		} catch (MalformedURLException e) {
			System.out.println("Failed to set CSVM URL to " + wsdlURL);
		}
	}

	private INestedCompositionVerificationPortType getPort() {
		QName SERVICE_NAME = new QName("http://ncvm.aniketos.eu/", "INestedCompositionVerification");

		INestedCompositionVerificationPortType servicePort = null;
		INestedCompositionVerificationClient service = new INestedCompositionVerificationClient(wsdlURL, SERVICE_NAME);

		try {
			servicePort = service.getINestedCompositionVerificationPort();

			// Authenticate using BASIC HTTP username and password
			Map<String, Object> requestContext = ((BindingProvider)servicePort).getRequestContext();
			requestContext.put(BindingProvider.USERNAME_PROPERTY, username);
			requestContext.put(BindingProvider.PASSWORD_PROPERTY, password);
			requestContext.put("javax.xml.ws.client.receiveTimeout", Integer.toString(timeout));
		}
		catch (Exception exception) {
			System.out.println("NCVM Proxy exception: " + exception.getMessage());
		}

		return servicePort;
	}

	private eu.aniketos.ncvm.client.ICompositionPlan convertCompositionPlanToWS(ICompositionPlan service) {
		ObjectFactory factory = new ObjectFactory();
		eu.aniketos.ncvm.client.ICompositionPlan result = factory.createICompositionPlan();

		result.setActivitiFile(factory.createICompositionPlanActivitiFile(service.getActivitiFile()));
		result.setBPMNXML(factory.createICompositionPlanBPMNXML(service.getBPMNXML()));
		result.setCompositionPlanID(factory.createICompositionPlanCompositionPlanID(service.getCompositionPlanID()));

		return result;
	}

	private eu.aniketos.ncvm.client.IConsumerPolicy convertConsumerPolicyToWS(IConsumerPolicy policy) {
		ObjectFactory factory = new ObjectFactory();
		eu.aniketos.ncvm.client.IConsumerPolicy result = factory.createIConsumerPolicy();

		result.setXML(factory.createIConsumerPolicyXML(policy.getXML()));
		result.setProperties(factory.createISecurityDescriptorProperties(convertSecurityPropertiesToWS(policy.getProperties())));
		result.setXmlContents(factory.createIConsumerPolicyXmlContents(convertStringArrayToWS(policy.getXmlContents())));

		return result;
	}

	private eu.aniketos.ncvm.client.ISecurityProperty convertSecurityPropertyToWS(ISecurityProperty property) {
		eu.aniketos.ncvm.client.ISecurityProperty result = new eu.aniketos.ncvm.client.ISecurityProperty();
		ObjectFactory factory = new ObjectFactory();

		result.setPropertyID(factory.createISecurityPropertyPropertyID(property.getPropertyID()));
		result.setPropertyValue(factory.createISecurityPropertyPropertyValue(property.getPropertyValue()));

		try {
			Date freshness = property.getFreshness();
			if (freshness != null) {
				GregorianCalendar gregorianCalendar = new GregorianCalendar();
				gregorianCalendar.setTime(freshness);
				result.setFreshness(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar));
			}
		} catch (DatatypeConfigurationException e) {
			System.out.printf("Failed to convert date: " + e.getLocalizedMessage());
		}

		SPState state = property.getState();
		if (state != null) {
			result.setState(factory.createISecurityPropertyState(eu.aniketos.ncvm.client.SPState.valueOf(state.name())));
		}

		//TODO: Add code for converting X509Certificate
		X509Certificate certificate = property.getCertificate();
		if (certificate != null) {
			System.out.println("Warning: X509Certificate data lost, see " + this.getClass().getName());
		}

		return result;
	}

	private ArrayOfISecurityProperty convertSecurityPropertiesToWS(List<ISecurityProperty> properties) {
		ArrayOfISecurityProperty result = new ArrayOfISecurityProperty();

		result.getISecurityProperty().clear();
		if (properties != null) {
			Iterator<ISecurityProperty> iter = properties.iterator();
			while (iter.hasNext()) {
				result.getISecurityProperty().add(convertSecurityPropertyToWS(iter.next()));
			}
		}
		return result;
	}

	private ArrayOfString convertStringArrayToWS(String[] strings) {
		ArrayOfString result = new ArrayOfString();
		
		result.getString().clear();
		for (String string : strings) {
			result.getString().add(string);
		}
		
		return result;
	}
	
	@Override
	@WebMethod
	public IVerificationResult verifyProperty(ICompositionPlan service, IConsumerPolicy policy) {
		ObjectFactory factory = new ObjectFactory();
		INestedCompositionVerificationPortType servicePort = getPort();
		IVerificationResult result = new VerificationResult();
		eu.aniketos.ncvm.client.IVerificationResult verificationResult = factory.createIVerificationResult();

		if (servicePort != null) {
			verificationResult = servicePort.verifyProperty(convertCompositionPlanToWS(service), convertConsumerPolicyToWS(policy));
			result.setResult(verificationResult.getResult());
			result.setError(verificationResult.getErrorValue(), verificationResult.getErrorExplanation().getValue());
		}

		return result;
	}

	@Override
	@WebMethod
	public IVerificationResult verifyPropertyDeployed(String serviceID, IConsumerPolicy policy) {
		ObjectFactory factory = new ObjectFactory();
		INestedCompositionVerificationPortType servicePort = getPort();
		IVerificationResult result = new VerificationResult();
		eu.aniketos.ncvm.client.IVerificationResult verificationResult = factory.createIVerificationResult();

		if (servicePort != null) {
			verificationResult = servicePort.verifyPropertyDeployed(serviceID, convertConsumerPolicyToWS(policy));
			result.setResult(verificationResult.getResult());
			result.setError(verificationResult.getErrorValue(), verificationResult.getErrorExplanation().getValue());
		}

		return result;
	}

	@Override
	@WebMethod
	public void configureNCVMFeedback(String wsdlUrl, boolean useTracker) {
		INestedCompositionVerificationPortType servicePort = getPort();

		if (servicePort != null) {
			servicePort.configureNCVMFeedback(wsdlUrl, useTracker);
		}
	}

	@Override
	@WebMethod
	public void configureCSVM(String wsdlUrl, boolean useTracker) {
		INestedCompositionVerificationPortType servicePort = getPort();

		if (servicePort != null) {
			servicePort.configureCSVM(wsdlUrl, useTracker);
		}
	}

	@Override
	@WebMethod
	public void configurePVM(String wsdlUrl, boolean useTracker) {
		INestedCompositionVerificationPortType servicePort = getPort();

		if (servicePort != null) {
			servicePort.configurePVM(wsdlUrl, useTracker);
		}
	}

	@Override
	@WebMethod
	public void configureSPDM(String wsdlUrl, boolean useTracker) {
		INestedCompositionVerificationPortType servicePort = getPort();

		if (servicePort != null) {
			servicePort.configureSPDM(wsdlUrl, useTracker);
		}
	}

	@Override
	@WebMethod
	public void configureMarketplace(String wsdlUrl, boolean useTracker) {
		INestedCompositionVerificationPortType servicePort = getPort();

		if (servicePort != null) {
			servicePort.configureMarketplace(wsdlUrl, useTracker);
		}
	}

	@Override
	@WebMethod
	public void performTests() {
		INestedCompositionVerificationPortType servicePort = getPort();

		if (servicePort != null) {
			servicePort.performTests();
		}
	}
}
