package eu.aniketos.spec;

import org.jdom.Element;
import org.jdom.Text;

/**
 * Represents a generic identifier
 * @author Luca
 *
 */
public class Identifier {

	public String identifier;
	public String ide_tag;
	
	public Identifier(){
		identifier="";
		ide_tag="";
	}
	
	/**
	 * Manual initialisation of identifier
	 * id - the identifier name
	 * tagForIdentifier - tag for xml {identifier, bidentifer, sidentifer, aidentifer} - if I am not mistaken 
	 */
	public Identifier(String id, String tagForIdentifier){
		identifier = id;
		ide_tag=tagForIdentifier;
	}
	
	/**
	 *@Artsiom: Create the class out of an xml element
	 */
	public Identifier(Element e){
		identifier = ((Text)e.getContent().iterator().next()).getTextTrim();
		ide_tag=e.getName();
	}
	
	/**
	 * trasfrom it to a DOM element
	 */
	public Element toElement() {
		Element vpa_elm = new Element(ide_tag);
//		Element vpa_elm = new Element(Tag.identifier_tag);
        vpa_elm.addContent(identifier);
        return vpa_elm;
	}

}
