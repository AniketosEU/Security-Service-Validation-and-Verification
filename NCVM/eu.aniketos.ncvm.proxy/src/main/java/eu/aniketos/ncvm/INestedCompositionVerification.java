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

package eu.aniketos.ncvm;

import eu.aniketos.data.ICompositionPlan;
import eu.aniketos.data.IConsumerPolicy;

/**
 * Recursively call other services to determine whether a policy applies to it
 * Performs the task of deconstructing a service into its subservices in order
 * to select the correct module to invoke for verification.
 * @author LJMU/David Llewellyn-Jones
 *
 */
public interface INestedCompositionVerification {

	/**
	 * Performs recursive decomposition of a service and invokes either the
	 * CSVM, PVM or SPDM depending on the result in order to verify whether
	 * the policy holds for the service.
	 * @param service the service to check the policy agains
	 * @param policy the policy to check against the service
	 * @return a value representing the result of the verification checks
	 */
	public IVerificationResult verifyProperty(ICompositionPlan service, IConsumerPolicy policy);

	public IVerificationResult verifyPropertyDeployed(String serviceID, IConsumerPolicy policy);

	public void configureNCVMFeedback(String wsdlUrl, boolean useTracker);

	public void configureCSVM(String wsdlUrl, boolean useTracker);

	public void configurePVM(String wsdlUrl, boolean useTracker);

	public void configureSPDM(String wsdlUrl, boolean useTracker);

	public void configureMarketplace(String wsdlUrl, boolean useTracker);

	public void performTests ();
}
