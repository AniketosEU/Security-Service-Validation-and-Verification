
package eu.aniketos.ncvm.client;

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
 * 2014-02-27T00:54:54.837Z
 * Generated source version: 2.7.4
 * 
 */
public final class INestedCompositionVerificationPortType_INestedCompositionVerificationPort_Client {

    private static final QName SERVICE_NAME = new QName("http://ncvm.aniketos.eu/", "INestedCompositionVerification");

    private INestedCompositionVerificationPortType_INestedCompositionVerificationPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = INestedCompositionVerificationClient.WSDL_LOCATION;
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
      
        INestedCompositionVerificationClient ss = new INestedCompositionVerificationClient(wsdlURL, SERVICE_NAME);
        INestedCompositionVerificationPortType port = ss.getINestedCompositionVerificationPort();  
        
        {
        System.out.println("Invoking performTests...");
        port.performTests();


        }
        {
        System.out.println("Invoking verifyProperty...");
        eu.aniketos.ncvm.client.ICompositionPlan _verifyProperty_arg0 = null;
        eu.aniketos.ncvm.client.IConsumerPolicy _verifyProperty_arg1 = null;
        eu.aniketos.ncvm.client.IVerificationResult _verifyProperty__return = port.verifyProperty(_verifyProperty_arg0, _verifyProperty_arg1);
        System.out.println("verifyProperty.result=" + _verifyProperty__return);


        }
        {
        System.out.println("Invoking configureNCVMFeedback...");
        java.lang.String _configureNCVMFeedback_arg0 = "";
        boolean _configureNCVMFeedback_arg1 = false;
        port.configureNCVMFeedback(_configureNCVMFeedback_arg0, _configureNCVMFeedback_arg1);


        }
        {
        System.out.println("Invoking verifyPropertyDeployed...");
        java.lang.String _verifyPropertyDeployed_arg0 = "";
        eu.aniketos.ncvm.client.IConsumerPolicy _verifyPropertyDeployed_arg1 = null;
        eu.aniketos.ncvm.client.IVerificationResult _verifyPropertyDeployed__return = port.verifyPropertyDeployed(_verifyPropertyDeployed_arg0, _verifyPropertyDeployed_arg1);
        System.out.println("verifyPropertyDeployed.result=" + _verifyPropertyDeployed__return);


        }
        {
        System.out.println("Invoking configureCSVM...");
        java.lang.String _configureCSVM_arg0 = "";
        boolean _configureCSVM_arg1 = false;
        port.configureCSVM(_configureCSVM_arg0, _configureCSVM_arg1);


        }
        {
        System.out.println("Invoking configureMarketplace...");
        java.lang.String _configureMarketplace_arg0 = "";
        boolean _configureMarketplace_arg1 = false;
        port.configureMarketplace(_configureMarketplace_arg0, _configureMarketplace_arg1);


        }
        {
        System.out.println("Invoking configurePVM...");
        java.lang.String _configurePVM_arg0 = "";
        boolean _configurePVM_arg1 = false;
        port.configurePVM(_configurePVM_arg0, _configurePVM_arg1);


        }
        {
        System.out.println("Invoking configureSPDM...");
        java.lang.String _configureSPDM_arg0 = "";
        boolean _configureSPDM_arg1 = false;
        port.configureSPDM(_configureSPDM_arg0, _configureSPDM_arg1);


        }

        System.exit(0);
    }

}
