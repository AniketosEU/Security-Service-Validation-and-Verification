package eu.aniketos.spec;

import java.util.Iterator;

import org.jdom.Element;

/**
 * Represents a reaction, composed by a guard and a command to perform in case the guard is true
 * @author Luca
 *
 */
public class Reaction {

	public BExp guard; //the guard
	public Update update; //command to perform in case the guard is true
	
	public Reaction(){
		// the guard BExp does not exist as it is, but only as one of its children
		guard = null;
		update = new Update();
	}
	
	/*
	 * Manual initialisation of Reaction class
	 * bexp - the guard of reaction
	 * upd - the action to be taken 
	 */
	public Reaction(BExp bexp, Update upd){
		guard = bexp;
		update = upd;
	}
	
	/**
	 *@Artsiom: Create the class out of an xml element
	 */
	public Reaction(Element e){
		Iterator<Element> iter = e.getChildren().iterator();
		Element elm = (Element)iter.next().getChildren().iterator().next();
		guard = BExp.generateBExp(elm);
		update = new Update((Element)iter.next());
	}
	
	/**
	 * trasfrom it to a DOM element
	 */
	public Element toElement(){
        Element elm = new Element(Tag.reaction_tag);
        Element guard_elm = new Element(Tag.guard_tag);
        guard_elm.addContent(guard.toElement());
        elm.addContent(guard_elm);
        elm.addContent(update.toElement());
        return elm;
	}
	
}
