package eu.aniketos.spec;

import java.util.Iterator;
import java.util.Vector;

import org.jdom.Element;

/**
 * Represents a Definition tag of the conspec policy
 * @author Artsiom
 *
 */

public class Definition {
	public Identifier identifier;
	public Vector<Parameter> parameters;
	
	public Definition(){
		identifier=null;
		parameters = new Vector<Parameter>();
	};
	
	
	public Definition(Identifier ident, Vector<Parameter> params){
		identifier=ident;
		parameters = params;
	}
	
    public Definition(Element e){
    	Iterator<Element> iter = e.getChildren().iterator();
    	identifier = new Identifier(iter.next());
    	parameters = new Vector<Parameter>();
    	while(iter.hasNext()){
    		Element next = iter.next();
    		parameters.add(new Parameter(next));
		}
    }
    
	/**
	 * transfrom it to a DOM element
	 */
    
    public Element toElement(){
    	Element e = new Element(Tag.definition_tag);
    	e.addContent(identifier.toElement());
    	Iterator<Parameter> par_iter = parameters.iterator();
    	while(par_iter.hasNext()){
    		e.addContent(par_iter.next().toElement());
    	}
    	return e;
    }
	
}
