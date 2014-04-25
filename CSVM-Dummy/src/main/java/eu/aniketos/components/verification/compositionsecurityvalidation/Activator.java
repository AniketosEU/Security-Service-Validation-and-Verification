/**
 * Copyright 2014 Liverpool John Moores University <http://www.ljmu.ac.uk/cmp/>
 * Aniketos Project FP7-ICT-257930 <http://www.aniketos.eu>
 * Brett Lempereur <B.Lempereur@ljmu.ac.uk>
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

package eu.aniketos.components.verification.compositionsecurityvalidation;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * Mock property verification module activator.
 * 
 * @author Brett Lempereur
 */
public class Activator implements BundleActivator {
	private static BundleContext context;
	private static Activator plugin;

	/**
     * The service endpoint.
     */
    private static final String ADDRESS = "http://%s:9095/csvm";

    private ServiceRegistration registration;
	private Settings settings;
    
    /**
     * Start the bundle.
     * 
     * @param context   the bundle context.
     */
    public void start(BundleContext context)
    {
		plugin = this;
		Activator.context = context;

		System.out.println("Aniketos Composition Security Validation Module running.");

		Hashtable<String, String> props = new Hashtable<String, String>();
        String registerAddress;

		settings = new Settings();
		try {
			settings.LoadConfigurationDefault();
		}
		catch (IOException e) {
			System.out.println("NVCM configuration file could not be read: " + e.getMessage());
		}

		registerAddress = settings.getRegisterAddress();
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
			settings.setRegisterAddress(registerAddress);
		}

        props.put("service.exported.interfaces", "*");
        props.put("service.exported.configs", "org.apache.cxf.ws");
        props.put("org.apache.cxf.ws.address", registerAddress);
        props.put("org.apache.cxf.ws.in.interceptors", "eu.aniketos.components.verification.compositionsecurityvalidation.BasicAuthAuthorizationInterceptor");
        
        registration = context.registerService(CompositionSecurityValidationService.class.getName(), new CompositionSecurityValidationServiceImpl(), props);

        System.out.println("CSVM registered at " + registerAddress + "?wsdl");
    }

    /**
     * Stop the bundle.
     * 
     * @param context   the bundle context.
     */
    public void stop(BundleContext context)
    {
        registration.unregister();
		Activator.context = null;
    }

	public static Activator getDefault() {
		return plugin;
	}

	static BundleContext getContext() {
		return context;
	}
}
