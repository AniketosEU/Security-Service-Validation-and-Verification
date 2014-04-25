
package eu.aniketos.ncvm.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for verifyProperty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="verifyProperty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://data.aniketos.eu}ICompositionPlan" minOccurs="0"/>
 *         &lt;element name="arg1" type="{http://data.aniketos.eu}IConsumerPolicy" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "verifyProperty", propOrder = {
    "arg0",
    "arg1"
})
public class VerifyProperty {

    protected ICompositionPlan arg0;
    protected IConsumerPolicy arg1;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link ICompositionPlan }
     *     
     */
    public ICompositionPlan getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link ICompositionPlan }
     *     
     */
    public void setArg0(ICompositionPlan value) {
        this.arg0 = value;
    }

    /**
     * Gets the value of the arg1 property.
     * 
     * @return
     *     possible object is
     *     {@link IConsumerPolicy }
     *     
     */
    public IConsumerPolicy getArg1() {
        return arg1;
    }

    /**
     * Sets the value of the arg1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link IConsumerPolicy }
     *     
     */
    public void setArg1(IConsumerPolicy value) {
        this.arg1 = value;
    }

}
