
package eu.aniketos.ncvm.pvm.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for verifyTechnicalTrustPropertiesResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="verifyTechnicalTrustPropertiesResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://propertyverification.verification.components.aniketos.eu}PropertyVerificationResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "verifyTechnicalTrustPropertiesResponse", propOrder = {
    "_return"
})
public class VerifyTechnicalTrustPropertiesResponse {

    @XmlElement(name = "return")
    protected PropertyVerificationResult _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link PropertyVerificationResult }
     *     
     */
    public PropertyVerificationResult getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertyVerificationResult }
     *     
     */
    public void setReturn(PropertyVerificationResult value) {
        this._return = value;
    }

}
