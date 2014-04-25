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
 * Encapsulates a service operation. A service operation must be associated with
 * a tag, so that it can be discovered.
 * @author Kostas Giannakakis
 */
public class ServiceOperation {
    
    private String method;
    
    private String tag;

    /**
     * Returns the method name of the operation
     * @return the method name of the operation
     */
    public String getMethod() {
        return method;
    }

    /**
     * Sets the method name of the operation
     * @param method the method name
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Returns the tag associated with the operation
     * @return the tag of the operation
     */
    public String getTag() {
        return tag;
    }

    /**
     * Sets the tag associated with the operation
     * @param tag the tag associated with the operation
     */
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    
}
