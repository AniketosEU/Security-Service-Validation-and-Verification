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

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import org.osgi.service.component.ComponentContext;
import org.osgi.util.tracker.ServiceTracker;

import eu.aniketos.csstm.ICSSTM;
import eu.aniketos.csstm.schema.flinder.Results.Report.Failure;
import eu.aniketos.marketplace.IMarketplace;
import eu.aniketos.notification.IAlert;
import eu.aniketos.notification.INotification;
import eu.aniketos.scf.ServiceCompositionFrameworkInterface;
import eu.aniketos.servicethreatmonitoring.Event;
import eu.aniketos.servicethreatmonitoring.IThreatEvent;
//import eu.aniketos.wp3.components.spdm.ds.api.ISPDMService; //TODO: SPDM integration temporarily disabled
//import eu.aniketos.spdm.SPDMService;
import eu.aniketos.spdm.ds.api.ISPDMService;

/**
 * This is the implementation class of the CSSTM interface. Currently dummy
 * status.
 * 
 * @author balazs
 * 
 */
public class CSSTMImpl implements ICSSTM {

	private static final String marketplace = "http://hestia.atc.gr/marketplace/?wsdl";
	private static final String spdm = "http://ec2-54-235-118-152.compute-1.amazonaws.com:9091/spdm_service?wsdl";
	private static final String trm = "https://svrs.shields-project.eu/ANIKETOS_DEV/MI";
	private static final String flinder = "http://flinderws.search-laboratory.com";
	
	private String aniketosusername = "aniketos";
	private String aniketospass = "aniketos";
	private IAlert ns;
	private ISPDMService spdms;
	private ServiceTracker trackerSPDM;	
	private IThreatEvent stms;
	private IMarketplace mps;
	private ServiceCompositionFrameworkInterface scfs;

	private HashMap<String, Credentials> credentials = new HashMap<String, Credentials>();

    protected final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	

	/**
	 * The function for setting the class variable storing the Notification
	 * instance.
	 * 
	 * @param service
	 *            The Notification instance.
	 */

	protected synchronized void setINotification(IAlert service) {
		System.out.println("[CSSTM] Notification service was set!");
		this.ns = service;
	}

	/**
	 * The function for unsetting the class variable storing the Notification
	 * instance.
	 * 
	 * @param service
	 *            The Notification instance.
	 */
	protected synchronized void unsetINotification(IAlert service) {
		System.out.println("[CSSTM] Notification service was unset!");
		if (this.ns == service) {
			this.ns = null;
		}
	}
	
	/**
	 * The function for setting the class variable storing the STMM
	 * instance.
	 * 
	 * @param service
	 *            The STMM IThreatEvent instance.
	 */

	protected synchronized void setIThreatEvent(IThreatEvent service) {
		System.out.println("[CSSTM] STMM IThreatEvent service was set!");
		this.stms = service;
	}

	/**
	 * The function for unsetting the class variable storing the STMM
	 * instance.
	 * 
	 * @param service
	 *            The STMM IThreatEvent instance.
	 */
	protected synchronized void unsetIThreatEvent(IThreatEvent service) {
		System.out.println("[CSSTM] STMM IThreatEvent service was unset!");
		if (this.stms == service) {
			this.stms = null;
		}
	}
	
	/**
	 * The function for setting the class variable storing the SCF
	 * instance.
	 * 
	 * @param service
	 *            The SCF instance.
	 */

	protected synchronized void setServiceCompositionFrameworkInterface(ServiceCompositionFrameworkInterface service) {
		System.out.println("[CSSTM] SCF was set!");
		this.scfs = service;
	}

	/**
	 * The function for unsetting the class variable storing the SCF
	 * instance.
	 * 
	 * @param service
	 *            The SCF instance.
	 */
	protected synchronized void unsetServiceCompositionFrameworkInterface(ServiceCompositionFrameworkInterface service) {
		System.out.println("[CSSTM] SCF was unset!");
		if (this.scfs == service) {
			this.scfs = null;
		}
	}	
	

	private ISPDMService connectToSPDM(String addressSPDM) {

		QName serviceName = new QName(
				"http://api.ds.spdm.aniketos.eu/",
				"ISPDMService");
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = eu.aniketos.spdm.ds.api.ISPDMService.class
					.getResource(".");
			url = new URL(baseUrl, addressSPDM);
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		}

//		if (spdms == null)
//			spdms = new SPDMService(url, serviceName);

		return spdms;

	}
	
 // Currently not used
 /*
	private ISPDMService connectToSPDM(ComponentContext context) {
		trackerSPDM = new ServiceTracker(context.getBundleContext(),ISPDMService.class.getName(),null);
		trackerSPDM.open();
		return (ISPDMService) trackerSPDM.getService();
	}
	*/

	private IMarketplace connectToMarketplace(String addressMarketplace) {

		QName serviceName = new QName("http://marketplace.aniketos.eu/",
				"IMarketplace");
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = eu.aniketos.marketplace.IMarketplace.class
					.getResource(".");
			url = new URL(baseUrl, addressMarketplace);
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		}

		// Integration with Aniketos Marketplace
		IMarketplace marketplaceService = new IMarketplace(url, serviceName);

		return marketplaceService;

	}

	private void sendTestReport(String serviceID, String result) {
		// connect to SPDM
		connectToSPDM(spdm);
		spdms.updateSecurityProperty(serviceID, "test", result);
		
		// are we connected to the SCF?
		if (scfs!=null)
		{
			// TODO: once SCF implements an external 'register results' function, use it here
		}
	}


	private void registerVulnerabilityinSTMM(String serviceID,
			String vulnID) {

		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		df.setTimeZone(tz);
		String curtime = df.format(new Date());

		Event threatevent = new Event();
		threatevent.setEventServiceID(serviceID);
		threatevent.setEventComposingService(serviceID);
		threatevent.setEventDate(curtime);
		threatevent.setEventDesc(vulnID);
		threatevent.setEventID("csstm"); // TODO: change this depending on id generation
		threatevent.setEventMonSource(eu.aniketos.servicethreatmonitoring.MonitoringSource.SERVICE_THREAT_MONITORING);
		threatevent.setEventType("vulnerability");

		if (stms != null)
			stms.processEvent(threatevent);
	}	
	
	protected String upvoteVulnerabilityinTRM(String vuln) {
		System.out.println("GET " + trm + "/rating/" + vuln + "/1/up");

		// Use standard Aniketos acct for now
		//String username = "aniketos";
		//String password = "aniketos";

		String result = "";

		try {

			// Create upload request
			UUID uuid = UUID.fromString(vuln);
			HttpsURLConnection connection = CSSTMUtils.Connect(trm + "/rating/"
					+ uuid.toString() + "/1/up");
			connection.setRequestMethod("GET");
			connection.setDoOutput(false);
			String authdata = Base64.encodeBytes(new String(aniketosusername + ":"
					+ aniketospass).getBytes());
			connection.setRequestProperty("Authorization", "Basic " + authdata);

			// Send request to SVRS
			connection.connect();

			result = Integer.toString(connection.getResponseCode());

			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * This method is used to test the tester credentials to use during the
	 * automated tests, if applicable. It should be invoked by the service
	 * provider through the SCF or IdM.
	 * 
	 * Note that it is the responsibility of the service provider to disable the
	 * test account after testing has concluded.
	 * 
	 * @param serviceID
	 *            A String representing the Marketplace service ID of the
	 *            composite service to be tested.
	 * @param username
	 *            A String representing the username of the test account to use.
	 * @param password
	 *            A String representing the password of the test account to use.
	 */
	public void sendUserCredentials(String serviceID, String username,
			String password) {
		Credentials c = new Credentials();
		c.setUsername(username);
		c.setPassword(password);
		credentials.put(serviceID, c);
	}

	/**
	 * This method is used to trigger the actual testing on a composite service.
	 * It should be invoked by the SPDM if the composite service has the
	 * appropriate security property.
	 * 
	 * @param compositionPlan
	 *            A representation of the SecureBPMN file that contains all
	 *            information necessary to start the tests: the Marketplace
	 *            service ID of the composite service, a list of marketplace IDs
	 *            of the components used by the service, and optional
	 *            information useful during the tests (such as an annotated WSDL
	 *            or a 'known good' example message).
	 */
	public void requestSecurityTest(
			eu.aniketos.data.ICompositionPlan compositionPlan) {
		ArrayList<String> components;
		String bpmn = compositionPlan.getBPMNXML().getValue();
		String wsdl;
		boolean testable = true;
		boolean error = false;

		// get endpoint (composite service)
		String serviceID = compositionPlan.getCompositionPlanID().getValue();

		// get endpoint (components)
		components = new ArrayList<String>(BPMNParser.getServicesList(bpmn));

		// Get WSDL (GET on marketplace ID of composite service)
		wsdl = CSSTMUtils.httpGet(serviceID, null, null);

		// Connect to marketplace
		if (mps == null)
			mps = connectToMarketplace(marketplace);

		// Are the components testable?
		for (String id : components) {
			if (mps.getIMarketplacePort().isTestable(id) == false)
				testable = false;
		}

		// Parse error or component not testable: end test process immediately
		// with result
		// "INCONCLUSIVE".
		if ((testable == false) || (error == true))
			sendTestReport(serviceID, "inconclusive");
		else {
			// TODO: add authentication / authorization check
			// initiate the test
			callFlinder(serviceID, wsdl, credentials.get(serviceID));
		}
	}

	void callFlinder(final String serviceID, String wsdl,
			Credentials cred) {

		// invoke Flinder web service
		String flinderuri = flinder + "/fuzz?wsdl=" + serviceID; // ?wsdl=http://x.y.z/x.wsdl

		final String uuid = CSSTMUtils.httpGet(flinderuri, aniketosusername, aniketospass);
		// start timer to watch the result URL
		System.out.println("uuid received: " + uuid);
		final Runnable checkURL = new Runnable() {

			@Override
			public void run() {

				try {
					System.out.println("polling: " + flinder + "/result/" + uuid);
					String res = CSSTMUtils
							.httpGet(flinder + "/result/" + uuid, aniketosusername, aniketospass);
					StringReader sr = new StringReader(res);
					ClassLoader cl = eu.aniketos.csstm.schema.flinder.ObjectFactory.class
							.getClassLoader();
					JAXBContext jc = JAXBContext.newInstance(
							"eu.aniketos.csstm.schema.flinder", cl);
					Unmarshaller u = jc.createUnmarshaller();
					eu.aniketos.csstm.schema.flinder.Results r = (eu.aniketos.csstm.schema.flinder.Results) u
							.unmarshal(sr);

					if (r.getReport() != null) {
						System.out.println("received report");
						// we received a report, no more polling needed
						// this.cancel();
						scheduler.shutdown();						

						if (!(r.getReport().getFail().equalsIgnoreCase("0"))) {
							// there were failures
							processTestResults(serviceID, "fail", r.getReport()
									.getFailure());
						} else if (!(r.getReport().getInconclusive().equalsIgnoreCase("0"))) {
							// there were inconclusive results
							processTestResults(serviceID, "inconclusive", null);
						} else {
							// all tests passed
							processTestResults(serviceID, "pass", null);
						}
					} else if (r.getError() != null) {
						System.out.println("received error");
						
						// there was an error during testing, no more polling
						// needed
						// this.cancel();
						scheduler.shutdown();

						processTestResults(serviceID, "inconclusive", null);
					}
					System.out.println("continuing");

				} catch (JAXBException ex) {
					System.out.println("JAXB error:" + ex.getMessage());
					scheduler.shutdown();
					processTestResults(serviceID, "inconclusive", null);
				}
			}
		};

		final ScheduledFuture<?> flinderPoll = scheduler.scheduleWithFixedDelay(checkURL, 1L, 5L, java.util.concurrent.TimeUnit.SECONDS);
	}

	public void processTestResults(String serviceID, String result,
			List<Failure> vulnerabilities) {

		if (result.equalsIgnoreCase("pass")) {
			sendTestReport(serviceID, "pass");
			// send positive response to SPDM/SCF
			return;
		} else if (result.equalsIgnoreCase("fail")) {
			ArrayList<Failure> parsedvulns = new ArrayList<Failure>();
			for (Failure vuln : vulnerabilities) {
				if (!(parsedvulns.contains(vuln)))
				{
					// register vulnerabilities at STMM
					registerVulnerabilityinSTMM(serviceID, vuln.getManipulator());

					// upvote vulnerabilities at TRM
					upvoteVulnerabilityinTRM(vuln.getManipulator());
					parsedvulns.add(vuln);
				}
			}

			// send negative response to SPDM/SCF
			sendTestReport(serviceID, "fail");
			return;
		} else {
			// send inconclusive response to SPDM/SCF
			sendTestReport(serviceID, "inconclusive");
			return;
		}
	}

	/** This method is used to set the Aniketos Premium authentication information the CSSTM will use when communicating with Flinder.
	 * This method may be called at any time prior to calling requestSecurityTest.
	 * 
	 * @param csstmuser A String representing the Aniketos username to use.
	 * @param csstmpass A String representing the Aniketos password to use.
	 */
	public void setCSSTMCredentials(String csstmuser, String csstmpass) {
		if (csstmuser != null && csstmuser.length()>0)
			aniketosusername = csstmuser;
		if (csstmpass != null && csstmpass.length()>0)
			aniketospass = csstmpass;
	}

}
