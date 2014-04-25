package eu.aniketos.spec;

import org.jdom.Element;

/**
 * Represent an invocation that returns a string
 * @author Luca
 *
 */
public class SInvocation extends SExp {

	public Invocation invocation;
	
	/**
	 * invoke - invocation that returns string 
	 */
	public SInvocation(Invocation invoke){
		invocation=invoke;
	}
	
	/**
	 *@Artsiom: Create the class out of an xml element
	 */
	public SInvocation(Element e){
		invocation = new Invocation(e);
	}
	
	/**
	 * trasfrom it to a DOM element
	 */
	@Override
	public Element toElement() {
		return invocation.toElement();
	}

}
