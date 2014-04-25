package eu.aniketos.spec;

import java.util.Iterator;
import java.util.Vector;

import org.jdom.Element;

/**
 * Represents the <after> tag of a conspec policy
 * @author Luca
 *
 * After - defines that the check must be performed AFTER execution of the event 
 */
public class After extends When {
	
	public Return ret;

	public After(){
		identifier=new Identifier();
		parameters = new Vector<Parameter>();
	}
	
	/**
	 *@Artsiom: the constructor for manual initialising:
	 * r - Return of the event
	 * ident - identifier of the event
	 * paramas - parameters of the event.
	 */
	public After(Identifier ident, Vector<Parameter> params, Return r){
		ret=r;
		identifier = ident;
		parameters= params;
	}
	
	/**
	 *@Artsiom: Create the class out of an xml element
	 */
	
    public After(Element e){
    	Iterator<Element> iter = e.getChildren().iterator();
    	identifier = new Identifier(iter.next());
    	parameters = new Vector<Parameter>();
    	ret = null;
    	while(iter.hasNext()){
    		Element next = iter.next();
    		if(next.getName().equals(Tag.parameter_tag)){
    			parameters.add(new Parameter(next));
    		} else if(next.getName().equals(Tag.return_tag)){
    			ret = new Return(next);
    		}
		}
    }
    
	/**
	 * trasfrom it to a DOM element
	 */
    public Element toElement(){
    	Element e = new Element(Tag.after_tag);
    	e.addContent(identifier.toElement());
    	Iterator<Parameter> par_iter = parameters.iterator();
    	while(par_iter.hasNext()){
    		e.addContent(par_iter.next().toElement());
    	}
    	if(ret != null)
    		e.addContent(ret.toElement());
    	return e;
    }

}
