
package eu.aniketos.ncvm.spdm.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfIWebService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfIWebService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IWebService" type="{http://api.ds.spdm.aniketos.eu}IWebService" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfIWebService", namespace = "http://api.ds.spdm.aniketos.eu", propOrder = {
    "iWebService"
})
public class ArrayOfIWebService {

    @XmlElement(name = "IWebService", nillable = true)
    protected List<IWebService> iWebService;

    /**
     * Gets the value of the iWebService property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the iWebService property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIWebService().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IWebService }
     * 
     * 
     */
    public List<IWebService> getIWebService() {
        if (iWebService == null) {
            iWebService = new ArrayList<IWebService>();
        }
        return this.iWebService;
    }

}
