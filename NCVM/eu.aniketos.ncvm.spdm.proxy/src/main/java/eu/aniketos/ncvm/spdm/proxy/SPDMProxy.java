/**
 * Copyright 2014 Liverpool John Moores University <http://www.ljmu.ac.uk/cmp/>
 * Aniketos Project FP7-ICT-257930 <http://www.aniketos.eu>
 * David Llewellyn-Jones <D.Llewellyn-Jones@ljmu.ac.uk>
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

package eu.aniketos.ncvm.spdm.proxy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import eu.aniketos.data.ISPDMService;
import eu.aniketos.data.ISPSRepository;
import eu.aniketos.data.ISecurityDescriptor;
import eu.aniketos.data.ISecurityProperty;
import eu.aniketos.data.IWebService;
import eu.aniketos.data.SPState;
import eu.aniketos.ncvm.spdm.client.ArrayOfISecurityProperty;
import eu.aniketos.ncvm.spdm.client.ArrayOfIWebService;
import eu.aniketos.ncvm.spdm.client.ISPDMServiceClient;
import eu.aniketos.ncvm.spdm.client.ISPDMServicePortType;
import eu.aniketos.ncvm.spdm.client.ObjectFactory;
import eu.aniketos.ncvm.userinterface.proxy.INCVMProxy;

public class SPDMProxy implements ISPDMService, INCVMProxy {
    private URL wsdlURL = ISPDMServiceClient.WSDL_LOCATION;
	private final static int timeout = 1 * 60 * 1000; // in milliseconds

	@Override
	public void setURL(String wsdlURL) {
    	try {
			this.wsdlURL = new URL(wsdlURL);
		} catch (MalformedURLException e) {
			System.out.println("Failed to set SPDM URL to " + wsdlURL);
		}
	}
	
	private ISPDMServicePortType getPort() {
	    QName SERVICE_NAME = new QName("http://api.ds.spdm.aniketos.eu/", "ISPDMService");

        ISPDMServicePortType servicePort = null;
        ISPDMServiceClient service = new ISPDMServiceClient(wsdlURL, SERVICE_NAME);
        
		try {
	        servicePort = service.getISPDMServicePort();

			Map<String, Object> requestContext = ((BindingProvider)servicePort).getRequestContext();
			requestContext.put("javax.xml.ws.client.receiveTimeout", Integer.toString(timeout));
		} catch (Exception exception) {
			System.out.println("SPDM Proxy exception: " + exception.getMessage());
		}
		
		return servicePort;
	}

	private Set<ISecurityProperty> convertSecurityPropertiesFromWS(ArrayOfISecurityProperty properties) {
        Set<ISecurityProperty> result = new HashSet<ISecurityProperty>();
		
		result.clear();
		Iterator<eu.aniketos.ncvm.spdm.client.ISecurityProperty> iter = properties.getISecurityProperty().iterator();
		while (iter.hasNext()) {
			result.add(convertSecurityPropertyFromWS(iter.next()));
		}
		return result;
	}

	private ArrayOfISecurityProperty convertSecurityPropertiesToWS(List<ISecurityProperty> properties) {
		ArrayOfISecurityProperty result = new ArrayOfISecurityProperty();
		
		result.getISecurityProperty().clear();
		Iterator<ISecurityProperty> iter = properties.iterator();
		while (iter.hasNext()) {
			result.getISecurityProperty().add(convertSecurityPropertyToWS(iter.next()));
		}
		return result;
	}

	private Set<IWebService> convertWebServicesFromWS(ArrayOfIWebService properties) {
        Set<IWebService> result = new HashSet<IWebService>();
		
		result.clear();
		Iterator<eu.aniketos.ncvm.spdm.client.IWebService> iter = properties.getIWebService().iterator();
		while (iter.hasNext()) {
			result.add(convertWebServiceFromWS (iter.next()));
		}
		return result;
	}
	
	private IWebService convertWebServiceFromWS(eu.aniketos.ncvm.spdm.client.IWebService webService) {
		IWebService result = new WebService();
		
		result.setServiceID(webService.getServiceID().getValue());
		
		return result;
	}

	private eu.aniketos.ncvm.spdm.client.IWebService convertWebServiceToWS(IWebService webService) {
		eu.aniketos.ncvm.spdm.client.IWebService result = new eu.aniketos.ncvm.spdm.client.IWebService();
		ObjectFactory factory = new ObjectFactory();
		
		result.setServiceID(factory.createIWebServiceServiceID(webService.getServiceID()));
		
		return result;
	}

	private ISecurityProperty convertSecurityPropertyFromWS(eu.aniketos.ncvm.spdm.client.ISecurityProperty property) {
		SecurityProperty result = new SecurityProperty();

		result.setPropertyID(property.getPropertyID().getValue());
		result.setPropertyValue(property.getPropertyValue().getValue());
		
		XMLGregorianCalendar freshness = property.getFreshness();
		if (freshness != null) {
			result.setFreshness(freshness.toGregorianCalendar().getTime());
		}
		
		eu.aniketos.ncvm.spdm.client.X509Certificate certificate = property.getCertificate().getValue();
		if (certificate != null) {
			try {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutput out = new ObjectOutputStream(bos);
				out.writeObject(certificate);
				byte[] data = bos.toByteArray();
				bos.close();
				
				CertificateFactory cf = CertificateFactory.getInstance("X.509");
				X509Certificate x509Certificate = (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(data));		
				
				result.setCertificate(x509Certificate);
			}
			catch (Exception e) {
				System.out.println("Exception creating X509 Certificate: " + e.getMessage());
			}
		}

		eu.aniketos.ncvm.spdm.client.SPState state = property.getState().getValue();
		if (state != null) {
			result.setState(convertStateFromWS(property.getState().getValue()));
		}
		
		return result;
	}
	
	private SPState convertStateFromWS(eu.aniketos.ncvm.spdm.client.SPState state) {
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
	
	private eu.aniketos.ncvm.spdm.client.SPState convertStateToWS(SPState state) {
		eu.aniketos.ncvm.spdm.client.SPState result = eu.aniketos.ncvm.spdm.client.SPState.BIND;
		
		switch (state) {
		case Bind:
			result = eu.aniketos.ncvm.spdm.client.SPState.BIND;
			break;
		case Signed:
			result = eu.aniketos.ncvm.spdm.client.SPState.SIGNED;
			break;
		case UnBind:
			result = eu.aniketos.ncvm.spdm.client.SPState.UN_BIND;
			break;
		case Verified:
			result = eu.aniketos.ncvm.spdm.client.SPState.VERIFIED;
			break;
		default:
			result = eu.aniketos.ncvm.spdm.client.SPState.valueOf(state.name());
			break;
		}
		
		return result;
	}
	
	private XMLGregorianCalendar convertDateToWS (Date date) throws DatatypeConfigurationException {
		XMLGregorianCalendar result = null;

		if (date != null) {
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(date);
			result = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
		}
		
		return result;
	}
	
	private eu.aniketos.ncvm.spdm.client.ISecurityProperty convertSecurityPropertyToWS(ISecurityProperty property) {
		eu.aniketos.ncvm.spdm.client.ISecurityProperty result = new eu.aniketos.ncvm.spdm.client.ISecurityProperty();
		ObjectFactory factory = new ObjectFactory();

		result.setPropertyID(factory.createISecurityPropertyPropertyID(property.getPropertyID()));
		result.setPropertyValue(factory.createISecurityPropertyPropertyValue(property.getPropertyValue()));

		try {
			result.setFreshness(convertDateToWS(property.getFreshness()));
		} catch (DatatypeConfigurationException e) {
			System.out.printf("Failed to convert date: " + e.getLocalizedMessage());
		}

		SPState state = property.getState();
		if (state != null) {
			result.setState(factory.createISecurityPropertyState(convertStateToWS(state)));
		}

		try {
			result.setCertificate(factory.createISecurityPropertyCertificate(convertX509CertificateToWS(property.getCertificate())));
		} catch (CertificateEncodingException e) {
			System.out.println("Warning: X509Certificate encoding could not be converted.");
		} catch (CertificateParsingException e) {
			System.out.println("Warning: X509Certificate failed to parse correctly.");
		} catch (DatatypeConfigurationException e) {
			System.out.println("Warning: X509Certificate error converting dates.");
		}

		return result;
	}
	
	private eu.aniketos.ncvm.spdm.client.ArrayOfString convertStringListToWS(List<String> stringList) {
		eu.aniketos.ncvm.spdm.client.ObjectFactory factory = new eu.aniketos.ncvm.spdm.client.ObjectFactory();
		eu.aniketos.ncvm.spdm.client.ArrayOfString result = factory.createArrayOfString();
		
		result.getString().clear();
		if (stringList != null) {
			for (String string : stringList) {
				result.getString().add(string);
			}
		}
		
		return result;
	}
	
	private eu.aniketos.ncvm.spdm.client.ArrayOfArrayOfAnyType convertCollectionListToWS(Collection<List<?>> collectionList) {
		eu.aniketos.ncvm.spdm.client.ObjectFactory factory = new eu.aniketos.ncvm.spdm.client.ObjectFactory();
		eu.aniketos.ncvm.spdm.client.ArrayOfArrayOfAnyType result = factory.createArrayOfArrayOfAnyType();
		
		result.getArrayOfAnyType().clear();
		if (collectionList != null) {
			for (List<?> list : collectionList) {
				eu.aniketos.ncvm.spdm.client.ArrayOfAnyType resultList = factory.createArrayOfAnyType();
				resultList.getAnyType().clear();
				if (list != null) {
					for (Object item : list) {
						resultList.getAnyType().add(item);
					}
				}
			}
		}
		
		return result;
	}

	private eu.aniketos.ncvm.spdm.client.Principal convertPrincipalToWS(java.security.Principal principal) {
		eu.aniketos.ncvm.spdm.client.ObjectFactory factory = new eu.aniketos.ncvm.spdm.client.ObjectFactory();
		eu.aniketos.ncvm.spdm.client.Principal result;
		
		result = factory.createPrincipal();
		
		result.setName(factory.createPrincipalName(principal.getName()));
		
		return result;
	}
	
	private eu.aniketos.ncvm.spdm.client.ArrayOfBoolean convertyBooleanArrayToWS(boolean[] booleanArray) {
		eu.aniketos.ncvm.spdm.client.ObjectFactory factory = new eu.aniketos.ncvm.spdm.client.ObjectFactory();
		eu.aniketos.ncvm.spdm.client.ArrayOfBoolean result = factory.createArrayOfBoolean();
		
		result.getBoolean().clear();
		if (booleanArray != null) {
			for (boolean item : booleanArray) {
				result.getBoolean().add(item);
			}
		}
		
		return result;
	}
	
	public eu.aniketos.ncvm.spdm.client.X500Principal convertX500PrincipalToWS(javax.security.auth.x500.X500Principal principal) {
		eu.aniketos.ncvm.spdm.client.ObjectFactory factory = new eu.aniketos.ncvm.spdm.client.ObjectFactory();
		eu.aniketos.ncvm.spdm.client.X500Principal result;

		result = factory.createX500Principal();
		
		result.setEncoded(factory.createCertificateEncoded(principal.getEncoded()));
		result.setName(factory.createX500PrincipalName(principal.getName()));
		
		return result;
	}

	private eu.aniketos.ncvm.spdm.client.PublicKey convertPublicKeyToWS(java.security.PublicKey publicKey) {
		eu.aniketos.ncvm.spdm.client.ObjectFactory factory = new eu.aniketos.ncvm.spdm.client.ObjectFactory();
		eu.aniketos.ncvm.spdm.client.PublicKey result;
		
		result = factory.createPublicKey();
		
		result.setAlgorithm(factory.createKeyAlgorithm(publicKey.getAlgorithm()));
		result.setEncoded(factory.createKeyEncoded(publicKey.getEncoded()));
		result.setFormat(factory.createKeyFormat(publicKey.getFormat()));
		
		return result;
	}

	private eu.aniketos.ncvm.spdm.client.X509Certificate convertX509CertificateToWS (X509Certificate certificate) throws CertificateEncodingException, CertificateParsingException, DatatypeConfigurationException {
		eu.aniketos.ncvm.spdm.client.ObjectFactory factory = new eu.aniketos.ncvm.spdm.client.ObjectFactory();
		eu.aniketos.ncvm.spdm.client.X509Certificate result = null;

		if (certificate != null) {
			result = new eu.aniketos.ncvm.spdm.proxy.X509Certificate();
			
			result.setBasicConstraints(certificate.getBasicConstraints());
			result.setEncoded(factory.createCertificateEncoded(certificate.getEncoded()));
			result.setExtendedKeyUsage(factory.createX509CertificateExtendedKeyUsage(convertStringListToWS(certificate.getExtendedKeyUsage())));
			result.setIssuerAlternativeNames(factory.createX509CertificateIssuerAlternativeNames(convertCollectionListToWS(certificate.getIssuerAlternativeNames())));
			result.setIssuerDN(factory.createX509CertificateIssuerDN(convertPrincipalToWS(certificate.getIssuerDN())));
			result.setIssuerUniqueID(factory.createX509CertificateIssuerUniqueID(convertyBooleanArrayToWS(certificate.getIssuerUniqueID())));
			result.setIssuerX500Principal(factory.createX509CertificateIssuerX500Principal(convertX500PrincipalToWS(certificate.getIssuerX500Principal())));
			result.setKeyUsage(factory.createX509CertificateIssuerUniqueID(convertyBooleanArrayToWS(certificate.getKeyUsage())));
			result.setNotAfter(convertDateToWS(certificate.getNotAfter()));
			result.setNotBefore(convertDateToWS(certificate.getNotBefore()));
			result.setPublicKey(factory.createCertificatePublicKey(convertPublicKeyToWS(certificate.getPublicKey())));
			result.setSerialNumber(factory.createX509CertificateSerialNumber(certificate.getSerialNumber()));
			result.setSigAlgName(factory.createX509CertificateSigAlgName(certificate.getSigAlgName()));
			result.setSigAlgOID(factory.createX509CertificateSigAlgOID(certificate.getSigAlgOID()));
			result.setSigAlgParams(factory.createX509CertificateSigAlgParams(certificate.getSigAlgParams()));
			result.setSignature(factory.createX509CertificateSignature(certificate.getSignature()));
			result.setSubjectAlternativeNames(factory.createX509CertificateSubjectAlternativeNames(convertCollectionListToWS(certificate.getSubjectAlternativeNames())));
			result.setSubjectDN(factory.createX509CertificateSubjectDN(convertPrincipalToWS(certificate.getSubjectDN())));
			result.setSubjectUniqueID(factory.createX509CertificateSubjectUniqueID(convertyBooleanArrayToWS(certificate.getSubjectUniqueID())));
			result.setSubjectX500Principal(factory.createX509CertificateIssuerX500Principal(convertX500PrincipalToWS(certificate.getSubjectX500Principal())));
			result.setTBSCertificate(factory.createX509CertificateTBSCertificate(certificate.getTBSCertificate()));
			result.setType(factory.createCertificateType(certificate.getType()));
			result.setVersion(certificate.getVersion());
		}
		
		return result;
	}

	private eu.aniketos.ncvm.spdm.client.ISecurityDescriptor convertSecurityDescriptorToWS(ISecurityDescriptor descriptor) {
		eu.aniketos.ncvm.spdm.client.ISecurityDescriptor result = new eu.aniketos.ncvm.spdm.client.ISecurityDescriptor();
		ArrayOfISecurityProperty properties;
		
		ObjectFactory factory = new ObjectFactory();

		properties = convertSecurityPropertiesToWS(descriptor.getProperties());
		result.setProperties(factory.createISecurityDescriptorProperties(properties));
		
		return result;
	}
	
	@Override
	public int cache_size() {
        ISPDMServicePortType servicePort = getPort();
        int result = 0;

		if (servicePort != null) {
			result = servicePort.cacheSize();
		}
        
        return result;
	}

	@Override
	public void emptyCache() {
        ISPDMServicePortType servicePort = getPort();
		
		if (servicePort != null) {
			servicePort.emptyCache();
		}
	}

	@Override
	public ISPSRepository fetchRepository() {
        ISPDMServicePortType servicePort = getPort();
        eu.aniketos.ncvm.spdm.client.ISPSRepository result = null;
		
		if (servicePort != null) {
			result = servicePort.fetchRepository();
		}

		return (ISPSRepository)result;
	}

	@Override
	public Set<ISecurityProperty> getProperties(IWebService arg0, SPState arg1) {
        ISPDMServicePortType servicePort = getPort();
        ArrayOfISecurityProperty result = new ArrayOfISecurityProperty();
        
		
		if (servicePort != null) {
			result = servicePort.getProperties(convertWebServiceToWS(arg0), eu.aniketos.ncvm.spdm.client.SPState.fromValue(arg1.name()));
		}

		return convertSecurityPropertiesFromWS(result);
	}

	@Override
	public ISecurityProperty getSecurityProperty(String arg0) {
        ISPDMServicePortType servicePort = getPort();
		ISecurityProperty result = null;

		if (servicePort != null) {
			result = convertSecurityPropertyFromWS(servicePort.getSecurityProperty(arg0));
		}
		return result;
	}

	//@Override
	public int updateSecurityProperty(String serviceID, String sp_id, String update_value) {
        ISPDMServicePortType servicePort = getPort();
		int result = -1;

		if (servicePort != null) {
			result = servicePort.updateSecurityProperty(serviceID, sp_id, update_value);
		}
		return result;
	}
	
	@Override
	public ISecurityProperty getSecurityProperty(String arg0, String arg1) {
        ISPDMServicePortType servicePort = getPort();
		ISecurityProperty result = null;

		if (servicePort != null) {
			result = convertSecurityPropertyFromWS(servicePort.getSecurityProperty1(arg0, arg1));
		}
		return result;
	}

	@Override
	public IWebService getService(String arg0) {
        ISPDMServicePortType servicePort = getPort();
		IWebService result = null;

		if (servicePort != null) {
			result = convertWebServiceFromWS (servicePort.getService(arg0));
		}
		return result;
	}

	@Override
	public Set<ISecurityProperty> getVerifiedProperties(IWebService arg0) {
        ISPDMServicePortType servicePort = getPort();
        ArrayOfISecurityProperty result = new ArrayOfISecurityProperty();
		
		if (servicePort != null) {
			result = servicePort.getVerifiedProperties(convertWebServiceToWS(arg0));
		}

		return convertSecurityPropertiesFromWS(result);
	}

	@Override
	public Set<ISecurityProperty> lookUpSecurityProperty(IWebService arg0) {
        ISPDMServicePortType servicePort = getPort();
        ArrayOfISecurityProperty result = new ArrayOfISecurityProperty();
		
		if (servicePort != null) {
			result = servicePort.lookUpSecurityProperty(convertWebServiceToWS(arg0));
		}

		return convertSecurityPropertiesFromWS(result);
	}

	@Override
	public Set<IWebService> lookupService(ISecurityProperty arg0) {
        ISPDMServicePortType servicePort = getPort();
        ArrayOfIWebService result = new ArrayOfIWebService();
		
		if (servicePort != null) {
			result = servicePort.lookupService(convertSecurityPropertyToWS(arg0));
		}

		return convertWebServicesFromWS(result);
	}

	@Override
	public void persist_cache() {
        ISPDMServicePortType servicePort = getPort();
		
		if (servicePort != null) {
			servicePort.persistCache();
		}
	}

	@Override
	public void print_repository() {
        ISPDMServicePortType servicePort = getPort();
		
		if (servicePort != null) {
			servicePort.printRepository();
		}
	}

	@Override
	public void print_sp_entries() {
        ISPDMServicePortType servicePort = getPort();
		
		if (servicePort != null) {
			servicePort.printSpEntries();
		}
	}

	@Override
	public void print_ws_entries() {
        ISPDMServicePortType servicePort = getPort();
		
		if (servicePort != null) {
			servicePort.printWsEntries();
		}
	}

	@Override
	public void registerService(IWebService arg0, ISecurityProperty arg1) {
        ISPDMServicePortType servicePort = getPort();
		
		if (servicePort != null) {
			servicePort.registerService1(convertWebServiceToWS(arg0), convertSecurityPropertyToWS(arg1));
		}
	}

	@Override
	public void registerService(IWebService arg0, ISecurityDescriptor arg1) {
        ISPDMServicePortType servicePort = getPort();
		
		if (servicePort != null) {
			servicePort.registerService(convertWebServiceToWS(arg0), convertSecurityDescriptorToWS(arg1));
		}
	}

	//@Override
	public void setTestResults(String result) {
        ISPDMServicePortType servicePort = getPort();
		
		if (servicePort != null) {
			servicePort.setTestResults(result);
		}
	}
	
	//@Override
	public void startTest() {
        ISPDMServicePortType servicePort = getPort();
		
		if (servicePort != null) {
			servicePort.startTest();
		}
	}
	
	//@Override
	public String getTestResults() {
        ISPDMServicePortType servicePort = getPort();
        String result = null;
		
		if (servicePort != null) {
			result = servicePort.getTestResults();
		}

		return result;
	}
	
	@Override
	public void removeSeucrityProeprty(ISecurityProperty arg0) {
        ISPDMServicePortType servicePort = getPort();
		
		if (servicePort != null) {
			servicePort.removeSeucrityProeprty(convertSecurityPropertyToWS(arg0));
		}
	}

	@Override
	public void unregisterService(IWebService arg0) {
        ISPDMServicePortType servicePort = getPort();
		
		if (servicePort != null) {
			servicePort.unregisterService(convertWebServiceToWS(arg0));
		}
	}
}
