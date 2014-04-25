/*
Copyright (c) 2013, Aneel Rahim,Bernard Butler and Arif Fareed (Waterford Institute of Technology, Ireland), Project: FP7-ICT-257930 Aniketos
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

 -  Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 -  Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 -  Neither the name of WATERFORD INSTITUTE OF TECHNOLOGY nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package eu.aniketos.spdm.ds.impl;

import javax.persistence.*;

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

@Entity
@Table(name = "SERVICES")
@Component(name="web-service")@Service
public class WebService implements IWebService {

	private static final long serialVersionUID = 1L;
	
	@Property(value="Web Service")
	static final String CONSTANT_NAME = "service.description";
	@Property(value="Aniketos SPDM")
	static final String CONSTANT_NAME_1 = "service.vendor";
	
	@Property(value="*")
	static final String CONSTANT_NAME_2 = "service.exported.interfaces";
	@Property(value="org.apache.cxf.ws")
	static final String CONSTANT_NAME_3 = "service.exported.configs";
	@Property(value="http://ec2-54-235-118-152.compute-1.amazonaws.com:9091/web-service")
	static final String CONSTANT_NAME_4 = "org.apache.cxf.ws.address";
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;

	private String serviceID;
	
	
	//HowTo Reference this classes in client application
	// @Reference(name = "service",
	// cardinality = ReferenceCardinality.OPTIONAL_MULTIPLE,
	// referenceInterface = IService.class,
	// strategy = ReferenceStrategy.EVENT,
	// policy = ReferencePolicy.DYNAMIC,
	// bind = "bindSecurityProperty",
	// unbind = "unbindService")
	// private IService service;
	
//	@Reference(name = "log",
//	cardinality = ReferenceCardinality.MANDATORY_UNARY,
//	referenceInterface = org.osgi.service.log.LogService.class,
//	policy = ReferencePolicy.STATIC,
//	bind = "bindLog",
//	unbind = "unbindLog")
//    private LogService log;
	
// static Logger log = Logger.getLogger(HelloWorldServiceImp.class);
	
	/**
     * Register SecurityProperty service with OSGi container
	 * @param context
	 */
	@Activate
	protected void activateWebService(ComponentContext context) {
		// log.debug("Activate ReferenceManager");
		System.out.println("Activate Service Component");
//		log.log(LogService.LOG_INFO, "Activate Security Property Component!");

		// @TODO
	}

	/**
	 * UnRegister SecurityProperty Service with OSCi container 
	 * @param context
	 */
	@Deactivate
	protected void deactivateWebService(ComponentContext context) {
		// log.debug("Deactivate ReferenceManager");
		System.out.println("Deactivate ServiceComponent");
//	    log.log(LogService.LOG_INFO, "Deactivate Security Property Component!");
	    this.serviceID = "http:://localhost?default";
	    //@TODO
	}
	 
	public WebService() {
		this.serviceID = "http://default.url";
	}
	
    /**
     * 
     * @return
     */
    public int getId() {
        return id;
    }
    
	//ID is URI
	public void setServiceID(String id) {
		 this.serviceID =  id;
	}	

	
	//ID is URI
	public String getServiceID() {
		return this.serviceID;
	}	


	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.serviceID);
		return sb.toString();
	}
	
}
