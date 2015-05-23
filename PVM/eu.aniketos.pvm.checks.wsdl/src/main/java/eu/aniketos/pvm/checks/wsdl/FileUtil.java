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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.UUID;

import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.*;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

public class FileUtil {

    private static Document document;

    /**
     * @param zipFile
     * @param jiniHomeParentDir
     */
    public static void unzipFileIntoDirectory(ZipFile zipFile, File jiniHomeParentDir) {
        Enumeration<? extends ZipEntry> files = zipFile.entries();
        File f = null;
        FileOutputStream fos = null;

        while (files.hasMoreElements()) {
            try {
                ZipEntry entry = (ZipEntry) files.nextElement();
                InputStream eis = zipFile.getInputStream(entry);
                byte[] buffer = new byte[1024];
                int bytesRead = 0;

                f = new File(jiniHomeParentDir.getAbsolutePath() + File.separator + entry.getName());

                if (entry.isDirectory()) {
                    f.mkdirs();
                    continue;
                } else {
                    f.getParentFile().mkdirs();
                    f.createNewFile();
                }

                fos = new FileOutputStream(f);

                while ((bytesRead = eis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        // ignore
                    }
                }
            }
        }
    }


    /**
     * Create a new temporary directory. Use something like
     * {@link #recursiveDelete(File)} to clean this directory up since it isn't
     * deleted automatically
     *
     * @return the new directory
     * @throws IOException
     *           if there is an error creating the temporary directory
     */
    public static File createTempDir() throws IOException {
        final File sysTempDir = new File(System.getProperty("java.io.tmpdir"));
        File newTempDir;
        final int maxAttempts = 9;
        int attemptCount = 0;
        do {
            attemptCount++;
            if (attemptCount > maxAttempts) {
                throw new IOException("The highly improbable has occurred! Failed to "
                                      + "create a unique temporary directory after " + maxAttempts + " attempts.");
            }
            String dirName = UUID.randomUUID().toString();
            newTempDir = new File(sysTempDir, dirName);
        } while (newTempDir.exists());

        if (newTempDir.mkdirs()) {
            return newTempDir;
        } else {
            throw new IOException("Failed to create temp dir named " + newTempDir.getAbsolutePath());
        }
    }

    public static void startNewOutputFile() {

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.newDocument();
            Element result = document.createElement("pvmResult");
            document.appendChild(result);
            Element DangerousFunctions = document.createElement("DangerousFunctions");
            result.appendChild(DangerousFunctions);
            Element InputValidations = document.createElement("InputValidations");
            result.appendChild(InputValidations);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

    public static void addDangerousFunction(String name, String location, String className) {
        NodeList subroot = document.getElementsByTagName("DangerousFunctions");
        Node node = subroot.item(0);
        Element DangerousFunction = document.createElement("DangerousFunction");
        node.appendChild(DangerousFunction);
        Element Name = document.createElement("Name");
        Name.appendChild(document.createTextNode(name));
        DangerousFunction.appendChild(Name);
        Element Location = document.createElement("Location");
        DangerousFunction.appendChild(Location);
        Location.appendChild(document.createTextNode(location));

        Element Class = document.createElement("Class");
        DangerousFunction.appendChild(Class);
        Class.appendChild(document.createTextNode(className));
    }

    public static void addValidationFunction(String name, boolean validation) {
        NodeList subroot = document.getElementsByTagName("InputValidations");
        Node node = subroot.item(0);
        Element InputValidation = document.createElement("InputValidation");
        node.appendChild(InputValidation);
        Element Function = document.createElement("Function");
        Function.appendChild(document.createTextNode(name));
        InputValidation.appendChild(Function);
        Element Validated = document.createElement("Validated");
        InputValidation.appendChild(Validated);
        if (validation)
            Validated.appendChild(document.createTextNode("true"));
        else
            Validated.appendChild(document.createTextNode("false"));

    }

    public static String getXML() {
        DOMImplementationLS domImplementation = (DOMImplementationLS) document.getImplementation();
        LSSerializer lsSerializer = domImplementation.createLSSerializer();
        return lsSerializer.writeToString(document);
    }

    public static void printXMLtoFile(String path) {

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            File outfile = new File(path);
            StreamResult result = new StreamResult(outfile);
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }



    /**
     * Recursively delete file or directory
     *
     * @param fileOrDir
     *          the file or dir to delete
     * @return true iff all files are successfully deleted
     */
    public static boolean recursiveDelete(File fileOrDir) {
        if (fileOrDir.isDirectory()) {
            // recursively delete contents
            for (File innerFile : fileOrDir.listFiles()) {
                if (!recursiveDelete(innerFile)) {
                    return false;
                }
            }
        }

        return fileOrDir.delete();
    }

    public static void downloadFile(URL url, File tempdir, String fileExtension) {
        try {
            // Copy resource to local file, use remote file
            // if no local file name specified
            InputStream is = url.openStream();

            FileOutputStream fos = null;
            fos = new FileOutputStream(tempdir + File.separator  + fileExtension);
            int oneChar, count = 0;
            while ((oneChar = is.read()) != -1) {
                fos.write(oneChar);
                count++;
            }
            is.close();
            fos.close();
            System.out.println(count + " byte(s) copied");
        } catch (MalformedURLException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Decode a given string using base64 Assertion if string is not base64
     * encoded
     *
     * @param encodedContent
     * @return Decoded string
     */
    public static String decodeBase64(String encodedContent) {

        assert !Base64.isBase64(encodedContent) : "Not base64";

        Base64 decoder = new Base64();
        byte[] decodedContent = decoder.decode(encodedContent);
        return new String(decodedContent);

    }

    public static void writeStringToFile(String content, String path) {
        try {
            // Create file
            FileWriter fstream = new FileWriter(path);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(content);
            out.close();
        } catch (Exception e) {// Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }



}