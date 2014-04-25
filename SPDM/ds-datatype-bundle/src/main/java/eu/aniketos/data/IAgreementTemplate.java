package eu.aniketos.data;

import java.util.List;


/**
 * 
 * IAgreementTemplate - an interface specifying a Agreement Template - that is a
 * proposed contract (set of properties) offered by a service.
 * 
 * @author David Lamb, LJMU
 * @revised by Bo Zhou LJMU Aug 2011 
 * @revised by Bernard Butler & Barry Mulcahy TSSG Aug 2011
 * @revise by Bo Zhou LJMU Feb 2013
 */
public interface IAgreementTemplate extends ISecurityDescriptor
{
	public abstract String getAgreementTemplateID();

	public abstract void setAgreementTemplateID(String agreementTemplateID);


	public abstract void setSecurityProperties(List<ISecurityProperty> securityProperties);
	
	public abstract void setXML (String xmlContent);
	
	public abstract String getXML();
	
	
}
