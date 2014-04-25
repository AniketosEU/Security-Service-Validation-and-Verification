/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.aniketos.spec;

import java.util.Iterator;

import org.jdom.Element;

/**
 *
 * @author Administrator
 */
public abstract class Path {

	public final static String sequence_tag = "sequence";
    public final static String choice_tag = "choice";
    public final static String parallel_tag = "parallel";
    public final static String tryaction_tag = "tryaction";

    public Path() {}

    public abstract Element toElement();

    public static Path generatePath(Element e) throws PolicyFormatException {
    	Iterator<Element> iter = e.getContent().iterator();
        String name = e.getName();
        if(name.equals(Path.sequence_tag)) {
            return new Sequence(generatePath(iter.next()), generatePath(iter.next()));
        }
        else if(name.equals(Path.choice_tag)) {
            return new Choice(generatePath(iter.next()), generatePath(iter.next()));
        }
        else if(name.equals(Path.parallel_tag)) {
            return new Parallel(generatePath(iter.next()), generatePath(iter.next()));
        }
        else if(name.equals(Path.tryaction_tag)) {
            return new Perform(iter.next());
        }
        else {
            throw new PolicyFormatException("Wrong Path Name: "+name);
        }
    }
}
