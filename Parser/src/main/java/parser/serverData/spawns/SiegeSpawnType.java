//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.11.29 at 08:46:27 DE CET 
//


package parser.serverData.spawns;

import javax.xml.bind.annotation.XmlEnum;


/**
 * <p>Java class for SiegeSpawnType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SiegeSpawnType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PEACE"/>
 *     &lt;enumeration value="GUARD"/>
 *     &lt;enumeration value="ARTIFACT"/>
 *     &lt;enumeration value="PROTECTOR"/>
 *     &lt;enumeration value="MINE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum SiegeSpawnType {

    PEACE,
    GUARD,
    ARTIFACT,
    PROTECTOR,
    MINE;

    public String value() {
        return name();
    }

    public static SiegeSpawnType fromValue(String v) {
        return valueOf(v);
    }

}
