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

public class Activator implements BundleActivator {
	private static BundleContext context;
	private static Activator plugin;
	private Services services;
	private Settings settings;

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

	public void stop(BundleContext bundleContext) throws Exception {
		services.unregisterService();
		Activator.context = null;
	}

	public static Activator getDefault() {
		return plugin;
	}

	static BundleContext getContext() {
		return context;
	}

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
	
	public Settings getSettings () {
		return settings;
	}

	public Services getServices () {
		return services;
	}
}
