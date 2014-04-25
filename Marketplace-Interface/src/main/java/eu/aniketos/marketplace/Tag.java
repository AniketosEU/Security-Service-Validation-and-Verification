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

/***
 * A class that encapsulates a tag describing a service.
 * @author Kostas Giannakakis
 *
 */
public class Tag {

	/** The tag */
	private String tag;
	
	/** How many time the tag has been used */
	private int occurences;

	/** 
	 * Returns the tag
	 * @return The tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Sets the tag
	 * @param tag The tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * Returns the number of times the tag has been used
	 * @return the number of times the tag has been used
	 */
	public int getOccurences() {
		return occurences;
	}

	/**
	 * Sets the number of times the tag has been used
	 * @param occurences The number of times the tag has been used
	 */
	public void setOccurences(int occurences) {
		this.occurences = occurences;
	}
	
	
}
