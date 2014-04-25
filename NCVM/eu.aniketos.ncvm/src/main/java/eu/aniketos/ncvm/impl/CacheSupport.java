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

import eu.aniketos.data.ISPDMService;
import eu.aniketos.data.ISecurityProperty;
import eu.aniketos.data.SPState;
import eu.aniketos.ncvm.IVerificationResult;

class CacheSupport {
	static SPState CheckCachedProperty (ISPDMService spdm, String serviceID, String propertyID, IVerificationResult result) {
		SPState state = SPState.UnBind;
		if (spdm != null) {
			Activator.logLine("SPDM service: " + serviceID);
			Activator.logLine("SPDM checking property: " + propertyID);

			// TODO: Figure out why this bit of code, which must be removed, is needed
			//eu.aniketos.ncvm.impl.WebService registerService = new eu.aniketos.ncvm.impl.WebService();
			//registerService.setServiceID(serviceID);
			//eu.aniketos.ncvm.impl.SecurityProperty registerProperty = new eu.aniketos.ncvm.impl.SecurityProperty();
			//registerProperty.setPropertyID(propertyID);
			//registerProperty.setPropertyValue("true");
			//spdm.registerService(registerService, registerProperty);

			ISecurityProperty property = spdm.getSecurityProperty(serviceID, propertyID);
			if (property != null) {
				state = property.getState();
			}
		} else {
			Activator.logLine("SPDM not found.");
			result.setError(1, "SPDM not found");
		}
		
		return state;
	}
	
	static void SetCachedProperty (ISPDMService spdm, String serviceID, String propertyID, String propertyValue, SPState state) {
		if (spdm != null) {
			Activator.logLine("SPDM service: " + serviceID);
			Activator.logLine("SPDM setting property: " + propertyID);

			eu.aniketos.ncvm.impl.WebService registerService = new eu.aniketos.ncvm.impl.WebService();
			registerService.setServiceID(serviceID);
			eu.aniketos.ncvm.impl.SecurityProperty registerProperty = new eu.aniketos.ncvm.impl.SecurityProperty();
			registerProperty.setPropertyID(propertyID);
			registerProperty.setPropertyValue(propertyValue);
			registerProperty.setState(state);
			spdm.registerService(registerService, registerProperty);
		}
	}
}
