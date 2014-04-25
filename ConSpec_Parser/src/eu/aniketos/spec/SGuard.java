/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.aniketos.spec;

import java.util.Iterator;

import org.jdom.Element;

/**
 *
 */
public class SGuard extends BExp {

    public String type;
    public SExp sexp1;
    public SExp sexp2;

    public SGuard(){
    	type = "";
    	sexp1 = null;
    	sexp2 = null;
    }
    
    /*
     * Manual initialisation of SGuard class
     * tag - type of string relation
     * sexp1, sexp2 - arguaments of string type
     */
    public SGuard(String tag, SExp s1, SExp s2) {
        type = tag;
        sexp1 = s1;
        sexp2 = s2;
    }

    /**
	 *@Artsiom: Create the class out of an xml element
	 */
    public SGuard(Element e){
    	type = e.getName();
    	Iterator iter = e.getChildren().iterator();
        Element el = (Element)iter.next();
    	sexp1 = SExp.generateSExp(el);
    	sexp2 = SExp.generateSExp((Element)iter.next());
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
