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
import eu.aniketos.data.IConsumerPolicy;
import java.util.List;

/**
 * Marketplace interface
 * @author Kostas Giannakakis
 *
 */
public interface IMarketplace {

    /** Registers a service into the Marketplace
     * @param authToken The authentication token for the request
     * @param announcement The MarketplaceAnnouncement object
     * @return A unique serviceId, if the request is successful. An empty string otherwise.
     */
	String announceService(String authToken, MarketplaceAnnouncement announcement);

    /**
     * Searches for services that match the given criteria.
     * @param authToken The authentication token for the request.
     * @param params MarketplaceSearchParams object, which contains the search criteria
     * @return A list of service descriptors that match the specified criteria
     */
	List<ServiceDescriptor> discoverService(String authToken, MarketplaceSearchParams params);

    /**
     * Retrieves the service descriptor of a specific service
     * @param authToken The authentication token for the request
     * @param serviceId The service Id
     * @return The security descriptor of the service
     */
	MarketplaceSecurityDescriptor getSecurityDescriptor(String authToken, String serviceId);
	
    /**
     * Retrieves the BPMN diagram of a specific service
     * @param serviceId The service Id
     * @return The security descriptor of the service
     */
	String getBpmnDiagram(String serviceId);	

	/**
	 * Updates the conspec of a service
	 * @param authToken The authentication token for the request
	 * @param serviceId The service Id of the service to be updated
	 * @param conspec The updated conspec files
	 * @return true if the operation is successful
	 */
	boolean updateSecurityDescription(String authToken, String serviceId, IConsumerPolicy conspec);

	/**
	 * Updates the BPMN diagram of a service
	 * @param authToken The authentication token for the request
	 * @param serviceId The service Id of the service to be updated
	 * @param bpmnDiagram The updated BPMN diagram
	 * @return true if the operation is successful
	 */
	boolean updateBpmnDiagram(String authToken, String serviceId, String bpmnDiagram);

	/**
	 * Marks a service to be provided by the Marketplace
	 * @param authToken The authentication token for the request
	 * @param serviceId The service Id
	 */
	void provide(String authToken, String serviceId);

	/**
	 * Marks a service not to be provided by the Marketplace
	 * @param authToken The authentication token for the request
	 * @param serviceId The service Id
	 */
	void notProvide(String authToken, String serviceId);

    /** Retrieves an authentication token.
     * @param username Username
     * @param password Password
     * @return The authentication token that can be used in future requests. null if the authentication fails
     */
	String getAuthToken(String username, String password);

    /**
     * Removes a registered service from the Marketplace
     * @param authToken The authentication token for the request.
     * @param serviceId The service Id
     * @return true if the service is successfully removed
     */
	boolean deleteService(String authToken, String serviceId);
	
	/**
	 * Retrieves the most popular tags in marketplace
	 * @param authToken The authentication token for the request.
	 * @return
	 */
	List<Tag> getTags(String authToken);
	
	/**
	 * Checks is a service is testable
	 * @param serviceId The service Id
	 * @return true, if the service is testable
	 */
	boolean isTestable(String serviceId);
	
	/**
	 * Registers the source URL for a specific service
	 * @param serviceId The service id
	 * @param sourceUrl The source URL
	 * @return true if the operation succeeded
	 */
	boolean registerSource(String serviceId, String sourceUrl);
	
	/**
	 * Retrieves the source URL for a service
	 * @param serviceId The service id
	 * @return The source URL of the service
	 */
	String getSource(String serviceId); 
    
	/**
	 * Retrieves the rules associated with a service
	 * @param serviceName The service name
	 * @return The rules associated with a service
	 */
	String getRules(String serviceId); 
    
	/**
	 * Retrieves the rules associated with a service
	 * @param serviceId The service name
	 * @return The rules associated with a service
	 */
	String getRulesByServiceName(String serviceName);     
	
	/**
     * Retrieves the composition plans of a service
     * @param serviceId The service id
     * @return The composition plans of a service
     */
    ICompositionPlan [] getCompositionPlans(String serviceId); 
}
