
package eu.aniketos.ncvm.marketplace.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfISecurityProperty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfISecurityProperty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ISecurityProperty" type="{http://data.aniketos.eu}ISecurityProperty" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfISecurityProperty", namespace = "http://data.aniketos.eu", propOrder = {
    "iSecurityProperty"
})
public class ArrayOfISecurityProperty {

    @XmlElement(name = "ISecurityProperty", nillable = true)
    protected List<ISecurityProperty> iSecurityProperty;

    /**
     * Gets the value of the iSecurityProperty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the iSecurityProperty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getISecurityProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ISecurityProperty }
     * 
     * 
     */
    public List<ISecurityProperty> getISecurityProperty() {
        if (iSecurityProperty == null) {
            iSecurityProperty = new ArrayList<ISecurityProperty>();
        }
        return this.iSecurityProperty;
    }

}
