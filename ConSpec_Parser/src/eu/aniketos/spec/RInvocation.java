package eu.aniketos.spec;

import org.jdom.Element;

public class RInvocation extends RExp {

	public Invocation invocation;
	
	public RInvocation(){
		super();
		invocation=new Invocation();
	}
	
	
	/**
	 * invoke - invocation that returns int 
	 */
	public RInvocation(Invocation invoke){
		invocation=invoke;
	}
	
	/**
	 *@Artsiom: Create the class out of an xml element
	 */
	public RInvocation(Element e){
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