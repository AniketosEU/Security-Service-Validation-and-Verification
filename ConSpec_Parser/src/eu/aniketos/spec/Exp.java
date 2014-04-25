/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.aniketos.spec;

import java.io.Serializable;
import java.util.Iterator;

import org.jdom.Element;

/**
 * Represent a generic expression
 * an expression can be an integer expression (AExp), boolean (BExp) and String (SExp)
 * @author Administrator
 */
public abstract class Exp implements Serializable {

	public static final int EXP_INT = 0;
	public static final int EXP_BOOL = 1;
	public static final int EXP_STR = 2;
	public static final int EXP_REAL = 3;
	
	public int exp_type;
    public abstract Element toElement();

    /**
     * generate an expression from a DOM element
     * it investigates on element tags to see which type of expression it is
     * @param e: the element to be transformed in a java object
     * @return : the expression
     */
    public static Exp generateExp(Element e) {
    	int curr_type = EXP_BOOL;
    	Exp exp = null;
    	exp = generateBExp(e);
    	//if null, it's not a boolean exp
    	if(exp == null){
    		curr_type = EXP_INT;
    		exp = generateAExp(e);
        	//if null, it's not an integer exp
    		if(exp == null){
    			curr_type = EXP_STR;
    			exp = generateSExp(e);
    			//if null, it's not a string exp
    			if(exp == null){
        			curr_type = EXP_REAL;
        			exp = generateRExp(e);
    			}
    		}
    	}
    	exp.exp_type = curr_type;
    	return exp;
    }
    
	/**
	 *@Artsiom: Generate one of the integer expressions
	 */
    protected static AExp generateAExp(Element e) {
    	AExp ret = null;
    	if(e.getName().equals(Tag.iconst_tag)){
    		ret = new IConst(e);
    	}
    	if(e.getName().equals(Tag.i_identifier_tag)){
    		ret = new AIdentifier(e);
    	}
    	if(e.getName().equals(Tag.sum_tag) || e.getName().equals(Tag.dif_tag) || e.getName().equals(Tag.mul_tag) || e.getName().equals(Tag.mod_tag) ){
    		ret = new IOp(e);
    	}
    	if(e.getName().equals(Tag.invocation_tag)){
    		Iterator<Element> iter = e.getChildren().iterator();
            Identifier identifier = new Identifier(iter.next());
            if(identifier.ide_tag==Tag.i_identifier_tag)
            	ret = new AInvocation(e);
    	}
    	return ret;
    }
    
	/**
	 *@Artsiom: Generate one of the real expressions
	 */
    protected static RExp generateRExp(Element e) {
    	RExp ret = null;
    	if(e.getName().equals(Tag.rconst_tag)){
    		ret = new RConst(e);
    	}
    	if(e.getName().equals(Tag.r_identifier_tag)){
    		ret = new RIdentifier(e);
    	}
    	if(e.getName().equals(Tag.rsum_tag) || e.getName().equals(Tag.rdif_tag) || e.getName().equals(Tag.rmul_tag) || e.getName().equals(Tag.rmod_tag) || e.getName().equals(Tag.rdiv_tag) || e.getName().equals(Tag.round_tag) ){
    		ret = new ROp(e);
    	}
    	if(e.getName().equals(Tag.invocation_tag)){
    		Iterator<Element> iter = e.getChildren().iterator();
            Identifier identifier = new Identifier(iter.next());
            if(identifier.ide_tag==Tag.r_identifier_tag)
            	ret = new RInvocation(e);
    	}
    	return ret;
    }
    
    /**
	 *@Artsiom: Generate one of the boolean expressions
	 */
    protected static BExp generateBExp(Element e){

    	BExp ret = null;
    	if(e.getName().equals(Tag.bconst_tag)){
    		ret = new BConst(e);
    	}
    	if(e.getName().equals(Tag.and_tag) || e.getName().equals(Tag.not_tag) || e.getName().equals(Tag.or_tag)){
    		ret = new BOp(e);
    	}
    	if(e.getName().equals(Tag.b_identifier_tag)){
    		ret = new BIdentifier(e);
    	}
    	if(e.getName().equals(Tag.iequal_tag) || e.getName().equals(Tag.morequalthan_tag) || e.getName().equals(Tag.lessequalthan_tag)
    			|| e.getName().equals(Tag.lessthan_tag) || e.getName().equals(Tag.morethan_tag)|| e.getName().equals(Tag.notequal_tag)){
    		ret = new IGuard(e);
    	}
    	if(e.getName().equals(Tag.requal_tag) || e.getName().equals(Tag.rmorequalthan_tag) || e.getName().equals(Tag.rlessequalthan_tag)
    			|| e.getName().equals(Tag.rlessthan_tag) || e.getName().equals(Tag.rmorethan_tag)|| e.getName().equals(Tag.rnotequal_tag)){
    		ret = new RGuard(e);
    	}
    	
    	if(e.getName().equals(Tag.invocation_tag)){
    		Iterator<Element> iter = e.getChildren().iterator();
            Identifier identifier = new Identifier(iter.next());
            if(identifier.ide_tag==Tag.b_identifier_tag)
            	ret = new BInvocation(e);
    	}
    	if(e.getName().equals(Tag.bequal_tag)){
    		ret = new BGuard(e);
    	}
    	if(e.getName().equals(Tag.sequal_tag)){
    		ret = new SGuard(e);
    	}
    	return ret;
    }
    
    /**
	 *@Artsiom: Generate one of the string expressions
	 */
    protected static SExp generateSExp(Element e) {
    	SExp ret = null;
    	if(e.getName().equals(Tag.sconst_tag)){
    		ret = new SConst(e);
    	}
    	if(e.getName().equals(Tag.append_tag)){
    		ret = new Append(e);
    	}
    	if(e.getName().equals(Tag.s_identifier_tag)){
    		ret = new SIdentifier(e);
    	}
    	if(e.getName().equals(Tag.invocation_tag)){
    		Iterator<Element> iter = e.getChildren().iterator();
            Identifier identifier = new Identifier(iter.next());
            if(identifier.ide_tag==Tag.s_identifier_tag)
            	ret = new SInvocation(e);
    	}
    	return ret;
    }

    
    public boolean isVPA(){
    	return false;
    }
}
