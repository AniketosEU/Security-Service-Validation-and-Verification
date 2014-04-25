
package eu.aniketos.ncvm.spdm.client;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the eu.aniketos.ncvm.spdm.client package. 
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

    private final static QName _GetSecurityPropertyArg0_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "arg0");
    private final static QName _SetTestResultsResponse_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "setTestResultsResponse");
    private final static QName _StartTestResponse_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "startTestResponse");
    private final static QName _LookUpSecurityProperty_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "lookUpSecurityProperty");
    private final static QName _SetTestResults_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "setTestResults");
    private final static QName _GetVerifiedProperties_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "getVerifiedProperties");
    private final static QName _UnregisterServiceResponse_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "unregisterServiceResponse");
    private final static QName _GetSecurityPropertyResponse_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "getSecurityPropertyResponse");
    private final static QName _UpdateSecurityPropertyResponse_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "updateSecurityPropertyResponse");
    private final static QName _FetchRepositoryResponse_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "fetchRepositoryResponse");
    private final static QName _GetTestResults_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "getTestResults");
    private final static QName _UnregisterService_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "unregisterService");
    private final static QName _PrintRepository_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "print_repository");
    private final static QName _GetService_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "getService");
    private final static QName _GetServiceResponse_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "getServiceResponse");
    private final static QName _RegisterService1Response_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "registerService1Response");
    private final static QName _GetVerifiedPropertiesResponse_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "getVerifiedPropertiesResponse");
    private final static QName _GetSecurityProperty1_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "getSecurityProperty1");
    private final static QName _PrintWsEntries_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "print_ws_entries");
    private final static QName _RegisterService_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "registerService");
    private final static QName _GetProperties_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "getProperties");
    private final static QName _LookUpSecurityPropertyResponse_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "lookUpSecurityPropertyResponse");
    private final static QName _UpdateSecurityProperty_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "updateSecurityProperty");
    private final static QName _GetTestResultsResponse_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "getTestResultsResponse");
    private final static QName _PersistCacheResponse_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "persist_cacheResponse");
    private final static QName _CacheSizeResponse_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "cache_sizeResponse");
    private final static QName _PersistCache_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "persist_cache");
    private final static QName _GetSecurityProperty_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "getSecurityProperty");
    private final static QName _RemoveSeucrityProeprtyResponse_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "removeSeucrityProeprtyResponse");
    private final static QName _EmptyCache_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "emptyCache");
    private final static QName _PrintWsEntriesResponse_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "print_ws_entriesResponse");
    private final static QName _GetPropertiesResponse_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "getPropertiesResponse");
    private final static QName _GetSecurityProperty1Response_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "getSecurityProperty1Response");
    private final static QName _PrintSpEntriesResponse_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "print_sp_entriesResponse");
    private final static QName _FetchRepository_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "fetchRepository");
    private final static QName _CacheSize_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "cache_size");
    private final static QName _LookupServiceResponse_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "lookupServiceResponse");
    private final static QName _EmptyCacheResponse_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "emptyCacheResponse");
    private final static QName _PrintRepositoryResponse_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "print_repositoryResponse");
    private final static QName _RemoveSeucrityProeprty_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "removeSeucrityProeprty");
    private final static QName _LookupService_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "lookupService");
    private final static QName _PrintSpEntries_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "print_sp_entries");
    private final static QName _RegisterServiceResponse_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "registerServiceResponse");
    private final static QName _StartTest_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "startTest");
    private final static QName _RegisterService1_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "registerService1");
    private final static QName _CertificatePublicKey_QNAME = new QName("http://cert.security.java", "publicKey");
    private final static QName _CertificateType_QNAME = new QName("http://cert.security.java", "type");
    private final static QName _CertificateEncoded_QNAME = new QName("http://cert.security.java", "encoded");
    private final static QName _KeyFormat_QNAME = new QName("http://security.java", "format");
    private final static QName _KeyAlgorithm_QNAME = new QName("http://security.java", "algorithm");
    private final static QName _KeyEncoded_QNAME = new QName("http://security.java", "encoded");
    private final static QName _X500PrincipalName_QNAME = new QName("http://x500.auth.security.javax", "name");
    private final static QName _X500PrincipalEncoded_QNAME = new QName("http://x500.auth.security.javax", "encoded");
    private final static QName _GetSecurityProperty1Arg1_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "arg1");
    private final static QName _UpdateSecurityPropertyArg2_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "arg2");
    private final static QName _EntryValue_QNAME = new QName("http://util.java", "value");
    private final static QName _EntryKey_QNAME = new QName("http://util.java", "key");
    private final static QName _ISPSRepositoryEntriest_QNAME = new QName("http://api.ds.spdm.aniketos.eu", "entriest");
    private final static QName _ISPSRepositoryServiceEntries_QNAME = new QName("http://api.ds.spdm.aniketos.eu", "serviceEntries");
    private final static QName _ISPSRepositoryPropertyEntries_QNAME = new QName("http://api.ds.spdm.aniketos.eu", "propertyEntries");
    private final static QName _ISecurityDescriptorProperties_QNAME = new QName("http://data.aniketos.eu", "properties");
    private final static QName _ISecurityPropertyState_QNAME = new QName("http://data.aniketos.eu", "state");
    private final static QName _ISecurityPropertyPropertyID_QNAME = new QName("http://data.aniketos.eu", "propertyID");
    private final static QName _ISecurityPropertyPropertyValue_QNAME = new QName("http://data.aniketos.eu", "propertyValue");
    private final static QName _ISecurityPropertyCertificate_QNAME = new QName("http://data.aniketos.eu", "certificate");
    private final static QName _IWebServiceServiceID_QNAME = new QName("http://api.ds.spdm.aniketos.eu", "serviceID");
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
    private final static QName _PrincipalName_QNAME = new QName("http://security.java", "name");
    private final static QName _GetTestResultsResponseReturn_QNAME = new QName("http://api.ds.spdm.aniketos.eu/", "return");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: eu.aniketos.ncvm.spdm.client
     * 
     */
    public ObjectFactory() {
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
     * Create an instance of {@link ArrayOfEntry }
     * 
     */
    public ArrayOfEntry createArrayOfEntry() {
        return new ArrayOfEntry();
    }

    /**
     * Create an instance of {@link Entry }
     * 
     */
    public Entry createEntry() {
        return new Entry();
    }

    /**
     * Create an instance of {@link X500Principal }
     * 
     */
    public X500Principal createX500Principal() {
        return new X500Principal();
    }

    /**
     * Create an instance of {@link CacheSize }
     * 
     */
    public CacheSize createCacheSize() {
        return new CacheSize();
    }

    /**
     * Create an instance of {@link FetchRepository }
     * 
     */
    public FetchRepository createFetchRepository() {
        return new FetchRepository();
    }

    /**
     * Create an instance of {@link PrintSpEntriesResponse }
     * 
     */
    public PrintSpEntriesResponse createPrintSpEntriesResponse() {
        return new PrintSpEntriesResponse();
    }

    /**
     * Create an instance of {@link EmptyCacheResponse }
     * 
     */
    public EmptyCacheResponse createEmptyCacheResponse() {
        return new EmptyCacheResponse();
    }

    /**
     * Create an instance of {@link LookupServiceResponse }
     * 
     */
    public LookupServiceResponse createLookupServiceResponse() {
        return new LookupServiceResponse();
    }

    /**
     * Create an instance of {@link LookupService }
     * 
     */
    public LookupService createLookupService() {
        return new LookupService();
    }

    /**
     * Create an instance of {@link RemoveSeucrityProeprty }
     * 
     */
    public RemoveSeucrityProeprty createRemoveSeucrityProeprty() {
        return new RemoveSeucrityProeprty();
    }

    /**
     * Create an instance of {@link PrintRepositoryResponse }
     * 
     */
    public PrintRepositoryResponse createPrintRepositoryResponse() {
        return new PrintRepositoryResponse();
    }

    /**
     * Create an instance of {@link RegisterService1 }
     * 
     */
    public RegisterService1 createRegisterService1() {
        return new RegisterService1();
    }

    /**
     * Create an instance of {@link StartTest }
     * 
     */
    public StartTest createStartTest() {
        return new StartTest();
    }

    /**
     * Create an instance of {@link RegisterServiceResponse }
     * 
     */
    public RegisterServiceResponse createRegisterServiceResponse() {
        return new RegisterServiceResponse();
    }

    /**
     * Create an instance of {@link PrintSpEntries }
     * 
     */
    public PrintSpEntries createPrintSpEntries() {
        return new PrintSpEntries();
    }

    /**
     * Create an instance of {@link GetSecurityProperty }
     * 
     */
    public GetSecurityProperty createGetSecurityProperty() {
        return new GetSecurityProperty();
    }

    /**
     * Create an instance of {@link PersistCache }
     * 
     */
    public PersistCache createPersistCache() {
        return new PersistCache();
    }

    /**
     * Create an instance of {@link CacheSizeResponse }
     * 
     */
    public CacheSizeResponse createCacheSizeResponse() {
        return new CacheSizeResponse();
    }

    /**
     * Create an instance of {@link PersistCacheResponse }
     * 
     */
    public PersistCacheResponse createPersistCacheResponse() {
        return new PersistCacheResponse();
    }

    /**
     * Create an instance of {@link EmptyCache }
     * 
     */
    public EmptyCache createEmptyCache() {
        return new EmptyCache();
    }

    /**
     * Create an instance of {@link RemoveSeucrityProeprtyResponse }
     * 
     */
    public RemoveSeucrityProeprtyResponse createRemoveSeucrityProeprtyResponse() {
        return new RemoveSeucrityProeprtyResponse();
    }

    /**
     * Create an instance of {@link PrintWsEntriesResponse }
     * 
     */
    public PrintWsEntriesResponse createPrintWsEntriesResponse() {
        return new PrintWsEntriesResponse();
    }

    /**
     * Create an instance of {@link GetSecurityProperty1Response }
     * 
     */
    public GetSecurityProperty1Response createGetSecurityProperty1Response() {
        return new GetSecurityProperty1Response();
    }

    /**
     * Create an instance of {@link GetPropertiesResponse }
     * 
     */
    public GetPropertiesResponse createGetPropertiesResponse() {
        return new GetPropertiesResponse();
    }

    /**
     * Create an instance of {@link RegisterService1Response }
     * 
     */
    public RegisterService1Response createRegisterService1Response() {
        return new RegisterService1Response();
    }

    /**
     * Create an instance of {@link GetServiceResponse }
     * 
     */
    public GetServiceResponse createGetServiceResponse() {
        return new GetServiceResponse();
    }

    /**
     * Create an instance of {@link PrintWsEntries }
     * 
     */
    public PrintWsEntries createPrintWsEntries() {
        return new PrintWsEntries();
    }

    /**
     * Create an instance of {@link RegisterService }
     * 
     */
    public RegisterService createRegisterService() {
        return new RegisterService();
    }

    /**
     * Create an instance of {@link GetSecurityProperty1 }
     * 
     */
    public GetSecurityProperty1 createGetSecurityProperty1() {
        return new GetSecurityProperty1();
    }

    /**
     * Create an instance of {@link GetVerifiedPropertiesResponse }
     * 
     */
    public GetVerifiedPropertiesResponse createGetVerifiedPropertiesResponse() {
        return new GetVerifiedPropertiesResponse();
    }

    /**
     * Create an instance of {@link LookUpSecurityPropertyResponse }
     * 
     */
    public LookUpSecurityPropertyResponse createLookUpSecurityPropertyResponse() {
        return new LookUpSecurityPropertyResponse();
    }

    /**
     * Create an instance of {@link GetProperties }
     * 
     */
    public GetProperties createGetProperties() {
        return new GetProperties();
    }

    /**
     * Create an instance of {@link GetTestResultsResponse }
     * 
     */
    public GetTestResultsResponse createGetTestResultsResponse() {
        return new GetTestResultsResponse();
    }

    /**
     * Create an instance of {@link UpdateSecurityProperty }
     * 
     */
    public UpdateSecurityProperty createUpdateSecurityProperty() {
        return new UpdateSecurityProperty();
    }

    /**
     * Create an instance of {@link LookUpSecurityProperty }
     * 
     */
    public LookUpSecurityProperty createLookUpSecurityProperty() {
        return new LookUpSecurityProperty();
    }

    /**
     * Create an instance of {@link StartTestResponse }
     * 
     */
    public StartTestResponse createStartTestResponse() {
        return new StartTestResponse();
    }

    /**
     * Create an instance of {@link SetTestResultsResponse }
     * 
     */
    public SetTestResultsResponse createSetTestResultsResponse() {
        return new SetTestResultsResponse();
    }

    /**
     * Create an instance of {@link UnregisterServiceResponse }
     * 
     */
    public UnregisterServiceResponse createUnregisterServiceResponse() {
        return new UnregisterServiceResponse();
    }

    /**
     * Create an instance of {@link GetVerifiedProperties }
     * 
     */
    public GetVerifiedProperties createGetVerifiedProperties() {
        return new GetVerifiedProperties();
    }

    /**
     * Create an instance of {@link SetTestResults }
     * 
     */
    public SetTestResults createSetTestResults() {
        return new SetTestResults();
    }

    /**
     * Create an instance of {@link GetTestResults }
     * 
     */
    public GetTestResults createGetTestResults() {
        return new GetTestResults();
    }

    /**
     * Create an instance of {@link FetchRepositoryResponse }
     * 
     */
    public FetchRepositoryResponse createFetchRepositoryResponse() {
        return new FetchRepositoryResponse();
    }

    /**
     * Create an instance of {@link UpdateSecurityPropertyResponse }
     * 
     */
    public UpdateSecurityPropertyResponse createUpdateSecurityPropertyResponse() {
        return new UpdateSecurityPropertyResponse();
    }

    /**
     * Create an instance of {@link GetSecurityPropertyResponse }
     * 
     */
    public GetSecurityPropertyResponse createGetSecurityPropertyResponse() {
        return new GetSecurityPropertyResponse();
    }

    /**
     * Create an instance of {@link GetService }
     * 
     */
    public GetService createGetService() {
        return new GetService();
    }

    /**
     * Create an instance of {@link PrintRepository }
     * 
     */
    public PrintRepository createPrintRepository() {
        return new PrintRepository();
    }

    /**
     * Create an instance of {@link UnregisterService }
     * 
     */
    public UnregisterService createUnregisterService() {
        return new UnregisterService();
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
     * Create an instance of {@link IWebService }
     * 
     */
    public IWebService createIWebService() {
        return new IWebService();
    }

    /**
     * Create an instance of {@link ISPSRepository }
     * 
     */
    public ISPSRepository createISPSRepository() {
        return new ISPSRepository();
    }

    /**
     * Create an instance of {@link ArrayOfIWebService }
     * 
     */
    public ArrayOfIWebService createArrayOfIWebService() {
        return new ArrayOfIWebService();
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
     * Create an instance of {@link ArrayOfISecurityProperty }
     * 
     */
    public ArrayOfISecurityProperty createArrayOfISecurityProperty() {
        return new ArrayOfISecurityProperty();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "arg0", scope = GetSecurityProperty.class)
    public JAXBElement<String> createGetSecurityPropertyArg0(String value) {
        return new JAXBElement<String>(_GetSecurityPropertyArg0_QNAME, String.class, GetSecurityProperty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetTestResultsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "setTestResultsResponse")
    public JAXBElement<SetTestResultsResponse> createSetTestResultsResponse(SetTestResultsResponse value) {
        return new JAXBElement<SetTestResultsResponse>(_SetTestResultsResponse_QNAME, SetTestResultsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartTestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "startTestResponse")
    public JAXBElement<StartTestResponse> createStartTestResponse(StartTestResponse value) {
        return new JAXBElement<StartTestResponse>(_StartTestResponse_QNAME, StartTestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LookUpSecurityProperty }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "lookUpSecurityProperty")
    public JAXBElement<LookUpSecurityProperty> createLookUpSecurityProperty(LookUpSecurityProperty value) {
        return new JAXBElement<LookUpSecurityProperty>(_LookUpSecurityProperty_QNAME, LookUpSecurityProperty.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetTestResults }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "setTestResults")
    public JAXBElement<SetTestResults> createSetTestResults(SetTestResults value) {
        return new JAXBElement<SetTestResults>(_SetTestResults_QNAME, SetTestResults.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetVerifiedProperties }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "getVerifiedProperties")
    public JAXBElement<GetVerifiedProperties> createGetVerifiedProperties(GetVerifiedProperties value) {
        return new JAXBElement<GetVerifiedProperties>(_GetVerifiedProperties_QNAME, GetVerifiedProperties.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnregisterServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "unregisterServiceResponse")
    public JAXBElement<UnregisterServiceResponse> createUnregisterServiceResponse(UnregisterServiceResponse value) {
        return new JAXBElement<UnregisterServiceResponse>(_UnregisterServiceResponse_QNAME, UnregisterServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSecurityPropertyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "getSecurityPropertyResponse")
    public JAXBElement<GetSecurityPropertyResponse> createGetSecurityPropertyResponse(GetSecurityPropertyResponse value) {
        return new JAXBElement<GetSecurityPropertyResponse>(_GetSecurityPropertyResponse_QNAME, GetSecurityPropertyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateSecurityPropertyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "updateSecurityPropertyResponse")
    public JAXBElement<UpdateSecurityPropertyResponse> createUpdateSecurityPropertyResponse(UpdateSecurityPropertyResponse value) {
        return new JAXBElement<UpdateSecurityPropertyResponse>(_UpdateSecurityPropertyResponse_QNAME, UpdateSecurityPropertyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FetchRepositoryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "fetchRepositoryResponse")
    public JAXBElement<FetchRepositoryResponse> createFetchRepositoryResponse(FetchRepositoryResponse value) {
        return new JAXBElement<FetchRepositoryResponse>(_FetchRepositoryResponse_QNAME, FetchRepositoryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTestResults }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "getTestResults")
    public JAXBElement<GetTestResults> createGetTestResults(GetTestResults value) {
        return new JAXBElement<GetTestResults>(_GetTestResults_QNAME, GetTestResults.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnregisterService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "unregisterService")
    public JAXBElement<UnregisterService> createUnregisterService(UnregisterService value) {
        return new JAXBElement<UnregisterService>(_UnregisterService_QNAME, UnregisterService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintRepository }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "print_repository")
    public JAXBElement<PrintRepository> createPrintRepository(PrintRepository value) {
        return new JAXBElement<PrintRepository>(_PrintRepository_QNAME, PrintRepository.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "getService")
    public JAXBElement<GetService> createGetService(GetService value) {
        return new JAXBElement<GetService>(_GetService_QNAME, GetService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "getServiceResponse")
    public JAXBElement<GetServiceResponse> createGetServiceResponse(GetServiceResponse value) {
        return new JAXBElement<GetServiceResponse>(_GetServiceResponse_QNAME, GetServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterService1Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "registerService1Response")
    public JAXBElement<RegisterService1Response> createRegisterService1Response(RegisterService1Response value) {
        return new JAXBElement<RegisterService1Response>(_RegisterService1Response_QNAME, RegisterService1Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetVerifiedPropertiesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "getVerifiedPropertiesResponse")
    public JAXBElement<GetVerifiedPropertiesResponse> createGetVerifiedPropertiesResponse(GetVerifiedPropertiesResponse value) {
        return new JAXBElement<GetVerifiedPropertiesResponse>(_GetVerifiedPropertiesResponse_QNAME, GetVerifiedPropertiesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSecurityProperty1 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "getSecurityProperty1")
    public JAXBElement<GetSecurityProperty1> createGetSecurityProperty1(GetSecurityProperty1 value) {
        return new JAXBElement<GetSecurityProperty1>(_GetSecurityProperty1_QNAME, GetSecurityProperty1 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintWsEntries }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "print_ws_entries")
    public JAXBElement<PrintWsEntries> createPrintWsEntries(PrintWsEntries value) {
        return new JAXBElement<PrintWsEntries>(_PrintWsEntries_QNAME, PrintWsEntries.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "registerService")
    public JAXBElement<RegisterService> createRegisterService(RegisterService value) {
        return new JAXBElement<RegisterService>(_RegisterService_QNAME, RegisterService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProperties }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "getProperties")
    public JAXBElement<GetProperties> createGetProperties(GetProperties value) {
        return new JAXBElement<GetProperties>(_GetProperties_QNAME, GetProperties.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LookUpSecurityPropertyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "lookUpSecurityPropertyResponse")
    public JAXBElement<LookUpSecurityPropertyResponse> createLookUpSecurityPropertyResponse(LookUpSecurityPropertyResponse value) {
        return new JAXBElement<LookUpSecurityPropertyResponse>(_LookUpSecurityPropertyResponse_QNAME, LookUpSecurityPropertyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateSecurityProperty }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "updateSecurityProperty")
    public JAXBElement<UpdateSecurityProperty> createUpdateSecurityProperty(UpdateSecurityProperty value) {
        return new JAXBElement<UpdateSecurityProperty>(_UpdateSecurityProperty_QNAME, UpdateSecurityProperty.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTestResultsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "getTestResultsResponse")
    public JAXBElement<GetTestResultsResponse> createGetTestResultsResponse(GetTestResultsResponse value) {
        return new JAXBElement<GetTestResultsResponse>(_GetTestResultsResponse_QNAME, GetTestResultsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersistCacheResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "persist_cacheResponse")
    public JAXBElement<PersistCacheResponse> createPersistCacheResponse(PersistCacheResponse value) {
        return new JAXBElement<PersistCacheResponse>(_PersistCacheResponse_QNAME, PersistCacheResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CacheSizeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "cache_sizeResponse")
    public JAXBElement<CacheSizeResponse> createCacheSizeResponse(CacheSizeResponse value) {
        return new JAXBElement<CacheSizeResponse>(_CacheSizeResponse_QNAME, CacheSizeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersistCache }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "persist_cache")
    public JAXBElement<PersistCache> createPersistCache(PersistCache value) {
        return new JAXBElement<PersistCache>(_PersistCache_QNAME, PersistCache.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSecurityProperty }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "getSecurityProperty")
    public JAXBElement<GetSecurityProperty> createGetSecurityProperty(GetSecurityProperty value) {
        return new JAXBElement<GetSecurityProperty>(_GetSecurityProperty_QNAME, GetSecurityProperty.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveSeucrityProeprtyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "removeSeucrityProeprtyResponse")
    public JAXBElement<RemoveSeucrityProeprtyResponse> createRemoveSeucrityProeprtyResponse(RemoveSeucrityProeprtyResponse value) {
        return new JAXBElement<RemoveSeucrityProeprtyResponse>(_RemoveSeucrityProeprtyResponse_QNAME, RemoveSeucrityProeprtyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyCache }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "emptyCache")
    public JAXBElement<EmptyCache> createEmptyCache(EmptyCache value) {
        return new JAXBElement<EmptyCache>(_EmptyCache_QNAME, EmptyCache.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintWsEntriesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "print_ws_entriesResponse")
    public JAXBElement<PrintWsEntriesResponse> createPrintWsEntriesResponse(PrintWsEntriesResponse value) {
        return new JAXBElement<PrintWsEntriesResponse>(_PrintWsEntriesResponse_QNAME, PrintWsEntriesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPropertiesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "getPropertiesResponse")
    public JAXBElement<GetPropertiesResponse> createGetPropertiesResponse(GetPropertiesResponse value) {
        return new JAXBElement<GetPropertiesResponse>(_GetPropertiesResponse_QNAME, GetPropertiesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSecurityProperty1Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "getSecurityProperty1Response")
    public JAXBElement<GetSecurityProperty1Response> createGetSecurityProperty1Response(GetSecurityProperty1Response value) {
        return new JAXBElement<GetSecurityProperty1Response>(_GetSecurityProperty1Response_QNAME, GetSecurityProperty1Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintSpEntriesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "print_sp_entriesResponse")
    public JAXBElement<PrintSpEntriesResponse> createPrintSpEntriesResponse(PrintSpEntriesResponse value) {
        return new JAXBElement<PrintSpEntriesResponse>(_PrintSpEntriesResponse_QNAME, PrintSpEntriesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FetchRepository }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "fetchRepository")
    public JAXBElement<FetchRepository> createFetchRepository(FetchRepository value) {
        return new JAXBElement<FetchRepository>(_FetchRepository_QNAME, FetchRepository.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CacheSize }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "cache_size")
    public JAXBElement<CacheSize> createCacheSize(CacheSize value) {
        return new JAXBElement<CacheSize>(_CacheSize_QNAME, CacheSize.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LookupServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "lookupServiceResponse")
    public JAXBElement<LookupServiceResponse> createLookupServiceResponse(LookupServiceResponse value) {
        return new JAXBElement<LookupServiceResponse>(_LookupServiceResponse_QNAME, LookupServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyCacheResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "emptyCacheResponse")
    public JAXBElement<EmptyCacheResponse> createEmptyCacheResponse(EmptyCacheResponse value) {
        return new JAXBElement<EmptyCacheResponse>(_EmptyCacheResponse_QNAME, EmptyCacheResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintRepositoryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "print_repositoryResponse")
    public JAXBElement<PrintRepositoryResponse> createPrintRepositoryResponse(PrintRepositoryResponse value) {
        return new JAXBElement<PrintRepositoryResponse>(_PrintRepositoryResponse_QNAME, PrintRepositoryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveSeucrityProeprty }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "removeSeucrityProeprty")
    public JAXBElement<RemoveSeucrityProeprty> createRemoveSeucrityProeprty(RemoveSeucrityProeprty value) {
        return new JAXBElement<RemoveSeucrityProeprty>(_RemoveSeucrityProeprty_QNAME, RemoveSeucrityProeprty.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LookupService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "lookupService")
    public JAXBElement<LookupService> createLookupService(LookupService value) {
        return new JAXBElement<LookupService>(_LookupService_QNAME, LookupService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintSpEntries }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "print_sp_entries")
    public JAXBElement<PrintSpEntries> createPrintSpEntries(PrintSpEntries value) {
        return new JAXBElement<PrintSpEntries>(_PrintSpEntries_QNAME, PrintSpEntries.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "registerServiceResponse")
    public JAXBElement<RegisterServiceResponse> createRegisterServiceResponse(RegisterServiceResponse value) {
        return new JAXBElement<RegisterServiceResponse>(_RegisterServiceResponse_QNAME, RegisterServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartTest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "startTest")
    public JAXBElement<StartTest> createStartTest(StartTest value) {
        return new JAXBElement<StartTest>(_StartTest_QNAME, StartTest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterService1 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "registerService1")
    public JAXBElement<RegisterService1> createRegisterService1(RegisterService1 value) {
        return new JAXBElement<RegisterService1>(_RegisterService1_QNAME, RegisterService1 .class, null, value);
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
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "arg0", scope = GetSecurityProperty1 .class)
    public JAXBElement<String> createGetSecurityProperty1Arg0(String value) {
        return new JAXBElement<String>(_GetSecurityPropertyArg0_QNAME, String.class, GetSecurityProperty1 .class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "arg1", scope = GetSecurityProperty1 .class)
    public JAXBElement<String> createGetSecurityProperty1Arg1(String value) {
        return new JAXBElement<String>(_GetSecurityProperty1Arg1_QNAME, String.class, GetSecurityProperty1 .class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SPState }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "arg1", scope = GetProperties.class)
    public JAXBElement<SPState> createGetPropertiesArg1(SPState value) {
        return new JAXBElement<SPState>(_GetSecurityProperty1Arg1_QNAME, SPState.class, GetProperties.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "arg2", scope = UpdateSecurityProperty.class)
    public JAXBElement<String> createUpdateSecurityPropertyArg2(String value) {
        return new JAXBElement<String>(_UpdateSecurityPropertyArg2_QNAME, String.class, UpdateSecurityProperty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "arg0", scope = UpdateSecurityProperty.class)
    public JAXBElement<String> createUpdateSecurityPropertyArg0(String value) {
        return new JAXBElement<String>(_GetSecurityPropertyArg0_QNAME, String.class, UpdateSecurityProperty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "arg1", scope = UpdateSecurityProperty.class)
    public JAXBElement<String> createUpdateSecurityPropertyArg1(String value) {
        return new JAXBElement<String>(_GetSecurityProperty1Arg1_QNAME, String.class, UpdateSecurityProperty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://util.java", name = "value", scope = Entry.class)
    public JAXBElement<Object> createEntryValue(Object value) {
        return new JAXBElement<Object>(_EntryValue_QNAME, Object.class, Entry.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://util.java", name = "key", scope = Entry.class)
    public JAXBElement<Object> createEntryKey(Object value) {
        return new JAXBElement<Object>(_EntryKey_QNAME, Object.class, Entry.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfEntry }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu", name = "entriest", scope = ISPSRepository.class)
    public JAXBElement<ArrayOfEntry> createISPSRepositoryEntriest(ArrayOfEntry value) {
        return new JAXBElement<ArrayOfEntry>(_ISPSRepositoryEntriest_QNAME, ArrayOfEntry.class, ISPSRepository.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfEntry }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu", name = "serviceEntries", scope = ISPSRepository.class)
    public JAXBElement<ArrayOfEntry> createISPSRepositoryServiceEntries(ArrayOfEntry value) {
        return new JAXBElement<ArrayOfEntry>(_ISPSRepositoryServiceEntries_QNAME, ArrayOfEntry.class, ISPSRepository.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfEntry }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu", name = "propertyEntries", scope = ISPSRepository.class)
    public JAXBElement<ArrayOfEntry> createISPSRepositoryPropertyEntries(ArrayOfEntry value) {
        return new JAXBElement<ArrayOfEntry>(_ISPSRepositoryPropertyEntries_QNAME, ArrayOfEntry.class, ISPSRepository.class, value);
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
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "arg0", scope = GetService.class)
    public JAXBElement<String> createGetServiceArg0(String value) {
        return new JAXBElement<String>(_GetSecurityPropertyArg0_QNAME, String.class, GetService.class, value);
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
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu", name = "serviceID", scope = IWebService.class)
    public JAXBElement<String> createIWebServiceServiceID(String value) {
        return new JAXBElement<String>(_IWebServiceServiceID_QNAME, String.class, IWebService.class, value);
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
    @XmlElementDecl(namespace = "http://security.java", name = "name", scope = Principal.class)
    public JAXBElement<String> createPrincipalName(String value) {
        return new JAXBElement<String>(_PrincipalName_QNAME, String.class, Principal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "return", scope = GetTestResultsResponse.class)
    public JAXBElement<String> createGetTestResultsResponseReturn(String value) {
        return new JAXBElement<String>(_GetTestResultsResponseReturn_QNAME, String.class, GetTestResultsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.ds.spdm.aniketos.eu/", name = "arg0", scope = SetTestResults.class)
    public JAXBElement<String> createSetTestResultsArg0(String value) {
        return new JAXBElement<String>(_GetSecurityPropertyArg0_QNAME, String.class, SetTestResults.class, value);
    }

}
