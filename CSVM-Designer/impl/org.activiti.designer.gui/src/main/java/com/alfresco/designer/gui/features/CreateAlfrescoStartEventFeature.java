package com.alfresco.designer.gui.features;

import org.activiti.designer.util.features.AbstractCreateBPMNFeature;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.mm.pictograms.Diagram;

public class CreateAlfrescoStartEventFeature extends AbstractCreateBPMNFeature {
	
	public static final String FEATURE_ID_KEY = "alfrescoStartevent";

	public CreateAlfrescoStartEventFeature(IFeatureProvider fp) {
		// set name and description of the creation feature
		super(fp, "AlfrescoStartEvent", "Add Alfresco start event");
	}

	public boolean canCreate(ICreateContext context) {
	  Object parentObject = getBusinessObjectForPictogramElement(context.getTargetContainer());
    return (context.getTargetContainer() instanceof Diagram || parentObject instanceof SubProcess);
	}

	public Object[] create(ICreateContext context) {
		StartEvent startEvent = Bpmn2Factory.eINSTANCE.createAlfrescoStartEvent();
		
		startEvent.setId(getNextId());
		startEvent.setName("Alfresco start");
		
		Object parentObject = getBusinessObjectForPictogramElement(context.getTargetContainer());
    if (parentObject instanceof SubProcess) {
      ((SubProcess) parentObject).getFlowElements().add(startEvent);
    } else {
      getDiagram().eResource().getContents().add(startEvent);
    }
    
		addGraphicalRepresentation(context, startEvent);
		
		// return newly created business object(s)
		return new Object[] { startEvent };
	}
	
	@Override
	public String getCreateImageId() {
		return "org.activiti.designer.startevent.none";
	}
	
	@Override
	protected String getFeatureIdKey() {
		return FEATURE_ID_KEY;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getFeatureClass() {
		return Bpmn2Factory.eINSTANCE.createAlfrescoStartEvent().getClass();
	}

}
