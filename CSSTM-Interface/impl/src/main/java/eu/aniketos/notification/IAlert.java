/**
Copyright (c) 2014, Erlend Andreas Gjære (SINTEF), erlendandreas.gjare@sintef.no
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
package eu.aniketos.notification;


/**
 * Receives an alert from some Aniketos component/service and allows any evaluation and reasoning to be done, 
 * before passing it on to the message broker where it is published to its subscribing entities.
 * 
 * @version 1.0
 * @author Erlend Andreas Gjære (SINTEF), erlendandreas.gjare@sintef.no
 */
public interface IAlert {
     
	/**
	 * Receives an alert from some Aniketos component/service, and passes it on to the 
	 * notification message broker for evaluation and publishing to subscribing entities.
	 *  
	 * @param serviceId The identification of composed service in the Marketplace.
	 * @param alertType Types of alert to be sent to the Notification module (class constants of <i>Notification</i>)
	 * @param value Contains the value corresponding to the type parameter concerned.
	 */
	public void alert(String serviceId, String alertType, String value);

	/**
	 * Receives an alert from some Aniketos component/service, and passes it on to the 
	 * notification message broker for evaluation and publishing to subscribing entities.
	 *  
	 * @param serviceId The identification of composed service in the Marketplace.
	 * @param alertType Types of alert to be sent to the Notification module (class constants of <i>Notification</i>)
	 * @param value Contains the value corresponding to the type parameter concerned.
	 * @param description An alert description. Can be one of the constant fields in <i>AlertDescription</i>, however free text descriptions are allowed.
	 */
	public void alert(String serviceId, String alertType, String value, String description);

	/**
	 * Receives an alert from some Aniketos component/service, and passes it on to the 
	 * notification message broker for evaluation and publishing to subscribing entities.
	 *  
	 * @param serviceId The identification of composed service in the Marketplace.
	 * @param alertType Types of alert to be sent to the Notification module (class constants of <i>Notification</i>)
	 * @param value Contains the value corresponding to the type parameter concerned.
	 * @param description An alert description. Can be one of the constant fields in <i>AlertDescription</i>, however free text descriptions are allowed.
	 * @param threatId The threat identification with which the notification is associated, as it is registered in the Marketplace.
	 */
	public void alert(String serviceId, String alertType, String value, String description, String threatId);
	
}