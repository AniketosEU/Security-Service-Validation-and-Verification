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
import java.util.Map;

/**
 * Class for storing global settings for the module.
 * Settings are initially loaded in from the configure/config.txt file,
 * but some settings can also be configured through the external 
 * service interface of the NCVM.
 * @author LJMU/David Llewellyn-Jones
 *
 */
public class Settings {
	private String ncvmFeedbackWsdl;
	private String csvmWsdl;
	private String pvmWsdl;
	private String spdmWsdl;
	private String marketplaceWsdl;
	private boolean ncvmFeedbackTrack;
	private boolean csvmTrack;
	private boolean pvmTrack;
	private boolean spdmTrack;
	private boolean marketplaceTrack;
	private String registerAddress;

	/**
	 * Initialise the settings class.
	 */
	public Settings() {
		ncvmFeedbackWsdl = "http://localhost:9094/ncvmfeedback?wsdl";
		csvmWsdl = "http://http://ec2-54-235-118-152.compute-1.amazonaws.com:9095/csvm?wsdl";
		pvmWsdl = "http://http://ec2-54-235-118-152.compute-1.amazonaws.com:9090/pvm?wsdl";
		spdmWsdl = "http://http://ec2-54-235-118-152.compute-1.amazonaws.com:9091/spdm_service?wsdl";
		marketplaceWsdl = "http://hestia.atc.gr/marketplace2?wsdl";

		ncvmFeedbackTrack = false;
		csvmTrack = false;
		pvmTrack = false;
		spdmTrack = false;
		marketplaceTrack = false;

		registerAddress = null;
	}
	
	/**
	 * Load the default settings from configuration file configure/config.txt
	 * @throws IOException if an error is generated when attempting to read the file.
	 */
	public void LoadConfigurationDefault() throws IOException {
		LoadConfiguration("configure/config.txt");
	}

	/**
	 * Load settings from a configuration file.
	 * @param file the file to load the configuration details from.
	 * @throws IOException if an error is generated when attempting to read the file.
	 */
	public void LoadConfiguration(String file) throws IOException {
		Map<String, String> configuration = EncodeSupport.LoadKeyValueFile (file);

		// Now do something with the loaded configuration
		if (configuration.containsKey("address")) {
			registerAddress = configuration.get("address");
		}
		if (configuration.containsKey("feedbackaddress")) {
			ncvmFeedbackWsdl = configuration.get("feedbackaddress") + "?wsdl";
		}
		if (configuration.containsKey("csvmaddress")) {
			csvmWsdl = configuration.get("csvmaddress") + "?wsdl";
		}
		if (configuration.containsKey("pvmaddress")) {
			pvmWsdl = configuration.get("pvmaddress") + "?wsdl";
		}
		if (configuration.containsKey("spdmaddress")) {
			spdmWsdl = configuration.get("spdmaddress") + "?wsdl";
		}
		if (configuration.containsKey("marketplaceaddress")) {
			marketplaceWsdl = configuration.get("marketplaceaddress") + "?wsdl";
		}
		if (configuration.containsKey("feedbacktrack")) {
			ncvmFeedbackTrack = Boolean.parseBoolean(configuration.get("feedbacktrack"));
		}
		if (configuration.containsKey("csvmtrack")) {
			csvmTrack = Boolean.parseBoolean(configuration.get("csvmtrack"));
		}
		if (configuration.containsKey("pvmtrack")) {
			pvmTrack = Boolean.parseBoolean(configuration.get("pvmtrack"));
		}
		if (configuration.containsKey("spdmtrack")) {
			spdmTrack = Boolean.parseBoolean(configuration.get("spdmtrack"));
		}
		if (configuration.containsKey("marketplacetrack")) {
			marketplaceTrack = Boolean.parseBoolean(configuration.get("marketplacetrack"));
		}

		Activator.logLine("Feedback (" + (ncvmFeedbackTrack?"tracked":"declarative") + "): " + ncvmFeedbackWsdl);
		Activator.logLine("CSVM (" + (csvmTrack?"tracked":"declarative") + "): " + csvmWsdl);
		Activator.logLine("PVM (" + (pvmTrack?"tracked":"declarative") + "): " + pvmWsdl);
		Activator.logLine("SPDM (" + (spdmTrack?"tracked":"declarative") + "): " + spdmWsdl);
		Activator.logLine("Marketplace (" + (marketplaceTrack?"tracked":"declarative") + "): " + marketplaceWsdl);
	}
	
	/**
	 * Returns the URL of the WSDL for the NCVM Feedback interface.
	 * The returned value is the URL including the "?wsdl" suffix.
	 * @return ncvmFeedbackWsdl the URL for the WSDL.
	 */
	public String getNcvmFeedbackWsdl() {
		return ncvmFeedbackWsdl;
	}

	/**
	 * Set the URL of the WSDL for the NCVM Feedback interface.
	 * The value of the URL should include the "?wsdl" suffix.
	 * @param ncvmFeedbackWsdl the URL to set
	 */
	public void setNcvmFeedbackWsdl(String ncvmFeedbackWsdl) {
		this.ncvmFeedbackWsdl = ncvmFeedbackWsdl;
		Activator.logLine("Feedback (" + (ncvmFeedbackTrack?"tracked":"declarative") + "): " + ncvmFeedbackWsdl);
	}

	/**
	 * Returns the URL of the WSDL for the CSVM.
	 * The returned value is the URL including the "?wsdl" suffix.
	 * @return csvmWsdl the URL for the WSDL.
	 */
	public String getCsvmWsdl() {
		return csvmWsdl;
	}

	/**
	 * Set the URL of the WSDL for the CSVM.
	 * The value of the URL should include the "?wsdl" suffix.
	 * @param csvmWsdl the URL to set
	 */
	public void setCsvmWsdl(String csvmWsdl) {
		this.csvmWsdl = csvmWsdl;
		Activator.logLine("CSVM (" + (csvmTrack?"tracked":"declarative") + "): " + csvmWsdl);
	}

	/**
	 * Returns the URL of the WSDL for the PVM.
	 * The returned value is the URL including the "?wsdl" suffix.
	 * @return pvmWsdl the URL for the WSDL.
	 */
	public String getPvmWsdl() {
		return pvmWsdl;
	}

	/**
	 * Set the URL of the WSDL for the PVM.
	 * The value of the URL should include the "?wsdl" suffix.
	 * @param pvmWsdl the URL to set
	 */
	public void setPvmWsdl(String pvmWsdl) {
		this.pvmWsdl = pvmWsdl;
		Activator.logLine("PVM (" + (pvmTrack?"tracked":"declarative") + "): " + pvmWsdl);
	}

	/**
	 * Returns the URL of the WSDL for the SPDM.
	 * The returned value is the URL including the "?wsdl" suffix.
	 * @return spdmWsdl the URL for the WSDL.
	 */
	public String getSpdmWsdl() {
		return spdmWsdl;
	}

	/**
	 * Set the URL of the WSDL for the SPDM.
	 * The value of the URL should include the "?wsdl" suffix.
	 * @param spdmWsdl the URL to set
	 */
	public void setSpdmWsdl(String spdmWsdl) {
		this.spdmWsdl = spdmWsdl;
		Activator.logLine("SPDM (" + (spdmTrack?"tracked":"declarative") + "): " + spdmWsdl);
	}

	/**
	 * Returns the URL of the WSDL for the Marketplace.
	 * The returned value is the URL including the "?wsdl" suffix.
	 * @return marketplaceWsdl the URL for the WSDL.
	 */
	public String getMarketplaceWsdl() {
		return marketplaceWsdl;
	}

	/**
	 * Set the URL of the WSDL for the Marketplace.
	 * The value of the URL should include the "?wsdl" suffix.
	 * @param marketplaceWsdl the URL to set
	 */
	public void setMarketplaceWsdl(String marketplaceWsdl) {
		this.marketplaceWsdl = marketplaceWsdl;
		Activator.logLine("Marketplace (" + (marketplaceTrack?"tracked":"declarative") + "): " + marketplaceWsdl);
	}

	/**
	 * Gets whether the NCVM Feedback interface is set to be tracked.
	 * @return true if it should be tracked.
	 */
	public boolean isNcvmFeedbackTrack() {
		return ncvmFeedbackTrack;
	}

	/**
	 * Set whether the NCVM Feedback interface should be tracked.
	 * @param ncvmFeedbackTrack true if it should be tracked.
	 */
	public void setNcvmFeedbackTrack(boolean ncvmFeedbackTrack) {
		this.ncvmFeedbackTrack = ncvmFeedbackTrack;
	}

	/**
	 * Gets whether the CSVM is set to be tracked.
	 * @return true if it should be tracked.
	 */
	public boolean isCsvmTrack() {
		return csvmTrack;
	}

	/**
	 * Set whether the CSVM should be tracked.
	 * @param csvmTrack true if it should be tracked.
	 */
	public void setCsvmTrack(boolean csvmTrack) {
		this.csvmTrack = csvmTrack;
	}

	/**
	 * Gets whether the PVM is set to be tracked.
	 * @return true if it should be tracked.
	 */
	public boolean isPvmTrack() {
		return pvmTrack;
	}

	/**
	 * Set whether the PVM should be tracked.
	 * @param pvmTrack true if it should be tracked.
	 */
	public void setPvmTrack(boolean pvmTrack) {
		this.pvmTrack = pvmTrack;
	}

	/**
	 * Gets whether the SPDM is set to be tracked.
	 * @return true if it should be tracked.
	 */
	public boolean isSpdmTrack() {
		return spdmTrack;
	}

	/**
	 * Set whether the SPDM should be tracked.
	 * @param spdmTrack true if it should be tracked.
	 */
	public void setSpdmTrack(boolean spdmTrack) {
		this.spdmTrack = spdmTrack;
	}

	/**
	 * Gets whether the Marketplace is set to be tracked.
	 * @return true if it should be tracked.
	 */
	public boolean isMarketplaceTrack() {
		return marketplaceTrack;
	}

	/**
	 * Set whether the Marketplace should be tracked.
	 * @param marketplaceTrack true if it should be tracked.
	 */
	public void setMarketplaceTrack(boolean marketplaceTrack) {
		this.marketplaceTrack = marketplaceTrack;
	}

	/**
	 * Return the registered address for the service.
	 * @return the registered address.
	 */
	public String getRegisterAddress() {
		return registerAddress;
	}

	/**
	 * Set the registered address of the service.
	 * Note this doesn't actually change the address, so should only be used to register the actual address.
	 * @param registerAddress the address the service is registered at.
	 */
	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}
}
