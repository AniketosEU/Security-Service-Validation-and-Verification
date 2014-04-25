/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.aniketos.spec;

import java.util.Iterator;

import org.jdom.Element;

/**
 *
 * @author Administrator
 */
public class IOp extends AExp {

    //public static final String strlen_tag = "strlen"; se si vuol fare fare un'altra classe

	public String type;
	public AExp exp1;
	public AExp exp2;

	
    /**
     * Initialisation of integer operation
     * tag - type of operation
     * b1, b2 - parameters
     * in case of NOT: b2 = null.
     */
    public IOp(String tag, AExp e1, AExp e2) {
        type = tag;
        exp1 = e1;
        exp2 = e2;
    }
    
    /**
	 *@Artsiom: Create the class out of an xml element
	 */
    public IOp(Element e){
    	type = e.getName();
    	Iterator iter = e.getChildren().iterator();
    	exp1 = AExp.generateAExp((Element)iter.next());
    	exp2 = AExp.generateAExp((Element)iter.next());
    }

	/**
	 * trasfrom it to a DOM element
	 */
    public Element toElement() {
        Element elm = new Element(type);
        elm.addContent(exp1.toElement());
        elm.addContent( exp2.toElement());
        return elm;
    }
}
