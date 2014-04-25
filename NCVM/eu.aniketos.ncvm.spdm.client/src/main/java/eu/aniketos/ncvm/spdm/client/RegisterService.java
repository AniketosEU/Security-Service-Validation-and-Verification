
package eu.aniketos.ncvm.spdm.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for registerService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="registerService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://api.ds.spdm.aniketos.eu}IWebService" minOccurs="0"/>
 *         &lt;element name="arg1" type="{http://data.aniketos.eu}ISecurityDescriptor" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registerService", propOrder = {
    "arg0",
    "arg1"
})
public class RegisterService {

    protected IWebService arg0;
    protected ISecurityDescriptor arg1;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link IWebService }
     *     
     */
    public IWebService getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link IWebService }
     *     
     */
    public void setArg0(IWebService value) {
        this.arg0 = value;
    }

    /**
     * Gets the value of the arg1 property.
     * 
     * @return
     *     possible object is
     *     {@link ISecurityDescriptor }
     *     
     */
    public ISecurityDescriptor getArg1() {
        return arg1;
    }

    /**
     * Sets the value of the arg1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link ISecurityDescriptor }
     *     
     */
    public void setArg1(ISecurityDescriptor value) {
        this.arg1 = value;
    }

}
