/*
Copyright (c) 2013, Aneel Rahim, Bernard Butler and Arif Fareed (Waterford Institute of Technology, Ireland), Project: FP7-ICT-257930 Aniketos
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

 -  Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 -  Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 -  Neither the name of WATERFORD INSTITUTE OF TECHNOLOGY nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package eu.aniketos.spdm.console;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

import java.io.File;
import java.net.URL;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import eu.aniketos.data.ISecurityDescriptor;
import eu.aniketos.data.ISecurityProperty;
import eu.aniketos.data.SPState;
import eu.aniketos.data.SPType;
import eu.aniketos.data.ICompositionPlan;
import eu.aniketos.data.IConsumerPolicy;
import eu.aniketos.marketplace.*;
import eu.aniketos.spdm.ds.api.ISPDMService;
import eu.aniketos.spdm.ds.api.IWebService;
import org.osgi.framework.ServiceReference;
import eu.aniketos.spdm.ds.api.ISPSRepository;





import eu.aniketos.trustworthiness.ext.messaging.ITrustworthinessPrediction;
import eu.aniketos.trustworthiness.ext.messaging.Trustworthiness;
import eu.aniketos.trustworthiness.ext.messaging.ISecurityPropertiesService;

/**
 * Console Command Activator class which registers a list of Commands 
 * as properties with OSGi containers (Felix, Equinox, knopflerfish)
 * @author Bernard Butler and M. Arif Fareed (TSSG)
 *
 */
public class Activator implements BundleActivator {
	
    private ServiceRegistration serviceRegistration;

    private boolean isActive;
    
    private ServiceTracker trackerMarketplace;
    private ServiceTracker trackerTrustworthinessPrediction;
    private ServiceTracker trackerPropertiesService;
 //   private ServiceTracker trackerCSSTM;
    
    private ISecurityDescriptor security_descriptor;
	private ISecurityProperty security_property;
//	private ISPSRepository sps_repository;	
	private IWebService web_service;

	private ISPDMService spdm_service;
	private ITrustworthinessPrediction trust_service;
	
    /**
     * Register Console Command and notify user
     * BundleContext context
     */
    public void start(BundleContext context) throws Exception {
    	
    	System.out.println("++ SPDM Console Client Service Registered ++");
    	int temp=0;
    	// SecurityDescriptor
    			ServiceReference security_descriptor_serviceReference = context.getServiceReference(ISecurityDescriptor.class.getName());
    			this.security_descriptor = (ISecurityDescriptor) context.getService(security_descriptor_serviceReference);
    			System.out.println("Calling Declarative service using Bundle: "
    					+ this.security_descriptor);
    	
    			// SecurityProperty
    			ServiceReference security_property_serviceReference = context.getServiceReference(ISecurityProperty.class.getName());
    			this.security_property = (ISecurityProperty) context.getService(security_property_serviceReference);
    			System.out.println("Calling Declarative service using Bundle: "
    					+ this.security_property);
    	
    	
    			// WebService
    			ServiceReference webservice_serviceReference = context.getServiceReference(IWebService.class.getName());
    			this.web_service = (IWebService) context.getService(webservice_serviceReference);
    			System.out.println("Calling Declarative service using Bundle: "
    					+ this.web_service);
    			
    			// SPDM Service
    			ServiceReference spdm_serviceReference = context.getServiceReference(ISPDMService.class.getName());
    			this.spdm_service = (ISPDMService) context.getService(spdm_serviceReference);
    			System.out.println("Calling Declarative service using Bundle: "
    					+ this.spdm_service);
    			
    		/*	ServiceReference trust_serviceReference = context.getServiceReference(ITrustworthinessPrediction.class.getName());
    			trust_service = (ITrustworthinessPrediction) context.getService(trust_serviceReference);
    			System.out.println("Calling Declarative service using Bundle: "
    					+ this.trust_service);
    		*/

        
        
		
		
	//Creating Service Reference for Marketplace and TM
		
		
        trackerTrustworthinessPrediction = new ServiceTracker(context,ITrustworthinessPrediction.class.getName(), null);
        trackerTrustworthinessPrediction.open();
		
        trackerPropertiesService = new ServiceTracker(context,ISecurityPropertiesService.class.getName(), null);
        trackerPropertiesService.open();
		
		trackerMarketplace = new ServiceTracker(context, 
				IMarketplace.class.getName(), null);
		trackerMarketplace.open();
		
		
	//	trackerCSSTM = new ServiceTracker(context,ICSSTM.class.getName(), null);
     //  trackerCSSTM.open();
		
		
							
       
       // = new CompositionPlan("");
   					//Connecting to CSSTM
       
       //IConsumerPolicy policy = new ConsumerPolicy();
	   //ICompositionPlan compositionPlan = new CompositionPlan("<?xml version=\"1.0\" encoding=\"UTF-8\"?><bpmn2:definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmn2=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" id=\"__qGLsHWwEeKGRoZvDGwPaQ\"><bpmn2:process id=\"_theProcess\"><bpmn2:startEvent id=\"_theStart\"/><bpmn2:serviceTask id=\"spdmTestService\"/><bpmn2:endEvent id=\"_theEnd\"/></bpmn2:process></bpmn2:definitions>");
       
      // ICSSTM csstm_obj = (ICSSTM) trackerCSSTM.getService();
      //csstm_obj.sendUserCredentials("service","a","b");
   
      //csstm_obj.requestSecurityTest (iplan);
       
       				//Connecting to Marketplace
		
					System.out.println("");
					System.out.println("Trying to connect Marketplace...\n ");
					IMarketplace marketplaceService = 
							(IMarketplace) trackerMarketplace.getService();						
					if (marketplaceService != null) {
							try {
								System.out.println("\n Successfully connectted to  Marketplace ");		
								MarketplaceSecurityDescriptor desc  = marketplaceService.getSecurityDescriptor(null,"http://83.235.133.36/AniketosAlternateWS/DoUPModuleSoapHttpPort?wsdl");	
								System.out.println("\n SPDM Getting Security Descriptor from Marketplace"   + desc);					
								List<ServiceDescriptor> services = marketplaceService.discoverService(null, null);
								System.out.println(" \n SPDM Getting Webservices from Marketplace");
							
						
								//Register it in SPDM
								System.out.println(" \n Register  Webservices in SPDM");
								for(ServiceDescriptor service: services) {
									System.out.println(service.getId());
									web_service.setServiceID(""+service.getId());
									spdm_service.registerService(web_service, security_descriptor);	}																								
						        }
							catch (Exception ex) {
							System.out.println("Error: " + ex.getMessage()); }			
					}
					else 
						System.out.println("Marketplace not found!");
					
		
					
					//creating some dummy data to send to TM
					
	Map<String, String> securityProperty = new HashMap<String, String>();	
	securityProperty.put("serviceId", "99");
	securityProperty.put("property", "aa");
	securityProperty.put("value", "143");
	securityProperty.put("type", "metric");
					
					// Connecting to TM
	
	System.out.println("Trying to connect Trustworthiness Module...\n ");
	ITrustworthinessPrediction predictionService = (ITrustworthinessPrediction) trackerTrustworthinessPrediction.getService();
	ISecurityPropertiesService propertyService = (ISecurityPropertiesService) trackerPropertiesService.getService();
	System.out.println("prediction service..." + predictionService);
	System.out.println("security properity service..." + propertyService);
	
					//Sending Data to TM
	
	if (propertyService != null) {
			try {
			System.out.println("Successfully connected to Security Property Service of Trustworthiness");
			propertyService.receiveProperty(securityProperty);
			System.out.println("Successfully Sending Data to TM");
			}
		catch (Exception e){
			System.out.println("Error in sending data to TM : " + e.getMessage());}
	}
	else 
		System.out.println("Security Property Service of Trustworthiness is null");

					//Getting Data from TM	
	if (predictionService != null) {
			try {
				System.out.println("Successfully connected to Prediction service of Trustworthiness");
				Trustworthiness trustworthiness = predictionService.getTrustworthiness("http://83.235.133.36/AniketosWS/DoUPModuleSoap12HttpPort?wsdl");
				if (trustworthiness != null) {
					System.out.println("Getting some data from Trustworthiness");
					System.out.println("WebService ID  " + trustworthiness.getServiceId());
					System.out.println("TrustworthinessScore " +trustworthiness.getTrustworthinessScore());
				}
				else 
				System.out.println("Trustworthiness is not found");
				}
			catch (RuntimeException ex) {
				System.out.println("Error: " + ex.getMessage());}
	}
	else 
		System.out.println("Prediction service of trustworthiness not found!");
			
		
	
	
	
	Dictionary<String, Object> props = new Hashtable<String, Object>();
    props.put("org.knowhowlab.osgi.shell.group.id", "spdm");
    props.put("org.knowhowlab.osgi.shell.group.name", " commands");
    props.put("org.knowhowlab.osgi.shell.commands", new String[][]{
    		{"lssp", "lssp,lssp - List registered Security Properties."},
    			{"lsws", "lsws,lsws - List registered services."},
    				{"unregister", "unregister,unregister - unregister services."},
    					{"delsp", "delsp,delsp <id> - delete Security Property."},
    					{"getsp", "getsp,getsp <id> - search Security Property by ID."},
    					{"getws", "getws,getws <id> - search Service by ID."},
    					{"register","register,register - registering Service & Security Descriptor."},
    							{"cache","cache,cache - Nr. of Entries in SPDM Repository."},
    							{"persist","persist,persist - Persisting the Entries of SPDM."},
    							{"update","update,update - update the security properties in SPDM"},
    								{"commands","commands,commands - List supported commands."}});
	
	serviceRegistration = context.registerService(CommandLineService.class.getName(), new CommandLineService(context), props);	
			       	
					
    }
    /**
     * Stop bundle of Command Class
     * BundleContext context
     */
    public void stop(BundleContext context) throws Exception {
        serviceRegistration.unregister();
        trackerMarketplace.close();
        trackerTrustworthinessPrediction.close();
    	trackerPropertiesService.close();
    }
}
