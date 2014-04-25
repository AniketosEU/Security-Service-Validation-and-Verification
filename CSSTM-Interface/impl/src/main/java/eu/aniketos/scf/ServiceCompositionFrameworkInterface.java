/**
Copyright (c) 2014, Francesco Malmignati, Selex Elsag
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of SEARCH-LAB Ltd. nor the names of its contributors 
      may be used to endorse or promote products derived from this software 
      without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL SEARCH-LAB LTD. BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
**/
package eu.aniketos.scf;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import eu.aniketos.data.IAgreementTemplate;
import eu.aniketos.data.ICompositionPlan;
import eu.aniketos.data.IConsumerPolicy;
import eu.aniketos.sre.DeployResult;

/**
 * @author Francesco Malmignati, Selex Elsag
 *
 */
public interface ServiceCompositionFrameworkInterface {
	
	/**
	 * @param populatedCompositionPlan
	 * 				the compositionPlan to be validated
	 * @return true if service is valid, false otherwise
	 */
	public boolean validateService(ICompositionPlan populatedCompositionPlan);
	
	/**
	 * @param serviceQuery
	 * 				The query of the service
	 * @return A set of services comply with the service query
	 */
	public Set<Service> discoverServices(ServiceQuery serviceQuery, String addressMarketplace);
	
	/**
	 * @param serviceSpecification
	 * 				The BPMN service specification
	 * @param servicesLocations
	 * 				A Map containing the serviceTask id as key and a set of services as value
	 * @return The list containing all the created composition plans
	 */
	public List<ICompositionPlan> createCompositionPlans(ICompositionPlan serviceSpecification, Map<String,Set<Service>> mapTaskServices);
	
	/**
	 * @param compositionPlan
	 * 				The compositionPlan to be deployed
	 * @return true if the service has been deployed, false otherwise
	 */
	public boolean deployService(ICompositionPlan compositionPlan, String applicationServerAddress, String activitiEngineAddress, String usernameActivitiEngine, String passwordActivitiEngine, String usernameTomcat, String passwordTomcat);

	/**
	 * @param compositionPlan
	 * 				The compositionPlan from which take the agreement templates
	 * @return A List of Agreement Template
	 */
	public List<IAgreementTemplate> getAgreementTemplates(ICompositionPlan compositionPlan);
	
	public boolean uploadCompositionPlan(String compositionPlanPath, String activitiEngineAddress, String ActivitiEngineUsername, String ActivitiEnginePassword);
	
	public List<String> checkBPMNSpecificationWithSRCM(ICompositionPlan serviceSpecification, String srs, String mapping, String srcmAddress);
	
	public List<String> getRecommendationsFromTRRM(ICompositionPlan serviceSpecification, String addressTRRM);
	
	public List<String> requestCountermeasuresFromTRRM(String threatId, String addressTRRM);
	
	public String deployService(String compositionId, String compositionName, String sreAddress);
	
	public boolean uploadCompositionPlan(ICompositionPlan compositionPlan, String sreAddress);
	
	public DeployResult deployService(ICompositionPlan compositionPlan, IConsumerPolicy consumerPolicy, IAgreementTemplate agreementTemplate, String sreAddress);
}
