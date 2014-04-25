/**
 * Copyright 2012  Bo Zhou <B.Zhou@ljmu.ac.uk>
 * Liverpool John Moores University <http://www.ljmu.ac.uk/cmp/>
 * Aniketos Project <http://www.aniketos.eu>
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either 
 * version 3 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package eu.aniketos.scpm.impl.client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import eu.aniketos.data.ISecurityProperty;
import eu.aniketos.data.IWebService;
import eu.aniketos.data.SPState;
import eu.aniketos.scpm.spdm.client.ObjectFactory;
import eu.aniketos.scpm.spdm.client.ArrayOfISecurityProperty;
import eu.aniketos.scpm.spdm.client.ISPDMService;
import eu.aniketos.scpm.spdm.client.ISPDMServicePortType;



public class SPDMClient {
	private final static int timeout = 1 * 60 * 1000; // in milliseconds
	public static Set<ISecurityProperty> getProperties(IWebService arg0, SPState arg1, String addressSPDM){
		
		ISPDMServicePortType servicePort = getPort(addressSPDM);
		        
        ArrayOfISecurityProperty result = new ArrayOfISecurityProperty();
        
		
		if (servicePort != null) {
			result = servicePort.getProperties(convertWebServiceToWS(arg0), eu.aniketos.scpm.spdm.client.SPState.fromValue(arg1.name()));
		}

		return convertSecurityPropertiesFromWS(result);
        
        
		
	}
	
	
	public Set<ISecurityProperty> getVerifiedProperties(IWebService arg0, String addressSPDM) {
        ISPDMServicePortType servicePort = getPort(addressSPDM);
        ArrayOfISecurityProperty result = new ArrayOfISecurityProperty();
		
		if (servicePort != null) {
			result = servicePort.getVerifiedProperties(convertWebServiceToWS(arg0));
		}

		return convertSecurityPropertiesFromWS(result);
	}
	
	private static ISPDMServicePortType getPort(String address)
	{
		QName serviceName = new QName("http://api.ds.spdm.aniketos.eu/", "ISPDMService");
		URL url = null;
        try {
            URL baseUrl;
            baseUrl = eu.aniketos.scpm.spdm.client.ISPDMService.class.getResource(".");
            url = new URL(baseUrl, address);
        } catch (MalformedURLException ex) {
        	ex.printStackTrace();
        }
		
				
        ISPDMService spdmService = new ISPDMService(url,serviceName);
        ISPDMServicePortType servicePort = null;
        try {
        	servicePort = spdmService.getISPDMServicePort();;

			Map<String, Object> requestContext = ((BindingProvider)servicePort).getRequestContext();
			requestContext.put("javax.xml.ws.client.receiveTimeout", Integer.toString(timeout));
		} catch (Exception exception) {
			System.out.println("SPDM Proxy exception: " + exception.getMessage());
		}
        
        return servicePort;
	}



	private static eu.aniketos.scpm.spdm.client.IWebService convertWebServiceToWS(IWebService webService) {
		eu.aniketos.scpm.spdm.client.IWebService result = new eu.aniketos.scpm.spdm.client.IWebService();
		ObjectFactory factory = new ObjectFactory();
		
		result.setServiceID(factory.createIWebServiceServiceID(webService.getServiceID()));
		
		return result;
	}
	
	private static Set<ISecurityProperty> convertSecurityPropertiesFromWS(ArrayOfISecurityProperty properties) {
        Set<ISecurityProperty> result = new HashSet<ISecurityProperty>();
		
		result.clear();
		Iterator<eu.aniketos.scpm.spdm.client.ISecurityProperty> iter = properties.getISecurityProperty().iterator();
		while (iter.hasNext()) {
			result.add(convertSecurityPropertyFromWS(iter.next()));
		}
		return result;
	}
	
	private static ISecurityProperty convertSecurityPropertyFromWS(eu.aniketos.scpm.spdm.client.ISecurityProperty property) {
		SecurityProperty result = new SecurityProperty();

		result.setPropertyID(property.getPropertyID().getValue());
		result.setPropertyValue(property.getPropertyValue().getValue());
		
		XMLGregorianCalendar freshness = property.getFreshness();
		if (freshness != null) {
			result.setFreshness(freshness.toGregorianCalendar().getTime());
		}
		
		eu.aniketos.scpm.spdm.client.X509Certificate certificate = property.getCertificate().getValue();
		if (certificate != null) {
			try {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutput out = new ObjectOutputStream(bos);
				out.writeObject(certificate);
				byte[] data = bos.toByteArray();
				bos.close();
				
				CertificateFactory cf = CertificateFactory.getInstance("X.509");
				X509Certificate x509Certificate = (X509Certificate) cf.generateCertificate(new  ByteArrayInputStream(data));		
				
				result.setCertificate(x509Certificate);
			}
			catch (Exception e) {
				System.out.println("Exception creating X509 Certificate: " + e.getMessage());
			}
		}

		eu.aniketos.scpm.spdm.client.SPState state = property.getState().getValue();
		if (state != null) {
			result.setState(convertStateFromWS(property.getState().getValue()));
		}
		
		return result;
	}
	
	private static SPState convertStateFromWS(eu.aniketos.scpm.spdm.client.SPState state) {
		SPState result = SPState.Bind;
		
		switch (state) {
		case BIND:
			result = SPState.Bind;
			break;
		case SIGNED:
			result = SPState.Signed;
			break;
		case UN_BIND:
			result = SPState.UnBind;
			break;
		case VERIFIED:
			result = SPState.Verified;
			break;
		default:
			result = eu.aniketos.data.SPState.valueOf(state.name());
			break;
		}
		
		return result;
	}

}
