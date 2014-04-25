
package eu.aniketos.ncvm.marketplace.client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SPState.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SPState">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="UnBind"/>
 *     &lt;enumeration value="Bind"/>
 *     &lt;enumeration value="Verified"/>
 *     &lt;enumeration value="Signed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SPState", namespace = "http://data.aniketos.eu")
@XmlEnum
public enum SPState {

    @XmlEnumValue("UnBind")
    UN_BIND("UnBind"),
    @XmlEnumValue("Bind")
    BIND("Bind"),
    @XmlEnumValue("Verified")
    VERIFIED("Verified"),
    @XmlEnumValue("Signed")
    SIGNED("Signed");
    private final String value;

    SPState(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SPState fromValue(String v) {
        for (SPState c: SPState.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
