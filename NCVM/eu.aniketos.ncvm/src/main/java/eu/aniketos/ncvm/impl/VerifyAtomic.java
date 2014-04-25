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

import org.apache.commons.codec.binary.Base64;

import eu.aniketos.components.verification.propertyverification.PropertyVerificationResult;
import eu.aniketos.data.SPState;
import eu.aniketos.ncvm.IVerificationResult;
import eu.aniketos.ncvm.impl.NestedCompositionVerification.AniketosServices;

public class VerifyAtomic {
	private String conspec;
	private String propertyID;
	private String serviceID;
	private AniketosServices call;
	private String details;
	private String serviceFilter;

	public VerifyAtomic(String serviceID, String propertyID, String conspec, AniketosServices call, String details, String serviceFilter) {
		this.serviceID = serviceID;
		this.propertyID = propertyID;
		this.conspec = conspec;
		this.call = call;
		this.details = details;
		this.serviceFilter = serviceFilter;
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
	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	public IVerificationResult verify () {
		IVerificationResult result = new VerificationResult();
		boolean filterMatches = (serviceFilter.isEmpty() || serviceFilter.equalsIgnoreCase(serviceID));

		if (!propertyID.equals("Confidentiality") && !propertyID.equals("DangerousFunctions")) {
			Activator.logLine("Property doesn't apply to atomic services: " + propertyID);
		}
		
		if (filterMatches) {
			SPState state = CacheSupport.CheckCachedProperty (call.spdm, serviceID, propertyID, result);
			if (state == SPState.Verified) {
				Activator.logLine("Property already verified and cached");
				result.setResult(1);
			}
			else {
				Activator.logLine("Calling PVM, service task ID: " + serviceID);
				if (call.pvm != null) {
		            Base64 encoder = new Base64();
		            String agreementTemplateEncoded = encoder.encodeToString(conspec.getBytes());
		
		            try {
		            	PropertyVerificationResult verificationProperty = call.pvm.verifyTechnicalTrustProperties(agreementTemplateEncoded, details);
		    			Activator.logLine("PVM result: " + verificationProperty.getVerificationResult() + "; " + verificationProperty.getVerificationExplaination());
		    			int verified = verificationProperty.getVerificationResult();
		    			if (verified != 0) {
		    				result.setResult(0);
							Activator.logLine("Atomic property verification unsuccessful");
		    			}
		    			else {
		    				result.setResult(1);
							CacheSupport.SetCachedProperty (call.spdm, serviceID, propertyID, Integer.toString(result.getResult()), SPState.Verified);
							Activator.logLine("Atomic property verification successful, result cached in SPDM");
		    			}            	
		            }
		            catch (Exception e) {
		    			Activator.logLine("PVM error: " + e.getMessage());
		    			Activator.logLine("The PVM is not accessible without authentication using dummy mode.");
		    			result.setError(3, "PVM error");
		            }
				}
				else {
					Activator.logLine("PVM not found");
					result.setError(3, "PVM not found");
				}
			}
		}
		else {
			Activator.logLine("ConSpec only applies to " + serviceFilter);
			Activator.logLine("Therefore skipping for " + serviceID);
			result.setResult(1);
		}
		
		return result;
	}
}
