/**
 * Copyright 2012  Bo Zhou <B.Zhou@ljmu.ac.uk>
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

package eu.aniketos.scpm;

import java.util.List;


import eu.aniketos.data.IAgreementTemplate;
import eu.aniketos.data.ICompositionPlan;
import eu.aniketos.data.IConsumerPolicy;
import eu.aniketos.scpm.data.OrderCriteria;

/**
 * The interface that our Secure Composition Planners will implement, based on
 * the WP5 draft
 * 
 * @author David Lamb, Bo Zhou, LJMU
 * 
 */
public interface ICompositionPlanner
{
     
	/** Select those composition plans from the specified list that adhere to the specified consumer policy.
	 * @param functionalCompositions A set of alternative service composition plans that are FUNCTIONALLY correct.
	 * @param consumerPolicies Specifications of the consumer policies on each atomic service that composed the composition plans.
	 * @param agreementTemplates Corresponding agreement templates for each composition plan.
	 * @return A set containing service compositions (a subset of functionalCompositions) that are functionally correct and consistent with the consumer�s security policy.
	 * 
	 */
	public List<ISelectResult> selectSecureCompositions(List<ICompositionPlan> functionalCompositions, 
    	    List<IConsumerPolicy> consumerPolicies, List<IAgreementTemplate> agreementTemplates);
    

	/** Select those compositions from the specified list that adhere to the specified consumer policy, 
	 * and orders those that do according to the specified Ranking Criteria.
	 * @param securedCompositions A set of alternative service composition plans that are FUNCTIONALLY correct.
	 * @param consumerPolicies Specifications of the consumer policies on each atomic service that composed the composition plans.
	 * @param agreementTemplates Corresponding agreement templates for each composition plan.
	 * @param order An indication of how the resulting set should be ordered.
	 * @return An ordered set of service compositions (a subset of functionalCompositions) that are functionally correct and consistent with the consumer�s security policy, 
	 * and ordered according to the specified order criteria.
	 * 
	 */
    public List<ICompositionPlan> orderSecureCompositions(List<ICompositionPlan> securedCompositions, 
    	    List<IConsumerPolicy> consumerPolicies, List<IAgreementTemplate> agreementTemplates, OrderCriteria order);

    /** Generate a set of new composition plans as suggestions that adhere to the specified consumer policy.
     * This method yet to be implemented thus subject to change.
	 * @param consumerPolicy A specification of the consumer�s security policy.
	 * @param compositionPlan A service composition that is FUNCTIONALLY correct.
	 * @return A set (possibly empty) containing new service compositions that are consistent with the consumer�s security policy. 
	 * This may include service compositions that differ from the input composition in terms of security properties (and potentially functional properties too).
	 * 
	 */


    public ICompositionPlan reconfiguration(
    		ICompositionPlan backupBPMNDiagram, IAgreementTemplate agreementTemplate, IConsumerPolicy consumerPolicy);


    /** Assume the servceiId is in the serviceTask field, e.g. 
     * <serviceTask activiti:class="org.aniketos.runtime.AniketosClientDelegation" id="servicetask1" name="map service">
     * 
     * @param backupBPMNDiagram
     * @param serviceIdToBeReplaced
     * @param agreementTemplace
     * @return
     */
	public ICompositionPlan recomposition(ICompositionPlan backupBPMNDiagram,
			String serviceTaskIdToBeReplaced, IAgreementTemplate agreementTemplate, IConsumerPolicy consumerPolicy);

    


}
