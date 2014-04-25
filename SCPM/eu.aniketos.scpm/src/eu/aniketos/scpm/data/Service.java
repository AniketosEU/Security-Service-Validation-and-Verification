/**
 * Copyright 2012  Bo Zhou <B.Zhou@ljmu.ac.uk>
 * Liverpool John Moores University <http://www.ljmu.ac.uk/cmp/>
 * Aniketos Project <http://www.aniketos.eu>
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

package eu.aniketos.scpm.data;

import java.util.Set;

public class Service {
	
	private String serviceId;
	
	private String location;
	
	private String provider;
	
	public Service(String serviceId, String location, String provider) {
		this.serviceId = serviceId;
		this.location = location;
		this.provider = provider;
	}

	public Service(String serviceId, String location){
		this.serviceId = serviceId;
		this.location = location;
	}
	
	public String getServiceId(){
		return serviceId;
	}
	
	public String getLocation(){
		return location;
	}
	
	public void setServiceId(String serviceId){
		this.serviceId = serviceId;
	}
	
	public void setLocation(String locations){
		this.location = locations;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

}
