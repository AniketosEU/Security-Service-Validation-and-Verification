
package eu.aniketos.ncvm.spdm.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getProperties complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getProperties">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://api.ds.spdm.aniketos.eu}IWebService" minOccurs="0"/>
 *         &lt;element name="arg1" type="{http://data.aniketos.eu}SPState" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getProperties", propOrder = {
    "arg0",
    "arg1"
})
public class GetProperties {

    protected IWebService arg0;
    @XmlElementRef(name = "arg1", namespace = "http://api.ds.spdm.aniketos.eu/", type = JAXBElement.class)
    protected JAXBElement<SPState> arg1;

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
     *     {@link JAXBElement }{@code <}{@link SPState }{@code >}
     *     
     */
    public JAXBElement<SPState> getArg1() {
        return arg1;
    }

    /**
     * Sets the value of the arg1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SPState }{@code >}
     *     
     */
    public void setArg1(JAXBElement<SPState> value) {
        this.arg1 = value;
    }

}
