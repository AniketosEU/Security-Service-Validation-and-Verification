package org.activiti.designer.security.property;



import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.AlfrescoMailTask;
import org.eclipse.bpmn2.AlfrescoScriptBase;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

/**
 * @author Raj Ruparel
 *
 */
public class PropertyRbacFilter extends AbstractPropertySectionFilter {
	
	@Override
	protected boolean accept(PictogramElement pe) {
		EObject bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
		if (bo instanceof Activity && bo instanceof AlfrescoScriptBase == false && bo instanceof AlfrescoMailTask == false ) {
			return true;
		}
		return false;
	}

}

