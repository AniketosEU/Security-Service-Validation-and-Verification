package eu.aniketos.spec;

import org.jdom.Element;

/**
 * Represents an identifier of type String
 * It must be one of the following children:
 * SConst - boolean constant
 * SIdentifier - boolean identifier (a variable) 
 * SAppend - append one string to another;
 * SInvocation - boolean invocation (function returning integer)
 * @author Luca
 *
 */
public class SIdentifier extends SExp {

	public Identifier identifier;
	
	/**
	 * type - EXP_STR = 2;
	 * Ident - the identifier itself 
	 */
	public SIdentifier(int type, Identifier ident){
		exp_type = type;
		identifier=ident;
	}
	
	
	/**
	 *@Artsiom: Create the class out of an xml element
	 */
	
	public SIdentifier(Element e){
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
