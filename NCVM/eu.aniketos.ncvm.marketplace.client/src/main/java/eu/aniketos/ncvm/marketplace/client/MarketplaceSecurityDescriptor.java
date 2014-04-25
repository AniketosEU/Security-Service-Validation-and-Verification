
package eu.aniketos.ncvm.marketplace.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MarketplaceSecurityDescriptor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MarketplaceSecurityDescriptor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="securityProperties" type="{http://marketplace.aniketos.eu}ArrayOfMarketplaceSecurityProperty" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MarketplaceSecurityDescriptor", namespace = "http://marketplace.aniketos.eu", propOrder = {
    "securityProperties"
})
public class MarketplaceSecurityDescriptor {

    @XmlElementRef(name = "securityProperties", namespace = "http://marketplace.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<ArrayOfMarketplaceSecurityProperty> securityProperties;

    /**
     * Gets the value of the securityProperties property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfMarketplaceSecurityProperty }{@code >}
     *     
     */
    public JAXBElement<ArrayOfMarketplaceSecurityProperty> getSecurityProperties() {
        return securityProperties;
    }

    /**
     * Sets the value of the securityProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfMarketplaceSecurityProperty }{@code >}
     *     
     */
    public void setSecurityProperties(JAXBElement<ArrayOfMarketplaceSecurityProperty> value) {
        this.securityProperties = value;
    }

}
