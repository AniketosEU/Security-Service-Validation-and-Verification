/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.aniketos.spec;

import java.util.Iterator;

import org.jdom.Element;

/**
 * Represents a guard that has integer operands
 * @author Administrator
 */
public class IGuard extends BExp {


	public String type;
	public AExp sexp1;
	public AExp sexp2;

	/**
	 * Intager guard - an operation returning boolean result. E.g., a>b
	 * tag - type of the operation
	 * s1, s2 - arguments 
	 */
    public IGuard(String tag, AExp s1, AExp s2) {
        type = tag;
        sexp1 = s1;
        sexp2 = s2;
    }
    
    /**
	 *@Artsiom: Create the class out of an xml element
	 */
    public IGuard(Element e){
    	type = e.getName();
    	Iterator iter = e.getChildren().iterator();
    	sexp1 = AExp.generateAExp((Element)iter.next());
    	sexp2 = AExp.generateAExp((Element)iter.next());
    }

	/**
	 * trasfrom it to a DOM element
	 */
    public Element toElement() {
        Element sguard_elm = new Element(type);
        sguard_elm.addContent(sexp1.toElement());
        sguard_elm.addContent(sexp2.toElement());
        return sguard_elm;
    }

}
