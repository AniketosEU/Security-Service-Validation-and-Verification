
package eu.aniketos.ncvm.csvm.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CompositionSecurityValidationResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CompositionSecurityValidationResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="verificationExplaination" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="verificationResult" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompositionSecurityValidationResult", namespace = "http://compositionsecurityvalidation.verification.components.aniketos.eu", propOrder = {
    "verificationExplaination",
    "verificationResult"
})
public class CompositionSecurityValidationResult {

    @XmlElementRef(name = "verificationExplaination", namespace = "http://compositionsecurityvalidation.verification.components.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<String> verificationExplaination;
    @XmlElementRef(name = "verificationResult", namespace = "http://compositionsecurityvalidation.verification.components.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<Boolean> verificationResult;

    /**
     * Gets the value of the verificationExplaination property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getVerificationExplaination() {
        return verificationExplaination;
    }

    /**
     * Sets the value of the verificationExplaination property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setVerificationExplaination(JAXBElement<String> value) {
        this.verificationExplaination = value;
    }

    /**
     * Gets the value of the verificationResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public JAXBElement<Boolean> getVerificationResult() {
        return verificationResult;
    }

    /**
     * Sets the value of the verificationResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public void setVerificationResult(JAXBElement<Boolean> value) {
        this.verificationResult = value;
    }

}
