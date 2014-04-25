package eu.aniketos.spec;

import org.jdom.Element;

public class Scope{
	public static final String SESSION = "session", MULTISESSION = "multisession", GLOBAL="global";
	
	public String t;
	
	public Scope(){
		t="";
	}
	
	/*
	 * Manual initialisation of class Scope
	 * s - type of the scope: SESSION = "session", MULTISESSION = "multisession", GLOBAL="global"
	 */
	public Scope(String session2) {
		t = session2;
	}

	
	/**
	 *@Artsiom: Create the class out of an xml element
	 */
	public Scope(Element e){
		t = e.getTextTrim();
	}
	
	/**
	 * trasfrom it to a DOM element
	 */
	public Element toElement(){
		Element e = new Element(Tag.scope_tag);
		e.setText(t);
		return e;
	}
}
