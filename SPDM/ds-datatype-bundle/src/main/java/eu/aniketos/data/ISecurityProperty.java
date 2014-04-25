/*
Copyright (c) 2013, Aneel Rahim, Bernard Butler and Arif Fareed (Waterford Institute of Technology, Ireland), Project: FP7-ICT-257930 Aniketos
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

 -  Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 -  Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 -  Neither the name of WATERFORD INSTITUTE OF TECHNOLOGY nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package eu.aniketos.data;

import java.util.Date;
import java.security.cert.X509Certificate;

/**
 * ISecurityProerpty is an interface that specify the behavior of the
 * Security Properties.
 * 
 * @author Bernard Butler and M. Arif Fareed (TSSG)
 *
 */
public interface ISecurityProperty {
	
	public abstract String getPropertyID();

	public abstract String getPropertyValue();

	public abstract Date getFreshness();

	public abstract void setPropertyID(String propertyID);

	public abstract void setPropertyValue(String value);

	public abstract void setFreshness(Date freshness);
	
	public abstract SPState getState();

	
	public abstract X509Certificate getCertificate();

	public abstract void setCertificate(X509Certificate certificate);

}
