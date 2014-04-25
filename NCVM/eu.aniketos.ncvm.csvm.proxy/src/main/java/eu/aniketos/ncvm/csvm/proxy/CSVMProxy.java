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

package eu.aniketos.ncvm.csvm.proxy;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import eu.aniketos.components.verification.compositionsecurityvalidation.CompositionSecurityValidationResult;
import eu.aniketos.components.verification.compositionsecurityvalidation.CompositionSecurityValidationResultImpl;
import eu.aniketos.components.verification.compositionsecurityvalidation.CompositionSecurityValidationService;
import eu.aniketos.data.ICompositionPlan;
import eu.aniketos.ncvm.csvm.client.CompositionSecurityValidationServiceClient;
import eu.aniketos.ncvm.csvm.client.CompositionSecurityValidationServicePortType;
import eu.aniketos.ncvm.csvm.client.ObjectFactory;
import eu.aniketos.ncvm.userinterface.proxy.INCVMProxy;

public class CSVMProxy implements CompositionSecurityValidationService, INCVMProxy {
	private URL wsdlURL = CompositionSecurityValidationServiceClient.WSDL_LOCATION;
	private final static int timeout = 5 * 60 * 1000; // in milliseconds
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

	private eu.aniketos.ncvm.csvm.client.ICompositionPlan convertCompositionPlanToWS(ICompositionPlan service) {
		ObjectFactory factory = new ObjectFactory();
		eu.aniketos.ncvm.csvm.client.ICompositionPlan result = factory.createICompositionPlan();

		result.setActivitiFile(factory.createICompositionPlanActivitiFile(service.getActivitiFile()));
		result.setBPMNXML(factory.createICompositionPlanBPMNXML(service.getBPMNXML()));
		result.setCompositionPlanID(factory.createICompositionPlanCompositionPlanID(service.getCompositionPlanID()));

		return result;
	}

	@Override
	public CompositionSecurityValidationResult VerifyCompositionCompliance(ICompositionPlan CompositionPlan) {
		QName SERVICE_NAME = new QName("http://compositionsecurityvalidation.verification.components.aniketos.eu/", "CompositionSecurityValidationService");
		CompositionSecurityValidationServicePortType servicePort = null;
		CompositionSecurityValidationServiceClient service = new CompositionSecurityValidationServiceClient(wsdlURL, SERVICE_NAME);
		eu.aniketos.ncvm.csvm.client.CompositionSecurityValidationResult resultWS;
		CompositionSecurityValidationResult result = null;

		try {
			servicePort = service.getCompositionSecurityValidationServicePort();

			// Authenticate using BASIC HTTP username and password
			Map<String, Object> requestContext = ((BindingProvider)servicePort).getRequestContext();
			requestContext.put(BindingProvider.USERNAME_PROPERTY, username);
			requestContext.put(BindingProvider.PASSWORD_PROPERTY, password);
			requestContext.put("javax.xml.ws.client.receiveTimeout", Integer.toString(timeout));
		}
		catch (Exception exception) {
			System.out.println("CSVM Proxy exception: " + exception.getMessage());
		}

		if (servicePort != null) {
			resultWS = servicePort.verifyCompositionCompliance(convertCompositionPlanToWS(CompositionPlan));
			result = new CompositionSecurityValidationResultImpl(resultWS.getVerificationResult().getValue(), resultWS.getVerificationExplaination().getValue());
		}

		return result;
	}

}
