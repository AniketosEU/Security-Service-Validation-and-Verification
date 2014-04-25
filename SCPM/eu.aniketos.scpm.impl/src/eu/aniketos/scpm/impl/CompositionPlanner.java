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

package eu.aniketos.scpm.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.StringTokenizer;
import java.util.List;




//import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.google.common.collect.Sets;

import eu.aniketos.data.IAgreementTemplate;
import eu.aniketos.data.ICompositionPlan;
import eu.aniketos.data.IConsumerPolicy;
import eu.aniketos.data.ISecurityProperty;
import eu.aniketos.data.SPState;
import eu.aniketos.scpm.ICompositionPlanner;
import eu.aniketos.scpm.ISelectResult;
import eu.aniketos.scpm.SelectResult;
import eu.aniketos.scpm.ServiceQuery;
import eu.aniketos.scpm.data.OrderCriteria;
import eu.aniketos.scpm.data.Service;
import eu.aniketos.scpm.data.impl.CompositionPlan;
import eu.aniketos.scpm.data.impl.ConsumerPolicy;
import eu.aniketos.scpm.impl.client.BPMNParser;
import eu.aniketos.scpm.impl.client.MarketplaceClient;
import eu.aniketos.ncvm.proxy.NCVMProxy;
import eu.aniketos.scpm.impl.client.CMMClient;
import eu.aniketos.scpm.impl.client.FormatHelper;
import eu.aniketos.scpm.impl.client.SPDMClient;
import eu.aniketos.scpm.impl.client.TrustClient;
import eu.aniketos.scpm.impl.client.WebService;
import eu.aniketos.data.impl.Result;
import eu.aniketos.ncvm.INestedCompositionVerification;
import eu.aniketos.ncvm.IVerificationResult;
import eu.aniketos.ncvm.userinterface.proxy.*;



/**
 * Composition Planner - loosely implements planner interface from WP5.
 * Implementation focuses on a demonstration of Eclipse plugins and OSGI bundle
 * communication.
 * 
 * @author Bo Zhou
 */
public class CompositionPlanner implements ICompositionPlanner {
	/**
	 * Dummy marketplace service implemented in Security Property Determination
	 * Module. To use it, the service needs to be registered as Declarative
	 * Service first.
	 */
	
	private INestedCompositionVerification ncvmService;
	private static final Logger logger = Logger
			.getLogger(CompositionPlanner.class);
	private static final String URL_Marketplace = "http://hestia.atc.gr/marketplace2/?wsdl";
	//private static final String URL_TrustPrediction = "http://aniketos.tssg.org/eu/aniketos/trustworthiness/ext/messaging/ITrustworthinessPrediction?wsdl";
	private static final String URL_SPDM = "http://ec2-54-235-118-152.compute-1.amazonaws.com:9091/spdm_service?wsdl";//"http://localhost:9091/spdm_service?wsdl";
	private static final String URL_NCVM = "http://ec2-54-235-118-152.compute-1.amazonaws.com:9093/ncvm?wsdl";
	private static final String URL_CompositeTrustPrediction = "http://aniketos.tssg.org/eu/aniketos/trustworthiness/ext/messaging/ICompositeTrustworthinessPrediction?wsdl";
	private static final String URL_CMM = "http://hestia.atc.gr/contractmanager?wsdl";//
	//private static final String URL_CMM = "http://localhost:9090/contractmanager?wsdl";//
	//private static final String URL_CSVM = "http://localhost:9090/csvm?wsdl";


	
	public INestedCompositionVerification getNCVM() throws Exception {
		INestedCompositionVerification ncvm = null;
		// Create a reference to the nested composition verification service
		// if
		// (eu.aniketos.ncvm.userinterface.Activator.getDefault().getSettings().isNcvmTracker())
		// {
		// ncvm = (INestedCompositionVerification)
		// trackerNCVM.waitForService(1000);
		// }
		// else {
		ncvm = new NCVMProxy();
		((INCVMProxy) ncvm).setURL(URL_NCVM);

		return ncvm;
	}

	@Override
	public List<ISelectResult> selectSecureCompositions(
			List<ICompositionPlan> functionalCompositions,
			List<IConsumerPolicy> consumerPolicies,
			List<IAgreementTemplate> agreementTemplates) {
		
	
		//get NCVM service
		try {
			ncvmService = (INestedCompositionVerification) getNCVM();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}

		if (ncvmService == null) {
			System.out.println("NCVM Service is null");
			// return null;//should return null once NCVM becomes Declarative
			
		} else
			System.out.println("NCVM service is found");

		List<ISelectResult> returnCompositions = new Vector<ISelectResult>();
		
		//List<ContractStatus> results = new Vector<ContractStatus>();

		for (int index = 0; index < functionalCompositions.size(); index++) {
			
			IConsumerPolicy temp = FormatHelper.updatePolicyServiceID(functionalCompositions.get(index), consumerPolicies.get(index));
			
			IConsumerPolicy generalPolicy = FormatHelper.generalisePolicy(temp);
			
			consumerPolicies.get(index).setXmlContents(temp.getXmlContents());
			
			ISelectResult selectResult = new SelectResult();
			selectResult.setPlan(functionalCompositions.get(index));
			String reason ="";

			//Verification with CMM for contract matching
			boolean cmmPass = false;
			boolean trustPass = false;

			try {
				System.out.println("Connecting to CMM");
				URL cmmurl = new URL(URL_CMM);
			} catch (MalformedURLException e) {

				//System.out.println(e.getMessage());
				e.printStackTrace();
			}

			try{
				
				//System.out.println(agreementTemplates.get(index).getXmlContents()[0]);
				//System.out.println(consumerPolicies.get(index).getXmlContents()[0]);
				Result matchingResult = CMMClient.CheckMatching(agreementTemplates.get(index), generalPolicy, URL_CMM);
				System.out.println("CMM returns matching result: "+ matchingResult.getErrorCode());
				System.out.println("The explanation from CMM is: "+ matchingResult.getExplanation());
				if (matchingResult.getErrorCode() == 0)
				{
					cmmPass = true;
					reason += "CMM returns pass with value "+matchingResult.getErrorCode()+"; ";
				}
				else 
				{
					selectResult.setResult(1);
					reason += "CMM returns failiure with value "+ matchingResult.getErrorCode()+"; ";
					selectResult.setExplanation(reason);
				}
				
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				//e.printStackTrace();
				selectResult.setResult(1);
				reason += "CMM returns Error; ";
				selectResult.setExplanation(reason);
			}

			//if (cmmPass)
			{
				// code for getting trust value for single service
				//					URL url = null;
				//					try {
				//						System.out.println("Connecting to Trust Service");
				//						url = new URL(URL_TrustPrediction);
				//					} catch (MalformedURLException e) {
				//
				//						e.printStackTrace();
				//					}
				//
				//					double trustwrothiness = 21.0; 
				//				try{
				//					trustwrothiness = TrustClient.getTrustworthiness(functionalCompositions.get(0), URL_TrustPrediction);
				//					System.out.println("atomic trustworthiness value is: "+ trustwrothiness);
				//				}catch(Exception e)
				//				{
				//					e.printStackTrace();
				//				}

//				double compositeTrust;				
//
//				try {
//					System.out.println("Connecting to Composite Trust Service");
//					URL urlcomp = new URL(URL_CompositeTrustPrediction);
//				} catch (MalformedURLException e) {
//					
//					System.out.println(e.getMessage());
//				}
//
//
//				try {
//					String id = functionalCompositions.get(index).getCompositionPlanID();
//
//					compositeTrust = TrustClient.getCompositeTrustworthiness(functionalCompositions.get(index), URL_CompositeTrustPrediction);
//					System.out.println("Composite trustworthiness value for composite plan " + id +" is: "+ compositeTrust);
//					trustPass = true;//TODO: no threshold set at the moment. set to pass as long as there is a value
//
//				}
//				catch(Exception e)
//				{
//					//System.out.println(e.getMessage());
//					e.printStackTrace();
//					selectResult.setResult(1);
//					reason += "Error while checking the trustworthiness value;";
//					selectResult.setExplanation(reason);
//				}

				//if (trustPass)
				{
					try {


						
						IVerificationResult result =  null;
										
						System.out.println("Validating agreement templates and consumer policies with NCVM...");
						
						IConsumerPolicy policyCombined = new ConsumerPolicy();
						int aLength = agreementTemplates.get(index).getXmlContents().length;
						int cLength = consumerPolicies.get(index).getXmlContents().length;
						String[] xmlContents = new String [aLength+cLength];
						System.arraycopy(agreementTemplates.get(index).getXmlContents(), 0, xmlContents, 0, aLength);
						System.arraycopy(consumerPolicies.get(index).getXmlContents(), 0, xmlContents, aLength, cLength);
						policyCombined.setXmlContents(xmlContents);
						
						//System.out.println(xmlContents.length);
						//System.out.println(xmlContents[0]);
						//System.out.println(xmlContents[1]);
						
						result = ncvmService.verifyProperty(functionalCompositions.get(index), policyCombined);
						System.out.println("SCPM gets validation result of combined conspecs from NCVM: "
							+ result.getResult());
						
						
						selectResult.setResult(result.getErrorValue()); 
								
						if (result.getErrorValue() == 0)
						{
						    selectResult.setResult(result.getResult());
						    if (result.getResult()>0) 
						    {
						        reason += "NCVM returns pass with value " + result.getResult();
						        selectResult.setExplanation (reason);
						    } 
						    else 
						    {
						        reason += "NCVM returns failure";
						        selectResult.setExplanation (reason);
						    }
						} 
						else 
						{
						    reason += "NCVM returns error code "+ result.getErrorValue()+ ": "+result.getErrorExplanation();
						    selectResult.setExplanation(reason);
						}

													
						
					}
					catch (Exception e)
					{
						e.printStackTrace();
						selectResult.setResult(1);
						reason += "Error while verify with NCVM";
						selectResult.setExplanation(reason);
					}
				}
			}
			returnCompositions.add(selectResult);//Now all the compositions will be returned to the user interface. it's user interface job to check the result and show explanation
		}


		return returnCompositions;
	}
	
	
	// consumerPoliy and agreementTemplates not really used in this version,
	// only the first criterion used here.
	@Override
	public List<ICompositionPlan> orderSecureCompositions(
			List<ICompositionPlan> securedCompositions,
			List<IConsumerPolicy> consumerPolicies,
			List<IAgreementTemplate> agreementTemplates, OrderCriteria criteria) {
		
		try {
			System.out.println("Connecting to SPDM");
			URL spdmurl = new URL(URL_SPDM);
		} catch (MalformedURLException e) {

			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
	
		List<ICompositionPlan> orderedCompositions = new Vector<ICompositionPlan>();

		try {

			String value = criteria.getCriteria().get("Trustworthiness");
			System.out.println("Weight set by consumer for Trustworthiness: "+Double.parseDouble(value));
			value = criteria.getCriteria().get("Credibility");
			System.out.println("Weight set by consumer for Credibility: "+value);
			value = criteria.getCriteria().get("Validness");
			System.out.println("Weight set by consumer for Validness: "+value);
			
			String criteriaValueString = null;
			
			for (int index = 0; index < securedCompositions.size(); index++) {
				
				double overallValue = getOrderValue(securedCompositions.get(index), criteria);
				//Subtle action: use the bpmnstring to save the value. It will not affect the userinterface part as the bpmnstring already saved there.
				
				criteriaValueString = Double.toString(overallValue);
				securedCompositions.get(index).setBPMNXML(criteriaValueString);				

				int position = 0;
				String comparedValueString = null;
				if (orderedCompositions.size() == 0)
					orderedCompositions.add(position, securedCompositions.get(index));
				else {
					comparedValueString = orderedCompositions.get(position).getBPMNXML();
							
					while (criteriaValueString.compareTo(comparedValueString) < 0) {
						position++;
						if (position < orderedCompositions.size())
							comparedValueString = orderedCompositions.get(position).getBPMNXML();
													
						else
							break;

					}
					orderedCompositions.add(position, securedCompositions.get(index));
				}
				
			
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();

		}

		return orderedCompositions;
	}
	
	
	private double getOrderValue(ICompositionPlan plan, OrderCriteria oc)
	{
		double  trustworthiness = 0.0;
		double  credibility =0.0;
		double  validness = 0.0;
		List<Service> subServices = BPMNParser.getServices(plan.getBPMNXML());
		int allPropertyNum =0;
		int verifiedPropertyNum =0;
		
		double daysToExpire = 0;
		for (int i = 0; i < subServices.size(); i++){
			try{
			WebService ws = new WebService();
			ws.setServiceID(subServices.get(i).getServiceId());
			SPState bind = SPState.Bind;
			allPropertyNum += SPDMClient.getProperties(ws, bind, URL_SPDM).size();
			SPState unbind = SPState.UnBind;
			allPropertyNum += SPDMClient.getProperties(ws, unbind, URL_SPDM).size();
			SPState signed = SPState.Signed;
			allPropertyNum += SPDMClient.getProperties(ws, signed, URL_SPDM).size();
			
			SPState verified = SPState.Verified;
			Set<ISecurityProperty> verifiedProperties = SPDMClient.getProperties(ws, verified, URL_SPDM);
			verifiedPropertyNum += verifiedProperties.size();
			allPropertyNum += verifiedProperties.size();
			Iterator<ISecurityProperty> it = verifiedProperties.iterator();
						
							
			long freshness = 0;
	        while(it.hasNext())
	        {
	        	ISecurityProperty sp = it.next();
	            freshness += (new Date().getTime() - sp.getFreshness().getTime())/1000/60/60/24;
	        }
	        
	        daysToExpire += freshness;
			}
			catch (Exception e)
			{
				System.out.println("SPDM returns error: "+ e.getMessage());
				
			}
			
		}
		
		
		try {
			System.out.println("Connecting to Composite Trust Service");
			URL url = new URL(URL_CompositeTrustPrediction);
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		
		try
		{
			trustworthiness = TrustClient.getCompositeTrustworthiness(plan, URL_CompositeTrustPrediction);
			System.out.println("Get Trustworhtiness value: "+trustworthiness);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
			trustworthiness  = 0;
		}
						
		System.out.println("Number of properties for all subservices in the SPDM is "+ allPropertyNum);
		if (allPropertyNum != 0)
		{
			credibility = ((double)verifiedPropertyNum)/allPropertyNum;
			System.out.println("Credibility value is " +credibility);
		}
											
			
		System.out.println("Number of verified properties for all subservices in the SPDM is "+ verifiedPropertyNum);
		if (verifiedPropertyNum != 0)
		{
			validness = 1/(1 + 0.6*daysToExpire/verifiedPropertyNum); //TODO: limit to 0-1
			System.out.println("Valiness value is " +validness);
		}
		else{
			System.out.println("Credibility and Validness set to 0 because no verified properties found in SPDM");
			credibility = 0;
			validness = 0;
		}

	
		double trustWeight = Double.parseDouble(oc.getCriteria().get("Trustworthiness"));
		double creditWeight = Double.parseDouble(oc.getCriteria().get("Credibility"));
		double validWeight = Double.parseDouble(oc.getCriteria().get("Validness"));
		double overallValue = trustWeight* trustworthiness + creditWeight*credibility
				+ validWeight * validness;
		
		return overallValue;
	}


	/*
	 * (non-Javadoc)
	 */
	@Override
	public ICompositionPlan reconfiguration(ICompositionPlan backupBPMNdiagram,
			IAgreementTemplate agreementTemplate, IConsumerPolicy consumerPolicy) {

		// ServiceQuery serviceQuery = new ServiceQuery("map","","");
		String testS = "";
		
		Map<String, Set<Service>> taskServices = new HashMap<String, Set<Service>>();

		try {
			List<String> serviceTaskIDs = new Vector<String>();
			//System.out.println(backupBPMNdiagram.getBPMNXML());
			serviceTaskIDs = BPMNParser.getServiceTaskList(backupBPMNdiagram
					.getBPMNXML());
			for (String serviceTask : serviceTaskIDs) {
				String serviceType = BPMNParser.getServiceType(
						backupBPMNdiagram.getBPMNXML(), serviceTask);

				ServiceQuery serviceQuery = new ServiceQuery(serviceType, "",
						"");

				Set<Service> discoveredSerivces = discoverServices(
						serviceQuery, URL_Marketplace);
				//
				//
				// if (discoveredSerivces.size()>0){
				// for(Service service : discoveredSerivces){
				// testS=testS+"Disvocered Service ID: "+service.getServiceId()+"   Location: "+service.getLocation()+"\n";
				// }
				// }
				// else {testS+="no service discovered";}

				taskServices.put(serviceTask, discoveredSerivces);

				testS += serviceTask + " and " + serviceType + "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
			testS = "Error while tring to connect the Marketplace";
		}

		List<ICompositionPlan> newPlans = new Vector<ICompositionPlan>();
		newPlans = createCompositionPlans(backupBPMNdiagram, taskServices);
		System.out.println("SCPM generated " + newPlans.size()
				+ " new composition plans for reconfiguratoin.");

		List<ICompositionPlan> verifiedPlans = new Vector<ICompositionPlan>();
			
		verifiedPlans = verifyAtRuntime(newPlans, consumerPolicy, agreementTemplate);
		
		ICompositionPlan returnComposition = new CompositionPlan ("Error: Reconfiguration cannot find composition plan that satisfies the security policies");
		if (verifiedPlans.size() != 0)			
			returnComposition = orderAtRuntime (verifiedPlans, consumerPolicy);
		
		
		return returnComposition;

	}

	@Override
	public ICompositionPlan recomposition(ICompositionPlan backupBPMNdiagram,
			String serviceTaskIdToBeReplaced,
			IAgreementTemplate at, IConsumerPolicy cp) {

		String testS = "";
		Map<String, Set<Service>> taskServices = new HashMap<String, Set<Service>>();
		boolean serviceTaskFound = false;

		try {
			List<String> serviceTaskIDs = new Vector<String>();
			//System.out.println(backupBPMNdiagram.getBPMNXML());
			serviceTaskIDs = BPMNParser.getServiceTaskList(backupBPMNdiagram
					.getBPMNXML());
			for (String serviceTask : serviceTaskIDs) {
				if (serviceTask.equals(serviceTaskIdToBeReplaced)) {
					serviceTaskFound = true;
					String serviceType = BPMNParser.getServiceType(
							backupBPMNdiagram.getBPMNXML(), serviceTask);
					Service service = BPMNParser.getService(
							backupBPMNdiagram.getBPMNXML(), serviceTask);
					ServiceQuery serviceQuery = new ServiceQuery(serviceType,
							"", "");

					Set<Service> discoveredServices = discoverServices(
							serviceQuery, URL_Marketplace);
					
					Set<Service> temp = new HashSet<Service>();
					//take the original servicetoBeReplaced out of the discovery
					for(Service i : discoveredServices)
					{
						if (!i.getServiceId().equals(service.getServiceId()))
							temp.add(i);
					}
					
										
					if (temp.size() <= 0)
					{
						ICompositionPlan returnComposition = new CompositionPlan ("Error: Recomposiiton cannot find alternative services for the specified ServiceTask");
						return returnComposition;
					}
					
					taskServices.put(serviceTask, temp);
					//
					//
					// if (discoveredSerivces.size()>0){
					// for(Service service : discoveredSerivces){
					// testS=testS+"Disvocered Service ID: "+service.getServiceId()+"   Location: "+service.getLocation()+"\n";
					// }
					// }
					// else {testS+="no service discovered";}
				} else {
					Set<Service> unchangedService = new HashSet<Service>();
					Service service = BPMNParser.getService(
							backupBPMNdiagram.getBPMNXML(), serviceTask);
					unchangedService.add(service);
					taskServices.put(serviceTask, unchangedService);
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
			testS = "Error while tring to connect the Marketplace";
		}
		
		if (!serviceTaskFound)
		{
			ICompositionPlan returnComposition = new CompositionPlan ("Error: Recomposition cannot match the ServiceTask ID in the composition plan");
			return returnComposition;
		}
		
		
		List<ICompositionPlan> newPlans = new Vector<ICompositionPlan>();
		newPlans = createCompositionPlans(backupBPMNdiagram, taskServices);
		System.out.println("SCPM generated " + newPlans.size()
				+ " new composition plans for recomposition.");

		List<ICompositionPlan> verifiedPlans = new Vector<ICompositionPlan>();
		//System.out.println(newPlans.get(0).getBPMNXML());
		verifiedPlans = verifyAtRuntime(newPlans, cp, at);
				//System.out.println(cp.getXML());
		ICompositionPlan returnComposition = new CompositionPlan ("Error: Recomposition cannot find composition plan that satisfies the security policies");
		
		if (verifiedPlans.size() != 0)			
			returnComposition = orderAtRuntime (verifiedPlans, cp);
		
		return returnComposition;

	}
	
	//runtime verification for recomposition and reconfiguration
	private List<ICompositionPlan> verifyAtRuntime(
			List<ICompositionPlan> newPlans,
			IConsumerPolicy newPolicy,
			IAgreementTemplate newTemplate) {
		
		List<ICompositionPlan> verifiedPlans = new Vector<ICompositionPlan>();
		List<ISelectResult> verifiedResults = new Vector<ISelectResult>();
		List<IAgreementTemplate> newTemplates = new Vector<IAgreementTemplate>();
		List<IConsumerPolicy> newPolicies = new Vector<IConsumerPolicy>();
		
		
		for (int itemNum = 0; itemNum < newPlans.size(); itemNum++)
		{
			
			newTemplates.add(newTemplate);
			newPolicies.add(newPolicy);
		}

		verifiedResults = selectSecureCompositions(newPlans, newPolicies, newTemplates);
		int verifiedNum = verifiedResults.size();
		for (int planNum = 0; planNum < verifiedNum; planNum ++)
		{
			if ((verifiedResults.get(planNum).getResult() > 0) &&
			(verifiedResults.get(planNum).getExplanation().indexOf("NCVM returns pass") >0))
			{
				verifiedPlans.add(verifiedResults.get(planNum).getPlan());
				System.out.println(verifiedResults.get(planNum).getExplanation());
			}
		}
		
		
		return verifiedPlans;
	}
	
	//runtime ordering based on orderCriteria saved in consumerPolicy
	private ICompositionPlan orderAtRuntime(
			List<ICompositionPlan> securedCompositions, IConsumerPolicy consumerPolicy
			) {
	
		try {
			System.out.println("Connecting to SPDM");
			URL spdmurl = new URL(URL_SPDM);
		} catch (MalformedURLException e) {

			//System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		String commentCriteria = consumerPolicy.getXML();
		//System.out.println(commentCriteria);
		int j = commentCriteria.indexOf("<!--orderCriteria");
		int k = commentCriteria.indexOf("-->");
		if (k > j)//if the order criteria indeed saved in the consumer policy
		{
			commentCriteria = commentCriteria.substring(j, k-1);
		}
		else commentCriteria = "<!--orderCriteria Trustworthiness 1 Credibility 1 Validness 1 -->";
		
		OrderCriteria criteria = new OrderCriteria();
		ICompositionPlan bestComposition = new CompositionPlan("");
		
		StringTokenizer st = new StringTokenizer(commentCriteria);
		
		while (st.hasMoreElements()) {
			String temp = st.nextToken();
			 if (temp.equals("Trustworthiness"))
			 	criteria.addCriterion("Trustworthiness", st.nextToken());
			 else if (temp.equals("Credibility"))
					criteria.addCriterion("Credibility", st.nextToken());
			 else if (temp.equals("Validness"))
					criteria.addCriterion("Validness", st.nextToken());
			 										
		}
		

		try {

			String value = criteria.getCriteria().get("Trustworthiness");
			System.out.println("trustworthiness:"+value);
			value = criteria.getCriteria().get("Credibility");
			System.out.println("Credibility:"+value);
			value = criteria.getCriteria().get("Validness");
			System.out.println("validness:"+value);
			
			
			double maxValue = 0;
			for (int index = 0; index < securedCompositions.size(); index++) {
				
				double overallValue = getOrderValue(securedCompositions.get(index), criteria); 
				
				String compositionID = securedCompositions.get(index)
						.getCompositionPlanID();
				System.out.println("Overall value of " +compositionID+ ": "+overallValue);
				if (overallValue >= maxValue){
					maxValue = overallValue;
					bestComposition = securedCompositions.get(index);
				}
				
					
			}

		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}

		
		return bestComposition;
	}

	/* (non-Javadoc)
	 * @see eu.aniketos.wp5.dummy.scf.ServiceCompositionFrameworkInterface#discoverServices(eu.aniketos.wp5.dummy.scf.ServiceQuery)
	 */
	private Set<Service> discoverServices(ServiceQuery serviceQuery, String addressMarketplace) {
		

		if (logger.isDebugEnabled()) {
			logger.debug("Discovering services"); //$NON-NLS-1$
		}
		
	
		try {
			URL url = new URL(URL_Marketplace);
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		
		Set<Service> servicesDiscovered = new HashSet<Service>();
		
		//Integration with Aniketos Marketplace
		
		eu.aniketos.scpm.marketplace.client.ArrayOfServiceDescriptor serviceDescriptors = MarketplaceClient.discoverServices(serviceQuery, addressMarketplace);
		
		List<eu.aniketos.scpm.marketplace.client.ServiceDescriptor> listServiceDescriptors = serviceDescriptors.getServiceDescriptor();
		
		for(eu.aniketos.scpm.marketplace.client.ServiceDescriptor serviceDescriptor : listServiceDescriptors){
			String location = serviceDescriptor.getBinding().getValue();
			String serviceId = serviceDescriptor.getId().getValue();
			String provider = serviceDescriptor.getProviderName().getValue().toLowerCase();
			if(location!=null){
				Service service = new Service(serviceId, location, provider);
				servicesDiscovered.add(service);
			}
//			if(location!=null){
//				locations.add(location);
//			}
		}
		


		return servicesDiscovered;
		
	}

	/* (non-Javadoc)
	 * @see eu.aniketos.wp5.dummy.scf.ServiceCompositionFrameworkInterface#createCompositionPlans(java.lang.String, java.util.Hashtable)
	 */
	private List<ICompositionPlan> createCompositionPlans(ICompositionPlan serviceSpecification,
			Map<String, Set<Service>> mapTaskServices) {
		
		//Creation of several composition plans combining the service locations

		if (logger.isDebugEnabled()) {
			logger.debug("Creating composition Plans for process " +serviceSpecification.getCompositionPlanID()); //$NON-NLS-1$
		}
		
		Set<String> taskIds = mapTaskServices.keySet();
		
		Object[] taskIdsArray = (Object[]) taskIds.toArray();
		
		LinkedList<ICompositionPlan> listCompositionPlan = new LinkedList<ICompositionPlan>();

		XMLOutputter xmlOutput = new XMLOutputter();
		xmlOutput.setFormat(Format.getPrettyFormat());
		
		Iterator<String> itTaskIds = taskIds.iterator();

		List<Set<Service>> list = new LinkedList<Set<Service>>();
		while(itTaskIds.hasNext()){
			String taskId = itTaskIds.next();
			Set<Service> services = mapTaskServices.get(taskId);
			Set<String> locations = new HashSet<String>();
			for(Service service : services){
				locations.add(service.getLocation());
			}
			list.add(services);
		}
		
		Set<List<Service>> set = Sets.cartesianProduct(list);
		Object[] setArray = (Object[]) set.toArray();
		

		for(int j = 0; j<setArray.length; j++){
			Document newDocument = BPMNParser.getDocument(serviceSpecification.getBPMNXML());
			
		//	List<String> locationsParsed = new Vector<String>();
			
			@SuppressWarnings("unchecked")
			List<Object> listS = (List<Object>) setArray[j];
			for(int i=taskIdsArray.length-1; i>=0; i--){
				String taskId = (String)taskIdsArray[i];
//				String taskId = serviceIds.get(i);
				Service service = (Service)listS.get(i);
				String location = service.getLocation();
//				if(!locationsParsed.contains(location)){
//					String portTypeName = WsdlParser.getInstance().getPortTypeName(location);
//					Element interfaceElement = XMLParser.addPortTypeName(newDocument, portTypeName);
//					Map<String,List<String>> hashOperationsData = WsdlParser.getInstance().getOperationsData(location);
//					Set<String> setOperations = hashOperationsData.keySet();
//					for(String operation : setOperations){
//						List<String> listInputOutput = hashOperationsData.get(operation);
//						XMLParser.addOperation(newDocument, interfaceElement, portTypeName, operation, listInputOutput);
//					}
//					locationsParsed.add(location);
//				}
				
				String serviceId = service.getServiceId();
				logger.debug("ServiceId: "+serviceId);
				
				String provider = service.getProvider();
				
				BPMNParser.addServiceId(newDocument, taskId, serviceId);
				BPMNParser.addLocationField(newDocument, taskId, location);
				BPMNParser.addProviderField(newDocument, taskId, provider);
			}
			int c = j+1;
			BPMNParser.addProcessId(newDocument, "compositionPlan"+c);
			logger.debug("Created compositon plan"+c);
			
			String specification = xmlOutput.outputString(newDocument);
			ICompositionPlan compositionPlan = new CompositionPlan(specification);
			compositionPlan.setCompositionPlanID("compositionPlan"+c);
			
			//Interaction with the composition planner
			
			listCompositionPlan.add(compositionPlan);
			logger.debug("compositionPlan " + compositionPlan.getBPMNXML());

		}
		
		logger.info("Created "+listCompositionPlan.size()+" Composition Plans");
		
		return listCompositionPlan;


	}

//	@Override
//	public ICompositionPlan inputSPDMData() {
//		
//		ISPDMService spdmService = null;
//		
//		if (logger.isDebugEnabled()) {
//			logger.debug("Discovering services"); //$NON-NLS-1$
//		}
//		
//		URL url = null;
//		try {
//			url = new URL(URL_Marketplace);
//		} catch (MalformedURLException e) {
//			
//			e.printStackTrace();
//		}
//		
//		
//		eu.aniketos.scpm.marketplace.client.ArrayOfServiceDescriptor serviceDescriptors = MarketplaceClient.discoverServices(null, URL_Marketplace);
//		List<eu.aniketos.scpm.marketplace.client.ServiceDescriptor> listServiceDescriptors = serviceDescriptors.getServiceDescriptor();
//				
//
//		try {
//			spdmService = (ISPDMService) getSPDM();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		
//
//		if (spdmService == null) {
//			System.out.println("SPDM Service is null");
//						
//		} else
//		{
//			System.out.println("SPDM service is found");
//			System.out.println("Size of SPDM before: "+ spdmService.cache_size() );
//			spdmService.emptyCache();
//			System.out.println("Size of SPDM now: "+ spdmService.cache_size() );
//			int i = 0;
//			for(eu.aniketos.scpm.marketplace.client.ServiceDescriptor serviceDescriptor : listServiceDescriptors){
//				i++;
//				if (i<10)
//				{
//				String location = serviceDescriptor.getBinding().getValue();
//				String serviceId = serviceDescriptor.getId().getValue();
//				System.out.println(serviceId);
//				//String provider = serviceDescriptor.getProviderName().getValue().toLowerCase();
//				if(serviceId!=null){
//					//Service service = new Service(serviceId, location, provider);
//					WebService registerService = new WebService();
//					
//					registerService.setServiceID(serviceId);
//					SecurityProperty registerProperty = new SecurityProperty();
//					registerProperty.setPropertyID("SoD");
//					registerProperty.setPropertyValue("false");
//					
//					//SecurityDescriptor registerDescriptor = new SecurityDescriptor();
//					//registerDescriptor.addProperty(registerProperty);
//					
//					SecurityProperty registerProperty1 = new SecurityProperty();
//					registerProperty1.setPropertyID("BoD");
//					registerProperty1.setPropertyValue("true");
//					//registerDescriptor.addProperty(registerProperty1);
//					
//					//spdmService.registerService(registerService, registerDescriptor);
//					
//							
//					spdmService.registerService(registerService, registerProperty1);
//
//					
//				}
//				}
//			}
//			
//			
//		
//		}
//		
//		System.out.println("Size of SPDM after: "+ spdmService.cache_size() );
//		spdmService.print_ws_entries();
//		spdmService.print_repository();
//		spdmService.print_sp_entries();
//		
//		
//		ISecurityProperty sp = new SecurityProperty();
//		sp.setPropertyID("BoD");
//		sp.setPropertyValue("true");
//		Set <IWebService> wws = spdmService.lookupService(sp);
//		System.out.println(wws.size());
//		SPState unbind = SPState.UnBind;
//			
//		IWebService ws = spdmService.getService("http://83.235.133.36/AniketosAlternateWS/LawInfoServiceSoapHttpPort?wsdl");
//		
//		Set <ISecurityProperty> verificationReslt1 = spdmService.getProperties(ws, unbind);
//		System.out.println("SPDM testing result: " + verificationReslt1.size());
//		
//		
//		ISecurityProperty verificationReslt = spdmService.getSecurityProperty("http://83.235.133.36/AniketosAlternateWS/LawInfoServiceSoapHttpPort?wsdl", "BoD");
//		System.out.println("SPDM testing result: " + verificationReslt.getPropertyValue());
//			
//		return null;
//	}
	
//	public void testCSVM(String bpmn, String activiti) {
//		URL csvmurl = null;
//		try {
//			System.out.println("Connecting to CSVM");
//			csvmurl = new URL(URL_CSVM);
//		} catch (MalformedURLException e) {
//
//			e.printStackTrace();
//		}
//		try{
//			ICompositionPlan cp = new CompositionPlan("");
//			cp.setCompositionPlanID("compositionPlan2");
//			cp.setBPMNXML(bpmn);
//			cp.setActivitiFile(activiti);
//			
//			eu.aniketos.scpm.csvm.client.CompositionSecurityValidationResult result= CSVMClient.validateCSVM(cp, URL_CSVM);
//			System.out.println(result.getVerificationResult().getValue());
//			System.out.println(result.getVerificationExplaination().getValue());
//		}
//		catch (Exception e){
//			e.printStackTrace();
//		}
//	
//	}

	/**
	 * Decomposite the selected composition plan It uses the planner's parser.
	 */

	/*
	 * @Override public List<String>
	 * decompositeComposition(List<ICompositionPlan> compositionPlans) {
	 * List<String> serviceIDs = new Vector<String>(); List<String> bpmnString =
	 * new Vector<String>(); for (ICompositionPlan composition :
	 * compositionPlans) bpmnString.add(composition.getBPMNXML());
	 * 
	 * serviceIDs = getServiceIDs(bpmnString);
	 * 
	 * return serviceIDs; }
	 * 
	 * 
	 * public List<String> getServiceIDs(List<String> inputSet) {
	 * System.out.println
	 * ("In SimpleParse Composition Planner, translating BPMN2"); List<String>
	 * serviceIDs = new Vector<String>();
	 * 
	 * try { List<NodeList> translated = new Vector<NodeList>(); for (String
	 * bpmn : inputSet) { File fXmlFile = new File(bpmn);
	 * 
	 * 
	 * 
	 * DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	 * DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); Document doc =
	 * dBuilder.parse(fXmlFile); doc.getDocumentElement().normalize();
	 * 
	 * System.out.println("Root element :" +
	 * doc.getDocumentElement().getNodeName()); NodeList nList =
	 * doc.getElementsByTagName("serviceTask");
	 * System.out.println("-----------------------"); translated.add(nList); }
	 * 
	 * List<Element> tasks= new Vector<Element>(); for (NodeList check :
	 * translated) {
	 * 
	 * 
	 * 
	 * for (int temp = 0; temp < check.getLength(); temp++) {
	 * 
	 * Node nNode = check.item(temp); if (nNode.getNodeType() ==
	 * Node.ELEMENT_NODE) {
	 * 
	 * Element eElement = (Element) nNode; tasks.add(eElement);
	 * 
	 * System.out.println("Service ID : " + getAttValue("id", eElement));
	 * System.out.println("Service Name : " + getAttValue("name", eElement));
	 * 
	 * 
	 * } } }
	 * 
	 * 
	 * for (Element service : tasks) { serviceIDs.add(getAttValue("id",
	 * service)); }
	 * 
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * 
	 * 
	 * return serviceIDs;
	 * 
	 * 
	 * }
	 * 
	 * private static String getAttValue(String sAtt, Element eElement) {
	 * 
	 * 
	 * return eElement.getAttribute(sAtt); }
	 * 
	 * private static String getTagValue(String sTag, Element eElement) {
	 * NodeList nlList =
	 * eElement.getElementsByTagName(sTag).item(0).getChildNodes();
	 * 
	 * Node nValue = (Node) nlList.item(0);
	 * 
	 * return nValue.getNodeValue(); }
	 */

	/*    *//**
	 * Set the service centre service.
	 * 
	 * @param service
	 *            The service centre service.
	 * 
	 */
	/*
	 * public synchronized void setServiceCentreService(IServiceCentre service)
	 * { System.out.println("serviceCentre was set. !"); this.serviceCentre =
	 * service; }
	 *//**
	 * Release the service centre service.
	 * 
	 * @param service
	 *            The service centre service.
	 * 
	 */
	/*
	 * public synchronized void unsetServiceCentreService(IServiceCentre
	 * service) { System.out.println("serviceCentre was unset."); if
	 * (this.serviceCentre == service) { this.serviceCentre = null; } }
	 *//**
	 * Set the CMM service.
	 * 
	 * @param service
	 *            The CMM service.
	 * 
	 */
	/*
	 * public synchronized void setContractManagerService(ContractManagerService
	 * service) { System.out.println("contractManagementService was set. !");
	 * this.contractManagementService = service; }
	 *//**
	 * Release the CMM service.
	 * 
	 * @param service
	 *            The CMM service.
	 * 
	 */
	/*
	 * public synchronized void
	 * unsetContractManagerService(ContractManagerService service) {
	 * System.out.println("contractManagementService was unset."); if
	 * (this.contractManagementService == service) {
	 * this.contractManagementService = null; } }
	 */
	
//	/**
//	 * Transform a String into Base64 String format.
//	 * @param s Input String.
//	 * @return Output String in base64 format.
//	 */
//	private static String stringToBase64String(String s) {
//
//		byte[] bytes;
//
//		try
//		{
//			bytes = s.getBytes("US-ASCII");
//		}
//		catch (UnsupportedEncodingException ue)
//		{
//			ue.printStackTrace();
//			bytes = s.getBytes();
//		}
//		return Base64.encodeBase64String(bytes);
//
//	}
//	
//	/**
//	 * Transform a Base64 String into  String format.
//	 * @param s Input String in base64 format.
//	 * @return Output String .
//	 */
//	private static String base64StringToString(String s) {
//
//		byte[] bytes;
//		bytes = Base64.decodeBase64(s);
//		
//		try {
//			String str = new String(bytes, "UTF-8");
//			return str;
//		} catch (UnsupportedEncodingException e) {
//			
//			e.printStackTrace();
//			return "there is en error with base64";
//		}
//		
//
//	}

}
