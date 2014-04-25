/*
Copyright (c) 2013, Aneel Rahim, Bernard Butler and Arif Fareed (Waterford Institute of Technology, Ireland), Project: FP7-ICT-257930 Aniketos
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

 -  Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 -  Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 -  Neither the name of WATERFORD INSTITUTE OF TECHNOLOGY nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package eu.aniketos.spdm;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Vector;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.ReferencePolicy;
import org.apache.felix.scr.annotations.Service;

import eu.aniketos.data.ISecurityProperty;
import eu.aniketos.data.SPState;
import eu.aniketos.spdm.ds.api.ISPManager;

import org.osgi.service.component.ComponentContext;

@Component(name = "sp-lifecyclemanager-service")@Service
public class LifeCycleManager implements ISPManager{

	/**
	 * @author: Bernard Butler and M. Arif Fareed (TSSG)
	 */
	private static final long serialVersionUID = 1L;

	@Property(value = "Security Property Life Cycle Manager Service")
	static final String CONSTANT_NAME = "service.description";
	@Property(value = "Aniketos SPDM")
	static final String CONSTANT_NAME_1 = "service.vendor";
	@Property(value = "*")
	static final String CONSTANT_NAME_2 = "service.exported.interfaces";
	@Property(value = "org.apache.cxf.ws")
	static final String CONSTANT_NAME_3 = "service.exported.configs";
	@Property(value = "http://ec2-54-235-118-152.compute-1.amazonaws.com:9091/sp_life_cycle_manager")
	static final String CONSTANT_NAME_4 = "org.apache.cxf.ws.address";

	Queue<ISecurityProperty> job_queue;
	Vector<ISecurityProperty> trash;

	// HowTo Reference this classes in client application
	// @Reference(name = "agreement_template",
	// cardinality = ReferenceCardinality.OPTIONAL_MULTIPLE,
	// referenceInterface = IAgreementTemplate.class,
	// strategy = ReferenceStrategy.EVENT,
	// policy = ReferencePolicy.DYNAMIC,
	// bind = "bindAgreementTemplate",
	// unbind = "unbindAgreementTemplate")
	// private IAgreementTemplate agreement_template;

	// @Reference(name = "log",
	// cardinality = ReferenceCardinality.MANDATORY_UNARY,
	// referenceInterface = org.osgi.service.log.LogService.class,
	// policy = ReferencePolicy.STATIC,
	// bind = "bindLog",
	// unbind = "unbindLog")
	// private LogService log;
	// static Logger log = Logger.getLogger(HelloWorldServiceImp.class);

	/**
	 * Register AgreementTemplate service with OSGi container
	 * 
	 * @param context
	 *            Handle to declarative service
	 */
	@Activate
	protected void activateLifeCycleManager(ComponentContext context) {
		// log.debug("Activate ReferenceManager");

		System.out
				.println("Activate Security Property Life Cycle Manager Service");
		init();
		// Default Security Properties

		// log.log(LogService.LOG_INFO,
		// "Activate Agreement Template Component!");

	}

	/**
	 * UnRegister AgreementTemplate Service with OSCi container
	 * 
	 * @param context
	 */
	@Deactivate
	protected void deactivateLifeCycleManager(ComponentContext context) {
		// log.debug("Deactivate ReferenceManager");
		System.out
				.println("Deactivate Security Property Life Cycle Manager Service");
		// log.log(LogService.LOG_INFO,
		// "Deactivate Agreement Template Component!");
	}

	protected void init() {
		this.job_queue = new LinkedList<ISecurityProperty>();
		this.trash = new Vector<ISecurityProperty>();
	}

	public void push(ISecurityProperty sp) {
		this.job_queue.add(sp);
	}

	public ISecurityProperty pop() {
		return this.job_queue.poll();
	}

	public void run_manager() {

		ISecurityProperty sp;

		while (this.job_queue.isEmpty() == false) {

			sp = this.pop();

			switch (sp.getState()) {

				case UnBind: {
					// push to trash
					break;
				}
				case Bind: {
					// Do the verification
					break;
				}
				case Verified: {
					// Sign the property with certificate
					break;
				}
				case Signed: {
					// Check expiry of signed certificate
					break;
				}
			}
		}
	}

	public String toString() {
		StringBuffer bf = new StringBuffer();
		bf.append(this.job_queue);
		return bf.toString();
	}
}
