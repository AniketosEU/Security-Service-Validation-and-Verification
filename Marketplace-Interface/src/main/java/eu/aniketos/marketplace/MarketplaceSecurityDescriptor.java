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

import java.util.List;

import eu.aniketos.data.ISecurityProperty;

/**
 * Marketplace Security Descriptor
 * @author Kostas Giannakakis
 *
 */
public class MarketplaceSecurityDescriptor {

	private List<MarketplaceSecurityProperty> securityProperties;

	/**
	 * Gets the security properties
	 * @return The security properties 
	 */
	public List<MarketplaceSecurityProperty> getSecurityProperties() {
		return securityProperties;
	}

	/**
	 * Sets security properties
	 * @param securityProperties The security properties
	 */
	public void setSecurityProperties(
			List<MarketplaceSecurityProperty> securityProperties) {
		this.securityProperties = securityProperties;
	}
	

	/**
	 * Static method that converts a Marketplace Security Descriptor to a Security Descriptor
	 * @param msd A MarketplaceSecurityDescriptor
	 * @return A SecurityDescriptor from the data of a MarketplaceSecurityDescriptor
	 */
	public static SecurityDescriptor getSecurityDescriptor(MarketplaceSecurityDescriptor msd) {
		if (msd != null && msd.getSecurityProperties() != null) {
			SecurityDescriptor sd = new SecurityDescriptor();
			for(MarketplaceSecurityProperty msp: msd.getSecurityProperties()) {
				ISecurityProperty securityProperty = MarketplaceSecurityProperty.getSecurityProperty(msp);
				sd.addProperty(securityProperty );
			}
		}
		return null;
	}
	
}
