/**
 * Copyright 2014  Bo Zhou <B.Zhou@ljmu.ac.uk>
 * Liverpool John Moores University <http://www.ljmu.ac.uk/cmp/>
 * Copyright 2014  Francesco Malmignati, Selex Elsag
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

package eu.aniketos.scpm;

/**
 * @author Francesco Malmignati, Selex Elsag
 *
 */
public class ServiceQuery {
	
	private String type;
	private String operation;
	private String securityReq;
	
	public ServiceQuery(String type, String securityReq){
		this.type = type;
		this.securityReq = securityReq;
	}
	
	public ServiceQuery(String type, String securityReq, String operation){
		this.type = type;
		this.securityReq = securityReq;
		this.operation = operation;
	}
	
	public String getType(){
		return type;
	}
	
	public String getOperation(){
		return operation;
	}
	
	public String securityReq(){
		return securityReq;
	}

}
