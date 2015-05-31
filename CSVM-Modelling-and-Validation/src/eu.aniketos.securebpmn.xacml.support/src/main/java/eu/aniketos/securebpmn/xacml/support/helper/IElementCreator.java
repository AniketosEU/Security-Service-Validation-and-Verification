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

package eu.aniketos.securebpmn.xacml.support.helper;


import java.net.URI;
import java.util.Set;

import com.sun.xacml.ParsingException;
import com.sun.xacml.UnknownIdentifierException;
import com.sun.xacml.ctx.Attribute;
import com.sun.xacml.ctx.RequestElement;

public interface IElementCreator {
    RequestElement getRequestElement(String value) throws ParsingException;
    RequestElement getRequestElement(Set<Attribute> attrs);

    Attribute getAttribute(URI attributeId, URI dataType, String value) throws ParsingException, UnknownIdentifierException;
    Attribute getAttributeDefaultType(String value) throws ParsingException;
}
