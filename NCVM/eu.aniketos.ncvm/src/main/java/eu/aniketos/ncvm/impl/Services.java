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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Hashtable;

import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

import eu.aniketos.components.verification.compositionsecurityvalidation.CompositionSecurityValidationService;
import eu.aniketos.components.verification.propertyverification.PropertyVerificationService;
import eu.aniketos.data.ISPDMService;
import eu.aniketos.marketplace.IMarketplace;
import eu.aniketos.ncvm.INestedCompositionVerification;
import eu.aniketos.ncvm.csvm.proxy.CSVMProxy;
import eu.aniketos.ncvm.marketplace.proxy.MarketplaceProxy;
import eu.aniketos.ncvm.pvm.proxy.PVMProxy;
import eu.aniketos.ncvm.spdm.proxy.SPDMProxy;
import eu.aniketos.ncvm.userinterface.INCVMFeedback;
import eu.aniketos.ncvm.userinterface.proxy.INCVMProxy;
import eu.aniketos.ncvm.userinterface.proxy.NCVMFeedbackProxy;

/**
 * Class for managing external services.
 * @author LJMU/David Llewellyn-Jones
 *
 */
public class Services {
	private static final String ADDRESS = "http://%s:9093/ncvm";

	private ServiceRegistration<?> registration;

	private ServiceTracker<?, ?> trackerCSVM = null;

	private ServiceTracker<?, ?> trackerPVM = null;

	private ServiceTracker<?, ?> trackerSPDM = null;

	private ServiceTracker<?, ?> trackerNCVMFeedback = null;

	private ServiceTracker<?, ?> trackerMarketplace = null;

	/**
	 * Initially register the service and when the module starts
	 * so that it can be accessed by other services.
	 */
	public void registerService() {
		Hashtable<String, String> props = new Hashtable<String, String>();
		String registerAddress;

		registerAddress = Activator.getDefault().getSettings().getRegisterAddress();
		if ((registerAddress == null) || (registerAddress =="")) {
			// The address wasn't in the configuration file, so we have to figure it out for ourselves
			String hostname;
			try {
				InetAddress localhost = InetAddress.getLocalHost();
				hostname = localhost.getHostName();
			} catch (UnknownHostException uhe) {
				hostname = "localhost";
			}
			registerAddress = String.format(ADDRESS, hostname);
			Activator.getDefault().getSettings().setRegisterAddress(registerAddress);
		}

		props.put("service.exported.interfaces", "*");
		props.put("service.exported.configs", "org.apache.cxf.ws");
		props.put("org.apache.cxf.ws.address", registerAddress);
        props.put("org.apache.cxf.ws.in.interceptors", "eu.aniketos.ncvm.impl.BasicAuthAuthorizationInterceptor");

		registration = Activator.getContext().registerService(INestedCompositionVerification.class.getName(), new NestedCompositionVerification(), props);

		System.out.println("NCVM registered at " + registerAddress + "?wsdl");
	}
	
	/**
	 * Unregister the service so it's no longer accessible to other external services.
	 */
	public void unregisterService() {
		registration.unregister();
	}
	
	/**
	 * Get the registration details for the service.
	 * @return registration details.
	 */
	public ServiceRegistration<?> getRegistration () {
		return registration;
	}
	
	/**
	 * Initialise the external services so that they can be accessed
	 * from this service.
	 */
	public void initialiseServices() {
		initialiseNCVMFeedback();
		initialiseCSVM();
		initialisePVM();
		initialiseSPDM();
		initialiseMarketplace();
	}
	
	/**
	 * Set up the service tracker for an external service.
	 * @param tracker service tracker
	 * @param track true of a service tracker should be used for this service.
	 * @param clazz the class name of the service to track.
	 * @return the initialised service tacker.
	 */
	public ServiceTracker<?, ?> initialiseService(ServiceTracker<?, ?> tracker, boolean track, String clazz) {
		if (track) {
			if (tracker == null) {
				tracker = new ServiceTracker<Object, Object>(Activator.getContext(), clazz, null);
				tracker.open();
			}
		}
		else {
			if (tracker != null) {
				tracker.close();
				tracker = null;
			}
		}
		return tracker;
	}
	
	/**
	 * Initialise the service tracker for the CSVM.
	 */
	public void initialiseCSVM() {
		trackerCSVM = initialiseService(trackerCSVM, Activator.getDefault().getSettings().isCsvmTrack(), CompositionSecurityValidationService.class.getName());
	}

	/**
	 * Initialise the service tracker for the PVM.
	 */
	public void initialisePVM() {
		trackerPVM = initialiseService(trackerPVM, Activator.getDefault().getSettings().isPvmTrack(), PropertyVerificationService.class.getName());
	}
	
	/**
	 * Initialise the service tracker for the SPDM.
	 */
	public void initialiseSPDM() {
		trackerSPDM = initialiseService(trackerSPDM, Activator.getDefault().getSettings().isSpdmTrack(), ISPDMService.class.getName());
	}
	
	/**
	 * Initialise the service tracker for the NCVM Feedback Interface.
	 */
	public void initialiseNCVMFeedback() {
		trackerNCVMFeedback = initialiseService(trackerNCVMFeedback, Activator.getDefault().getSettings().isNcvmFeedbackTrack(), INCVMFeedback.class.getName());
	}
	
	/**
	 * Initialise the service tracker for the Marketplace.
	 */
	public void initialiseMarketplace() {
		trackerMarketplace = initialiseService(trackerMarketplace, Activator.getDefault().getSettings().isMarketplaceTrack(), IMarketplace.class.getName());
	}
	
	/**
	 * Return a proxy to the external CSVM which will act as if it's a local version.
	 * @return the proxy object.
	 * @throws Exception if the service doesn't respond before the timeout period expires.
	 */
	public CompositionSecurityValidationService getCSVM() throws Exception {
		CompositionSecurityValidationService csvm = null;
		// Create a reference to the property verification service
		if (Activator.getDefault().getSettings().isCsvmTrack()) {
			csvm = (CompositionSecurityValidationService) trackerCSVM.waitForService(1000);
		}
		else {
			csvm = new CSVMProxy();
			((INCVMProxy) csvm).setURL(Activator.getDefault().getSettings().getCsvmWsdl());
		}
		
		return csvm;
	}

	/**
	 * Return a proxy to the external PVM which will act as if it's a local version.
	 * @return the proxy object.
	 * @throws Exception if the service doesn't respond before the timeout period expires.
	 */
	public PropertyVerificationService getPVM() throws Exception {
		PropertyVerificationService pvm = null;
		// Create a reference to the property verification service
		if (Activator.getDefault().getSettings().isPvmTrack()) {
			pvm = (PropertyVerificationService) trackerPVM.waitForService(1000);
		}
		else {
			pvm = new PVMProxy();
			((INCVMProxy) pvm).setURL(Activator.getDefault().getSettings().getPvmWsdl());
		}
		
		return pvm;
	}

	/**
	 * Return a proxy to the external SPDM which will act as if it's a local version.
	 * @return the proxy object.
	 * @throws Exception if the service doesn't respond before the timeout period expires.
	 */
	public ISPDMService getSPDM() throws Exception {
		ISPDMService spdm = null;
		// Create a reference to the property verification service
		if (Activator.getDefault().getSettings().isSpdmTrack()) {
			spdm = (ISPDMService) trackerSPDM.waitForService(1000);
		}
		else {
			spdm = new SPDMProxy();
			((INCVMProxy) spdm).setURL(Activator.getDefault().getSettings().getSpdmWsdl());
		}
		
		return spdm;
	}

	/**
	 * Return a proxy to the external NCVM Feedback interface which will act as if it's a local version.
	 * @return the proxy object.
	 * @throws Exception if the service doesn't respond before the timeout period expires.
	 */
	public INCVMFeedback getNCVMFeedback() throws Exception {
		INCVMFeedback ncvmFeedback = null;
		// Create a reference to the property verification service
		if (Activator.getDefault().getSettings().isNcvmFeedbackTrack()) {
			ncvmFeedback = (INCVMFeedback) trackerNCVMFeedback.waitForService(1000);
		}
		else {
			ncvmFeedback = new NCVMFeedbackProxy();
			((INCVMProxy) ncvmFeedback).setURL(Activator.getDefault().getSettings().getNcvmFeedbackWsdl());
		}
		return ncvmFeedback;
	}

	/**
	 * Return a proxy to the external Marketplace which will act as if it's a local version.
	 * @return the proxy object.
	 * @throws Exception if the service doesn't respond before the timeout period expires.
	 */
	public IMarketplace getMarketplace() throws Exception {
		IMarketplace marketplace = null;
		// Create a reference to the marketplace service
		if (Activator.getDefault().getSettings().isMarketplaceTrack()) {
			marketplace = (IMarketplace) trackerMarketplace.waitForService(1000);
		}
		else {
			marketplace = new MarketplaceProxy();
			((INCVMProxy) marketplace).setURL(Activator.getDefault().getSettings().getMarketplaceWsdl());
		}
		return marketplace;
	}
}
