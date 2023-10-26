package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlTransient;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase Dtpostulacion.
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DtPostulacion implements Serializable {
  
  private static final long serialVersionUID = -5291054783279291754L;
  private String nicknamePostulante;
  
  private String descripMotivacion;
  @XmlTransient
  private LocalDate fechaPostulacion;
  
  private String cvReducido;
  
  private String nombreOferta;
  
  private String linkVideo;
  
  /**
   * Contructor.
   */
  
  public DtPostulacion(String postulante,
      String descripMotivacion, LocalDate fechaPostulacion,
      String cvReducido, String nombreOferta, String linkVideo) {
    this.nicknamePostulante = postulante;
    this.descripMotivacion = descripMotivacion;
    this.fechaPostulacion = fechaPostulacion;
    this.cvReducido = cvReducido;
    this.nombreOferta = nombreOferta;
    this.setLinkVideo(linkVideo);
  }
  
  public DtPostulacion() {
  }
  
  public String getNicknamePostulante() {
    return nicknamePostulante;
  }
  
  public void setNicknamePostulante(
      String nicknamePostulante) {
    this.nicknamePostulante = nicknamePostulante;
  }
  
  public void setDescripMotivacion(
      String descripMotivacion) {
    this.descripMotivacion = descripMotivacion;
  }
  
  public void setFechaPostulacion(
      LocalDate fechaPostulacion) {
    this.fechaPostulacion = fechaPostulacion;
  }
  
  public void setCvReducido(String cvReducido) {
    this.cvReducido = cvReducido;
  }
  
  public void setNombreOferta(String nombreOferta) {
    this.nombreOferta = nombreOferta;
  }
  
  public String getnicknamePostulante() {
    return nicknamePostulante;
  }
  
  public String getDescripMotivacion() {
    return descripMotivacion;
  }
  
  public LocalDate getFechaPostulacion() {
    return fechaPostulacion;
  }
  
  public String getCvReducido() {
    return cvReducido;
  }
  
  public String getNombreOferta() {
    return nombreOferta;
  }

/**
 * @return the linkVideo
 */
public String getLinkVideo() {
	return linkVideo;
}

/**
 * @param linkVideo the linkVideo to set
 */
public void setLinkVideo(String linkVideo) {
	this.linkVideo = linkVideo;
}
  
}
