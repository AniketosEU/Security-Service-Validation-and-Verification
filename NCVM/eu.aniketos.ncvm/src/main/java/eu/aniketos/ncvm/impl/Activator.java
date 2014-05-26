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

import java.io.IOException;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import eu.aniketos.ncvm.userinterface.INCVMFeedback;

/**
 * OSGi activator class. Sets things up when the module
 * is started in the OSGi container, including exposing
 * methods for access to other modules.
 * @author LJMU/David Llewellyn-Jones
 *
 */
public class Activator implements BundleActivator {
	/**
	 * The OSGi context for the module.
	 */
	private static BundleContext context;

	/**
	 * A reference to this object, so it can be accessed from anywhere
	 * using a static reference.
	 */
	private static Activator plugin;

	/**
	 * Global object for managing external services such as
	 * the CSVM, PVM, SPDM, Marketplace, etc.
	 */
	private Services services;

	/**
	 * Global object for storing the module's settings.
	 */
	private Settings settings;

	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		// Used to expose as a Web Service when deployed in Karaf using CXF
		plugin = this;
		Activator.context = context;

		System.out.println("Aniketos Nested Composition Verification Module running.");

		settings = new Settings();
		try {
			settings.LoadConfigurationDefault();
		}
		catch (IOException e) {
			System.out.println("NVCM configuration file could not be read: " + e.getMessage());
		}

		services = new Services();
		services.registerService();
		services.initialiseServices();
	}

	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		services.unregisterService();
		Activator.context = null;
	}

	/**
	 * Returns a reference to this activator class.
	 * @return a reference to this class.
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns the context of the OSGi module.
	 * @return the module context.
	 */
	static BundleContext getContext() {
		return context;
	}

	/**
	 * Log a user feedback message. The functionality may vary, but
	 * by default this will print the message to the console and also
	 * send it to the remote feedback interface.
	 * If the feedback interface can't be accessed no warning is printed.
	 * @param message the log message.
	 */
	public static void logLine(String message) {
		// Log the message to the screen
		System.out.println(message);
		// Log the message via the NCVM Feedback proxy
		// This will show the message in Eclipse if the NCVM Feedback view is running
		INCVMFeedback ncvmFeedback;
		try {
			ncvmFeedback = getDefault().getServices().getNCVMFeedback();
			if (ncvmFeedback != null) {
				ncvmFeedback.logLine(message);
			}
		} catch (Exception e) {
			// Do nothing
		}
	}
	
	/**
	 * Log a user feedback message. The functionality may vary, but
	 * by default this will print the message to the console and also
	 * send it to the remote feedback interface.
	 * In contrast to the logLine method, if the feedback interface can't 
	 * be accessed an warning message is output to the console.
	 * @param message the log message.
	 */
	public static void logLineCheck(String message) {
		// Log the message to the screen
		System.out.println(message);
		// Log the message via the NCVM Feedback proxy
		// This will show the message in Eclipse if the NCVM Feedback view is running
		INCVMFeedback ncvmFeedback;
		try {
			ncvmFeedback = getDefault().getServices().getNCVMFeedback();
			if (ncvmFeedback != null) {
				ncvmFeedback.logLine(message);
			}
			else {
				System.out.println("No NCVM feedback. Caller will only receive the final result.");
			}
		} catch (Exception e) {
			System.out.println("NCVM feedback could not be called: " + e.getMessage());
		}
	}
	
	/**
	 * Get a reference to the global settings object.
	 * @return the global settings object.
	 */
	public Settings getSettings () {
		return settings;
	}

	/**
	 * Get a reference to the global class for managing and
	 * accessing the external services (CSVM, PVM, SPDM, Marketplace, etc.).
	 * @return the global service management class.
	 */
	public Services getServices () {
		return services;
	}
}
