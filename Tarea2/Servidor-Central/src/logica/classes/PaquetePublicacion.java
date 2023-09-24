package logica.classes;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import logica.DataTypes.DTCantidadTipoPublicacion;
import logica.DataTypes.DTPaquetePublicacion;

public class PaquetePublicacion {
	private String nombre;
	private String descripcion;
	private int periodoValidez;
	private float descuento;
	private float costo;
	private BufferedImage imagen;
	private ArrayList<CantidadTotalTipoPublicacion> cantidadTipoPublicaciones;

	public PaquetePublicacion(String nombre, String descripcion, int periodoValidez, float descuento, BufferedImage imagen,
			ArrayList<CantidadTotalTipoPublicacion> cantidadTipo) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.periodoValidez = periodoValidez;
		this.descuento = descuento;
		this.imagen = imagen;
		this.cantidadTipoPublicaciones = cantidadTipo;
		this.setCosto();
	}

	public String getNombre() {
		return nombre;
	}

	private void setCosto() {
		float costoTotal = 0;
		for (CantidadTotalTipoPublicacion cantidadTipoPublicacion : cantidadTipoPublicaciones) {
			costoTotal += cantidadTipoPublicacion.obtenerCostoTotalPublicaciones();
		}
		this.costo = costoTotal * ((100 - descuento) / 100);
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



	public int getPeriodoValidez() {
		return periodoValidez;
	}

	public void setPeriodoValidez(int periodoValidez) {
		this.periodoValidez = periodoValidez;
	}

	public float getDescuento() {
		return descuento;
	}

	public BufferedImage getImagen() {
		return imagen;
	}

	public void setImagen(BufferedImage imagen) {
		this.imagen = imagen;
	}


	public float getCosto() {
		return this.costo;
	}

	public void setCantidadTotalTipoPublicaciones(ArrayList<CantidadTotalTipoPublicacion> cantidadTipoPublicaciones) {
		this.cantidadTipoPublicaciones = cantidadTipoPublicaciones;
	}

	public DTPaquetePublicacion obtenerDTPaquete() {
		ArrayList<DTCantidadTipoPublicacion> listDTcantidad = new ArrayList<DTCantidadTipoPublicacion>();
		for (CantidadTotalTipoPublicacion cantidad : cantidadTipoPublicaciones) {
			listDTcantidad.add(cantidad.obtenerDTCantidadTipoPublicacion());
		}
		return new DTPaquetePublicacion(nombre, descripcion, periodoValidez, descuento,
				costo, imagen, listDTcantidad);
	}


	
	
	public ArrayList<CantidadTotalTipoPublicacion> obtenerCantidadTotalTipoPublicaciones() {
		return cantidadTipoPublicaciones;
	}

	public ArrayList<String> obtenerNombresTipoPublicaciones() {
		ArrayList<String> listaResultado = new ArrayList<String>();
		for (CantidadTotalTipoPublicacion cantidad : cantidadTipoPublicaciones) {
			listaResultado.add(cantidad.getTipoPublicacion().getNombre());
		}
		return listaResultado;
	}

	public void agregarTipoPublicacion(TipoPublicacion tipoPublicacion) {
		CantidadTotalTipoPublicacion cantidadTotalTipoPublicacion = tipoPublicacion.getCantidadTipoPublicacion();
		cantidadTipoPublicaciones.add(cantidadTotalTipoPublicacion);
	}

	public ArrayList<String> listarTipoPublicacion() {
		ArrayList<String> resultado = new ArrayList<>();
		for (CantidadTotalTipoPublicacion tipoPublicacion : cantidadTipoPublicaciones) {
			resultado.add(tipoPublicacion.getTipoPublicacion().getNombre());
		}
		return resultado;
	}

	public void crearCantidadTipoPublicacion(int cantIncluida, TipoPublicacion tipoPublicacion) {
		CantidadTotalTipoPublicacion cantidad = new CantidadTotalTipoPublicacion(cantIncluida, tipoPublicacion);
		cantidadTipoPublicaciones.add(cantidad);
		
	}
	
	

}
