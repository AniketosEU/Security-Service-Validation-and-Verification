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
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import eu.aniketos.scpm.trust.atomic.client.ITrustworthinessPrediction;
import eu.aniketos.scpm.trust.atomic.client.ITrustworthinessPredictionPortType;
import eu.aniketos.scpm.trust.atomic.client.Trustworthiness;
import eu.aniketos.data.ICompositionPlan;
import eu.aniketos.scpm.trust.composite.client.ICompositeTrustworthinessPrediction;
import eu.aniketos.scpm.trust.composite.client.ICompositeTrustworthinessPredictionPortType;
import eu.aniketos.scpm.trust.composite.client.ObjectFactory;


public class TrustClient {
	private final static int timeout = 1 * 60 * 1000; // in milliseconds
	public static double getTrustworthiness(eu.aniketos.data.ICompositionPlan compositionPlan, String addressTrustPredict){
		
		QName serviceName = new QName("http://messaging.ext.trustworthiness.aniketos.eu/", "ITrustworthinessPrediction");
		URL url = null;
        try {
            URL baseUrl;
            baseUrl = eu.aniketos.scpm.trust.atomic.client.ITrustworthinessPrediction.class.getResource(".");
            url = new URL(baseUrl, addressTrustPredict);
        } catch (MalformedURLException ex) {
        	ex.printStackTrace();
        }
		
				
        ITrustworthinessPrediction trustService = new ITrustworthinessPrediction(url,serviceName);
        ITrustworthinessPredictionPortType trustModule = null;
        try {
        	trustModule = trustService.getITrustworthinessPredictionPort();

			Map<String, Object> requestContext = ((BindingProvider)trustModule).getRequestContext();
			requestContext.put("javax.xml.ws.client.receiveTimeout", Integer.toString(timeout));
		} catch (Exception exception) {
			System.out.println("Marketplace Proxy exception: " + exception.getMessage());
		}
        
		System.out.println("Testing trust value for atomic service " + "http://83.235.133.36/AniketosWS/MapsServiceSoap12HttpPort?wsdl");
        Trustworthiness trustworthiness = trustModule.getTrustworthiness("http://83.235.133.36/AniketosWS/MapsServiceSoap12HttpPort?wsdl");
		
        if (trustworthiness == null)
        {
        	System.out.println("atomic service is unknown to trust model. return null");
        	return 0;
        }
        else 
        	return trustworthiness.getReputationScore();
		
	}
	
public static double getCompositeTrustworthiness(ICompositionPlan compositionPlan, String addressTrustPredict){
		
		QName serviceName = new QName("http://messaging.ext.trustworthiness.aniketos.eu/", "ICompositeTrustworthinessPrediction");
		URL url = null;
        try {
            URL baseUrl;
            baseUrl = eu.aniketos.scpm.trust.composite.client.ICompositeTrustworthinessPrediction.class.getResource(".");
            url = new URL(baseUrl, addressTrustPredict);
        } catch (MalformedURLException ex) {
        	ex.printStackTrace();
        }
		
				
        ICompositeTrustworthinessPrediction trustService = new ICompositeTrustworthinessPrediction(url,serviceName);
        ICompositeTrustworthinessPredictionPortType compositeTM = trustService.getICompositeTrustworthinessPredictionPort();
		System.out.println("Testing trust value for composite service: " + compositionPlan.getCompositionPlanID());
		eu.aniketos.scpm.trust.composite.client.Trustworthiness trustworthiness = compositeTM.getCompositeTrustworthiness(convertICompositionPlanToWS(compositionPlan));
		
        if (trustworthiness == null)
        {
        	System.out.println("composite service is unknown to trust model. return null");
        	return 0;
        }
        else 
        	return trustworthiness.getReputationScore();
		
	}


private static eu.aniketos.scpm.trust.composite.client.ICompositionPlan convertICompositionPlanToWS(ICompositionPlan plan) {
	ObjectFactory factory = new ObjectFactory();
	eu.aniketos.scpm.trust.composite.client.ICompositionPlan result = new eu.aniketos.scpm.trust.composite.client.ICompositionPlan();
		
	result.setCompositionPlanID(factory.createICompositionPlanCompositionPlanID(plan.getCompositionPlanID()));
	result.setActivitiFile(factory.createICompositionPlanActivitiFile(plan.getActivitiFile()));
	result.setBPMNXML(factory.createICompositionPlanBPMNXML(plan.getBPMNXML()));
	
	return result;
}

}
