package eu.aniketos.ncvm.pvm.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.4
 * 2014-02-20T15:45:19.847Z
 * Generated source version: 2.7.4
 * 
 */
@WebServiceClient(name = "PropertyVerificationService", 
                  wsdlLocation = "http://ec2-54-235-118-152.compute-1.amazonaws.com:9090/pvm?wsdl",
                  targetNamespace = "http://propertyverification.verification.components.aniketos.eu/") 
public class PropertyVerificationServiceClient extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://propertyverification.verification.components.aniketos.eu/", "PropertyVerificationService");
    public final static QName PropertyVerificationServicePort = new QName("http://propertyverification.verification.components.aniketos.eu/", "PropertyVerificationServicePort");
    static {
        URL url = null;
        try {
            url = new URL("http://ec2-54-235-118-152.compute-1.amazonaws.com:9090/pvm?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(PropertyVerificationServiceClient.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://ec2-54-235-118-152.compute-1.amazonaws.com:9090/pvm?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public PropertyVerificationServiceClient(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public PropertyVerificationServiceClient(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PropertyVerificationServiceClient() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns PropertyVerificationServicePortType
     */
    @WebEndpoint(name = "PropertyVerificationServicePort")
    public PropertyVerificationServicePortType getPropertyVerificationServicePort() {
        return super.getPort(PropertyVerificationServicePort, PropertyVerificationServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PropertyVerificationServicePortType
     */
    @WebEndpoint(name = "PropertyVerificationServicePort")
    public PropertyVerificationServicePortType getPropertyVerificationServicePort(WebServiceFeature... features) {
        return super.getPort(PropertyVerificationServicePort, PropertyVerificationServicePortType.class, features);
    }

}
