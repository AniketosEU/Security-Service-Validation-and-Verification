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
 * Marketplace Search Parameters. Encapsulates parameters used during searching for services.
 * @author Kostas Giannakakis
 *
 */
public class MarketplaceSearchParams {

	/** The name of the service */
	private String name;

	/** The url of the service */
	private String url;

	/** The service's description */
	private String description;

	/** Service's tags */
	private String [] tags;
    
	/** Service's operations */
	private String [] operations;    

	/** Service's owner */
	private String owner;
	
	/** Security property of the service */ 
	private String securityProperty;
	
	/**
	 * Retrieves the name of the service
	 * @return Service's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the service's name
	 * @param name Service's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the service's URL
	 * @return Service's URL
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the service's URL
	 * @param url Service's URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Retrieves the service's description
	 * @return Service's description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the service's description
	 * @param description Service's description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Retrieves the service's tags
	 * @return Services tags
	 */
	public String[] getTags() {
		return tags;
	}

	/**
	 * Sets the service's tags
	 * @param tags Service's tags
	 */
	public void setTags(String[] tags) {
		this.tags = tags;
	}
    
    /**
     * Retrieve's the service's operations
     * @return the service's operations
     */
    public String[] getOperations() {
        return operations;
    }

    /**
     * Sets the service's operations
     * @param operations the service's operations
     */
    public void setOperations(String[] operations) {
        this.operations = operations;
    }    

	/**
	 * Retrieves the service's owner
	 * @return Service's owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Sets the service's owner
	 * @param owner Service's owner
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * Gets the security property of the service
	 * @return The security property
	 */
	public String getSecurityProperty() {
		return securityProperty;
	}

	/**
	 * Sets the security property of the service
	 * @param securityPropery The security property
	 */
	public void setSecurityProperty(String securityProperty) {
		this.securityProperty = securityProperty;
	}	
	
	
}
