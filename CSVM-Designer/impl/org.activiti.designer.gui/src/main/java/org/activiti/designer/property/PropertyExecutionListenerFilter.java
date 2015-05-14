package org.activiti.designer.property;

import org.activiti.designer.property.extension.util.ExtensionUtil;
import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

public class PropertyExecutionListenerFilter extends AbstractPropertySectionFilter {

	@Override
	protected boolean accept(PictogramElement pe) {
		EObject bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
		if (bo instanceof Activity && ExtensionUtil.isCustomServiceTask(bo) == false && (bo instanceof UserTask == false)) {
			return true;
		} else if (bo instanceof SequenceFlow || pe instanceof Diagram) {
		  return true;
		}
		return false;
	}

}
