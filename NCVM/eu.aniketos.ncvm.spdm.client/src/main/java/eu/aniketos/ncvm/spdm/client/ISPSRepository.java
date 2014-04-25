
package eu.aniketos.ncvm.spdm.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ISPSRepository complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ISPSRepository">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entriest" type="{http://util.java}ArrayOfEntry" minOccurs="0"/>
 *         &lt;element name="propertyEntries" type="{http://util.java}ArrayOfEntry" minOccurs="0"/>
 *         &lt;element name="serviceEntries" type="{http://util.java}ArrayOfEntry" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ISPSRepository", namespace = "http://api.ds.spdm.aniketos.eu", propOrder = {
    "entriest",
    "propertyEntries",
    "serviceEntries"
})
public class ISPSRepository {

    @XmlElementRef(name = "entriest", namespace = "http://api.ds.spdm.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<ArrayOfEntry> entriest;
    @XmlElementRef(name = "propertyEntries", namespace = "http://api.ds.spdm.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<ArrayOfEntry> propertyEntries;
    @XmlElementRef(name = "serviceEntries", namespace = "http://api.ds.spdm.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<ArrayOfEntry> serviceEntries;

    /**
     * Gets the value of the entriest property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfEntry }{@code >}
     *     
     */
    public JAXBElement<ArrayOfEntry> getEntriest() {
        return entriest;
    }

    /**
     * Sets the value of the entriest property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfEntry }{@code >}
     *     
     */
    public void setEntriest(JAXBElement<ArrayOfEntry> value) {
        this.entriest = value;
    }

    /**
     * Gets the value of the propertyEntries property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfEntry }{@code >}
     *     
     */
    public JAXBElement<ArrayOfEntry> getPropertyEntries() {
        return propertyEntries;
    }

    /**
     * Sets the value of the propertyEntries property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfEntry }{@code >}
     *     
     */
    public void setPropertyEntries(JAXBElement<ArrayOfEntry> value) {
        this.propertyEntries = value;
    }

    /**
     * Gets the value of the serviceEntries property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfEntry }{@code >}
     *     
     */
    public JAXBElement<ArrayOfEntry> getServiceEntries() {
        return serviceEntries;
    }

    /**
     * Sets the value of the serviceEntries property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfEntry }{@code >}
     *     
     */
    public void setServiceEntries(JAXBElement<ArrayOfEntry> value) {
        this.serviceEntries = value;
    }

}
