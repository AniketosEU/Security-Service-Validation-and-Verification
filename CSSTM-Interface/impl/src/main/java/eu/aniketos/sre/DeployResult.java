/**
Copyright (c) 2014, Kostas Giannakakis (ATC)
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
package eu.aniketos.sre;

/**
 * Class that encapsulates the result of a deployment
 * @author Kostas Giannakakis
 *
 */
public class DeployResult {

	/** The address of the deployed service */
	private String serviceAddress;
	
	/** An error message. If there is no error, it is null */
	private String errorMessage;
	
	/** The result of the deployment */
	private boolean ok;

	/**
	 * Gets the address of the deployed service
	 * @return the  address of the deployed service
	 */
	public String getServiceAddress() {
		return serviceAddress;
	}

	/**
	 * Sets the address of the deployed service
	 * @param serviceAddress The  address of the deployed service
	 */
	public void setServiceAddress(String serviceAddress) {
		this.serviceAddress = serviceAddress;
	}

	/**
	 * Gets the error message
	 * @return The error message, if there is any, null otherwise
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Sets the error message
	 * @param errorMessage The error message
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * Gets the result of the deployment
	 * @return true if the deployment is successful, false otherwise
	 */
	public boolean isOk() {
		return ok;
	}

	/**
	 * Sets the result of the deployment
	 * @param ok The result of the deployment
	 */
	public void setOk(boolean ok) {
		this.ok = ok;
	}

	
}
