package eu.aniketos.spec;

import org.jdom.Element;

/**
 * Represent an invocation that returns a boolean
 * @author Luca
 *
 */
public class BInvocation extends BExp {

	public Invocation invocation;
	
	public BInvocation(){
		exp_type=0;
		invocation = new Invocation();
	}

	
	/**
	 * invoke - invocation that returns bool
	 */
	public BInvocation(Invocation invoke){
		invocation = invoke;
	}
	
	/**
	 *@Artsiom: Create the class out of an xml element
	 */
	public BInvocation(Element e){
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
