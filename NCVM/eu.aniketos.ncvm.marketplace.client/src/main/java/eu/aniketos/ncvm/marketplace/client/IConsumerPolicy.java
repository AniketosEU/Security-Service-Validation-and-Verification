
package eu.aniketos.ncvm.marketplace.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IConsumerPolicy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IConsumerPolicy">
 *   &lt;complexContent>
 *     &lt;extension base="{http://data.aniketos.eu}ISecurityDescriptor">
 *       &lt;sequence>
 *         &lt;element name="XML" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="xmlContents" type="{http://marketplace.aniketos.eu/}ArrayOfString" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IConsumerPolicy", namespace = "http://data.aniketos.eu", propOrder = {
    "xml",
    "xmlContents"
})
public class IConsumerPolicy
    extends ISecurityDescriptor
{

    @XmlElementRef(name = "XML", namespace = "http://data.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<String> xml;
    @XmlElementRef(name = "xmlContents", namespace = "http://data.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<ArrayOfString> xmlContents;

    /**
     * Gets the value of the xml property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getXML() {
        return xml;
    }

    /**
     * Sets the value of the xml property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setXML(JAXBElement<String> value) {
        this.xml = value;
    }

    /**
     * Gets the value of the xmlContents property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}
     *     
     */
    public JAXBElement<ArrayOfString> getXmlContents() {
        return xmlContents;
    }

    /**
     * Sets the value of the xmlContents property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}
     *     
     */
    public void setXmlContents(JAXBElement<ArrayOfString> value) {
        this.xmlContents = value;
    }

}
