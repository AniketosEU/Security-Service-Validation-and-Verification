
package eu.aniketos.ncvm.marketplace.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceDescriptor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceDescriptor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="binding" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operations" type="{http://marketplace.aniketos.eu}ArrayOfServiceOperation" minOccurs="0"/>
 *         &lt;element name="providerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="testable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceDescriptor", namespace = "http://marketplace.aniketos.eu", propOrder = {
    "binding",
    "description",
    "id",
    "name",
    "operations",
    "providerName",
    "testable"
})
public class ServiceDescriptor {

    @XmlElementRef(name = "binding", namespace = "http://marketplace.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<String> binding;
    @XmlElementRef(name = "description", namespace = "http://marketplace.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<String> description;
    @XmlElementRef(name = "id", namespace = "http://marketplace.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<String> id;
    @XmlElementRef(name = "name", namespace = "http://marketplace.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<String> name;
    @XmlElementRef(name = "operations", namespace = "http://marketplace.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<ArrayOfServiceOperation> operations;
    @XmlElementRef(name = "providerName", namespace = "http://marketplace.aniketos.eu", type = JAXBElement.class)
    protected JAXBElement<String> providerName;
    protected Boolean testable;

    /**
     * Gets the value of the binding property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBinding() {
        return binding;
    }

    /**
     * Sets the value of the binding property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBinding(JAXBElement<String> value) {
        this.binding = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescription(JAXBElement<String> value) {
        this.description = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setId(JAXBElement<String> value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setName(JAXBElement<String> value) {
        this.name = value;
    }

    /**
     * Gets the value of the operations property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfServiceOperation }{@code >}
     *     
     */
    public JAXBElement<ArrayOfServiceOperation> getOperations() {
        return operations;
    }

    /**
     * Sets the value of the operations property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfServiceOperation }{@code >}
     *     
     */
    public void setOperations(JAXBElement<ArrayOfServiceOperation> value) {
        this.operations = value;
    }

    /**
     * Gets the value of the providerName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getProviderName() {
        return providerName;
    }

    /**
     * Sets the value of the providerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setProviderName(JAXBElement<String> value) {
        this.providerName = value;
    }

    /**
     * Gets the value of the testable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTestable() {
        return testable;
    }

    /**
     * Sets the value of the testable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTestable(Boolean value) {
        this.testable = value;
    }

}
