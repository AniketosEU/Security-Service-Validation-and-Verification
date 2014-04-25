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

import eu.aniketos.data.IConsumerPolicy;
import eu.aniketos.data.ISecurityProperty;

public class ConsumerPolicy implements IConsumerPolicy {

	private String xmlContent;
	private String[] xmlContents;
	Map<String, ISecurityProperty> properties;
	
	public String[] getXmlContents() {
		return xmlContents;
	}

	public void setXmlContents(String[] xmlContents) {
		this.xmlContents = xmlContents;
	}	

	public ConsumerPolicy() {
		properties = new HashMap<String, ISecurityProperty>();
	}

	public void setXML(String xmlContent) {
		this.xmlContent = xmlContent;
	}

	public String getXML() {
		return xmlContent;
	}

	public void addProperty(ISecurityProperty arg0) {
		// TODO Auto-generated method stub
		
	}

	public List<ISecurityProperty> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	public ISecurityProperty getProperty(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeProperty(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setProperties(Map<String, ISecurityProperty> arg0) {
		// TODO Auto-generated method stub
		
	}

}
