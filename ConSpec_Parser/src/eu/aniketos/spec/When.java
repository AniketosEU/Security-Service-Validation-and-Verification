package eu.aniketos.spec;

import java.util.Vector;

import org.jdom.Element;

/**
 * Represents a When tag of the conspec policy
 * @author Luca
 *
 */
public abstract class When {
	
	public Identifier identifier;
	public Vector<Parameter> parameters;
	
	public abstract Element toElement();
	
	/**
	 *@Artsiom: Generate the (CHILD) class out of an xml element
	 */
	public static When generateWhen(Element e){
		if(e.getName().equals(Tag.before_tag)){
			return new Before(e);
		} else if(e.getName().equals(Tag.after_tag)){
			return new After(e);
		} else if(e.getName().equals(Tag.exceptional_tag)){
			return new Exceptional(e);
		}
		return null;
	}

}
