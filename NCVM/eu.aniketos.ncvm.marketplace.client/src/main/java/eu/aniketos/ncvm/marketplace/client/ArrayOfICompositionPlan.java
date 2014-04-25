
package eu.aniketos.ncvm.marketplace.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfICompositionPlan complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfICompositionPlan">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ICompositionPlan" type="{http://data.aniketos.eu}ICompositionPlan" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfICompositionPlan", namespace = "http://data.aniketos.eu", propOrder = {
    "iCompositionPlan"
})
public class ArrayOfICompositionPlan {

    @XmlElement(name = "ICompositionPlan", nillable = true)
    protected List<ICompositionPlan> iCompositionPlan;

    /**
     * Gets the value of the iCompositionPlan property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the iCompositionPlan property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getICompositionPlan().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ICompositionPlan }
     * 
     * 
     */
    public List<ICompositionPlan> getICompositionPlan() {
        if (iCompositionPlan == null) {
            iCompositionPlan = new ArrayList<ICompositionPlan>();
        }
        return this.iCompositionPlan;
    }

}
