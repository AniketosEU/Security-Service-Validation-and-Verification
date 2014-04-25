
package eu.aniketos.ncvm.pvm.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the eu.aniketos.ncvm.pvm.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _VerifyTechnicalTrustPropertiesResponse_QNAME = new QName("http://propertyverification.verification.components.aniketos.eu/", "verifyTechnicalTrustPropertiesResponse");
    private final static QName _VerifyWSDL_QNAME = new QName("http://propertyverification.verification.components.aniketos.eu/", "verifyWSDL");
    private final static QName _VerifyWSDLResponse_QNAME = new QName("http://propertyverification.verification.components.aniketos.eu/", "verifyWSDLResponse");
    private final static QName _VerifyTechnicalTrustProperties_QNAME = new QName("http://propertyverification.verification.components.aniketos.eu/", "verifyTechnicalTrustProperties");
    private final static QName _PropertyVerificationResultVerificationExplaination_QNAME = new QName("http://propertyverification.verification.components.aniketos.eu", "verificationExplaination");
    private final static QName _VerifyWSDLArg6_QNAME = new QName("http://propertyverification.verification.components.aniketos.eu/", "arg6");
    private final static QName _VerifyWSDLArg8_QNAME = new QName("http://propertyverification.verification.components.aniketos.eu/", "arg8");
    private final static QName _VerifyWSDLArg5_QNAME = new QName("http://propertyverification.verification.components.aniketos.eu/", "arg5");
    private final static QName _VerifyWSDLArg4_QNAME = new QName("http://propertyverification.verification.components.aniketos.eu/", "arg4");
    private final static QName _VerifyWSDLArg2_QNAME = new QName("http://propertyverification.verification.components.aniketos.eu/", "arg2");
    private final static QName _VerifyWSDLArg1_QNAME = new QName("http://propertyverification.verification.components.aniketos.eu/", "arg1");
    private final static QName _VerifyWSDLArg0_QNAME = new QName("http://propertyverification.verification.components.aniketos.eu/", "arg0");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: eu.aniketos.ncvm.pvm.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link VerifyWSDL }
     * 
     */
    public VerifyWSDL createVerifyWSDL() {
        return new VerifyWSDL();
    }

    /**
     * Create an instance of {@link VerifyWSDLResponse }
     * 
     */
    public VerifyWSDLResponse createVerifyWSDLResponse() {
        return new VerifyWSDLResponse();
    }

    /**
     * Create an instance of {@link VerifyTechnicalTrustProperties }
     * 
     */
    public VerifyTechnicalTrustProperties createVerifyTechnicalTrustProperties() {
        return new VerifyTechnicalTrustProperties();
    }

    /**
     * Create an instance of {@link VerifyTechnicalTrustPropertiesResponse }
     * 
     */
    public VerifyTechnicalTrustPropertiesResponse createVerifyTechnicalTrustPropertiesResponse() {
        return new VerifyTechnicalTrustPropertiesResponse();
    }

    /**
     * Create an instance of {@link PropertyVerificationResult }
     * 
     */
    public PropertyVerificationResult createPropertyVerificationResult() {
        return new PropertyVerificationResult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerifyTechnicalTrustPropertiesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://propertyverification.verification.components.aniketos.eu/", name = "verifyTechnicalTrustPropertiesResponse")
    public JAXBElement<VerifyTechnicalTrustPropertiesResponse> createVerifyTechnicalTrustPropertiesResponse(VerifyTechnicalTrustPropertiesResponse value) {
        return new JAXBElement<VerifyTechnicalTrustPropertiesResponse>(_VerifyTechnicalTrustPropertiesResponse_QNAME, VerifyTechnicalTrustPropertiesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerifyWSDL }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://propertyverification.verification.components.aniketos.eu/", name = "verifyWSDL")
    public JAXBElement<VerifyWSDL> createVerifyWSDL(VerifyWSDL value) {
        return new JAXBElement<VerifyWSDL>(_VerifyWSDL_QNAME, VerifyWSDL.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerifyWSDLResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://propertyverification.verification.components.aniketos.eu/", name = "verifyWSDLResponse")
    public JAXBElement<VerifyWSDLResponse> createVerifyWSDLResponse(VerifyWSDLResponse value) {
        return new JAXBElement<VerifyWSDLResponse>(_VerifyWSDLResponse_QNAME, VerifyWSDLResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerifyTechnicalTrustProperties }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://propertyverification.verification.components.aniketos.eu/", name = "verifyTechnicalTrustProperties")
    public JAXBElement<VerifyTechnicalTrustProperties> createVerifyTechnicalTrustProperties(VerifyTechnicalTrustProperties value) {
        return new JAXBElement<VerifyTechnicalTrustProperties>(_VerifyTechnicalTrustProperties_QNAME, VerifyTechnicalTrustProperties.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://propertyverification.verification.components.aniketos.eu", name = "verificationExplaination", scope = PropertyVerificationResult.class)
    public JAXBElement<String> createPropertyVerificationResultVerificationExplaination(String value) {
        return new JAXBElement<String>(_PropertyVerificationResultVerificationExplaination_QNAME, String.class, PropertyVerificationResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://propertyverification.verification.components.aniketos.eu/", name = "arg6", scope = VerifyWSDL.class)
    public JAXBElement<String> createVerifyWSDLArg6(String value) {
        return new JAXBElement<String>(_VerifyWSDLArg6_QNAME, String.class, VerifyWSDL.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://propertyverification.verification.components.aniketos.eu/", name = "arg8", scope = VerifyWSDL.class)
    public JAXBElement<String> createVerifyWSDLArg8(String value) {
        return new JAXBElement<String>(_VerifyWSDLArg8_QNAME, String.class, VerifyWSDL.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://propertyverification.verification.components.aniketos.eu/", name = "arg5", scope = VerifyWSDL.class)
    public JAXBElement<String> createVerifyWSDLArg5(String value) {
        return new JAXBElement<String>(_VerifyWSDLArg5_QNAME, String.class, VerifyWSDL.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://propertyverification.verification.components.aniketos.eu/", name = "arg4", scope = VerifyWSDL.class)
    public JAXBElement<String> createVerifyWSDLArg4(String value) {
        return new JAXBElement<String>(_VerifyWSDLArg4_QNAME, String.class, VerifyWSDL.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://propertyverification.verification.components.aniketos.eu/", name = "arg2", scope = VerifyWSDL.class)
    public JAXBElement<String> createVerifyWSDLArg2(String value) {
        return new JAXBElement<String>(_VerifyWSDLArg2_QNAME, String.class, VerifyWSDL.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://propertyverification.verification.components.aniketos.eu/", name = "arg1", scope = VerifyWSDL.class)
    public JAXBElement<String> createVerifyWSDLArg1(String value) {
        return new JAXBElement<String>(_VerifyWSDLArg1_QNAME, String.class, VerifyWSDL.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://propertyverification.verification.components.aniketos.eu/", name = "arg0", scope = VerifyWSDL.class)
    public JAXBElement<String> createVerifyWSDLArg0(String value) {
        return new JAXBElement<String>(_VerifyWSDLArg0_QNAME, String.class, VerifyWSDL.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://propertyverification.verification.components.aniketos.eu/", name = "arg1", scope = VerifyTechnicalTrustProperties.class)
    public JAXBElement<String> createVerifyTechnicalTrustPropertiesArg1(String value) {
        return new JAXBElement<String>(_VerifyWSDLArg1_QNAME, String.class, VerifyTechnicalTrustProperties.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://propertyverification.verification.components.aniketos.eu/", name = "arg0", scope = VerifyTechnicalTrustProperties.class)
    public JAXBElement<String> createVerifyTechnicalTrustPropertiesArg0(String value) {
        return new JAXBElement<String>(_VerifyWSDLArg0_QNAME, String.class, VerifyTechnicalTrustProperties.class, value);
    }

}
