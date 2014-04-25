
package eu.aniketos.ncvm.marketplace.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfServiceDescriptor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfServiceDescriptor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceDescriptor" type="{http://marketplace.aniketos.eu}ServiceDescriptor" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfServiceDescriptor", namespace = "http://marketplace.aniketos.eu", propOrder = {
    "serviceDescriptor"
})
public class ArrayOfServiceDescriptor {

    @XmlElement(name = "ServiceDescriptor", nillable = true)
    protected List<ServiceDescriptor> serviceDescriptor;

    /**
     * Gets the value of the serviceDescriptor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceDescriptor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceDescriptor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceDescriptor }
     * 
     * 
     */
    public List<ServiceDescriptor> getServiceDescriptor() {
        if (serviceDescriptor == null) {
            serviceDescriptor = new ArrayList<ServiceDescriptor>();
        }
        return this.serviceDescriptor;
    }

}
