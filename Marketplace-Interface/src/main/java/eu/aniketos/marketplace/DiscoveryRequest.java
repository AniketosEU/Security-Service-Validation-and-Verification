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

/**
 * Discovery Request. Encapsulates parameters used during searching for services.
 * @author Kostas Giannakakis
 *
 */
public class DiscoveryRequest {
    /** The name of the service */
	private String name;

    /** The keywords associated with the service */
    private String[] keywords;

    /**
     * Retrieves the service name
     * @return The service name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the service name
     * @param name Sets the service name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the keywords associated with the service
     * @return Service's keywords
     */
    public String[] getKeywords() {
        return keywords;
    }

    /**
     * Sets the keywords associated with the service
     * @param keywords Service's keywords
     */
    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }
}
