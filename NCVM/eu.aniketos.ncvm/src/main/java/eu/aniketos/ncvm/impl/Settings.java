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
import java.net.MalformedURLException;
import java.util.Map;

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

	public Settings() throws MalformedURLException {
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
	
	public void LoadConfigurationDefault() throws IOException {
		LoadConfiguration("configure/config.txt");
	}

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
	 * @return the ncvmFeedbackWsdl
	 */
	public String getNcvmFeedbackWsdl() {
		return ncvmFeedbackWsdl;
	}
	/**
	 * @param ncvmFeedbackWsdl the ncvmFeedbackWsdl to set
	 */
	public void setNcvmFeedbackWsdl(String ncvmFeedbackWsdl) {
		this.ncvmFeedbackWsdl = ncvmFeedbackWsdl;
		Activator.logLine("Feedback (" + (ncvmFeedbackTrack?"tracked":"declarative") + "): " + ncvmFeedbackWsdl);
	}
	/**
	 * @return the csvmWsdl
	 */
	public String getCsvmWsdl() {
		return csvmWsdl;
	}
	/**
	 * @param csvmWsdl the csvmWsdl to set
	 */
	public void setCsvmWsdl(String csvmWsdl) {
		this.csvmWsdl = csvmWsdl;
		Activator.logLine("CSVM (" + (csvmTrack?"tracked":"declarative") + "): " + csvmWsdl);
	}
	/**
	 * @return the pvmWsdl
	 */
	public String getPvmWsdl() {
		return pvmWsdl;
	}
	/**
	 * @param pvmWsdl the pvmWsdl to set
	 */
	public void setPvmWsdl(String pvmWsdl) {
		this.pvmWsdl = pvmWsdl;
		Activator.logLine("PVM (" + (pvmTrack?"tracked":"declarative") + "): " + pvmWsdl);
	}
	/**
	 * @return the spdmWsdl
	 */
	public String getSpdmWsdl() {
		return spdmWsdl;
	}
	/**
	 * @param spdmWsdl the spdmWsdl to set
	 */
	public void setSpdmWsdl(String spdmWsdl) {
		this.spdmWsdl = spdmWsdl;
		Activator.logLine("SPDM (" + (spdmTrack?"tracked":"declarative") + "): " + spdmWsdl);
	}
	/**
	 * @return the marketplaceWsdl
	 */
	public String getMarketplaceWsdl() {
		return marketplaceWsdl;
	}
	/**
	 * @param marketplaceWsdl the marketplaceWsdl to set
	 */
	public void setMarketplaceWsdl(String marketplaceWsdl) {
		this.marketplaceWsdl = marketplaceWsdl;
		Activator.logLine("Marketplace (" + (marketplaceTrack?"tracked":"declarative") + "): " + marketplaceWsdl);
	}
	/**
	 * @return the ncvmFeedbackTrack
	 */
	public boolean isNcvmFeedbackTrack() {
		return ncvmFeedbackTrack;
	}
	/**
	 * @param ncvmFeedbackTrack the ncvmTrack to set
	 */
	public void setNcvmFeedbackTrack(boolean ncvmFeedbackTrack) {
		this.ncvmFeedbackTrack = ncvmFeedbackTrack;
	}
	/**
	 * @return the csvmTrack
	 */
	public boolean isCsvmTrack() {
		return csvmTrack;
	}
	/**
	 * @param csvmTrack the csvmTrack to set
	 */
	public void setCsvmTrack(boolean csvmTrack) {
		this.csvmTrack = csvmTrack;
	}
	/**
	 * @return the pvmTrack
	 */
	public boolean isPvmTrack() {
		return pvmTrack;
	}
	/**
	 * @param pvmTrack the pvmTrack to set
	 */
	public void setPvmTrack(boolean pvmTrack) {
		this.pvmTrack = pvmTrack;
	}
	/**
	 * @return the spdmTrack
	 */
	public boolean isSpdmTrack() {
		return spdmTrack;
	}
	/**
	 * @param spdmTrack the spdmTrack to set
	 */
	public void setSpdmTrack(boolean spdmTrack) {
		this.spdmTrack = spdmTrack;
	}
	/**
	 * @return the marketplaceTrack
	 */
	public boolean isMarketplaceTrack() {
		return marketplaceTrack;
	}
	/**
	 * @param marketplaceTrack the marketplaceTrack to set
	 */
	public void setMarketplaceTrack(boolean marketplaceTrack) {
		this.marketplaceTrack = marketplaceTrack;
	}
	/**
	 * Return the registered address for the service
	 * @return the registered address
	 */
	public String getRegisterAddress() {
		return registerAddress;
	}
	/**
	 * Set the registered address of the service
	 * Note this doesn't actual change the address, so should only be used to register the actual address
	 * @param registerAddress the address the service is registered at
	 */
	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}
}
