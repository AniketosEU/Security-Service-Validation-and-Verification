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

public class  PVMCoreResult {
    private int result = -1;
    private String explaination = "invalid result (you should never see this)";

    public PVMCoreResult(int result, String explaination)
    {
        this.result = result;
        this.explaination = explaination;
    }
    public int getVerificationResult() {
        return result;
    };
    public String getVerificationExplaination() {
        return explaination;
    };
}
