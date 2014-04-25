/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.aniketos.spec;

import org.jdom.Element;
import org.jdom.Text;

/**
 *represents an integer constant
 * @author Administrator
 */
public class RConst extends RExp {

    public double val;

    /**
	 *integer constant
	 */
    public RConst(double i) {
        val = i;
    }
    
    /**
	 *@Artsiom: Create the class out of an xml element
	 */
    public RConst(Element e){
		String s = ((Text)e.getContent().iterator().next()).getTextTrim();
		val = Double.parseDouble(s);
		
	}

	/**
	 * trasfrom it to a DOM element
	 */
    public Element toElement() {
        Element elm = new Element(Tag.rconst_tag);
        elm.addContent(""+val);
        return elm;
    }
}
