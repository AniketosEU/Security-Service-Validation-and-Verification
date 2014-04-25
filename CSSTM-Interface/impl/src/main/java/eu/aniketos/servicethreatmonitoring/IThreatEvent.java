/**
Copyright (c) 2014, Damien MORELLET (Thales)
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
package eu.aniketos.servicethreatmonitoring;

/**
 * ThreatEvent interface for the <b>Service Threat Monitoring Module</b>
 * 
 * @author Damien MORELLET (Thales)
 * 
 */
public interface IThreatEvent {

	/**
	 * This method receives an event from the Service Monitoring Module of the Environment and logs it.
	 * An alert is triggered if a threat is detected for that event type.
	 * 
	 * @param event The event to analyse
	 * 
	 * @return true if the rules have been executed correctly or false otherwise (but the event is always logged)
	 */
	public boolean processEvent(Event event);
	
	/**
	 * Invoke eu.aniketos.servicethreatmonitoring.IThreatEvent.processEvent(Event) instead
	 * 
	 * @see eu.aniketos.servicethreatmonitoring.Event
	 * @return true if the rules have been executed correctly or false otherwise (but the event is always logged)
	 */
	@Deprecated
	public boolean processEvent(String serviceID, String composingService, String serviceProvider, MonitoringSource monSource,
	 String counterMID, String eventType, String eventDesc, String eventDate, String eventTime);
}
