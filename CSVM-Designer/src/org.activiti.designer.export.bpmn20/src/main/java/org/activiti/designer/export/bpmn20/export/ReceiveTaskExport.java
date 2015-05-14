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
import org.eclipse.bpmn2.ReceiveTask;
import org.eclipse.emf.ecore.EObject;


/**
 * @author Tijs Rademakers
 */
public class ReceiveTaskExport implements ActivitiNamespaceConstants {

  public static void createReceiveTask(EObject object, XMLStreamWriter xtw) throws Exception {
    ReceiveTask receiveTask = (ReceiveTask) object;
    // start ReceiveTask element
    xtw.writeStartElement("receiveTask");
    xtw.writeAttribute("id", receiveTask.getId());
    if (receiveTask.getName() != null) {
      xtw.writeAttribute("name", receiveTask.getName());
    }
    DefaultFlowExport.createDefaultFlow(object, xtw);
    AsyncActivityExport.createDefaultFlow(object, xtw);
    ExtensionListenerExport.createExtensionListenerXML(receiveTask.getActivitiListeners(), true, EXECUTION_LISTENER, xtw);
    
    MultiInstanceExport.createMultiInstance(object, xtw);

    // end ReceiveTask element
    xtw.writeEndElement();
    
    if(receiveTask.getBoundaryEventRefs().size() > 0) {
    	for(BoundaryEvent event : receiveTask.getBoundaryEventRefs()) {
    		BoundaryEventExport.createBoundaryEvent(event, xtw);
    	}
    }
  }
}
