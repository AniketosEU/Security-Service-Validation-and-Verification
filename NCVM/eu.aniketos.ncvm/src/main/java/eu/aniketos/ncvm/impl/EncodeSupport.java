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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleContext;

//import org.apache.commons.codec.binary.Base64;

class EncodeSupport {
	static String decodeData(String data) {
		return data;
		//return base64StringToString(data);
	}

	static String encodeData(String data) {
        //Base64 encoder = new Base64();
        //return encoder.encodeToString(data.getBytes());
		return data;
	}

//	private static String base64StringToString(String base64String) {
//		byte[] bytes = Base64.decodeBase64(base64String);
//		return new String(bytes);
//
//	}

	/**
	 * This methods read a file into a string.
	 * 
	 * @param filePath
	 *            Path of the text file that should be read.
	 * @return File content as string.
	 */
	static String readFileAsString(String filePath) throws java.io.IOException {
		int fileSize = (int) new File(filePath).length();
		byte[] buffer = new byte[fileSize];
		BufferedInputStream f = null;
		try {
			FileInputStream inputStream = new FileInputStream(filePath);
			f = new BufferedInputStream(inputStream);
			f.read(buffer);
		} finally {
			if (f != null)
				try {
					f.close();
				} catch (IOException ignored) {
				}
		}
		return new String(buffer);
	}

	static Map<String, String> LoadKeyValueFile (String file) throws IOException {
		Map<String, String> keyValues = new HashMap<String, String>();
		keyValues.clear();

		BundleContext context = Activator.getContext();
		// Load data from the configuration file if there is one
		System.out.println("Reading key-value pairs from: " + file);
		URL configURL = context.getBundle().getEntry(file);
		if (configURL != null) {
			// Read the key value pairs
			BufferedReader input = new BufferedReader(new InputStreamReader(configURL.openStream()));
			try {
				String read = "";
				while (read != null) {
					read = input.readLine();
					// Skip comments and blank lines
					if ((read != null) && (!read.isEmpty()) && (!read.startsWith("#"))) {
						// Read in the key-value pair
						int separator = read.indexOf('=');
						if (separator >= 0) {
							String key = read.substring(0, separator).trim().toLowerCase();
							String value = read.substring(separator + 1).trim();
							// Store the result in our map
							keyValues.put(key, value);
						}
					}
				}
			}
			finally {
				input.close();
			}
		}
		
		return keyValues;
	}
	
}
