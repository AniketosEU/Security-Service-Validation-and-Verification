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

package eu.aniketos.ncvm.impl;

/**
 * This is the interface of the Security Property Verification Module (SPVM).
 * 
 * @author Achim D. Brucker
 */
public interface PropertyVerificationService {

    /** This function validates if a service implementation complies to its agreement 
     * template. 
     *
     * @param AgreementTemplate The agreement template of the service compositon.
     * @param ServiceImplementationUrl URL to the service implementation (zip file). 
     * @return The validation result. 
     */
    public PropertyVerificationResult verifyTechnicalTrustProperties(String AgreementTemplate, 
                                                                     String ServiceImplementationUrl);
}

