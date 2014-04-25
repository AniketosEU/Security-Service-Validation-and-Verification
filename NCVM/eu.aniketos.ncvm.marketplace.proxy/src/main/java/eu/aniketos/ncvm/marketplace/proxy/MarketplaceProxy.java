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

package eu.aniketos.ncvm.marketplace.proxy;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import eu.aniketos.data.ICompositionPlan;
import eu.aniketos.data.IConsumerPolicy;
import eu.aniketos.data.ISecurityProperty;
import eu.aniketos.data.SPState;
import eu.aniketos.marketplace.CompositionPlan;
import eu.aniketos.marketplace.IMarketplace;
import eu.aniketos.marketplace.MarketplaceAnnouncement;
import eu.aniketos.marketplace.MarketplaceSearchParams;
import eu.aniketos.marketplace.MarketplaceSecurityDescriptor;
import eu.aniketos.marketplace.MarketplaceSecurityProperty;
import eu.aniketos.marketplace.ServiceDescriptor;
import eu.aniketos.marketplace.Tag;
import eu.aniketos.ncvm.marketplace.client.ArrayOfAnyType;
import eu.aniketos.ncvm.marketplace.client.ArrayOfArrayOfAnyType;
import eu.aniketos.ncvm.marketplace.client.ArrayOfBoolean;
import eu.aniketos.ncvm.marketplace.client.ArrayOfICompositionPlan;
import eu.aniketos.ncvm.marketplace.client.ArrayOfServiceDescriptor;
import eu.aniketos.ncvm.marketplace.client.ArrayOfString;
import eu.aniketos.ncvm.marketplace.client.IMarketplaceClient;
import eu.aniketos.ncvm.marketplace.client.IMarketplacePortType;
import eu.aniketos.ncvm.marketplace.client.Principal;
import eu.aniketos.ncvm.marketplace.client.PublicKey;
import eu.aniketos.ncvm.marketplace.client.ServiceOperation;
import eu.aniketos.ncvm.marketplace.client.X500Principal;
import eu.aniketos.ncvm.userinterface.proxy.INCVMProxy;

public class MarketplaceProxy implements IMarketplace, INCVMProxy {
    private URL wsdlURL = IMarketplaceClient.WSDL_LOCATION;
	private final static int timeout = 1 * 60 * 1000; // in milliseconds

	@Override
	public void setURL(String wsdlURL) {
    	try {
			this.wsdlURL = new URL(wsdlURL);
		} catch (MalformedURLException e) {
			System.out.println("Failed to set Marketplace URL to " + wsdlURL);
		}
	}

	private ArrayOfString convertStringArrayToWS(String[] stringArray) {
		eu.aniketos.ncvm.marketplace.client.ObjectFactory factory = new eu.aniketos.ncvm.marketplace.client.ObjectFactory();
		ArrayOfString result = factory.createArrayOfString();
		
		result.getString().clear();
		if (stringArray != null) {
			for (String string : stringArray) {
				result.getString().add(string);
			}
		}
		
		return result;
	}
	
	private ArrayOfString convertStringListToWS(List<String> stringList) {
		eu.aniketos.ncvm.marketplace.client.ObjectFactory factory = new eu.aniketos.ncvm.marketplace.client.ObjectFactory();
		ArrayOfString result = factory.createArrayOfString();
		
		result.getString().clear();
		if (stringList != null) {
			for (String string : stringList) {
				result.getString().add(string);
			}
		}
		
		return result;
	}
	
	private ArrayOfArrayOfAnyType convertCollectionListToWS(Collection<List<?>> collectionList) {
		eu.aniketos.ncvm.marketplace.client.ObjectFactory factory = new eu.aniketos.ncvm.marketplace.client.ObjectFactory();
		ArrayOfArrayOfAnyType result = factory.createArrayOfArrayOfAnyType();
		
		result.getArrayOfAnyType().clear();
		if (collectionList != null) {
			for (List<?> list : collectionList) {
				ArrayOfAnyType resultList = factory.createArrayOfAnyType();
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
	
	private ArrayOfBoolean convertyBooleanArrayToWS(boolean[] booleanArray) {
		eu.aniketos.ncvm.marketplace.client.ObjectFactory factory = new eu.aniketos.ncvm.marketplace.client.ObjectFactory();
		ArrayOfBoolean result = factory.createArrayOfBoolean();
		
		result.getBoolean().clear();
		if (booleanArray != null) {
			for (boolean item : booleanArray) {
				result.getBoolean().add(item);
			}
		}
		
		return result;
	}
	
	private Tag convertTagFromWS(eu.aniketos.ncvm.marketplace.client.Tag tag) {
		Tag result = new Tag();
		
		result.setOccurences(tag.getOccurences());
		result.setTag(tag.getTag().getValue());
		
		return result;
	}
	
	private List<Tag> convertTagsFromWS(eu.aniketos.ncvm.marketplace.client.ArrayOfTag tags) {
		List<Tag> result = new Vector<Tag>();
		result.clear();
		
		Iterator<eu.aniketos.ncvm.marketplace.client.Tag> iter = tags.getTag().iterator();
		while (iter.hasNext()) {
			result.add(convertTagFromWS(iter.next()));
		}

		return result;
	}
	
	private Principal convertPrincipalToWS(java.security.Principal principal) {
		eu.aniketos.ncvm.marketplace.client.ObjectFactory factory = new eu.aniketos.ncvm.marketplace.client.ObjectFactory();
		Principal result;
		
		result = factory.createPrincipal();
		
		result.setName(factory.createPrincipalName(principal.getName()));
		
		return result;
	}
	
	private eu.aniketos.ncvm.marketplace.client.ServiceOperation convertServiceOperationToWS(eu.aniketos.marketplace.ServiceOperation operation) {
		eu.aniketos.ncvm.marketplace.client.ObjectFactory factory = new eu.aniketos.ncvm.marketplace.client.ObjectFactory();
		eu.aniketos.ncvm.marketplace.client.ServiceOperation result = factory.createServiceOperation();

		result.setMethod(factory.createServiceOperationMethod(operation.getMethod()));
		result.setTag(factory.createServiceOperationTag(operation.getTag()));
		
		return result;
	}
	
	private eu.aniketos.marketplace.ServiceOperation convertServiceOperationFromWS(ServiceOperation operation) {
		eu.aniketos.marketplace.ServiceOperation result = new eu.aniketos.marketplace.ServiceOperation();
		
		result.setMethod(operation.getMethod().getValue());
		result.setTag(operation.getTag().getValue());
		
		return result;
	}
	
	private eu.aniketos.ncvm.marketplace.client.ServiceDescriptor convertServiceDescriptorToWS(ServiceDescriptor descriptor) {
		eu.aniketos.ncvm.marketplace.client.ObjectFactory factory = new eu.aniketos.ncvm.marketplace.client.ObjectFactory();
		eu.aniketos.ncvm.marketplace.client.ServiceDescriptor result = factory.createServiceDescriptor();

		result.setId(factory.createServiceDescriptorId(descriptor.getId()));
		result.setName(factory.createServiceDescriptorName(descriptor.getName()));
		result.setDescription(factory.createServiceDescriptorDescription(descriptor.getDescription()));
		result.setBinding(factory.createServiceDescriptorBinding(descriptor.getBinding()));
		result.setProviderName(factory.createServiceDescriptorProviderName(descriptor.getProviderName()));

		eu.aniketos.ncvm.marketplace.client.ArrayOfServiceOperation operations = factory.createArrayOfServiceOperation();
		operations.getServiceOperation().clear();
		if (descriptor.getOperations() != null) {
			for (eu.aniketos.marketplace.ServiceOperation operation : descriptor.getOperations()) {
				operations.getServiceOperation().add(convertServiceOperationToWS(operation));
			}
		}
		result.setOperations(factory.createServiceDescriptorOperations(operations));
		result.setTestable(descriptor.isTestable());
		
		return result;
	}
	
	private ServiceDescriptor convertServiceDescriptorFromWS(eu.aniketos.ncvm.marketplace.client.ServiceDescriptor descriptor) {
		ServiceDescriptor result = new ServiceDescriptor();
		
		result.setBinding(descriptor.getBinding().getValue());
		result.setDescription(descriptor.getDescription().getValue());
		result.setId(descriptor.getId().getValue());
		result.setName(descriptor.getName().getValue());
		result.setProviderName(descriptor.getProviderName().getValue());
		result.setTestable(descriptor.isTestable());

		eu.aniketos.marketplace.ServiceOperation[] operations = null;
		if ((descriptor.getOperations() != null) && (descriptor.getOperations().getValue().getServiceOperation() != null)) {
			int opNum = descriptor.getOperations().getValue().getServiceOperation().size();
			operations = new eu.aniketos.marketplace.ServiceOperation[opNum];
			List<eu.aniketos.ncvm.marketplace.client.ServiceOperation> operationsIn = descriptor.getOperations().getValue().getServiceOperation();
			for (int op = 0; op < opNum; op++) {
				operations[op] = convertServiceOperationFromWS(operationsIn.get(op));
			}
		}
		
		result.setOperations(operations);
		
		return result;
	}
//	
//	private eu.aniketos.ncvm.marketplace.client.SecurityDescriptor convertSecurityDescriptorToWS(SecurityDescriptor descriptor) {
//		eu.aniketos.ncvm.marketplace.client.ObjectFactory factory = new eu.aniketos.ncvm.marketplace.client.ObjectFactory();
//		eu.aniketos.ncvm.marketplace.client.SecurityDescriptor result = factory.createSecurityDescriptor();
//
//		result.setConspec(factory.createSecurityDescriptorConspec(descriptor.getConspec()));
//		
//		return result;
//	}
//	
	private MarketplaceSecurityDescriptor convertMarketplaceSecurityDescriptorFromWS(eu.aniketos.ncvm.marketplace.client.MarketplaceSecurityDescriptor descriptor) {
		MarketplaceSecurityDescriptor result = new MarketplaceSecurityDescriptor();

		List<MarketplaceSecurityProperty> properties = new Vector<MarketplaceSecurityProperty>();
		properties.clear();

		if ((descriptor != null) && (descriptor.getSecurityProperties() != null)) {
			List<eu.aniketos.ncvm.marketplace.client.MarketplaceSecurityProperty> propertiesIn = descriptor.getSecurityProperties().getValue().getMarketplaceSecurityProperty();
			if (propertiesIn != null) {
				for (eu.aniketos.ncvm.marketplace.client.MarketplaceSecurityProperty property : propertiesIn) {
					properties.add(convertMarketplaceSecurtyPropertyFromWS(property));
				}
			}
		}

		result.setSecurityProperties(properties);

		return result;
	}
	
	private eu.aniketos.ncvm.marketplace.client.ICompositionPlan convertCompositionPlanToWS(ICompositionPlan service) {
		eu.aniketos.ncvm.marketplace.client.ObjectFactory factory = new eu.aniketos.ncvm.marketplace.client.ObjectFactory();
		eu.aniketos.ncvm.marketplace.client.ICompositionPlan result = factory.createICompositionPlan();
		
		result.setActivitiFile(factory.createICompositionPlanActivitiFile(service.getActivitiFile()));
		result.setBPMNXML(factory.createICompositionPlanBPMNXML(service.getBPMNXML()));
		result.setCompositionPlanID(factory.createICompositionPlanCompositionPlanID(service.getCompositionPlanID()));
		
		return result;
	}
	
	private SPState convertStateFromWS(eu.aniketos.ncvm.marketplace.client.SPState state) {
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

	private eu.aniketos.ncvm.marketplace.client.SPState convertStateToWS(SPState state) {
		eu.aniketos.ncvm.marketplace.client.SPState result = eu.aniketos.ncvm.marketplace.client.SPState.BIND;
		
		switch (state) {
		case Bind:
			result = eu.aniketos.ncvm.marketplace.client.SPState.BIND;
			break;
		case Signed:
			result = eu.aniketos.ncvm.marketplace.client.SPState.SIGNED;
			break;
		case UnBind:
			result = eu.aniketos.ncvm.marketplace.client.SPState.UN_BIND;
			break;
		case Verified:
			result = eu.aniketos.ncvm.marketplace.client.SPState.VERIFIED;
			break;
		default:
			result = eu.aniketos.ncvm.marketplace.client.SPState.valueOf(state.name());
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
	
	private eu.aniketos.ncvm.marketplace.client.MarketplaceSecurityProperty convertMarketplaceSecurtyPropertyToWS(eu.aniketos.marketplace.MarketplaceSecurityProperty property) {
		eu.aniketos.ncvm.marketplace.client.ObjectFactory factory = new eu.aniketos.ncvm.marketplace.client.ObjectFactory();
		eu.aniketos.ncvm.marketplace.client.MarketplaceSecurityProperty result;

		result = factory.createMarketplaceSecurityProperty();
		
		try {
			result.setFreshness(convertDateToWS(property.getFreshness()));
		} catch (DatatypeConfigurationException e) {
			System.out.printf("Failed to convert date: " + e.getLocalizedMessage());
		}

		result.setPropertyID(factory.createMarketplaceSecurityPropertyPropertyID(property.getPropertyID()));
		result.setPropertyValue(factory.createMarketplaceSecurityPropertyPropertyValue(property.getPropertyValue()));
		result.setState(factory.createMarketplaceSecurityPropertyState(convertStateToWS(property.getState())));
		result.setConspec(factory.createMarketplaceSecurityPropertyConspec(property.getConspec()));

		return result;
	}
	
	private eu.aniketos.marketplace.MarketplaceSecurityProperty convertMarketplaceSecurtyPropertyFromWS(eu.aniketos.ncvm.marketplace.client.MarketplaceSecurityProperty property) {
		eu.aniketos.marketplace.MarketplaceSecurityProperty result = new eu.aniketos.marketplace.MarketplaceSecurityProperty();

		XMLGregorianCalendar freshness = property.getFreshness();
		if (freshness != null) {
			result.setFreshness(freshness.toGregorianCalendar().getTime());
		}
		result.setPropertyID(property.getPropertyID().getValue());
		result.setPropertyValue(property.getPropertyValue().getValue());
		result.setState(convertStateFromWS(property.getState().getValue()));
		result.setConspec(property.getConspec().getValue());
		
		return result;
	}
	
	private eu.aniketos.ncvm.marketplace.client.MarketplaceSecurityDescriptor convertMarketplaceSecurityDescriptorToWS(eu.aniketos.marketplace.MarketplaceSecurityDescriptor descriptor) {
		eu.aniketos.ncvm.marketplace.client.ObjectFactory factory = new eu.aniketos.ncvm.marketplace.client.ObjectFactory();
		eu.aniketos.ncvm.marketplace.client.MarketplaceSecurityDescriptor result;

		result = factory.createMarketplaceSecurityDescriptor();
		
		eu.aniketos.ncvm.marketplace.client.ArrayOfMarketplaceSecurityProperty properties = factory.createArrayOfMarketplaceSecurityProperty();
		properties.getMarketplaceSecurityProperty().clear();
		if (descriptor.getSecurityProperties() != null) {
			for (eu.aniketos.marketplace.MarketplaceSecurityProperty property : descriptor.getSecurityProperties()) {
				properties.getMarketplaceSecurityProperty().add(convertMarketplaceSecurtyPropertyToWS(property));
			}
		}

		result.setSecurityProperties(factory.createMarketplaceSecurityDescriptorSecurityProperties(properties));
		
		return result;
	}
	
	private eu.aniketos.ncvm.marketplace.client.MarketplaceAnnouncement convertMarketplaceAnnouncementToWS(MarketplaceAnnouncement announcement) {
		eu.aniketos.ncvm.marketplace.client.ObjectFactory factory = new eu.aniketos.ncvm.marketplace.client.ObjectFactory();
		eu.aniketos.ncvm.marketplace.client.MarketplaceAnnouncement result;
		
		result = factory.createMarketplaceAnnouncement();

		result.setRegistry(factory.createMarketplaceAnnouncementRegistry(announcement.getRegistry()));
		result.setSender(factory.createMarketplaceAnnouncementSender(announcement.getSender()));
		result.setServiceDescriptor(factory.createMarketplaceAnnouncementServiceDescriptor(convertServiceDescriptorToWS(announcement.getServiceDescriptor())));
		result.setSecurityDescriptor(factory.createMarketplaceAnnouncementSecurityDescriptor(convertMarketplaceSecurityDescriptorToWS(announcement.getSecurityDescriptor())));

		eu.aniketos.ncvm.marketplace.client.ArrayOfICompositionPlan compositionPlans = factory.createArrayOfICompositionPlan();
		compositionPlans.getICompositionPlan().clear();
		if (announcement.getCompositionPlans() != null) {
			for (ICompositionPlan compositionPlan : announcement.getCompositionPlans()) {
				compositionPlans.getICompositionPlan().add(convertCompositionPlanToWS(compositionPlan));
			}
		}

		result.setCompositionPlans(factory.createMarketplaceAnnouncementCompositionPlans(compositionPlans));		
		result.setRules(factory.createMarketplaceAnnouncementRules(announcement.getRegistry()));

		return result;
	}
	
	private eu.aniketos.ncvm.marketplace.client.MarketplaceSearchParams convertMarketplaceSearchParamsToWS (MarketplaceSearchParams params) {
		eu.aniketos.ncvm.marketplace.client.ObjectFactory factory = new eu.aniketos.ncvm.marketplace.client.ObjectFactory();
		eu.aniketos.ncvm.marketplace.client.MarketplaceSearchParams result;
		
		result = factory.createMarketplaceSearchParams();

		result.setName(factory.createMarketplaceSearchParamsName(params.getName()));
		result.setUrl(factory.createMarketplaceSearchParamsUrl(params.getUrl()));
		result.setDescription(factory.createMarketplaceSearchParamsDescription(params.getDescription()));
		result.setTags(factory.createMarketplaceSearchParamsTags(convertStringArrayToWS(params.getTags())));
		result.setOperations(factory.createMarketplaceSearchParamsOperations(convertStringArrayToWS(params.getOperations())));
		result.setOwner(factory.createMarketplaceSearchParamsOwner(params.getOwner()));
		result.setSecurityProperty(factory.createMarketplaceSearchParamsSecurityProperty(params.getSecurityProperty()));
		
		return result;
	}

	private List<ServiceDescriptor> convertServiceDescriptorsFromWS(ArrayOfServiceDescriptor descriptors) {
		List<ServiceDescriptor> result = new Vector<ServiceDescriptor>();
		result.clear();
		
		Iterator<eu.aniketos.ncvm.marketplace.client.ServiceDescriptor> iter = descriptors.getServiceDescriptor().iterator();
		while (iter.hasNext()) {
			result.add(convertServiceDescriptorFromWS(iter.next()));
		}
		
		return result;
	}

	private eu.aniketos.ncvm.marketplace.client.ISecurityProperty convertSecurityPropertyToWS(ISecurityProperty property) {
		eu.aniketos.ncvm.marketplace.client.ISecurityProperty result = new eu.aniketos.ncvm.marketplace.client.ISecurityProperty();
		eu.aniketos.ncvm.marketplace.client.ObjectFactory factory = new eu.aniketos.ncvm.marketplace.client.ObjectFactory();

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
	
	public X500Principal convertX500PrincipalToWS(javax.security.auth.x500.X500Principal principal) {
		eu.aniketos.ncvm.marketplace.client.ObjectFactory factory = new eu.aniketos.ncvm.marketplace.client.ObjectFactory();
		eu.aniketos.ncvm.marketplace.client.X500Principal result;

		result = factory.createX500Principal();
		
		result.setEncoded(factory.createCertificateEncoded(principal.getEncoded()));
		result.setName(factory.createX500PrincipalName(principal.getName()));
		
		return result;
	}
	
	private PublicKey convertPublicKeyToWS(java.security.PublicKey publicKey) {
		eu.aniketos.ncvm.marketplace.client.ObjectFactory factory = new eu.aniketos.ncvm.marketplace.client.ObjectFactory();
		PublicKey result;
		
		result = factory.createPublicKey();
		
		result.setAlgorithm(factory.createKeyAlgorithm(publicKey.getAlgorithm()));
		result.setEncoded(factory.createKeyEncoded(publicKey.getEncoded()));
		result.setFormat(factory.createKeyFormat(publicKey.getFormat()));
		
		return result;
	}
	
	private eu.aniketos.ncvm.marketplace.client.X509Certificate convertX509CertificateToWS (X509Certificate certificate) throws CertificateEncodingException, CertificateParsingException, DatatypeConfigurationException {
		eu.aniketos.ncvm.marketplace.client.ObjectFactory factory = new eu.aniketos.ncvm.marketplace.client.ObjectFactory();
		eu.aniketos.ncvm.marketplace.client.X509Certificate result = null;

		if (certificate != null) {
			result = new eu.aniketos.ncvm.marketplace.proxy.X509Certificate();
			
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
	
	private eu.aniketos.ncvm.marketplace.client.ArrayOfISecurityProperty convertISecurityPropertyListToWS (List<ISecurityProperty> properties) {
		eu.aniketos.ncvm.marketplace.client.ObjectFactory factory = new eu.aniketos.ncvm.marketplace.client.ObjectFactory();
		eu.aniketos.ncvm.marketplace.client.ArrayOfISecurityProperty result;
		
		result = factory.createArrayOfISecurityProperty();
		
		if (properties != null) {
			for (ISecurityProperty property : properties) {
				result.getISecurityProperty().add(convertSecurityPropertyToWS(property));
			}
		}
		
		return result;
	}
	
	private eu.aniketos.ncvm.marketplace.client.IConsumerPolicy convertIConsumerPolicyToWS(IConsumerPolicy conspec) {
		eu.aniketos.ncvm.marketplace.client.ObjectFactory factory = new eu.aniketos.ncvm.marketplace.client.ObjectFactory();
		eu.aniketos.ncvm.marketplace.client.IConsumerPolicy result;
		
		result = factory.createIConsumerPolicy();
		
		result.setXML(factory.createIConsumerPolicyXML(conspec.getXML()));
		result.setXmlContents(factory.createIConsumerPolicyXmlContents(convertStringArrayToWS(conspec.getXmlContents())));
		result.setProperties(factory.createISecurityDescriptorProperties(convertISecurityPropertyListToWS(conspec.getProperties())));
		
		return result;
	}
	
	private ICompositionPlan convertCompositionPlanFromWS(eu.aniketos.ncvm.marketplace.client.ICompositionPlan compositionPlan) {
		ICompositionPlan result = new CompositionPlan();
		
		result.setActivitiFile(compositionPlan.getActivitiFile().getValue());
		result.setBPMNXML(compositionPlan.getBPMNXML().getValue());
		result.setCompositionPlanID(compositionPlan.getCompositionPlanID().getValue());

		return result;
	}
	
	
	private ICompositionPlan[] convertCompositionPlansFromWS(ArrayOfICompositionPlan compositionPlans) {
		ICompositionPlan[] result = null;
		if ((compositionPlans != null) && (compositionPlans.getICompositionPlan() != null)) {
			int planNum = compositionPlans.getICompositionPlan().size();
			result = new ICompositionPlan[planNum];
	
			for (int plan = 0; plan < planNum; plan++) {
				result[plan] = convertCompositionPlanFromWS(compositionPlans.getICompositionPlan().get(plan));
			}
		}
		
		return result;
	}
	
	private IMarketplacePortType getPort() {
	    QName SERVICE_NAME = new QName("http://marketplace.aniketos.eu/", "IMarketplace");

	    IMarketplacePortType servicePort = null;
	    IMarketplaceClient service = new IMarketplaceClient(wsdlURL, SERVICE_NAME);
        
		try {
	        servicePort = service.getIMarketplacePort();

			Map<String, Object> requestContext = ((BindingProvider)servicePort).getRequestContext();
			requestContext.put("javax.xml.ws.client.receiveTimeout", Integer.toString(timeout));
		} catch (Exception exception) {
			System.out.println("Marketplace Proxy exception: " + exception.getMessage());
		}
		
		return servicePort;
	}

	@Override
	public String announceService(String authToken, MarketplaceAnnouncement announcement) {
		IMarketplacePortType servicePort = getPort();
		String result = null;

		if (servicePort != null) {
			result = servicePort.announceService(authToken, convertMarketplaceAnnouncementToWS(announcement));
		}

		return result;
	}

	@Override
	public List<ServiceDescriptor> discoverService(String authToken, MarketplaceSearchParams params) {
		IMarketplacePortType servicePort = getPort();
		List<ServiceDescriptor> result = new Vector<ServiceDescriptor>();
		result.clear();
		
		if (servicePort != null) {
			result = convertServiceDescriptorsFromWS (servicePort.discoverService(authToken, convertMarketplaceSearchParamsToWS(params)));
		}
		
		return result;
	}

	@Override
	public MarketplaceSecurityDescriptor getSecurityDescriptor(String authToken, String serviceId) {
		IMarketplacePortType servicePort = getPort();
		MarketplaceSecurityDescriptor result = new MarketplaceSecurityDescriptor();
		
		if (servicePort != null) {
			result = convertMarketplaceSecurityDescriptorFromWS(servicePort.getSecurityDescriptor(authToken, serviceId));
		}
		
		return result;
	}

	@Override
	public String getBpmnDiagram(String serviceId) {
		IMarketplacePortType servicePort = getPort();
		String result = null;

		if (servicePort != null) {
			result = servicePort.getBpmnDiagram(serviceId);
		}

		return result;
	}

	@Override
	public boolean updateSecurityDescription(String authToken, String serviceId, IConsumerPolicy conspec) {
		IMarketplacePortType servicePort = getPort();
		boolean result = false;

		if (servicePort != null) {
			result = servicePort.updateSecurityDescription(authToken, serviceId, convertIConsumerPolicyToWS(conspec));
		}

		return result;
	}

	@Override
	public boolean updateBpmnDiagram(String authToken, String serviceId, String bpmnDiagram) {
		IMarketplacePortType servicePort = getPort();
		boolean result = false;

		if (servicePort != null) {
			result = servicePort.updateBpmnDiagram(authToken, serviceId, bpmnDiagram);
		}

		return result;
	}

	@Override
	public void provide(String authToken, String serviceId) {
		IMarketplacePortType servicePort = getPort();

		if (servicePort != null) {
			servicePort.provide(authToken, serviceId);
		}
	}

	@Override
	public void notProvide(String authToken, String serviceId) {
		IMarketplacePortType servicePort = getPort();

		if (servicePort != null) {
			servicePort.notProvide(authToken, serviceId);
		}
	}

	@Override
	public String getAuthToken(String username, String password) {
		IMarketplacePortType servicePort = getPort();
		String result = null;

		if (servicePort != null) {
			result = servicePort.getAuthToken(username, password);
		}

		return result;
	}

	@Override
	public boolean deleteService(String authToken, String serviceId) {
		IMarketplacePortType servicePort = getPort();
		boolean result = false;

		if (servicePort != null) {
			result = servicePort.deleteService(authToken, serviceId);
		}

		return result;
	}

	@Override
	public List<Tag> getTags(String authToken) {
		IMarketplacePortType servicePort = getPort();
		List<Tag> result = new Vector<Tag>();
		result.clear();

		if (servicePort != null) {
			result = convertTagsFromWS(servicePort.getTags(authToken));
		}

		return result;
	}

	@Override
	public boolean isTestable(String serviceId) {
		IMarketplacePortType servicePort = getPort();
		boolean result = false;

		if (servicePort != null) {
			result = servicePort.isTestable(serviceId);
		}

		return result;
	}

	@Override
	public boolean registerSource(String serviceId, String sourceUrl) {
		IMarketplacePortType servicePort = getPort();
		boolean result = false;

		if (servicePort != null) {
			result = servicePort.registerSource(serviceId, sourceUrl);
		}

		return result;
	}

	@Override
	public String getSource(String serviceId) {
		IMarketplacePortType servicePort = getPort();
		String result = null;

		if (servicePort != null) {
			result = servicePort.getSource(serviceId);
		}

		return result;
	}

	@Override
	public ICompositionPlan[] getCompositionPlans(String serviceId) {
		IMarketplacePortType servicePort = getPort();
		ICompositionPlan[] result = null;

		if (servicePort != null) {
			result = convertCompositionPlansFromWS(servicePort.getCompositionPlans(serviceId));
		}

		return result;
	}

	@Override
	public String getRules(String serviceName) {
		IMarketplacePortType servicePort = getPort();
		String result = "";
		
		if (servicePort != null) {
			result = servicePort.getRules(serviceName);
		}
		
		return result;
	}
}
