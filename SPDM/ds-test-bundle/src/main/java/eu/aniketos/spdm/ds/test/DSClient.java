/*
Copyright (c) 2013, Aneel Rahim, Bernard Butler and Arif Fareed (Waterford Institute of Technology, Ireland), Project: FP7-ICT-257930 Aniketos
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

 -  Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 -  Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 -  Neither the name of WATERFORD INSTITUTE OF TECHNOLOGY nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package eu.aniketos.spdm.ds.test;
/**
 * Declarative Service Client
 * @author Bernard Butler and M. Arif Fareed (TSSG)
 * 
 */

import org.apache.felix.scr.annotations.Activate;
import org.osgi.service.component.ComponentContext;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferencePolicy;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.ReferenceStrategy;

import eu.aniketos.spdm.ds.api.ISPSRepository;

import eu.aniketos.spdm.ds.api.IWebService;

import eu.aniketos.data.ISecurityDescriptor;
import eu.aniketos.data.ISecurityProperty;

/**
 * 
 * @author Bernard Butler and M. Arif Fareed (TSSG)
 *
 */
@Component
public class DSClient {

	 @Reference(name = "security_desciptor",
	 cardinality = ReferenceCardinality.OPTIONAL_MULTIPLE,
	 referenceInterface = ISecurityDescriptor.class,
	 strategy = ReferenceStrategy.EVENT,
	 policy = ReferencePolicy.DYNAMIC,
	 bind = "bindSecurityDescriptor",
	 unbind = "unbindSecurityDescriptor")
	 private ISecurityDescriptor security_descriptor;
	 
	 @Reference(name = "security_property",
	 cardinality = ReferenceCardinality.OPTIONAL_MULTIPLE,
	 referenceInterface = ISecurityProperty.class,
	 strategy = ReferenceStrategy.EVENT,
	 policy = ReferencePolicy.DYNAMIC,
	 bind = "bindSecurityProperty",
	 unbind = "unbindSecurityProperty")
	 private ISecurityProperty security_property;

	 @Reference(name = "sps_repository",
	 cardinality = ReferenceCardinality.OPTIONAL_MULTIPLE,
	 referenceInterface = ISPSRepository.class,
	 strategy = ReferenceStrategy.EVENT,
	 policy = ReferencePolicy.DYNAMIC,
	 bind = "bindSPSRepository",
	 unbind = "unbindSPSRepository")
	 private ISPSRepository sps_repository;
			 
	 
	 @Reference(name = "web_service",
	 cardinality = ReferenceCardinality.OPTIONAL_MULTIPLE,
	 referenceInterface = IWebService.class,
	 strategy = ReferenceStrategy.EVENT,
	 policy = ReferencePolicy.DYNAMIC,
	 bind = "bindWebService",
	 unbind = "unbindWebService")
	 private IWebService web_service;
	 
	/**
     * Register DSClient as a service with OSGi container
	 * @param cc
	 */
	@Activate
	public void activate(ComponentContext cc) {
		
	/*	System.out.println("Calling Declarative Service: "
		+ this.security_descriptor);
		System.out.println("Calling Declarative Service: "
		+ this.security_property);

		this.sps_repository.registerService(this.web_service, this.security_descriptor);
		System.out.println("Repository Size : === "+ this.sps_repository.repository_size());
		System.out.println("+++ Printing SPS Repository +++: "+ this.sps_repository.lookUpSecurityProperty(this.web_service));
*/
	}

	 
	 /**
	  * Announcing AgreementTemplate as an OSGi Service
	  * @param agreement_template service
	  */
	//Binding & Unbinding AgreementTemplate
	 public void bindSecurityDescriptor(ISecurityDescriptor security_descriptor) {
		 this.security_descriptor = security_descriptor;
		// System.out.println("Binding Service --- SecurityDescriptor");
	 }
	
	 /**
	  * Unbinding AgreementTemplate service
	  * @param agreement_template
	  */
	 public void unbindSecurityDescriptor(ISecurityDescriptor security_descriptor) {
		 this.security_descriptor = null;
		// System.out.println("Unbinding Service --- SecurityDescriptor");
	 }  	 
	 
	 /**
	  * Announcing SecurityProperty as an OSGi Service 
	  * @param security_property service
	  */
	 //Binding & Unbinding SecurityProperty 
	 public void bindSecurityProperty(ISecurityProperty security_property) {
		 this.security_property = security_property;
		// System.out.println("Binding Service --- SecurityProperty");
	 }
	
	 /**
	  * Unbinding SecurityProperty service
	  * @param security_property
	  */
	 public void unbindSecurityProperty(ISecurityProperty security_property) {
		 this.security_property = null;
		// System.out.println("Unbinding Service --- Security Property");
	 }  
	 
	 /**
	  * Announcing SecurityProperty as an OSGi Service 
	  * @param security_property service
	  */
	 //Binding & Unbinding SecurityProperty 
	 public void bindWebService(IWebService web_service) {
		 this.web_service = web_service;
		// System.out.println("Binding Service --- Web Serive");
	 }
	
	 /**
	  * Unbinding SecurityProperty service
	  * @param security_property
	  */
	 public void unbindWebService(IWebService web_service) {
		 this.web_service = null;
		// System.out.println("Unbinding Service --- Web Service");
	 }  
	 
	 /**
	  * Announcing ServiceCentre as an OSGi Service
	  * @param service_centre
	  */
	 //Binding & Unbinding ServiceCentre
	 public void bindSPSRepository(ISPSRepository sps_repository) {
		 this.sps_repository = sps_repository;
	//	 System.out.println("Binding Service --- SPS Repository");
		 		 
//	 System.out.println("SPS Repository Size ======:" + this.sps_repository.repository_size());
		
	 }
	
	 /**
	  * Unbinding ServiceCentre
	  * @param service_centre
	  */
	 public void unbindSPSRepository(ISPSRepository sps_repository) {
		 this.sps_repository = null;
	//	 System.out.println("Unbinding Service --- SPS Repository");
	 }  
}
