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


/**
 * Service Descriptor
 * @author Kostas Giannakakis
 *
 */
public class ServiceDescriptor {

    /** The service id */
	private String id;

	/** The service name */
    private String name;

	/** The service description */
    private String description;

    /** The service binding */
    private String binding;

    /** The provider of the service */
    private String providerName;
    
    /** Service's operations */
    private ServiceOperation [] operations;
    
    /** Whether the service can be tested or not */
    private boolean testable;
    
    /**
     * Retrieves the binding (endpoint) of the service
     * @return The binding of the service
     */
    public String getBinding() {
		return binding;
	}

    /**
     * Sets the binding (endpoint) of the service
     * @param binding Service binding
     */
	public void setBinding(String binding) {
		this.binding = binding;
	}

	/**
	 * Retrieves the service Id
	 * @return The service Id
	 */
	public String getId() {
        return id;
    }

	/**
	 * Sets the service Id
	 * @param id The service Id
	 */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the name of the service
     * @param name The service name
     */
    public void setName(String name) {
        this.name = name;

    }

    /**
     * Retrieves the name of the service
     * @return The service name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the description of the service
     * @param description The service description
     */
    public void setDescription(String description) {
        this.description = description;

    }

    /**
     * Retrieves the service description
     * @return The service description
     */
    public String getDescription() {
        return description;
    }
    
    public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	/**
     * Retrieves the testable flag
     * @return true, if the service is testable
     */
	public boolean isTestable() {
		return testable;
	}

	/**
	 * Sets the testable flag
	 * @param testable Whether the service is testable or not
	 */
	public void setTestable(boolean testable) {
		this.testable = testable;
	}

    public ServiceOperation[] getOperations() {
        return operations;
    }

    public void setOperations(ServiceOperation[] operations) {
        this.operations = operations;
    }

}