/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.aniketos.spec;

import java.util.Iterator;

import org.jdom.Element;


/**
 *Represents a boolean guard -> a guard with boolean operands
 * @author Administrator
 */
public class BGuard extends BExp {

    public String type;
    public BExp l, r;

    public BGuard(){
    	type="";
    	l=null;
    	r=null;
    }
    
	/**
	 * Initialisation 
	 * tag - type of the guard 
	 * b1 - one parameter
	 * b2 - second parameter 
	 */
    public BGuard(String tag, BExp b1, BExp b2) {
        type = tag;
        l = b1;
        r = b2;
    }
    
    /**
	 *@Artsiom: Create the class out of an xml element
	 */
    
    public BGuard(Element e){
    	type = e.getName();
        
    	Iterator iter = e.getChildren().iterator();
    	l = BExp.generateBExp((Element)iter.next());
    	r = BExp.generateBExp((Element)iter.next());
    }

	/**
	 * trasfrom it to a DOM element
	 */
    public Element toElement() {
        Element sguard_elm = new Element(type);
        sguard_elm.addContent(l.toElement());
        sguard_elm.addContent(r.toElement());
        return sguard_elm;
    }

}
