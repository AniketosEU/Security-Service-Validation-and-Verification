
package eu.aniketos.ncvm.client;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the eu.aniketos.ncvm.client package. 
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

    private final static QName _ConfigureNCVMFeedbackArg0_QNAME = new QName("http://ncvm.aniketos.eu/", "arg0");
    private final static QName _ConfigureCSVMResponse_QNAME = new QName("http://ncvm.aniketos.eu/", "configureCSVMResponse");
    private final static QName _ConfigureNCVMFeedback_QNAME = new QName("http://ncvm.aniketos.eu/", "configureNCVMFeedback");
    private final static QName _ConfigureSPDMResponse_QNAME = new QName("http://ncvm.aniketos.eu/", "configureSPDMResponse");
    private final static QName _ConfigurePVMResponse_QNAME = new QName("http://ncvm.aniketos.eu/", "configurePVMResponse");
    private final static QName _ConfigureMarketplaceResponse_QNAME = new QName("http://ncvm.aniketos.eu/", "configureMarketplaceResponse");
    private final static QName _PerformTestsResponse_QNAME = new QName("http://ncvm.aniketos.eu/", "performTestsResponse");
    private final static QName _ConfigureNCVMFeedbackResponse_QNAME = new QName("http://ncvm.aniketos.eu/", "configureNCVMFeedbackResponse");
    private final static QName _ConfigureSPDM_QNAME = new QName("http://ncvm.aniketos.eu/", "configureSPDM");
    private final static QName _VerifyPropertyDeployed_QNAME = new QName("http://ncvm.aniketos.eu/", "verifyPropertyDeployed");
    private final static QName _ConfigurePVM_QNAME = new QName("http://ncvm.aniketos.eu/", "configurePVM");
    private final static QName _ConfigureMarketplace_QNAME = new QName("http://ncvm.aniketos.eu/", "configureMarketplace");
    private final static QName _ConfigureCSVM_QNAME = new QName("http://ncvm.aniketos.eu/", "configureCSVM");
    private final static QName _VerifyPropertyResponse_QNAME = new QName("http://ncvm.aniketos.eu/", "verifyPropertyResponse");
    private final static QName _VerifyPropertyDeployedResponse_QNAME = new QName("http://ncvm.aniketos.eu/", "verifyPropertyDeployedResponse");
    private final static QName _PerformTests_QNAME = new QName("http://ncvm.aniketos.eu/", "performTests");
    private final static QName _VerifyProperty_QNAME = new QName("http://ncvm.aniketos.eu/", "verifyProperty");
    private final static QName _KeyFormat_QNAME = new QName("http://security.java", "format");
    private final static QName _KeyAlgorithm_QNAME = new QName("http://security.java", "algorithm");
    private final static QName _KeyEncoded_QNAME = new QName("http://security.java", "encoded");
    private final static QName _ICompositionPlanCompositionPlanID_QNAME = new QName("http://data.aniketos.eu", "compositionPlanID");
    private final static QName _ICompositionPlanBPMNXML_QNAME = new QName("http://data.aniketos.eu", "BPMNXML");
    private final static QName _ICompositionPlanActivitiFile_QNAME = new QName("http://data.aniketos.eu", "activitiFile");
    private final static QName _PrincipalName_QNAME = new QName("http://security.java", "name");
    private final static QName _X509CertificateSubjectUniqueID_QNAME = new QName("http://cert.security.java", "subjectUniqueID");
    private final static QName _X509CertificateSigAlgOID_QNAME = new QName("http://cert.security.java", "sigAlgOID");
    private final static QName _X509CertificateTBSCertificate_QNAME = new QName("http://cert.security.java", "TBSCertificate");
    private final static QName _X509CertificateSigAlgParams_QNAME = new QName("http://cert.security.java", "sigAlgParams");
    private final static QName _X509CertificateSerialNumber_QNAME = new QName("http://cert.security.java", "serialNumber");
    private final static QName _X509CertificateIssuerAlternativeNames_QNAME = new QName("http://cert.security.java", "issuerAlternativeNames");
    private final static QName _X509CertificateKeyUsage_QNAME = new QName("http://cert.security.java", "keyUsage");
    private final static QName _X509CertificateSigAlgName_QNAME = new QName("http://cert.security.java", "sigAlgName");
    private final static QName _X509CertificateIssuerDN_QNAME = new QName("http://cert.security.java", "issuerDN");
    private final static QName _X509CertificateIssuerX500Principal_QNAME = new QName("http://cert.security.java", "issuerX500Principal");
    private final static QName _X509CertificateExtendedKeyUsage_QNAME = new QName("http://cert.security.java", "extendedKeyUsage");
    private final static QName _X509CertificateIssuerUniqueID_QNAME = new QName("http://cert.security.java", "issuerUniqueID");
    private final static QName _X509CertificateSubjectAlternativeNames_QNAME = new QName("http://cert.security.java", "subjectAlternativeNames");
    private final static QName _X509CertificateSubjectX500Principal_QNAME = new QName("http://cert.security.java", "subjectX500Principal");
    private final static QName _X509CertificateSubjectDN_QNAME = new QName("http://cert.security.java", "subjectDN");
    private final static QName _X509CertificateSignature_QNAME = new QName("http://cert.security.java", "signature");
    private final static QName _IVerificationResultErrorExplanation_QNAME = new QName("http://ncvm.aniketos.eu", "errorExplanation");
    private final static QName _CertificatePublicKey_QNAME = new QName("http://cert.security.java", "publicKey");
    private final static QName _CertificateType_QNAME = new QName("http://cert.security.java", "type");
    private final static QName _CertificateEncoded_QNAME = new QName("http://cert.security.java", "encoded");
    private final static QName _IConsumerPolicyXmlContents_QNAME = new QName("http://data.aniketos.eu", "xmlContents");
    private final static QName _IConsumerPolicyXML_QNAME = new QName("http://data.aniketos.eu", "XML");
    private final static QName _ISecurityPropertyState_QNAME = new QName("http://data.aniketos.eu", "state");
    private final static QName _ISecurityPropertyPropertyID_QNAME = new QName("http://data.aniketos.eu", "propertyID");
    private final static QName _ISecurityPropertyPropertyValue_QNAME = new QName("http://data.aniketos.eu", "propertyValue");
    private final static QName _ISecurityPropertyCertificate_QNAME = new QName("http://data.aniketos.eu", "certificate");
    private final static QName _ISecurityDescriptorProperties_QNAME = new QName("http://data.aniketos.eu", "properties");
    private final static QName _X500PrincipalName_QNAME = new QName("http://x500.auth.security.javax", "name");
    private final static QName _X500PrincipalEncoded_QNAME = new QName("http://x500.auth.security.javax", "encoded");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: eu.aniketos.ncvm.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConfigureNCVMFeedback }
     * 
     */
    public ConfigureNCVMFeedback createConfigureNCVMFeedback() {
        return new ConfigureNCVMFeedback();
    }

    /**
     * Create an instance of {@link ConfigureCSVMResponse }
     * 
     */
    public ConfigureCSVMResponse createConfigureCSVMResponse() {
        return new ConfigureCSVMResponse();
    }

    /**
     * Create an instance of {@link ConfigurePVMResponse }
     * 
     */
    public ConfigurePVMResponse createConfigurePVMResponse() {
        return new ConfigurePVMResponse();
    }

    /**
     * Create an instance of {@link ConfigureMarketplaceResponse }
     * 
     */
    public ConfigureMarketplaceResponse createConfigureMarketplaceResponse() {
        return new ConfigureMarketplaceResponse();
    }

    /**
     * Create an instance of {@link ConfigureSPDMResponse }
     * 
     */
    public ConfigureSPDMResponse createConfigureSPDMResponse() {
        return new ConfigureSPDMResponse();
    }

    /**
     * Create an instance of {@link PerformTestsResponse }
     * 
     */
    public PerformTestsResponse createPerformTestsResponse() {
        return new PerformTestsResponse();
    }

    /**
     * Create an instance of {@link ConfigureNCVMFeedbackResponse }
     * 
     */
    public ConfigureNCVMFeedbackResponse createConfigureNCVMFeedbackResponse() {
        return new ConfigureNCVMFeedbackResponse();
    }

    /**
     * Create an instance of {@link ConfigurePVM }
     * 
     */
    public ConfigurePVM createConfigurePVM() {
        return new ConfigurePVM();
    }

    /**
     * Create an instance of {@link ConfigureSPDM }
     * 
     */
    public ConfigureSPDM createConfigureSPDM() {
        return new ConfigureSPDM();
    }

    /**
     * Create an instance of {@link VerifyPropertyDeployed }
     * 
     */
    public VerifyPropertyDeployed createVerifyPropertyDeployed() {
        return new VerifyPropertyDeployed();
    }

    /**
     * Create an instance of {@link VerifyPropertyResponse }
     * 
     */
    public VerifyPropertyResponse createVerifyPropertyResponse() {
        return new VerifyPropertyResponse();
    }

    /**
     * Create an instance of {@link VerifyPropertyDeployedResponse }
     * 
     */
    public VerifyPropertyDeployedResponse createVerifyPropertyDeployedResponse() {
        return new VerifyPropertyDeployedResponse();
    }

    /**
     * Create an instance of {@link ConfigureMarketplace }
     * 
     */
    public ConfigureMarketplace createConfigureMarketplace() {
        return new ConfigureMarketplace();
    }

    /**
     * Create an instance of {@link ConfigureCSVM }
     * 
     */
    public ConfigureCSVM createConfigureCSVM() {
        return new ConfigureCSVM();
    }

    /**
     * Create an instance of {@link PerformTests }
     * 
     */
    public PerformTests createPerformTests() {
        return new PerformTests();
    }

    /**
     * Create an instance of {@link VerifyProperty }
     * 
     */
    public VerifyProperty createVerifyProperty() {
        return new VerifyProperty();
    }

    /**
     * Create an instance of {@link ArrayOfString }
     * 
     */
    public ArrayOfString createArrayOfString() {
        return new ArrayOfString();
    }

    /**
     * Create an instance of {@link ArrayOfBoolean }
     * 
     */
    public ArrayOfBoolean createArrayOfBoolean() {
        return new ArrayOfBoolean();
    }

    /**
     * Create an instance of {@link PublicKey }
     * 
     */
    public PublicKey createPublicKey() {
        return new PublicKey();
    }

    /**
     * Create an instance of {@link Key }
     * 
     */
    public Key createKey() {
        return new Key();
    }

    /**
     * Create an instance of {@link Principal }
     * 
     */
    public Principal createPrincipal() {
        return new Principal();
    }

    /**
     * Create an instance of {@link X500Principal }
     * 
     */
    public X500Principal createX500Principal() {
        return new X500Principal();
    }

    /**
     * Create an instance of {@link ArrayOfArrayOfAnyType }
     * 
     */
    public ArrayOfArrayOfAnyType createArrayOfArrayOfAnyType() {
        return new ArrayOfArrayOfAnyType();
    }

    /**
     * Create an instance of {@link ArrayOfAnyType }
     * 
     */
    public ArrayOfAnyType createArrayOfAnyType() {
        return new ArrayOfAnyType();
    }

    /**
     * Create an instance of {@link IVerificationResult }
     * 
     */
    public IVerificationResult createIVerificationResult() {
        return new IVerificationResult();
    }

    /**
     * Create an instance of {@link ISecurityProperty }
     * 
     */
    public ISecurityProperty createISecurityProperty() {
        return new ISecurityProperty();
    }

    /**
     * Create an instance of {@link ISecurityDescriptor }
     * 
     */
    public ISecurityDescriptor createISecurityDescriptor() {
        return new ISecurityDescriptor();
    }

    /**
     * Create an instance of {@link IConsumerPolicy }
     * 
     */
    public IConsumerPolicy createIConsumerPolicy() {
        return new IConsumerPolicy();
    }

    /**
     * Create an instance of {@link ArrayOfISecurityProperty }
     * 
     */
    public ArrayOfISecurityProperty createArrayOfISecurityProperty() {
        return new ArrayOfISecurityProperty();
    }

    /**
     * Create an instance of {@link ICompositionPlan }
     * 
     */
    public ICompositionPlan createICompositionPlan() {
        return new ICompositionPlan();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ncvm.aniketos.eu/", name = "arg0", scope = ConfigureNCVMFeedback.class)
    public JAXBElement<String> createConfigureNCVMFeedbackArg0(String value) {
        return new JAXBElement<String>(_ConfigureNCVMFeedbackArg0_QNAME, String.class, ConfigureNCVMFeedback.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfigureCSVMResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ncvm.aniketos.eu/", name = "configureCSVMResponse")
    public JAXBElement<ConfigureCSVMResponse> createConfigureCSVMResponse(ConfigureCSVMResponse value) {
        return new JAXBElement<ConfigureCSVMResponse>(_ConfigureCSVMResponse_QNAME, ConfigureCSVMResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfigureNCVMFeedback }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ncvm.aniketos.eu/", name = "configureNCVMFeedback")
    public JAXBElement<ConfigureNCVMFeedback> createConfigureNCVMFeedback(ConfigureNCVMFeedback value) {
        return new JAXBElement<ConfigureNCVMFeedback>(_ConfigureNCVMFeedback_QNAME, ConfigureNCVMFeedback.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfigureSPDMResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ncvm.aniketos.eu/", name = "configureSPDMResponse")
    public JAXBElement<ConfigureSPDMResponse> createConfigureSPDMResponse(ConfigureSPDMResponse value) {
        return new JAXBElement<ConfigureSPDMResponse>(_ConfigureSPDMResponse_QNAME, ConfigureSPDMResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfigurePVMResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ncvm.aniketos.eu/", name = "configurePVMResponse")
    public JAXBElement<ConfigurePVMResponse> createConfigurePVMResponse(ConfigurePVMResponse value) {
        return new JAXBElement<ConfigurePVMResponse>(_ConfigurePVMResponse_QNAME, ConfigurePVMResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfigureMarketplaceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ncvm.aniketos.eu/", name = "configureMarketplaceResponse")
    public JAXBElement<ConfigureMarketplaceResponse> createConfigureMarketplaceResponse(ConfigureMarketplaceResponse value) {
        return new JAXBElement<ConfigureMarketplaceResponse>(_ConfigureMarketplaceResponse_QNAME, ConfigureMarketplaceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PerformTestsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ncvm.aniketos.eu/", name = "performTestsResponse")
    public JAXBElement<PerformTestsResponse> createPerformTestsResponse(PerformTestsResponse value) {
        return new JAXBElement<PerformTestsResponse>(_PerformTestsResponse_QNAME, PerformTestsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfigureNCVMFeedbackResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ncvm.aniketos.eu/", name = "configureNCVMFeedbackResponse")
    public JAXBElement<ConfigureNCVMFeedbackResponse> createConfigureNCVMFeedbackResponse(ConfigureNCVMFeedbackResponse value) {
        return new JAXBElement<ConfigureNCVMFeedbackResponse>(_ConfigureNCVMFeedbackResponse_QNAME, ConfigureNCVMFeedbackResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfigureSPDM }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ncvm.aniketos.eu/", name = "configureSPDM")
    public JAXBElement<ConfigureSPDM> createConfigureSPDM(ConfigureSPDM value) {
        return new JAXBElement<ConfigureSPDM>(_ConfigureSPDM_QNAME, ConfigureSPDM.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerifyPropertyDeployed }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ncvm.aniketos.eu/", name = "verifyPropertyDeployed")
    public JAXBElement<VerifyPropertyDeployed> createVerifyPropertyDeployed(VerifyPropertyDeployed value) {
        return new JAXBElement<VerifyPropertyDeployed>(_VerifyPropertyDeployed_QNAME, VerifyPropertyDeployed.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfigurePVM }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ncvm.aniketos.eu/", name = "configurePVM")
    public JAXBElement<ConfigurePVM> createConfigurePVM(ConfigurePVM value) {
        return new JAXBElement<ConfigurePVM>(_ConfigurePVM_QNAME, ConfigurePVM.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfigureMarketplace }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ncvm.aniketos.eu/", name = "configureMarketplace")
    public JAXBElement<ConfigureMarketplace> createConfigureMarketplace(ConfigureMarketplace value) {
        return new JAXBElement<ConfigureMarketplace>(_ConfigureMarketplace_QNAME, ConfigureMarketplace.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfigureCSVM }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ncvm.aniketos.eu/", name = "configureCSVM")
    public JAXBElement<ConfigureCSVM> createConfigureCSVM(ConfigureCSVM value) {
        return new JAXBElement<ConfigureCSVM>(_ConfigureCSVM_QNAME, ConfigureCSVM.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerifyPropertyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ncvm.aniketos.eu/", name = "verifyPropertyResponse")
    public JAXBElement<VerifyPropertyResponse> createVerifyPropertyResponse(VerifyPropertyResponse value) {
        return new JAXBElement<VerifyPropertyResponse>(_VerifyPropertyResponse_QNAME, VerifyPropertyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerifyPropertyDeployedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ncvm.aniketos.eu/", name = "verifyPropertyDeployedResponse")
    public JAXBElement<VerifyPropertyDeployedResponse> createVerifyPropertyDeployedResponse(VerifyPropertyDeployedResponse value) {
        return new JAXBElement<VerifyPropertyDeployedResponse>(_VerifyPropertyDeployedResponse_QNAME, VerifyPropertyDeployedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PerformTests }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ncvm.aniketos.eu/", name = "performTests")
    public JAXBElement<PerformTests> createPerformTests(PerformTests value) {
        return new JAXBElement<PerformTests>(_PerformTests_QNAME, PerformTests.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerifyProperty }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ncvm.aniketos.eu/", name = "verifyProperty")
    public JAXBElement<VerifyProperty> createVerifyProperty(VerifyProperty value) {
        return new JAXBElement<VerifyProperty>(_VerifyProperty_QNAME, VerifyProperty.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://security.java", name = "format", scope = Key.class)
    public JAXBElement<String> createKeyFormat(String value) {
        return new JAXBElement<String>(_KeyFormat_QNAME, String.class, Key.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://security.java", name = "algorithm", scope = Key.class)
    public JAXBElement<String> createKeyAlgorithm(String value) {
        return new JAXBElement<String>(_KeyAlgorithm_QNAME, String.class, Key.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://security.java", name = "encoded", scope = Key.class)
    public JAXBElement<byte[]> createKeyEncoded(byte[] value) {
        return new JAXBElement<byte[]>(_KeyEncoded_QNAME, byte[].class, Key.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ncvm.aniketos.eu/", name = "arg0", scope = ConfigureSPDM.class)
    public JAXBElement<String> createConfigureSPDMArg0(String value) {
        return new JAXBElement<String>(_ConfigureNCVMFeedbackArg0_QNAME, String.class, ConfigureSPDM.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ncvm.aniketos.eu/", name = "arg0", scope = VerifyPropertyDeployed.class)
    public JAXBElement<String> createVerifyPropertyDeployedArg0(String value) {
        return new JAXBElement<String>(_ConfigureNCVMFeedbackArg0_QNAME, String.class, VerifyPropertyDeployed.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://data.aniketos.eu", name = "compositionPlanID", scope = ICompositionPlan.class)
    public JAXBElement<String> createICompositionPlanCompositionPlanID(String value) {
        return new JAXBElement<String>(_ICompositionPlanCompositionPlanID_QNAME, String.class, ICompositionPlan.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://data.aniketos.eu", name = "BPMNXML", scope = ICompositionPlan.class)
    public JAXBElement<String> createICompositionPlanBPMNXML(String value) {
        return new JAXBElement<String>(_ICompositionPlanBPMNXML_QNAME, String.class, ICompositionPlan.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://data.aniketos.eu", name = "activitiFile", scope = ICompositionPlan.class)
    public JAXBElement<String> createICompositionPlanActivitiFile(String value) {
        return new JAXBElement<String>(_ICompositionPlanActivitiFile_QNAME, String.class, ICompositionPlan.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://security.java", name = "name", scope = Principal.class)
    public JAXBElement<String> createPrincipalName(String value) {
        return new JAXBElement<String>(_PrincipalName_QNAME, String.class, Principal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfBoolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cert.security.java", name = "subjectUniqueID", scope = X509Certificate.class)
    public JAXBElement<ArrayOfBoolean> createX509CertificateSubjectUniqueID(ArrayOfBoolean value) {
        return new JAXBElement<ArrayOfBoolean>(_X509CertificateSubjectUniqueID_QNAME, ArrayOfBoolean.class, X509Certificate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cert.security.java", name = "sigAlgOID", scope = X509Certificate.class)
    public JAXBElement<String> createX509CertificateSigAlgOID(String value) {
        return new JAXBElement<String>(_X509CertificateSigAlgOID_QNAME, String.class, X509Certificate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cert.security.java", name = "TBSCertificate", scope = X509Certificate.class)
    public JAXBElement<byte[]> createX509CertificateTBSCertificate(byte[] value) {
        return new JAXBElement<byte[]>(_X509CertificateTBSCertificate_QNAME, byte[].class, X509Certificate.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cert.security.java", name = "sigAlgParams", scope = X509Certificate.class)
    public JAXBElement<byte[]> createX509CertificateSigAlgParams(byte[] value) {
        return new JAXBElement<byte[]>(_X509CertificateSigAlgParams_QNAME, byte[].class, X509Certificate.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cert.security.java", name = "serialNumber", scope = X509Certificate.class)
    public JAXBElement<BigInteger> createX509CertificateSerialNumber(BigInteger value) {
        return new JAXBElement<BigInteger>(_X509CertificateSerialNumber_QNAME, BigInteger.class, X509Certificate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfArrayOfAnyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cert.security.java", name = "issuerAlternativeNames", scope = X509Certificate.class)
    public JAXBElement<ArrayOfArrayOfAnyType> createX509CertificateIssuerAlternativeNames(ArrayOfArrayOfAnyType value) {
        return new JAXBElement<ArrayOfArrayOfAnyType>(_X509CertificateIssuerAlternativeNames_QNAME, ArrayOfArrayOfAnyType.class, X509Certificate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfBoolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cert.security.java", name = "keyUsage", scope = X509Certificate.class)
    public JAXBElement<ArrayOfBoolean> createX509CertificateKeyUsage(ArrayOfBoolean value) {
        return new JAXBElement<ArrayOfBoolean>(_X509CertificateKeyUsage_QNAME, ArrayOfBoolean.class, X509Certificate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cert.security.java", name = "sigAlgName", scope = X509Certificate.class)
    public JAXBElement<String> createX509CertificateSigAlgName(String value) {
        return new JAXBElement<String>(_X509CertificateSigAlgName_QNAME, String.class, X509Certificate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Principal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cert.security.java", name = "issuerDN", scope = X509Certificate.class)
    public JAXBElement<Principal> createX509CertificateIssuerDN(Principal value) {
        return new JAXBElement<Principal>(_X509CertificateIssuerDN_QNAME, Principal.class, X509Certificate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link X500Principal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cert.security.java", name = "issuerX500Principal", scope = X509Certificate.class)
    public JAXBElement<X500Principal> createX509CertificateIssuerX500Principal(X500Principal value) {
        return new JAXBElement<X500Principal>(_X509CertificateIssuerX500Principal_QNAME, X500Principal.class, X509Certificate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cert.security.java", name = "extendedKeyUsage", scope = X509Certificate.class)
    public JAXBElement<ArrayOfString> createX509CertificateExtendedKeyUsage(ArrayOfString value) {
        return new JAXBElement<ArrayOfString>(_X509CertificateExtendedKeyUsage_QNAME, ArrayOfString.class, X509Certificate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfBoolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cert.security.java", name = "issuerUniqueID", scope = X509Certificate.class)
    public JAXBElement<ArrayOfBoolean> createX509CertificateIssuerUniqueID(ArrayOfBoolean value) {
        return new JAXBElement<ArrayOfBoolean>(_X509CertificateIssuerUniqueID_QNAME, ArrayOfBoolean.class, X509Certificate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfArrayOfAnyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cert.security.java", name = "subjectAlternativeNames", scope = X509Certificate.class)
    public JAXBElement<ArrayOfArrayOfAnyType> createX509CertificateSubjectAlternativeNames(ArrayOfArrayOfAnyType value) {
        return new JAXBElement<ArrayOfArrayOfAnyType>(_X509CertificateSubjectAlternativeNames_QNAME, ArrayOfArrayOfAnyType.class, X509Certificate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link X500Principal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cert.security.java", name = "subjectX500Principal", scope = X509Certificate.class)
    public JAXBElement<X500Principal> createX509CertificateSubjectX500Principal(X500Principal value) {
        return new JAXBElement<X500Principal>(_X509CertificateSubjectX500Principal_QNAME, X500Principal.class, X509Certificate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Principal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cert.security.java", name = "subjectDN", scope = X509Certificate.class)
    public JAXBElement<Principal> createX509CertificateSubjectDN(Principal value) {
        return new JAXBElement<Principal>(_X509CertificateSubjectDN_QNAME, Principal.class, X509Certificate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cert.security.java", name = "signature", scope = X509Certificate.class)
    public JAXBElement<byte[]> createX509CertificateSignature(byte[] value) {
        return new JAXBElement<byte[]>(_X509CertificateSignature_QNAME, byte[].class, X509Certificate.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ncvm.aniketos.eu/", name = "arg0", scope = ConfigurePVM.class)
    public JAXBElement<String> createConfigurePVMArg0(String value) {
        return new JAXBElement<String>(_ConfigureNCVMFeedbackArg0_QNAME, String.class, ConfigurePVM.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ncvm.aniketos.eu", name = "errorExplanation", scope = IVerificationResult.class)
    public JAXBElement<String> createIVerificationResultErrorExplanation(String value) {
        return new JAXBElement<String>(_IVerificationResultErrorExplanation_QNAME, String.class, IVerificationResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ncvm.aniketos.eu/", name = "arg0", scope = ConfigureCSVM.class)
    public JAXBElement<String> createConfigureCSVMArg0(String value) {
        return new JAXBElement<String>(_ConfigureNCVMFeedbackArg0_QNAME, String.class, ConfigureCSVM.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PublicKey }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cert.security.java", name = "publicKey", scope = Certificate.class)
    public JAXBElement<PublicKey> createCertificatePublicKey(PublicKey value) {
        return new JAXBElement<PublicKey>(_CertificatePublicKey_QNAME, PublicKey.class, Certificate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cert.security.java", name = "type", scope = Certificate.class)
    public JAXBElement<String> createCertificateType(String value) {
        return new JAXBElement<String>(_CertificateType_QNAME, String.class, Certificate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cert.security.java", name = "encoded", scope = Certificate.class)
    public JAXBElement<byte[]> createCertificateEncoded(byte[] value) {
        return new JAXBElement<byte[]>(_CertificateEncoded_QNAME, byte[].class, Certificate.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://data.aniketos.eu", name = "xmlContents", scope = IConsumerPolicy.class)
    public JAXBElement<ArrayOfString> createIConsumerPolicyXmlContents(ArrayOfString value) {
        return new JAXBElement<ArrayOfString>(_IConsumerPolicyXmlContents_QNAME, ArrayOfString.class, IConsumerPolicy.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://data.aniketos.eu", name = "XML", scope = IConsumerPolicy.class)
    public JAXBElement<String> createIConsumerPolicyXML(String value) {
        return new JAXBElement<String>(_IConsumerPolicyXML_QNAME, String.class, IConsumerPolicy.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SPState }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://data.aniketos.eu", name = "state", scope = ISecurityProperty.class)
    public JAXBElement<SPState> createISecurityPropertyState(SPState value) {
        return new JAXBElement<SPState>(_ISecurityPropertyState_QNAME, SPState.class, ISecurityProperty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://data.aniketos.eu", name = "propertyID", scope = ISecurityProperty.class)
    public JAXBElement<String> createISecurityPropertyPropertyID(String value) {
        return new JAXBElement<String>(_ISecurityPropertyPropertyID_QNAME, String.class, ISecurityProperty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://data.aniketos.eu", name = "propertyValue", scope = ISecurityProperty.class)
    public JAXBElement<String> createISecurityPropertyPropertyValue(String value) {
        return new JAXBElement<String>(_ISecurityPropertyPropertyValue_QNAME, String.class, ISecurityProperty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link X509Certificate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://data.aniketos.eu", name = "certificate", scope = ISecurityProperty.class)
    public JAXBElement<X509Certificate> createISecurityPropertyCertificate(X509Certificate value) {
        return new JAXBElement<X509Certificate>(_ISecurityPropertyCertificate_QNAME, X509Certificate.class, ISecurityProperty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfISecurityProperty }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://data.aniketos.eu", name = "properties", scope = ISecurityDescriptor.class)
    public JAXBElement<ArrayOfISecurityProperty> createISecurityDescriptorProperties(ArrayOfISecurityProperty value) {
        return new JAXBElement<ArrayOfISecurityProperty>(_ISecurityDescriptorProperties_QNAME, ArrayOfISecurityProperty.class, ISecurityDescriptor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://x500.auth.security.javax", name = "name", scope = X500Principal.class)
    public JAXBElement<String> createX500PrincipalName(String value) {
        return new JAXBElement<String>(_X500PrincipalName_QNAME, String.class, X500Principal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://x500.auth.security.javax", name = "encoded", scope = X500Principal.class)
    public JAXBElement<byte[]> createX500PrincipalEncoded(byte[] value) {
        return new JAXBElement<byte[]>(_X500PrincipalEncoded_QNAME, byte[].class, X500Principal.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ncvm.aniketos.eu/", name = "arg0", scope = ConfigureMarketplace.class)
    public JAXBElement<String> createConfigureMarketplaceArg0(String value) {
        return new JAXBElement<String>(_ConfigureNCVMFeedbackArg0_QNAME, String.class, ConfigureMarketplace.class, value);
    }

}
