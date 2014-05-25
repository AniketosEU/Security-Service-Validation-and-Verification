/**
 * Copyright 2012  David Llewellyn-Jones <D.Llewellyn-Jones@ljmu.ac.uk>
 * Liverpool John Moores University <http://www.ljmu.ac.uk/cmp/>
 * Aniketos Project <http://www.aniketos.eu>
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
	 * the policy holds for the service. The method acts on the given
	 * service plan.
	 * @param service the BPMN service composition definition to check the policy against
	 * @param policy the ConSpec security policy to check against the service
	 * @return a value representing the result of the verification checks
	 */
	public IVerificationResult verifyProperty(ICompositionPlan service, IConsumerPolicy policy);

	/**
	 * Performs recursive decomposition of a service and invokes either the
	 * CSVM, PVM or SPDM depending on the result in order to verify whether
	 * the policy holds for the service. The method acts on a service that
	 * has already been deployed to the marketplace and therefore no service
	 * plan is required.
	 * @param serviceID the ID of the service as stored in the marketplace to check the policy against
	 * @param policy the ConSpec security policy to check against the service
	 * @return a value representing the result of the verification checks
	 */
	public IVerificationResult verifyPropertyDeployed(String serviceID, IConsumerPolicy policy);

	/**
	 * Configure the address and access method of the feedback service to use to generate log/feedback output to
	 * @param wsdlUrl address of the Web Service to send feedback to. Only needed if useTracker is false.
	 * @param useTracker set to true if DOSGi service tracking should be used to access the service, or direct access as a Web Service otherwise
	 */
	public void configureNCVMFeedback(String wsdlUrl, boolean useTracker);

	/**
	 * Configure the address and access method of the CSVM service to use
	 * @param wsdlUrl address of the Web Service to use. Only needed if useTracker is false.
	 * @param useTracker set to true if DOSGi service tracking should be used to access the service, or direct access as a Web Service otherwise
	 */
	public void configureCSVM(String wsdlUrl, boolean useTracker);

	/**
	 * Configure the address and access method of the PVM service to use
	 * @param wsdlUrl address of the Web Service to use. Only needed if useTracker is false.
	 * @param useTracker set to true if DOSGi service tracking should be used to access the service, or direct access as a Web Service otherwise
	 */
	public void configurePVM(String wsdlUrl, boolean useTracker);

	/**
	 * Configure the address and access method of the SPDM service to use
	 * @param wsdlUrl address of the Web Service to use. Only needed if useTracker is false.
	 * @param useTracker set to true if DOSGi service tracking should be used to access the service, or direct access as a Web Service otherwise
	 */
	public void configureSPDM(String wsdlUrl, boolean useTracker);

	/**
	 * Configure the address and access method of the Marketplace service to use
	 * @param wsdlUrl address of the Web Service to use. Only needed if useTracker is false.
	 * @param useTracker set to true if DOSGi service tracking should be used to access the service, or direct access as a Web Service otherwise
	 */
	public void configureMarketplace(String wsdlUrl, boolean useTracker);

	/**
	 * Perform various functionality tests. Each of the services needed for the verification chain
	 * are called to ensure they're available. Test data will also be sent to each of the services
	 * and the return results checked for errors and correctness. Results output are generated as log
	 * output and to the feedback service.
	 */
	public void performTests ();
}
