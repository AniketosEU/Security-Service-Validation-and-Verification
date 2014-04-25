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

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    /**
     * The service endpoint.
     */
    //private static final String ADDRESS = "http://%s:9095/ncvmfeedback/proxy";
    //private ServiceRegistration<?> registration;
	private static Activator plugin;
	private static BundleContext context;

    /**
	 * Service tracker for Security Property Determination Module.
	 */
    //ServiceTracker trackerNCVMFeedback = null;
    /**
	 * Service reference for Security Property Determination Module.
	 */
    //ServiceReference ncvmFeedbackReference = null;
	
	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		// Used to expose as a Web Service when deployed in Karaf using CXF
		plugin = this;
		Activator.context = bundleContext;

		System.out.println("NCVM Feedback Proxy running.");
//
//    	trackerNCVMFeedback = new ServiceTracker(bundleContext, INCVMFeedback.class.getName(), null) {
//            @Override
//            public Object addingService(ServiceReference reference) {
//                Object result = super.addingService(reference);
//                ncvmFeedbackReference = reference;                
//                return result;
//            }
//        };
//        
//        trackerNCVMFeedback.open();
        
        //ncvmFeedbackReference = this.context.getServiceReference(INCVMFeedback.class.getName());
        
        
        
//        Hashtable<String, String> props = new Hashtable<String, String>();
//        String hostname;
//        
//        try {
//        	InetAddress localhost = InetAddress.getLocalHost();
//        	hostname = localhost.getHostName();
//        }
//        catch (UnknownHostException uhe) {
//        	hostname = "localhost";
//        }
//        String registerAddress = String.format(ADDRESS, hostname);
//    	
//    	props.put("service.exported.interfaces", "*");
//        props.put("service.exported.configs", "org.apache.cxf.ws");
//        props.put("org.apache.cxf.ws.address", registerAddress);
//        
//		registration = bundleContext.registerService(INCVMFeedback.class.getName(), new NCVMFeedbackProxy(), props);
//
//        System.out.println("NCVM Feedback Proxy registered at " + registerAddress + "?wsdl");
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		//registration.unregister();
		Activator.context = null;
	}

    public static Activator getDefault()
    {
    	return plugin;
    }

//    public INCVMFeedback getNCVMFeedback(){
//    	
//    	// create a reference to the ncvm feedback service 
//    	
//    	INCVMFeedback ncvmFeedback = (INCVMFeedback) context.getService(ncvmFeedbackReference);
//    	if (ncvmFeedback == null) {
//        	System.out.println("Cannot find NCVM Feedback service");
//            return null;
//        }
//
//    	return ncvmFeedback;
//    }
}
