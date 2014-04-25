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

package eu.aniketos.scpm.data.impl;

import java.util.ArrayList;
import java.util.List;


import eu.aniketos.data.ISecurityProperty;
import eu.aniketos.data.IAgreementTemplate;

/**
 * This class implements the interfaces of IAgreementTemplate and also contains extra methods that used in Work Package 3. 
 * 
 * @author Bo Zhou, LJMU
 * 
 */

public class AgreementTemplate implements IAgreementTemplate{
	/**
	 * The XML content of an agreement template.
	 */
	private String xmlContent;
	/**
	 * The agreement template ID.
	 */
	private String agreementTemplateID;
	/**
	 * A Map that contains all properties. The property value can be retrieved given its ID.
	 */
	private List<ISecurityProperty> properties;
	//There will be a certificate object here in the future.
	
	private String[] xmlContents;
	
	/** Set the XML content. It is an easy way to modify the agreement template
	 *
	 * @param xmlContent The content of an agreement template in XML format.
	 * 
	 */
	public void setXML (String xmlContent)
	{
		this.xmlContent = xmlContent;
	}
	
	/** Get the XML content of agreement template. It temporarily used to replace method toString().
	 *
	 * @return The content of an agreement template in XML format.
	 * 
	 */
	@Override
	public String getXML()
	{
		// TODO Auto-generated method stub
		return xmlContent;
	}
	
	/** Constructor.
	 *
	 * @param agreementTemplateID The ID of the agreement template.
	 * 
	 */
	public AgreementTemplate(String agreementTemplateID){		
		this.agreementTemplateID = agreementTemplateID;
		properties = new ArrayList<ISecurityProperty>();
	}
	
	/** Get an agreement template ID.
	 *
	 * @return The ID of the agreement template.
	 * 
	 */
	public String getAgreementTemplateID() {
		return agreementTemplateID;
	}
	
	/** Get all properties in an agreement template.
	 *
	 * @return All properties of the agreement template. 
	 * It's saved in a map from where value can be retrieved given the property ID.
	 * 
	 */
	public List<ISecurityProperty> getProperties() {
		return properties;
	}
	
	/** Get the value of a property given its ID.
	 *
	 * @param propertyID The ID of a property.
	 * @return The value of the property that specified with ID.
	 * 
	 */
	public ISecurityProperty getProperty(String propertyID) {
		
		for(ISecurityProperty property : properties){
			if (property.getPropertyID().equals(propertyID))
			{
				return property;
			}
		}
		return null;
	
	}
	
	/** Set the agreement template ID.
	 *
	 * @param agreementTemplateID The ID of the agreement template.
	 * 
	 */
	public void setAgreementTemplateID(String agreementTemplateID) {
		this.agreementTemplateID = agreementTemplateID;
	}

	/** Set properties in the agreement template.
	 *
	 * @param properties The properties of the agreement template. 
	 * It's in a form of map from where value can be retrieved given the property ID.
	 * 
	 */
	public void setProperties(List<ISecurityProperty> properties) {
		this.properties = properties;
	}

	/** Add a new property in an agreement template.
	 *
	 * @param property The property that to be added. 
	 * It contains both ID and value. 
	 * 
	 */
	public void addProperty(ISecurityProperty property) {
		properties.add(property);
	}

	/** Remove a property from an agreement template.
	 *
	 * @param propertyID The ID that is used to find the property in the properties Map. 
	 * 
	 */
	public void removeProperty(String propertyID) {
		properties.remove(propertyID);
	}

	/** Output the content of the AgreementTemplate.
	 *
	 * @return A String that contains all the properties in the agreement template. This is currently not used.
	 * 			A simplified solution of getXML() is temporarily in use. 
	 * 
	 */
	public String toString(){
		String temp = "AgreementTemplateID: "+agreementTemplateID+"\n";
		for(ISecurityProperty property : properties){
			temp = temp + property.toString()+"\n";
		}
		return temp;
	}


	public void setSecurityProperties(List<ISecurityProperty> securityProperties) {
		properties = securityProperties;
		
	}
	
	public String[] getXmlContents() {
		return xmlContents;
	}

	public void setXmlContents(String[] xmlContents) {
		this.xmlContents = xmlContents;
	}	


}
