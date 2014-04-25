package eu.aniketos.ncvm.pvm.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.4
 * 2014-02-20T15:45:19.833Z
 * Generated source version: 2.7.4
 * 
 */
@WebService(targetNamespace = "http://propertyverification.verification.components.aniketos.eu/", name = "PropertyVerificationServicePortType")
@XmlSeeAlso({ObjectFactory.class})
public interface PropertyVerificationServicePortType {

    @WebResult(name = "return", targetNamespace = "http://propertyverification.verification.components.aniketos.eu/")
    @RequestWrapper(localName = "verifyTechnicalTrustProperties", targetNamespace = "http://propertyverification.verification.components.aniketos.eu/", className = "eu.aniketos.ncvm.pvm.client.VerifyTechnicalTrustProperties")
    @WebMethod
    @ResponseWrapper(localName = "verifyTechnicalTrustPropertiesResponse", targetNamespace = "http://propertyverification.verification.components.aniketos.eu/", className = "eu.aniketos.ncvm.pvm.client.VerifyTechnicalTrustPropertiesResponse")
    public eu.aniketos.ncvm.pvm.client.PropertyVerificationResult verifyTechnicalTrustProperties(
        @WebParam(name = "arg0", targetNamespace = "http://propertyverification.verification.components.aniketos.eu/")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "http://propertyverification.verification.components.aniketos.eu/")
        java.lang.String arg1
    );

    @WebResult(name = "return", targetNamespace = "http://propertyverification.verification.components.aniketos.eu/")
    @RequestWrapper(localName = "verifyWSDL", targetNamespace = "http://propertyverification.verification.components.aniketos.eu/", className = "eu.aniketos.ncvm.pvm.client.VerifyWSDL")
    @WebMethod
    @ResponseWrapper(localName = "verifyWSDLResponse", targetNamespace = "http://propertyverification.verification.components.aniketos.eu/", className = "eu.aniketos.ncvm.pvm.client.VerifyWSDLResponse")
    public eu.aniketos.ncvm.pvm.client.PropertyVerificationResult verifyWSDL(
        @WebParam(name = "arg0", targetNamespace = "http://propertyverification.verification.components.aniketos.eu/")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "http://propertyverification.verification.components.aniketos.eu/")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "http://propertyverification.verification.components.aniketos.eu/")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "http://propertyverification.verification.components.aniketos.eu/")
        int arg3,
        @WebParam(name = "arg4", targetNamespace = "http://propertyverification.verification.components.aniketos.eu/")
        java.lang.String arg4,
        @WebParam(name = "arg5", targetNamespace = "http://propertyverification.verification.components.aniketos.eu/")
        java.lang.String arg5,
        @WebParam(name = "arg6", targetNamespace = "http://propertyverification.verification.components.aniketos.eu/")
        java.lang.String arg6,
        @WebParam(name = "arg7", targetNamespace = "http://propertyverification.verification.components.aniketos.eu/")
        int arg7,
        @WebParam(name = "arg8", targetNamespace = "http://propertyverification.verification.components.aniketos.eu/")
        java.lang.String arg8
    );
}
