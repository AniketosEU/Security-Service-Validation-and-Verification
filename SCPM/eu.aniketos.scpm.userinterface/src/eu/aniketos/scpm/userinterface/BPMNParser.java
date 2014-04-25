/**
 * Copyright 2012  Bo Zhou <B.Zhou@ljmu.ac.uk>
 * Liverpool John Moores University <http://www.ljmu.ac.uk/cmp/>
 * Copyright 2012  Francesco Malmignati, Selex Elsag
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

package eu.aniketos.scpm.userinterface;

import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Vector;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

import eu.aniketos.scpm.data.Service;

/**
 * @author Francesco Malmignati, Selex Elsag
 *
 */
public class BPMNParser {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BPMNParser.class);

	public static Document getDocument(String file){

		SAXBuilder builder = new SAXBuilder();
		
		Document doc = null;

		try {
			InputStream is = new ByteArrayInputStream(file.getBytes("UTF-8"));
			doc = (Document) builder.build(is);
			
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		} catch (JDOMException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
		
		return doc;
	}
	
	public static void addLocationField(Document document, String taskId, String location){
		
		Element definitions = document.getRootElement();
		
		Namespace n = definitions.getNamespace();
		
		Element process = definitions.getChild("process", n);
		
		@SuppressWarnings("unchecked")
		List<Element> listServiceTask = process.getChildren("serviceTask", n);
		
		for(Element serviceTask: listServiceTask){
			if(serviceTask.getAttribute("id").getValue().equals(taskId)){
				Element extensionElements = serviceTask.getChild("extensionElements", n);
				
				Namespace ns = Namespace.getNamespace("activiti", "http://activiti.org/bpmn");
				Element field = new Element("field", ns);
				field.setAttribute("name", "location");
				Element string = new Element("string",ns);
				string.setText(location);
				field.addContent(string);
				
				if(extensionElements != null){
					boolean newfield = true;
					@SuppressWarnings("unchecked")
					List<Element> listField = extensionElements.getChildren("field",ns);
					for(Element testfield : listField){
						if(testfield.getAttributeValue("name").equals("location")){
							testfield.getChild("string",ns).setText(location);
							newfield = false;
						}
					}
					if (newfield)
						extensionElements.addContent(field);
				}
				
				logger.debug("Location "+ location +" added in ServiceTask "+ taskId);
				
			}
		}
	}
	
public static void addServiceProviderField(Document document, String taskId, String serviceProvider){
		
		Element definitions = document.getRootElement();
		
		Namespace n = definitions.getNamespace();
		
		Element process = definitions.getChild("process", n);
		
		@SuppressWarnings("unchecked")
		List<Element> listServiceTask = process.getChildren("serviceTask", n);
		
		for(Element serviceTask: listServiceTask){
			if(serviceTask.getAttribute("id").getValue().equals(taskId)){
				Element extensionElements = serviceTask.getChild("extensionElements", n);
				
				Namespace ns = Namespace.getNamespace("activiti", "http://activiti.org/bpmn");
				Element field = new Element("field", ns);
				field.setAttribute("name", "serviceProvider");
				Element string = new Element("string",ns);
				string.setText(serviceProvider);
				field.addContent(string);
				
				if(extensionElements != null){
					boolean newfield = true;
					@SuppressWarnings("unchecked")
					List<Element> listField = extensionElements.getChildren("field",ns);
					for(Element testfield : listField){
						if(testfield.getAttributeValue("name").equals("serviceProvider")){
							testfield.getChild("string",ns).setText(serviceProvider);
							newfield = false;
						}
					}
					if (newfield)
						extensionElements.addContent(field);
				}
				
				logger.debug("ServiceProvider "+ serviceProvider +" added in ServiceTask "+ taskId);
				
			}
		}
	}
	
	public static void addProviderField(Document document, String taskId, String provider){
		
		Element definitions = document.getRootElement();
		
		Namespace n = definitions.getNamespace();
		
		Element process = definitions.getChild("process", n);
		
		@SuppressWarnings("unchecked")
		List<Element> listServiceTask = process.getChildren("serviceTask", n);
		
		for(Element serviceTask: listServiceTask){
			if(serviceTask.getAttribute("id").getValue().equals(taskId)){
				Element extensionElements = serviceTask.getChild("extensionElements", n);
				
				Namespace ns = Namespace.getNamespace("activiti", "http://activiti.org/bpmn");
				Element field = new Element("field", ns);
				field.setAttribute("name", "serviceProvider");
				Element string = new Element("string",ns);
				string.setText(provider);
				field.addContent(string);
				
				
				if(extensionElements != null){
					boolean newfield = true;
					@SuppressWarnings("unchecked")
					List<Element> listField = extensionElements.getChildren("field",ns);
					for(Element testfield : listField){
						if(testfield.getAttributeValue("name").equals("serviceProvider")){
							testfield.getChild("string",ns).setText(provider);
							newfield = false;
						}
					}
					if (newfield)
						extensionElements.addContent(field);
				}
				
				logger.debug("ServiceProvider "+ provider +" added in ServiceTask "+ taskId);
				
			}
		}
	}	
	
	public static void addProcessId(Document document, String processId){
		
		Element definitions = document.getRootElement();
		
		Namespace n = definitions.getNamespace();
		Element process = definitions.getChild("process", n);
		process.getAttribute("id").setValue(processId);
		process.getAttribute("name").setValue(processId);
		
		Namespace n1 = definitions.getNamespace("bpmndi");
		Element BPMNDiagram = definitions.getChild("BPMNDiagram", n1);
		Element BPMNPlane = BPMNDiagram.getChild("BPMNPlane", n1);
		
		BPMNPlane.getAttribute("bpmnElement").setValue(processId);
		
	}
	
	public static String getProcessId(Document document){
		
		Element definitions = document.getRootElement();
		
		Namespace n = definitions.getNamespace();
		Element process = definitions.getChild("process", n);
		String compositionPlanId = process.getAttribute("id").getValue();
		
		return compositionPlanId;
	}
	
	public static List<String> getServicesList(String file){
		
		SAXBuilder builder = new SAXBuilder();
		
		Document doc = null;

		try {
			InputStream is = new ByteArrayInputStream(file.getBytes("UTF-8"));
			doc = (Document) builder.build(is);
			
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		} catch (JDOMException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
		
		Element definitions = doc.getRootElement();
		
		Namespace n = definitions.getNamespace();
		Element process = definitions.getChild("process", n);
		
		@SuppressWarnings("unchecked")
		List<Element> list = process.getChildren("serviceTask", n);
		
		List<String> serviceList = new Vector<String>();
		
		for(Element serviceTask : list){
			Element extensionElements = serviceTask.getChild("extensionElements", n);
			
			Namespace ns = Namespace.getNamespace("activiti", "http://activiti.org/bpmn");
			
			List<Element> listField = extensionElements.getChildren("field",ns);
			for(Element field : listField){
				if(field.getAttributeValue("name").equals("id")){
					String serviceId = field.getChild("string",ns).getValue();
					serviceList.add(serviceId);
				}
				
			}
	
		}
		
		return serviceList;
	}
	
	
	public static void addServiceId(Document document, String taskId, String serviceId){
		
		Element definitions = document.getRootElement();
		
		Namespace n = definitions.getNamespace();
		
		Element process = definitions.getChild("process", n);
		
		@SuppressWarnings("unchecked")
		List<Element> listServiceTask = process.getChildren("serviceTask", n);
		
		for(Element serviceTask: listServiceTask){
			if(serviceTask.getAttribute("id").getValue().equals(taskId)){
				Element extensionElements = serviceTask.getChild("extensionElements", n);
				
				Namespace ns = Namespace.getNamespace("activiti", "http://activiti.org/bpmn");
				Element field = new Element("field", ns);
				field.setAttribute("name", "id");
				Element string = new Element("string",ns);
				string.setText(serviceId);
				field.addContent(string);
				
				if(extensionElements != null){
					boolean newfield = true;
					@SuppressWarnings("unchecked")
					List<Element> listField = extensionElements.getChildren("field",ns);
					for(Element testfield : listField){
						if(testfield.getAttributeValue("name").equals("id")){
							testfield.getChild("string",ns).setText(serviceId);
							newfield = false;
						}
					}
					if (newfield)
						extensionElements.addContent(field);
				}
				
				logger.debug("ServiceId "+ serviceId +" added in ServiceTask "+ taskId);
				
			}
		}
	}
	
	public static Element addPortTypeName(Document document, String portTypeName){
		
		Element definitions = document.getRootElement();
		
		Namespace n1 = Namespace.getNamespace(portTypeName.toLowerCase(), "http://webservice.activiti.org/");
		definitions.addNamespaceDeclaration(n1);

		Namespace n = definitions.getNamespace();
		
		Element interfaceElement = new Element("interface", n);
		interfaceElement.setAttribute(new Attribute("name",portTypeName+" Interface"));
		interfaceElement.setAttribute(new Attribute("implementationRef",portTypeName.toLowerCase()+":"+portTypeName));
		definitions.addContent(interfaceElement);
		
		return interfaceElement;
		
	}
	
	public static void addOperation(Document document, Element interfaceElement, String portTypeName, String operation, List<String> listInputOutput){
		
		Element definitions = document.getRootElement();
		Namespace n = definitions.getNamespace();
		
		Element operationElement = new Element("operation", n);
		operationElement.setAttribute(new Attribute("id",operation+"Operation"));
		operationElement.setAttribute(new Attribute("name",operation+" Operation"));
		operationElement.setAttribute(new Attribute("implementationRef",portTypeName.toLowerCase()+":"+operation));
		interfaceElement.addContent(operationElement);
		
		Element inMessageRef = new Element("inMessageRef", n);
		inMessageRef.setText("tns:"+operation+"RequestMessage");
		Element outMessageRef = new Element("outMessageRef", n);
		outMessageRef.setText("tns:"+operation+"ResponseMessage");
		operationElement.addContent(inMessageRef);
		operationElement.addContent(outMessageRef);
		
		Element messageRequest = new Element("message");
		messageRequest.setAttribute(new Attribute("id",operation+"RequestMessage"));
		messageRequest.setAttribute(new Attribute("itemRef","tns:"+operation+"RequestItem"));
		definitions.addContent(messageRequest);
		
		Element messageResponse = new Element("message");
		messageResponse.setAttribute(new Attribute("id",operation+"ResponseMessage"));
		messageResponse.setAttribute(new Attribute("itemRef","tns:"+operation+"ResponseItem"));
		definitions.addContent(messageResponse);
		
		Element itemDefinitionRequest = new Element("itemDefinition");
		itemDefinitionRequest.setAttribute(new Attribute("id",operation+"RequestItem"));
		itemDefinitionRequest.setAttribute(new Attribute("structureRef",portTypeName.toLowerCase()+":"+listInputOutput.get(0)));
		definitions.addContent(itemDefinitionRequest);
		
		Element itemDefinitionResponse = new Element("itemDefinition");
		itemDefinitionResponse.setAttribute(new Attribute("id",operation+"ResponseItem"));
		itemDefinitionResponse.setAttribute(new Attribute("structureRef",portTypeName.toLowerCase()+":"+listInputOutput.get(1)));
		definitions.addContent(itemDefinitionResponse);
	}
	
	
	// extract service task IDs from the BPMN
	public static List<String> getServiceTaskList(String file){
		
		SAXBuilder builder = new SAXBuilder();
		
		Document doc = null;

		try {
			InputStream is = new ByteArrayInputStream(file.getBytes("UTF-8"));
			doc = (Document) builder.build(is);
			
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		} catch (JDOMException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
		
		Element definitions = doc.getRootElement();
		
		Namespace n = definitions.getNamespace();
		Element process = definitions.getChild("process", n);
		
		@SuppressWarnings("unchecked")
		List<Element> list = process.getChildren("serviceTask", n);
		
		List<String> serviceTaskList = new Vector<String>();
		
		for(Element serviceTask : list){
			String serviceTaskID = serviceTask.getAttribute("id").getValue();
			
			serviceTaskList.add(serviceTaskID);
	
		}
		
		return serviceTaskList;
		
	}
	
	
	
	public static String getServiceType(String file, String serviceTaskID)
	{
		SAXBuilder builder = new SAXBuilder();
		
		Document doc = null;
		String serviceType = null;

		try {
			InputStream is = new ByteArrayInputStream(file.getBytes("UTF-8"));
			doc = (Document) builder.build(is);
			
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		} catch (JDOMException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
		
		Element definitions = doc.getRootElement();
		
		Namespace n = definitions.getNamespace();
		Element process = definitions.getChild("process", n);
		
		@SuppressWarnings("unchecked")
		List<Element> list = process.getChildren("serviceTask", n);
		
		List<String> serviceList = new Vector<String>();
		
		for(Element serviceTask : list){
			
			String taskID = serviceTask.getAttribute("id").getValue();
			if (taskID.equals(serviceTaskID))
			{
				Element extensionElements = serviceTask.getChild("extensionElements", n);
				
				Namespace ns = Namespace.getNamespace("activiti", "http://activiti.org/bpmn");
				
				List<Element> listField = extensionElements.getChildren("field",ns);
				for(Element field : listField){
					if(field.getAttributeValue("name").equals("type")){
						serviceType = field.getChild("string",ns).getValue();
						
					}
					
				}
			}
		}
		
		return serviceType;
	}
	
	public static String getServiceID(String file, String serviceTaskID)
	{
		SAXBuilder builder = new SAXBuilder();
		
		Document doc = null;
		String serviceID = null;

		try {
			InputStream is = new ByteArrayInputStream(file.getBytes("UTF-8"));
			doc = (Document) builder.build(is);
			
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		} catch (JDOMException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
		
		Element definitions = doc.getRootElement();
		
		Namespace n = definitions.getNamespace();
		Element process = definitions.getChild("process", n);
		
		@SuppressWarnings("unchecked")
		List<Element> list = process.getChildren("serviceTask", n);
		
		List<String> serviceList = new Vector<String>();
		
		for(Element serviceTask : list){
			
			String taskID = serviceTask.getAttribute("id").getValue();
			if (taskID.equals(serviceTaskID))
			{
				Element extensionElements = serviceTask.getChild("extensionElements", n);
				
				Namespace ns = Namespace.getNamespace("activiti", "http://activiti.org/bpmn");
				
				List<Element> listField = extensionElements.getChildren("field",ns);
				for(Element field : listField){
					if(field.getAttributeValue("name").equals("id")){
						serviceID = field.getChild("string",ns).getValue();
						
					}
					
				}
			}
		}
		
		return serviceID;
	}
	
	// extract Services from the BPMN
	public static List<Service> getServices(String file){
		
		SAXBuilder builder = new SAXBuilder();
		
		Document doc = null;

		try {
			InputStream is = new ByteArrayInputStream(file.getBytes("UTF-8"));
			doc = (Document) builder.build(is);
			
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		} catch (JDOMException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
		
		Element definitions = doc.getRootElement();
		
		Namespace n = definitions.getNamespace();
		Element process = definitions.getChild("process", n);
		
		@SuppressWarnings("unchecked")
		List<Element> list = process.getChildren("serviceTask", n);
		
		List<Service> serviceList = new Vector<Service>();
		
		for(Element serviceTask : list){
			String serviceId = null;
			String serviceLocation = null;
			String serviceProvider = null;
			Element extensionElements = serviceTask.getChild("extensionElements", n);
			
			Namespace ns = Namespace.getNamespace("activiti", "http://activiti.org/bpmn");
			
			List<Element> listField = extensionElements.getChildren("field",ns);
			for(Element field : listField){
				
				if(field.getAttributeValue("name").equals("id")){
					serviceId = field.getChild("string",ns).getValue();
					
				}
				else if (field.getAttributeValue("name").equals("location")){
					serviceLocation = field.getChild("string",ns).getValue();
				}
				else if (field.getAttributeValue("name").equals("serviceProvider")){
					serviceProvider = field.getChild("string",ns).getValue();
				}
				
			}
			Service service = new Service (serviceId, serviceLocation, serviceProvider);
			serviceList.add(service);
	
		}
		
		return serviceList;
	}
	
	// extract Service from the BPMN based on serviceTask
		public static Service getService(String file, String serviceTaskID){
			
			SAXBuilder builder = new SAXBuilder();
			
			Document doc = null;

			try {
				InputStream is = new ByteArrayInputStream(file.getBytes("UTF-8"));
				doc = (Document) builder.build(is);
				
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			} catch (JDOMException e) {
				logger.error(e);
			} catch (IOException e) {
				logger.error(e);
			}
			
			Element definitions = doc.getRootElement();
			
			Namespace n = definitions.getNamespace();
			Element process = definitions.getChild("process", n);
			
			@SuppressWarnings("unchecked")
			List<Element> list = process.getChildren("serviceTask", n);
						
			String serviceId = null;
			String serviceLocation = null;
			String serviceProvider = null;
			
			for(Element serviceTask : list){
				
				String taskID = serviceTask.getAttribute("id").getValue();
				if (taskID.equals(serviceTaskID))
				{
					Element extensionElements = serviceTask.getChild("extensionElements", n);
					
					Namespace ns = Namespace.getNamespace("activiti", "http://activiti.org/bpmn");
					
					List<Element> listField = extensionElements.getChildren("field",ns);
					for(Element field : listField){
						if(field.getAttributeValue("name").equals("id")){
							serviceId = field.getChild("string",ns).getValue();
							
						}
						else if(field.getAttributeValue("name").equals("location")){
							serviceLocation = field.getChild("string",ns).getValue();
							
						}
						else if(field.getAttributeValue("name").equals("serviceProvider")){
							serviceProvider = field.getChild("string",ns).getValue();
						}
					}
				}
		
			}
			Service service = new Service (serviceId, serviceLocation, serviceProvider);
			
			return service;
		}
		
		

}
