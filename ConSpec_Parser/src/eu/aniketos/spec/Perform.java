/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.aniketos.spec;

import java.util.Iterator;
import java.util.Vector;
import org.jdom.Element;

/**
 * Represent a perform tag, that contains the action to be executed when the method is activated
 * @author Administrator
 */
public class Perform extends Path {

	//the actions to be done
	public Vector<Reaction> reactions;
    //the command to be executed if no reactions are activated
	public Update elze;

	public Perform(){
		reactions = new Vector<Reaction>();
		elze = new Update();
	}
	
	/**
	 *Manual initialisation of Perform class
	 *react - vector of Reactions to be taken when the method id activated
	 *upd - actions to be taken, when no reaction is taken. null if no else statement exists.
	 */
	public Perform(Vector<Reaction> react, Update upd){
		reactions = react;
		elze=upd;
	}
	
	/**
	 *@Artsiom: Create the class out of an xml element
	 */
    public Perform(Element e) {
    	reactions = new Vector<Reaction>();
    	Iterator<Element> iter = e.getChildren().iterator();
    	for(int i = 0; iter.hasNext(); i++){
    		Element elm = iter.next();
    		if(elm.getName().equals(Tag.reaction_tag)){
    			reactions.add(new Reaction(elm));
    		} else if(elm.getName().equals(Tag.else_tag)){
    			//elze = new Update(elm);
    			elze = new Update((Element)elm.getChildren().iterator().next());
    			
    		}
    	}
        
        
    }
        
	/**
	 * trasfrom it to a DOM element
	 */
    public Element toElement() {
        Element ta_elm = new Element(Tag.perform_tag);
        Iterator<Reaction> iter = reactions.iterator();
        for(int i = 0; iter.hasNext(); i++){
        	ta_elm.addContent(iter.next().toElement());
        }
        if(elze!=null){
        	Element el_elm = new Element(Tag.else_tag);
        	el_elm.addContent(elze.toElement());
        	ta_elm.addContent(el_elm);
        }
        return ta_elm;
    }

}
