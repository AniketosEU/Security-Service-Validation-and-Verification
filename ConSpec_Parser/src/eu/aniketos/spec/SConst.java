/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.aniketos.spec;

import org.jdom.Element;
import org.jdom.Text;

/**
 *Represents a string constant
 * @author Administrator
 */
public class SConst extends SExp {

    public String text;

	/**
	 * Manual initialisation of SConst class
	 * s - string constant
	 */
    public SConst(String s) {
        text = s;
    }

    //XXX:OLD METHOD, TRY THIS IF THE NEW ONE GIVES PROBLEMS
//    public SConst(Element e){
//		text = (e.getTextTrim());
//	}
    
    /**
	 *@Artsiom: Create the class out of an xml element
	 */
    public SConst(Element e){
		text = ((Text)e.getContent().iterator().next()).getTextTrim();
	}
    
	/**
	 * trasfrom it to a DOM element
	 */
    public Element toElement() {
        Element elm = new Element(Tag.sconst_tag);
        elm.setText(text);
        return elm;
    }

}
