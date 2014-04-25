
package eu.aniketos.ncvm.marketplace.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getCompositionPlansResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getCompositionPlansResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://data.aniketos.eu}ArrayOfICompositionPlan"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCompositionPlansResponse", propOrder = {
    "_return"
})
public class GetCompositionPlansResponse {

    @XmlElement(name = "return", required = true, nillable = true)
    protected ArrayOfICompositionPlan _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfICompositionPlan }
     *     
     */
    public ArrayOfICompositionPlan getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfICompositionPlan }
     *     
     */
    public void setReturn(ArrayOfICompositionPlan value) {
        this._return = value;
    }

}
