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

/* <message type="input" >
		<confidentiality >
			<properties>
				<wssAlgorithmSuite>Basic256Sha256Rsa15
					<encryptionScheme>symmetric</encryptionScheme>
					<algorithm>AES</algorithm>
					<key>256</key>
				</wssAlgorithmSuite>
			</properties>
		</confidentiality>
	</message>*/

public class CryptoSpec {
    private String algorithmSuite="";
    private String encryptionSchema="";
    private String algorithm="";
    private int keyLength=-255;

    @SuppressWarnings("unused")
    private CryptoSpec() {};

    public CryptoSpec(String algorithmSuite, String encryptionSchema, String algorithm, int keyLength) {
        this.algorithmSuite   = algorithmSuite.toLowerCase();
        this.encryptionSchema = encryptionSchema.toLowerCase();
        this.algorithm        = algorithm.toLowerCase();
        this.keyLength        = keyLength;
    }

    public String getAlgorithmSuite() {
        return algorithmSuite;
    }

    public String getEncryptionSchema() {
        return encryptionSchema;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public int getKeyLength() {
        return keyLength;
    }

    public String toString() {
        return "{suite = "+algorithmSuite+", scheme = "+encryptionSchema+", algorithm = "+algorithm+", keylength = "+keyLength+"}";
    }

    public Boolean isEqualOrStrongerAs(CryptoSpec spec)
    {
        if (spec == null) return false;
        if(   this.algorithm.equals(spec.getAlgorithm())
                && this.encryptionSchema.equals(spec.getEncryptionSchema())
                && this.algorithmSuite.replaceAll("[0-9]", "").equals(spec.getAlgorithmSuite().replaceAll("[0-9]", ""))
                && spec.getKeyLength() <= this.keyLength) {
            return true;
        } else {
            return false;
        }
    }
}
