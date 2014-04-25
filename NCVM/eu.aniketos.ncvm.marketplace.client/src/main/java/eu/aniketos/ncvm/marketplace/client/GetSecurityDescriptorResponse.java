
package eu.aniketos.ncvm.marketplace.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getSecurityDescriptorResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getSecurityDescriptorResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://marketplace.aniketos.eu}MarketplaceSecurityDescriptor" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSecurityDescriptorResponse", propOrder = {
    "_return"
})
public class GetSecurityDescriptorResponse {

    @XmlElement(name = "return")
    protected MarketplaceSecurityDescriptor _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link MarketplaceSecurityDescriptor }
     *     
     */
    public MarketplaceSecurityDescriptor getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link MarketplaceSecurityDescriptor }
     *     
     */
    public void setReturn(MarketplaceSecurityDescriptor value) {
        this._return = value;
    }

}
