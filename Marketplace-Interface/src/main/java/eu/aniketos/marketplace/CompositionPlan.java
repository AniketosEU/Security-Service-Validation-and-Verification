/**
 * Copyright 2014 Athens Technology Centre SA <http://www.atc.gr/>
 * Aniketos Project FP7-ICT-257930 <http://www.aniketos.eu>
 * Konstantinos Giannakakis <k.giannakakis@atc.gr>
 * Vasilis Tountopoulos <v.tountopoulos@atc.gr>
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

package eu.aniketos.marketplace;

import eu.aniketos.data.ICompositionPlan;

/**
 * A basic implementation of ICompositionPlan
 * @author Kostas Giannakakis
 *
 */
public class CompositionPlan implements ICompositionPlan {

	private String activitiFile;
	
	private String compositionPlanId;
	
	private String bpmn;

	/**
	 * Returns the activiti file
	 * @return The activiti file
	 */
	@Override
	public String getActivitiFile() {
		return activitiFile;
	}

	/**
	 * Returns the BPMN
	 * @return The BPMN
	 */	
	@Override
	public String getBPMNXML() {
		return bpmn;
	}

	/**
	 * Returns the composition plan ID
	 * @return The composition plan ID	
	 */
	@Override
	public String getCompositionPlanID() {
		return compositionPlanId;
	}

	/**
	 * Sets the activiti file
	 * @param activitiFile The activiti file
	 */
	@Override
	public void setActivitiFile(String activitiFile) {
		this.activitiFile = activitiFile;
	}

	/**
	 * Sets the BPMN process
	 * @param bpmn The BPMN process
	 */
	@Override
	public void setBPMNXML(String bpmn) {
		this.bpmn = bpmn;
	}

	/**
	 * Sets the composition plan ID
	 * @param compositionPlanId The composition plan ID 
	 */
	@Override
	public void setCompositionPlanID(String compositionPlanId) {
		this.compositionPlanId = compositionPlanId;
	}



}
