
package eu.aniketos.ncvm.spdm.client;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for X509Certificate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="X509Certificate">
 *   &lt;complexContent>
 *     &lt;extension base="{http://cert.security.java}Certificate">
 *       &lt;sequence>
 *         &lt;element name="TBSCertificate" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="basicConstraints" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="extendedKeyUsage" type="{http://api.ds.spdm.aniketos.eu/}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="issuerAlternativeNames" type="{http://cxf.apache.org/arrays}ArrayOfArrayOfAnyType" minOccurs="0"/>
 *         &lt;element name="issuerDN" type="{http://security.java}Principal" minOccurs="0"/>
 *         &lt;element name="issuerUniqueID" type="{http://api.ds.spdm.aniketos.eu/}ArrayOfBoolean" minOccurs="0"/>
 *         &lt;element name="issuerX500Principal" type="{http://x500.auth.security.javax}X500Principal" minOccurs="0"/>
 *         &lt;element name="keyUsage" type="{http://api.ds.spdm.aniketos.eu/}ArrayOfBoolean" minOccurs="0"/>
 *         &lt;element name="notAfter" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="notBefore" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="serialNumber" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="sigAlgName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sigAlgOID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sigAlgParams" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="signature" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="subjectAlternativeNames" type="{http://cxf.apache.org/arrays}ArrayOfArrayOfAnyType" minOccurs="0"/>
 *         &lt;element name="subjectDN" type="{http://security.java}Principal" minOccurs="0"/>
 *         &lt;element name="subjectUniqueID" type="{http://api.ds.spdm.aniketos.eu/}ArrayOfBoolean" minOccurs="0"/>
 *         &lt;element name="subjectX500Principal" type="{http://x500.auth.security.javax}X500Principal" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "X509Certificate", namespace = "http://cert.security.java", propOrder = {
    "tbsCertificate",
    "basicConstraints",
    "extendedKeyUsage",
    "issuerAlternativeNames",
    "issuerDN",
    "issuerUniqueID",
    "issuerX500Principal",
    "keyUsage",
    "notAfter",
    "notBefore",
    "serialNumber",
    "sigAlgName",
    "sigAlgOID",
    "sigAlgParams",
    "signature",
    "subjectAlternativeNames",
    "subjectDN",
    "subjectUniqueID",
    "subjectX500Principal",
    "version"
})
public abstract class X509Certificate
    extends Certificate
{

    @XmlElementRef(name = "TBSCertificate", namespace = "http://cert.security.java", type = JAXBElement.class)
    protected JAXBElement<byte[]> tbsCertificate;
    protected Integer basicConstraints;
    @XmlElementRef(name = "extendedKeyUsage", namespace = "http://cert.security.java", type = JAXBElement.class)
    protected JAXBElement<ArrayOfString> extendedKeyUsage;
    @XmlElementRef(name = "issuerAlternativeNames", namespace = "http://cert.security.java", type = JAXBElement.class)
    protected JAXBElement<ArrayOfArrayOfAnyType> issuerAlternativeNames;
    @XmlElementRef(name = "issuerDN", namespace = "http://cert.security.java", type = JAXBElement.class)
    protected JAXBElement<Principal> issuerDN;
    @XmlElementRef(name = "issuerUniqueID", namespace = "http://cert.security.java", type = JAXBElement.class)
    protected JAXBElement<ArrayOfBoolean> issuerUniqueID;
    @XmlElementRef(name = "issuerX500Principal", namespace = "http://cert.security.java", type = JAXBElement.class)
    protected JAXBElement<X500Principal> issuerX500Principal;
    @XmlElementRef(name = "keyUsage", namespace = "http://cert.security.java", type = JAXBElement.class)
    protected JAXBElement<ArrayOfBoolean> keyUsage;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar notAfter;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar notBefore;
    @XmlElementRef(name = "serialNumber", namespace = "http://cert.security.java", type = JAXBElement.class)
    protected JAXBElement<BigInteger> serialNumber;
    @XmlElementRef(name = "sigAlgName", namespace = "http://cert.security.java", type = JAXBElement.class)
    protected JAXBElement<String> sigAlgName;
    @XmlElementRef(name = "sigAlgOID", namespace = "http://cert.security.java", type = JAXBElement.class)
    protected JAXBElement<String> sigAlgOID;
    @XmlElementRef(name = "sigAlgParams", namespace = "http://cert.security.java", type = JAXBElement.class)
    protected JAXBElement<byte[]> sigAlgParams;
    @XmlElementRef(name = "signature", namespace = "http://cert.security.java", type = JAXBElement.class)
    protected JAXBElement<byte[]> signature;
    @XmlElementRef(name = "subjectAlternativeNames", namespace = "http://cert.security.java", type = JAXBElement.class)
    protected JAXBElement<ArrayOfArrayOfAnyType> subjectAlternativeNames;
    @XmlElementRef(name = "subjectDN", namespace = "http://cert.security.java", type = JAXBElement.class)
    protected JAXBElement<Principal> subjectDN;
    @XmlElementRef(name = "subjectUniqueID", namespace = "http://cert.security.java", type = JAXBElement.class)
    protected JAXBElement<ArrayOfBoolean> subjectUniqueID;
    @XmlElementRef(name = "subjectX500Principal", namespace = "http://cert.security.java", type = JAXBElement.class)
    protected JAXBElement<X500Principal> subjectX500Principal;
    protected Integer version;

    /**
     * Gets the value of the tbsCertificate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getTBSCertificate() {
        return tbsCertificate;
    }

    /**
     * Sets the value of the tbsCertificate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setTBSCertificate(JAXBElement<byte[]> value) {
        this.tbsCertificate = value;
    }

    /**
     * Gets the value of the basicConstraints property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBasicConstraints() {
        return basicConstraints;
    }

    /**
     * Sets the value of the basicConstraints property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBasicConstraints(Integer value) {
        this.basicConstraints = value;
    }

    /**
     * Gets the value of the extendedKeyUsage property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}
     *     
     */
    public JAXBElement<ArrayOfString> getExtendedKeyUsage() {
        return extendedKeyUsage;
    }

    /**
     * Sets the value of the extendedKeyUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}
     *     
     */
    public void setExtendedKeyUsage(JAXBElement<ArrayOfString> value) {
        this.extendedKeyUsage = value;
    }

    /**
     * Gets the value of the issuerAlternativeNames property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfArrayOfAnyType }{@code >}
     *     
     */
    public JAXBElement<ArrayOfArrayOfAnyType> getIssuerAlternativeNames() {
        return issuerAlternativeNames;
    }

    /**
     * Sets the value of the issuerAlternativeNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfArrayOfAnyType }{@code >}
     *     
     */
    public void setIssuerAlternativeNames(JAXBElement<ArrayOfArrayOfAnyType> value) {
        this.issuerAlternativeNames = value;
    }

    /**
     * Gets the value of the issuerDN property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Principal }{@code >}
     *     
     */
    public JAXBElement<Principal> getIssuerDN() {
        return issuerDN;
    }

    /**
     * Sets the value of the issuerDN property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Principal }{@code >}
     *     
     */
    public void setIssuerDN(JAXBElement<Principal> value) {
        this.issuerDN = value;
    }

    /**
     * Gets the value of the issuerUniqueID property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfBoolean }{@code >}
     *     
     */
    public JAXBElement<ArrayOfBoolean> getIssuerUniqueID() {
        return issuerUniqueID;
    }

    /**
     * Sets the value of the issuerUniqueID property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfBoolean }{@code >}
     *     
     */
    public void setIssuerUniqueID(JAXBElement<ArrayOfBoolean> value) {
        this.issuerUniqueID = value;
    }

    /**
     * Gets the value of the issuerX500Principal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link X500Principal }{@code >}
     *     
     */
    public JAXBElement<X500Principal> getIssuerX500Principal() {
        return issuerX500Principal;
    }

    /**
     * Sets the value of the issuerX500Principal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link X500Principal }{@code >}
     *     
     */
    public void setIssuerX500Principal(JAXBElement<X500Principal> value) {
        this.issuerX500Principal = value;
    }

    /**
     * Gets the value of the keyUsage property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfBoolean }{@code >}
     *     
     */
    public JAXBElement<ArrayOfBoolean> getKeyUsage() {
        return keyUsage;
    }

    /**
     * Sets the value of the keyUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfBoolean }{@code >}
     *     
     */
    public void setKeyUsage(JAXBElement<ArrayOfBoolean> value) {
        this.keyUsage = value;
    }

    /**
     * Gets the value of the notAfter property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNotAfter() {
        return notAfter;
    }

    /**
     * Sets the value of the notAfter property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNotAfter(XMLGregorianCalendar value) {
        this.notAfter = value;
    }

    /**
     * Gets the value of the notBefore property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNotBefore() {
        return notBefore;
    }

    /**
     * Sets the value of the notBefore property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNotBefore(XMLGregorianCalendar value) {
        this.notBefore = value;
    }

    /**
     * Gets the value of the serialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     *     
     */
    public JAXBElement<BigInteger> getSerialNumber() {
        return serialNumber;
    }

    /**
     * Sets the value of the serialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     *     
     */
    public void setSerialNumber(JAXBElement<BigInteger> value) {
        this.serialNumber = value;
    }

    /**
     * Gets the value of the sigAlgName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSigAlgName() {
        return sigAlgName;
    }

    /**
     * Sets the value of the sigAlgName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSigAlgName(JAXBElement<String> value) {
        this.sigAlgName = value;
    }

    /**
     * Gets the value of the sigAlgOID property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSigAlgOID() {
        return sigAlgOID;
    }

    /**
     * Sets the value of the sigAlgOID property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSigAlgOID(JAXBElement<String> value) {
        this.sigAlgOID = value;
    }

    /**
     * Gets the value of the sigAlgParams property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getSigAlgParams() {
        return sigAlgParams;
    }

    /**
     * Sets the value of the sigAlgParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setSigAlgParams(JAXBElement<byte[]> value) {
        this.sigAlgParams = value;
    }

    /**
     * Gets the value of the signature property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getSignature() {
        return signature;
    }

    /**
     * Sets the value of the signature property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setSignature(JAXBElement<byte[]> value) {
        this.signature = value;
    }

    /**
     * Gets the value of the subjectAlternativeNames property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfArrayOfAnyType }{@code >}
     *     
     */
    public JAXBElement<ArrayOfArrayOfAnyType> getSubjectAlternativeNames() {
        return subjectAlternativeNames;
    }

    /**
     * Sets the value of the subjectAlternativeNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfArrayOfAnyType }{@code >}
     *     
     */
    public void setSubjectAlternativeNames(JAXBElement<ArrayOfArrayOfAnyType> value) {
        this.subjectAlternativeNames = value;
    }

    /**
     * Gets the value of the subjectDN property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Principal }{@code >}
     *     
     */
    public JAXBElement<Principal> getSubjectDN() {
        return subjectDN;
    }

    /**
     * Sets the value of the subjectDN property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Principal }{@code >}
     *     
     */
    public void setSubjectDN(JAXBElement<Principal> value) {
        this.subjectDN = value;
    }

    /**
     * Gets the value of the subjectUniqueID property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfBoolean }{@code >}
     *     
     */
    public JAXBElement<ArrayOfBoolean> getSubjectUniqueID() {
        return subjectUniqueID;
    }

    /**
     * Sets the value of the subjectUniqueID property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfBoolean }{@code >}
     *     
     */
    public void setSubjectUniqueID(JAXBElement<ArrayOfBoolean> value) {
        this.subjectUniqueID = value;
    }

    /**
     * Gets the value of the subjectX500Principal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link X500Principal }{@code >}
     *     
     */
    public JAXBElement<X500Principal> getSubjectX500Principal() {
        return subjectX500Principal;
    }

    /**
     * Sets the value of the subjectX500Principal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link X500Principal }{@code >}
     *     
     */
    public void setSubjectX500Principal(JAXBElement<X500Principal> value) {
        this.subjectX500Principal = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVersion(Integer value) {
        this.version = value;
    }

}
