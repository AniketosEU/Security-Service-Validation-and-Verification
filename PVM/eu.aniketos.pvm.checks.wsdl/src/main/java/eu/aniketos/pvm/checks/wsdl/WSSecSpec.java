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


public class WSSecSpec {

    @SuppressWarnings("unused")
    private WSSecSpec() {}

    private CryptoSpec input;
    private CryptoSpec output;

    public WSSecSpec(CryptoSpec input, CryptoSpec output)
    {
        this.input=input;
        this.output=output;
    }

    public CryptoSpec getOutput()
    {
        return output;
    }
    public CryptoSpec getInput()
    {
        return input;
    }

    public String toString()
    {
        return "{input = "+input+", output = "+output+"}";
    }

    public boolean isEqualOrStrongerAs(WSSecSpec spec) {
        if(spec.getInput()==null) {
            if (spec.getOutput() == null) {
                return true; // any policy implements the empty spec
            } else {
                return this.output.isEqualOrStrongerAs(spec.getOutput());
            }
        } else {
            if (spec.getOutput() == null) {
                return this.input.isEqualOrStrongerAs(spec.getInput()); // any policy implements the empty spec
            } else {
                return   (this.input.isEqualOrStrongerAs(spec.getInput())
                          && this.output.isEqualOrStrongerAs(spec.getOutput()));
            }
        }
    }
}
