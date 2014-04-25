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

import java.util.Set;

import org.apache.felix.gogo.commands.Command;
import org.apache.felix.gogo.commands.Option;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.scr.annotations.Activate;

import org.apache.karaf.shell.console.OsgiCommandSupport;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferencePolicy;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.ReferenceStrategy;

import eu.aniketos.data.ISecurityDescriptor;
import eu.aniketos.data.ISecurityProperty;
import eu.aniketos.spdm.ds.api.ISPDMService;
import eu.aniketos.spdm.ds.api.ISPSRepository;

import org.osgi.framework.ServiceReference;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;



@Command(scope = "spdm", name = "cache", description="Print SPDM Cache Entries <Service, SecurityProperty>")
public class CacheCommand extends OsgiCommandSupport implements BundleActivator {

	 private ISPDMService spdm_service;
	
	 
    @Argument(index = 0, name = "arg", description = "The command argument", required = false, multiValued = false)
    String arg = null;

 //   private BundleContext bc;
	/**
	 * 
	 * @param BundleContext
	 */
	public void start(BundleContext context) throws Exception {
//		this.bc  = context;
	}
	
	/**
	 * 
	 */
	public void stop(BundleContext context) throws Exception {
//		this.bc = null;
	}
	
  //  public CacheCommand(Bundle)
    @Override
    protected Object doExecute() throws Exception {

    	// SPDM Service
	//	System.out.println("Bundle Contex: " + this.bc);
		ServiceReference spdm_serviceReference = this.bundleContext.getServiceReference(ISPDMService.class.getName());
		this.spdm_service = (ISPDMService) this.bundleContext.getService(spdm_serviceReference);
		//System.out.println("\n");
	//	System.out.println("SPDM Cache Size : "+ this.spdm_service.cache_size());
//		System.out.println("SPDM Cache @@@ ");
		//this.spdm_service.print_repository();
	//	System.out.println("\n");
		System.out.println("Registered Services %%%%: ");
		this.spdm_service.print_ws_entries();
	//	System.out.println("\n");
		
	//System.out.println("Registered Security Properties ***: ");
	//	this.spdm_service.print_sp_entries();
		
   
		return null;
    }
    

}
