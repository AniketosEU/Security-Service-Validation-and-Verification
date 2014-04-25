package eu.aniketos.spec;

import java.util.Iterator;

import org.jdom.Element;

public class ROp extends RExp {

	public String type;
	public RExp exp1;
	public RExp exp2;
	public AExp aexp1;
	public AExp aexp2;

	
    /**
     * Initialisation of real operation
     * tag - type of operation
     * b1, b2 - parameters
     * in case of NOT: b2 = null.
     */
    public ROp(String tag, RExp e1, RExp e2) {
        type = tag;
        exp1 = e1;
        exp2 = e2;
        aexp1=null;
    	aexp2=null;
    }
    
    public ROp(String tag, AExp e1, RExp e2) {
        type = tag;
        exp1 = null;
        exp2 = e2;
        aexp1=e1;
    	aexp2=null;
    }
    
    public ROp(String tag, RExp e1, AExp e2) {
        type = tag;
        exp1 = e1;
        exp2 = null;
        aexp1=null;
    	aexp2=e2;
    }
    
    public ROp(String tag, AExp e1, AExp e2) {
        type = tag;
        exp1 = null;
        exp2 = null;
        aexp1=e1;
    	aexp2=e2;
    }
    
    /**
	 *@Artsiom: Create the class out of an xml element
	 */
    public ROp(Element e){
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
        Element elm = new Element(type);
        if(exp1!=null) elm.addContent(exp1.toElement());
        if(aexp1!=null) elm.addContent(aexp1.toElement());
        if(exp2!=null) elm.addContent( exp2.toElement());
        if(aexp2!=null) elm.addContent(aexp2.toElement());
        return elm;
    }
}
