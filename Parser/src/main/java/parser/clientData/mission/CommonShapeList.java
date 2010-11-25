//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.11.23 at 05:57:07 DE CET 
//


package parser.clientData.mission;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CommonShapeList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CommonShapeList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="flying_zones" type="{}FlyingZones" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sub_zones" type="{}SubZones" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommonShapeList", propOrder = {
    "flyingZones",
    "subZones"
})
public class CommonShapeList {

    @XmlElement(name = "flying_zones")
    protected List<FlyingZones> flyingZones;
    @XmlElement(name = "sub_zones")
    protected List<SubZones> subZones;

    /**
     * Gets the value of the flyingZones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flyingZones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlyingZones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FlyingZones }
     * 
     * 
     */
    public List<FlyingZones> getFlyingZones() {
        if (flyingZones == null) {
            flyingZones = new ArrayList<FlyingZones>();
        }
        return this.flyingZones;
    }

    /**
     * Gets the value of the subZones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subZones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubZones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubZones }
     * 
     * 
     */
    public List<SubZones> getSubZones() {
        if (subZones == null) {
            subZones = new ArrayList<SubZones>();
        }
        return this.subZones;
    }

}