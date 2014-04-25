/**
 * Copyright 2014 Athens Technology Centre SA <http://www.atc.gr/>
 * Aniketos Project FP7-ICT-257930 <http://www.aniketos.eu>
 * Konstantinos Giannakakis <k.giannakakis@atc.gr>
 * Vasilis Tountopoulos <v.tountopoulos@atc.gr>
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either 
 * version 3 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package eu.aniketos.marketplace;

import java.security.cert.X509Certificate;
import java.util.Date;

import eu.aniketos.data.ISecurityProperty;
import eu.aniketos.data.SPState;

/**
 * Security Property implementation
 * @author Kostas Giannakakis
 *
 */
public class SecurityProperty implements ISecurityProperty {

	private X509Certificate x509Certificate;
	
	private Date freshness;
	
	private String propertyID;
	
	private String propertyValue;
	
	private SPState state;
	
	private String conspec;
	
	@Override
	/**
	 * Gets the certificate associated with the property
	 * @return The X509 certificate
	 */
	public X509Certificate getCertificate() {
		return x509Certificate;
	}

	@Override
	/**
	 * Gets the freshness of the property
	 * @return The property's freshness
	 */
	public Date getFreshness() {
		return freshness;
	}

	@Override
	/**
	 * Gets the ID of the property
	 * @return The property ID
	 */
	public String getPropertyID() {
		return propertyID;
	}

	@Override
	/**
	 * Gets the value of the property
	 * @return The property value
	 */
	public String getPropertyValue() {
		return propertyValue;
	}

	@Override
	/**
	 * Gets the state of the property
	 * @return The state of the property
	 */
	public SPState getState() {
		return state;
	}

	@Override
	/**
	 * Sets the certificate of the property
	 * @param x509Certificate The certificate
	 */
	public void setCertificate(X509Certificate x509Certificate) {
		this.x509Certificate = x509Certificate;
	}

	@Override
	/**
	 * Sets the freshness of the property
	 * @param freshness The freshness
	 */
	public void setFreshness(Date freshness) {
		this.freshness = freshness;		
	}

	@Override
	/**
	 * Sets the property ID
	 * @param propertyID The property ID
	 */
	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}

	@Override
	/**
	 * Sets the property value
	 * @param propertyValue The property value
	 */
	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

	/**
	 * Sets the state of the property
	 * @param state The state of the property
	 */
	public void setState(SPState state) {
		this.state = state;
	}

	/**
	 * Gets the conspec associated with the property
	 * @return The conspec associated with the property 
	 */
	public String getConspec() {
		return conspec;
	}

	/**
	 * Sets the conspec associated with the property
	 * @param conspec The conspec associated with the property
	 */
	public void setConspec(String conspec) {
		this.conspec = conspec;
	}	
		
}
