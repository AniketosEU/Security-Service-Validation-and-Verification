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

import eu.aniketos.marketplace.IMarketplace;
import eu.aniketos.ncvm.impl.NestedCompositionVerification.AniketosServices;
import eu.aniketos.ncvm.impl.NestedCompositionVerification.ServiceInfo;
import eu.aniketos.ncvm.impl.NestedCompositionVerification.ServiceType;

class ModuleSupport {
	static AniketosServices ConnectToAniketosServices () {
		AniketosServices call = new AniketosServices();

		// Obtain connection to the SPDM
		try {
			call.spdm = Activator.getDefault().getServices().getSPDM();
		} catch (Exception e) {
			call.spdm = null;
			Activator.logLine("Failed to get remote SPDM services");
			e.printStackTrace();
		}

		// Obtain connection to the CSVM
		try {
			call.csvm = Activator.getDefault().getServices().getCSVM();
		} catch (Exception e) {
			call.csvm = null;
			Activator.logLine("Failed to get remote CSVM service.");
			e.printStackTrace();
		}

		// Obtain connection to the PVM
		try {
			call.pvm = Activator.getDefault().getServices().getPVM();
		} catch (Exception e) {
			call.pvm = null;
			Activator.logLine("Failed to get remote PVM service.");
			e.printStackTrace();
		}

		// Obtain connection to the Marketplace
		try {
			call.marketplace = Activator.getDefault().getServices().getMarketplace();
		} catch (Exception e) {
			call.marketplace = null;
			Activator.logLine("Failed to get remote Marketplace service.");
			e.printStackTrace();
		}

		return call;
	}

	static ServiceInfo FindServiceInfo (IMarketplace marketplace, String serviceID) {
		ServiceInfo info = new ServiceInfo();

		info.type = ServiceType.UNKNOWN;
		if (marketplace != null) {
			// Check whether the service is composite
			try {
				String bpmn = marketplace.getBpmnDiagram(serviceID);
				if (!bpmn.isEmpty()) {
					Activator.logLine("Composite BPMN: " + bpmn);
					info.details = bpmn;
					info.type = ServiceType.COMPOSITE;
				}
			}
			catch (Exception e) {
				// Do nothing
				Activator.logLine("No BPMN for service: " + serviceID);
			}
			
			// Check whether the service is atomic
			if (info.type == ServiceType.UNKNOWN) {
				try {
					String sourceURL = marketplace.getSource(serviceID);
					if (!sourceURL.isEmpty()) {
						Activator.logLine("Source URL: " + sourceURL);
						info.details = sourceURL;
						info.type = ServiceType.ATOMIC;
					}
					else {
						// We assume it's atomic anyway
						info.details = serviceID;
						info.type = ServiceType.ATOMIC;
					}
				}
				catch (Exception e) {
					Activator.logLine("No source for service: " + serviceID);

					// We assume it's atomic anyway
					info.details = serviceID;
					info.type = ServiceType.ATOMIC;
				}
			}
		}
		else {
			Activator.logLine("Marketplace not found.");
			info.type = ServiceType.ERROR;
		}
		
		return info;
	}
}
