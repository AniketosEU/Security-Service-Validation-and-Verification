package eu.aniketos.spdm.ds.impl;

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
    private String activitiFile;
    
    /**
	 * The composition plan ID.
	 */
    private String compositionPlanID;

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
		// TODO Auto-generated method stub
		return this.activitiFile;
	}

	@Override
	public void setActivitiFile(String activiti) {
		// TODO Auto-generated method stub
		this.activitiFile = activiti;
	}

	@Override
	public void setBPMNXML(String bpmn) {
		// TODO Auto-generated method stub
		this.bpmnXML = bpmn;
	}

}
