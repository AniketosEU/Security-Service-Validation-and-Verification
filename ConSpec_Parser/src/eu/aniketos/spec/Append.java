package eu.aniketos.spec;

import java.util.Iterator;

import org.jdom.Element;

public class Append extends SExp {

	public SExp exp1, exp2;
	
	public Append(){
		exp1 = null;
		exp2 = null;
	}
	
	/**
	 * Initialise Append type
	 * l - the string we extend
	 * r - the extending string 
	 */
	public Append(SExp l, SExp r){
		exp1 = l;
		exp2 = r;
	}
	/**
	 *@Artsiom: Create the class out of an xml element
	 */
	public Append(Element e){
	    Iterator iter = e.getContent().iterator();
	    exp1 = SExp.generateSExp((Element)iter.next());
	    exp2 = SExp.generateSExp((Element)iter.next());
	}
	
	/**
	 * trasfrom it to a DOM element
	 */
	@Override
	public Element toElement() {
        Element elm = new Element(Tag.append_tag);
        elm.addContent(exp1.toElement());
        elm.addContent( exp2.toElement());
        return elm;
	}

}
