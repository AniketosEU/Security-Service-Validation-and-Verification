/*
Copyright (c) 2012, Bernard Butler and Arif Fareed (Waterford Institute of Technology, Ireland), Project: FP7-ICT-257930 Aniketos
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

 -  Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 -  Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 -  Neither the name of WATERFORD INSTITUTE OF TECHNOLOGY nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package eu.aniketos.data;


import eu.aniketos.data.ISecurityDescriptor;
import eu.aniketos.data.ISecurityProperty;

import java.util.Map;
import java.util.Set;


/**
 * ISPSRepository is an interface to the SPDM local persistence storage.
 * 
 * @author Bernard Butler and M. Arif Fareed (TSSG)
 *
 */
public interface ISPSRepository {

	public abstract void registerService(IWebService service_key, ISecurityDescriptor security_descriptor);

	public abstract void registerService(IWebService service_key, ISecurityProperty property);
	
	public abstract void registerSecurityProperty(ISecurityProperty property, IWebService service_key);

	public abstract void registerSecurityProperty(ISecurityDescriptor security_descriptor, IWebService service_key);

	public abstract Set<ISecurityProperty> lookUpSecurityProperty(IWebService service_key);

	public abstract Set<IWebService> lookupService(ISecurityProperty property_value);
	
	public abstract void removeSecurityProperty(ISecurityProperty property_value);
	
	public abstract ISecurityProperty getSecurityProperty(String sp_id);
	
	public abstract IWebService getService(String service_id);
	
	public abstract Set<Map.Entry<String, IWebService>>  getServiceEntries();
	
	public abstract Set<Map.Entry<String, ISecurityProperty>>  getPropertyEntries();
	
//	public abstract void removeService(IWebService service_key);

//	public abstract EntrySet<IService, ISecurityProperty> getRepositoryEntries();

	public abstract void printRegisteredServices();
	
	public abstract void printRegisteredSecurityProperties();
	
	public abstract void clear_repository();
	
	public abstract int repository_size();

	public abstract Set<Map.Entry<IWebService, ISecurityProperty>>  getEntriest();
	
	//Print Key Entry Value
	public abstract String toString();
	

}
