/* Copyright 2012-2015 SAP SE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.aniketos.securebpmn.xacml.api.autho;

import eu.aniketos.securebpmn.xacml.api.SecurityError;
import eu.aniketos.securebpmn.xacml.api.log.AccessControlRequest;

/**
 * 
 * This interface allows the PDP to pass an access control request
 * to the PDP State module and update the pdp state according to the 
 * executed action
 *
 */
public interface IPDPStateManagement {
	/**
	 * update the PDPState according to the access control request
	 * @param execRequest
	 */
	void updatePDPState(AccessControlRequest execRequest)  throws SecurityError;

}