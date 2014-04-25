
package eu.aniketos.ncvm.marketplace.client;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the eu.aniketos.ncvm.marketplace.client package. 
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

    private final static QName _GetRules_QNAME = new QName("http://marketplace.aniketos.eu/", "getRules");
    private final static QName _GetSecurityDescriptorResponse_QNAME = new QName("http://marketplace.aniketos.eu/", "getSecurityDescriptorResponse");
    private final static QName _AnnounceServiceResponse_QNAME = new QName("http://marketplace.aniketos.eu/", "announceServiceResponse");
    private final static QName _UpdateBpmnDiagramResponse_QNAME = new QName("http://marketplace.aniketos.eu/", "updateBpmnDiagramResponse");
    private final static QName _GetBpmnDiagram_QNAME = new QName("http://marketplace.aniketos.eu/", "getBpmnDiagram");
    private final static QName _DiscoverServiceResponse_QNAME = new QName("http://marketplace.aniketos.eu/", "discoverServiceResponse");
    private final static QName _Provide_QNAME = new QName("http://marketplace.aniketos.eu/", "provide");
    private final static QName _GetBpmnDiagramResponse_QNAME = new QName("http://marketplace.aniketos.eu/", "getBpmnDiagramResponse");
    private final static QName _GetSource_QNAME = new QName("http://marketplace.aniketos.eu/", "getSource");
    private final static QName _GetTags_QNAME = new QName("http://marketplace.aniketos.eu/", "getTags");
    private final static QName _NotProvide_QNAME = new QName("http://marketplace.aniketos.eu/", "notProvide");
    private final static QName _GetAuthTokenResponse_QNAME = new QName("http://marketplace.aniketos.eu/", "getAuthTokenResponse");
    private final static QName _GetSecurityDescriptor_QNAME = new QName("http://marketplace.aniketos.eu/", "getSecurityDescriptor");
    private final static QName _GetAuthToken_QNAME = new QName("http://marketplace.aniketos.eu/", "getAuthToken");
    private final static QName _NotProvideResponse_QNAME = new QName("http://marketplace.aniketos.eu/", "notProvideResponse");
    private final static QName _ProvideResponse_QNAME = new QName("http://marketplace.aniketos.eu/", "provideResponse");
    private final static QName _IsTestableResponse_QNAME = new QName("http://marketplace.aniketos.eu/", "isTestableResponse");
    private final static QName _DiscoverService_QNAME = new QName("http://marketplace.aniketos.eu/", "discoverService");
    private final static QName _GetCompositionPlansResponse_QNAME = new QName("http://marketplace.aniketos.eu/", "getCompositionPlansResponse");
    private final static QName _GetCompositionPlans_QNAME = new QName("http://marketplace.aniketos.eu/", "getCompositionPlans");
    private final static QName _DeleteService_QNAME = new QName("http://marketplace.aniketos.eu/", "deleteService");
    private final static QName _IsTestable_QNAME = new QName("http://marketplace.aniketos.eu/", "isTestable");
    private final static QName _GetTagsResponse_QNAME = new QName("http://marketplace.aniketos.eu/", "getTagsResponse");
    private final static QName _UpdateSecurityDescriptionResponse_QNAME = new QName("http://marketplace.aniketos.eu/", "updateSecurityDescriptionResponse");
    private final static QName _AnnounceService_QNAME = new QName("http://marketplace.aniketos.eu/", "announceService");
    private final static QName _RegisterSource_QNAME = new QName("http://marketplace.aniketos.eu/", "registerSource");
    private final static QName _GetSourceResponse_QNAME = new QName("http://marketplace.aniketos.eu/", "getSourceResponse");
    private final static QName _UpdateSecurityDescription_QNAME = new QName("http://marketplace.aniketos.eu/", "updateSecurityDescription");
    private final static QName _UpdateBpmnDiagram_QNAME = new QName("http://marketplace.aniketos.eu/", "updateBpmnDiagram");
    private final static QName _RegisterSourceResponse_QNAME = new QName("http://marketplace.aniketos.eu/", "registerSourceResponse");
    private final static QName _DeleteServiceResponse_QNAME = new QName("http://marketplace.aniketos.eu/", "deleteServiceResponse");
    private final static QName _GetRulesResponse_QNAME = new QName("http://marketplace.aniketos.eu/", "getRulesResponse");
    private final static QName _GetSourceArg0_QNAME = new QName("http://marketplace.aniketos.eu/", "arg0");
    private final static QName _GetBpmnDiagramResponseReturn_QNAME = new QName("http://marketplace.aniketos.eu/", "return");
    private final static QName _MarketplaceSecurityPropertyPropertyValue_QNAME = new QName("http://marketplace.aniketos.eu", "propertyValue");
    private final static QName _MarketplaceSecurityPropertyPropertyID_QNAME = new QName("http://marketplace.aniketos.eu", "propertyID");
    private final static QName _MarketplaceSecurityPropertyConspec_QNAME = new QName("http://marketplace.aniketos.eu", "conspec");
    private final static QName _MarketplaceSecurityPropertyState_QNAME = new QName("http://marketplace.aniketos.eu", "state");
    private final static QName _KeyFormat_QNAME = new QName("http://security.java", "format");
    private final static QName _KeyAlgorithm_QNAME = new QName("http://security.java", "algorithm");
    private final static QName _KeyEncoded_QNAME = new QName("http://security.java", "encoded");
    private final static QName _GetAuthTokenArg1_QNAME = new QName("http://marketplace.aniketos.eu/", "arg1");
    private final static QName _ICompositionPlanCompositionPlanID_QNAME = new QName("http://data.aniketos.eu", "compositionPlanID");
    private final static QName _ICompositionPlanBPMNXML_QNAME = new QName("http://data.aniketos.eu", "BPMNXML");
    private final static QName _ICompositionPlanActivitiFile_QNAME = new QName("http://data.aniketos.eu", "activitiFile");
    private final static QName _MarketplaceAnnouncementSender_QNAME = new QName("http://marketplace.aniketos.eu", "sender");
    private final static QName _MarketplaceAnnouncementServiceDescriptor_QNAME = new QName("http://marketplace.aniketos.eu", "serviceDescriptor");
    private final static QName _MarketplaceAnnouncementRegistry_QNAME = new QName("http://marketplace.aniketos.eu", "registry");
    private final static QName _MarketplaceAnnouncementCompositionPlans_QNAME = new QName("http://marketplace.aniketos.eu", "compositionPlans");
    private final static QName _MarketplaceAnnouncementSecurityDescriptor_QNAME = new QName("http://marketplace.aniketos.eu", "securityDescriptor");
    private final static QName _MarketplaceAnnouncementRules_QNAME = new QName("http://marketplace.aniketos.eu", "rules");
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
    private final static QName _ServiceOperationTag_QNAME = new QName("http://marketplace.aniketos.eu", "tag");
    private final static QName _ServiceOperationMethod_QNAME = new QName("http://marketplace.aniketos.eu", "method");
    private final static QName _IConsumerPolicyXmlContents_QNAME = new QName("http://data.aniketos.eu", "xmlContents");
    private final static QName _IConsumerPolicyXML_QNAME = new QName("http://data.aniketos.eu", "XML");
    private final static QName _ServiceDescriptorId_QNAME = new QName("http://marketplace.aniketos.eu", "id");
    private final static QName _ServiceDescriptorName_QNAME = new QName("http://marketplace.aniketos.eu", "name");
    private final static QName _ServiceDescriptorDescription_QNAME = new QName("http://marketplace.aniketos.eu", "description");
    private final static QName _ServiceDescriptorProviderName_QNAME = new QName("http://marketplace.aniketos.eu", "providerName");
    private final static QName _ServiceDescriptorOperations_QNAME = new QName("http://marketplace.aniketos.eu", "operations");
    private final static QName _ServiceDescriptorBinding_QNAME = new QName("http://marketplace.aniketos.eu", "binding");
    private final static QName _ISecurityPropertyState_QNAME = new QName("http://data.aniketos.eu", "state");
    private final static QName _ISecurityPropertyPropertyID_QNAME = new QName("http://data.aniketos.eu", "propertyID");
    private final static QName _ISecurityPropertyPropertyValue_QNAME = new QName("http://data.aniketos.eu", "propertyValue");
    private final static QName _ISecurityPropertyCertificate_QNAME = new QName("http://data.aniketos.eu", "certificate");
    private final static QName _CertificatePublicKey_QNAME = new QName("http://cert.security.java", "publicKey");
    private final static QName _CertificateType_QNAME = new QName("http://cert.security.java", "type");
    private final static QName _CertificateEncoded_QNAME = new QName("http://cert.security.java", "encoded");
    private final static QName _UpdateBpmnDiagramArg2_QNAME = new QName("http://marketplace.aniketos.eu/", "arg2");
    private final static QName _MarketplaceSecurityDescriptorSecurityProperties_QNAME = new QName("http://marketplace.aniketos.eu", "securityProperties");
    private final static QName _ISecurityDescriptorProperties_QNAME = new QName("http://data.aniketos.eu", "properties");
    private final static QName _MarketplaceSearchParamsTags_QNAME = new QName("http://marketplace.aniketos.eu", "tags");
    private final static QName _MarketplaceSearchParamsSecurityProperty_QNAME = new QName("http://marketplace.aniketos.eu", "securityProperty");
    private final static QName _MarketplaceSearchParamsOwner_QNAME = new QName("http://marketplace.aniketos.eu", "owner");
    private final static QName _MarketplaceSearchParamsUrl_QNAME = new QName("http://marketplace.aniketos.eu", "url");
    private final static QName _X500PrincipalName_QNAME = new QName("http://x500.auth.security.javax", "name");
    private final static QName _X500PrincipalEncoded_QNAME = new QName("http://x500.auth.security.javax", "encoded");
    private final static QName _PrincipalName_QNAME = new QName("http://security.java", "name");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: eu.aniketos.ncvm.marketplace.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetBpmnDiagramResponse }
     * 
     */
    public GetBpmnDiagramResponse createGetBpmnDiagramResponse() {
        return new GetBpmnDiagramResponse();
    }

    /**
     * Create an instance of {@link Provide }
     * 
     */
    public Provide createProvide() {
        return new Provide();
    }

    /**
     * Create an instance of {@link GetAuthTokenResponse }
     * 
     */
    public GetAuthTokenResponse createGetAuthTokenResponse() {
        return new GetAuthTokenResponse();
    }

    /**
     * Create an instance of {@link GetSecurityDescriptor }
     * 
     */
    public GetSecurityDescriptor createGetSecurityDescriptor() {
        return new GetSecurityDescriptor();
    }

    /**
     * Create an instance of {@link GetSource }
     * 
     */
    public GetSource createGetSource() {
        return new GetSource();
    }

    /**
     * Create an instance of {@link GetTags }
     * 
     */
    public GetTags createGetTags() {
        return new GetTags();
    }

    /**
     * Create an instance of {@link NotProvide }
     * 
     */
    public NotProvide createNotProvide() {
        return new NotProvide();
    }

    /**
     * Create an instance of {@link AnnounceServiceResponse }
     * 
     */
    public AnnounceServiceResponse createAnnounceServiceResponse() {
        return new AnnounceServiceResponse();
    }

    /**
     * Create an instance of {@link GetRules }
     * 
     */
    public GetRules createGetRules() {
        return new GetRules();
    }

    /**
     * Create an instance of {@link GetSecurityDescriptorResponse }
     * 
     */
    public GetSecurityDescriptorResponse createGetSecurityDescriptorResponse() {
        return new GetSecurityDescriptorResponse();
    }

    /**
     * Create an instance of {@link DiscoverServiceResponse }
     * 
     */
    public DiscoverServiceResponse createDiscoverServiceResponse() {
        return new DiscoverServiceResponse();
    }

    /**
     * Create an instance of {@link UpdateBpmnDiagramResponse }
     * 
     */
    public UpdateBpmnDiagramResponse createUpdateBpmnDiagramResponse() {
        return new UpdateBpmnDiagramResponse();
    }

    /**
     * Create an instance of {@link GetBpmnDiagram }
     * 
     */
    public GetBpmnDiagram createGetBpmnDiagram() {
        return new GetBpmnDiagram();
    }

    /**
     * Create an instance of {@link UpdateBpmnDiagram }
     * 
     */
    public UpdateBpmnDiagram createUpdateBpmnDiagram() {
        return new UpdateBpmnDiagram();
    }

    /**
     * Create an instance of {@link RegisterSourceResponse }
     * 
     */
    public RegisterSourceResponse createRegisterSourceResponse() {
        return new RegisterSourceResponse();
    }

    /**
     * Create an instance of {@link AnnounceService }
     * 
     */
    public AnnounceService createAnnounceService() {
        return new AnnounceService();
    }

    /**
     * Create an instance of {@link RegisterSource }
     * 
     */
    public RegisterSource createRegisterSource() {
        return new RegisterSource();
    }

    /**
     * Create an instance of {@link GetSourceResponse }
     * 
     */
    public GetSourceResponse createGetSourceResponse() {
        return new GetSourceResponse();
    }

    /**
     * Create an instance of {@link UpdateSecurityDescription }
     * 
     */
    public UpdateSecurityDescription createUpdateSecurityDescription() {
        return new UpdateSecurityDescription();
    }

    /**
     * Create an instance of {@link DeleteServiceResponse }
     * 
     */
    public DeleteServiceResponse createDeleteServiceResponse() {
        return new DeleteServiceResponse();
    }

    /**
     * Create an instance of {@link GetRulesResponse }
     * 
     */
    public GetRulesResponse createGetRulesResponse() {
        return new GetRulesResponse();
    }

    /**
     * Create an instance of {@link IsTestableResponse }
     * 
     */
    public IsTestableResponse createIsTestableResponse() {
        return new IsTestableResponse();
    }

    /**
     * Create an instance of {@link DiscoverService }
     * 
     */
    public DiscoverService createDiscoverService() {
        return new DiscoverService();
    }

    /**
     * Create an instance of {@link GetCompositionPlansResponse }
     * 
     */
    public GetCompositionPlansResponse createGetCompositionPlansResponse() {
        return new GetCompositionPlansResponse();
    }

    /**
     * Create an instance of {@link GetCompositionPlans }
     * 
     */
    public GetCompositionPlans createGetCompositionPlans() {
        return new GetCompositionPlans();
    }

    /**
     * Create an instance of {@link GetAuthToken }
     * 
     */
    public GetAuthToken createGetAuthToken() {
        return new GetAuthToken();
    }

    /**
     * Create an instance of {@link ProvideResponse }
     * 
     */
    public ProvideResponse createProvideResponse() {
        return new ProvideResponse();
    }

    /**
     * Create an instance of {@link NotProvideResponse }
     * 
     */
    public NotProvideResponse createNotProvideResponse() {
        return new NotProvideResponse();
    }

    /**
     * Create an instance of {@link GetTagsResponse }
     * 
     */
    public GetTagsResponse createGetTagsResponse() {
        return new GetTagsResponse();
    }

    /**
     * Create an instance of {@link UpdateSecurityDescriptionResponse }
     * 
     */
    public UpdateSecurityDescriptionResponse createUpdateSecurityDescriptionResponse() {
        return new UpdateSecurityDescriptionResponse();
    }

    /**
     * Create an instance of {@link DeleteService }
     * 
     */
    public DeleteService createDeleteService() {
        return new DeleteService();
    }

    /**
     * Create an instance of {@link IsTestable }
     * 
     */
    public IsTestable createIsTestable() {
        return new IsTestable();
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
     * Create an instance of {@link ISecurityDescriptor }
     * 
     */
    public ISecurityDescriptor createISecurityDescriptor() {
        return new ISecurityDescriptor();
    }

    /**
     * Create an instance of {@link ISecurityProperty }
     * 
     */
    public ISecurityProperty createISecurityProperty() {
        return new ISecurityProperty();
    }

    /**
     * Create an instance of {@link IConsumerPolicy }
     * 
     */
    public IConsumerPolicy createIConsumerPolicy() {
        return new IConsumerPolicy();
    }

    /**
     * Create an instance of {@link ArrayOfICompositionPlan }
     * 
     */
    public ArrayOfICompositionPlan createArrayOfICompositionPlan() {
        return new ArrayOfICompositionPlan();
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
     * Create an instance of {@link ArrayOfTag }
     * 
     */
    public ArrayOfTag createArrayOfTag() {
        return new ArrayOfTag();
    }

    /**
     * Create an instance of {@link MarketplaceSearchParams }
     * 
     */
    public MarketplaceSearchParams createMarketplaceSearchParams() {
        return new MarketplaceSearchParams();
    }

    /**
     * Create an instance of {@link ArrayOfServiceOperation }
     * 
     */
    public ArrayOfServiceOperation createArrayOfServiceOperation() {
        return new ArrayOfServiceOperation();
    }

    /**
     * Create an instance of {@link Tag }
     * 
     */
    public Tag createTag() {
        return new Tag();
    }

    /**
     * Create an instance of {@link ServiceOperation }
     * 
     */
    public ServiceOperation createServiceOperation() {
        return new ServiceOperation();
    }

    /**
     * Create an instance of {@link ArrayOfServiceDescriptor }
     * 
     */
    public ArrayOfServiceDescriptor createArrayOfServiceDescriptor() {
        return new ArrayOfServiceDescriptor();
    }

    /**
     * Create an instance of {@link MarketplaceAnnouncement }
     * 
     */
    public MarketplaceAnnouncement createMarketplaceAnnouncement() {
        return new MarketplaceAnnouncement();
    }

    /**
     * Create an instance of {@link MarketplaceSecurityProperty }
     * 
     */
    public MarketplaceSecurityProperty createMarketplaceSecurityProperty() {
        return new MarketplaceSecurityProperty();
    }

    /**
     * Create an instance of {@link ServiceDescriptor }
     * 
     */
    public ServiceDescriptor createServiceDescriptor() {
        return new ServiceDescriptor();
    }

    /**
     * Create an instance of {@link ArrayOfMarketplaceSecurityProperty }
     * 
     */
    public ArrayOfMarketplaceSecurityProperty createArrayOfMarketplaceSecurityProperty() {
        return new ArrayOfMarketplaceSecurityProperty();
    }

    /**
     * Create an instance of {@link MarketplaceSecurityDescriptor }
     * 
     */
    public MarketplaceSecurityDescriptor createMarketplaceSecurityDescriptor() {
        return new MarketplaceSecurityDescriptor();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRules }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "getRules")
    public JAXBElement<GetRules> createGetRules(GetRules value) {
        return new JAXBElement<GetRules>(_GetRules_QNAME, GetRules.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSecurityDescriptorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "getSecurityDescriptorResponse")
    public JAXBElement<GetSecurityDescriptorResponse> createGetSecurityDescriptorResponse(GetSecurityDescriptorResponse value) {
        return new JAXBElement<GetSecurityDescriptorResponse>(_GetSecurityDescriptorResponse_QNAME, GetSecurityDescriptorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnnounceServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "announceServiceResponse")
    public JAXBElement<AnnounceServiceResponse> createAnnounceServiceResponse(AnnounceServiceResponse value) {
        return new JAXBElement<AnnounceServiceResponse>(_AnnounceServiceResponse_QNAME, AnnounceServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateBpmnDiagramResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "updateBpmnDiagramResponse")
    public JAXBElement<UpdateBpmnDiagramResponse> createUpdateBpmnDiagramResponse(UpdateBpmnDiagramResponse value) {
        return new JAXBElement<UpdateBpmnDiagramResponse>(_UpdateBpmnDiagramResponse_QNAME, UpdateBpmnDiagramResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBpmnDiagram }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "getBpmnDiagram")
    public JAXBElement<GetBpmnDiagram> createGetBpmnDiagram(GetBpmnDiagram value) {
        return new JAXBElement<GetBpmnDiagram>(_GetBpmnDiagram_QNAME, GetBpmnDiagram.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DiscoverServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "discoverServiceResponse")
    public JAXBElement<DiscoverServiceResponse> createDiscoverServiceResponse(DiscoverServiceResponse value) {
        return new JAXBElement<DiscoverServiceResponse>(_DiscoverServiceResponse_QNAME, DiscoverServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Provide }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "provide")
    public JAXBElement<Provide> createProvide(Provide value) {
        return new JAXBElement<Provide>(_Provide_QNAME, Provide.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBpmnDiagramResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "getBpmnDiagramResponse")
    public JAXBElement<GetBpmnDiagramResponse> createGetBpmnDiagramResponse(GetBpmnDiagramResponse value) {
        return new JAXBElement<GetBpmnDiagramResponse>(_GetBpmnDiagramResponse_QNAME, GetBpmnDiagramResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSource }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "getSource")
    public JAXBElement<GetSource> createGetSource(GetSource value) {
        return new JAXBElement<GetSource>(_GetSource_QNAME, GetSource.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTags }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "getTags")
    public JAXBElement<GetTags> createGetTags(GetTags value) {
        return new JAXBElement<GetTags>(_GetTags_QNAME, GetTags.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotProvide }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "notProvide")
    public JAXBElement<NotProvide> createNotProvide(NotProvide value) {
        return new JAXBElement<NotProvide>(_NotProvide_QNAME, NotProvide.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAuthTokenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "getAuthTokenResponse")
    public JAXBElement<GetAuthTokenResponse> createGetAuthTokenResponse(GetAuthTokenResponse value) {
        return new JAXBElement<GetAuthTokenResponse>(_GetAuthTokenResponse_QNAME, GetAuthTokenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSecurityDescriptor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "getSecurityDescriptor")
    public JAXBElement<GetSecurityDescriptor> createGetSecurityDescriptor(GetSecurityDescriptor value) {
        return new JAXBElement<GetSecurityDescriptor>(_GetSecurityDescriptor_QNAME, GetSecurityDescriptor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAuthToken }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "getAuthToken")
    public JAXBElement<GetAuthToken> createGetAuthToken(GetAuthToken value) {
        return new JAXBElement<GetAuthToken>(_GetAuthToken_QNAME, GetAuthToken.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotProvideResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "notProvideResponse")
    public JAXBElement<NotProvideResponse> createNotProvideResponse(NotProvideResponse value) {
        return new JAXBElement<NotProvideResponse>(_NotProvideResponse_QNAME, NotProvideResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProvideResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "provideResponse")
    public JAXBElement<ProvideResponse> createProvideResponse(ProvideResponse value) {
        return new JAXBElement<ProvideResponse>(_ProvideResponse_QNAME, ProvideResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsTestableResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "isTestableResponse")
    public JAXBElement<IsTestableResponse> createIsTestableResponse(IsTestableResponse value) {
        return new JAXBElement<IsTestableResponse>(_IsTestableResponse_QNAME, IsTestableResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DiscoverService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "discoverService")
    public JAXBElement<DiscoverService> createDiscoverService(DiscoverService value) {
        return new JAXBElement<DiscoverService>(_DiscoverService_QNAME, DiscoverService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCompositionPlansResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "getCompositionPlansResponse")
    public JAXBElement<GetCompositionPlansResponse> createGetCompositionPlansResponse(GetCompositionPlansResponse value) {
        return new JAXBElement<GetCompositionPlansResponse>(_GetCompositionPlansResponse_QNAME, GetCompositionPlansResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCompositionPlans }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "getCompositionPlans")
    public JAXBElement<GetCompositionPlans> createGetCompositionPlans(GetCompositionPlans value) {
        return new JAXBElement<GetCompositionPlans>(_GetCompositionPlans_QNAME, GetCompositionPlans.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "deleteService")
    public JAXBElement<DeleteService> createDeleteService(DeleteService value) {
        return new JAXBElement<DeleteService>(_DeleteService_QNAME, DeleteService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsTestable }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "isTestable")
    public JAXBElement<IsTestable> createIsTestable(IsTestable value) {
        return new JAXBElement<IsTestable>(_IsTestable_QNAME, IsTestable.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTagsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "getTagsResponse")
    public JAXBElement<GetTagsResponse> createGetTagsResponse(GetTagsResponse value) {
        return new JAXBElement<GetTagsResponse>(_GetTagsResponse_QNAME, GetTagsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateSecurityDescriptionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "updateSecurityDescriptionResponse")
    public JAXBElement<UpdateSecurityDescriptionResponse> createUpdateSecurityDescriptionResponse(UpdateSecurityDescriptionResponse value) {
        return new JAXBElement<UpdateSecurityDescriptionResponse>(_UpdateSecurityDescriptionResponse_QNAME, UpdateSecurityDescriptionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnnounceService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "announceService")
    public JAXBElement<AnnounceService> createAnnounceService(AnnounceService value) {
        return new JAXBElement<AnnounceService>(_AnnounceService_QNAME, AnnounceService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterSource }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "registerSource")
    public JAXBElement<RegisterSource> createRegisterSource(RegisterSource value) {
        return new JAXBElement<RegisterSource>(_RegisterSource_QNAME, RegisterSource.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSourceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "getSourceResponse")
    public JAXBElement<GetSourceResponse> createGetSourceResponse(GetSourceResponse value) {
        return new JAXBElement<GetSourceResponse>(_GetSourceResponse_QNAME, GetSourceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateSecurityDescription }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "updateSecurityDescription")
    public JAXBElement<UpdateSecurityDescription> createUpdateSecurityDescription(UpdateSecurityDescription value) {
        return new JAXBElement<UpdateSecurityDescription>(_UpdateSecurityDescription_QNAME, UpdateSecurityDescription.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateBpmnDiagram }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "updateBpmnDiagram")
    public JAXBElement<UpdateBpmnDiagram> createUpdateBpmnDiagram(UpdateBpmnDiagram value) {
        return new JAXBElement<UpdateBpmnDiagram>(_UpdateBpmnDiagram_QNAME, UpdateBpmnDiagram.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterSourceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "registerSourceResponse")
    public JAXBElement<RegisterSourceResponse> createRegisterSourceResponse(RegisterSourceResponse value) {
        return new JAXBElement<RegisterSourceResponse>(_RegisterSourceResponse_QNAME, RegisterSourceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "deleteServiceResponse")
    public JAXBElement<DeleteServiceResponse> createDeleteServiceResponse(DeleteServiceResponse value) {
        return new JAXBElement<DeleteServiceResponse>(_DeleteServiceResponse_QNAME, DeleteServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRulesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "getRulesResponse")
    public JAXBElement<GetRulesResponse> createGetRulesResponse(GetRulesResponse value) {
        return new JAXBElement<GetRulesResponse>(_GetRulesResponse_QNAME, GetRulesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg0", scope = GetSource.class)
    public JAXBElement<String> createGetSourceArg0(String value) {
        return new JAXBElement<String>(_GetSourceArg0_QNAME, String.class, GetSource.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "return", scope = GetBpmnDiagramResponse.class)
    public JAXBElement<String> createGetBpmnDiagramResponseReturn(String value) {
        return new JAXBElement<String>(_GetBpmnDiagramResponseReturn_QNAME, String.class, GetBpmnDiagramResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "propertyValue", scope = MarketplaceSecurityProperty.class)
    public JAXBElement<String> createMarketplaceSecurityPropertyPropertyValue(String value) {
        return new JAXBElement<String>(_MarketplaceSecurityPropertyPropertyValue_QNAME, String.class, MarketplaceSecurityProperty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "propertyID", scope = MarketplaceSecurityProperty.class)
    public JAXBElement<String> createMarketplaceSecurityPropertyPropertyID(String value) {
        return new JAXBElement<String>(_MarketplaceSecurityPropertyPropertyID_QNAME, String.class, MarketplaceSecurityProperty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "conspec", scope = MarketplaceSecurityProperty.class)
    public JAXBElement<String> createMarketplaceSecurityPropertyConspec(String value) {
        return new JAXBElement<String>(_MarketplaceSecurityPropertyConspec_QNAME, String.class, MarketplaceSecurityProperty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SPState }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "state", scope = MarketplaceSecurityProperty.class)
    public JAXBElement<SPState> createMarketplaceSecurityPropertyState(SPState value) {
        return new JAXBElement<SPState>(_MarketplaceSecurityPropertyState_QNAME, SPState.class, MarketplaceSecurityProperty.class, value);
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
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg1", scope = GetAuthToken.class)
    public JAXBElement<String> createGetAuthTokenArg1(String value) {
        return new JAXBElement<String>(_GetAuthTokenArg1_QNAME, String.class, GetAuthToken.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg0", scope = GetAuthToken.class)
    public JAXBElement<String> createGetAuthTokenArg0(String value) {
        return new JAXBElement<String>(_GetSourceArg0_QNAME, String.class, GetAuthToken.class, value);
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
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg0", scope = AnnounceService.class)
    public JAXBElement<String> createAnnounceServiceArg0(String value) {
        return new JAXBElement<String>(_GetSourceArg0_QNAME, String.class, AnnounceService.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg1", scope = GetSecurityDescriptor.class)
    public JAXBElement<String> createGetSecurityDescriptorArg1(String value) {
        return new JAXBElement<String>(_GetAuthTokenArg1_QNAME, String.class, GetSecurityDescriptor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg0", scope = GetSecurityDescriptor.class)
    public JAXBElement<String> createGetSecurityDescriptorArg0(String value) {
        return new JAXBElement<String>(_GetSourceArg0_QNAME, String.class, GetSecurityDescriptor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg0", scope = GetCompositionPlans.class)
    public JAXBElement<String> createGetCompositionPlansArg0(String value) {
        return new JAXBElement<String>(_GetSourceArg0_QNAME, String.class, GetCompositionPlans.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg1", scope = NotProvide.class)
    public JAXBElement<String> createNotProvideArg1(String value) {
        return new JAXBElement<String>(_GetAuthTokenArg1_QNAME, String.class, NotProvide.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg0", scope = NotProvide.class)
    public JAXBElement<String> createNotProvideArg0(String value) {
        return new JAXBElement<String>(_GetSourceArg0_QNAME, String.class, NotProvide.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "sender", scope = MarketplaceAnnouncement.class)
    public JAXBElement<String> createMarketplaceAnnouncementSender(String value) {
        return new JAXBElement<String>(_MarketplaceAnnouncementSender_QNAME, String.class, MarketplaceAnnouncement.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceDescriptor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "serviceDescriptor", scope = MarketplaceAnnouncement.class)
    public JAXBElement<ServiceDescriptor> createMarketplaceAnnouncementServiceDescriptor(ServiceDescriptor value) {
        return new JAXBElement<ServiceDescriptor>(_MarketplaceAnnouncementServiceDescriptor_QNAME, ServiceDescriptor.class, MarketplaceAnnouncement.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "registry", scope = MarketplaceAnnouncement.class)
    public JAXBElement<String> createMarketplaceAnnouncementRegistry(String value) {
        return new JAXBElement<String>(_MarketplaceAnnouncementRegistry_QNAME, String.class, MarketplaceAnnouncement.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfICompositionPlan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "compositionPlans", scope = MarketplaceAnnouncement.class)
    public JAXBElement<ArrayOfICompositionPlan> createMarketplaceAnnouncementCompositionPlans(ArrayOfICompositionPlan value) {
        return new JAXBElement<ArrayOfICompositionPlan>(_MarketplaceAnnouncementCompositionPlans_QNAME, ArrayOfICompositionPlan.class, MarketplaceAnnouncement.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MarketplaceSecurityDescriptor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "securityDescriptor", scope = MarketplaceAnnouncement.class)
    public JAXBElement<MarketplaceSecurityDescriptor> createMarketplaceAnnouncementSecurityDescriptor(MarketplaceSecurityDescriptor value) {
        return new JAXBElement<MarketplaceSecurityDescriptor>(_MarketplaceAnnouncementSecurityDescriptor_QNAME, MarketplaceSecurityDescriptor.class, MarketplaceAnnouncement.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "rules", scope = MarketplaceAnnouncement.class)
    public JAXBElement<String> createMarketplaceAnnouncementRules(String value) {
        return new JAXBElement<String>(_MarketplaceAnnouncementRules_QNAME, String.class, MarketplaceAnnouncement.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "return", scope = AnnounceServiceResponse.class)
    public JAXBElement<String> createAnnounceServiceResponseReturn(String value) {
        return new JAXBElement<String>(_GetBpmnDiagramResponseReturn_QNAME, String.class, AnnounceServiceResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg1", scope = DeleteService.class)
    public JAXBElement<String> createDeleteServiceArg1(String value) {
        return new JAXBElement<String>(_GetAuthTokenArg1_QNAME, String.class, DeleteService.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg0", scope = DeleteService.class)
    public JAXBElement<String> createDeleteServiceArg0(String value) {
        return new JAXBElement<String>(_GetSourceArg0_QNAME, String.class, DeleteService.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg0", scope = IsTestable.class)
    public JAXBElement<String> createIsTestableArg0(String value) {
        return new JAXBElement<String>(_GetSourceArg0_QNAME, String.class, IsTestable.class, value);
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
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "tag", scope = ServiceOperation.class)
    public JAXBElement<String> createServiceOperationTag(String value) {
        return new JAXBElement<String>(_ServiceOperationTag_QNAME, String.class, ServiceOperation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "method", scope = ServiceOperation.class)
    public JAXBElement<String> createServiceOperationMethod(String value) {
        return new JAXBElement<String>(_ServiceOperationMethod_QNAME, String.class, ServiceOperation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg0", scope = GetRules.class)
    public JAXBElement<String> createGetRulesArg0(String value) {
        return new JAXBElement<String>(_GetSourceArg0_QNAME, String.class, GetRules.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "return", scope = GetRulesResponse.class)
    public JAXBElement<String> createGetRulesResponseReturn(String value) {
        return new JAXBElement<String>(_GetBpmnDiagramResponseReturn_QNAME, String.class, GetRulesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg1", scope = RegisterSource.class)
    public JAXBElement<String> createRegisterSourceArg1(String value) {
        return new JAXBElement<String>(_GetAuthTokenArg1_QNAME, String.class, RegisterSource.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg0", scope = RegisterSource.class)
    public JAXBElement<String> createRegisterSourceArg0(String value) {
        return new JAXBElement<String>(_GetSourceArg0_QNAME, String.class, RegisterSource.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "tag", scope = Tag.class)
    public JAXBElement<String> createTagTag(String value) {
        return new JAXBElement<String>(_ServiceOperationTag_QNAME, String.class, Tag.class, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "id", scope = ServiceDescriptor.class)
    public JAXBElement<String> createServiceDescriptorId(String value) {
        return new JAXBElement<String>(_ServiceDescriptorId_QNAME, String.class, ServiceDescriptor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "name", scope = ServiceDescriptor.class)
    public JAXBElement<String> createServiceDescriptorName(String value) {
        return new JAXBElement<String>(_ServiceDescriptorName_QNAME, String.class, ServiceDescriptor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "description", scope = ServiceDescriptor.class)
    public JAXBElement<String> createServiceDescriptorDescription(String value) {
        return new JAXBElement<String>(_ServiceDescriptorDescription_QNAME, String.class, ServiceDescriptor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "providerName", scope = ServiceDescriptor.class)
    public JAXBElement<String> createServiceDescriptorProviderName(String value) {
        return new JAXBElement<String>(_ServiceDescriptorProviderName_QNAME, String.class, ServiceDescriptor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfServiceOperation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "operations", scope = ServiceDescriptor.class)
    public JAXBElement<ArrayOfServiceOperation> createServiceDescriptorOperations(ArrayOfServiceOperation value) {
        return new JAXBElement<ArrayOfServiceOperation>(_ServiceDescriptorOperations_QNAME, ArrayOfServiceOperation.class, ServiceDescriptor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "binding", scope = ServiceDescriptor.class)
    public JAXBElement<String> createServiceDescriptorBinding(String value) {
        return new JAXBElement<String>(_ServiceDescriptorBinding_QNAME, String.class, ServiceDescriptor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg0", scope = GetTags.class)
    public JAXBElement<String> createGetTagsArg0(String value) {
        return new JAXBElement<String>(_GetSourceArg0_QNAME, String.class, GetTags.class, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "return", scope = GetAuthTokenResponse.class)
    public JAXBElement<String> createGetAuthTokenResponseReturn(String value) {
        return new JAXBElement<String>(_GetBpmnDiagramResponseReturn_QNAME, String.class, GetAuthTokenResponse.class, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg1", scope = UpdateBpmnDiagram.class)
    public JAXBElement<String> createUpdateBpmnDiagramArg1(String value) {
        return new JAXBElement<String>(_GetAuthTokenArg1_QNAME, String.class, UpdateBpmnDiagram.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg0", scope = UpdateBpmnDiagram.class)
    public JAXBElement<String> createUpdateBpmnDiagramArg0(String value) {
        return new JAXBElement<String>(_GetSourceArg0_QNAME, String.class, UpdateBpmnDiagram.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg2", scope = UpdateBpmnDiagram.class)
    public JAXBElement<String> createUpdateBpmnDiagramArg2(String value) {
        return new JAXBElement<String>(_UpdateBpmnDiagramArg2_QNAME, String.class, UpdateBpmnDiagram.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfMarketplaceSecurityProperty }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "securityProperties", scope = MarketplaceSecurityDescriptor.class)
    public JAXBElement<ArrayOfMarketplaceSecurityProperty> createMarketplaceSecurityDescriptorSecurityProperties(ArrayOfMarketplaceSecurityProperty value) {
        return new JAXBElement<ArrayOfMarketplaceSecurityProperty>(_MarketplaceSecurityDescriptorSecurityProperties_QNAME, ArrayOfMarketplaceSecurityProperty.class, MarketplaceSecurityDescriptor.class, value);
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
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg0", scope = DiscoverService.class)
    public JAXBElement<String> createDiscoverServiceArg0(String value) {
        return new JAXBElement<String>(_GetSourceArg0_QNAME, String.class, DiscoverService.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg1", scope = UpdateSecurityDescription.class)
    public JAXBElement<String> createUpdateSecurityDescriptionArg1(String value) {
        return new JAXBElement<String>(_GetAuthTokenArg1_QNAME, String.class, UpdateSecurityDescription.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg0", scope = UpdateSecurityDescription.class)
    public JAXBElement<String> createUpdateSecurityDescriptionArg0(String value) {
        return new JAXBElement<String>(_GetSourceArg0_QNAME, String.class, UpdateSecurityDescription.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "tags", scope = MarketplaceSearchParams.class)
    public JAXBElement<ArrayOfString> createMarketplaceSearchParamsTags(ArrayOfString value) {
        return new JAXBElement<ArrayOfString>(_MarketplaceSearchParamsTags_QNAME, ArrayOfString.class, MarketplaceSearchParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "name", scope = MarketplaceSearchParams.class)
    public JAXBElement<String> createMarketplaceSearchParamsName(String value) {
        return new JAXBElement<String>(_ServiceDescriptorName_QNAME, String.class, MarketplaceSearchParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "description", scope = MarketplaceSearchParams.class)
    public JAXBElement<String> createMarketplaceSearchParamsDescription(String value) {
        return new JAXBElement<String>(_ServiceDescriptorDescription_QNAME, String.class, MarketplaceSearchParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "securityProperty", scope = MarketplaceSearchParams.class)
    public JAXBElement<String> createMarketplaceSearchParamsSecurityProperty(String value) {
        return new JAXBElement<String>(_MarketplaceSearchParamsSecurityProperty_QNAME, String.class, MarketplaceSearchParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "operations", scope = MarketplaceSearchParams.class)
    public JAXBElement<ArrayOfString> createMarketplaceSearchParamsOperations(ArrayOfString value) {
        return new JAXBElement<ArrayOfString>(_ServiceDescriptorOperations_QNAME, ArrayOfString.class, MarketplaceSearchParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "owner", scope = MarketplaceSearchParams.class)
    public JAXBElement<String> createMarketplaceSearchParamsOwner(String value) {
        return new JAXBElement<String>(_MarketplaceSearchParamsOwner_QNAME, String.class, MarketplaceSearchParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu", name = "url", scope = MarketplaceSearchParams.class)
    public JAXBElement<String> createMarketplaceSearchParamsUrl(String value) {
        return new JAXBElement<String>(_MarketplaceSearchParamsUrl_QNAME, String.class, MarketplaceSearchParams.class, value);
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
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg0", scope = GetBpmnDiagram.class)
    public JAXBElement<String> createGetBpmnDiagramArg0(String value) {
        return new JAXBElement<String>(_GetSourceArg0_QNAME, String.class, GetBpmnDiagram.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg1", scope = Provide.class)
    public JAXBElement<String> createProvideArg1(String value) {
        return new JAXBElement<String>(_GetAuthTokenArg1_QNAME, String.class, Provide.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "arg0", scope = Provide.class)
    public JAXBElement<String> createProvideArg0(String value) {
        return new JAXBElement<String>(_GetSourceArg0_QNAME, String.class, Provide.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.aniketos.eu/", name = "return", scope = GetSourceResponse.class)
    public JAXBElement<String> createGetSourceResponseReturn(String value) {
        return new JAXBElement<String>(_GetBpmnDiagramResponseReturn_QNAME, String.class, GetSourceResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://security.java", name = "name", scope = Principal.class)
    public JAXBElement<String> createPrincipalName(String value) {
        return new JAXBElement<String>(_PrincipalName_QNAME, String.class, Principal.class, value);
    }

}
