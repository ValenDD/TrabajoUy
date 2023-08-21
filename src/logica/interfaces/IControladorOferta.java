package logica.interfaces;

import java.sql.Date;
import java.util.ArrayList;

import excepciones.ColeccionTipoPublicacionEsVaciaException;
import excepciones.KeywordNoExisteException;
import excepciones.TipoPublicacionNoExiste;
import excepciones.UsuarioNoExisteUsuarioException;
import logica.classes.OfertaLaboral;

public interface IControladorOferta {
	
	public abstract void altaOfertaLaboral(String nombre, String descrip, Date horaInicio, Date horaFin, double remuneracion, String ciudad, String departamento, Date fechaAlta, ArrayList<String> keywords, String nomTpoPublic, String nicknameEmpresa) throws KeywordNoExisteException, TipoPublicacionNoExiste, UsuarioNoExisteUsuarioException;
	

	public abstract OfertaLaboral obtenerOfertaLaboral(String nomOferta);

	

}

/*

+listarPostulantes(): Set(string)
+registrarPostulacion(cvReducido: String, motivacion: String, fechaPostulacion: DTFecha, nickname : String, nomOferta: String);
+listarTipoDePublicaciones(): Set(String)
+listarKeywords(): Set(String)
+obtenerKeyword(nomKeyword: String): Keyword
+confirmarAltaPublicacion(nombre: String, descripcion: String, exposicion: String, duracion: int, costo: Foat, fechaPub: DTFecha)
+altaOfertaLaboral(nombre: String, descrip: String, horaInicio: DTHora, horaFin: DTHora, remuneracion: Float, ciudad: String, departamento: String, fechaAlta: DTFecha, listaKeywords: Set(String), nomTpoPublic: String, nicknameEmpresa: String)
+listarPaquetes(): Set(String)
+agregarTipoPublicacionAlPaquete(cantIncluida: Int)
+obtenerDTOfertaLaboral(nomOferta: String):DTOferta
+obtenerOfertaLaboral(nomOferta): OfertaLaboral
*/
