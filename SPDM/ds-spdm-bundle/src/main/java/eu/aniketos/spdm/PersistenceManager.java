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

import eu.aniketos.data.ISecurityDescriptor;
import eu.aniketos.data.ISecurityProperty;
import eu.aniketos.spdm.ds.api.ISPSRepository;
import eu.aniketos.spdm.ds.api.IWebService;
import eu.aniketos.spdm.ds.impl.BidiMultiHashMap.EntrySet;
import eu.aniketos.spdm.ds.api.IPersistenceManager;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.ReferencePolicy;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.ReferenceStrategy;

import org.osgi.service.component.ComponentContext;

import org.osgi.service.jpa.EntityManagerFactoryBuilder;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceProvider;

@Component(name = "persistence-manager-service")@Service
public class PersistenceManager implements IPersistenceManager {

	/**
	 * @author: Bernard Butler and M. Arif Fareed (TSSG)
	 */

	private static final long serialVersionUID = 1L;

	@Property(value = "Persistence Manager Service")
	static final String CONSTANT_NAME = "service.description";
	@Property(value = "Aniketos SPDM")
	static final String CONSTANT_NAME_1 = "service.vendor";
	@Property(value = "*")
	static final String CONSTANT_NAME_2 = "service.exported.interfaces";
	@Property(value = "org.apache.cxf.ws")
	static final String CONSTANT_NAME_3 = "service.exported.configs";
	@Property(value = "http://ec2-54-235-118-152.compute-1.amazonaws.com:9091/persistence_manager")
	static final String CONSTANT_NAME_4 = "org.apache.cxf.ws.address";
	
	private static final String SECURITY_PROPERTY_UNIT = "jpa.sps";


	// HowTo Reference this classes in client application
	// @Reference(name = "persistence_manager",
	// cardinality = ReferenceCardinality.OPTIONAL_MULTIPLE,
	// referenceInterface = IPersistenceManager.class,
	// strategy = ReferenceStrategy.EVENT,
	// policy = ReferencePolicy.DYNAMIC,
	// bind = "bindPersistenceManager",
	// unbind = "unbindPersistenceManagre")
	// private IPersistenceManager persistence_manager;
	
	private EntityManagerFactory entityManagerFactory;
	private BundleContext bc;

	@Activate
	protected void activatePersistenceManager(ComponentContext componentContext) {
		this.bc = componentContext.getBundleContext();

	}

	@Deactivate
	protected void deactivatePersistenceManager(ComponentContext componentContext) {
		if (this.entityManagerFactory != null
				&& this.entityManagerFactory.isOpen()) {
			this.entityManagerFactory.close();
		}
	}

	/**
	 * Local method to obtain an EntityManager Reference for persistence.
	 * 
	 * @return
	 * @throws Exception
	 */
	private ServiceReference getEntityManagerFactoryServiceReference()
			throws Exception {
		ServiceReference[] serviceReferences = this.bc.getServiceReferences(
				EntityManagerFactory.class.getName(), String.format("(%s=%s)",
						EntityManagerFactoryBuilder.JPA_UNIT_NAME,
						SECURITY_PROPERTY_UNIT));
		if (serviceReferences != null && serviceReferences.length > 0) {
			return serviceReferences[0];
		} else {
			throw new Exception("EntityManagerFactory is not available");
		}
	}

	public void saveService(IWebService service) {
    	
	    
	    try {

	        ServiceReference reference = getEntityManagerFactoryServiceReference();
	        
		     try {   
		        EntityManagerFactory emf = (EntityManagerFactory) this.bc.getService(reference);
	
				EntityManager em = emf.createEntityManager();     	
			    EntityTransaction transaction = em.getTransaction();
		
			    try {
			    	transaction.begin();
		        
			    	em.persist(service);
			    	transaction.commit();
		        
			    	System.out.println("Service is persisted with ID: " + service.getServiceID());
	               } catch (Exception e) {
	                   transaction.rollback();
	                   e.printStackTrace();
	               }
			    em.close();
		     } finally {
		    	 this.bc.ungetService(reference);
		     } 
		  } catch(Exception ex) {
	    	ex.printStackTrace();
	    }
    }

	public void saveSecurityProperty(ISecurityProperty sp) {
           
     try{
    	 
           ServiceReference reference = getEntityManagerFactoryServiceReference();
           
           try{
               EntityManagerFactory emf = (EntityManagerFactory) this.bc.getService(reference);

        	   EntityManager em = emf.createEntityManager();     	
        	   EntityTransaction transaction = em.getTransaction();

        	   try{
        		   transaction.begin();                                                        
        		   em.persist(sp);
        		   transaction.commit();
           
        		   System.out.println("Security Property is persisted with ID: " + sp.getPropertyID());
               } catch (Exception e) {
                   transaction.rollback();
                   e.printStackTrace();
               }
        	   em.close();
           } finally {
        	   this.bc.ungetService(reference);
           } 
     } catch(Exception ex) {
    	 ex.printStackTrace();
     	}

    }
}
