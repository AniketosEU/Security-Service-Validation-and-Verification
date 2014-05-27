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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xml.type.AnyType;

/**
 * Support class for working with BPMN files.
 * @author LJMU/David Llewellyn-Jones
 *
 */
class BPMNSupport {
	static String getExtensionValue (BaseElement element, String extensionName) {
		String result = "";
		Boolean found = false;

		if ((element.getExtensionValues() != null) && (element.getExtensionValues().size() > 0)) {
			ExtensionAttributeValue extensionAttributeValue = element.getExtensionValues().get(0);
			FeatureMap extensionElements = extensionAttributeValue.getValue();
			
			// Work through all of the extension elements
			for (FeatureMap.Entry extension : extensionElements) {
				if (extension.getEStructuralFeature().getName().equalsIgnoreCase("field")) {
					// Check all of the attributes to the field element
					AnyType fieldElement = (AnyType)extension.getValue();
					FeatureMap fieldAttributes = fieldElement.getAnyAttribute();
					for (FeatureMap.Entry fieldAttribute : fieldAttributes) {
						if (fieldAttribute.getEStructuralFeature().getName().equalsIgnoreCase("name")) {
							if (fieldAttribute.getValue().toString().equalsIgnoreCase(extensionName)) {
								found = true;
							}
						}
					}

					if (found) {
						// Check all of the sub elements of the field element
						FeatureMap subElements = fieldElement.getAny();
						for (FeatureMap.Entry sub : subElements) {
							if (sub.getEStructuralFeature().getName().equalsIgnoreCase("string")) {
								AnyType stringElement = (AnyType) sub.getValue();
								result = stringElement.getMixed().get(0).getValue().toString();
							}
						}
						found = false;
					}
				}
			}
		}

		return result;
	}
	
	static Definitions parseBPMN (String bpmn2Data) throws IOException {
		org.eclipse.emf.ecore.resource.ResourceSet resourceSet = new ResourceSetImpl();
		org.eclipse.emf.ecore.resource.Resource resource = resourceSet.createResource(URI.createURI(""));
		Map<String, Object> opts = new HashMap<String, Object>();
		opts.put(XMLResource.OPTION_EXTENDED_META_DATA, new Boolean(true));// or you could specify an ExtendedMetaData instance

		InputStream input = new ByteArrayInputStream(bpmn2Data.getBytes());
		resource.load(input, opts);
		resourceSet.getResources().add(resource);

		Definitions definitions;
		EObject root = resource.getContents().get(0);
		if ((root instanceof org.eclipse.bpmn2.DocumentRoot) || (root instanceof org.eclipse.bpmn2.impl.DocumentRootImpl))
			definitions = ((org.eclipse.bpmn2.DocumentRoot) root).getDefinitions();
		else {
			definitions = (org.eclipse.bpmn2.Definitions) root;
		}
		
		return definitions;
	}
}
