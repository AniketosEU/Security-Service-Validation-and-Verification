/**
 * Copyright 2014 Liverpool John Moores University <http://www.ljmu.ac.uk/cmp/>
 * Aniketos Project FP7-ICT-257930 <http://www.aniketos.eu>
 * David Lamb <D.J.Lamb@ljmu.ac.uk>
 * Bo Zhou <B.Zhou@ljmu.ac.uk>
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

package eu.aniketos.ncvm.userinterface.views;

import eu.aniketos.data.ICompositionPlan;

/**
 * A dummy implementation of Composition Plan that just wraps a BPMN XML String.
 * Located in the WP3 package as it is likely to be replaced or at best,
 * superseded.
 * 
 * @author David Lamb, Bo Zhou, LJMU
 */
public class CompositionPlan implements ICompositionPlan
{

	/**
	 * The XML content of a composition plan.
	 */
    private String bpmnXML;
    
    /**
	 * The composition plan ID.
	 */
    private String compositionPlanID;
    
    private String activitiFile;

    /** Constructor.
	 *
	 * @param bpmnXML The XML content to be saved in the composition plan.
	 * 
	 */
    public CompositionPlan(String bpmnXML)
    {
    	this.bpmnXML = bpmnXML;
    }

    /** Get the XML content of a composition plan. 
	 *
	 * @return The content of a composition plan in XML format.
	 * 
	 */
    @Override
    public String getBPMNXML()
    {
	
    	return bpmnXML;
    }

	/** Get a composition plan ID.
	 *
	 * @return The ID of the composition plan.
	 * 
	 */
    @Override
	public String getCompositionPlanID() {
		// TODO Auto-generated method stub
		return compositionPlanID;
	}

	/** Set the ID for a composition plan.
	 *
	 * @param compositionPlanID ID of the composition plan to be set.
	 * 
	 */
    @Override
	public void setCompositionPlanID(String compositionPlanID) {
		this.compositionPlanID = compositionPlanID;
		
	}

	@Override
	public String getActivitiFile() {
		return activitiFile;
	}

	@Override
	public void setActivitiFile(String activitiFile) {
		this.activitiFile = activitiFile;
		
	}

	@Override
	public void setBPMNXML(String xml) {
    	this.bpmnXML = xml;
	}

}
