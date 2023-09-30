package logica.interfaces;

import excepciones.KeywordNoExisteException;
import excepciones.KeywordYaExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralNoTienePaquete;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.PaquetePublicacionNoExisteException;
import excepciones.PaquetePublicacionYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioNoExisteException;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import logica.classes.Keyword;
import logica.classes.OfertaLaboral;
import logica.classes.PaquetePublicacion;
import logica.classes.TipoPublicacion;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.DtcantidadTipoPublicacion;
import logica.datatypes.DtpaquetePublicacion;
import logica.datatypes.Dtpostulacion;
import logica.datatypes.DttipoPublicacion;
import logica.datatypes.EstadoOferta;

/**
 * Interface ControladorOferta .
 */

public interface IcontroladorOferta {
  
  public void altaOfertaLaboral(String nombre, String descripcion, String horarioInicial,
      String horarioFinal, float remuneracion, String ciudad, String departamento,
      LocalDate fechaAlta, String nomTipoPublicacion, String nicknameEmpresa,
      List<String> listakeywords, BufferedImage imagen, String nombrePaquete)
      throws OfertaLaboralYaExisteException, TipoPublicacionNoExisteException,
      KeywordNoExisteException, UsuarioNoExisteException;
  
  public List<String> listarTipoDePublicaciones();
  
  public OfertaLaboral obtenerOfertaLaboral(String nomOferta)
      throws OfertaLaboralNoExisteException;
  
  public List<String> listarPaquetes();
  
  public TipoPublicacion obtenerTipoPublicacion(String nomTpoPublic)
      throws TipoPublicacionNoExisteException, TipoPublicacionYaExisteException;
  
  public Keyword obtenerKeywords(String nomKeyword)
      throws KeywordNoExisteException, TipoPublicacionNoExisteException;
  
  public void altaKeyword(String nomKeyword) throws KeywordYaExisteException;
  
  public List<String> listarKeywords();
  
  public DtOfertaLaboral obtenerDtOfertaLaboral(String nomOferta)
      throws OfertaLaboralNoExisteException;
  
  public List<String> obtenerOfertasEmpresa(String nicknameEmpresa)
      throws UsuarioNoExisteException;
  
  public void registrarPaquete(String nombre, String descripcion, int periodoValDias,
      float descuento, BufferedImage imagen, LocalDate fechaAlta,
      List<DtcantidadTipoPublicacion> cantidadTipoPublicacion)
      throws PaquetePublicacionYaExisteException, TipoPublicacionYaExisteException,
      TipoPublicacionNoExisteException;
  
  public void altaTipoPublicacion(String nombre, String descripcion, String exposicion,
      int duracion, float costo, LocalDate fechaPub) throws TipoPublicacionYaExisteException;
  
  public void agregarTipoPublicacionAlPaquete(int cantIncluida, String nomTipoPublicacion,
      String nomTipoPaquete)
      throws TipoPublicacionNoExisteException, PaquetePublicacionNoExisteException;
  
  public PaquetePublicacion obtenerPaquetePublicacion(String string)
      throws PaquetePublicacionNoExisteException;
  
  public boolean estaPostulado(String postulante, String nomOfertaLaboral)
      throws UsuarioNoExisteException, OfertaLaboralNoExisteException;
  
  void aceptarRechazarOfertaLaboral(String nombreOferta, EstadoOferta estadoOferta,
      LocalDate fechaResolucion) throws OfertaLaboralNoExisteException;
  
  public List<DtOfertaLaboral> obtenerDtOfertasConfirmadas();
  
  List<DtOfertaLaboral> obtenerDtofertasPorKeyword(String keyword);
  
  public List<Dtpostulacion> obtenerDtPostulacionesDeOferta(String nombreOferta)
      throws OfertaLaboralNoExisteException;
  
  public boolean estaCompradoPorPaqueteOferta(String nombreOferta)
      throws OfertaLaboralNoExisteException;
  
  public DtpaquetePublicacion obtenerDtPaquetePublicacion(String nombreOferta)
      throws OfertaLaboralNoExisteException, OfertaLaboralNoTienePaquete;
  
  public List<String> listarTipoPublicacionDePaquete(String nombrePaquete)
      throws PaquetePublicacionNoExisteException;
  
  List<String> obtenerKeywordsDeOfertaLaboral(String nomOfertaLab)
      throws OfertaLaboralNoExisteException;
  
  DtpaquetePublicacion obtenerDtpaquete(String nombrePaquete)
      throws PaquetePublicacionNoExisteException;
  
  DttipoPublicacion obtenerDttipoPublicacion(String nombreTipo)
      throws TipoPublicacionNoExisteException;
  
  List<String> listarPaquetesNoComprados();
  
  List<DtpaquetePublicacion> listarDtpaquetes();
  
}
