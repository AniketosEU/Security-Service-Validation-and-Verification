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

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
    /**
     * The service endpoint.
     */
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

		System.out.println("CSVM Proxy running.");
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

    public static Activator getDefault()
    {
    	return plugin;
    }
}
