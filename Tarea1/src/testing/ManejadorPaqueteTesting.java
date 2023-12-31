package testing;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import excepciones.PaquetePublicacionNoExisteException;
import excepciones.PaquetePublicacionYaExisteException;
import junit.framework.Assert;
import logica.classes.CantidadTipoPublicacion;
import logica.classes.Empresa;
import logica.classes.PaquetePublicacion;
import logica.classes.Postulante;
import logica.classes.TipoPublicacion;
import logica.handlers.ManejadorPaquetes;
import logica.handlers.ManejadorUsuario;

public class ManejadorPaqueteTesting {

	private static ManejadorPaquetes manejadorPaquete;
	private static Date fechaDate1;
	private static Date fechaDate2;
	public static void setUp()
	{		
		String fecha1 = "02/09/1988";
		String fecha2 = "04/4/2023";
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try
		{
			fechaDate1 = dateFormat.parse(fecha1);
			fechaDate2 = dateFormat.parse(fecha2);
		}
		catch (ParseException e)
		{
			e.getMessage();
		}
		
	}
	
	@Before
	public void cleanUp() {
		manejadorPaquete = ManejadorPaquetes.getInstance();
		manejadorPaquete.clean();
	}
	
	@Test
	public void agregarPaqueteTest() throws PaquetePublicacionYaExisteException, PaquetePublicacionNoExisteException {
		manejadorPaquete = ManejadorPaquetes.getInstance();
		TipoPublicacion tipoPublicacion = new TipoPublicacion("nombreTipo", "descripcionTipo", "exposicionTipo", 2, 200f, fechaDate1);
		CantidadTipoPublicacion cantidadTipoPublicacion = new CantidadTipoPublicacion(4, tipoPublicacion);
		ArrayList<CantidadTipoPublicacion> listaCantidadTipoP = new ArrayList<CantidadTipoPublicacion>();
		listaCantidadTipoP.add(cantidadTipoPublicacion);
		PaquetePublicacion paquete = new PaquetePublicacion("nombrePaquete", "descripcionPaquete", 5, 20f, listaCantidadTipoP);
		manejadorPaquete.agregarPaquete(paquete);
		PaquetePublicacion resultado = manejadorPaquete.obtenerPaquete("nombrePaquete");
		Assert.assertEquals(paquete, resultado);
	}
	@Test
	public void listarPaquetesTesting() throws PaquetePublicacionYaExisteException {
		manejadorPaquete = ManejadorPaquetes.getInstance();
		TipoPublicacion tipoPublicacion1 = new TipoPublicacion("nombreTipo1", "descripcionTipo1", "exposicionTipo1", 2, 200f, fechaDate1);
		TipoPublicacion tipoPublicacion2 = new TipoPublicacion("nombreTipo2", "descripcionTipo2", "exposicionTipo2", 3, 20f, fechaDate2);
		CantidadTipoPublicacion cantidadTipoPublicacion1 = new CantidadTipoPublicacion(4, tipoPublicacion1);
		CantidadTipoPublicacion cantidadTipoPublicacion2 = new CantidadTipoPublicacion(3, tipoPublicacion2);
		ArrayList<CantidadTipoPublicacion> listaCantidadTipoP1 = new ArrayList<CantidadTipoPublicacion>();
		ArrayList<CantidadTipoPublicacion> listaCantidadTipoP2 = new ArrayList<CantidadTipoPublicacion>();
		listaCantidadTipoP1.add(cantidadTipoPublicacion1);
		listaCantidadTipoP2.add(cantidadTipoPublicacion2);
		PaquetePublicacion paquete1 = new PaquetePublicacion("nombrePaquete1", "descripcionPaquete1", 5, 20f, listaCantidadTipoP1);
		PaquetePublicacion paquete2 = new PaquetePublicacion("nombrePaquete2", "descripcionPaquete2", 5, 20f, listaCantidadTipoP2);
		manejadorPaquete.agregarPaquete(paquete1);
		manejadorPaquete.agregarPaquete(paquete2);
		ArrayList<String> listaResultado = manejadorPaquete.listarPaquetes();
		Collections.sort(listaResultado);
		ArrayList<String> listaEsperada = new ArrayList<String>();
		listaEsperada.add("nombrePaquete1");
		listaEsperada.add("nombrePaquete2");
		Assert.assertEquals(listaResultado, listaEsperada);
		
	}
	
	@Test
	public void eliminarPaqueteTest() throws PaquetePublicacionYaExisteException, PaquetePublicacionNoExisteException {
		manejadorPaquete = ManejadorPaquetes.getInstance();
		TipoPublicacion tipoPublicacion1 = new TipoPublicacion("nombreTipo1", "descripcionTipo1", "exposicionTipo1", 2, 200f, fechaDate1);
		CantidadTipoPublicacion cantidadTipoPublicacion1 = new CantidadTipoPublicacion(4, tipoPublicacion1);
		ArrayList<CantidadTipoPublicacion> listaCantidadTipoP1 = new ArrayList<CantidadTipoPublicacion>();
		listaCantidadTipoP1.add(cantidadTipoPublicacion1);
		PaquetePublicacion paquete1 = new PaquetePublicacion("nombrePaquete1", "descripcionPaquete1", 5, 20f, listaCantidadTipoP1);
		manejadorPaquete.agregarPaquete(paquete1);
		manejadorPaquete.eliminarPaquete("nombrePaquete1");
		ArrayList<String> listaResultado = manejadorPaquete.listarPaquetes();
		Assert.assertTrue(listaResultado.isEmpty());
	}
	
	@Test
	public void agregarPaqueteRepetidoTest() {
		boolean exception = false;
		manejadorPaquete = ManejadorPaquetes.getInstance();
		TipoPublicacion tipoPublicacion1 = new TipoPublicacion("nombreTipo1", "descripcionTipo1", "exposicionTipo1", 2, 200f, fechaDate1);
		TipoPublicacion tipoPublicacion2 = new TipoPublicacion("nombreTipo2", "descripcionTipo2", "exposicionTipo2", 3, 20f, fechaDate2);
		CantidadTipoPublicacion cantidadTipoPublicacion1 = new CantidadTipoPublicacion(4, tipoPublicacion1);
		CantidadTipoPublicacion cantidadTipoPublicacion2 = new CantidadTipoPublicacion(3, tipoPublicacion2);
		ArrayList<CantidadTipoPublicacion> listaCantidadTipoP1 = new ArrayList<CantidadTipoPublicacion>();
		ArrayList<CantidadTipoPublicacion> listaCantidadTipoP2 = new ArrayList<CantidadTipoPublicacion>();
		listaCantidadTipoP1.add(cantidadTipoPublicacion1);
		listaCantidadTipoP2.add(cantidadTipoPublicacion2);
		PaquetePublicacion paquete1 = new PaquetePublicacion("nombrePaquete1", "descripcionPaquete1", 5, 20f, listaCantidadTipoP1);
		PaquetePublicacion paquete2 = new PaquetePublicacion("nombrePaquete1", "descripcionPaquete2", 5, 20f, listaCantidadTipoP2);
		try {
			manejadorPaquete.agregarPaquete(paquete1);
			manejadorPaquete.agregarPaquete(paquete2);
		} catch (PaquetePublicacionYaExisteException e) {
			exception = true;
		}
		Assert.assertTrue(exception);
	}
	
	@Test
	public void obtenerPaqueteInexistenteTest() {
		boolean exception = false;
		manejadorPaquete = ManejadorPaquetes.getInstance();
		TipoPublicacion tipoPublicacion1 = new TipoPublicacion("nombreTipo1", "descripcionTipo1", "exposicionTipo1", 2, 200f, fechaDate1);
		CantidadTipoPublicacion cantidadTipoPublicacion1 = new CantidadTipoPublicacion(4, tipoPublicacion1);
		ArrayList<CantidadTipoPublicacion> listaCantidadTipoP1 = new ArrayList<CantidadTipoPublicacion>();
		listaCantidadTipoP1.add(cantidadTipoPublicacion1);
		PaquetePublicacion paquete1 = new PaquetePublicacion("nombrePaquete1", "descripcionPaquete1", 5, 20f, listaCantidadTipoP1);
		try {
			manejadorPaquete.agregarPaquete(paquete1);
			PaquetePublicacion resultado = manejadorPaquete.obtenerPaquete("Pepe");
		} catch (PaquetePublicacionYaExisteException e) {
			
		} catch (PaquetePublicacionNoExisteException e) {
			exception = true;
		}
		Assert.assertTrue(exception);
	}
	
	@Test
	public void eliminarPaqueteInexistente() {
		boolean exception = false;
		manejadorPaquete = ManejadorPaquetes.getInstance();
		TipoPublicacion tipoPublicacion1 = new TipoPublicacion("nombreTipo1", "descripcionTipo1", "exposicionTipo1", 2, 200f, fechaDate1);
		CantidadTipoPublicacion cantidadTipoPublicacion1 = new CantidadTipoPublicacion(4, tipoPublicacion1);
		ArrayList<CantidadTipoPublicacion> listaCantidadTipoP1 = new ArrayList<CantidadTipoPublicacion>();
		listaCantidadTipoP1.add(cantidadTipoPublicacion1);
		PaquetePublicacion paquete1 = new PaquetePublicacion("nombrePaquete1", "descripcionPaquete1", 5, 20f, listaCantidadTipoP1);
		try {
			manejadorPaquete.agregarPaquete(paquete1);
			manejadorPaquete.eliminarPaquete("Pepe");
		} catch (PaquetePublicacionYaExisteException e) {
			
		} catch (PaquetePublicacionNoExisteException e) {
			exception = true;
		}
		Assert.assertTrue(exception);
	}
}
