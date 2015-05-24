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
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.zip.ZipFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import eu.aniketos.pvm.commons.FileUtil;

public class Manager {

    private static Manager instance = null;

    public static Manager getInstance() {
        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    private LinkedList<String> dangerousFunctions;
    private HashMap<String, HashSet<String>> dangerousFunctionsMap;

    public String PROJECTPATH;

    public String XMLFILE;

    public File PROJECTFILE;

    private Map<String, String> slices;

    private String ZIPFILEPATH;

    public URL ZIPFILEURL;


    public File tempDirectory;

    private Manager() {
        try {
            tempDirectory = FileUtil.createTempDir();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<String> getDangerousFunctions() {
        return dangerousFunctions;
    }
    public HashMap<String, HashSet<String>> getDangerousFunctionsMap() {
        return dangerousFunctionsMap;
    }

    public Map<String, String> getSlices() {
        return slices;
    }

    public boolean informationCheck() {
        return (ZIPFILEURL != null && XMLFILE != null);
    }

    /**
     * Opens and unzips the project zip.
     *
     * @return The path to the temporary directory containing the unzipped project
     *         files
     */
    public void openZipFile() throws IOException {
        assert ZIPFILEURL != null : "No file path set";
        assert ZIPFILEURL.toString().endsWith(".zip") : "Not a valid file format";


        FileUtil.downloadFile(ZIPFILEURL, tempDirectory, "zip");
        ZIPFILEPATH = tempDirectory.getAbsolutePath() + File.separator + "project.zip";
        System.out.println(tempDirectory);
        ZipFile file = new ZipFile(ZIPFILEPATH);
        PROJECTFILE = tempDirectory;
        PROJECTPATH = PROJECTFILE.getAbsolutePath();
        FileUtil.unzipFileIntoDirectory(file, PROJECTFILE);
    }

    public void setDangerousFunctions(HashMap<String, HashSet<String>> dangerousFunctions) {
        this.dangerousFunctionsMap = dangerousFunctions;
    }

    public void setDangerousFunctions(LinkedList<String> dangerousFunctions) {
        this.dangerousFunctions = dangerousFunctions;
    }

    public void setSlices(Map<String, String> slices) {
        this.slices = slices;
    }

    public void readXML() {
        readXML(XMLFILE);
    }

    public static void readXML(String path) {

        boolean ConSpeX = false;

        Manager m = Manager.getInstance();

        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(path));
            doc.normalize();

            Node DangerousFunctions = doc.getElementsByTagName(
                                          "DangerousFunctions").item(0);

            LinkedList<String> dangerousFunctions = new LinkedList<String>();
            HashMap<String, String> slices = new HashMap<String, String>();

            NodeList nodes = DangerousFunctions.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodes.item(i);
                    dangerousFunctions.add(element.getChildNodes().item(0)
                                           .getNodeValue());
                }
            }

            m.setDangerousFunctions(dangerousFunctions);

            NodeList Slices = doc.getElementsByTagName("Slice");
            for (int i = 0; i < Slices.getLength(); i++) {
                Node node = Slices.item(i);
                nodes = node.getChildNodes();
                String sliceTar = null;
                String sliceSan = null;
                for (int j = 0; j < nodes.getLength(); j++) {
                    if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        Element el = (Element) nodes.item(j);
                        if (el.getNodeName().equals("TargetFunction"))
                            sliceTar = el.getChildNodes().item(0)
                                       .getNodeValue();
                        else if (el.getNodeName().equals("SanitationFunction"))
                            sliceSan = el.getChildNodes().item(0)
                                       .getNodeValue();
                    }
                    if (sliceSan != null && sliceTar != null)
                        slices.put(sliceTar, sliceSan);

                }
                m.setSlices(slices);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }


}
