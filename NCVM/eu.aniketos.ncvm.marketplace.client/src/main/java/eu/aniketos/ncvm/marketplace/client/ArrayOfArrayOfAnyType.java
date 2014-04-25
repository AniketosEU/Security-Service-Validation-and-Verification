
package eu.aniketos.ncvm.marketplace.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfArrayOfAnyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfArrayOfAnyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ArrayOfAnyType" type="{http://cxf.apache.org/arrays}ArrayOfAnyType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfArrayOfAnyType", namespace = "http://cxf.apache.org/arrays", propOrder = {
    "arrayOfAnyType"
})
public class ArrayOfArrayOfAnyType {

    @XmlElement(name = "ArrayOfAnyType", nillable = true)
    protected List<ArrayOfAnyType> arrayOfAnyType;

    /**
     * Gets the value of the arrayOfAnyType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the arrayOfAnyType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArrayOfAnyType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ArrayOfAnyType }
     * 
     * 
     */
    public List<ArrayOfAnyType> getArrayOfAnyType() {
        if (arrayOfAnyType == null) {
            arrayOfAnyType = new ArrayList<ArrayOfAnyType>();
        }
        return this.arrayOfAnyType;
    }

}
