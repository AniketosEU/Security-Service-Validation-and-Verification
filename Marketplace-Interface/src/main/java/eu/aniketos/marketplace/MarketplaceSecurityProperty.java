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

import java.util.Date;

import eu.aniketos.data.SPState;

/**
 * Marketplace security property. Duplicates the SecurityProperty class, without the X509Certificate
 * @author Kostas Giannakakis
 *
 */
public class MarketplaceSecurityProperty {

	private Date freshness;
	
	private String propertyID;
	
	private String propertyValue;
	
	private SPState state;
	
	private String conspec;
	
	/**
	 * Gets the freshness of the property
	 * @return The property's freshness
	 */
	public Date getFreshness() {
		return freshness;
	}

	/**
	 * Gets the ID of the property
	 * @return The property ID
	 */
	public String getPropertyID() {
		return propertyID;
	}

	/**
	 * Gets the value of the property
	 * @return The property value
	 */
	public String getPropertyValue() {
		return propertyValue;
	}

	/**
	 * Gets the state of the property
	 * @return The state of the property
	 */
	public SPState getState() {
		return state;
	}

	/**
	 * Sets the freshness of the property
	 * @param freshness The freshness
	 */
	public void setFreshness(Date freshness) {
		this.freshness = freshness;		
	}

	/**
	 * Sets the property ID
	 * @param propertyID The property ID
	 */
	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}

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

	/**
	 * Converts state to an integer
	 * @param state A SPState value
	 * @return An integer value corresponding to the state
	 */
	public static int getState(SPState state) {
		switch(state) {
		case Bind:
			return 0;
		case Signed:
			return 1;
		case UnBind:
			return 2;
		case Verified:
			return 3;
		}
		return -1;
	}
	
	/**
	 * Converts integer state to SPState
	 * @param state An integer state
	 * @return A SPState corresponding to the integer value
	 */
	public static SPState getSPState(int state) {
		switch(state) {
		case 0:
			return SPState.Bind;
		case 1:
			return SPState.Signed;
		case 2:
			return SPState.UnBind;
		case 3:
			return SPState.Verified;
		}
		throw new IndexOutOfBoundsException("Not a valid integer state");
	}
	
	/**
	 * Static method that converts a marketplace security property to a security property
	 * @param msp A MarketplaceSecurityProperty
	 * @return
	 */
	public static SecurityProperty getSecurityProperty(MarketplaceSecurityProperty msp) {
		if (msp != null) {
			SecurityProperty sp = new SecurityProperty();
			sp.setPropertyID(msp.getPropertyID());
			sp.setPropertyValue(msp.getPropertyValue());
			sp.setFreshness(msp.getFreshness());
			sp.setState(msp.getState());
			sp.setConspec(msp.getConspec());
			return sp;
		}
		return null;
	}
}
