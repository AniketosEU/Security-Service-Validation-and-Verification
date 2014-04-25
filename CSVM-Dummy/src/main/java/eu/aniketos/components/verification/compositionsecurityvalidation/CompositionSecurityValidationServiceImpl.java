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

package eu.aniketos.components.verification.compositionsecurityvalidation;

import eu.aniketos.data.*;

/**
 * This class is a wrapper of the proprietary implementation of the Composition Security Validation Module (CSVM).
 * 
 * @author Achim D. Brucker
 */
public class CompositionSecurityValidationServiceImpl implements  CompositionSecurityValidationService {
    
    /** This function validates if a given composition plan complies 
     *  to the AgreementTemplate.
     *
     * @param CompositionPlan The composition plan and the information of the service provider (user of a service task) and their types (according roles for service tasks)
     * @return The validation result. 
     */
    public CompositionSecurityValidationResult VerifyCompositionCompliance(ICompositionPlan CompositionPlan)
    {
    	// Collect the authorisation level
    	// There are basically two relevant levels: NORMAL and FULL
    	// Roughly these equate to "Open Source" and "Pro" access to the PVM functionality
    	BasicAuthAuthorizationInterceptor.AuthLevel authorisation = BasicAuthAuthorizationInterceptor.getAuthLevel();
    	if (authorisation != BasicAuthAuthorizationInterceptor.AuthLevel.FULL) {
            return new CompositionSecurityValidationResultImpl(true, "Dummy result");
    	}

    	return new CompositionSecurityValidationResultImpl(true, "maybe secure");
    }                                                        
}

