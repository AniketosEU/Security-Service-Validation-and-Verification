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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Hashtable;

import org.osgi.framework.ServiceRegistration;

import eu.aniketos.ncvm.userinterface.Activator;
import eu.aniketos.ncvm.userinterface.INCVMFeedback;
import eu.aniketos.ncvm.userinterface.NCVMFeedback;

public class Register {
    /**
     * The service endpoint.
     */
    private static final String ADDRESS = "http://%s:9094/ncvmfeedback";
    private NCVMFeedback ncvmFeedback = null;
	private ServiceRegistration<?> registration = null;

	public void RegisterServce () {
        Hashtable<String, String> props = new Hashtable<String, String>();
        String registerAddress = "";

        if (registration == null) {
        	System.out.println("Aniketos NCVM log interface running.");
        }
        else {
        	// Note, unregistering the service fails to remove the endpoint with CXF before 1.5
        	// However, the CXF 1.5 SNAPSHOT currently fails to generate the WSDL properly (it's unstable)
        	// In the meantime, an address on a different port must be chosen to prevent conflicts
        	DeRegisterService();
        }

        if (Activator.getDefault().getSettings().isRegisterAuto()) {
        	registerAddress = getAutoAddress();
        	Activator.getDefault().getSettings().setRegisterAddress(registerAddress);
        }
        else {
        	registerAddress = Activator.getDefault().getSettings().getRegisterAddress();
        }
        
    	props.put("service.exported.interfaces", "*");
        props.put("service.exported.configs", "org.apache.cxf.ws");
        props.put("org.apache.cxf.ws.address", registerAddress);
        props.put("org.apache.cxf.ws.in.interceptors", "eu.aniketos.ncvm.userinterface.views.BasicAuthAuthorizationInterceptor");

        ncvmFeedback = new NCVMFeedback();
        registration = Activator.getContext().registerService(INCVMFeedback.class.getName(), ncvmFeedback, props);

        System.out.println("NCVM log interface registered at " + registerAddress + "?wsdl");
	}

	public void DeRegisterService() {
		System.out.println("Unregistering NCVM Feedback.");
		registration.unregister();
		registration = null;
	}
	
	public NCVMFeedback getNCVMFeedback() {
		return ncvmFeedback;
	}
	
	public static String getAutoAddress() {
        String hostname;
        String registerAddress;
        
		try {
        	InetAddress localhost = InetAddress.getLocalHost();
        	hostname = localhost.getHostName();
        }
        catch (UnknownHostException uhe) {
        	hostname = "localhost";
        }
        registerAddress = String.format(ADDRESS, hostname);
        
        return registerAddress;
	}
}
