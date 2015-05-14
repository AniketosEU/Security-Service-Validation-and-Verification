package org.activiti.designer.security.property;

import org.activiti.designer.property.extension.util.ExtensionUtil;
import org.eclipse.bpmn2.AlfrescoUserTask;
import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

/**
 * @author Jan Alexander
 * 
 */
public class PropertyNtkFilter extends AbstractPropertySectionFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter#accept
	 * (org.eclipse.graphiti.mm.pictograms.PictogramElement)
	 */
	@Override
	protected boolean accept(PictogramElement pe) {
		EObject bo = Graphiti.getLinkService()
				.getBusinessObjectForLinkedPictogramElement(pe);
		if ((bo instanceof UserTask && bo instanceof AlfrescoUserTask == false)
				|| (bo instanceof ServiceTask && !ExtensionUtil
						.isCustomServiceTask(bo))) {
			return true;
		}
		return false;
	}

}
