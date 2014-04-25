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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.jws.WebMethod;
import javax.jws.WebService;

import eu.aniketos.components.verification.compositionsecurityvalidation.CompositionSecurityValidationService;
import eu.aniketos.components.verification.propertyverification.PropertyVerificationService;
import eu.aniketos.data.ICompositionPlan;
import eu.aniketos.data.IConsumerPolicy;
import eu.aniketos.data.ISPDMService;
import eu.aniketos.marketplace.IMarketplace;
import eu.aniketos.ncvm.INestedCompositionVerification;
import eu.aniketos.ncvm.IVerificationResult;
import eu.aniketos.spec.PolicyFormatException;

//@WebService(endpointInterface = "eu.aniketos.wp3.ncvm.INestedCompositionVerification")
/**
 * The implementation of the NCVM INestedCompositionVerification code.
 * Recursively call other services to determine whether a policy applies to it
 * Performs the task of deconstructing a service into its subservices in order
 * to select the correct module to invoke for verification.
 * 
 * @author LJMU/David Llewellyn-Jones
 * 
 */
@WebService
public class NestedCompositionVerification implements INestedCompositionVerification {

	static enum ServiceType {
	    COMPOSITE,
	    ATOMIC,
	    UNKNOWN,
	    ERROR,
	    INVALID
	}
	
	static class ServiceInfo {
		public ServiceType type;
		public String details;

		public ServiceInfo () {
			type = ServiceType.ATOMIC;
			details = "http://www.flypig.co.uk/dnload/dnload/aniketos/example-safe.zip";
		}
	}
	
	static class AniketosServices {
		PropertyVerificationService pvm = null;
		ISPDMService spdm = null;
		CompositionSecurityValidationService csvm = null;
		IMarketplace marketplace = null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eu.aniketos.wp3.ncvm.INestedCompositionVerification#verifyProperty(eu
	 * .aniketos.data.ICompositionPlan, eu.aniketos.data.IConsumerPolicy)
	 */
	@Override
	public IVerificationResult verifyProperty(ICompositionPlan service, IConsumerPolicy policy) {
		Activator.logLineCheck("NCVM received verifyProperty request");
		Activator.logLineCheck("Authentication level: " + BasicAuthAuthorizationInterceptor.getAuthLevel().toString());

		IVerificationResult result = new VerificationResult();
		result.setResult(1);

		// Get the objects for calling the remote services we need
		AniketosServices call = ModuleSupport.ConnectToAniketosServices ();

		// Pull the XML from the service and policy
		// Commented lines are provided for testing
		String encodedFile = service.getBPMNXML();
		String bpmn = EncodeSupport.decodeData(encodedFile);

		String[] policies = policy.getXmlContents();
		
		boolean failed = false;
		for (int policyNum = 0; (policyNum < policies.length) && (failed == false); policyNum++) {
			String encodedPolicy = policies[policyNum];

			String conspec = EncodeSupport.decodeData(encodedPolicy);

			// Parse the ConSpec policy
			eu.aniketos.spec.Specification spec = new eu.aniketos.spec.Specification();
			String propertyID = "";
			try {
				InputStream input = new ByteArrayInputStream(conspec.getBytes());
				spec.load(input);
				Activator.logLine("ConSpec data correctly parsed");
			} catch (IOException e) {
				Activator.logLine("Input/output exception while interpreting ConSpec file.");
			} catch (PolicyFormatException e) {
				Activator.logLine("ConSpec file format failed to parse.");
				e.printStackTrace();
			} catch (Exception e) {
				Activator.logLine("Error reading ConSpec file.");
				e.printStackTrace();
			}

			// Establish the property ID from the ConSpec file
			propertyID = ConspecSupport.getConspecIdentifier(spec);
			Activator.logLine("Property ID: " + propertyID);

			// Establish whether the ConSpec file specifies a particular service
			String serviceFilter = ConspecSupport.getConspecService (spec);	
			Activator.logLine("Apply to: " + (serviceFilter.isEmpty()?"all services":serviceFilter));

			// Perform the verification
			VerifyComposite verify = new VerifyComposite (null, propertyID, conspec, call, bpmn, serviceFilter);
						
			IVerificationResult partial = verify.verify();

			if (partial.getErrorValue() != 0) {
				result = partial;
				failed = true;
			}
			else if (partial.getResult() <= 0) {
				result = partial;
				failed = true;
			}
		}
		
		Activator.logLine("Output: " + result.getResult());

		return result;
	}

	@Override
	public IVerificationResult verifyPropertyDeployed(String serviceID, IConsumerPolicy policy) {
		Activator.logLineCheck("NCVM received verifyPropertyDeployed request");
		Activator.logLineCheck("Authentication level: " + BasicAuthAuthorizationInterceptor.getAuthLevel().toString());

		IVerificationResult result = new VerificationResult();
		result.setResult(1);

		// Get the objects for calling the remote services we need
		AniketosServices call = ModuleSupport.ConnectToAniketosServices ();

		String[] policies = policy.getXmlContents();
		
		boolean failed = false;
		for (int policyNum = 0; (policyNum < policies.length) && (failed == false); policyNum++) {
			String encodedPolicy = policies[policyNum];

			String conspec = EncodeSupport.decodeData(encodedPolicy);

			// Parse the ConSpec policy
			eu.aniketos.spec.Specification spec = new eu.aniketos.spec.Specification();
			String propertyID = "";
			try {
				InputStream input = new ByteArrayInputStream(conspec.getBytes());
				spec.load(input);
				Activator.logLine("ConSpec data correctly parsed");
			} catch (IOException e) {
				Activator.logLine("Input/output exception while interpreting ConSpec file.");
			} catch (PolicyFormatException e) {
				Activator.logLine("ConSpec file format failed to parse.");
				e.printStackTrace();
			} catch (Exception e) {
				Activator.logLine("Error reading ConSpec file.");
				e.printStackTrace();
			}

			// Establish the property ID from the ConSpec file
			propertyID = ConspecSupport.getConspecIdentifier(spec);
			Activator.logLine("Property ID: " + propertyID);

			// Collect details about the service from the Marketplace
			Activator.logLine("Marketplace, requesting service: " + serviceID);
			ServiceInfo serviceInfo = ModuleSupport.FindServiceInfo(call.marketplace, serviceID);
			
			// Establish whether the ConSpec file specifies a particular service
			String serviceFilter = ConspecSupport.getConspecService (spec);	
			Activator.logLine("Apply to: " + (serviceFilter.isEmpty()?"all services":serviceFilter));

			switch (serviceInfo.type) {
			case ATOMIC:
			{
				Activator.logLine("Atomic service");
				// Perform the verification
				VerifyAtomic verify = new VerifyAtomic(serviceID, propertyID, conspec, call, serviceInfo.details, serviceFilter);
				result = verify.verify();
			}
				break;
			case COMPOSITE:
			{
				Activator.logLine("Composite service");
				// Perform the verification
				VerifyComposite verify = new VerifyComposite(serviceID, propertyID, conspec, call, serviceInfo.details, serviceFilter);
				result = verify.verify();
			}
				break;
			case ERROR:
				Activator.logLine("Error: Marketplace error when determining composite/atomic type");
				result.setError(4, "Marketplace error");
				break;
			default:
				Activator.logLine("Error: unknown composite/atomic type");
				result.setError(4, "Unknown service type");
				break;
			}

			if (result.getErrorValue() != 0) {
				failed = true;
			}
			else if (result.getResult() <= 0) {
				failed = true;
			}
		}

		if (!failed) {
			Activator.logLine("Output: " + result.getResult());
		}
		else {
			Activator.logLine("Output error: " + result.getErrorExplanation());
		}
		
		return result;
	}

	@Override
	@WebMethod
	public void configureNCVMFeedback(String wsdlUrl, boolean useTracker) {
		Activator.getDefault().getSettings().setNcvmFeedbackWsdl(wsdlUrl);
		Activator.getDefault().getSettings().setNcvmFeedbackTrack(useTracker);
		Activator.getDefault().getServices().initialiseNCVMFeedback();
	}

	@Override
	@WebMethod
	public void configureCSVM(String wsdlUrl, boolean useTracker) {
		Activator.getDefault().getSettings().setCsvmWsdl(wsdlUrl);
		Activator.getDefault().getSettings().setCsvmTrack(useTracker);
		Activator.getDefault().getServices().initialiseCSVM();
	}

	@Override
	@WebMethod
	public void configurePVM(String wsdlUrl, boolean useTracker) {
		Activator.getDefault().getSettings().setPvmWsdl(wsdlUrl);
		Activator.getDefault().getSettings().setPvmTrack(useTracker);
		Activator.getDefault().getServices().initialisePVM();
	}

	@Override
	@WebMethod
	public void configureSPDM(String wsdlUrl, boolean useTracker) {
		Activator.getDefault().getSettings().setSpdmWsdl(wsdlUrl);
		Activator.getDefault().getSettings().setSpdmTrack(useTracker);
		Activator.getDefault().getServices().initialiseSPDM();
	}

	@Override
	@WebMethod
	public void configureMarketplace(String wsdlUrl, boolean useTracker) {
		Activator.getDefault().getSettings().setMarketplaceWsdl(wsdlUrl);
		Activator.getDefault().getSettings().setMarketplaceTrack(useTracker);
		Activator.getDefault().getServices().initialiseMarketplace();
	}

	@Override
	public void performTests () {
		Activator.logLine("NCVM tests");
		Activator.logLineCheck("Authentication level: " + BasicAuthAuthorizationInterceptor.getAuthLevel().toString());
		Tests.performTests();
	}
}
