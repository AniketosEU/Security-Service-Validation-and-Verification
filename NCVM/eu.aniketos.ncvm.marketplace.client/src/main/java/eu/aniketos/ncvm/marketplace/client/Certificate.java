
package eu.aniketos.ncvm.marketplace.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Certificate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Certificate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="encoded" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="publicKey" type="{http://security.java}PublicKey" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Certificate", namespace = "http://cert.security.java", propOrder = {
    "encoded",
    "publicKey",
    "type"
})
@XmlSeeAlso({
    X509Certificate.class
})
public abstract class Certificate {

    @XmlElementRef(name = "encoded", namespace = "http://cert.security.java", type = JAXBElement.class)
    protected JAXBElement<byte[]> encoded;
    @XmlElementRef(name = "publicKey", namespace = "http://cert.security.java", type = JAXBElement.class)
    protected JAXBElement<PublicKey> publicKey;
    @XmlElementRef(name = "type", namespace = "http://cert.security.java", type = JAXBElement.class)
    protected JAXBElement<String> type;

    /**
     * Gets the value of the encoded property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getEncoded() {
        return encoded;
    }

    /**
     * Sets the value of the encoded property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setEncoded(JAXBElement<byte[]> value) {
        this.encoded = value;
    }

    /**
     * Gets the value of the publicKey property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PublicKey }{@code >}
     *     
     */
    public JAXBElement<PublicKey> getPublicKey() {
        return publicKey;
    }

    /**
     * Sets the value of the publicKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PublicKey }{@code >}
     *     
     */
    public void setPublicKey(JAXBElement<PublicKey> value) {
        this.publicKey = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setType(JAXBElement<String> value) {
        this.type = value;
    }

}
