package eu.aniketos.spec;

	import java.util.Iterator;

import org.jdom.Element;

	/**
	 * Represents a guard that has integer operands
	 * @author Administrator
	 */
	public class RGuard extends BExp {


		public String type;
		public RExp exp1;
		public RExp exp2;
		public AExp aexp1;
		public AExp aexp2;
		
		/**
		 * Intager guard - an operation returning boolean result. E.g., a>b
		 * tag - type of the operation
		 * s1, s2 - arguments 
		 */
	    public RGuard(String tag, RExp s1, RExp s2) {
	        type = tag;
	        exp1 = s1;
	        exp2 = s2;
	        aexp1=null;
	    	aexp2=null;
	    }

	    public RGuard(String tag, AExp e1, RExp e2) {
	        type = tag;
	        exp1 = null;
	        exp2 = e2;
	        aexp1=e1;
	    	aexp2=null;
	    }
	    
	    public RGuard(String tag, RExp e1, AExp e2) {
	        type = tag;
	        exp1 = e1;
	        exp2 = null;
	        aexp1=null;
	    	aexp2=e2;
	    }
	    
	    public RGuard(String tag, AExp e1, AExp e2) {
	        type = tag;
	        exp1 = null;
	        exp2 = null;
	        aexp1=e1;
	    	aexp2=e2;
	    }
	    
	    /**
		 *@Artsiom: Create the class out of an xml element
		 */
	    public RGuard(Element e){
	    	type = e.getName();
	    	Iterator iter = e.getChildren().iterator();
	    	exp1=null;
	    	exp2=null;
	    	
	    	Element elem = (Element)iter.next();
	    	aexp1 = AExp.generateAExp(elem);
	    	if(aexp1==null) exp1 = RExp.generateRExp(elem);
	    	
	    	elem = (Element)iter.next();
	    	aexp2 = AExp.generateAExp(elem);
	    	if(aexp2==null) exp2 = RExp.generateRExp(elem);
	    }

		/**
		 * trasfrom it to a DOM element
		 */
	    public Element toElement() {
	        Element sguard_elm = new Element(type);
	        if(exp1!=null) sguard_elm.addContent(exp1.toElement());
	        if(aexp1!=null) sguard_elm.addContent(aexp1.toElement());
	        if(exp2!=null) sguard_elm.addContent( exp2.toElement());
	        if(aexp2!=null) sguard_elm.addContent(aexp2.toElement());
	        return sguard_elm;
	    }

	}
