package eu.aniketos.spec;

import org.jdom.Element;


	/**
	 * Abstract model of an Aritmetical Expression
	 * It must be one of the following children:
	 * IConst - integer constant
	 * AIdentifier - integer identifier (a variable) 
	 * IOp - integer operation: 
	 * 		sum_tag - sum
	 * 		dif_tag = difference
	 * 		mul_tag - multiplication
	 * 		mod_tag = module
	 * AInvocation - integer invocation (function returning integer)
	 *
	 * @author Gabriele Costa & Luca Wiegand
	 */
	public abstract class RExp extends Exp {

		/*
		public AExp(){}
		
		public AExp(int type){
			exp_type=type;
		}
		*/

		/**
		 * trasfrom it to a DOM element
		 */
	    public abstract Element toElement();
	    
	    /**
	     * @Artsiom: this is the main function which generates the expression out of an xml element.
	     */
	    public static SExp generateSExp(Element e) {
	    	return Exp.generateSExp(e);
	    }

	}