
package eu.aniketos.ncvm.csvm.client;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.4
 * 2013-05-30T21:12:32.508+01:00
 * Generated source version: 2.7.4
 * 
 */
public final class CompositionSecurityValidationServicePortType_CompositionSecurityValidationServicePort_Client {

    private static final QName SERVICE_NAME = new QName("http://compositionsecurityvalidation.verification.components.aniketos.eu/", "CompositionSecurityValidationService");

    private CompositionSecurityValidationServicePortType_CompositionSecurityValidationServicePort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = CompositionSecurityValidationServiceClient.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        CompositionSecurityValidationServiceClient ss = new CompositionSecurityValidationServiceClient(wsdlURL, SERVICE_NAME);
        CompositionSecurityValidationServicePortType port = ss.getCompositionSecurityValidationServicePort();  
        
        {
        System.out.println("Invoking verifyCompositionCompliance...");
        eu.aniketos.ncvm.csvm.client.ICompositionPlan _verifyCompositionCompliance_arg0 = null;
        eu.aniketos.ncvm.csvm.client.CompositionSecurityValidationResult _verifyCompositionCompliance__return = port.verifyCompositionCompliance(_verifyCompositionCompliance_arg0);
        System.out.println("verifyCompositionCompliance.result=" + _verifyCompositionCompliance__return);


        }

        System.exit(0);
    }

}
