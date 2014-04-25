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

import eu.aniketos.data.IAgreementTemplate;
import eu.aniketos.data.IConsumerPolicy;
import eu.aniketos.scpm.cmm.client.ArrayOfString;
import eu.aniketos.scpm.cmm.client.IContractManagement;
import eu.aniketos.scpm.cmm.client.IContractManagementPortType;
import eu.aniketos.scpm.cmm.client.ObjectFactory;
import eu.aniketos.data.impl.Result;




public class CMMClient {
	private final static int timeout = 1 * 60 * 1000; // in milliseconds
	public static eu.aniketos.data.impl.Result CheckMatching(eu.aniketos.data.IAgreementTemplate agreementTemplate, eu.aniketos.data.IConsumerPolicy consumerPolicy, String addressCMM){
		
		QName serviceName = new QName("http://contractmanager.aniketos.eu/", "IContractManagement");
		URL url = null;
        try {
            URL baseUrl;
            baseUrl = eu.aniketos.scpm.cmm.client.IContractManagement.class.getResource(".");
            url = new URL(baseUrl, addressCMM);
        } catch (MalformedURLException ex) {
        	ex.printStackTrace();
        }
		
				
        IContractManagement cmmService = new IContractManagement(url,serviceName);
        IContractManagementPortType cmmModule = null;
        try {
	        cmmModule = cmmService.getIContractManagementPort();;

			Map<String, Object> requestContext = ((BindingProvider)cmmModule).getRequestContext();
			requestContext.put("javax.xml.ws.client.receiveTimeout", Integer.toString(timeout));
		} catch (Exception exception) {
			System.out.println("Marketplace Proxy exception: " + exception.getMessage());
		}
		//System.out.println("Testing CMM service");
        eu.aniketos.scpm.cmm.client.IAgreementTemplate a = convertIAgreementTemplateToWS(agreementTemplate);
        eu.aniketos.scpm.cmm.client.IConsumerPolicy p = convertIConsumerPolicyToWS(consumerPolicy);
        eu.aniketos.scpm.cmm.client.Result matchingResult = cmmModule.checkMatching(a, p);
		
       	return convertResultFromWS(matchingResult);
		
	}
	


private static eu.aniketos.scpm.cmm.client.IAgreementTemplate convertIAgreementTemplateToWS(IAgreementTemplate template) {
	ObjectFactory factory = new ObjectFactory();
	eu.aniketos.scpm.cmm.client.IAgreementTemplate result = new eu.aniketos.scpm.cmm.client.IAgreementTemplate();
		
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

private static eu.aniketos.scpm.cmm.client.IConsumerPolicy convertIConsumerPolicyToWS(IConsumerPolicy policy) {
	ObjectFactory factory = new ObjectFactory();
	eu.aniketos.scpm.cmm.client.IConsumerPolicy result = new eu.aniketos.scpm.cmm.client.IConsumerPolicy();
		
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

private static  Result convertResultFromWS (eu.aniketos.scpm.cmm.client.Result matchingResult){
	ObjectFactory factory = new ObjectFactory();
	Result result = new Result(matchingResult.getErrorCode().intValue(), matchingResult.getExplanation().getValue());
	return result;
}

}
