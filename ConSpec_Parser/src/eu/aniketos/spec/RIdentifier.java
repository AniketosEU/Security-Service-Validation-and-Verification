package eu.aniketos.spec;

import org.jdom.Element;

/**
 * Represents an identifier of type Real
 * @author Artsiom
 *
 */

	public class RIdentifier extends RExp {
		public Identifier identifier;
		
		public RIdentifier(){
			super();
			identifier = new Identifier();
		}
		
		/**
		 * type - EXP_REAL = 3
		 * Ident - the identifier itself 
		 */
		
		public RIdentifier(int type, Identifier ident){
			exp_type = type;
			identifier=ident;
		}
		
		/**
		 *@Artsiom: Create the class out of an xml element
		 */
		
		public RIdentifier(Element e){
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
