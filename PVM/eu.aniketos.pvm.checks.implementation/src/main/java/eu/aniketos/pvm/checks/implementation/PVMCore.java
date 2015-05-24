/*
 * (C) Copyright 2010-2015 SAP SE.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 */

package eu.aniketos.pvm.checks.implementation;
import java.io.File;
import java.net.URL;
import java.io.IOException;

import com.ibm.wala.cast.java.client.JavaSourceAnalysisEngine;
import com.ibm.wala.cast.java.translator.polyglot.PolyglotJavaSourceAnalysisEngine;
import com.ibm.wala.classLoader.SourceDirectoryTreeModule;
import com.ibm.wala.ipa.callgraph.CallGraph;
import com.ibm.wala.util.CancelException;

import eu.aniketos.pvm.commons.FileUtil;
import eu.aniketos.pvm.commons.PVMCoreResult;
import eu.aniketos.pvm.checks.implementation.Manager;
import eu.aniketos.pvm.checks.implementation.wala.WalaUtil;
import eu.aniketos.pvm.checks.implementation.wala.Preferences;


public class PVMCore {

    private JavaSourceAnalysisEngine engine;

    private Manager m = Manager.getInstance();

    private  void initializeWala() throws IOException {
        engine = new PolyglotJavaSourceAnalysisEngine();
        engine.addSystemModule(Preferences.getLib());
        m.openZipFile();
        m.readXML();
    }

    private PVMCoreResult findDangerousFunctions()
    throws IOException, IllegalArgumentException, CancelException {
        PVMCoreResult result= new PVMCoreResult(-1,"Uncaught exception in findDangerousFunctions.");
        engine.addSourceModule(new SourceDirectoryTreeModule(m.PROJECTFILE));
        engine.setExclusionsFile(Preferences.REGRESSION_EXCLUSIONS);
        engine.buildAnalysisScope();

        CallGraph cg = engine.buildDefaultCallGraph();
        WalaUtil util = new WalaUtil();

        FileUtil.startNewOutputFile();

        boolean res = false;

        for (String s : m.getDangerousFunctions()) {
            res = res || util.findDangerousFunctions(cg, s);
        }

        if (res) {
            result = new PVMCoreResult(-1,FileUtil.getXML());
        } else {
            result = new PVMCoreResult(0,FileUtil.getXML());
        }
        return result;
    }

    public  PVMCoreResult verifyTechnicalTrustProperties(String AgreementTemplate,
            URL ServiceImplementation)
    {
        PVMCoreResult result;

        if (AgreementTemplate == null) {
            return new PVMCoreResult(0,"No AgreementTemplate given");
        }

        Manager m = Manager.getInstance();
        m.ZIPFILEURL= ServiceImplementation;
        m.XMLFILE = m.tempDirectory.toString() + File.separator + "agreementTemp.xml";


        // Currently uses a link to an xml file
        String DecodedAgreementTemplate = FileUtil.decodeBase64(AgreementTemplate);
        FileUtil.writeStringToFile(DecodedAgreementTemplate,m.XMLFILE);


        try {
            initializeWala();
            result = findDangerousFunctions();
        } catch(Exception e) { // can be improved ...
            return  new PVMCoreResult(-2, e.getMessage());
        }
        return result;
    }

}
