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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import eu.aniketos.scpm.ServiceQuery;
import eu.aniketos.scpm.marketplace.client.ArrayOfServiceDescriptor;
import eu.aniketos.scpm.marketplace.client.ArrayOfString;
import eu.aniketos.scpm.marketplace.client.IMarketplace;
import eu.aniketos.scpm.marketplace.client.IMarketplacePortType;
import eu.aniketos.scpm.marketplace.client.MarketplaceSearchParams;
import eu.aniketos.scpm.marketplace.client.ObjectFactory;

public class MarketplaceClient {
	private final static int timeout = 1 * 60 * 1000; // in milliseconds
	public static eu.aniketos.scpm.marketplace.client.ArrayOfServiceDescriptor discoverServices(ServiceQuery serviceQuery, String addressMarketplace){

		QName serviceName = new QName("http://marketplace.aniketos.eu/", "IMarketplace");
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = eu.aniketos.scpm.marketplace.client.IMarketplace.class.getResource(".");
			url = new URL(baseUrl, addressMarketplace);
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		}

		//Integration with Aniketos Marketplace

		
		eu.aniketos.scpm.marketplace.client.IMarketplace marketplaceService = new eu.aniketos.scpm.marketplace.client.IMarketplace(url,serviceName);
		eu.aniketos.scpm.marketplace.client.IMarketplacePortType marketplace = null;
        try {
        	marketplace = marketplaceService.getIMarketplacePort();;

			Map<String, Object> requestContext = ((BindingProvider)marketplace).getRequestContext();
			requestContext.put("javax.xml.ws.client.receiveTimeout", Integer.toString(timeout));
		} catch (Exception exception) {
			System.out.println("Marketplace Proxy exception: " + exception.getMessage());
		}
        eu.aniketos.scpm.marketplace.client.ObjectFactory o = new eu.aniketos.scpm.marketplace.client.ObjectFactory();
		eu.aniketos.scpm.marketplace.client.MarketplaceSearchParams searchParams = new eu.aniketos.scpm.marketplace.client.MarketplaceSearchParams();
		eu.aniketos.scpm.marketplace.client.ArrayOfString paramArrayOfString = o.createArrayOfString();
		eu.aniketos.scpm.marketplace.client.ArrayOfServiceDescriptor serviceDescriptors = null;
		if (serviceQuery != null)
		{
        String types = serviceQuery.getType();
        List<String> list = paramArrayOfString.getString();

        StringTokenizer st = new StringTokenizer(types);
        while(st.hasMoreTokens()){
                list.add(st.nextToken());
        }
        JAXBElement<eu.aniketos.scpm.marketplace.client.ArrayOfString> array = o.createMarketplaceSearchParamsTags(paramArrayOfString);
        searchParams.setTags(array);

        serviceDescriptors = marketplace.discoverService(null, searchParams);

		}
		else  serviceDescriptors = marketplace.discoverService(null, searchParams);
		
        return serviceDescriptors;

		
	}

}
