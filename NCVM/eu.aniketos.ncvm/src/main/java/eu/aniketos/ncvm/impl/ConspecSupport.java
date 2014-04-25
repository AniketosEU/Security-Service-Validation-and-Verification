/**
 * Copyright 2014 Liverpool John Moores University <http://www.ljmu.ac.uk/cmp/>
 * Aniketos Project FP7-ICT-257930 <http://www.aniketos.eu>
 * David Llewellyn-Jones <D.Llewellyn-Jones@ljmu.ac.uk>
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

import eu.aniketos.spec.Declaration;
import eu.aniketos.spec.SConst;

class ConspecSupport {
	static String getConspecIdentifier (eu.aniketos.spec.Specification spec) {
		String id = "";

		// If the two IDs are different, which takes priority? We assume the specification attribute identifier
		id = spec.getAttribute("id");

		if ((id == null) || (id.isEmpty())) {
			if ((spec.getDefinition() != null) && (spec.getDefinition().identifier != null) && (spec.getDefinition().identifier.identifier != null)) {
				id = spec.getDefinition().identifier.identifier;
			}
		}

		return id;
	}
	
	static String getConspecService (eu.aniketos.spec.Specification spec) {
		String service = "";
		
		// Check the service global ID
		for (Declaration declaration : spec.getDeclarations()) {
			String id = declaration.identifier.identifier;
			if (id.equalsIgnoreCase("ServiceID")) {
				if (declaration.value instanceof SConst) {
					service = ((SConst) declaration.value).text;
				}
			}
		}

		return service;
	}
}
