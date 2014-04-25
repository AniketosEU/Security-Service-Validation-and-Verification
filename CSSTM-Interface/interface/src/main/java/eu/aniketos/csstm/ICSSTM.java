/**
Copyright (c) 2014, SEARCH-LAB Ltd. (http://www.search-lab.hu)
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of SEARCH-LAB Ltd. nor the names of its contributors 
      may be used to endorse or promote products derived from this software 
      without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL SEARCH-LAB LTD. BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
**/

package eu.aniketos.csstm;

/** This is the Composite Service Security Testing Module's interface as defined in D3.4.
 * 
 * @author balazs 
 */
public interface ICSSTM {
	
	/** This method is used to test the tester credentials to use during the automated tests,
	 * if applicable. It should be invoked by the service provider through the SCF or IdM.
	 * 
	 * Note that it is the responsibility of the service provider to disable the test account after testing has concluded.
	 * 
	 * @param serviceID A String representing the Marketplace service ID of the composite service to be tested.
	 * @param username A String representing the username of the test account to use.
	 * @param password A String representing the password of the test account to use. 
	 */
	void sendUserCredentials(String serviceID, String username, String password);
	
	/** This method is used to trigger the actual testing on a composite service.
	 * It should be invoked by the SPDM if the composite service has the appropriate security property.
	 * 
	 * @param compositionPlan A String representation of the SecureBPMN file that contains all information necessary to start the tests:
	 * the Marketplace service ID of the composite service, a list of marketplace IDs of the components used by the service, and optional
	 * information useful during the tests (such as an annotated WSDL or a 'known good' example message).
	 */
	void requestSecurityTest(eu.aniketos.data.ICompositionPlan compositionPlan);

	/** This method is used to set the Aniketos Premium authentication information the CSSTM will use when communicating with Flinder.
	 * This method may be called at any time prior to calling requestSecurityTest.
	 * 
	 * @param csstmuser A String representing the Aniketos username to use.
	 * @param csstmpass A String representing the Aniketos password to use.
	 */
	void setCSSTMCredentials(String csstmuser, String csstmpass);
}
