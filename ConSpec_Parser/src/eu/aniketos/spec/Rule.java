/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.aniketos.spec;

import java.util.Iterator;

import org.jdom.Element;

/**
 * represent a conspec rule, made by a when tag an some reactions (perform tag).
 * the when tag is the trigger of the rule, we check if the request to the PDP matches with this rule by checking the when tag.
 * If the request matches, the reactions are activated.
 * @author Administrator
 */
public class Rule {


    public When when;
    public Perform perform;

    public Rule(){
    	when = null;
    	perform = new Perform();
    }
    
	/**
	 * Manual initialisation of Rule class
	 * when - determines WHEN the rule should be fired {Before, After or when execution is Exceptional}.
	 * perform - determines WHAT the rule should do when fired. 
	 */
    public Rule(When when, Perform perform) {
    	this.when = when;
    	this.perform = perform;
    }

    
    /**
	 *@Artsiom: Create the class out of an xml element
	 */
    public Rule(Element e) {
    	Iterator<Element> iter = e.getChildren().iterator();
    	when = When.generateWhen(iter.next());
    	perform = new Perform(iter.next());
    }

	/**
	 * trasfrom it to a DOM element
	 */
    public Element toElement() {
        Element rule_elm = new Element(Tag.rule_tag);
        rule_elm.addContent(when.toElement());
        rule_elm.addContent(perform.toElement());
        return rule_elm;
    }
}
