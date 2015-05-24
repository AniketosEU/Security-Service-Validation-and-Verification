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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

import eu.aniketos.pvm.commons.PVMCoreResult;

public class Main {

    private static String readFileAsString(String filePath) throws java.io.IOException {
        byte[] buffer = new byte[(int) new File(filePath).length()];
        BufferedInputStream f = null;
        try {
            f = new BufferedInputStream(new FileInputStream(filePath));
            f.read(buffer);
        } finally {
            if (f != null) try {
                    f.close();
                }
                catch (IOException ignored) { }
        }
        return new String(buffer);
    }


    public static void main(String[] args) {
        int i = 0;
        String arg;
        String source = null;
        String policy = null;
        boolean vflag = false;
        boolean hflag = false;

        while (i < args.length && args[i].startsWith("-")) {
            arg = args[i++];
            if (arg.equals("--verbose") || arg.equals("-v")) {
                System.out.println("verbose mode on");
                vflag = true;
            } else if (arg.equals("--help") || arg.equals("-h")) {
                hflag = true;
            }
        }
        if (args.length > i) {
            source = args[i++];
            if (vflag)
                System.out.println("service implementation: = " + source);
        }
        if (args.length > i) {
            policy = args[i++];
            if (vflag)
                System.out.println("agreement template: = " + policy);
        }

        if (hflag || (i != args.length) || (0 == args.length)) {
            System.err
            .println("java -jar pvm [OPTION] ... [SERVICE_IMPLEMENTATION] [AGREEMENT_TEMPLATE]");
            System.err
            .println("     The Property Verification Module (PVM) checks that a SERVICE_IMPLEMENTATION (given as");
            System.err
            .println("     zip archive containing the Java source files) complies to the AGREEMENT_TEMPLATE.");
            System.err
            .println("     The AGREEMENT_TEMPLATE should either be given in ConSpec format or Simple Policy Format.");
            System.err.println("     ");
            System.err
            .println("     -h, --help                print out this help text");
            System.err.println("     -v, --verbose             verbose mode");
            System.err.println("     -o FILE, --output FILE    output file");
            System.exit(0);
        }

        PVMCore pvm = new PVMCore();

        String policyBase64=null;
        URL sourceURL=null;
        PVMCoreResult result;

        if (source.startsWith("http://") || source.startsWith("ftp://") || source.startsWith("file://")) {
        } else if (source.startsWith("/") || source.startsWith("\\")) {
            source = "file://"+source;
        } else {
            source = "file://"+System.getProperty("user.dir")+"/"+source;
        }

        try {
            sourceURL=new URL(source);
        } catch (MalformedURLException e) {
            System.err.println("Source URL not valid: "+source);
            System.exit(1);
        }
        Base64 encoder = new Base64();
        try {
            policyBase64 = encoder.encodeToString(readFileAsString(policy).getBytes());
        } catch (IOException e) {
            System.err.println("Policy not found: "+policy);
            System.exit(1);
        }

        if (vflag)
            System.out.println("Policy: "+policyBase64);
        if(vflag)
            System.out.println("Implementation: "+sourceURL);

        result=pvm.verifyTechnicalTrustProperties(policyBase64,sourceURL);

        System.out.println("Overall result: "+result.getVerificationResult());
        System.out.println("Explaination: "+result.getVerificationExplaination());
    }

}
