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

package org.activiti.designer.eclipse.bpmn;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.ActivitiListener;
import org.eclipse.bpmn2.FormalExpression;


/**
 * @author Tijs Rademakers
 */
public class SequenceFlowModel {
  
  public String id;
  public String sourceRef;
  public String targetRef;
  public FormalExpression conditionExpression;
  public List<ActivitiListener> listenerList = new ArrayList<ActivitiListener>();
}
