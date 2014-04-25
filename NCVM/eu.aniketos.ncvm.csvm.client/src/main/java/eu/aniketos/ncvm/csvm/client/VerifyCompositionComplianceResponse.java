
package eu.aniketos.ncvm.csvm.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VerifyCompositionComplianceResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VerifyCompositionComplianceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://compositionsecurityvalidation.verification.components.aniketos.eu}CompositionSecurityValidationResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VerifyCompositionComplianceResponse", propOrder = {
    "_return"
})
public class VerifyCompositionComplianceResponse {

    @XmlElement(name = "return")
    protected CompositionSecurityValidationResult _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link CompositionSecurityValidationResult }
     *     
     */
    public CompositionSecurityValidationResult getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link CompositionSecurityValidationResult }
     *     
     */
    public void setReturn(CompositionSecurityValidationResult value) {
        this._return = value;
    }

}
