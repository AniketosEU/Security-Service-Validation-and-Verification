/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.designer.export.bpmn20.export;

import javax.xml.stream.XMLStreamWriter;

import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.IOParameter;
import org.eclipse.emf.ecore.EObject;


/**
 * @author Tijs Rademakers
 */
public class CallActivityExport implements ActivitiNamespaceConstants {

  public static void createCallActivity(EObject object, XMLStreamWriter xtw) throws Exception {
    CallActivity callActivity = (CallActivity) object;
    
    // start CallActivity element
    xtw.writeStartElement("callActivity");
    xtw.writeAttribute("id", callActivity.getId());
    if (callActivity.getName() != null) {
      xtw.writeAttribute("name", callActivity.getName());
    }
    
    DefaultFlowExport.createDefaultFlow(object, xtw);
    AsyncActivityExport.createDefaultFlow(object, xtw);

    if(callActivity.getCalledElement() != null && callActivity.getCalledElement().length() > 0) {
      xtw.writeAttribute("calledElement", callActivity.getCalledElement());
    }
    
    if(callActivity.getActivitiListeners().size() > 0 || 
    		callActivity.getInParameters().size() > 0 || 
    		callActivity.getOutParameters().size() > 0) {
    	
      xtw.writeStartElement("extensionElements");
    }
    
    ExtensionListenerExport.createExtensionListenerXML(callActivity.getActivitiListeners(), false, EXECUTION_LISTENER, xtw);
    
    if(callActivity.getInParameters().size() > 0 || callActivity.getOutParameters().size() > 0) {
      
      for(IOParameter parameter : callActivity.getInParameters()) {
        writeParameter(parameter, "in", xtw);
      }
      
      for(IOParameter parameter : callActivity.getOutParameters()) {
        writeParameter(parameter, "out", xtw);
      }
    }
    
    if(callActivity.getActivitiListeners().size() > 0 || 
    		callActivity.getInParameters().size() > 0 || 
    		callActivity.getOutParameters().size() > 0) {
    	
      xtw.writeEndElement();
    }
    
    MultiInstanceExport.createMultiInstance(object, xtw);

    // end CallActivity element
    xtw.writeEndElement();
    
    if(callActivity.getBoundaryEventRefs().size() > 0) {
    	for(BoundaryEvent event : callActivity.getBoundaryEventRefs()) {
    		BoundaryEventExport.createBoundaryEvent(event, xtw);
    	}
    }
  }
  
  private static void writeParameter(IOParameter parameter, String name, XMLStreamWriter xtw) throws Exception {
    xtw.writeStartElement(ACTIVITI_EXTENSIONS_PREFIX, name, ACTIVITI_EXTENSIONS_NAMESPACE);
    if(parameter.getSource().contains("${") == true) {
      xtw.writeAttribute("sourceExpression", parameter.getSource());
    } else {
      xtw.writeAttribute("source", parameter.getSource());
    }
    if(parameter.getTarget().contains("${") == true) {
      xtw.writeAttribute("targetExpression", parameter.getTarget());
    } else {
      xtw.writeAttribute("target", parameter.getTarget());
    }
    xtw.writeEndElement();
  }
}
