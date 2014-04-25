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

import static com.google.common.base.Objects.toStringHelper;
import static com.google.common.base.Strings.nullToEmpty;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Representation of an Aniketos Event
 * 
 * @author Damien MORELLET (Thales)
 * 
 */
public class Event {
	private static final Logger logger = LoggerFactory.getLogger(Event.class.getName());
	
	private String eventID; ///< The ID of the event in the database
	private String eventType; ///< An ID code reported by the monitoring source
	private String eventDesc; ///< More contextual information reported by the source (for example, ContextChange, ServiceChange)
	private MonitoringSource eventMonSource; ///< Monitoring source element which sent the Event
	private String eventCounterMID; ///< The monitoring control ID as defined in the specifications and the threat repository, corresponding to the event
	private String eventServiceID; ///<  ServiceID, The identification of service specification in the Marketplace
	private String eventComposingService; ///< The service composition on which the event has been detected
	private String eventServiceProvider; ///< The service providers of the service on which the event has been detected
	private String eventDate; ///< The real date when the event occured
	private String eventTime; ///< The real time when the event occured
	
	public Event() {}
	
	/**
	 * Constructor with eventDateTime
	 */
	public Event(String eventID, String eventType, String eventDesc, MonitoringSource eventMonSource,
			String eventCounterMID, String eventService, String eventComposingService,
			String eventServiceProvider, Date eventDateTime) {
		this(
				eventID, eventType, eventDesc, eventMonSource, eventCounterMID,
				eventService, eventComposingService, eventServiceProvider,
				new SimpleDateFormat("dd/MM/yyyy").format(eventDateTime),
				new SimpleDateFormat("HH:mm:ss").format(eventDateTime));
	}

	/**
	 * Constructor with separated eventDate & eventTime
	 */
	public Event(String eventID, String eventType, String eventDesc, MonitoringSource eventMonSource,
			String eventCounterMID, String eventService, String eventComposingService,
			String eventServiceProvider, String eventDate, String eventTime) {
		this.eventID = nullToEmpty(eventID);
		this.eventType = nullToEmpty(eventType);
		this.eventDesc = nullToEmpty(eventDesc);
		this.eventMonSource = eventMonSource;
		this.eventCounterMID = nullToEmpty(eventCounterMID);
		this.eventServiceID = nullToEmpty(eventService);
		this.eventComposingService = nullToEmpty(eventComposingService);
		this.eventServiceProvider = nullToEmpty(eventServiceProvider);
		this.eventDate = nullToEmpty(eventDate);
		this.eventTime = nullToEmpty(eventTime);
	}

	/**
	 * @return the eventID
	 */
	public String getEventID() {
		return eventID;
	}

	/**
	 * @param eventID
	 *            the eventID to set
	 */
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType
	 *            the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the eventDesc
	 */
	public String getEventDesc() {
		return eventDesc;
	}

	/**
	 * @param eventDesc
	 *            the eventDesc to set
	 */
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}

	/**
	 * @return the eventMonSource
	 */
	public MonitoringSource getEventMonSource() {
		return eventMonSource;
	}

	/**
	 * @param eventMonSource
	 *            the eventMonSource to set
	 */
	public void setEventMonSource(MonitoringSource eventMonSource) {
		this.eventMonSource = eventMonSource;
	}

	/**
	 * @return the eventCounterMID
	 */
	public String getEventCounterMID() {
		return eventCounterMID;
	}

	/**
	 * @param eventCounterMID
	 *            the eventCounterMID to set
	 */
	public void setEventCounterMID(String eventCounterMID) {
		this.eventCounterMID = eventCounterMID;
	}

	/**
	 * @return the eventServiceID
	 */
	public String getEventServiceID() {
		return eventServiceID;
	}

	/**
	 * @param eventServiceID
	 *            the eventServiceID to set
	 */
	public void setEventServiceID(String eventServiceID) {
		this.eventServiceID = eventServiceID;
	}

	/**
	 * @return the eventComposingService
	 */
	public String getEventComposingService() {
		return eventComposingService;
	}

	/**
	 * @param eventComposingService
	 *            the eventComposingService to set
	 */
	public void setEventComposingService(String eventComposingService) {
		this.eventComposingService = eventComposingService;
	}

	/**
	 * @return the eventServiceProvider
	 */
	public String getEventServiceProvider() {
		return eventServiceProvider;
	}

	/**
	 * @param eventServiceProvider
	 *            the eventServiceProvider to set
	 */
	public void setEventServiceProvider(String eventServiceProvider) {
		this.eventServiceProvider = eventServiceProvider;
	}

	/**
	 * @return the eventDate
	 */
	public String getEventDate() {
		return eventDate;
	}

	/**
	 * @param eventDate
	 *            the eventDate to set
	 */
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * @return the eventTime
	 */
	public String getEventTime() {
		return eventTime;
	}

	/**
	 * @param eventTime
	 *            the eventTime to set
	 */
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	/**
	 * Helper method to get a Date object from the Date and Time of the event
	 * 
	 * @return a Date object from the current Event (default: current datetime)
	 */
	public Date getEventDateTime() {
		// We use the current timestamp by default if we can't parse the parameters
		Date eventDateTime = new Date();

		if (!this.getEventDate().isEmpty() && !this.getEventTime().isEmpty()) {
			try {
				eventDateTime = DateFormat.getInstance().parse(this.getEventDate() + " " + this.getEventTime());
			} catch (ParseException e) {
				logger.warn(e.toString() + " -> The current dateTime will be used");
			}
		}
		else if (this.getEventDate().isEmpty() || this.getEventTime().isEmpty()) {
			logger.warn("The date and/or time are empty -> The current dateTime will be used");
		}

		return eventDateTime;
	}

	@Override
	public String toString() {
		return toStringHelper(this)
				.add("Id", eventID)
				.add("Type", eventType)
				.add("Description", eventDesc)
				.add("Source", eventMonSource.getValue())
				.add("CounterMID", eventCounterMID)
				.add("ServiceID", eventServiceID)
				.add("Composing Service", eventComposingService)
				.add("Service Provider", eventServiceProvider)
				.add("Date", eventDate)
				.add("Time", eventTime)
				.toString();
	}
}