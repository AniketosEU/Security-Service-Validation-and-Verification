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


import javax.persistence.*;


import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;


//import org.hibernate.validator.Length;
//import org.hibernate.validator.NotNull;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import eu.aniketos.data.ISecurityDescriptor;
import eu.aniketos.data.ISecurityProperty;
import eu.aniketos.data.SPState;
import eu.aniketos.data.SPType;

import eu.aniketos.spdm.ds.api.IWebService;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.ReferencePolicy;
import org.apache.felix.scr.annotations.Service;

import org.osgi.service.component.ComponentContext;

//import org.osgi.service.log.LogService;
/**
 * 
 * @author: Bernard Butler and M. Arif Fareed (TSSG)
 *
 */
@Entity
@Table(name = "SECURITYPROPERTIES")
@Component(name="security-property-service")@Service
public class SecurityProperty implements Serializable, ISecurityProperty{
	
	/**
	 * @author: Bernard Butler and M. Arif Fareed (TSSG)
	 */
	private static final long serialVersionUID = 1L;
	
	@Property(value="Security Property Service")
	static final String CONSTANT_NAME = "service.description";
	@Property(value="Aniketos SPDM")
	static final String CONSTANT_NAME_1 = "service.vendor";
	
	@Property(value="*")
	static final String CONSTANT_NAME_2 = "service.exported.interfaces";
	@Property(value="org.apache.cxf.ws")
	static final String CONSTANT_NAME_3 = "service.exported.configs";
	@Property(value="http://ec2-54-235-118-152.compute-1.amazonaws.com:9091/spdm_securityproperty")
	static final String CONSTANT_NAME_4 = "org.apache.cxf.ws.address";

	//HowTo Reference this classes in client application
	// @Reference(name = "security_property",
	// cardinality = ReferenceCardinality.OPTIONAL_MULTIPLE,
	// referenceInterface = ISecurityProperty.class,
	// strategy = ReferenceStrategy.EVENT,
	// policy = ReferencePolicy.DYNAMIC,
	// bind = "bindSecurityProperty",
	// unbind = "unbindSecurityProperty")
	// private ISecurityProperty security_property;
	
//	@Reference(name = "log",
//	cardinality = ReferenceCardinality.MANDATORY_UNARY,
//	referenceInterface = org.osgi.service.log.LogService.class,
//	policy = ReferencePolicy.STATIC,
//	bind = "bindLog",
//	unbind = "unbindLog")
//    private LogService log;
	// static Logger log = Logger.getLogger(HelloWorldServiceImp.class);
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
    private int id;
    
	private String propertyID;
	
//	@Column(name = "sp_value")
	private String propertyValue;
	
//	@Column(name = "sp_date")
//	@Temporal(value=TemporalType.TIMESTAMP)
	private Date freshness;

	private X509Certificate certificate;
	
	private SPState propertyState;
	
	private SPType propertyType;

    @ManyToOne(optional = false)
	private IWebService service;
	//	@ManyToMany(mappedBy="securityProperties")
//	private Set<ISecurityDescriptor> agreementTemplates = new HashSet<ISecurityDescriptor>();

	/**
     * Register SecurityProperty service with OSGi container
	 * @param context
	 */
	@Activate
	protected void activateSecurityProperty(ComponentContext context) {
		// log.debug("Activate ReferenceManager");
		System.out.println("Activate Security Property Component");
//		log.log(LogService.LOG_INFO, "Activate Security Property Component!");

		// @TODO
	}

	/**
	 * UnRegister SecurityProperty Service with OSCi container 
	 * @param context
	 */
	@Deactivate
	protected void deactivateSecurityProperty(ComponentContext context) {
		// log.debug("Deactivate ReferenceManager");
		System.out.println("Deactivate Security Property Component");
//	    log.log(LogService.LOG_INFO, "Deactivate Security Property Component!");
	    
	    //@TODO
	}
	 
    //Binding signatures for this service for client applications
	// public void bindSecurityProperty(ISecurityProperty security_property) {
	// this.security_property = security_property;
	// System.out.println("Binding Service --- SecurityProperty");
	// }
	//
	// public void unbindSecurityProperty(ISecurityProperty security_property) {
	// this.security_property = null;
	// System.out.println("Unbinding Service --- SecurityProperty");
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
	
	/**
	 * Constructor for SecurityProperty
	 */
	public SecurityProperty(){
	}

	/**
	 * Overloaded Constructor for SecurityProperty
	 * @param propertyID
	 * @param value
	 * @param freshness
	 */
	public SecurityProperty(String propertyID, String value, IWebService service ,Date freshness, SPType type, SPState state){
		this.propertyID = propertyID;
		this.propertyValue = value;
		this.service = service;
		this.freshness = freshness;
		this.propertyType = type;
		this.propertyState = state;
	}
	
	/**
	 * Overloaded Constructor for SecurityProperty
	 * @param propertyID
	 * @param value
	 */
	public SecurityProperty(String propertyID, String value){
		this.propertyID = propertyID;
		this.propertyValue = value;
		this.freshness = null;
	}
	
	/**
	 * Security Property ID
	 * @param propertyID
	 */
	
	public SecurityProperty(String propertyID){
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
	public String getPropertyID(){
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
	public String getPropertyValue(){
		return this.propertyValue;
	}


	public void setPropertyValue(String propertyValue){
		this.propertyValue = propertyValue;
	}

    /**
     * Signed verfication date of SecurityProperty
     * @return
     */
    public Date getFreshness() {
        return this.freshness;
    }

	/**
	 * Signed date of SecurityProperty
	 * @param freshness
	 */
	public void setFreshness(Date freshness) {
		this.freshness = freshness;
	}


	public SPState getState() {
		return this.propertyState;
	}


	public void setState(SPState state) {
		this.propertyState =  state;
	}

	
	public X509Certificate getCertificate() {
		return this.certificate;
	}

	public void setCertificate(X509Certificate certificate) {
		this.certificate = certificate;
 	}

	
	/**
	 * Comparing instances of SecurityProperty. 
	 */
//	@Override
//	public boolean equals(Object o) {
//
//		if (this == o)
//			return true;
//		if (o == null || !(o instanceof SecurityProperty)) {
//
//			return false;
//		}
//
//		SecurityProperty other = (SecurityProperty) o;
//
//		/*
//		 * equivalence by propertyID
//		 */
//		SecurityProperty castOther = (SecurityProperty) other;
//		return new EqualsBuilder().append(propertyID, castOther.getPropertyID()).append(propertyValue,
//				castOther.getPropertyValue()).append(freshness, castOther.getFreshness()).isEquals();
//	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(441293447, 2056268651).append(propertyID).append(
				propertyValue).append(freshness).toHashCode();
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("propertyID: ", propertyID)
				.append("value: ", propertyValue).append("freshness: ", freshness).toString();
	}

}
