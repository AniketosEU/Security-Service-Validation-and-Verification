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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.aniketos.data.ISecurityDescriptor;
import eu.aniketos.data.ISecurityProperty;

/**
 * Security Descriptor implementation
 * @author Kostas Giannakakis
 *
 */
public class SecurityDescriptor implements ISecurityDescriptor {

	private Map<String, ISecurityProperty> securityProperties = 
				new HashMap<String, ISecurityProperty>();
	
	@Override
	public void addProperty(ISecurityProperty securityProperty) {
		securityProperties.put(securityProperty.getPropertyID(), securityProperty);
	}

	@Override
	public List<ISecurityProperty> getProperties() {
		return new ArrayList<ISecurityProperty>(securityProperties.values());
	}

	@Override
	public synchronized ISecurityProperty getProperty(String propertyId) {
		if (securityProperties.containsKey(propertyId)) {
			return securityProperties.get(propertyId);
		}
		return null;
	}

	@Override
	public synchronized void removeProperty(String propertyId) {
		securityProperties.remove(propertyId);		
	}


}
