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

package eu.aniketos.ncvm.userinterface;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import eu.aniketos.ncvm.userinterface.views.Register;
import eu.aniketos.ncvm.userinterface.views.Settings;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.aniketos.ncvm.userinterface"; //$NON-NLS-1$
	public static final String IMG_NCVM_CONFIGURE = "ncvm-configure.image";
	public static final String IMG_NCVM_ANIKETOS_SMALL = "ncvm-aniketos-small.image";

    // The shared instance
	private static Activator plugin;
	
    private static BundleContext context = null;
    private Register registration;

    private Settings settings;
    
    /**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		plugin = this;
		Activator.context = bundleContext;
	
		settings = new Settings();

		registration = new Register();
		registration.RegisterServce();

	    getPreferenceStore().setDefault("bpmn2", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<bpmn2:definitions xmlns:bpmn2=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" id=\"__qGLsHWwEeKGRoZvDGwPaQ\">\n	<bpmn2:process id=\"_theProcess\">\n		<bpmn2:startEvent id=\"_theStart\"/>\n		<bpmn2:serviceTask id=\"_serviceTask1\" name=\"\"/>\n		<bpmn2:serviceTask id=\"_serviceTask2\"/>\n		<bpmn2:userTask id=\"_userTask\"/>\n		<bpmn2:serviceTask id=\"_serviceTask3\"/>\n		<bpmn2:endEvent id=\"_theEnd\"/>\n	</bpmn2:process>\n</bpmn2:definitions>\n");
	    getPreferenceStore().setDefault("conspec", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<specification id=\"Integrity_MD5\" noNamespaceSchemaLocation=\"xsd_aniketos.xsd\">\n	<maxint>32000</maxint>\n	<maxlen>1000</maxlen>\n	<scope>Session</scope>\n	<securitystate>\n		<declaration>\n			<type>string</type>\n			<identifier>md5_hash</identifier>\n			<value><sconst>_</sconst></value>\n		</declaration>\n		<declaration>\n			<type>string</type>\n			<identifier>guardedSender</identifier>\n			<value><sconst>telco_operator_customer</sconst></value>\n		</declaration>\n	</securitystate>\n	<rule>\n		<before>\n			<identifier>ProcessingService.IProcessInput.getUserProfile</identifier>\n			<parameter>\n				<type>string</type>\n				<identifier>sender</identifier>\n			</parameter>\n			<parameter>\n				<type>string</type>\n				<identifier>data</identifier>\n			</parameter>\n		</before>\n		<perform>\n		<reaction>\n			<guard>\n				<s_equal>\n					<s_identifier>guardedSender</s_identifier>\n					<s_identifier>sender</s_identifier>\n				</s_equal>\n			</guard>\n			<update>\n				<assign>\n					<s_identifier>md5_hash</s_identifier>\n				<value>\n					<invocation>\n						<s_identifier>MD5Hash</s_identifier>\n						<s_identifier>result</s_identifier>\n						<argument>\n							<s_identifier>data</s_identifier>\n						</argument>\n					</invocation>\n					</value>\n				</assign>\n			</update>\n		</reaction>\n		<reaction>\n			<guard>\n				<not>\n					<s_equal>\n						<s_identifier>guardedSender</s_identifier>\n						<s_identifier>sender</s_identifier>\n					</s_equal>\n				</not>\n			</guard>\n			<update/>\n		</reaction>\n		</perform>\n	</rule>\n	<rule>\n		<before>\n			<identifier>getUserProfileImplementation</identifier>\n			<parameter>\n				<type>string</type>\n				<identifier>sender</identifier>\n			</parameter>\n			<parameter>\n				<type>string</type>\n				<identifier>data</identifier>\n			</parameter>\n		</before>\n		<perform>\n			<reaction>\n				<guard>\n					<and>\n						<s_equal>\n							<s_identifier>guardedSender</s_identifier>\n							<s_identifier>sender</s_identifier>\n						</s_equal>\n						<s_equal>\n							<s_identifier>md5_hash</s_identifier>\n							<invocation>\n								<s_identifier>MD5Hash</s_identifier>\n								<s_identifier>result</s_identifier>\n								<argument>\n									<s_identifier>data</s_identifier>\n								</argument>\n							</invocation>\n						</s_equal>\n					</and>\n				</guard>\n				<update/>\n			</reaction>\n			<reaction>\n				<guard>\n					<not>\n						<s_equal>\n							<s_identifier>guardedSender</s_identifier>\n							<s_identifier>sender</s_identifier>\n						</s_equal>\n					</not>\n				</guard>\n				<update/>\n			</reaction>\n		</perform>\n	</rule>\n</specification>\n");
	}
	
    public String getPrefString(String key) {
    	return getPreferenceStore().getString(key);
    }
    
    public void setPrefString(String key, String value) {
    	getPreferenceStore().setValue(key, value);
    }
    
    public String resetPref(String key) {
    	String value = getPreferenceStore().getDefaultString(key);
    	getPreferenceStore().setValue(key, value);
    	return value;
    }
    
    /*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		registration.DeRegisterService();
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public static BundleContext getContext() {
		return context;
	}

	/**
	 * Returns the shared settings
	 *
	 * @return the shared settings
	 */
	public Settings getSettings() {
		return settings;
	}
	
	public String getAddress() {
		return settings.getRegisterAddress() + "?wsdl";
	}

	public void reRegister () {
		registration.RegisterServce();
	}
	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
	public NCVMFeedback getNCVMFeedback () {
		return registration.getNCVMFeedback();
	}
	
	protected void initializeImageRegistry(ImageRegistry registry) {
		IPath path;
		URL url;
		ImageDescriptor desc;
		
		Bundle bundle = Platform.getBundle(PLUGIN_ID);
		path = new Path("icons/configure.gif");
		url = FileLocator.find(bundle, path, null);
		desc = ImageDescriptor.createFromURL(url);
		registry.put(IMG_NCVM_CONFIGURE, desc);

		path = new Path("icons/aniketos-small.png");
		url = FileLocator.find(bundle, path, null);
		desc = ImageDescriptor.createFromURL(url);
		registry.put(IMG_NCVM_ANIKETOS_SMALL, desc);
	}

	public void assignSettings(Settings newSettings) {
		Settings currentSettings = Activator.getDefault().getSettings();
		String address;
		boolean useTracker;
		boolean registerAuto;

		address = newSettings.getNcvmAddress();
		useTracker = newSettings.isNcvmTracker();
		currentSettings.setNcvmAddress(address);
		currentSettings.setNcvmTracker(useTracker);
		Activator.getDefault().setPrefString("ncvmAddress", address);
		Activator.getDefault().getPreferenceStore().setValue("ncvmTracker", useTracker);

		address = newSettings.getPvmAddress();
		useTracker = newSettings.isPvmTracker();
		currentSettings.setPvmAddress(address);
		currentSettings.setPvmTracker(useTracker);
		Activator.getDefault().setPrefString("pvmAddress", address);
		Activator.getDefault().getPreferenceStore().setValue("pvmTracker", useTracker);

		address = newSettings.getCsvmAddress();
		useTracker = newSettings.isCsvmTracker();
		currentSettings.setCsvmAddress(address);
		currentSettings.setCsvmTracker(useTracker);
		Activator.getDefault().setPrefString("csvmAddress", address);
		Activator.getDefault().getPreferenceStore().setValue("csvmTracker", useTracker);

		address = newSettings.getSpdmAddress();
		useTracker = newSettings.isSpdmTracker();
		currentSettings.setSpdmAddress(address);
		currentSettings.setSpdmTracker(useTracker);
		Activator.getDefault().setPrefString("spdmAddress", address);
		Activator.getDefault().getPreferenceStore().setValue("spdmTracker", useTracker);

		address = newSettings.getMarketplaceAddress();
		useTracker = newSettings.isMarketplaceTracker();
		currentSettings.setMarketplaceAddress(address);
		currentSettings.setMarketplaceTracker(useTracker);
		Activator.getDefault().setPrefString("marketplaceAddress", address);
		Activator.getDefault().getPreferenceStore().setValue("marketplaceTracker", useTracker);
		
		address = newSettings.getRegisterAddress();
		registerAuto = newSettings.isRegisterAuto();
		currentSettings.setRegisterAddress(address);
		currentSettings.setRegisterAuto(registerAuto);
		Activator.getDefault().setPrefString("registerAddress", address);;
		Activator.getDefault().getPreferenceStore().setValue("registerAuto", registerAuto);
	}
}
