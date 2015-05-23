/*
 * (C) Copyright 2010-2015 SAP SE.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 */

package eu.aniketos.pvm.checks.wsdl;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import eu.aniketos.pvm.commons.*;


public class WsdlCheck {


    public static WSSecSpec readSpec(String atfile) {
        WSSecSpec spec = null;
        CryptoSpec input = null;
        CryptoSpec output =null;
        String algorithmSuite="";
        String encryptionSchema="";
        String algorithm="";
        int keyLength=0;

        try {
            Document atDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(atfile));
            atDoc.normalize();

            NodeList suites = atDoc.getElementsByTagName("wssAlgorithmSuite");


            for (int i=0; i < suites.getLength(); i++)
            {
                NodeList reqs = suites.item(i).getChildNodes();
                for (int j=0; j < reqs.getLength(); j++)
                {
                    if(reqs.item(j).getNodeName().equals("encryptionScheme")) {
                        encryptionSchema=reqs.item(j).getTextContent().trim();
                    } else if(reqs.item(j).getNodeName().equals("algorithm")) {
                        algorithm=reqs.item(j).getTextContent().trim();
                    } else if(reqs.item(j).getNodeName().equals("key")) {
                        keyLength=(new Integer(reqs.item(j).getTextContent()));
                    } else if(reqs.item(j).getNodeName().equals("#text")) {
                        String tmp = reqs.item(j).getNodeValue().trim();
                        if (!tmp.equals("")) {
                            algorithmSuite=reqs.item(j).getNodeValue().trim();
                        }
                    }
                }
                if((suites.item(i).getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("type")).getTextContent().equals("input"))
                {   // input
                    input=null;
                    input=new CryptoSpec(algorithmSuite, encryptionSchema, algorithm, keyLength);
                }
                if(suites.item(i).getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("type").getTextContent().equals("output"))
                {   // output
                    output=null;
                    output=new CryptoSpec(algorithmSuite, encryptionSchema, algorithm, keyLength);
                }
            }

            if (input != null || output != null)
            {
                spec = new WSSecSpec(input,output);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }

        return spec;
    }


    private static PVMCoreResult checkWsdlSpec(WSSecSpec spec, WSSecSpec wsdl) {
        if (    spec == null
                || (spec.getInput()==null && spec.getOutput()==null)) {
            return new PVMCoreResult(0,"no requirements given, thus satisfied");
        } else if (wsdl == null) {
            return new PVMCoreResult(-1,"no WSDL specification found");
        }
        if(wsdl.isEqualOrStrongerAs(spec)) {
            if(spec.isEqualOrStrongerAs(wsdl)) {
                return new PVMCoreResult(0,"WSDL is equal to specification");
            } else {
                return new PVMCoreResult(0,"WSDL is stronger as specification");
            }
        } else {
            return new PVMCoreResult(-2,"WSDL specification to weak");
        }

    }

    private static WSSecSpec readWSDL(String wsdlfile) {
        WSSecSpec spec = null;
        CryptoSpec input = null;
        CryptoSpec output =null;

        String InputURI=null;
        String OutputURI=null;
        try {
            Document wsdlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(wsdlfile));
            wsdlDoc.normalize();
            NodeList wsPoliciesRefs = wsdlDoc.getElementsByTagName("wsp:PolicyReference");
            for (int i=0; i < wsPoliciesRefs.getLength(); i++)
            {
                if(wsPoliciesRefs.item(i).getParentNode().getNodeName().equals("wsdl:operation")) {
                    // only one policy for both input and output
                    InputURI  = wsPoliciesRefs.item(i).getAttributes().getNamedItem("URI").getNodeValue();
                    OutputURI = wsPoliciesRefs.item(i).getAttributes().getNamedItem("URI").getNodeValue();
                } else if (wsPoliciesRefs.item(i).getParentNode().getNodeName().equals("wsdl:input")) {
                    InputURI  = wsPoliciesRefs.item(i).getAttributes().getNamedItem("URI").getNodeValue();
                } else if (wsPoliciesRefs.item(i).getParentNode().getNodeName().equals("wsdl:output")) {
                    OutputURI  = wsPoliciesRefs.item(i).getAttributes().getNamedItem("URI").getNodeValue();
                }
            }

            NodeList wsPolicies = wsdlDoc.getElementsByTagName("wsp:Policy");
            for (int i=0; i < wsPolicies.getLength(); i++)
            {
                if(wsPolicies.item(i).getAttributes().getNamedItem("wsu:Id")!=null) {
                    if(InputURI != null && InputURI.equals("#"+wsPolicies.item(i).getAttributes().getNamedItem("wsu:Id").getNodeValue())) {
                        input=policyNode2CryptoSpec(wsPolicies.item(i));
                    }
                    if(OutputURI != null && OutputURI.equals("#"+wsPolicies.item(i).getAttributes().getNamedItem("wsu:Id").getNodeValue())) {
                        output=policyNode2CryptoSpec(wsPolicies.item(i));
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }

        if (null != input  || null != output)
        {
            spec = new WSSecSpec(input,output);
        }

        return spec ;
    }




    private static CryptoSpec policyNode2CryptoSpec(Node policyNode) {
        String algorithmSuite="";
        String encryptionSchema="";
        String algorithm="";
        int keyLength=-128;


        Node binding = policyNode.getChildNodes().item(1)
                       .getChildNodes().item(1)
                       .getChildNodes().item(1)
                       .getChildNodes().item(1);

        // Schema
        if(binding.getNodeName().toLowerCase().equals("sp:symmetricbinding")) {
            encryptionSchema="symmetric";
        } else if(binding.getNodeName().toLowerCase().equals("sp:asymmetricbinding")) {
            encryptionSchema="symmetric";
        }

        Node innerPolicy = binding.getChildNodes().item(1);
        //
        for (int i=0; i < innerPolicy.getChildNodes().getLength(); i++)
        {
            if(innerPolicy.getChildNodes().item(i).getNodeName().toLowerCase().equals("sp:algorithmsuite"))
            {
                algorithmSuite=innerPolicy.getChildNodes().item(i).getChildNodes().item(1).getChildNodes().item(1).getNodeName();
                algorithmSuite=algorithmSuite.replaceFirst("sp:","").toLowerCase();
            }
        }

        // The rest is hardcoded based on the Suite:
        // https://access.redhat.com/site/documentation/en-US/JBoss_Fuse/6.0/html/Web_Services_Security_Guide/files/MsgProtect-SOAP-SpecifyAlgorithmSuite.html#
        // Basic256Sha256Rsa15	Sha256	Aes256	KwAes256	KwRsa15	PSha1L256	PSha1L192
        // Basic192Sha256Rsa15	Sha256	Aes192	KwAes192	KwRsa15	PSha1L192	PSha1L192
        // Basic128Sha256Rsa15	Sha256	Aes128	KwAes128	KwRsa15	PSha1L128	PSha1L128
        if(algorithmSuite.equals("basic256sha256rsa15"))
        {
            if(encryptionSchema.equals("symmetric")) {
                algorithm="aes";
                keyLength=256;

            } else {
                algorithm="rsa";
                keyLength=15;
            }
        } else if(algorithmSuite.equals("basic192sha256rsa15"))
        {
            if(encryptionSchema.equals("symmetric")) {
                algorithm="aes";
                keyLength=192;

            } else {
                algorithm="rsa";
                keyLength=15;
            }
        }
        if(algorithmSuite.equals("basic128sha256rsa15"))
        {
            if(encryptionSchema.equals("symmetric")) {
                algorithm="aes";
                keyLength=128;

            } else {
                algorithm="rsa";
                keyLength=15;
            }
        }

        return new CryptoSpec(algorithmSuite, encryptionSchema, algorithm, keyLength);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////



    public PVMCoreResult verifyTechnicalTrustProperties(String AgreementTemplate, URL ServiceImplementation) {
        PVMCoreResult result = new PVMCoreResult(-255,"Fatal Error");




        // Check for correct input
        if (AgreementTemplate == null) {
            return new PVMCoreResult(0, "No AgreementTemplate given");
        }
        if (ServiceImplementation == null) {
            return new PVMCoreResult(0, "No ServiceImplementation (WSDL) given");
        }

        File tempDirectory=null;
        try {
            tempDirectory = FileUtil.createTempDir();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(tempDirectory!=null) {
            String specfile= tempDirectory.toString() + File.separator + "agreementTemp.xml";

            // Currently uses a link to an xml file
            String DecodedAgreementTemplate = FileUtil.decodeBase64(AgreementTemplate);
            FileUtil.writeStringToFile(DecodedAgreementTemplate, specfile);
            ///////////////////////////
            FileUtil.downloadFile(ServiceImplementation, tempDirectory, "service.wsdl");
            String wsdlfile= tempDirectory.toString() + File.separator + "service.wsdl";
            ////////////////////////////////
            WSSecSpec spec = readSpec(specfile);
            WSSecSpec wsdl = readWSDL(wsdlfile);
            result = WsdlCheck.checkWsdlSpec(spec,wsdl);
        }

        return result;
    }


    public PVMCoreResult verifyWSDL(WSSecSpec spec, URL ServiceImplementation) {
        PVMCoreResult result = new PVMCoreResult(-255,"Fatal Error");
        if (spec == null) {
            return new PVMCoreResult(0, "No Requirements (Spec) given");
        }
        if (ServiceImplementation == null) {
            return new PVMCoreResult(0, "No ServiceImplementation (WSDL) given");
        }
        File tempDirectory=null;
        try {
            tempDirectory = FileUtil.createTempDir();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(tempDirectory!=null) {
            FileUtil.downloadFile(ServiceImplementation, tempDirectory, "service.wsdl");
            String wsdlfile= tempDirectory.toString() + File.separator + "service.wsdl";
            WSSecSpec wsdl = readWSDL(wsdlfile);
            result = WsdlCheck.checkWsdlSpec(spec,wsdl);
        }
        return result;
    }
}