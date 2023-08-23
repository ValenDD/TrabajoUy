package logica.controllers;

import java.sql.Date;
import java.util.ArrayList;

import excepciones.ColeccionEmpresaEsVaciaException;
import excepciones.ColeccionTipoPublicacionEsVaciaException;
import excepciones.KeywordNoExisteException;
import excepciones.KeywordYaExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.TipoPublicacionNoExiste;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioNoExisteException;
import logica.DataTypes.DTOfertaLaboral;
import logica.classes.CantidadTipoPublicacion;
import logica.classes.CompraPaquete;
import logica.classes.Empresa;
import logica.classes.Keyword;
import logica.classes.OfertaLaboral;
import logica.classes.PaquetePublicacion;
import logica.classes.Postulacion;
import logica.classes.Postulante;
import logica.classes.TipoPublicacion;
import logica.handlers.ManejadorOfertas;
import logica.handlers.ManejadorPaquetes;
import logica.handlers.ManejadorSettings;
import logica.handlers.ManejadorUsuario;
import logica.interfaces.IControladorOferta;

public class ControladorOferta implements IControladorOferta {

	public ArrayList<String> listarTipoDePublicaciones() throws ColeccionTipoPublicacionEsVaciaException{
		ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
		ArrayList<String> nombreTiposPublicacion = manejadorSettings.listarTipoDePublicaciones();
		if (nombreTiposPublicacion != null) {
			return nombreTiposPublicacion;
		}
		else {
			throw new ColeccionTipoPublicacionEsVaciaException("No existen tipos de publicaciones registrados");
		}
	}

	public void altaOfertaLaboral(String nombre, String descripcion, Date horaInicio, Date horaFin, Float remuneracion,
			String ciudad, String departamento, Date fechaAlta, ArrayList<Keyword> keywords,
			TipoPublicacion tipoPublicacion) throws OfertaLaboralYaExisteException {

		ManejadorOfertas manejadorOfertas = ManejadorOfertas.getInstance();
		OfertaLaboral ofertaLaboral = manejadorOfertas.obtenerOfertaLaboral(nombre);
		if (ofertaLaboral != null) {
			throw new OfertaLaboralYaExisteException("La Oferta Laboral " + nombre + " ya se encuentra registrada");
		}
		else {
			ofertaLaboral = new OfertaLaboral(nombre, descripcion, ciudad, departamento, horaInicio, horaFin,
					remuneracion, fechaAlta, keywords, tipoPublicacion);
			manejadorOfertas.agregarOferta(ofertaLaboral);
		}
		
	}

	public OfertaLaboral obtenerOfertaLaboral(String nomOferta) throws OfertaLaboralNoExisteException{
		ManejadorOfertas manejadorOfertas = ManejadorOfertas.getInstance();
		OfertaLaboral ofertaLaboral = manejadorOfertas.obtenerOfertaLaboral(nomOferta);
		if (ofertaLaboral == null) {
			throw new OfertaLaboralNoExisteException("La Oferta laboral " + nomOferta + " no existe");
		}
		else {
			return ofertaLaboral;
		} 
	}

	public void agregarTipoPublicacionAlPaquete(int cantIncluida, String nomTipoPublicacion, String nomTipoPaquete) {
		ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
		ManejadorPaquetes manejadorPaquetes = ManejadorPaquetes.getInstance();

		TipoPublicacion tipoPublicacion;
		try {
			tipoPublicacion = manejadorSettings.obtenerTipoPublicacion(nomTipoPublicacion);
			PaquetePublicacion paquetePublicacion = manejadorPaquetes.obtenerPaquete(nomTipoPaquete);

			paquetePublicacion.crearCantidadTipoPublicacion(paquetePublicacion, cantIncluida, tipoPublicacion);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> listarPaquetes() {
		ManejadorPaquetes manejadorPaquetes = ManejadorPaquetes.getInstance();
		return manejadorPaquetes.listarPaquetes();
	}

	public void altaTipoPublicacion(String nombre, String descripcion, String exposicion, int duracion, Float costo,
			Date fechaPub) throws TipoPublicacionYaExisteException {
		ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
		TipoPublicacion tpoPublic = manejadorSettings.obtenerTipoPublicacion(nombre);
		if (tpoPublic != null) {
			throw new TipoPublicacionYaExisteException("El tipo de publicacion " + nombre + " ya se encuentra registrado");
		}
		else {
			TipoPublicacion tipoPublicacion = new TipoPublicacion(nombre, descripcion, exposicion, duracion, costo,
					fechaPub);
			manejadorSettings.addTipoPublicacion(tipoPublicacion);
		}

	}
	
	public void altaKeyword(String nomKeyword) throws KeywordYaExisteException {
		ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
		if (manejadorSettings.obtenerKeyword(nomKeyword) == null) {
			Keyword keyword = new Keyword(nomKeyword);
			manejadorSettings.addKeyword(keyword);	
		}
		else {
			throw new KeywordYaExisteException("La keyword " + nomKeyword + " ya se encuentra registrada");
		}
	}
	
	

	public Keyword obtenerKeywords(String nomKeyword) throws KeywordNoExisteException {
		ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
		Keyword keyword = manejadorSettings.obtenerKeyword(nomKeyword);
		if  (keyword == null) {
			throw new KeywordNoExisteException("La Keyword " + nomKeyword + " no existe");
		}
		else {
			return keyword;
		}
	}

	public ArrayList<String> listarKeywords() {
		ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
		ArrayList<String> listKeywords = manejadorSettings.listarKeywords();
		return listKeywords;
	}

	public void registrarPostulacion(String cvReducido, String motivacion, Date fechaPostulacion, String nickname,
			String nomOferta) {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
		ManejadorOfertas manejadorOfertas = ManejadorOfertas.getInstance();

		try {
			Postulante postulante = manejadorUsuario.obtenerPostulante(nickname);
			OfertaLaboral ofertaLaboral = manejadorOfertas.obtenerOfertaLaboral(nomOferta);
			Postulacion postulacion = new Postulacion(motivacion, fechaPostulacion, cvReducido, postulante,
					ofertaLaboral);
			ofertaLaboral.agregarPostulacion(postulacion);
			postulante.agregarPostulacion(postulacion);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<String> listarPostulantes() {
		ControladorUsuario controladorUsuario = new ControladorUsuario();

		ArrayList<String> lpostulantes = controladorUsuario.listarPostulantes();
		return lpostulantes;
	}

	public DTOfertaLaboral obtenerDtOfertaLaboral(String nomOferta) {
		ManejadorOfertas manejadorOfertas = ManejadorOfertas.getInstance();
		try {
			OfertaLaboral ofertaLaboral = manejadorOfertas.obtenerOfertaLaboral(nomOferta);
			DTOfertaLaboral dtOfertaLaboral = ofertaLaboral.obtenerDTOfertaLaboral();
			return dtOfertaLaboral;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public ArrayList<String> obtenerOfertasEmpresa(String nicknameEmpresa) {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
		try {
			Empresa empresa = manejadorUsuario.obtenerEmpresa(nicknameEmpresa);
			ArrayList<OfertaLaboral> ofertas = empresa.getOfertasLaborales();

			ArrayList<String> nombreOfertas = new ArrayList<String>();

			for (OfertaLaboral ofertaLaboral : ofertas) {
				nombreOfertas.add(ofertaLaboral.getNombre());
			}
			return nombreOfertas;

		} catch (UsuarioNoExisteException e) {
			e.printStackTrace();
			return null;
		}

	}

	public void registrarPaquete(String nombre, String descripcion, int cantidadPublicaciones, int periodoValDias,
			Float descuento, Date fechaAlta, ArrayList<CantidadTipoPublicacion> cantidadTipoPublicacion) {
		try {
			ManejadorPaquetes manejadorPaquetes = ManejadorPaquetes.getInstance();
			PaquetePublicacion paquetePublicacion = new PaquetePublicacion(nombre, descripcion, cantidadPublicaciones,
					periodoValDias, descuento, cantidadTipoPublicacion);
			manejadorPaquetes.agregarPaquete(paquetePublicacion);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void altaOfertaLaboral(String nombre, String descrip, Date horaInicio, Date horaFin, double remuneracion,
			String ciudad, String departamento, Date fechaAlta, ArrayList<String> keywords, String nomTpoPublic,
			String nicknameEmpresa) throws TipoPublicacionNoExiste, KeywordNoExisteException, UsuarioNoExisteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregarTipoPublicacionAlPaquete(int cantIncluida) {
		// TODO Auto-generated method stub

	}

	@Override
	public void confirmarAltaPublicacion(String nombre, String descripcion, String exposicion, int duracion,
			Float costo, Date fechaPub) {
		// TODO Auto-generated method stub

	}
}