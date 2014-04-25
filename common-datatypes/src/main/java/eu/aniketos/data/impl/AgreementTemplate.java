/*
Copyright (c) 2014, Bernard Butler and Barry Mulcahy (Waterford Institute of Technology, Ireland), Project: FP7-ICT-257930 Aniketos
Copyright (c) 2014, David Lamb and Bo Zhou (Liverpool John Moores University, UK), Project: FP7-ICT-257930 Aniketos
Copyright (c) 2014, Kostas Giannakakis (ATC, Greece), Project: FP7-ICT-257930 Aniketos
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

 -  Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 -  Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 -  Neither the name of WATERFORD INSTITUTE OF TECHNOLOGY nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package eu.aniketos.data.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import eu.aniketos.data.ISecurityProperty;
import eu.aniketos.data.IAgreementTemplate;
import java.util.ArrayList;

public class AgreementTemplate implements IAgreementTemplate {

	private String agreementTemplateID;
	private Map<String, ISecurityProperty> properties;
	private String xmlContent;
	private String[] xmlContents;

	public AgreementTemplate(String agreementTemplateID) {
		this.agreementTemplateID = agreementTemplateID;
		properties = new HashMap<String, ISecurityProperty>();
	}

	public String[] getXmlContents() {
		return xmlContents;
	}

	public void setXmlContents(String[] xmlContents) {
		this.xmlContents = xmlContents;
	}	
	
	public String getAgreementTemplateID() {
		return agreementTemplateID;
	}

	@SuppressWarnings("unchecked")
	public List<ISecurityProperty> getProperties() {
		// TODO: This breaks in runtime.
        //return (List<ISecurityProperty>) properties;
        return new ArrayList<ISecurityProperty>();
	}

	public ISecurityProperty getProperty(String propertyID) {
		return properties.get(propertyID);
	}

	public void setAgreementTemplateID(String agreementTemplateID) {
		this.agreementTemplateID = agreementTemplateID;
	}

	public void setProperties(Map<String, ISecurityProperty> properties) {
		this.properties = properties;
	}

	public void addProperty(ISecurityProperty property) {
		properties.put(property.getPropertyID(), property);
	}

	public void removeProperty(String propertyID) {
		properties.remove(propertyID);
	}

	public String toString() {
		String temp = "AgreementTemplateID: " + agreementTemplateID + "\n";
		for (String propertyID : properties.keySet()) {
			temp = temp + properties.get(propertyID).toString() + "\n";
		}
		return temp;
	}

	public void setXML(String xmlContent) {
		this.xmlContent = xmlContent;
	}

	public String getXML() {
		return xmlContent;
	}

	public Set<ISecurityProperty> getSecurityProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, ISecurityProperty> getSecurityPropertyMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getServiceProviderID() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setSecurityProperties(Set<ISecurityProperty> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setSecurityPropertyMap(Map<String, ISecurityProperty> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServiceProviderID(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setSecurityProperties(List<ISecurityProperty> securityProperties) {
		// TODO Auto-generated method stub
		
	}
	
}
