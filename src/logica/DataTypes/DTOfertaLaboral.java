package logica.DataTypes;

import java.util.ArrayList;
import java.sql.Date;

public class DTOfertaLaboral {
	private String nombre;
	private String descripcion;
	private String ciudad;
	private String departamento;
	private Date horaInicio;
	private Date horaFin;
	private Float remuneracion;
	private Date fechaAlta;
	private DTPaquetePublicacion paquete;
	private ArrayList<DTPostulacion> postulaciones;
	
	public DTOfertaLaboral(String nombre, String descripcion, String ciudad, Date horaInicio, Date horaFin, Float remuneracion, Date fechaAlta, DTPaquetePublicacion paquete, ArrayList<DTPostulacion> postulaciones)
	{
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ciudad = ciudad;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
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
	
	public Date getHoraInicio() {
		return horaInicio;
	}
	
	public Date getHoraFin() {
		return horaFin;
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