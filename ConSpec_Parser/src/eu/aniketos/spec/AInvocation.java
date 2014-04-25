package eu.aniketos.spec;

import org.jdom.Element;


/**
 * Represent an invocation that returns an int
 * @author Luca
 *
 */
public class AInvocation extends AExp {

	public Invocation invocation;
	
	public AInvocation(){
		super();
		invocation=new Invocation();
	}
	
	
	/**
	 * invoke - invocation that returns int 
	 */
	public AInvocation(Invocation invoke){
		invocation=invoke;
	}
	
	/**
	 *@Artsiom: Create the class out of an xml element
	 */
	public AInvocation(Element e){
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
