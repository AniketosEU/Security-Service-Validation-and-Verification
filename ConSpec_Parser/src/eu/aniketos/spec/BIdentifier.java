package eu.aniketos.spec;

import org.jdom.Element;


/**
 * Represents an identifier of type Boolean
 * @author Luca
 *
 */
public class BIdentifier extends BExp {
	
	public Identifier identifier;
	
	public BIdentifier(){
		exp_type=0;
		identifier = new Identifier();
	}

	/**
	 * type - EXP_BOOL = 1
	 * Ident - the identifier itself 
	 */
	
	public BIdentifier(Identifier ident, int type){
		exp_type=type;
		identifier = ident;
	}
	
	/**
	 *@Artsiom: Create the class out of an xml element
	 */
	public BIdentifier(Element e){
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
