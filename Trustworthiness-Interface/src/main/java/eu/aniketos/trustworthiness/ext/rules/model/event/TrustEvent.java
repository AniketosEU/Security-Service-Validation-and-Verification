/**
 * Copyright (c) 2013, Waterford Institute of Technology
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met
 *    - Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *    - Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *    - Neither the name of Waterford Institute of Technology nor the
 *      names of its contributors may be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *      
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL WATERFORD INSTITUTE OF TECHNOLOGY BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package eu.aniketos.trustworthiness.ext.rules.model.event;

/**
 * @author Hisain Elshaafi Interface for external and internal events indicating
 *         possible change in trustworthiness
 */
public interface TrustEvent {

	/**
	 * @return serviceId unique service id
	 */
	public abstract String getServiceId();

	/**
	 * @param serviceId
	 */
	public abstract void setServiceId(String serviceId);

	/**
	 * @return value event's new property value
	 */
	public abstract String getValue();

	/**
	 * @param value
	 *            event's new property value
	 */
	public abstract void setValue(String metricValue);

	/**
	 * @param property
	 *            name or id of service property
	 */
	public abstract void setProperty(String property);

	/**
	 * @return property name or id of service property
	 */
	public abstract String getProperty();

	/**
	 * @return subproperty name or id of subcategory of service property
	 */
	public abstract String getSubproperty();

	/**
	 * @param subproperty
	 *            name or id of subcategory of service property
	 */
	public abstract void setSubproperty(String subproperty);

	/**
	 * @return timestamp of ISO 8601 format
	 */
	public abstract String getTimestamp();

	/**
	 * @param timestamp
	 *            of ISO 8601 format
	 */
	public abstract void setTimestamp(String timestamp);

	/**
	 * @return eventDescription event description, comment, info, etc
	 */
	public abstract String getEventDescription();

	/**
	 * @param eventDescription
	 *            event description, comment, info, etc
	 */
	public abstract void setEventDescription(String eventDescription);

}