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
 * The Notification module defines the INotification interface to be used by components that wants to receive notifications 
 * regardless of which service the notifications concern. The Service runtime environment (SRE) should always implement this interface.
 * 
 * @version 1.0
 * @author Erlend Andreas Gjære (SINTEF), erlendandreas.gjare@sintef.no
 * 
 */
public interface INotification {

	/**
	 * Receives a notification outside the regular subscription mechanism, for cases where message targeting 
	 * is done better from the Notification module's perspective rather than from the subscriber's.
	 * 
	 * @param alert Message object which contains the data about the notification, i.e. serviceId, alertType and value (required), and optionally description and threatId.
	 */
	public void alert(Notification alert);
	
}
