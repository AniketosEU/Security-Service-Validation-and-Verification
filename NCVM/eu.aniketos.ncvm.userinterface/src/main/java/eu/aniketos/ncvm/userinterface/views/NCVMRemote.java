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

package eu.aniketos.ncvm.userinterface.views;

import org.osgi.util.tracker.ServiceTracker;

import eu.aniketos.data.ICompositionPlan;
import eu.aniketos.data.IConsumerPolicy;
import eu.aniketos.ncvm.INestedCompositionVerification;
import eu.aniketos.ncvm.IVerificationResult;
import eu.aniketos.ncvm.proxy.NCVMProxy;
import eu.aniketos.ncvm.userinterface.proxy.INCVMProxy;

/**
 * Provides a local class for accessing the remote NCVM functionality.
 * @author LJMU/David Llewellyn-Jones
 *
 */
public class NCVMRemote {
	private ServiceTracker<?, ?> trackerNCVM = null;
	private NCVMProxy ncvmProxy = null;

    public NCVMRemote() {
    	ncvmProxy = new NCVMProxy();
	}
	
    public void initialiseNCVMRemote() {
		trackerNCVM = initialiseService(trackerNCVM, eu.aniketos.ncvm.userinterface.Activator.getDefault().getSettings().isNcvmTracker(), eu.aniketos.ncvm.INestedCompositionVerification.class.getName());
    }
    
    public ServiceTracker<?, ?> initialiseService(ServiceTracker<?, ?> tracker, boolean track, String clazz) {
    	if (track) {
    		if (tracker == null) {
    			tracker = new ServiceTracker<Object, Object>(eu.aniketos.ncvm.userinterface.Activator.getContext(), clazz, null);
    			tracker.open();
    		}
    		System.out.println("Tracking: " + clazz);
    	}
    	else {
    		if (tracker != null) {
    			tracker.close();
    			tracker = null;
    		}
    		System.out.println("Declarative: " + clazz);
    	}
    	return tracker;
    }

	public INestedCompositionVerification getNCVM() throws Exception {
		INestedCompositionVerification ncvm = null;
		// Create a reference to the nested composition verification service
		if (eu.aniketos.ncvm.userinterface.Activator.getDefault().getSettings().isNcvmTracker()) {
			ncvm = (INestedCompositionVerification) trackerNCVM.waitForService(1000);
		}
		else {
			ncvm = new NCVMProxy();
			((INCVMProxy) ncvm).setURL(eu.aniketos.ncvm.userinterface.Activator.getDefault().getSettings().getNcvmAddress());
		}
		
		return ncvm;
	}

	public IVerificationResult verifyProperty (ICompositionPlan service, IConsumerPolicy policy) throws Exception {
		String registerAddress = eu.aniketos.ncvm.userinterface.Activator.getDefault().getAddress();
		INestedCompositionVerification ncvm = getNCVM();
		IVerificationResult result = null;
		
		if (ncvm != null) {
            System.out.println("Setting feedback service to: " + registerAddress);
            ncvm.configureNCVMFeedback(registerAddress, false);
    		
    		System.out.println("Invoking verifyProperty...");
    		result = ncvm.verifyProperty(service, policy);
    		System.out.println("Calling with parameter lengths: " + service.getBPMNXML().length() + ", " + policy.getXML().length());
		}
		else {
    		System.out.println("Failed to discover NCVM");
		}
				
		return result;
	}

	public IVerificationResult verifyPropertyDeployed (String serviceID, IConsumerPolicy policy) throws Exception {
		String registerAddress = eu.aniketos.ncvm.userinterface.Activator.getDefault().getAddress();
		INestedCompositionVerification ncvm = getNCVM();
		IVerificationResult result = null;
		
		if (ncvm != null) {
            System.out.println("Setting feedback service to: " + registerAddress);
            ncvm.configureNCVMFeedback(registerAddress, false);
    		
    		System.out.println("Invoking verifyPropertyDeployed...");
    		result = ncvm.verifyPropertyDeployed(serviceID, policy);
    		System.out.println("Calling with parameter lengths: " + serviceID.length() + ", " + policy.getXML().length());
		}
		else {
    		System.out.println("Failed to discover NCVM");
		}
				
		return result;
	}
	
	public void configureNCVMFeedback (String wsdlUrl, boolean useTracker) throws Exception  {
		INestedCompositionVerification ncvm = getNCVM();

        if (ncvm != null) {
        	ncvm.configureNCVMFeedback(wsdlUrl, useTracker);
        }
        else {
    		System.out.println("Failed to discover NCVM");
        }
    }
    
    public void configureCSVM (String wsdlUrl, boolean useTracker) throws Exception  {
		INestedCompositionVerification ncvm = getNCVM();

        if (ncvm != null) {
        	ncvm.configureCSVM(wsdlUrl, useTracker);
        }
        else {
    		System.out.println("Failed to discover NCVM");
        }
    }

    public void configurePVM (String wsdlUrl, boolean useTracker) throws Exception  {
		INestedCompositionVerification ncvm = getNCVM();

        if (ncvm != null) {
        	ncvm.configurePVM(wsdlUrl, useTracker);
        }
        else {
    		System.out.println("Failed to discover NCVM");
        }
    }

    public void configureSPDM (String wsdlUrl, boolean useTracker) throws Exception  {
		INestedCompositionVerification ncvm = getNCVM();

        if (ncvm != null) {
        	ncvm.configureSPDM(wsdlUrl, useTracker);
        }
        else {
    		System.out.println("Failed to discover NCVM");
        }
    }

    public void configureMarketplace (String wsdlUrl, boolean useTracker) throws Exception  {
		INestedCompositionVerification ncvm = getNCVM();

        if (ncvm != null) {
        	ncvm.configureMarketplace(wsdlUrl, useTracker);
        }
        else {
    		System.out.println("Failed to discover NCVM");
        }
    }

    public void performTests () throws Exception  {
		INestedCompositionVerification ncvm = getNCVM();

        if (ncvm != null) {
        	ncvm.performTests();
        }
        else {
    		System.out.println("Failed to discover NCVM");
        }
    }
}
