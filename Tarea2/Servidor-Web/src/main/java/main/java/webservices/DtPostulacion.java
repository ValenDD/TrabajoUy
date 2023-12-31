
package main.java.webservices;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtPostulacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtPostulacion">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nicknamePostulante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descripMotivacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="cvReducido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nombreOferta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="linkVideo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaPostulacionString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtPostulacion", propOrder = {
    "nicknamePostulante",
    "descripMotivacion",
    "cvReducido",
    "nombreOferta",
    "linkVideo",
    "fechaPostulacionString"
})
public class DtPostulacion {

    protected String nicknamePostulante;
    protected String descripMotivacion;
    protected String cvReducido;
    protected String nombreOferta;
    protected String linkVideo;
    protected String fechaPostulacionString;

    /**
     * Obtiene el valor de la propiedad nicknamePostulante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNicknamePostulante() {
        return nicknamePostulante;
    }

    /**
     * Define el valor de la propiedad nicknamePostulante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNicknamePostulante(String value) {
        this.nicknamePostulante = value;
    }

    /**
     * Obtiene el valor de la propiedad descripMotivacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripMotivacion() {
        return descripMotivacion;
    }

    /**
     * Define el valor de la propiedad descripMotivacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripMotivacion(String value) {
        this.descripMotivacion = value;
    }

    /**
     * Obtiene el valor de la propiedad cvReducido.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCvReducido() {
        return cvReducido;
    }

    /**
     * Define el valor de la propiedad cvReducido.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCvReducido(String value) {
        this.cvReducido = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreOferta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreOferta() {
        return nombreOferta;
    }

    /**
     * Define el valor de la propiedad nombreOferta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreOferta(String value) {
        this.nombreOferta = value;
    }

    /**
     * Obtiene el valor de la propiedad linkVideo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkVideo() {
        return linkVideo;
    }

    /**
     * Define el valor de la propiedad linkVideo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkVideo(String value) {
        this.linkVideo = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaPostulacionString.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaPostulacionString() {
        return fechaPostulacionString;
    }

    /**
     * Define el valor de la propiedad fechaPostulacionString.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaPostulacionString(String value) {
        this.fechaPostulacionString = value;
    }

}
