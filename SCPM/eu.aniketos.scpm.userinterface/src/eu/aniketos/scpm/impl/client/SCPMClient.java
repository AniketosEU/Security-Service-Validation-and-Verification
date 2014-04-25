/**
 * Copyright 2012  Bo Zhou <B.Zhou@ljmu.ac.uk>
 * Liverpool John Moores University <http://www.ljmu.ac.uk/cmp/>
 * Aniketos Project <http://www.aniketos.eu>
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either 
 * version 3 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library. If not, see <http://www.gnu.org/licenses/>.
 *
 */


package eu.aniketos.scpm.impl.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import eu.aniketos.data.IAgreementTemplate;
import eu.aniketos.data.ICompositionPlan;
import eu.aniketos.data.IConsumerPolicy;
import eu.aniketos.scpm.client.ArrayOfICompositionPlan;
import eu.aniketos.scpm.client.ArrayOfIConsumerPolicy;
import eu.aniketos.scpm.client.ArrayOfIAgreementTemplate;
import eu.aniketos.scpm.client.ArrayOfISelectResult;
import eu.aniketos.scpm.client.ArrayOfString;
import eu.aniketos.scpm.client.ICompositionPlanner;
import eu.aniketos.scpm.client.ICompositionPlannerPortType;
import eu.aniketos.scpm.client.ObjectFactory;
import eu.aniketos.scpm.client.String2StringMap.Entry;
import eu.aniketos.scpm.data.impl.CompositionPlan;





public class SCPMClient {
	private final static int timeout = 1 * 60 * 1000; // in milliseconds
	public static eu.aniketos.data.ICompositionPlan Recomposition(eu.aniketos.data.ICompositionPlan cpToRecompose, String taskIDToReplace, eu.aniketos.data.IAgreementTemplate agreementTemplate, eu.aniketos.data.IConsumerPolicy consumerPolicy, String addressSCPM){
		
		QName serviceName = new QName("http://scpm.aniketos.eu/", "ICompositionPlanner");
		URL url = null;
        try {
            URL baseUrl;
            baseUrl = eu.aniketos.scpm.client.ICompositionPlanner.class.getResource(".");
            url = new URL(baseUrl, addressSCPM);
        } catch (MalformedURLException ex) {
        	ex.printStackTrace();
        }
		
				
        ICompositionPlanner scpmService = new ICompositionPlanner(url,serviceName);
        ICompositionPlannerPortType scpmModule = null;
        try {
	        scpmModule = scpmService.getICompositionPlannerPort();

			Map<String, Object> requestContext = ((BindingProvider)scpmModule).getRequestContext();
			requestContext.put("javax.xml.ws.client.receiveTimeout", Integer.toString(timeout));
		} catch (Exception exception) {
			System.out.println("SCPM Client exception: " + exception.getMessage());
		}
		
        eu.aniketos.scpm.client.IAgreementTemplate a = convertIAgreementTemplateToWS(agreementTemplate);
        eu.aniketos.scpm.client.IConsumerPolicy p = convertIConsumerPolicyToWS(consumerPolicy);
        eu.aniketos.scpm.client.ICompositionPlan cp = convertICompositionPlanToWS(cpToRecompose);
        eu.aniketos.scpm.client.ICompositionPlan newPlan = scpmModule.recomposition(cp, taskIDToReplace, a, p);
		
       	return convertICompositionPlanFromWS(newPlan);
		
	}
	
public static eu.aniketos.data.ICompositionPlan Reconfiguration(eu.aniketos.data.ICompositionPlan cpToRecompose, eu.aniketos.data.IAgreementTemplate agreementTemplate, eu.aniketos.data.IConsumerPolicy consumerPolicy, String addressSCPM){
		
		QName serviceName = new QName("http://scpm.aniketos.eu/", "ICompositionPlanner");
		URL url = null;
        try {
            URL baseUrl;
            baseUrl = eu.aniketos.scpm.client.ICompositionPlanner.class.getResource(".");
            url = new URL(baseUrl, addressSCPM);
        } catch (MalformedURLException ex) {
        	ex.printStackTrace();
        }
		
				
        ICompositionPlanner scpmService = new ICompositionPlanner(url,serviceName);
        ICompositionPlannerPortType scpmModule = null;
        try {
	        scpmModule = scpmService.getICompositionPlannerPort();

			Map<String, Object> requestContext = ((BindingProvider)scpmModule).getRequestContext();
			requestContext.put("javax.xml.ws.client.receiveTimeout", Integer.toString(timeout));
		} catch (Exception exception) {
			System.out.println("SCPM Client exception: " + exception.getMessage());
		}
		
        eu.aniketos.scpm.client.IAgreementTemplate a = convertIAgreementTemplateToWS(agreementTemplate);
        eu.aniketos.scpm.client.IConsumerPolicy p = convertIConsumerPolicyToWS(consumerPolicy);
        eu.aniketos.scpm.client.ICompositionPlan cp = convertICompositionPlanToWS(cpToRecompose);
        eu.aniketos.scpm.client.ICompositionPlan newPlan = scpmModule.reconfiguration(cp, a, p);
		
       	return convertICompositionPlanFromWS(newPlan);
		
	}
	
public static List<eu.aniketos.scpm.ISelectResult>	selectSecureCompositions (List<eu.aniketos.data.ICompositionPlan> plans, 
		List<eu.aniketos.data.IConsumerPolicy> policies, List<eu.aniketos.data.IAgreementTemplate> templates, String addressSCPM){
	QName serviceName = new QName("http://scpm.aniketos.eu/", "ICompositionPlanner");
	URL url = null;
    try {
        URL baseUrl;
        baseUrl = eu.aniketos.scpm.client.ICompositionPlanner.class.getResource(".");
        url = new URL(baseUrl, addressSCPM);
    } catch (MalformedURLException ex) {
    	System.out.println("Error with the SCPM address");
    	return null;
    }
	
			
    ICompositionPlanner scpmService = new ICompositionPlanner(url,serviceName);
    ICompositionPlannerPortType scpmModule = null;
    try {
        scpmModule = scpmService.getICompositionPlannerPort();

		Map<String, Object> requestContext = ((BindingProvider)scpmModule).getRequestContext();
		requestContext.put("javax.xml.ws.client.receiveTimeout", Integer.toString(timeout));
	} catch (Exception exception) {
		System.out.println("SCPM Client exception: " + exception.getMessage());
		return null;
	}

	//System.out.println("Testing CMM service");
    ArrayOfICompositionPlan aocp = new ArrayOfICompositionPlan();
    ArrayOfIConsumerPolicy aop = new ArrayOfIConsumerPolicy();
    ArrayOfIAgreementTemplate aoa = new ArrayOfIAgreementTemplate();
    int arrayLength = plans.size();
    for (int i = 0; i< arrayLength; i++)
	{
    	eu.aniketos.scpm.client.ICompositionPlan cp = convertICompositionPlanToWS(plans.get(i));
    	aocp.getICompositionPlan().add(cp);
    	eu.aniketos.scpm.client.IConsumerPolicy p = convertIConsumerPolicyToWS(policies.get(i));
    	aop.getIConsumerPolicy().add(p);
       	eu.aniketos.scpm.client.IAgreementTemplate a = convertIAgreementTemplateToWS(templates.get(i));
       	aoa.getIAgreementTemplate().add(a);
	}
    
    ArrayOfISelectResult selectedPlans = scpmModule.selectSecureCompositions(aocp, aop, aoa);
    List<eu.aniketos.scpm.ISelectResult> sr = new Vector<eu.aniketos.scpm.ISelectResult>();
    arrayLength = selectedPlans.getISelectResult().size();
    for (int i = 0; i< arrayLength; i++)
	{
    	sr.add(convertISelectResultFromWS(selectedPlans.getISelectResult().get(i)));
    	//System.out.println(sr.get(i).getPlan().getCompositionPlanID());
	}
	
   	return sr;
}

public static List<ICompositionPlan> orderSecureCompositions(List<eu.aniketos.data.ICompositionPlan> plans,
		List<eu.aniketos.data.IConsumerPolicy> policies, List<eu.aniketos.data.IAgreementTemplate> templates, 
		eu.aniketos.scpm.data.OrderCriteria criteria, String addressSCPM){
	
	QName serviceName = new QName("http://scpm.aniketos.eu/", "ICompositionPlanner");
	URL url = null;
    try {
        URL baseUrl;
        baseUrl = eu.aniketos.scpm.client.ICompositionPlanner.class.getResource(".");
        url = new URL(baseUrl, addressSCPM);
    } catch (MalformedURLException ex) {
    	System.out.println("Error with the SCPM address");
    	return null;
    }
	
			
    ICompositionPlanner scpmService = new ICompositionPlanner(url,serviceName);
    ICompositionPlannerPortType scpmModule = null;
    try {
        scpmModule = scpmService.getICompositionPlannerPort();

		Map<String, Object> requestContext = ((BindingProvider)scpmModule).getRequestContext();
		requestContext.put("javax.xml.ws.client.receiveTimeout", Integer.toString(timeout));
	} catch (Exception exception) {
		System.out.println("SCPM Client exception: " + exception.getMessage());
		return null;
	}

	
    ArrayOfICompositionPlan aocp = new ArrayOfICompositionPlan();
    ArrayOfIConsumerPolicy aop = new ArrayOfIConsumerPolicy();
    ArrayOfIAgreementTemplate aoa = new ArrayOfIAgreementTemplate();
    int arrayLength = plans.size();
    for (int i = 0; i< arrayLength; i++)
	{
    	eu.aniketos.scpm.client.ICompositionPlan cp = convertICompositionPlanToWS(plans.get(i));
    	aocp.getICompositionPlan().add(cp);
    	eu.aniketos.scpm.client.IConsumerPolicy p = convertIConsumerPolicyToWS(policies.get(i));
    	aop.getIConsumerPolicy().add(p);
       	eu.aniketos.scpm.client.IAgreementTemplate a = convertIAgreementTemplateToWS(templates.get(i));
       	aoa.getIAgreementTemplate().add(a);
	}
    eu.aniketos.scpm.client.OrderCriteria oc = convertOrderCriteriaToWS (criteria);
    
    ArrayOfICompositionPlan orderedPlans = scpmModule.orderSecureCompositions(aocp, aop, aoa, oc);
    List<eu.aniketos.data.ICompositionPlan> result = new Vector<eu.aniketos.data.ICompositionPlan>();
    arrayLength = orderedPlans.getICompositionPlan().size();
    for (int i = 0; i< arrayLength; i++)
	{
    	result.add(convertICompositionPlanFromWS(orderedPlans.getICompositionPlan().get(i)));
    	//System.out.println(result.get(i).getCompositionPlanID());
	}
	
	return result;
	
}

private static eu.aniketos.scpm.client.OrderCriteria convertOrderCriteriaToWS(eu.aniketos.scpm.data.OrderCriteria criteria) {
	ObjectFactory factory = new ObjectFactory();
	eu.aniketos.scpm.client.OrderCriteria result = new eu.aniketos.scpm.client.OrderCriteria();
	
	eu.aniketos.scpm.client.String2StringMap s2s = new eu.aniketos.scpm.client.String2StringMap();
		
	Entry e = new Entry();
	e = createEntry (criteria, "Trustworthiness");
	s2s.getEntry().add(e);
	e = createEntry (criteria, "Credibility");
	s2s.getEntry().add(e);
	e = createEntry (criteria, "Validness");
	s2s.getEntry().add(e);
	result.setCriteria(factory.createOrderCriteriaCriteria(s2s));
		
	return result;
}

private static Entry createEntry(eu.aniketos.scpm.data.OrderCriteria oc, String key){
	String value = oc.getCriterion(key);
	Entry e = new Entry();
	e.setKey(key);
	e.setValue(value);
	return e;
}

private static eu.aniketos.scpm.client.IAgreementTemplate convertIAgreementTemplateToWS(IAgreementTemplate template) {
	ObjectFactory factory = new ObjectFactory();
	eu.aniketos.scpm.client.IAgreementTemplate result = new eu.aniketos.scpm.client.IAgreementTemplate();
		
	result.setAgreementTemplateID(factory.createIAgreementTemplateAgreementTemplateID(template.getAgreementTemplateID()));
	result.setXML(factory.createIAgreementTemplateXML(template.getXML()));
	ArrayOfString aos = new ArrayOfString();
	int arrayLength = template.getXmlContents().length;
	for (int i = 0; i< arrayLength; i++)
	{
		//System.out.println(template.getXmlContents()[i]);
		aos.getString().add(template.getXmlContents()[i]);
	}
	
	aos.getString().subList(0, 0);
	
	result.setXmlContents(factory.createIAgreementTemplateXmlContents(aos));
	
	return result;
}

private static eu.aniketos.scpm.client.IConsumerPolicy convertIConsumerPolicyToWS(IConsumerPolicy policy) {
	ObjectFactory factory = new ObjectFactory();
	eu.aniketos.scpm.client.IConsumerPolicy result = new eu.aniketos.scpm.client.IConsumerPolicy();
		
	result.setXML(factory.createIConsumerPolicyXML(policy.getXML()));
	
	ArrayOfString aos = new ArrayOfString();
	int arrayLength = policy.getXmlContents().length;
	for (int i = 0; i< arrayLength; i++)
	{
		//System.out.println(policy.getXmlContents()[i]);
		aos.getString().add(policy.getXmlContents()[i]);
	}
	aos.getString().subList(0, 0);
	result.setXmlContents(factory.createIConsumerPolicyXmlContents(aos));
	
	return result;
}

private static  eu.aniketos.scpm.client.ICompositionPlan convertICompositionPlanToWS (ICompositionPlan plan){
	ObjectFactory factory = new ObjectFactory();
	eu.aniketos.scpm.client.ICompositionPlan result = new eu.aniketos.scpm.client.ICompositionPlan();
	
	result.setCompositionPlanID(factory.createICompositionPlanCompositionPlanID(plan.getCompositionPlanID()));
	result.setBPMNXML(factory.createICompositionPlanBPMNXML(plan.getBPMNXML()));
	result.setActivitiFile(factory.createICompositionPlanActivitiFile(plan.getActivitiFile()));
	
	return result;
}

private static ICompositionPlan convertICompositionPlanFromWS (eu.aniketos.scpm.client.ICompositionPlan plan){
	
	ICompositionPlan result = new CompositionPlan(plan.getBPMNXML().getValue());
	result.setCompositionPlanID(plan.getCompositionPlanID().getValue());
	result.setActivitiFile(plan.getActivitiFile().getValue());
	return result;
}
private static eu.aniketos.scpm.ISelectResult convertISelectResultFromWS (eu.aniketos.scpm.client.ISelectResult sr){
	
	
	eu.aniketos.scpm.ISelectResult result = new eu.aniketos.scpm.SelectResult();
	result.setExplanation(sr.getExplanation().getValue());
	result.setResult(sr.getResult());
	result.setPlan(convertICompositionPlanFromWS(sr.getPlan().getValue()));
	return result;
}

}
