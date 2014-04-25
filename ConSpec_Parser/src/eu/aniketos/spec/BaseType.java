package eu.aniketos.spec;

import org.jdom.Element;

public class BaseType {
	public static final String INT = "int", BOOL = "bool", STRING="string", REAL="real";
	
	public String t;
	
	public BaseType(){
		t="";
	}
	
	/**
	 * type int, bool or string 
	 */
	public BaseType(String type){
		t=type;
	}
	
	/**
	 *@Artsiom: Create the class out of an xml element
	 */
	public BaseType(Element e){
		t = e.getTextTrim();
	}
	
	/**
	 * trasfrom it to a DOM element
	 */
	public Element toElement(){
		Element e = new Element(Tag.type_tag);
		e.setText(t);
		return e;
	}
}


