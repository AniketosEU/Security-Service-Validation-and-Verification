
package eu.aniketos.ncvm.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ICompositionPlan complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ICompositionPlan">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BPMNXML" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="activitiFile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="compositionPlanID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ICompositionPlan", namespace = "http://data.aniketos.eu", propOrder = {
    "bpmnxml",
    "activitiFile",
    "compositionPlanID"
})
public class ICompositionPlan {

    @XmlElementRef(name = "BPMNXML", namespace = "http://data.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<String> bpmnxml;
    @XmlElementRef(name = "activitiFile", namespace = "http://data.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<String> activitiFile;
    @XmlElementRef(name = "compositionPlanID", namespace = "http://data.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<String> compositionPlanID;

    /**
     * Gets the value of the bpmnxml property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBPMNXML() {
        return bpmnxml;
    }

    /**
     * Sets the value of the bpmnxml property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBPMNXML(JAXBElement<String> value) {
        this.bpmnxml = value;
    }

    /**
     * Gets the value of the activitiFile property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getActivitiFile() {
        return activitiFile;
    }

    /**
     * Sets the value of the activitiFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setActivitiFile(JAXBElement<String> value) {
        this.activitiFile = value;
    }

    /**
     * Gets the value of the compositionPlanID property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCompositionPlanID() {
        return compositionPlanID;
    }

    /**
     * Sets the value of the compositionPlanID property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCompositionPlanID(JAXBElement<String> value) {
        this.compositionPlanID = value;
    }

}
