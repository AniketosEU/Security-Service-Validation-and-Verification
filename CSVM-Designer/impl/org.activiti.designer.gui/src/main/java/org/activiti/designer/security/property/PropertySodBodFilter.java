package org.activiti.designer.security.property;






import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;
import org.eclipse.securebpmn2.BindingOfDuty;
import org.eclipse.securebpmn2.SeparationOfDuty;

/**
 * @author Raj Ruparel
 *
 */
public class PropertySodBodFilter extends AbstractPropertySectionFilter {
	
	@Override
	protected boolean accept(PictogramElement pe) {
		EObject bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
		if (bo instanceof SeparationOfDuty || bo instanceof BindingOfDuty ) {
			return true;
		}
		return false;
	}

}

