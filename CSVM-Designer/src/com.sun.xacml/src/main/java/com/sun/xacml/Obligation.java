
/*
 * @(#)Obligation.java
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

package com.sun.xacml;

import com.sun.xacml.attr.AttributeFactory;
import com.sun.xacml.attr.AttributeValue;

import com.sun.xacml.cond.EvaluationResult;
import com.sun.xacml.ctx.Attribute;
import com.sun.xacml.ctx.Result;
import com.sun.xacml.debug.Locatable;
import com.sun.xacml.debug.RuntimeInfo;
import com.sun.xacml.debug.RuntimeInfo.ELEMENT_TYPE;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Represents the ObligationType XML type in XACML. This also stores all the
 * AttriubteAssignmentType XML types.
 *
 * @since 1.0
 * @author Seth Proctor
 */
public class Obligation implements Locatable
{

    // the obligation id
    private URI id;

    // effect to fulfill on, as defined in Result
    private int fulfillOn;

    // the attribute assignments
    private List<Attribute> assignments;
    
    // if the obligation has dynamic attribute assignments, i.e., if attribute values are assigned at runtime, default is false
    private boolean dynamic_attributes;
    
    private RuntimeInfo src;
    
    public static final Set<Obligation> EMPTY_SET = Collections.<Obligation>emptySet();

    /**
     * Constructor that takes all the data associated with an obligation.
     * The attribute assignment list contains <code>Attribute</code> objects,
     * but only the fields used by the AttributeAssignmentType are used.
     *
     * @param id the obligation's id
     * @param fulfillOn the effect denoting when to fulfill this obligation
     * @param assignments a <code>List</code> of <code>Attribute</code>s
     */
    public Obligation(URI id, int fulfillOn, List<Attribute> assignments) {
    	init(id, fulfillOn, assignments, false);
    }
    
    public Obligation(URI id, int fulfillOn, List<Attribute> assignments, boolean dynamic) {
    	init(id, fulfillOn, assignments, dynamic);

    }
    
    private void init(URI id, int fulfillOn, List<Attribute> assignments, boolean dynamic) {
        this.id = id;
        this.fulfillOn = fulfillOn;
        this.dynamic_attributes = dynamic;
        this.assignments = Collections.
            unmodifiableList(new ArrayList<Attribute>(assignments));
    }
    
    private Obligation(URI id, int fulfillOn, boolean dynamic) {
    	this.id = id;
    	this.fulfillOn = fulfillOn;
    	this.dynamic_attributes = dynamic;
    }

    /**
     * Creates an instance of <code>Obligation</code> based on the DOM root
     * node.
     *
     * @param root the DOM root of the ObligationType XML type
     *
     * @return an instance of an obligation
     *
     * @throws ParsingException if the structure isn't valid
     */
    public static Obligation getInstance(Node root) throws ParsingException {
    	RuntimeInfo src = RuntimeInfo.getRuntimeInfo(root, ELEMENT_TYPE.OBLIGATION);
        if (root.getNodeType() != Node.ELEMENT_NODE) {
            throw new ParsingException("Can't create an Obligation out of" 
                   + " a node that is not an Element node"
                   + (src != null ? src.getLocationMsgForError() : ""));
        }
        if (!root.getLocalName().equals("Obligation")) {
            throw new ParsingException("Can't create an Obligation with a "
                    + root.getLocalName() + " element"
                    + (src != null ? src.getLocationMsgForError() : ""));
        }
        URI id;
        int fulfillOn = -1;
        List<Attribute> assignments = new ArrayList<Attribute>();

        AttributeFactory attrFactory = AttributeFactory.getInstance();
        NamedNodeMap attrs = root.getAttributes();

        try {
            id = new URI(attrs.getNamedItem("ObligationId").getNodeValue());
        } catch (Exception e) {
            throw new ParsingException("Error parsing required attriubte " 
            		+ "ObligationId" + (src != null ? src.getLocationMsgForError() : ""), e);
        }

        String effect = null;

        try {
            effect = attrs.getNamedItem("FulfillOn").getNodeValue();
        } catch (Exception e) {
            throw new ParsingException("Error parsing required attriubte " 
            		+ "FulfillOn" + (src != null ? src.getLocationMsgForError() : ""), e);
        }

        if (effect.equals(Result.PERMIT)) {
            fulfillOn = Result.DECISION_PERMIT;
        } else if (effect.equals(Result.DENY)) {
            fulfillOn = Result.DECISION_DENY;
        } else {
            throw new ParsingException("Invlid Effect type: " + effect
            		+ (src != null ? src.getLocationMsgForError() : ""));
        }

        boolean dynamic = false;
        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE
                    && node.getLocalName().equals("AttributeAssignment")) {
            	URI attrId = null;
                try {
                    attrId =
                        new URI(node.getAttributes().
                                getNamedItem("AttributeId").getNodeValue());
                    AttributeValue attrValue = attrFactory.createValue(node);
                    dynamic |= attrValue.isDynamic();
                    assignments.add(new Attribute(attrId, null, attrValue));
                } catch (URISyntaxException use) {
                    throw new ParsingException("Error parsing URI: " + use.getMessage()
                    		+ (src != null ? src.getLocationMsgForError() : ""), use);
                } catch (UnknownIdentifierException uie) {
                    throw new ParsingException("Unknown AttributeId: " + uie.getMessage()
                    		+ (src != null ? src.getLocationMsgForError() : ""), uie);
                } catch (Exception e) {
                    throw new ParsingException("Error parsing attribute " + attrId 
                    		+ "assignments"
                    		+ (src != null ? src.getLocationMsgForError() : ""), e);
                }
            }
        }

        Obligation obligation = new Obligation(id, fulfillOn, assignments, dynamic);
        if ( src != null ) {
            obligation.src = src;
            src.setXACMLObject(obligation);
        }
        return obligation; 
    }
    
    /**
     * Returns the id of this obligation
     *
     * @return the id
     */
    public URI getId() {
        return this.id;
    }

    /**
     * Returns effect that will cause this obligation to be included in a
     * response
     *
     * @return the fulfillOn effect
     */
    public int getFulfillOn() {
        return this.fulfillOn;
    }
    

	public RuntimeInfo getRuntimeInfo() {
		return src;
	}

    /**
     * Returns the attribute assignment data in this obligation. The
     * <code>List</code> contains objects of type <code>Attribute</code>
     * with only the correct attribute fields being used.
     *
     * @return the assignments
     */
    public List<Attribute> getAssignments() {
        return this.assignments;
    }

    /**
     * Encodes this <code>Obligation</code> into its XML form and writes this
     * out to the provided <code>OutputStream<code> with no indentation.
     *
     * @param output a stream into which the XML-encoded data is written
     * @param charsetName  the character set to use in encoding of strings.
     *                     This may be null in which case the platform 
     *                     default character set will be used.
     *  
     * @throws UnsupportedEncodingException 
     */
    public void encode(OutputStream output, String charsetName)
            throws UnsupportedEncodingException {
        encode(output, charsetName, new Indenter(0));
    }

    /**
     * Encodes this <code>Obligation</code> into its XML form and writes this
     * out to the provided <code>OutputStream<code> with indentation.
     *
     * @param output  a stream into which the XML-encoded data is written
     * @param charsetName  the character set to use in encoding of strings.
     *                     This may be null in which case the platform
     *                      default character set will be used.
     * @param indenter  an object that creates indentation strings
     * 
     * @throws UnsupportedEncodingException 
     */
    public void encode(OutputStream output, String charsetName,
                       Indenter indenter) 
            throws UnsupportedEncodingException {
        PrintStream out;
        if(charsetName == null) {
            out = new PrintStream(output);
        } else {
            out = new PrintStream(output, false, charsetName);
        }
        String indent = indenter.makeString();

        out.println(indent + "<Obligation ObligationId=\"" + this.id.toString()
                + "\" FulfillOn=\"" + Result.DECISIONS.get(this.fulfillOn) 
                                                       + "\">");

        indenter.in();

        Iterator<Attribute> it = this.assignments.iterator();

        while (it.hasNext()) {
            Attribute attr = it.next();
            out.println(indenter.makeString() 
                    + "<AttributeAssignment AttributeId=\"" 
                    + attr.getId().toString() + "\" DataType=\"" 
                    + attr.getValue().getType().toString() + "\">" 
                    + attr.getValue().encode() 
                    + "</AttributeAssignment>");
        }

        indenter.out();

        out.println(indent + "</Obligation>");
    }

    /**
     * @param indenter
     * @return  the string representation of this obligation encoded in XML
     */
    public String toString(Indenter indenter) {
        String indent = indenter.makeString();
        
        String result =indent + "<Obligation ObligationId=\"" 
            + this.id.toString() + "\" FulfillOn=\"" 
            + Result.DECISIONS.get(this.fulfillOn) + "\">" + Constants.nl;

        indenter.in();
        
        Iterator<Attribute> it = this.assignments.iterator();

        while (it.hasNext()) {
            Attribute attr = it.next();
            result += indenter.makeString() +
                        "<AttributeAssignment AttributeId=\"" +
                        attr.getId().toString() + "\" DataType=\"" +
                        attr.getValue().getType().toString() + "\">" +
                        attr.getValue().encode() +
                        "</AttributeAssignment>" + Constants.nl;
        }

        indenter.out();

        result += indent + "</Obligation>" + Constants.nl;
        return result;
    }
    
    public Obligation evaluate(EvaluationCtx context) {
    	if ( ! dynamic_attributes ) {
    		return this;
    	} else {
    		Obligation obl = new Obligation(this.id, this.fulfillOn, this.dynamic_attributes);
    		
    		List<Attribute> tmpAttr = new ArrayList<Attribute>();
    		for ( Attribute attr : this.assignments ) {
    			if (  attr.getValue().isDynamic()) {
    				EvaluationResult evaluationResult =attr.getValue().evaluate(context); 
    				AttributeValue evaluated = evaluationResult.getAttributeValue(); 
    				tmpAttr.add(new Attribute(attr.getId(), attr.getIssuer(), evaluated));
    			} else {
    				tmpAttr.add(attr);
    			}
    		}
    		obl.assignments =  Collections.unmodifiableList(tmpAttr);
    			//    	this.assignments = new ArrayList<Attribute>();
    		return obl;
    	}
    }
    
    public boolean isDynamic() {
    	return dynamic_attributes;
    }

}
