/**
 * Copyright 2012  Bo Zhou <B.Zhou@ljmu.ac.uk>
 * Liverpool John Moores University <http://www.ljmu.ac.uk/cmp/>
 * Aniketos Project <http://www.aniketos.eu>
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

package eu.aniketos.scpm.impl;


import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
//import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

import eu.aniketos.scpm.ICompositionPlanner;
import eu.aniketos.data.ISPDMService;
import eu.aniketos.ncvm.INestedCompositionVerification;
import eu.aniketos.scpm.cmm.client.IContractManagement;

/**
 * Activator class for scp.domparse bundle.
 * 
 * @author Bo Zhou, LJMU
 */
public class Activator implements BundleActivator 
{
	private ServiceRegistration registration;
    private static BundleContext context;
    private static Activator plugin;
    private ServiceTracker<?, ?> trackerSPDM = null;
    private ServiceTracker<?, ?> trackerNCVM = null;
    private ServiceTracker<?, ?> trackerCMM = null;
    /**
	 * Service tracker for Security Property Determination Module.
	 */
    //ServiceTracker trackerSPDM = null;
    //ServiceTracker trackerNCVM = null;
    //ServiceTracker trackerCMM = null;
    /**
	 * Service reference for Security Property Determination Module.
	 */
//    ServiceReference spdmReference = null;
//    ServiceReference ncvmReference = null;
//    ServiceReference cmmReference = null;

 
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
     * )
     */
    public void start(BundleContext bundleContext) throws Exception
    {
    	plugin = this;
    	Activator.context = bundleContext;
    /*	
    	trackerSPDM = new ServiceTracker<Object, Object>(bundleContext, ISPDMService.class.getName(), null);
//            @Override
//            public Object addingService(ServiceReference reference) {
//                Object result = super.addingService(reference);
//                spdmReference = reference;                
//                return result;
//            }
//        };
        
        trackerSPDM.open();
        
        trackerNCVM = new ServiceTracker<Object, Object>(bundleContext, INestedCompositionVerification.class.getName(), null);// {
//            @Override
//            public Object addingService(ServiceReference reference) {
//                Object result = super.addingService(reference);
//                ncvmReference = reference;                
//                return result;
//            }
//        };
        
        trackerNCVM.open();
        
        trackerCMM = new ServiceTracker<Object, Object>(bundleContext, IContractManagement.class.getName(), null);// {
//            @Override
//            public Object addingService(ServiceReference reference) {
//                Object result = super.addingService(reference);
//                cmmReference = reference;                
//                return result;
//            }
//        };
        
        trackerCMM.open();
    	*/
    	Activator.context = bundleContext;
    	// DOM - Create some properties to help identify this bundle
    	Hashtable<String, String> props = new Hashtable<String, String>();
    	
    	props.put("service.exported.interfaces", "*");
        props.put("service.exported.configs", "org.apache.cxf.ws");
        props.put("immediate", "true");
        props.put("org.apache.cxf.ws.address", 
                  "http://ec2-54-235-118-152.compute-1.amazonaws.com:9092/scpm");

    	
    	// Create an instance of this planner and register it as a service
        registration = bundleContext.registerService(ICompositionPlanner.class.getName(), 
                new CompositionPlanner(), props);

    	
    	System.out.println("Registered Secure Composition Planner at http://localhost:9092/scpm");
    	
    	
    	
    }
    
    public ISPDMService getSPDM1() throws Exception{
		// create a reference to the get property service
    	
    	ISPDMService spdm = null;
		spdm = (ISPDMService) trackerSPDM.waitForService(1000);
				
		return spdm;
    	
//    	ISPDMService spdm = (ISPDMService) context.getService(spdmReference);
//    	
//    	if (spdm == null) {
//        	System.out.println("Cannot find SPDM service");
//            return null;
////        }
//
//		return spdm;
	}
    
    public INestedCompositionVerification getNCVM1() throws Exception{
	
    	// create a reference to the ncvm service 
    	
    	INestedCompositionVerification ncvm = null;
		ncvm = (INestedCompositionVerification) trackerNCVM.waitForService(1000);
		
    	return ncvm;
    }
    /*
    public CompositionSecurityValidationService getCSVM(){
    	    	
		// create a reference to the property verification service   
		//ServiceReference reference = context.getServiceReference(CompositionSecurityValidationService.class.getName());
    	CompositionSecurityValidationService csvm = 
    	    (CompositionSecurityValidationService) context.getService(csvmReference);
		return csvm;
	}
    */
    public IContractManagement getCMM1() throws Exception{
    	
		// create a reference to the contract management service  
    	
    	IContractManagement cmm = null;
		cmm = (IContractManagement) trackerCMM.waitForService(1000);
		
    	return cmm;

	}
    

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext bundleContext) throws Exception
    {
    	registration.unregister();
    	Activator.context = null;
    }
    public static Activator getDefault()
    {
    	return plugin;
    }
    
    static BundleContext getContext() {
		return context;
	}

}
