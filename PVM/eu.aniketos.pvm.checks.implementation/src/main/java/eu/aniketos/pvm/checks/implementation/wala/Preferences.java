/*
 * (C) Copyright 2010-2015 SAP SE.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 */

package eu.aniketos.pvm.checks.implementation.wala;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarFile;

import com.ibm.wala.classLoader.JarFileModule;
import com.ibm.wala.util.debug.Assertions;

public class Preferences {

    private static String javaHomePath;
    private static List<String> rtJar;
    public static String REGRESSION_EXCLUSIONS;

    /**
     * Set up stuff required by Wala to scan the source code
     */
    static {
        boolean found = false;
        try {
            rtJar = new LinkedList<String>();
            javaHomePath=System.getenv("JRE_LIB_DIR");

            REGRESSION_EXCLUSIONS = null;
            /* Should be loaded from JAR ...
            REGRESSION_EXCLUSIONS = System.getProperty("user.dir") + File.separator + "Java60RegressionExclusions.txt";
            System.out.println("url: "+ClassLoader.getSystemResource("Java60RegressionExclusions.txt"));
            */

            if (new File(javaHomePath).isDirectory()) {
                if ("Mac OS X".equals(System.getProperty("os.name"))) {
                    rtJar.add(javaHomePath + "/classes.jar");
                    rtJar.add(javaHomePath + "/ui.jar");
                } else {
                    rtJar.add(javaHomePath + File.separator + "classes.jar");
                    rtJar.add(javaHomePath + File.separator + "rt.jar");
                    rtJar.add(javaHomePath + File.separator + "core.jar");
                    rtJar.add(javaHomePath + File.separator + "vm.jar");
                }
                found = true;
            }
        } catch (Exception e) {
            // no properties
        }

        if (!found) {
            javaHomePath = System.getProperty("java.home");
            if ("Mac OS X".equals(System.getProperty("os.name"))) {
                rtJar.add(javaHomePath + "/../Classes/classes.jar");
                rtJar.add(javaHomePath + "/../Classes/ui.jar");
            } else {
                rtJar.add(javaHomePath + File.separator + "lib" + File.separator + "rt.jar");
                rtJar.add(javaHomePath + File.separator + "lib" + File.separator + "core.jar");
                rtJar.add(javaHomePath + File.separator + "lib" + File.separator + "vm.jar");
                rtJar.add(javaHomePath + File.separator + "lib" + File.separator + "classes.jar");
            }
        }
    }

    /**
     * Load required library
     * @return
     */
    public static JarFileModule getLib() {
        for (String lib : rtJar) {
            File libFile = new File(lib);
            if (libFile.exists()) {
                try {
                    return new JarFileModule(new JarFile(libFile));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Assertions.UNREACHABLE("Could not load library file. Please set JRE_LIB_DIR.");
        return null;
    }
}
