/*
Copyright (c) 2014, Bernard Butler and Barry Mulcahy (Waterford Institute of Technology, Ireland), Project: FP7-ICT-257930 Aniketos
Copyright (c) 2014, David Lamb and Bo Zhou (Liverpool John Moores University, UK), Project: FP7-ICT-257930 Aniketos
Copyright (c) 2014, Kostas Giannakakis (ATC, Greece), Project: FP7-ICT-257930 Aniketos

All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

 -  Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 -  Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 -  Neither the name of WATERFORD INSTITUTE OF TECHNOLOGY nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
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
