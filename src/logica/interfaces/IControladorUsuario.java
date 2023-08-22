package logica.interfaces;

import java.sql.Date;
import java.util.ArrayList;

import excepciones.ColeccionEmpresaEsVaciaException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import logica.DataTypes.DTUsuario;
import logica.classes.Empresa;

public interface IControladorUsuario {
	
	public abstract Empresa obtenerEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException;
	
	public abstract ArrayList<String> listarEmpresas() throws ColeccionEmpresaEsVaciaException;
	
	public abstract ArrayList<String> listaDeUsuarios();
	
	public abstract void editarDatosBasicos(DTUsuario usuario) throws UsuarioNoExisteException;
	
	public abstract ArrayList<String> obtenerOfertasEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException;
	
	public abstract ArrayList<String> listarPostulantes();
	
	public abstract void registrarPostulacion(String cvReducido,String motivacion, Date fechaPostulacion, String nickname, String nomOferta) throws UsuarioNoExisteException;
	
	public abstract void altaPostulante(String nickname, String nombre, String apellido, String email, Date fechaNac, String nacionalidad) throws UsuarioYaExisteException;
	
	public abstract void altaEmpresa(String nickname, String nombre, String apellido, String email, String descripcion, String link) throws UsuarioYaExisteException;
	

}