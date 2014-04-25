/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.aniketos.spec;

import java.io.Serializable;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;

import org.jdom.Element;

/**
 * Represents a command to be executed inside the environment
 * a command is one or more assigns
 * @author Administrator
 */
public class Update {

    
    public Vector<Assign> com;

    public Update() {
        com = new Vector<Assign>();
    }
    
    /*
     * Manual initialisation of Update class
     * command - command to be performed
     */
    public Update(Vector<Assign> command) {
        com = command;
    }

    /**
	 *@Artsiom: Create the class out of an xml element
	 */
    public Update(Element e) {
        com = new Vector<Assign>();
		Iterator<Element> iter = e.getChildren().iterator();
        while(iter.hasNext()) {
            com.add(new Assign(iter.next()));
        }
    }

	/**
	 * trasfrom it to a DOM element
	 */
    public Element toElement() {
    	Element e = new Element(Tag.update_tag);
        for(int i = 0; i<com.size(); i++) {
            Assign a = (Assign)com.elementAt(i);
            e.addContent(a.toElement());
        }
        return e;
    }

    public void addElement(Element e) {
        com.addElement(new Assign(e));
    }

    public void addAssign(String n, Exp v) {
    	
    	// ART: NOTE, that currently the type of the identifier is simple "identifier". 
    	// ART: This should be changed later
        com.addElement(new Assign(new Identifier(n, Tag.identifier_tag), v));
    }

    public static boolean isUpdate(Element e) {
    	return (e.getName().equals(Tag.assign_tag));
    }

    public class Assign{

        public Identifier identifier;
        public Exp value;

        public Assign(Identifier n, Exp e){
        	identifier = n;
        	value = e;
        }
        
        public Assign(Element e){
        	Iterator<Element> iter = e.getChildren().iterator();
            identifier = new Identifier(iter.next());
            Element child = iter.next();
            value = Exp.generateExp((Element)child.getChildren().iterator().next());
        }
        
        public Element toElement(){
            Element ch = new Element(Tag.assign_tag);
            ch.addContent(identifier.toElement());
            Element val_elm = new Element(Tag.value_tag);
            val_elm.addContent(value.toElement());
            ch.addContent(val_elm);
            return ch;
        }

    }
}
