
package eu.aniketos.ncvm.marketplace.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfServiceOperation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfServiceOperation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceOperation" type="{http://marketplace.aniketos.eu}ServiceOperation" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfServiceOperation", namespace = "http://marketplace.aniketos.eu", propOrder = {
    "serviceOperation"
})
public class ArrayOfServiceOperation {

    @XmlElement(name = "ServiceOperation", nillable = true)
    protected List<ServiceOperation> serviceOperation;

    /**
     * Gets the value of the serviceOperation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceOperation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceOperation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceOperation }
     * 
     * 
     */
    public List<ServiceOperation> getServiceOperation() {
        if (serviceOperation == null) {
            serviceOperation = new ArrayList<ServiceOperation>();
        }
        return this.serviceOperation;
    }

}
