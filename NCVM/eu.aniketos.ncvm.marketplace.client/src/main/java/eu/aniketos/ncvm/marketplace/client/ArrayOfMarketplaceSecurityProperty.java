
package eu.aniketos.ncvm.marketplace.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfMarketplaceSecurityProperty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMarketplaceSecurityProperty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MarketplaceSecurityProperty" type="{http://marketplace.aniketos.eu}MarketplaceSecurityProperty" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMarketplaceSecurityProperty", namespace = "http://marketplace.aniketos.eu", propOrder = {
    "marketplaceSecurityProperty"
})
public class ArrayOfMarketplaceSecurityProperty {

    @XmlElement(name = "MarketplaceSecurityProperty", nillable = true)
    protected List<MarketplaceSecurityProperty> marketplaceSecurityProperty;

    /**
     * Gets the value of the marketplaceSecurityProperty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the marketplaceSecurityProperty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMarketplaceSecurityProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MarketplaceSecurityProperty }
     * 
     * 
     */
    public List<MarketplaceSecurityProperty> getMarketplaceSecurityProperty() {
        if (marketplaceSecurityProperty == null) {
            marketplaceSecurityProperty = new ArrayList<MarketplaceSecurityProperty>();
        }
        return this.marketplaceSecurityProperty;
    }

}
