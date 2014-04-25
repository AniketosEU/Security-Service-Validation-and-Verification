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

import eu.aniketos.ncvm.userinterface.Activator;

public class Settings {
	String ncvmAddress;
	String pvmAddress;
	String csvmAddress;
	String spdmAddress;
	String marketplaceAddress;
	boolean ncvmTracker;
	boolean pvmTracker;
	boolean csvmTracker;
	boolean spdmTracker;
	boolean marketplaceTracker;
	String registerAddress;
	boolean registerAuto;

	public Settings() {
		Activator.getDefault().getPreferenceStore().setDefault("ncvmAddress", "http://montefalco:9094/ncvmfeedback?wsdl");
		Activator.getDefault().getPreferenceStore().setDefault("pvmAddress", "http://montefalco:9090/pvm?wsdl");
		Activator.getDefault().getPreferenceStore().setDefault("csvmAddress", "http://montefalco:9095/csvm?wsdl");
		Activator.getDefault().getPreferenceStore().setDefault("spdmAddress", "http://localhost:9092/scpm?wsdl");
		Activator.getDefault().getPreferenceStore().setDefault("marketplaceAddress", "http://hestia.atc.gr/marketplace?wsdl");

		Activator.getDefault().getPreferenceStore().setDefault("ncvmTracker", true);
		Activator.getDefault().getPreferenceStore().setDefault("pvmTracker", true);
		Activator.getDefault().getPreferenceStore().setDefault("csvmTracker", false);
		Activator.getDefault().getPreferenceStore().setDefault("spdmTracker", true);
		Activator.getDefault().getPreferenceStore().setDefault("marketplaceTracker", false);

		Activator.getDefault().getPreferenceStore().setDefault("registerAddress", "http://localhost:9094/ncvmfeedback");
		Activator.getDefault().getPreferenceStore().setDefault("registerAuto", true);
		
		ncvmAddress = Activator.getDefault().getPrefString("ncvmAddress");
		pvmAddress = Activator.getDefault().getPrefString("pvmAddress");
		csvmAddress = Activator.getDefault().getPrefString("csvmAddress");
		spdmAddress = Activator.getDefault().getPrefString("spdmAddress");
		marketplaceAddress = Activator.getDefault().getPrefString("marketplaceAddress");

		ncvmTracker = Activator.getDefault().getPreferenceStore().getBoolean("ncvmTracker");
		pvmTracker = Activator.getDefault().getPreferenceStore().getBoolean("pvmTracker");
		csvmTracker = Activator.getDefault().getPreferenceStore().getBoolean("csvmTracker");
		spdmTracker = Activator.getDefault().getPreferenceStore().getBoolean("spdmTracker");
		marketplaceTracker = Activator.getDefault().getPreferenceStore().getBoolean("marketplaceTracker");

		registerAddress = Activator.getDefault().getPrefString("registerAddress");
		registerAuto = Activator.getDefault().getPreferenceStore().getBoolean("registerAuto");
	}
	
	public Settings(Settings copyFrom) {
		ncvmAddress = copyFrom.ncvmAddress;
		pvmAddress = copyFrom.pvmAddress;
		csvmAddress = copyFrom.csvmAddress;
		spdmAddress = copyFrom.spdmAddress;
		marketplaceAddress = copyFrom.marketplaceAddress;
		
		ncvmTracker= copyFrom.ncvmTracker;
		pvmTracker = copyFrom.pvmTracker;
		csvmTracker = copyFrom.csvmTracker;
		spdmTracker = copyFrom.spdmTracker;
		marketplaceTracker = copyFrom.marketplaceTracker;

		registerAddress = copyFrom.registerAddress;
		registerAuto = copyFrom.registerAuto;
	}

	/**
	 * @return the ncvmAddress
	 */
	public String getNcvmAddress() {
		return ncvmAddress;
	}

	/**
	 * @param ncvmAddress the ncvmAddress to set
	 */
	public void setNcvmAddress(String ncvmAddress) {
		this.ncvmAddress = ncvmAddress;
	}

	/**
	 * @return the pvmAddress
	 */
	public String getPvmAddress() {
		return pvmAddress;
	}

	/**
	 * @param pvmAddress the pvmAddress to set
	 */
	public void setPvmAddress(String pvmAddress) {
		this.pvmAddress = pvmAddress;
	}

	/**
	 * @return the csvmAddress
	 */
	public String getCsvmAddress() {
		return csvmAddress;
	}

	/**
	 * @param csvmAddress the csvmAddress to set
	 */
	public void setCsvmAddress(String csvmAddress) {
		this.csvmAddress = csvmAddress;
	}

	/**
	 * @return the spdmAddress
	 */
	public String getSpdmAddress() {
		return spdmAddress;
	}

	/**
	 * @param spdmAddress the spdmAddress to set
	 */
	public void setSpdmAddress(String spdmAddress) {
		this.spdmAddress = spdmAddress;
	}

	/**
	 * @return the marketplaceAddress
	 */
	public String getMarketplaceAddress() {
		return marketplaceAddress;
	}

	/**
	 * @param marketplaceAddress the marketplaceAddress to set
	 */
	public void setMarketplaceAddress(String marketplaceAddress) {
		this.marketplaceAddress = marketplaceAddress;
	}

	/**
	 * @return the ncvmTracker
	 */
	public boolean isNcvmTracker() {
		return ncvmTracker;
	}

	/**
	 * @param ncvmTracker the ncvmTracker to set
	 */
	public void setNcvmTracker(boolean ncvmTracker) {
		this.ncvmTracker = ncvmTracker;
	}

	/**
	 * @return the pvmTracker
	 */
	public boolean isPvmTracker() {
		return pvmTracker;
	}

	/**
	 * @param pvmTracker the pvmTracker to set
	 */
	public void setPvmTracker(boolean pvmTracker) {
		this.pvmTracker = pvmTracker;
	}

	/**
	 * @return the csvmTracker
	 */
	public boolean isCsvmTracker() {
		return csvmTracker;
	}

	/**
	 * @param csvmTracker the csvmTracker to set
	 */
	public void setCsvmTracker(boolean csvmTracker) {
		this.csvmTracker = csvmTracker;
	}

	/**
	 * @return the spdmTracker
	 */
	public boolean isSpdmTracker() {
		return spdmTracker;
	}

	/**
	 * @param spdmTracker the spdmTracker to set
	 */
	public void setSpdmTracker(boolean spdmTracker) {
		this.spdmTracker = spdmTracker;
	}

	/**
	 * @return the marketplaceTracker
	 */
	public boolean isMarketplaceTracker() {
		return marketplaceTracker;
	}

	/**
	 * @param marketplaceTracker the marketplaceTracker to set
	 */
	public void setMarketplaceTracker(boolean marketplaceTracker) {
		this.marketplaceTracker = marketplaceTracker;
	}

	/**
	 * @return the registerAddress
	 */
	public String getRegisterAddress() {
		return registerAddress;
	}

	/**
	 * @param registerAddress the registerAddress to set
	 */
	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}

	/**
	 * @return the registerAuto
	 */
	public boolean isRegisterAuto() {
		return registerAuto;
	}

	/**
	 * @param registerAuto the registerAuto to set
	 */
	public void setRegisterAuto(boolean registerAuto) {
		this.registerAuto = registerAuto;
	}
}
