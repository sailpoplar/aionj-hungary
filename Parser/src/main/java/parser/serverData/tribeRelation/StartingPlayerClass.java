//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.11.08 at 07:07:21 DE CET 
//


package parser.serverData.tribeRelation;

import javax.xml.bind.annotation.XmlEnum;


/**
 * <p>Java class for startingPlayerClass.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="startingPlayerClass">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="WARRIOR"/>
 *     &lt;enumeration value="SCOUT"/>
 *     &lt;enumeration value="MAGE"/>
 *     &lt;enumeration value="PRIEST"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum StartingPlayerClass {

    WARRIOR,
    SCOUT,
    MAGE,
    PRIEST;

    public String value() {
        return name();
    }

    public static StartingPlayerClass fromValue(String v) {
        return valueOf(v);
    }

}
