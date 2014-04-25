/*package eu.aniketos.csstm.impl;

import eu.aniketos.data.ICompositionPlan;

/** Dummy CompositionPlan implementation for testing.
 * 
 * @author balazs
 *
 */
/*public class CompositionPlan implements ICompositionPlan {

	@Override
	public String getCompositionPlanID() {
		// TODO Auto-generated method stub
		return "https://advertisersapi.doubleclick.com/v1.19/api/dfa-api/report?wsdl";
	}

	@Override
	public void setCompositionPlanID(String compositionPlanID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBPMNXML(String xml) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getBPMNXML() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
				"<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:aniketos=\"http://aniketos.eu\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/test\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\">"+
				"  <process id=\"compositionPlan1\" name=\"compositionPlan1\">"+
				"    <documentation>Place documentation for the 'MyProcess' process here.</documentation>"+
				"    <startEvent id=\"startevent1\" name=\"Start\" />"+
				"    <serviceTask activiti:class=\"org.aniketos.runtime.AniketosClientDelegation\" id=\"infotast\" name=\"task info\">"+
				"      <extensionElements>"+
				"        <activiti:field name=\"type\">"+
				"          <activiti:string>info</activiti:string>"+
				"        </activiti:field>"+
				"        <activiti:field name=\"inputType\">"+
				"          <activiti:string>String_1 (type: string)</activiti:string>"+
				"        </activiti:field>"+
				"        <activiti:field name=\"outputType\">"+
				"          <activiti:string>result (type: string)</activiti:string>"+
				"        </activiti:field>"+
				"        <activiti:field name=\"operation\">"+
				"          <activiti:string>requestprice</activiti:string>"+
				"        </activiti:field>"+
				"        <activiti:field name=\"id\">"+
				"          <activiti:string>http://83.235.133.36/AniketosWS/LotInfoServiceSoap12HttpPort?wsdl</activiti:string>"+
				"        </activiti:field>"+
				"        <activiti:field name=\"location\">"+
				"          <activiti:string>http://83.235.133.36/AniketosWS/LotInfoServiceSoap12HttpPort?wsdl</activiti:string>"+
				"        </activiti:field>"+
				"        <activiti:field name=\"serviceProvider\">"+
				"          <activiti:string>daem</activiti:string>"+
				"        </activiti:field>"+
				"      </extensionElements>"+
				"    </serviceTask>"+
				"    <serviceTask activiti:class=\"org.aniketos.runtime.AniketosClientDelegation\" id=\"maptask\" name=\"taskmap\">"+
				"      <extensionElements>"+
				"        <activiti:field name=\"type\">"+
				"          <activiti:string>map</activiti:string>"+
				"        </activiti:field>"+
				"        <activiti:field name=\"inputType\">"+
				"          <activiti:string>longitude (type: string), latitude (type: string)</activiti:string>"+
				"        </activiti:field>"+
				"        <activiti:field name=\"outputType\">"+
				"          <activiti:string>result (type: string)</activiti:string>"+
				"        </activiti:field>"+
				"        <activiti:field name=\"operation\">"+
				"          <activiti:string>getMap</activiti:string>"+
				"        </activiti:field>"+
				"        <activiti:field name=\"id\">"+
				"          <activiti:string>http://83.235.133.36/AniketosWS/MapsServiceSoap12HttpPort?wsdl</activiti:string>"+
				"        </activiti:field>"+
				"        <activiti:field name=\"location\">"+
				"          <activiti:string>http://83.235.133.36/AniketosWS/MapsServiceSoap12HttpPort?wsdl</activiti:string>"+
				"        </activiti:field>"+
				"        <activiti:field name=\"serviceProvider\">"+
				"          <activiti:string>daem</activiti:string>"+
				"        </activiti:field>"+
				"      </extensionElements>"+
				"    </serviceTask>"+
				"    <endEvent id=\"endevent1\" name=\"End\" />"+
				"    <sequenceFlow id=\"flow1\" name=\"\" sourceRef=\"startevent1\" targetRef=\"infotast\" />"+
				"    <sequenceFlow id=\"flow2\" name=\"\" sourceRef=\"infotast\" targetRef=\"maptask\" />"+
				"    <sequenceFlow id=\"flow3\" name=\"\" sourceRef=\"maptask\" targetRef=\"endevent1\" />"+
				"  </process>"+
				"  <bpmndi:BPMNDiagram id=\"BPMNDiagram_MyProcess\">"+
				"    <bpmndi:BPMNPlane bpmnElement=\"compositionPlan1\" id=\"BPMNPlane_MyProcess\">"+
				"      <bpmndi:BPMNShape bpmnElement=\"startevent1\" id=\"BPMNShape_startevent1\">"+
				"        <omgdc:Bounds height=\"35\" width=\"35\" x=\"560\" y=\"190\" />"+
				"      </bpmndi:BPMNShape>"+
				"      <bpmndi:BPMNShape bpmnElement=\"infotast\" id=\"BPMNShape_infotast\">"+
				"        <omgdc:Bounds height=\"55\" width=\"105\" x=\"660\" y=\"220\" />"+
				"      </bpmndi:BPMNShape>"+
				"      <bpmndi:BPMNShape bpmnElement=\"maptask\" id=\"BPMNShape_maptask\">"+
				"        <omgdc:Bounds height=\"55\" width=\"105\" x=\"680\" y=\"330\" />"+
				"      </bpmndi:BPMNShape>"+
				"      <bpmndi:BPMNShape bpmnElement=\"endevent1\" id=\"BPMNShape_endevent1\">"+
				"        <omgdc:Bounds height=\"35\" width=\"35\" x=\"580\" y=\"330\" />"+
				"      </bpmndi:BPMNShape>"+
				"      <bpmndi:BPMNEdge bpmnElement=\"flow1\" id=\"BPMNEdge_flow1\">"+
				"        <omgdi:waypoint x=\"595\" y=\"207\" />"+
				"        <omgdi:waypoint x=\"712\" y=\"220\" />"+
				"      </bpmndi:BPMNEdge>"+
				"      <bpmndi:BPMNEdge bpmnElement=\"flow2\" id=\"BPMNEdge_flow2\">"+
				"        <omgdi:waypoint x=\"712\" y=\"275\" />"+
				"        <omgdi:waypoint x=\"732\" y=\"330\" />"+
				"      </bpmndi:BPMNEdge>"+
				"      <bpmndi:BPMNEdge bpmnElement=\"flow3\" id=\"BPMNEdge_flow3\">"+
				"        <omgdi:waypoint x=\"680\" y=\"357\" />"+
				"        <omgdi:waypoint x=\"615\" y=\"347\" />"+
				"      </bpmndi:BPMNEdge>"+
				"    </bpmndi:BPMNPlane>"+
				"  </bpmndi:BPMNDiagram>"+
				"</definitions>";
	}

	@Override
	public String getActivitiFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setActivitiFile(String file) {
		// TODO Auto-generated method stub

	}

}*/
