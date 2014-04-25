
package eu.aniketos.ncvm.userinterface.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the eu.aniketos.ncvm.userinterface.client package. 
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

    private final static QName _LogLineResponse_QNAME = new QName("http://userinterface.ncvm.aniketos.eu/", "logLineResponse");
    private final static QName _LogLine_QNAME = new QName("http://userinterface.ncvm.aniketos.eu/", "logLine");
    private final static QName _LogLineArg0_QNAME = new QName("http://userinterface.ncvm.aniketos.eu/", "arg0");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: eu.aniketos.ncvm.userinterface.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LogLineResponse }
     * 
     */
    public LogLineResponse createLogLineResponse() {
        return new LogLineResponse();
    }

    /**
     * Create an instance of {@link LogLine }
     * 
     */
    public LogLine createLogLine() {
        return new LogLine();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogLineResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://userinterface.ncvm.aniketos.eu/", name = "logLineResponse")
    public JAXBElement<LogLineResponse> createLogLineResponse(LogLineResponse value) {
        return new JAXBElement<LogLineResponse>(_LogLineResponse_QNAME, LogLineResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogLine }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://userinterface.ncvm.aniketos.eu/", name = "logLine")
    public JAXBElement<LogLine> createLogLine(LogLine value) {
        return new JAXBElement<LogLine>(_LogLine_QNAME, LogLine.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://userinterface.ncvm.aniketos.eu/", name = "arg0", scope = LogLine.class)
    public JAXBElement<String> createLogLineArg0(String value) {
        return new JAXBElement<String>(_LogLineArg0_QNAME, String.class, LogLine.class, value);
    }

}
