/*
Copyright (c) 2013, Aneel Rahim, Bernard Butler and Arif Fareed (Waterford Institute of Technology, Ireland), Project: FP7-ICT-257930 Aniketos
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

 -  Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 -  Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 -  Neither the name of WATERFORD INSTITUTE OF TECHNOLOGY nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
import java.lang.SecurityException;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.security.cert.CertificateFactory;

public interface CertificateManager {
	

    X509Certificate loadCertificate(InputStream in)
    throws SecurityException;

    X509Certificate[] getX509Certificates(byte[] data, boolean reverse)
    throws SecurityException;

    byte[] getCertificateData(boolean reverse, X509Certificate[] certs)
    throws SecurityException;

    public PrivateKey getPrivateKey(String alias, String password)
    throws Exception;

    public X509Certificate[] getCertificates(String alias)
    throws SecurityException;

    public String getAliasForX509Cert(Certificate cert)
    throws SecurityException;

    public String getAliasForX509Cert(String issuer)
    throws SecurityException;

    public String getAliasForX509Cert(String issuer, BigInteger serialNumber)
    throws SecurityException;

    public String getAliasForX509Cert(byte[] skiBytes)
    throws SecurityException;

    public String getDefaultX509Alias();

    public byte[] getSKIBytesFromCert(X509Certificate cert)
    throws SecurityException;
 
    public String getAliasForX509CertThumb(byte[] thumb)
    throws SecurityException;
 
    public KeyStore getKeyStore();

    public CertificateFactory getCertificateFactory()
    throws SecurityException;

    public boolean validateCertPath(X509Certificate[] certs)
    throws SecurityException;

    public String[] getAliasesForDN(String subjectDN)
    throws SecurityException;
}