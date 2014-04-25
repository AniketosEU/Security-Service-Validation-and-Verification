
package eu.aniketos.ncvm.spdm.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ISecurityProperty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ISecurityProperty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="certificate" type="{http://cert.security.java}X509Certificate" minOccurs="0"/>
 *         &lt;element name="freshness" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="propertyID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="propertyValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="state" type="{http://data.aniketos.eu}SPState" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ISecurityProperty", namespace = "http://data.aniketos.eu", propOrder = {
    "certificate",
    "freshness",
    "propertyID",
    "propertyValue",
    "state"
})
public class ISecurityProperty {

    @XmlElementRef(name = "certificate", namespace = "http://data.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<X509Certificate> certificate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar freshness;
    @XmlElementRef(name = "propertyID", namespace = "http://data.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<String> propertyID;
    @XmlElementRef(name = "propertyValue", namespace = "http://data.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<String> propertyValue;
    @XmlElementRef(name = "state", namespace = "http://data.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<SPState> state;

    /**
     * Gets the value of the certificate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link X509Certificate }{@code >}
     *     
     */
    public JAXBElement<X509Certificate> getCertificate() {
        return certificate;
    }

    /**
     * Sets the value of the certificate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link X509Certificate }{@code >}
     *     
     */
    public void setCertificate(JAXBElement<X509Certificate> value) {
        this.certificate = value;
    }

    /**
     * Gets the value of the freshness property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFreshness() {
        return freshness;
    }

    /**
     * Sets the value of the freshness property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFreshness(XMLGregorianCalendar value) {
        this.freshness = value;
    }

    /**
     * Gets the value of the propertyID property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPropertyID() {
        return propertyID;
    }

    /**
     * Sets the value of the propertyID property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPropertyID(JAXBElement<String> value) {
        this.propertyID = value;
    }

    /**
     * Gets the value of the propertyValue property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPropertyValue() {
        return propertyValue;
    }

    /**
     * Sets the value of the propertyValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPropertyValue(JAXBElement<String> value) {
        this.propertyValue = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SPState }{@code >}
     *     
     */
    public JAXBElement<SPState> getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SPState }{@code >}
     *     
     */
    public void setState(JAXBElement<SPState> value) {
        this.state = value;
    }

}
