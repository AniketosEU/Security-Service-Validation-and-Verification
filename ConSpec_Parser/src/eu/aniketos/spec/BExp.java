/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.aniketos.spec;

import org.jdom.Element;

/**
 *Abstract model of a boolean Expression
 * It must be one of the following children:
 * BConst - boolean constant
 * BIdentifier - boolean identifier (a variable) 
 * BOp - boolean operation: 
 * 		and_tag - and
 * 		not_tag = not
 * 		or_tag - or
 * BInvocation - boolean invocation (function returning boolean)
 * BGuard - boolean check
 * IGuard - integer check
 * SGuard - string check
 *
 * @author Administrator
 */
public abstract class BExp extends Exp {
	/**
	 * trasfrom it to a DOM element
	 */
    public abstract Element toElement();

    /**
	 *@Artsiom: Generate the (CHILD) class out of an xml element
	 */
    public static BExp generateBExp(Element e) {
    	return Exp.generateBExp(e);
    }

}
