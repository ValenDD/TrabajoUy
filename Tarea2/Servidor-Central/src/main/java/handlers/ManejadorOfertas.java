package main.java.handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.java.classes.OfertaLaboral;
import main.java.datatypes.DtOfertaLaboral;
import main.java.datatypes.EstadoOferta;
import main.java.excepciones.OfertaLaboralNoExisteException;
import main.java.excepciones.OfertaLaboralYaExisteException;

/**
 * Clase Manejador de ofertas.
 */

public class ManejadorOfertas {

  private static ManejadorOfertas instancia = null;

  // private HashMap<String, TipoPublicacion>
  // coleccionTipoPublicacion;
  private Map<String, OfertaLaboral> coleccionOfertaLaboral;

  private ManejadorOfertas() {
    // coleccionTipoPublicacion = new HashMap<String,
    // TipoPublicacion>();
    coleccionOfertaLaboral =
          new HashMap<String, OfertaLaboral>();
  }

  /**
   * Obtener instancia Manejador de ofertas.
   */

  public static ManejadorOfertas getInstance() {
    if (instancia == null) {
      instancia = new ManejadorOfertas();
    }
    return instancia;
  }

  /**
   * Metodo agregar oferta laboral .
   */

  public void agregarOferta(OfertaLaboral ofertaLaboral)
        throws OfertaLaboralYaExisteException {
    if (!coleccionOfertaLaboral
          .containsKey(ofertaLaboral.getNombre())) {
      coleccionOfertaLaboral.put(ofertaLaboral.getNombre(),
            ofertaLaboral);
    } else {
      throw new OfertaLaboralYaExisteException(
            "La oferta laboral que desea ingresar ya existe");
    }
  }

  /**
   * Obtener oferta laboral .
   */

  public OfertaLaboral obtenerOfertaLaboral(
        String nomOferta)
        throws OfertaLaboralNoExisteException {
    if (coleccionOfertaLaboral.containsKey(nomOferta)) {
      return coleccionOfertaLaboral.get(nomOferta);
    } else {
      throw new OfertaLaboralNoExisteException(
            "No existe la oferta solicitada");
    }
  }

  /**
   * Obtener lista de ofertas laborales .
   */

  public List<String> listarOfertasLaborales() {
    List<String> resultado = new ArrayList<String>();
    for (Map.Entry<String,
          OfertaLaboral> entry : this.coleccionOfertaLaboral
                .entrySet()) {
      resultado.add(entry.getKey());
    }
    return resultado;
  }

  public void clean() {
    instancia = null;
  }

  /**
   * Obtener DTOfertasLaboralesConfirmadas .
   */

  public List<DtOfertaLaboral> obtenerDtofertasConfirmadas()
        throws IOException {
    List<DtOfertaLaboral> listaResultado =
          new ArrayList<DtOfertaLaboral>();
    for (OfertaLaboral oferta : coleccionOfertaLaboral
          .values()) {
      if (oferta.getEstado() == EstadoOferta.CONFIRMADA) {
        listaResultado.add(oferta.obtenerDtOfertaLaboral());
      }

    }
    return listaResultado;
  }

  /**
   * Obtener obtenerDTOfertasPorKeyword .
   */

  public List<DtOfertaLaboral> obtenerDtofertasPorKeyword(
        String keyword) throws IOException {
    List<DtOfertaLaboral> listaResultado =
          new ArrayList<DtOfertaLaboral>();
    for (OfertaLaboral oferta : coleccionOfertaLaboral
          .values()) {
      if (oferta.tieneKeyword(keyword)
            && oferta.getEstado() == EstadoOferta.CONFIRMADA
            && !oferta.estaVencida()) {
        listaResultado.add(oferta.obtenerDtOfertaLaboral());
      }
    }
    return listaResultado;
  }

  public Boolean existeOfertaLaboral(String nombreOferta) {
    return coleccionOfertaLaboral.containsKey(nombreOferta);
  }

  /**
   * Metodo obtenerDtOfertas.
   */

  public List<DtOfertaLaboral> obtenerDtOfertas()
        throws IOException {
    List<DtOfertaLaboral> listaResultado =
          new ArrayList<DtOfertaLaboral>();
    for (OfertaLaboral oferta : coleccionOfertaLaboral
          .values()) {
      listaResultado.add(oferta.obtenerDtOfertaLaboral());
    }
    return listaResultado;
  }
}
