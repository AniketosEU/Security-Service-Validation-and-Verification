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

package eu.aniketos.components.verification.compositionsecurityvalidation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleContext;

public class Settings {
	private String registerAddress;

	public Settings() {
		registerAddress = null;
	}
	
	public void LoadConfigurationDefault() throws IOException {
		LoadConfiguration("configure/config.txt");
	}

	public void LoadConfiguration(String file) throws IOException {
		Map<String, String> configuration = new HashMap<String, String>();
		configuration.clear();

		BundleContext context = Activator.getContext();
		// Load data from the configuration file if there is one
		System.out.println("Reading configuration from: " + file);
		URL configURL = context.getBundle().getEntry(file);
		if (configURL != null) {
			// Read the configuration
			BufferedReader input = new BufferedReader(new InputStreamReader(configURL.openStream()));
			try {
				String read = "";
				while (read != null) {
					read = input.readLine();
					// Skip comments and blank lines
					if ((read != null) && (!read.isEmpty()) && (!read.startsWith("#"))) {
						// Read in the key=value pair
						int separator = read.indexOf('=');
						if (separator >= 0) {
							String key = read.substring(0, separator).trim().toLowerCase();
							String value = read.substring(separator + 1).trim();
							// Store the result in our map
							configuration.put(key, value);
						}
					}
				}
			}
			finally {
				input.close();
			}
		}

		// Now do something with the loaded configuration
		if (configuration.containsKey("address")) {
			registerAddress = configuration.get("address");
		}
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
