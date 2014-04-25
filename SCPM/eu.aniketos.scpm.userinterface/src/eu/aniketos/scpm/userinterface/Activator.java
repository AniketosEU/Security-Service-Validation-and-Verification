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


package eu.aniketos.scpm.userinterface;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.*;
import org.osgi.util.tracker.ServiceTracker;

import eu.aniketos.scpm.ICompositionPlanner;

//import eu.aniketos.wp3.components.spdm.ds.api.ISPDMService;

/**
 * 
 * The Activator for our test Eclipse plugin. We use this class to provide
 * access to the other OSGI bundles we'll need
 * 
 * @author David Lamb, Asim Muhammed, Bo Zhou, LJMU
 * 
 */
public class Activator extends AbstractUIPlugin implements BundleActivator {

	/**
	 * The plugin ID.
	 */
	public static final String PLUGIN_ID = "eu.aniketos.scpm.userinterface"; //$NON-NLS-1$

	/**
	 * The shared instance.
	 */
	private static Activator plugin;
	private ICompositionPlanner scpm;

	/**
	 * The bundle context.
	 */
	private static BundleContext context = null;

	/**
	 * Service tracker for Secure Composition Planner Module.
	 */
	ServiceTracker trackerSCPM = null;

	/**
	 * service reference for Secure Composition Planner Module.
	 */
	ServiceReference scpmReference = null;


	/**
	 * Get bundle context.
	 * 
	 * @return The bundle context.
	 * 
	 */
	static BundleContext getContext() {
		return context;
	}

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/**
	 * Specify the initial actions for the plugin.
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 * @param bundleContext
	 *            The bundle context
	 */
	public void start(BundleContext bundleContext) throws Exception {
		// super.start(bundleContext);
		plugin = this;
		// this.context = context;
		trackerSCPM = new ServiceTracker(bundleContext,
				ICompositionPlanner.class.getName(), null) {
			@Override
			public Object addingService(ServiceReference reference) {
				Object result = super.addingService(reference);
				scpmReference = reference;
				return result;
			}
		};

		trackerSCPM.open();

		Activator.context = bundleContext;

	}

	/**
	 * Get the service of Secure Composition Planner Module.
	 * 
	 * * @return The service that registered as Secure Composition Planner.
	 * 
	 */
	public ICompositionPlanner getSCPM() {

		// create a reference to the property verification service
		// ServiceReference reference =
		// context.getServiceReference(ICompositionPlanner.class.getName());
		try {
			Object svc = context.getService(scpmReference);

			if (svc == null) {
				System.out.println("Cannot find SCPM service");
				return null;
			}
			if (!(svc instanceof ICompositionPlanner)) {
				System.out.println("what are you scpm?"
						+ svc.getClass().getCanonicalName());
				return null;
			}
			scpm = (ICompositionPlanner) svc;

		} catch (NullPointerException e) {
			System.out.println("Cannot find SCPM service");
			return null;
		}

		return scpm;
	}



	/**
	 * Specify actions before the plugin terminates
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 * @param bundleContext
	 *            The bundle context
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		plugin = null;
		Activator.context = null;
		// super.stop(bundleContext);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

}
