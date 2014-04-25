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

import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.jdom.Element;
import org.jdom.JDOMException;

import eu.aniketos.data.ICompositionPlan;
import eu.aniketos.data.IConsumerPolicy;
import eu.aniketos.scpm.data.impl.ConsumerPolicy;
import eu.aniketos.spec.*;

/**
 * @author Bo Zhou, Liverpool John Moores University
 *
 */

public class FormatHelper {
	private static final Logger logger = Logger.getLogger(BPMNParser.class);

	public static IConsumerPolicy updatePolicyServiceID (ICompositionPlan plan, IConsumerPolicy policy){
		
		
		String bpmnContent = plan.getBPMNXML();
		String[] consumerPolicies = policy.getXmlContents();
		
		for (int i = 0; i< consumerPolicies.length; i++)
		{
			Specification spec = new Specification();
			try {
				spec.load(consumerPolicies[i].getBytes());
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JDOMException e) {
				e.printStackTrace();
			} catch (PolicyFormatException e) {
				e.printStackTrace();//logger.error(e);
			}
			//System.out.println(consumerPolicies[i]);
			Declaration[] declarations = spec.getDeclarations();
			//spec.remove(decl);
			for (int j = 0; j < declarations.length; j++)
			{
				if (declarations[j].identifier.identifier.equals("ServiceID"))
				{
					Element e = declarations[j].value.toElement();
					String serviceTaskID = e.getValue();
					//System.out.println(serviceTaskID);
					String serviceID = BPMNParser.getServiceID(bpmnContent, serviceTaskID);
					//System.out.println(serviceID);
					if (serviceID ==null)
					{
						System.out.println("Consumer Policy Error: Cannot find matched ServiceTaskID in the composition plan.");
						return policy;
					}
					
					BaseType bt = declarations[j].type;
					e.setText(serviceID);
					
					Exp exp = Exp.generateExp(e);
					Declaration decl = new Declaration(bt, "ServiceID", exp);
					
					//System.out.println(decl.identifier.identifier);
					//System.out.println(decl.value.toElement().getValue());
					spec.updateDeclaration(j, decl);
				
				}
			}

			
			String s = spec.saveToString();
				
			consumerPolicies[i] = s;
			//System.out.println(s);
		}
		
		return policy;
	}
	
	
	
public static IConsumerPolicy generalisePolicy (IConsumerPolicy policy){
		
		
		
		String[] consumerPolicies = policy.getXmlContents();
		
		for (int i = 0; i< consumerPolicies.length; i++)
		{
			Specification spec = new Specification();
			try {
				spec.load(consumerPolicies[i].getBytes());
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JDOMException e) {
				e.printStackTrace();
			} catch (PolicyFormatException e) {
				e.printStackTrace();//logger.error(e);
			}
			//System.out.println(consumerPolicies[i]);
			Declaration[] declarations = spec.getDeclarations();
			//spec.remove(decl);
			for (int j = 0; j < declarations.length; j++)
			{
				if (declarations[j].identifier.identifier.equals("ServiceID"))
				
					spec.remove(declarations[j]);
				
				
				
			}
			
			Rule[] rules = spec.getRules();
			for (int k = 0; k < rules.length; k++)
			{
				
			
				if (rules[k].when.identifier.identifier.startsWith("activity"))
					rules[k].when.identifier.identifier= rules[k].when.identifier.identifier.replaceAll("activity", "process");

				int rNumber = rules[k].perform.reactions.size();
				for (int l = 0; l < rNumber; l++)
				{
					Iterator<Element> elem = rules[k].perform.reactions.get(l).guard.toElement().getChildren().iterator();
					
					if (elem.hasNext())
					{
						Element e = elem.next();
						if (e.getParentElement().getName().equals("and"))
						{
							Element e1 = elem.next();
							if (e.getChildText("s_identifier")!=null)
							{
								if (e.getChildText("s_identifier").equals("ServiceID"))
								{
									BExp newGuard = BExp.generateBExp(e1);					        
									rules[k].perform.reactions.get(l).guard = newGuard;
								}
							}
							else if (e1.getChildText("s_identifier")!=null)
							{
								if (e1.getChildText("s_identifier").equals("ServiceID"))
								{
									BExp newGuard = BExp.generateBExp(e);					        
									rules[k].perform.reactions.get(l).guard = newGuard;
								}
							}
						
							
						}
					}
	        	        
					
				}
			}
			
			//and and or can only have two children, delete the one with ServiceID
	        //what about not?
			
			String s = spec.saveToString();
				
			consumerPolicies[i] = s;
			System.out.println(s);
		}
		
		return policy;
	}

}
