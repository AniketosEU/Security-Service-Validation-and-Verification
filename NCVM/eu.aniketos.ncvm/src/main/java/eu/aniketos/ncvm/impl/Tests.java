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
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.osgi.framework.BundleContext;

import eu.aniketos.components.verification.compositionsecurityvalidation.CompositionSecurityValidationResult;
import eu.aniketos.components.verification.compositionsecurityvalidation.CompositionSecurityValidationService;
import eu.aniketos.components.verification.propertyverification.PropertyVerificationResult;
import eu.aniketos.components.verification.propertyverification.PropertyVerificationService;
import eu.aniketos.data.IAgreementTemplate;
import eu.aniketos.data.ISPDMService;
import eu.aniketos.data.ISecurityProperty;
import eu.aniketos.data.impl.AgreementTemplate;
import eu.aniketos.marketplace.CompositionPlan;
import eu.aniketos.marketplace.IMarketplace;
import eu.aniketos.marketplace.MarketplaceSearchParams;
import eu.aniketos.marketplace.ServiceDescriptor;
import eu.aniketos.ncvm.impl.NestedCompositionVerification.AniketosServices;

/**
 * Class for performing various tests on the external service.
 * @author LJMU/David Llewellyn-Jones
 *
 */
class Tests {
	private static String details = "";
	private static String conspec = "";
	private static String bpmn = "";
	private static String serviceID = "";
	private static String propertyID = "";

	/**
	 * Perform various tests on the external classes.
	 * This checks the external services are accessible. If they are, it performs
	 * tests to check the functionality required by the NCVM is available and
	 * that the results match expectations.
	 */
	static void performTests () {
		AniketosServices call = ModuleSupport.ConnectToAniketosServices ();
		boolean[] passed = new boolean[4];
		Map<String, String> configuration = new HashMap<String, String>();
		try {
			configuration = EncodeSupport.LoadKeyValueFile ("tests/tests.txt");
		} catch (IOException e1) {
			// Do nothing; we just continue
		}

		// Now do something with the loaded configuration
		if (configuration.containsKey("details")) {
			details = configuration.get("details");
		}
		if (configuration.containsKey("serviceid")) {
			serviceID = configuration.get("serviceid");
		}
		if (configuration.containsKey("propertyid")) {
			propertyID = configuration.get("propertyid");
		}
		
		try {
			conspec = LoadTestFile ("tests/conspec.xml");
			bpmn = LoadTestFile ("tests/bpmn.xml");
		} catch (IOException e) {
			Activator.logLine("Failed to load test files.");
		}

		Activator.logLine("Testing CSVM remote service.");
		passed[0] = testCSVM (call.csvm, bpmn, conspec);
		Activator.logLine("Testing PVM remote service.");
		passed[1] = testPVM (call.pvm, conspec, details);
		Activator.logLine("Testing SPDM remote service.");
		passed[2] = testSPDM (call.spdm, serviceID, propertyID);
		Activator.logLine("Testing Marketplace remote service.");
		passed[3] = testMarketplace (call.marketplace, serviceID);

		Activator.logLine("");
		Activator.logLine("CSVM tests " + (passed[0]?"passed":"failed"));
		Activator.logLine("PVM tests " + (passed[1]?"passed":"failed"));
		Activator.logLine("SPDM tests " + (passed[2]?"passed":"failed"));
		Activator.logLine("Marketplace tests " + (passed[3]?"passed":"failed"));
		Activator.logLine("All tests completed");
	}
	
	/**
	 * Load a complete text file into a string for use by the testing process.
	 * @param file the pathname of the file to read in.
	 * @return the contents of the file.
	 * @throws IOException generated if there is a problem while attempting to read from the file.
	 */
	private static String LoadTestFile (String file) throws IOException {
		String contents = "";
		BundleContext context = Activator.getContext();
		System.out.println("Reading test file: " + file);
		URL configURL = context.getBundle().getEntry(file);
		if (configURL != null) {
			BufferedReader input = new BufferedReader(new InputStreamReader(configURL.openStream()));
			try {
				// Read in each user
				contents += input.readLine();
				String line = "";
				while (line != null) {
					line = input.readLine();
					if (line != null) {
						contents += line;
					}
				}
			}
			finally {
				input.close();
			}
		}
		
		return contents;
	}

	/**
	 * Perform various tests to ensure the CSVM is accessible and acts as expected.
	 * @param csvm the service to test.
	 * @param bpmn a BPMN composition plan to send to the service.
	 * @param conspec a ConSpec security policy to send to the service.
	 * @return true if the service was accessible and the tests returned the results expected.
	 */
	static boolean testCSVM (CompositionSecurityValidationService csvm, String bpmn, String conspec) {
		int passed = 0;
		boolean result = false;

		if (csvm != null) {
			Activator.logLine("Calling CSVM.");
			CompositionPlan compositionPlan = new CompositionPlan();
			compositionPlan.setBPMNXML(bpmn);
			
			IAgreementTemplate agreementTemplate = new AgreementTemplate("0");
			agreementTemplate.setXML(conspec);
			CompositionSecurityValidationResult validationProperty = csvm.VerifyCompositionCompliance(compositionPlan);
			Activator.logLine("CSVM result: " + validationProperty.getVerificationResult() + "; " + validationProperty.getVerificationExplaination());
			if (validationProperty.getVerificationResult() == true) {
				passed++;
			}
		} else {
			Activator.logLine("CSVM not found.");
		}

		if (passed == 1) {
			Activator.logLine("CSVM tests passed");
			result = true;
		}
		else {
			Activator.logLine("CSVM tests failed");
			result = false;
		}
		
		return result;
	}

	/**
	 * Perform various tests to ensure the PVM is accessible and acts as expected.
	 * @param pvm the service to test.
	 * @param conspec a ConSpec security policy to send to the service.
	 * @param details info about the service to be checked. For example, the name of the service in the marketplace.
	 * @return true if the service was accessible and the tests returned the results expected.
	 */
	static boolean testPVM (PropertyVerificationService pvm, String conspec, String details) {
		int passed = 0;
		boolean result = false;

		if (pvm != null) {
			Activator.logLine("Calling PVM.");
			
            Base64 encoder = new Base64();
			String agreementTemplateEncoded = "";
            //agreementTemplateEncoded = encodeData(agreementTemplate);

            PropertyVerificationResult verificationProperty;

            agreementTemplateEncoded = encoder.encodeToString(conspec.getBytes());

            Activator.logLine("Checking WSDL at: " + details);
			
			verificationProperty = pvm.verifyTechnicalTrustProperties(agreementTemplateEncoded, details);
			Activator.logLine("PVM result: " + verificationProperty.getVerificationResult() + "; " + verificationProperty.getVerificationExplaination());
			
			if (verificationProperty.getVerificationResult() >= 0) {
				passed++;
			}
		} else {
			Activator.logLine("PVM not found");
		}

		if (passed == 1) {
			Activator.logLine("PVM tests passed");
			result = true;
		}
		else {
			Activator.logLine("PVM tests failed");
			result = false;
		}

		return result;
	}

	/**
	 * Perform various tests to ensure the SPDM is accessible and acts as expected.
	 * @param spdm the service to test.
	 * @param serviceID the ID of a service to store and retrieve a property against in the cache.
	 * @param propertyID the ID of a property to store and retrieve to and from the cache.
	 * @return true if the service was accessible and the tests returned the results expected.
	 */
	static boolean testSPDM (ISPDMService spdm, String serviceID, String propertyID) {
		int passed = 0;
		boolean result = false;
		
		if (spdm != null) {
			Activator.logLine("Calling SPDM");

			ISecurityProperty verificationResult;
			eu.aniketos.ncvm.impl.WebService registerService = new eu.aniketos.ncvm.impl.WebService();
			registerService.setServiceID(serviceID);
			eu.aniketos.ncvm.impl.SecurityProperty registerProperty = new eu.aniketos.ncvm.impl.SecurityProperty();
			registerProperty.setPropertyID(propertyID);

			registerProperty.setPropertyValue("false");
			spdm.registerService(registerService, registerProperty);
			Activator.logLine("Set result to " + registerProperty.getPropertyValue() + " for " + serviceID);
			verificationResult = spdm.getSecurityProperty(serviceID, propertyID);
			Activator.logLine("Cached result: " + verificationResult.getPropertyValue());
			if (registerProperty.getPropertyValue().equals(verificationResult.getPropertyValue())) {
				passed++;
			}

			registerProperty.setPropertyValue("true");
			spdm.registerService(registerService, registerProperty);
			Activator.logLine("Set result to " + registerProperty.getPropertyValue() + " for " + serviceID);
			verificationResult = spdm.getSecurityProperty(serviceID, propertyID);
			Activator.logLine("Cached result: " + verificationResult.getPropertyValue());
			if (registerProperty.getPropertyValue().equals(verificationResult.getPropertyValue())) {
				passed++;
			}
		
		} else {
			Activator.logLine("SPDM not found");
		}

		if (passed == 2) {
			Activator.logLine("SPDM tests passed");
			result = true;
		}
		else {
			Activator.logLine("SPDM tests failed");
			result = false;
		}

		return result;
	}

	/**
	 * Perform various tests to ensure the Marketplace is accessible and acts as expected.
	 * @param marketplace the service to test.
	 * @param serviceID the ID of a service.
	 * @return true if the service was accessible and the tests returned the results expected.
	 */
	static boolean testMarketplace (IMarketplace marketplace, String serviceID) {
		int passed = 0;
		boolean result = false;

		if (marketplace != null) {
			String marketplaceAuthToken = "";
			Activator.logLine("Calling Marketplace.");
			marketplaceAuthToken = marketplace.getAuthToken("anonymous", "anonymous");
			Activator.logLine("Marketplace authtoken: " + marketplaceAuthToken);
			if (marketplaceAuthToken == null) {
				marketplaceAuthToken = "anon";
			}
			MarketplaceSearchParams params = new MarketplaceSearchParams();
			List<ServiceDescriptor> discovered = marketplace.discoverService(marketplaceAuthToken, params);
			Activator.logLine("Marketplace services found: " + discovered.size());

			for (ServiceDescriptor service : discovered) {
				String marketBpmn = marketplace.getBpmnDiagram(service.getId());
				if ((marketBpmn != null) && (marketBpmn.length() > 0)) {
					Activator.logLine("BPMN for " + service.getId() + " length " + marketBpmn.length());
				}
			}

			for (ServiceDescriptor service : discovered) {
				String marketSource = marketplace.getSource(service.getId());
				if ((marketSource != null) && (marketSource.length() > 0)) {
					Activator.logLine("Source for " + service.getId() + " at " + marketSource);
				}
			}
			
			if (discovered.size() > 0) {
				passed++;
			}
		} else {
			Activator.logLine("Marketplace not found.");
		}

		if (passed == 1) {
			Activator.logLine("Marketplace tests passed");
			result = true;
		}
		else {
			Activator.logLine("Marketplace tests failed");
			result = false;
		}

		return result;
	}
}
