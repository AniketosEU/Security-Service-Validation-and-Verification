/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.aniketos.spec;

import java.util.Iterator;

import org.jdom.Element;


/**
 * represents a boolean operation (AND and NOT)
 * @author Administrator
 */
public class BOp extends BExp {

	public String type;
	public BExp l, r;

	public BOp(){
		type="";
		l=null;
		r=null;
	}

    /**
     * Initialisation of boolean operation
     * tag - type of operation
     * b1, b2 - parameters
     * in case of NOT: b2 = null.
     */
    public BOp(String tag, BExp b1, BExp b2) {
        type = tag;
        l = b1;
        if(!type.equals(Tag.not_tag))
        	r = b2;
    }
    
    /**
	 *@Artsiom: Create the class out of an xml element
	 */
    public BOp(Element e){
    	type = e.getName();
    	Iterator iter = e.getChildren().iterator();
    	l = BExp.generateBExp((Element)iter.next());
    	if(!type.equals(Tag.not_tag))
    		r = BExp.generateBExp((Element)iter.next());
    }

	/**
	 * trasfrom it to a DOM element
	 */
    public Element toElement() {
        Element sguard_elm = new Element(type);
        sguard_elm.addContent(l.toElement());
        if(!type.equals(Tag.not_tag))
        	sguard_elm.addContent(r.toElement());
        return sguard_elm;
    }
}
