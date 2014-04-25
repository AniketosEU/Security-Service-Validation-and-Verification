package eu.aniketos.spec;

import java.util.Iterator;
import java.util.Vector;

import org.jdom.Element;

/**
 * Represents the <before> tag of a conspec policy
 * @author Luca
 *
 *The class shows that something has to be checked BEFORE execution of an event
 */
public class Before extends When {
	
	public Before(){
		identifier=new Identifier();
		parameters = new Vector<Parameter>();
	}
	
	/**
	 *  ident - identifier of the event
	 *  params - parameters of the event
	 */
	public Before(Identifier ident, Vector<Parameter> params){
		identifier=ident;
		parameters = params;
	}
	
	/**
	 *@Artsiom: Create the class out of an xml element
	 */
    public Before(Element e){
    	Iterator<Element> iter = e.getChildren().iterator();
    	identifier = new Identifier(iter.next());
    	parameters = new Vector<Parameter>();
    	while(iter.hasNext()){
    		Element next = iter.next();
    		parameters.add(new Parameter(next));
		}
    }
    
	/**
	 * trasfrom it to a DOM element
	 */
    public Element toElement(){
    	Element e = new Element(Tag.before_tag);
    	e.addContent(identifier.toElement());
    	Iterator<Parameter> par_iter = parameters.iterator();
    	while(par_iter.hasNext()){
    		e.addContent(par_iter.next().toElement());
    	}
    	return e;
    }
    
}
