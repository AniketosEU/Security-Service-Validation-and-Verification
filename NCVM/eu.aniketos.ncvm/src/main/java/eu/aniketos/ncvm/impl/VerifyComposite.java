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
import java.util.Iterator;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.ServiceTask;

import eu.aniketos.components.verification.compositionsecurityvalidation.CompositionSecurityValidationResult;
import eu.aniketos.data.SPState;
import eu.aniketos.marketplace.CompositionPlan;
import eu.aniketos.ncvm.IVerificationResult;
import eu.aniketos.ncvm.impl.NestedCompositionVerification.AniketosServices;
import eu.aniketos.ncvm.impl.NestedCompositionVerification.ServiceInfo;

public class VerifyComposite {
	private String bpmn;
	private String conspec;
	private String propertyID;
	private String serviceID;
	private AniketosServices call;
	private String serviceFilter;

	public VerifyComposite(String serviceID, String propertyID, String conspec, AniketosServices call, String bpmn, String serviceFilter) {
		this.serviceID = serviceID;
		this.propertyID = propertyID;
		this.conspec = conspec;
		this.call = call;
		this.bpmn = bpmn;
		this.serviceFilter = serviceFilter;
	}

	private Definitions definitions = null;

	/**
	 * @param bpmn the bpmn to set
	 */
	public void setBpmn(String bpmn) {
		this.bpmn = bpmn;
	}
	/**
	 * @param conspec the conspec to set
	 */
	public void setConspec(String conspec) {
		this.conspec = conspec;
	}
	/**
	 * @param propertyID the propertyID to set
	 */
	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}
	/**
	 * @param serviceID the serviceID to set
	 */
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}
	/**
	 * @param call the call to set
	 */
	public void setCall(AniketosServices call) {
		this.call = call;
	}

	public IVerificationResult verify () {
		IVerificationResult result = new VerificationResult();
		boolean filterMatches = (serviceFilter.isEmpty() || serviceFilter.equalsIgnoreCase(serviceID));
		result.setResult(1);

		SPState state = SPState.UnBind;
		if ((serviceID != null) && (serviceID.length() > 0)) {
			state = CacheSupport.CheckCachedProperty (call.spdm, serviceID, propertyID, result);
		}

		if (filterMatches && (state == SPState.Verified)) {
			Activator.logLine("Property pre-cached and verified");
		}
		else {
			// Check the property
			// We need to different things depending on what the property is
			if (filterMatches && (propertyID.equalsIgnoreCase("SoD") || propertyID.equalsIgnoreCase("BoD"))) {
				// Simply pass the query on to the CSVM
				CompositionPlan compositionPlan = new CompositionPlan();
				compositionPlan.setBPMNXML(bpmn);
				compositionPlan.setCompositionPlanID(serviceID);
				CompositionSecurityValidationResult partial;
				partial = call.csvm.VerifyCompositionCompliance(compositionPlan);
				if (partial.getVerificationResult() == true) {
					result.setResult(1);
					Activator.logLine("CSVM verification passed: " + partial.getVerificationExplaination());
					CacheSupport.SetCachedProperty (call.spdm, serviceID, propertyID, "1", SPState.Verified);
				}
				else {
					result.setResult(0);
					Activator.logLine("CSVM verification failed: " + partial.getVerificationExplaination());
					// TODO: Set correct verification state
					CacheSupport.SetCachedProperty (call.spdm, serviceID, propertyID, "0", SPState.Verified);
				}
			}
			else {
				// Parse the BPMN2 file
				try {
					definitions = BPMNSupport.parseBPMN(bpmn);
				} catch (IOException e) {
					Activator.logLine("Error reading BPMN2 data:" + e.toString());
				}
				
				// Cycle through all tasks in the composition
				for (Iterator<RootElement> rootIter = definitions.getRootElements().iterator(); rootIter.hasNext();) {
					RootElement rootElement = rootIter.next();
		
					if (rootElement instanceof org.eclipse.bpmn2.Process) {
						for (Iterator<FlowElement> flowIter = ((org.eclipse.bpmn2.Process) rootElement).getFlowElements().iterator(); flowIter.hasNext();) {
							FlowElement flow = flowIter.next();
							if (flow instanceof ServiceTask) {
								// Service Tasks need to be handled
								ServiceTask atomicService = (ServiceTask) flow;
		
								String serviceId = BPMNSupport.getExtensionValue (atomicService, "id");
								if (serviceId.isEmpty()) {
									// If there's no ID extension element, we default to the service type
									serviceId = atomicService.getId();
								}

								Activator.logLine("Marketplace, requesting service: " + serviceId);
								ServiceInfo serviceInfo = ModuleSupport.FindServiceInfo(call.marketplace, serviceId);
	
								switch (serviceInfo.type) {
								case ATOMIC:
								{
									VerifyAtomic verify = new VerifyAtomic(serviceId, propertyID, conspec, call, serviceInfo.details, serviceFilter);
									IVerificationResult partial = verify.verify();
	
									if (partial.getResult() <= 0) {
										// Verification fails completely
										result.setResult(0);
									}
								}
									break;
								case COMPOSITE:
								{
									VerifyComposite verify = new VerifyComposite(serviceId, propertyID, conspec, call, serviceInfo.details, serviceFilter);
									IVerificationResult partial = verify.verify();
									
									if (partial.getResult() <= 0) {
										// Verification fails completely
										result.setResult(0);
									}
								}
									break;
								case ERROR:
									result.setError(4, "Marketplace error");
									break;
								default:
									result.setError(4, "Unknown service type");
									break;
								}
							}
						}
					}
				}

				// Having checked all of the subservices, we now have a result for the composite service
				if (result.getResult() >= 0) {
					if ((serviceID != null) && (serviceID.length() > 0)) {
						CacheSupport.SetCachedProperty (call.spdm, serviceID, propertyID, Integer.toString(result.getResult()), SPState.Verified);
					}
					Activator.logLine("Composite property verification successful");
				}
				else {
					Activator.logLine("Composite property verification unsuccessful");
				}
			}
		}
		
		return result;
	}
}
