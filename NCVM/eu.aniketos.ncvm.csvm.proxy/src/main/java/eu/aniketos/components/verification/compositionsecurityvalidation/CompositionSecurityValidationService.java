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
 * This is the interface of the Composition Security Validation Module (CSVM).
 * 
 * @author Achim D. Brucker
 */
public interface CompositionSecurityValidationService {

       
    /** This function validates if a given composition plan complies 
     *  to the AgreementTemplate. .
     *
     * @param CompositionPlan The composition plan is provided as a zip-file. This zip-file contains an activiti file annotated with SecureBPMN specifications and a BPMN20.xml file with the service provider information (user for service tasks) and their types (role for the service providers)
     * @return The validation result. 
     */
    public CompositionSecurityValidationResult  VerifyCompositionCompliance(ICompositionPlan CompositionPlan);
}
