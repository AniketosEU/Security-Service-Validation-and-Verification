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

package org.activiti.designer.features;

import org.eclipse.bpmn2.SubProcess;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.impl.DefaultResizeShapeFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Shape;


/**
 * @author Tijs Rademakers
 */
public class SubProcessResizeFeature extends DefaultResizeShapeFeature {
  
  public SubProcessResizeFeature(IFeatureProvider fp) {
    super(fp);
  }

  @Override
  public boolean canResizeShape(IResizeShapeContext context) {
      boolean canResize = super.canResizeShape(context);

      // perform further check only if move allowed by default feature
      if (canResize == true) {
          // don't allow resize if the class name has the length of 1
          Shape shape = context.getShape();
          Object bo = getBusinessObjectForPictogramElement(shape);
          if (bo instanceof SubProcess) {
            canResize = true;
          } else {
            canResize = false;
          }
      }
      return canResize;
  }

  @Override
  public void resizeShape(IResizeShapeContext context) {
    super.resizeShape(context);
    context.getShape().getGraphicsAlgorithm().setHeight(context.getHeight());
    context.getShape().getGraphicsAlgorithm().setWidth(context.getWidth());
    for (GraphicsAlgorithm graphicsAlgorithm : context.getShape().getGraphicsAlgorithm().getGraphicsAlgorithmChildren()) {
      graphicsAlgorithm.setHeight(context.getHeight());
      graphicsAlgorithm.setWidth(context.getWidth());
    }
  }

}
