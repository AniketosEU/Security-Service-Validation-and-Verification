/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.aniketos.spec;

import org.jdom.Element;
import org.jdom.Text;

/**
 * Represents a boolean constant (true or false)
 * @author Administrator
 */
public class BConst extends BExp {
    

    public boolean val;

    public BConst() {
        val = false;
    }
    
	/**
	 * b - boolean value 
	 */
    public BConst(boolean b) {
        val = b;
    }
    
    /**
	 *@Artsiom: Create the class out of an xml element
	 */
    public BConst(Element e){
		String s = ((Text)e.getContent().iterator().next()).getTextTrim();
		val = Boolean.parseBoolean(s);
	}
	
	/**
	 * trasfrom it to a DOM element
	 */
    public Element toElement() {
        Element elm = new Element(Tag.bconst_tag);
        elm.addContent(""+val);
        return elm;
    }

}
