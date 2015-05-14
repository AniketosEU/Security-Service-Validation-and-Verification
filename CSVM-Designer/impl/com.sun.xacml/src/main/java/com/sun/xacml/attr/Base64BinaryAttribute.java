
/*
 * @(#)Base64BinaryAttribute.java
 *
 * Copyright 2003-2004 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *   1. Redistribution of source code must retain the above copyright notice,
 *      this list of conditions and the following disclaimer.
 * 
 *   2. Redistribution in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN MICROSYSTEMS, INC. ("SUN")
 * AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST
 * REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL,
 * INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY
 * OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE,
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed or intended for use in
 * the design, construction, operation or maintenance of any nuclear facility.
 */

package com.sun.xacml.attr;

import com.sun.xacml.Constants;
import com.sun.xacml.ParsingException;

import java.io.IOException;

import java.net.URI;

import java.util.Arrays;

import org.w3c.dom.Node;


/**
 * Representation of an xsi:base64Binary value. This class supports parsing
 * xsi:base64Binary values. All objects of this class are immutable and
 * all methods of the class are thread-safe.
 *
 * @since 1.0
 * @author Steve Hanna
 */
public class Base64BinaryAttribute extends AttributeValue
{
    /**
     * Official name of this type
     */
    public static final String identifier = 
    	TypeIdentifierConstants.BASE64BINARY;
 
    /**
     * URI version of name for this type
     * <p>
     * This field is initialized by a static initializer so that
     * we can catch any exceptions thrown by URI(String) and
     * transform them into a RuntimeException, since this should
     * never happen but should be reported properly if it ever does.
     */
    private static URI identifierURI;

    /**
     * RuntimeException that wraps an Exception thrown during the
     * creation of identifierURI, null if none.
     */
    private static RuntimeException earlyException;

    /**
     * Static initializer that initializes the identifierURI
     * class field so that we can catch any exceptions thrown
     * by URI(String) and transform them into a RuntimeException.
     * Such exceptions should never happen but should be reported
     * properly if they ever do.
     */
    static {
        try {
            identifierURI = URI.create(identifier);
        } catch (IllegalArgumentException e) {
            earlyException = e;
        }
    }

    /**
     * The actual binary value that this object represents.
     */
    private byte [] value;

    /**
     * The value returned by toString(). Cached, but only
     * generated if needed.
     */
    private String strValue;

    /**
     * Creates a new <code>Base64BinaryAttribute</code> that represents
     * the byte [] value supplied.
     *
     * @param value the <code>byte []</code> value to be represented
     */
    public Base64BinaryAttribute(byte [] value) {
        super(identifierURI);

        // Shouldn't happen, but just in case...
        if (earlyException != null) {
            throw earlyException;
        }
        // This will throw a NullPointerException if value == null.
        // That's what we want in that case.
        this.value = (byte[])value.clone();
    }

    /**
     * Returns a new <code>Base64BinaryAttribute</code> that represents
     * the xsi:base64Binary at a particular DOM node.
     *
     * @param root the <code>Node</code> that contains the desired value
     * @return a new <code>Base64BinaryAttribute</code> representing the
     *         appropriate value
     * @exception ParsingException if a parsing error occurs
     */
    public static Base64BinaryAttribute getInstance(Node root)
            throws ParsingException {
        if (root.getFirstChild() != null) {
            return getInstance(root.getFirstChild().getNodeValue());
        }
        throw new ParsingException("Error while parsing" 
                + "a Base64BinaryAttribute");
    }

    /**
     * Returns a new <code>Base64BinaryAttribute</code> that represents
     * the xsi:base64Binary value indicated by the string provided.
     *
     * @param value a string representing the desired value
     * @return a new <code>Base64BinaryAttribute</code> representing the
     *         desired value
     * @exception ParsingException if a parsing error occurs
     */
    public static Base64BinaryAttribute getInstance(String value)
            throws ParsingException {
        if (value == null) {
            throw new ParsingException("Can't create a " 
                    + "Base64BinaryAttribute from null input");
        }
        byte [] bytes = null;

        try {
            bytes = Base64.decode(value, false);
        } catch (IOException e) {
            throw new ParsingException("Couldn't parse purported " +
                                       "Base64 string: " + value, e);
        }
        
        return new Base64BinaryAttribute(bytes);
    }

    /**
     * Returns the <code>byte []</code> value represented by this object.
     * Note that this value is cloned before returning to prevent
     * unauthorized modifications.
     *
     * @return the <code>byte []</code> value
     */
    public byte [] getValue() {
        return (byte[])this.value.clone();
    }

    /**
     * Returns true if the input is an instance of this class and if its
     * value equals the value contained in this class.
     *
     * @param o the object to compare
     *
     * @return true if this object and the input represent the same value
     */
    public boolean equals(Object o) {
        if (! (o instanceof Base64BinaryAttribute)) {
            return false;
        }
        Base64BinaryAttribute other = (Base64BinaryAttribute)o;

        return Arrays.equals(this.value, other.value);
    }

    /**
     * Returns the hashcode value used to index and compare this object with
     * others of the same type. Typically this is the hashcode of the backing
     * data object.
     *
     * @return the object's hashcode value
     */
    public int hashCode() {
        int code = this.value[0];

        for (int i = 1; i < this.value.length; i++) {
            code *= 31;
            code += this.value[i];
        }

        return code;
    }

    /**
     * Make the String representation of this object.
     *
     * @return the String representation
     */
    private String makeStringRep() {
        return Base64.encode(this.value);
    }

    /**
     * Returns a String representation.
     *
     * @return the String representation
     */
    public String toString() {
        if (this.strValue == null) {
            this.strValue = makeStringRep();
        }
        return "Base64BinaryAttribute: [" + Constants.nl
            + this.strValue + "]" + Constants.nl;
    }

    /**
     * @return  The String encoding this AttributeValue.
     */
    public String encode() {
        if (this.strValue == null) {
            this.strValue = makeStringRep();
        }

        return this.strValue;
    }

}
