
package eu.aniketos.ncvm.csvm.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the eu.aniketos.ncvm.csvm.client package. 
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

    private final static QName _CompositionSecurityValidationResultVerificationResult_QNAME = new QName("http://compositionsecurityvalidation.verification.components.aniketos.eu", "verificationResult");
    private final static QName _CompositionSecurityValidationResultVerificationExplaination_QNAME = new QName("http://compositionsecurityvalidation.verification.components.aniketos.eu", "verificationExplaination");
    private final static QName _VerifyCompositionComplianceResponse_QNAME = new QName("http://compositionsecurityvalidation.verification.components.aniketos.eu/", "VerifyCompositionComplianceResponse");
    private final static QName _VerifyCompositionCompliance_QNAME = new QName("http://compositionsecurityvalidation.verification.components.aniketos.eu/", "VerifyCompositionCompliance");
    private final static QName _ICompositionPlanCompositionPlanID_QNAME = new QName("http://data.aniketos.eu", "compositionPlanID");
    private final static QName _ICompositionPlanBPMNXML_QNAME = new QName("http://data.aniketos.eu", "BPMNXML");
    private final static QName _ICompositionPlanActivitiFile_QNAME = new QName("http://data.aniketos.eu", "activitiFile");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: eu.aniketos.ncvm.csvm.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link VerifyCompositionCompliance }
     * 
     */
    public VerifyCompositionCompliance createVerifyCompositionCompliance() {
        return new VerifyCompositionCompliance();
    }

    /**
     * Create an instance of {@link VerifyCompositionComplianceResponse }
     * 
     */
    public VerifyCompositionComplianceResponse createVerifyCompositionComplianceResponse() {
        return new VerifyCompositionComplianceResponse();
    }

    /**
     * Create an instance of {@link ICompositionPlan }
     * 
     */
    public ICompositionPlan createICompositionPlan() {
        return new ICompositionPlan();
    }

    /**
     * Create an instance of {@link CompositionSecurityValidationResult }
     * 
     */
    public CompositionSecurityValidationResult createCompositionSecurityValidationResult() {
        return new CompositionSecurityValidationResult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://compositionsecurityvalidation.verification.components.aniketos.eu", name = "verificationResult", scope = CompositionSecurityValidationResult.class)
    public JAXBElement<Boolean> createCompositionSecurityValidationResultVerificationResult(Boolean value) {
        return new JAXBElement<Boolean>(_CompositionSecurityValidationResultVerificationResult_QNAME, Boolean.class, CompositionSecurityValidationResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://compositionsecurityvalidation.verification.components.aniketos.eu", name = "verificationExplaination", scope = CompositionSecurityValidationResult.class)
    public JAXBElement<String> createCompositionSecurityValidationResultVerificationExplaination(String value) {
        return new JAXBElement<String>(_CompositionSecurityValidationResultVerificationExplaination_QNAME, String.class, CompositionSecurityValidationResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerifyCompositionComplianceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://compositionsecurityvalidation.verification.components.aniketos.eu/", name = "VerifyCompositionComplianceResponse")
    public JAXBElement<VerifyCompositionComplianceResponse> createVerifyCompositionComplianceResponse(VerifyCompositionComplianceResponse value) {
        return new JAXBElement<VerifyCompositionComplianceResponse>(_VerifyCompositionComplianceResponse_QNAME, VerifyCompositionComplianceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerifyCompositionCompliance }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://compositionsecurityvalidation.verification.components.aniketos.eu/", name = "VerifyCompositionCompliance")
    public JAXBElement<VerifyCompositionCompliance> createVerifyCompositionCompliance(VerifyCompositionCompliance value) {
        return new JAXBElement<VerifyCompositionCompliance>(_VerifyCompositionCompliance_QNAME, VerifyCompositionCompliance.class, null, value);
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

}
