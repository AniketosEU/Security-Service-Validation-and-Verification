package eu.aniketos.spec;

import org.jdom.Element;

public class Return extends Parameter {

	public Return(){
		super();
	}
	
	/**
	 * Manual initialisation of Parameter class
	 * base - one of the base types: INT = "int", BOOL = "bool", STRING="string"
	 * ident - name of the identifier
	 */
	public Return(BaseType base, Identifier ident){
		super(base, ident);
	}
	/**
	 *@Artsiom: Create the class out of an xml element
	 */
	public Return(Element e) {
		super(e);
	}

	/**
	 * trasfrom it to a DOM element
	 */
	public Element toElement() {
		Element e = new Element(Tag.return_tag);
		e.addContent(type.toElement());
		e.addContent(identifier.toElement());
		return e;
	}
}
