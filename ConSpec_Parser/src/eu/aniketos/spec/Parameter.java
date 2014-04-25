package eu.aniketos.spec;

import java.util.Iterator;

import org.jdom.Element;

/**
 * Represents a method parameter
 * @author Luca
 *
 */
public class Parameter {

	public BaseType type;
	public Identifier identifier;
	
	public Parameter(){
	}
	
	/**
	 * Manual initialisation of Parameter class
	 * base - one of the base types: INT = "int", BOOL = "bool", STRING="string", REAL="real"
	 * ident - name of the identifier
	 */
	public Parameter(BaseType base, Identifier ident){
		identifier = ident;
		type = base;
	}
	
	/**
	 *@Artsiom: Create the class out of an xml element
	 */
	public Parameter(Element e){
		Iterator<Element> iter = e.getChildren().iterator();
    	type = new BaseType(iter.next());
    	identifier = new Identifier(iter.next());
	}
	
	/**
	 * trasfrom it to a DOM element
	 */
    public Element toElement(){
    	Element e = new Element(Tag.parameter_tag);
    	e.addContent(type.toElement());
    	e.addContent(identifier.toElement());
    	return e;
    }
}
