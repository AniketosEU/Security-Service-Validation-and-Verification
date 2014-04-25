/*
Copyright (c) 2014, Bernard Butler and Barry Mulcahy (Waterford Institute of Technology, Ireland), Project: FP7-ICT-257930 Aniketos
Copyright (c) 2014, David Lamb and Bo Zhou (Liverpool John Moores University, UK), Project: FP7-ICT-257930 Aniketos
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

 -  Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 -  Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 -  Neither the name of WATERFORD INSTITUTE OF TECHNOLOGY nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package eu.aniketos.data;

import java.util.List;

/**
 * 
 * IAgreementTemplate - an interface specifying a Agreement Template - that is a
 * proposed contract (set of properties) offered by a service.
 * 
 * @author David Lamb, LJMU
 * @revised by Bo Zhou LJMU Aug 2011
 * @revised by Bernard Butler & Barry Mulcahy TSSG Aug 2011
 * @revise by Bo Zhou LJMU Feb 2013
 */
public interface IAgreementTemplate extends ISecurityDescriptor {
	String getAgreementTemplateID();

	void setAgreementTemplateID(String agreementTemplateID);

	void setSecurityProperties(List<ISecurityProperty> securityProperties);

	void setXML(String xmlContent);

	String getXML();

	String[] getXmlContents();

	void setXmlContents(String[] xmlContents);

}
