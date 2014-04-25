
package eu.aniketos.ncvm.spdm.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ISecurityDescriptor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ISecurityDescriptor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="properties" type="{http://data.aniketos.eu}ArrayOfISecurityProperty" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ISecurityDescriptor", namespace = "http://data.aniketos.eu", propOrder = {
    "properties"
})
public class ISecurityDescriptor {

    @XmlElementRef(name = "properties", namespace = "http://data.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<ArrayOfISecurityProperty> properties;

    /**
     * Gets the value of the properties property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfISecurityProperty }{@code >}
     *     
     */
    public JAXBElement<ArrayOfISecurityProperty> getProperties() {
        return properties;
    }

    /**
     * Sets the value of the properties property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfISecurityProperty }{@code >}
     *     
     */
    public void setProperties(JAXBElement<ArrayOfISecurityProperty> value) {
        this.properties = value;
    }

}
