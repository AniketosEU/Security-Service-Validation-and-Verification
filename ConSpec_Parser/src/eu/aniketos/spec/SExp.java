/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.aniketos.spec;

import org.jdom.Element;

/**
 *Abstract model of a string Expression
 * @author Administrator
 */
public abstract class SExp extends Exp {
	/**
	 * trasfrom it to a DOM element
	 */
    public abstract Element toElement();

    /**
	 *@Artsiom: Generate the (CHILD) class out of an xml element
	 */
    public static SExp generateSExp(Element e) {
    	return Exp.generateSExp(e);
    }
}
