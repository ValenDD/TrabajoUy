package main.java.classes;

import java.time.LocalDate;
import main.java.datatypes.DtTipoPublicacion;

/**
 * Clase TipoPublicación .
 */

public class TipoPublicacion {

  private String nombre;
  private String descripcion;
  private String exposicion;
  private int duracionDia;
  private float costo;
  private LocalDate fechaAlta;

  /**
   * Constructor .
   */

  public TipoPublicacion(
        String nombre,
          String descripcion,
          String exposicion,
          int duracion,
          Float costo,
          LocalDate fechaPub) {
    this.setNombre(nombre);
    this.setDescripcion(descripcion);
    this.setExposicion(exposicion);
    this.setDuracionDia(duracion);
    this.setCosto(costo);
    this.setFechaAlta(fechaPub);
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getExposicion() {
    return exposicion;
  }

  public void setExposicion(String exposicion) {
    this.exposicion = exposicion;
  }

  public int getDuracionDia() {
    return duracionDia;
  }

  public void setDuracionDia(int duracionDia) {
    this.duracionDia = duracionDia;
  }

  public float getCosto() {
    return costo;
  }

  public void setCosto(float costo) {
    this.costo = costo;
  }

  public LocalDate getFechaAlta() {
    return fechaAlta;
  }

  public void setFechaAlta(LocalDate fechaAlta) {
    this.fechaAlta = fechaAlta;
  }

  /**
   * Obtener DTtipo de publicación .
   */
  public DtTipoPublicacion obtenerDttipoPublicacion() {
    return new DtTipoPublicacion(this.getNombre(),
          this.getDescripcion(), this.getExposicion(),
          this.getDuracionDia(), this.costo,
          this.getFechaAlta());
  }
}
