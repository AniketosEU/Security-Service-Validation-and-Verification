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
public class IConst extends AExp {

    public int val;

    /**
	 *integer constant
	 */
    public IConst(int i) {
        val = i;
    }
    
    /**
	 *@Artsiom: Create the class out of an xml element
	 */
    public IConst(Element e){
		String s = ((Text)e.getContent().iterator().next()).getTextTrim();
		val = Integer.parseInt(s);
		
	}

	/**
	 * trasfrom it to a DOM element
	 */
    public Element toElement() {
        Element elm = new Element(Tag.iconst_tag);
        elm.addContent(""+val);
        return elm;
    }
}
