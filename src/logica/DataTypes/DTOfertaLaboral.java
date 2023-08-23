package logica.DataTypes;

import java.util.ArrayList;
import java.sql.Date;
import java.sql.Time;

public class DTOfertaLaboral {
	private String nombre;
	private String descripcion;
	private String ciudad;
	private String departamento;
	private String horario;
	private Float remuneracion;
	private Date fechaAlta;
	private DTPaquetePublicacion paquete;
	private ArrayList<DTPostulacion> postulaciones;
	
	public DTOfertaLaboral(String nombre, String descripcion, String ciudad, String horario, Float remuneracion, Date fechaAlta, DTPaquetePublicacion paquete, ArrayList<DTPostulacion> postulaciones)
	{
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ciudad = ciudad;
		this.horario = horario;
		this.remuneracion = remuneracion;
		this.fechaAlta = fechaAlta;
		this.paquete = paquete;
		this.postulaciones = postulaciones;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getDepartamento() {
		return departamento;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	
	
	
	public Float getRemuneracion() {
		return remuneracion;
	}
	
	public Date getFechaAlta() {
		return fechaAlta;
	}

	public DTPaquetePublicacion getPaquete() {
		return paquete;
	}

	public ArrayList<DTPostulacion> getPostulaciones() {
		return postulaciones;
	}
}