/**
 * Copyright 2014 SAP <http://www.sap.com/>
 * Aniketos Project FP7-ICT-257930 <http://www.aniketos.eu>
 * Achim D. Brucker <achim.brucker@sap.com>
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

package eu.aniketos.components.verification.propertyverification;

/**
 * This class represents the result of verifying a security property using the SPVM.
 * 
 * @author Achim D. Brucker
 */
public class  PropertyVerificationResultImpl implements PropertyVerificationResult{
    int result = -1;
    String explaination = "invalid result (you should never see this)";
    
    /** This constructor creates a verification result object. 
     * 
     * @param  result The validation result (non-zero value indicates an error)
     * @param  explaination The textual explaination of the valdiation result.
     * @return The validation result object.  
     */

    public PropertyVerificationResultImpl(int result, String explaination)
    {
        this.result = result;
        this.explaination = explaination;
    } 

    /** This function returns the actual verification result. 
     *
     * @return The validation result (no-zero value indicates a violation)
     */
    public int getVerificationResult() {return result;};

    /** This function returns the actual verification result. 
     *
     * @return Textual explaination of the verification result.
     */
    public String getVerificationExplaination(){return explaination;};
}	
