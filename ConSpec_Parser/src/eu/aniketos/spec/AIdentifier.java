package eu.aniketos.spec;

import org.jdom.Element;

/**
 * Represents an identifier of type Integer
 * @author Luca
 *
 */
public class AIdentifier extends AExp {
	public Identifier identifier;
	
	public AIdentifier(){
		super();
		identifier = new Identifier();
	}
	
	/**
	 * type - EXP_INT = 0
	 * Ident - the identifier itself 
	 */
	
	public AIdentifier(int type, Identifier ident){
		exp_type = type;
		identifier=ident;
	}
	
	/**
	 *@Artsiom: Create the class out of an xml element
	 */
	
	public AIdentifier(Element e){
		identifier = new Identifier(e);
	}
	
	/**
	 * trasfrom it to a DOM element
	 */
	@Override
	public Element toElement() {
		return identifier.toElement();
	}

}
