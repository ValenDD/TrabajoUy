package logica.interfaces;

import java.sql.Date;
import java.util.ArrayList;

import excepciones.ColeccionEmpresaEsVaciaException;
import excepciones.UsuarioNoExisteUsuarioException;
import logica.DataTypes.DTUsuario;
import logica.classes.Empresa;

public interface IControladorUsuario {
	
	public abstract Empresa obtenerEmpresa(String nicknameEmpresa) throws UsuarioNoExisteUsuarioException;
	
	public abstract ArrayList<String> listarEmpresas() throws ColeccionEmpresaEsVaciaException;
	
	public abstract ArrayList<String> listaDeUsuarios();
	
	public abstract void editarDatosBasicos(DTUsuario usuario);
	
	public abstract ArrayList<String> obtenerOfertasEmpresa(String nicknameEmpresa);
	
	public abstract ArrayList<String> listarPostulantes();
	
	public abstract void registrarPostulacion(String cvReducido,String motivacion, Date fechaPostulacion, String nickname, String nomOferta);
	
	public abstract void altaPostulante(String nickname, String nombre, String apellido, String email, Date fechaNac, String nacionalidad);
	
	public abstract void altaEmpresa(String nickname, String nombre, String apellido, String email, String descripcion, String link);
	

}
