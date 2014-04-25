/*
Copyright (c) 2012, Bernard Butler and Arif Fareed (Waterford Institute of Technology, Ireland), Project: FP7-ICT-257930 Aniketos
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

 -  Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 -  Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 -  Neither the name of WATERFORD INSTITUTE OF TECHNOLOGY nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package eu.aniketos.scpm.impl.client;

import java.security.cert.X509Certificate;
import java.util.Date;

import eu.aniketos.data.ISecurityProperty;
import eu.aniketos.data.IWebService;
import eu.aniketos.data.SPState;
import eu.aniketos.data.SPType;


/**
 * 
 * @author: Bernard Butler and M. Arif Fareed (TSSG)
 * 
 */
public class SecurityProperty implements ISecurityProperty {

	/**
	 * @author: Bernard Butler and M. Arif Fareed (TSSG)
	 */
	private static final long serialVersionUID = 1L;

	static final String CONSTANT_NAME = "service.description";
	static final String CONSTANT_NAME_1 = "service.vendor";

	static final String CONSTANT_NAME_2 = "service.exported.interfaces";
	static final String CONSTANT_NAME_3 = "service.exported.configs";
	static final String CONSTANT_NAME_4 = "org.apache.cxf.ws.address";

	private int id;

	private String propertyID;

	private String propertyValue;

	private Date freshness;

	private X509Certificate certificate;

	private SPState propertyState;

	private SPType propertyType;

	private IWebService service;

	/**
	 * Constructor for SecurityProperty
	 */
	public SecurityProperty() {
	}

	/**
	 * Overloaded Constructor for SecurityProperty
	 * 
	 * @param propertyID
	 * @param value
	 * @param freshness
	 */
	public SecurityProperty(String propertyID, String value,
			IWebService service, Date freshness, SPType type, SPState state) {
		this.propertyID = propertyID;
		this.propertyValue = value;
		this.service = service;
		this.freshness = freshness;
		this.propertyType = type;
		this.propertyState = state;
	}

	/**
	 * Overloaded Constructor for SecurityProperty
	 * 
	 * @param propertyID
	 * @param value
	 */
	public SecurityProperty(String propertyID, String value) {
		this.propertyID = propertyID;
		this.propertyValue = value;
		this.freshness = null;
	}

	/**
	 * Security Property ID
	 * 
	 * @param propertyID
	 */

	public SecurityProperty(String propertyID) {
		this.propertyID = propertyID;
		this.propertyValue = null;
		this.freshness = null;
	}

	public int getId() {
		return id;
	}

	/**
	 * 
	 * @return
	 */
	public String getPropertyID() {
		return propertyID;
	}

	/**
	 * 
	 * @param propertyID
	 */
	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}

	/**
	 * 
	 * @return
	 */
	public String getPropertyValue() {
		return this.propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

	/**
	 * Signed verfication date of SecurityProperty
	 * 
	 * @return
	 */
	public Date getFreshness() {
		return this.freshness;
	}

	/**
	 * Signed date of SecurityProperty
	 * 
	 * @param freshness
	 */
	public void setFreshness(Date freshness) {
		this.freshness = freshness;
	}

	public SPState getState() {
		return this.propertyState;
	}

	public void setState(SPState state) {
		this.propertyState = state;
	}

	public X509Certificate getCertificate() {
		return this.certificate;
	}

	public void setCertificate(X509Certificate certificate) {
		this.certificate = certificate;
	}

}
