package eu.aniketos.data;

/**
 * Dummy placeholder interface describing a composition plan.
 * 
 * At this stage, composition plans are considered to be almost synonymous with
 * BPMN process specifications; however this will be updated as WP3/WP5(?) work
 * defines requirements
 * 
 * @author David Lamb, LJMU
 * @revised by Bernard Butler & Barry Mulcahy TSSG Aug 2011, 
 * 			   Kostas Giannakakis ATC May 2013
 * 
 */
public interface ICompositionPlan
{
	/**
	 * Gets the composition plan id
	 * @return the composition plan id
	 */
	String getCompositionPlanID();

	/**
	 * Sets the composition plan id
	 * @param compositionPlanID The composition plan id
	 */
	void setCompositionPlanID(String compositionPlanID);

	/**
	 * Sets the BPMN XML String describing this composition plan 
	 * @param xml the bpmn xml
	 */
	void setBPMNXML(String xml);
	
    /**
     * Gets the BPMN XML String describing this composition plan
     * 
     * @return the BPMN description as an XML String
     */
    String getBPMNXML();
    
    /**
     * Gets the activiti file
     * @return the activiti file
     */
    String getActivitiFile();
    
    /**
     * Sets the activiti file
     * @param file the activiti file
     */
    void setActivitiFile(String file);
    
}
