package org.activiti.designer.features;

import org.activiti.designer.ActivitiImageProvider;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.mm.pictograms.Diagram;

public class CreateEndEventFeature extends AbstractCreateFastBPMNFeature {
	
	public static final String FEATURE_ID_KEY = "endevent";

	public CreateEndEventFeature(IFeatureProvider fp) {
		// set name and description of the creation feature
		super(fp, "EndEvent", "Add end event");
	}

	public boolean canCreate(ICreateContext context) {
	  Object parentObject = getBusinessObjectForPictogramElement(context.getTargetContainer());
    return (context.getTargetContainer() instanceof Diagram || parentObject instanceof SubProcess);
	}

	public Object[] create(ICreateContext context) {
		EndEvent endEvent = Bpmn2Factory.eINSTANCE.createEndEvent();
		
		endEvent.setId(getNextId());
		endEvent.setName("End");
		
		Object parentObject = getBusinessObjectForPictogramElement(context.getTargetContainer());
    if (parentObject instanceof SubProcess) {
      ((SubProcess) parentObject).getFlowElements().add(endEvent);
    } else {
      getDiagram().eResource().getContents().add(endEvent);
    }

    addGraphicalContent(endEvent, context);
		
		// return newly created business object(s)
		return new Object[] { endEvent };
	}
	
	@Override
	public String getCreateImageId() {
		return ActivitiImageProvider.IMG_ENDEVENT_NONE;
	}

	@Override
	protected String getFeatureIdKey() {
		return FEATURE_ID_KEY;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getFeatureClass() {
		return Bpmn2Factory.eINSTANCE.createEndEvent().getClass();
	}

}
