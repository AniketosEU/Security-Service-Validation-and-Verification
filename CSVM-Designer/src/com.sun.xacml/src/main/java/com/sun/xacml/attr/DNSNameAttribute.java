
/*
 * @(#)DNSNameAttribute.java
 *
 * Copyright 2005 Sun Microsystems, Inc. All Rights Reserved.
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

import java.net.URI;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

import com.sun.xacml.ParsingException;


/**
 * Represents the DNSName datatype introduced in XACML 2.0. All objects of
 * this class are immutable and all methods of the class are thread-safe.
 *
 * @since 2.0
 * @author Seth Proctor
 */
public class DNSNameAttribute extends AttributeValue
{

    /**
     * Official name of this type
     */
    public static final String identifier =
        TypeIdentifierConstants.DNSNAME;

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
            identifierURI = new URI(identifier);
        } catch (Exception e) {
            earlyException = new IllegalArgumentException();
            earlyException.initCause(e);
        }
    }

    // the required hostname
    private String hostname;

    // the optional port range
    private PortRange range;

    // true if the hostname starts with a '*'
    private boolean isSubdomain = false;

    /**
     * Creates the new <code>DNSNameAttribute</code> with only the required
     * hostname component.
     *
     * @param hostname the host name component of the address
     */
    public DNSNameAttribute(String hostname) {
        this(hostname, new PortRange());
    }

    /**
     * Creates the new <code>DNSNameAttribute</code> with the optional
     * port range component.
     *
     * @param hostname the host name component of the address
     * @param range the port range
     */
    public DNSNameAttribute(String hostname, PortRange range) {
        super(identifierURI);

        // shouldn't happen, but just in case...
        if (earlyException != null) {
            throw earlyException;
        }
        // verify that the hostname is valid before we store it
        if (! isValidHostName(hostname)) {
            System.err.println("FIXME: throw error about bad hostname");
        }
        // see if it started with a '*' character
        if (hostname.charAt(0) == '*') {
            this.isSubdomain = true;
        }
        this.hostname = hostname;
        this.range = range;
    }

    /**
     * Private helper that tests whether the given string is valid.
     */
    private boolean isValidHostName(String hostname) {
        /*
          hostname      =  *( domainlabel "." ) toplabel [ "." ]
          domainlabel   =  alphanum | alphanum *( alphanum | "-" ) alphanum
          toplabel      =  alpha | alpha *( alphanum | "-" ) alphanum
        */
        
        String domainlabel = "\\w[[\\w|\\-]*\\w]?";
        String toplabel = "[a-zA-Z][[\\w|\\-]*\\w]?";
        String pattern = "[\\*\\.]?[" + domainlabel + "\\.]*" + toplabel +
            "\\.?";
        
        return hostname.matches(pattern);
    }

    /**
     * Returns a new <code>DNSNameAttribute</code> that represents
     * the name at a particular DOM node.
     *
     * @param root the <code>Node</code> that contains the desired value
     *
     * @return a new <code>DNSNameAttribute</code> representing the
     *         appropriate value (null if there is a parsing error)
     * @throws ParsingException 
     * @throws DOMException 
     */
    public static DNSNameAttribute getInstance(Node root) 
            throws DOMException, ParsingException {
        if (root.getFirstChild() != null) {
            return getInstance(root.getFirstChild().getNodeValue());
        }
        throw new ParsingException("Error while parsing" 
                + "a DNSNameAttribute");
    }

    /**
     * Returns a new <code>DNSNameAttribute</code> that represents
     * the name indicated by the <code>String</code> provided.
     *
     * @param value a string representing the name
     *
     * @return a new <code>DNSNameAttribute</code>
     * @throws ParsingException 
     */
    public static DNSNameAttribute getInstance(String value) 
            throws ParsingException {
        if (value == null) {
            throw new ParsingException("Can't create a " 
                    + "DNSNameAttribute from null input");
        }
        int portSep = value.indexOf(':');

        if (portSep == -1) {
            // there is no port range, so just use the name
            return new DNSNameAttribute(value);
        }
        // split the name and the port range
        String hostname = value.substring(0, portSep);
        PortRange range =
            PortRange.getInstance(value.substring(portSep + 1,
                                                  value.length()));
        return new DNSNameAttribute(hostname, range);
    }

    /**
     * Returns the host name represented by this object.
     *
     * @return the host name
     */
    public String getHostName() {
        return this.hostname;
    }

    /**
     * Returns the port range represented by this object which will be
     * unbound if no range was specified.
     *
     * @return the port range
     */
    public PortRange getPortRange() {
        return this.range;
    }

    /**
     * Returns true if the leading character in the hostname is a '*', and
     * therefore represents a matching subdomain, or false otherwise.
     *
     * @return true if the name represents a subdomain, false otherwise
     */
    public boolean isSubdomain() {
        return this.isSubdomain;
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
        if (! (o instanceof DNSNameAttribute)) {
            return false;
        }
        DNSNameAttribute other = (DNSNameAttribute)o;

        if (! this.hostname.toUpperCase().equals(
                other.hostname.toUpperCase())) {
            return false;
        }

        if (! this.range.equals(other.range)) {
            return false;
        }

        return true;
    }

    /**
     * Returns the hashcode value used to index and compare this object with
     * others of the same type.
     *
     * @return the object's hashcode value
     */
    public int hashCode() {
        return encode().hashCode();
    }

    /**
     * Converts to a String representation.
     *
     * @return the String representation
     */
    public String toString() {
        return "DNSNameAttribute: \"" + encode() + "\"";
    }

    /**
     * @return  The String encoding this AttributeValue.
     */
    public String encode() {
        if (this.range.isUnbound()) {
            return this.hostname;
        }

        return this.hostname + ":" + this.range.encode();
    }

}
