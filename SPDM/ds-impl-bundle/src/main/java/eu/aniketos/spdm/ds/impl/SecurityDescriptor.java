/*
Copyright (c) 2013, Aneel Rahim, Bernard Butler and Arif Fareed (Waterford Institute of Technology, Ireland), Project: FP7-ICT-257930 Aniketos
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

 -  Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 -  Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 -  Neither the name of WATERFORD INSTITUTE OF TECHNOLOGY nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package eu.aniketos.spdm.ds.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.JoinTable;

//import org.hibernate.validator.Length;
//import org.hibernate.validator.NotNull;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.ReferencePolicy;
import org.apache.felix.scr.annotations.Service;

import eu.aniketos.data.ISecurityProperty;
import eu.aniketos.data.ISecurityDescriptor;
import eu.aniketos.data.SPState;
import eu.aniketos.data.SPType;


import eu.aniketos.spdm.ds.api.IWebService;

//import org.apache.log4j.Logger;
import org.osgi.service.component.ComponentContext;
//import org.osgi.service.log.LogService;

/**
 * Security Descriptor is the implementation of ISecurityDescriptor interface.
 * An Security Descriptor is a collection of security properties. This class
 * provider the necessary methods to manipulate SecurityProperty
 * 
 * @author: Bernard Butler and M. Arif Fareed (TSSG)
 *
 */
@Component(name="securitydescriptor-service")@Service
public class SecurityDescriptor implements Serializable, ISecurityDescriptor {
	
	/**
	 * @author: Bernard Butler and M. Arif Fareed (TSSG)
	 */
	private static final long serialVersionUID = 1L;

	@Property(value="Security Descriptor Service")
	static final String CONSTANT_NAME = "service.description";
	@Property(value="Aniketos SPDM")
	static final String CONSTANT_NAME_1 = "service.vendor";
	@Property(value="*")
	static final String CONSTANT_NAME_2 = "service.exported.interfaces";
	@Property(value="org.apache.cxf.ws")
	static final String CONSTANT_NAME_3 = "service.exported.configs";
	@Property(value="http://ec2-54-235-118-152.compute-1.amazonaws.com:9091/spdm_securitydescriptor")
	static final String CONSTANT_NAME_4 = "org.apache.cxf.ws.address";

	//HowTo Reference this classes in client application
	// @Reference(name = "agreement_template",
	// cardinality = ReferenceCardinality.OPTIONAL_MULTIPLE,
	// referenceInterface = IAgreementTemplate.class,
	// strategy = ReferenceStrategy.EVENT,
	// policy = ReferencePolicy.DYNAMIC,
	// bind = "bindAgreementTemplate",
	// unbind = "unbindAgreementTemplate")
	// private IAgreementTemplate agreement_template;
	
//	@Reference(name = "log",
//	cardinality = ReferenceCardinality.MANDATORY_UNARY,
//	referenceInterface = org.osgi.service.log.LogService.class,
//	policy = ReferencePolicy.STATIC,
//	bind = "bindLog",
//	unbind = "unbindLog")
//    private LogService log;
	// static Logger log = Logger.getLogger(HelloWorldServiceImp.class);
	
	@Id
	@Column(name = "at_id")
	private String agreementTemplateID;
	
	@Column(name = "at_serviceProvider_id")
	private String serviceProviderID;
	
	@JoinTable(name="FAT_sp_at", joinColumns = {@JoinColumn(name = "at_id")}, inverseJoinColumns = {@JoinColumn(name = "sp_id")})
	
	
    private List<ISecurityProperty> securityPropertyList;
	//There will be a certificate object here in the future.
	
	private String conSpec_code;
	
    /**
     * Register AgreementTemplate service with OSGi container
     * @param context Handle to declarative service
     */
	@Activate
	protected void activateSecurityDescriptor(ComponentContext context) {
		// log.debug("Activate ReferenceManager");
		
		System.out.println("Activate Security Descriptor Component");
		init();
		//Default Security Properties
		
//		log.log(LogService.LOG_INFO, "Activate Agreement Template Component!");

	}
	
	/**
	 * UnRegister AgreementTemplate Service with OSCi container 
	 * @param context
	 */
	@Deactivate
	protected void deactivateSecurityDescriptor(ComponentContext context) {
		// log.debug("Deactivate ReferenceManager");
		System.out.println("Deactivate Security Descriptor Component");
//	    log.log(LogService.LOG_INFO, "Deactivate Agreement Template Component!");
	}
	 
    //Binding signatures for this service for client applications
	// public void bindAgreementTemplate(IAgreementTemplate agreement_template) {
	// this.agreement_tempalte = agreement_template;
	// System.out.println("Binding Service --- AgreementTemplate");
	// }
	//
	// public void unbindAgreementTemplate(IAgreementTemplate agreement_template) {
	// this.agreement_template = null;
	// System.out.println("Unbinding Service --- AgreementTemplate");
	// }  
    
//	protected void bindLog(LogService log)
//	{
//	    this.log = log;
//	}
//
//	protected void unbindLog(LogService log)
//	{
//	    this.log = null;
//	}
    


//	public abstract ISecurityProperty getProperty(String propertyID);
	
//	public abstract void addProperty(ISecurityProperty property);

//	public abstract void removeProperty(String propertyID);

	public SecurityDescriptor(){
		init();
	}

	private void init() {
		this.securityPropertyList = new  ArrayList<ISecurityProperty>();
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(1900 + 120, 20, 10);
		Date d = cal.getTime();
		
		IWebService s1 = new WebService();
		
		s1.setServiceID("google.com");
		
		ISecurityProperty p1 = new SecurityProperty("11", "True", s1, d, SPType.BoD, SPState.Bind);
		ISecurityProperty p2 = new SecurityProperty("12", "True", s1, d, SPType.SoD, SPState.Bind);
		ISecurityProperty p3 = new SecurityProperty("13", "True", s1, d, SPType.Confidentiality, SPState.Bind);
		
	this.securityPropertyList.add(p1);
	this.securityPropertyList.add(p2);
	this.securityPropertyList.add(p3);
	}
	



	public List<ISecurityProperty> getProperties(){
		return this.securityPropertyList;
	}

	public ISecurityProperty getProperty(String propertyID) {
		return null;
	}
	
	public void addProperty(ISecurityProperty property) {
		this.securityPropertyList.add(property);
	}

	public void removeProperty(String propertyID){
		
	}
	
	/**
	 * Comparision object for comparing AgreementTemplate instances
	 */
//	@Override
//	public boolean equals(Object o) {
//
//		if (this == o)
//			return true;
//		if (o == null || !(o instanceof SecurityDescriptor)) {
//			return false;
//		}
//
//		SecurityDescriptor other = (SecurityDescriptor) o;
//
//		/*
//		 * equivalence by agreementTemplateID
//		 */
//		SecurityDescriptor castOther = (SecurityDescriptor) other;
//		EqualsBuilder eqBuilder = new EqualsBuilder().append(agreementTemplateID, castOther.getAgreementTemplateID());
//		for (String propertyID:securityPropertyMap.keySet()) {
//			eqBuilder.append(securityPropertyMap.get(propertyID), castOther.getProperty(propertyID));
//		}
//		return eqBuilder.isEquals();
//	}

//	@Override
//	public int hashCode() {
//		HashCodeBuilder hcBuilder = new HashCodeBuilder(441293447, 2056268651).append(agreementTemplateID);
//		for (String propertyID:securityPropertyMap.keySet()) {
//			hcBuilder.append(securityPropertyMap.get(propertyID));
//		}
//		return hcBuilder.toHashCode();
//	}

	@Override
	public String toString() {
		ToStringBuilder tsBuilder = new ToStringBuilder(this).append("Security Descriptor: ");
//		for (String propertyID:securityPropertyMap.keySet()) {
//			tsBuilder.append("propertyID: ", securityPropertyMap.get(propertyID));
//		}
		return tsBuilder.toString();
	}

	
}
