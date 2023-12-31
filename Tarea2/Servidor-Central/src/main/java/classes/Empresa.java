package main.java.classes;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import main.java.datatypes.DtCompraPaquete;
import main.java.datatypes.DtEmpresa;
import main.java.datatypes.DtOfertaLaboral;
import main.java.datatypes.DtPaquetePublicacion;
import main.java.datatypes.DtUsuario;
import main.java.datatypes.EstadoOferta;
import main.java.excepciones.OfertaLaboralYaExisteException;

/**
 * Clase Empresa.
 */

public class Empresa extends Usuario {
  private String descripcion;
  private String sitioWeb;
  private List<OfertaLaboral> ofertasLaborales;
  private List<CompraPaquete> compraPaquetes;

  /**
   * Constructor.
   */

  public Empresa(
        String nickname,
          String nombre,
          String apellido,
          String email,
          String descripcion,
          String sitioWeb,
          BufferedImage imagen,
          String contrasenia) {
    super(nickname, nombre, apellido, email, imagen,
          contrasenia);
    setDescripcion(descripcion);
    setSitioWeb(sitioWeb);
    this.ofertasLaborales = new ArrayList<OfertaLaboral>();
    this.compraPaquetes = new ArrayList<CompraPaquete>();
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getSitioWeb() {
    return sitioWeb;
  }

  public void setSitioWeb(String sitioWeb) {
    this.sitioWeb = sitioWeb;
  }

  /**
   * Metodo para agregar oferta laboral.
   */

  public void agregarOferta(OfertaLaboral ofertaLaboral)
        throws OfertaLaboralYaExisteException {
    if (ofertasLaborales.indexOf(ofertaLaboral) != -1) {
      throw new OfertaLaboralYaExisteException(
            "La Oferta Laboral " + ofertaLaboral.getNombre()
                  + " ya esta asociada a la Empresa "
                  + this.nickname);
    }
    this.ofertasLaborales.add(ofertaLaboral);
  }

  /**
   * Metodo obtener nombre ofertas laborales.
   */

  public List<String> obtenerNombresOfertas() {
    List<String> ofertas = new ArrayList<String>();
    for (OfertaLaboral oferta : this.ofertasLaborales) {
      ofertas.add(oferta.getNombre());
    }
    return ofertas;
  }

  public List<OfertaLaboral> getOfertasLaborales() {
    return ofertasLaborales;
  }

  /**
   * Obtener DTEmpresa.
   */

  public DtEmpresa obtenerDtempresa() throws IOException {

    List<DtOfertaLaboral> listaDtofertas =
          new ArrayList<DtOfertaLaboral>();
    for (OfertaLaboral oferta : ofertasLaborales) {
      listaDtofertas.add(oferta.obtenerDtOfertaLaboral());
    }
    List<String> seguidos = new ArrayList<String>();
    for (String nickname : this.getSeguidos()) {
      seguidos.add(nickname);
    }
    List<String> seguidores = new ArrayList<String>();
    for (String nickname : this.getSeguidores()) {
      seguidores.add(nickname);
    }
    DtEmpresa resultado = new DtEmpresa(this.nickname,
          this.nombre, this.apellido, this.email,
          this.getImagen(), this.getContrasenia(),
          listaDtofertas, this.descripcion, this.sitioWeb,
          seguidos, seguidores);
    return resultado;
  }

  @Override
  public List<String> listarNombreOfertasUsuario() {
    return this.obtenerNombresOfertas();
  }

  @Override
  public DtUsuario obtenerDtusuario() throws IOException {
    return this.obtenerDtempresa();
  }

  /**
   * Obtener DTOfertaLaboral.
   */

  public List<DtOfertaLaboral> obtenerDtofertasIngresadas()
        throws IOException {
    List<DtOfertaLaboral> listaResultado =
          new ArrayList<DtOfertaLaboral>();
    for (OfertaLaboral oferta : ofertasLaborales) {
      if (oferta.getEstado() == EstadoOferta.INGRESADA) {
        listaResultado.add(oferta.obtenerDtOfertaLaboral());
      }
    }
    return listaResultado;
  }

  /**
   * Obtener DTOfertaLaboralConfirmadas.
   */

  public List<DtOfertaLaboral> obtenerDtofertasConfirmadas()
        throws IOException {
    List<DtOfertaLaboral> listaResultado =
          new ArrayList<DtOfertaLaboral>();
    for (OfertaLaboral oferta : ofertasLaborales) {
      if (oferta.getEstado() == EstadoOferta.CONFIRMADA) {
        listaResultado.add(oferta.obtenerDtOfertaLaboral());
      }
    }
    return listaResultado;
  }

  /**
   * Obtener DTOfertaLaboralRechazadas.
   */

  public List<DtOfertaLaboral> obtenerDtofertasRechazadas()
        throws IOException {
    List<DtOfertaLaboral> listaResultado =
          new ArrayList<DtOfertaLaboral>();
    for (OfertaLaboral oferta : ofertasLaborales) {
      if (oferta.getEstado() == EstadoOferta.RECHAZADA) {
        listaResultado.add(oferta.obtenerDtOfertaLaboral());
      }
    }
    return listaResultado;
  }

  /**
   * Metodo obtenerDtofertasFinalizadas.
   */

  public List<DtOfertaLaboral> obtenerDtofertasFinalizadas()
        throws IOException {
    List<DtOfertaLaboral> listaResultado =
          new ArrayList<DtOfertaLaboral>();
    for (OfertaLaboral oferta : ofertasLaborales) {
      if (oferta.getEstado() == EstadoOferta.FINALIZADA) {
        listaResultado.add(oferta.obtenerDtOfertaLaboral());
      }
    }
    return listaResultado;
  }

  public void comprarPaquete(CompraPaquete compraPaquete) {
    compraPaquetes.add(compraPaquete);
  }

  /**
   * Verifica si el paquete esta comprado.
   */

  public Boolean estaCompradoPaquete(String nombrePaquete) {
    Boolean resultado = false;
    for (CompraPaquete compraPaquete : compraPaquetes) {
      if (compraPaquete
            .obtenerNombrePaquete() == nombrePaquete) {
        resultado = true;
        break;
      }
    }
    return resultado;
  }

  /**
   * Obtener DTPaquetePublicacion.
   */

  public List<DtPaquetePublicacion> obtenerDtpaquetes()
        throws IOException {
    List<DtPaquetePublicacion> listaReultado =
          new ArrayList<DtPaquetePublicacion>();
    for (CompraPaquete compraPaquete : compraPaquetes) {
      listaReultado.add(compraPaquete.obtenerDtpaquete());
    }
    return listaReultado;
  }

  /**
   * Obtener listado de paquetes comprados.
   */

  public List<String> listarPaquetesComprados() {
    List<String> listaResultado = new ArrayList<String>();
    for (CompraPaquete compraPaquete : compraPaquetes) {
      listaResultado
            .add(compraPaquete.obtenerNombrePaquete());
    }
    return listaResultado;
  }

  /**
   * Comprar una oferta por paquete.
   */

  public void comprarOfertaPorPaquete(String nombrePaquete,
        String nomTipoPublicacion, OfertaLaboral oferta) {
    for (CompraPaquete compra : compraPaquetes) {
      if (nombrePaquete
            .equals(compra.obtenerNombrePaquete())) {
        compra.gastarTipoPublicacion(nomTipoPublicacion);
        oferta.setCompraPaquete(compra);
        break;
      }
    }

  }

  /**
   * Metodo obtenerDtCompraPaquetes.
   */

  public List<DtCompraPaquete> obtenerDtCompraPaquetes()
        throws IOException {
    List<DtCompraPaquete> resultado =
          new ArrayList<DtCompraPaquete>();
    for (CompraPaquete compra : compraPaquetes) {
      resultado.add(compra.obtenerDtCompraPaquete());
    }
    return resultado;
  }
}
