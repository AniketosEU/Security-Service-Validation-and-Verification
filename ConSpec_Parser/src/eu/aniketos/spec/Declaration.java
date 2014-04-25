/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.aniketos.spec;

import java.io.Serializable;
import java.util.Iterator;

import org.jdom.Element;

/**
 * Represent a conspoec declaration
 * @author Administrator
 */
public class Declaration implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String declarations_tag = "declarations";
    public static final String var_tag = "var";
    public static final String varname_tag = "varname";
    public static final String default_tag = "default";

    public static final String dcl_bool_typ = "bool";
    public static final String dcl_int_typ = "int";
    public static final String dcl_string_typ = "string";
    public static final String dcl_real_typ = "real";

    public BaseType type;
    public Identifier identifier;
    public Exp value;

    /**
     * Initialisation of a declaration (security state)
     * t - type of the declaration
     * n - name of the identifier 
     * e - expression we assign to the identifier  
     */
    public Declaration(BaseType t, String n, Exp e) {
    	
    	// ART: NOTE, that currently the type of the identifier is simple "identifier". 
    	// ART: This should be changed later
    	
    	identifier = new Identifier(n, Tag.identifier_tag);
        type = t;
        value = e;
    }
    
    /**
	 *@Artsiom: Create the class out of an xml element
	 */
    public Declaration(Element e){
    	Iterator<Element> iter = e.getChildren().iterator();
    	type = new BaseType(iter.next());
    	identifier = new Identifier(iter.next());
    	
    	value = Exp.generateExp((Element)iter.next().getChildren().iterator().next());
    }
    
	/**
	 * trasfrom it to a DOM element
	 */
    public Element toElement(){
    	Element e = new Element(Tag.declaration_tag);
    	e.addContent(type.toElement());
    	e.addContent(identifier.toElement());
    	Element val_elm = new Element(Tag.value_tag);
    	val_elm.addContent(value.toElement());
    	e.addContent(val_elm);
    	return e;
    }
}
