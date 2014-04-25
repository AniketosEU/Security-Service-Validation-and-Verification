/**
 * Copyright 2014 Athens Technology Centre SA <http://www.atc.gr/>
 * Aniketos Project FP7-ICT-257930 <http://www.aniketos.eu>
 * Konstantinos Giannakakis <k.giannakakis@atc.gr>
 * Vasilis Tountopoulos <v.tountopoulos@atc.gr>
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either 
 * version 3 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package eu.aniketos.marketplace;

import eu.aniketos.data.ICompositionPlan;

/**
 * Marketplace Announcement. Encapsulates parameters used during service registration.
 * @author Kostas Giannakakis
 *
 */
public class MarketplaceAnnouncement {

	/** Service's registry */
	private String registry;

	/** The user who registers the service */
	private String sender;

	/** Service's descriptor */
	private ServiceDescriptor serviceDescriptor;

	/** Service's security descriptor */
	private MarketplaceSecurityDescriptor securityDescriptor;

	/** Service's composition plans */
	private ICompositionPlan[] compositionPlans;

	/** Rules associated with a composite service */
	private String rules;
	
	/**
	 * Retrieves the service's registry
	 * @return Service's registry
	 */
	public String getRegistry() {
		return registry;
	}

	/**
	 * Sets the service's registry
	 * @param registry Service's registry
	 */
	public void setRegistry(String registry) {
		this.registry = registry;
	}

	/**
	 * Retrieves the user who registered the service
	 * @return The user who registered the service
	 */
    public String getSender() {
        return sender;
    }

    /**
     * Sets the user who registered the service
     * @param sender The user who registered the service
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * Retrieves the service's descriptor
     * @return The service's descriptor
     */
    public ServiceDescriptor getServiceDescriptor() {
        return serviceDescriptor;
    }

    /**
     * Sets the service's descriptor
     * @param serviceDescriptor The service's descriptor
     */
    public void setServiceDescriptor(ServiceDescriptor serviceDescriptor) {
        this.serviceDescriptor = serviceDescriptor;
    }

    /**
     * Retrieves the service's security descriptor
     * @return The service's security descriptor
     */
    public MarketplaceSecurityDescriptor getSecurityDescriptor() {
		return securityDescriptor;
	}

    /**
     * Sets the service's security descriptor
     * @param securityDescriptor The service's security descriptor
     */
	public void setSecurityDescriptor(MarketplaceSecurityDescriptor securityDescriptor) {
		this.securityDescriptor = securityDescriptor;
	}

	/**
	 * Retrieves the service's composition plans
	 * @return The service's composition plans
	 */
	public ICompositionPlan [] getCompositionPlans() {
		return compositionPlans;
	}
	
	/**
	 * Sets the service's composition plans
	 * @param compositionPlans The service's composition plans
	 */
	public void setCompositionPlans(ICompositionPlan [] compositionPlans) {
		this.compositionPlans = compositionPlans;
	}

	/**
	 * Retrieves the rules associated with a composite service
	 * @return The rules associated with a composite service
	 */
	public String getRules() {
		return rules;
	}

	/**
	 * Sets the rules associated with a composite service
	 * @param rules The rules associated with a composite service
	 */
	public void setRules(String rules) {
		this.rules = rules;
	}
	
}
