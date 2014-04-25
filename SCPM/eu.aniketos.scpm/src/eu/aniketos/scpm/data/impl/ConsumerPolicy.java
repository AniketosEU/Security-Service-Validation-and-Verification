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

import eu.aniketos.data.IConsumerPolicy;
import eu.aniketos.data.ISecurityProperty;

/**
 * This class implements the interfaces of IConsumerPolicy and also contains extra methods that used in Work Package 3. 
 * 
 * @author Bo Zhou, LJMU
 * @revised Bo Zhou LJMU Feb 2013
 */
public class ConsumerPolicy implements IConsumerPolicy{

	/**
	 * A List that contains all properties. .
	 */
	List<ISecurityProperty> properties;
	/**
	 * The XML content of a consumer policy. 
	 */
	private String xmlContent;
	
	private String[] xmlContents;
	
	/** 
	 * Constructor that initiates the property ID-value Map.
	 * 
	 */
	
	
	public ConsumerPolicy(){
		properties = new ArrayList<ISecurityProperty> ();
	}
	
	/** Set the XML content. It is an easy way to modify the consumer policy.
	 *
	 * @param xmlContent The content of a consumer policy in XML format.
	 * 
	 */
	public void setXML(String xmlContent)
	{
		this.xmlContent = xmlContent;
	}
	 
	/** Get the XML content of consumer policy. It temporarily used to replace method toString().
	 *
	 * @return The content of a consumer policy in XML format.
	 * 
	 */
	@Override
	public String getXML()
	{

		return xmlContent;
	}
	
	/** Get all properties in the consumer policy.
	 *
	 * @return All properties of the consumer policy. 
	 * It's saved in a list.
	 * 
	 */
	public List<ISecurityProperty> getProperties() {
		return properties;
	}

	/** Get the value of a property given its ID
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

	

	/** Add a new property in a consumer policy.
	 *
	 * @param property The property that to be added. It contains both ID and value. 
	 * 
	 */
	public void addProperty(ISecurityProperty property) {
		// TODO Auto-generated method stub
		properties.add(property);
	}

	/** Remove a property from a consumer policy.
	 *
	 * @param propertyID The ID that is used to find the property in the properties Map. 
	 * 
	 */
	public void removeProperty(String propertyID) {
		// TODO Auto-generated method stub
		properties.remove(propertyID);
	}
	
	public String[] getXmlContents() {
		return xmlContents;
	}

	public void setXmlContents(String[] xmlContents) {
		this.xmlContents = xmlContents;
	}	

}
