/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.aniketos.spec;

import org.jdom.Element;

/**
 *
 * @author Administrator
 */
public class Parallel extends Path {

    private Path op1;
    private Path op2;

    public Parallel(Path a, Path b) {
        op1 = a;
        op2 = b;
    }

	/**
	 * trasfrom it to a DOM element
	 */
    public Element toElement() {
        Element e = new Element(Path.parallel_tag);
        e.addContent(op1.toElement());
        e.addContent(op2.toElement());
        return e;
    }

}
