
package eu.aniketos.ncvm.marketplace.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MarketplaceAnnouncement complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MarketplaceAnnouncement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="compositionPlans" type="{http://data.aniketos.eu}ArrayOfICompositionPlan" minOccurs="0"/>
 *         &lt;element name="registry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rules" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="securityDescriptor" type="{http://marketplace.aniketos.eu}MarketplaceSecurityDescriptor" minOccurs="0"/>
 *         &lt;element name="sender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceDescriptor" type="{http://marketplace.aniketos.eu}ServiceDescriptor" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MarketplaceAnnouncement", namespace = "http://marketplace.aniketos.eu", propOrder = {
    "compositionPlans",
    "registry",
    "rules",
    "securityDescriptor",
    "sender",
    "serviceDescriptor"
})
public class MarketplaceAnnouncement {

    @XmlElementRef(name = "compositionPlans", namespace = "http://marketplace.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<ArrayOfICompositionPlan> compositionPlans;
    @XmlElementRef(name = "registry", namespace = "http://marketplace.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<String> registry;
    @XmlElementRef(name = "rules", namespace = "http://marketplace.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<String> rules;
    @XmlElementRef(name = "securityDescriptor", namespace = "http://marketplace.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<MarketplaceSecurityDescriptor> securityDescriptor;
    @XmlElementRef(name = "sender", namespace = "http://marketplace.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<String> sender;
    @XmlElementRef(name = "serviceDescriptor", namespace = "http://marketplace.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<ServiceDescriptor> serviceDescriptor;

    /**
     * Gets the value of the compositionPlans property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfICompositionPlan }{@code >}
     *     
     */
    public JAXBElement<ArrayOfICompositionPlan> getCompositionPlans() {
        return compositionPlans;
    }

    /**
     * Sets the value of the compositionPlans property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfICompositionPlan }{@code >}
     *     
     */
    public void setCompositionPlans(JAXBElement<ArrayOfICompositionPlan> value) {
        this.compositionPlans = value;
    }

    /**
     * Gets the value of the registry property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRegistry() {
        return registry;
    }

    /**
     * Sets the value of the registry property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRegistry(JAXBElement<String> value) {
        this.registry = value;
    }

    /**
     * Gets the value of the rules property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRules() {
        return rules;
    }

    /**
     * Sets the value of the rules property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRules(JAXBElement<String> value) {
        this.rules = value;
    }

    /**
     * Gets the value of the securityDescriptor property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link MarketplaceSecurityDescriptor }{@code >}
     *     
     */
    public JAXBElement<MarketplaceSecurityDescriptor> getSecurityDescriptor() {
        return securityDescriptor;
    }

    /**
     * Sets the value of the securityDescriptor property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link MarketplaceSecurityDescriptor }{@code >}
     *     
     */
    public void setSecurityDescriptor(JAXBElement<MarketplaceSecurityDescriptor> value) {
        this.securityDescriptor = value;
    }

    /**
     * Gets the value of the sender property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSender() {
        return sender;
    }

    /**
     * Sets the value of the sender property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSender(JAXBElement<String> value) {
        this.sender = value;
    }

    /**
     * Gets the value of the serviceDescriptor property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ServiceDescriptor }{@code >}
     *     
     */
    public JAXBElement<ServiceDescriptor> getServiceDescriptor() {
        return serviceDescriptor;
    }

    /**
     * Sets the value of the serviceDescriptor property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ServiceDescriptor }{@code >}
     *     
     */
    public void setServiceDescriptor(JAXBElement<ServiceDescriptor> value) {
        this.serviceDescriptor = value;
    }

}
