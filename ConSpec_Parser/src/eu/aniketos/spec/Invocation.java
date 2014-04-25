package eu.aniketos.spec;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Vector;

import org.jdom.Element;
import org.jdom.Text;

/**
 * Represents a generic invocation of a method
 * @author Luca
 *
 */
public class Invocation extends Exp {

	public Identifier function;
	public Identifier target;
	public Vector<Exp> arguments;
	
	public Invocation(){
		function = new Identifier();
		target = new Identifier();
		arguments = new Vector<Exp>();
	}
	
	/**
	 * Manual initialisation of Invocation class
	 * func - name of the function
	 * targ - result (I think)
	 * arg - a vector of arguments 
	 *
	 */
	public Invocation(Identifier func, Identifier targ, Vector<Exp> arg){
		function = func;
		target = targ;
		arguments = arg;
	}
	
	/**
	 *@Artsiom: Create the class out of an xml element
	 */
	public Invocation( Element e){
		Iterator<Element> iter = e.getChildren().iterator();
		
		function = new Identifier(iter.next());
		
		arguments = new Vector<Exp>();
		target = null;
		while(iter.hasNext()){
			Element next = iter.next();
			if(next.getName().equals(Tag.identifier_tag))
				target = new Identifier(next);
			else
				if(next.getName().equals(Tag.s_identifier_tag))
					target = new Identifier(next);
				else
					if(next.getName().equals(Tag.b_identifier_tag))
						target = new Identifier(next);
					else
						if(next.getName().equals(Tag.i_identifier_tag))
							target = new Identifier(next);
						else
							if(next.getName().equals(Tag.r_identifier_tag))
								target = new Identifier(next);	
							else{
								Element el = (Element)next.getChildren().iterator().next();
								arguments.add(Exp.generateExp(el));
							}
		}
	}
	
	/**
	 * trasfrom it to a DOM element
	 */
	@Override
	public Element toElement() {
		Element e = new Element(Tag.invocation_tag);
		e.addContent(function.toElement());
		
		if(target !=null)
			e.addContent(target.toElement());
		
		Iterator<Exp> iter = arguments.iterator();
		while(iter.hasNext()){
			Element arg_elm = new Element(Tag.argument_tag);
			arg_elm.addContent(iter.next().toElement());
			e.addContent(arg_elm);
		}
		return e;
	}

}
