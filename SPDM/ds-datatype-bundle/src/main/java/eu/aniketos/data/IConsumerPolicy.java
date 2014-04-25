package eu.aniketos.data;

/**
 * IConsumerPolicy - an interface specifying a Consumer's Policy - a set of security
 * properties required/requested from services
 * 
 * @author David Lamb, LJMU
 * @revised by Bo Zhou LJMU Aug 2011 
 * @revised by Bernard Butler & Barry Mulcahy TSSG Aug 2011
 * @revised by Bo Zhou LJMU Feb 2013
 */
public interface IConsumerPolicy extends ISecurityDescriptor
{
	public abstract void setXML (String xmlContent);
	
	public abstract String getXML();

}
