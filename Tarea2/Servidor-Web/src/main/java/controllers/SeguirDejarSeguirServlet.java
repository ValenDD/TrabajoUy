package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import logica.webservices.DtCompraPaquete;
import logica.webservices.DtEmpresa;
import logica.webservices.DtOfertaLaboral;
import logica.webservices.DtPostulacion;
import logica.webservices.DtPostulante;
import logica.webservices.DtUsuario;
import logica.webservices.IOException_Exception;
import logica.webservices.PublicadorService;
import logica.webservices.UsuarioNoEstaSeguidoException_Exception;
import logica.webservices.UsuarioNoExisteException_Exception;
import logica.webservices.UsuarioYaEstaSeguidoException_Exception;

@WebServlet("/seguirDejarSeguir")
public class SeguirDejarSeguirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SeguirDejarSeguirServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void procesarRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		DtUsuario usuarioLogueado = (DtUsuario) sesion.getAttribute("usuarioLogueado");
		if(usuarioLogueado == null){
			response.sendError(403, "No autorizado");
			return;
		}
		BufferedReader reader = request.getReader();
		StringBuilder data = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
		    data.append(line);
		}		
        
		String nicknameSeguidor = request.getParameter("perfilUsuario");
		
		
		
		if(nicknameSeguidor.equals(usuarioLogueado.getNickname())) {
			response.sendError(400, "Bad Request");
			return;
		}
		String accion = usuarioLogueado.getSeguidos().contains(nicknameSeguidor)? "dejarSeguir" : "seguir";
		logica.webservices.PublicadorService service = new PublicadorService();
		logica.webservices.Publicador port = service.getPublicadorPort();
		Boolean flag = usuarioLogueado.getSeguidos().contains(nicknameSeguidor);
		
		try {
			DtUsuario usuario = port.obtenerDtUsuario(nicknameSeguidor);
			request.setAttribute("usuario", usuario);
			if (usuario instanceof DtEmpresa) {
				ArrayList<DtOfertaLaboral> ofertasConfirmadas = (ArrayList<DtOfertaLaboral>) port
						.obtenerDtOfertasConfirmadasDeEmpresa(nicknameSeguidor).getItem();

				ArrayList<DtOfertaLaboral> ofertasIngresadas = (ArrayList<DtOfertaLaboral>) port
						.obtenerDtOfertasIngresadasDeEmpresa(nicknameSeguidor).getItem();

				ArrayList<DtOfertaLaboral> ofertasRechazadas = (ArrayList<DtOfertaLaboral>) port
						.obtenerDtOfertasRechazadasDeEmpresa(nicknameSeguidor).getItem();

				ArrayList<DtCompraPaquete> compraPaquetes = (ArrayList<DtCompraPaquete>) port
						.obtenerDtCompraPaqueteDeEmpresa(nicknameSeguidor).getItem();

				request.setAttribute("ofertasConfirmadas", ofertasConfirmadas);
				request.setAttribute("ofertasIngresadas", ofertasIngresadas);
				request.setAttribute("ofertasRechazadas", ofertasRechazadas);
				request.setAttribute("compraPaquetes", compraPaquetes);
				request.setAttribute("tipoUsuario", "empresa");
			}

			if (usuario instanceof DtPostulante) {
				ArrayList<DtPostulacion> postulaciones = (ArrayList<DtPostulacion>) port
						.obtenerDtPostulacionesDePostulante(nicknameSeguidor).getItem();

				request.setAttribute("postulaciones", postulaciones);
				request.setAttribute("tipoUsuario", "postulante");
			}
			
			if("seguir".equals(accion))
			{
				port.agregarSeguidor(usuarioLogueado.getNickname(), nicknameSeguidor);
				usuarioLogueado.getSeguidos().add(nicknameSeguidor);
				flag = true;
			}
			else if("dejarSeguir".equals(accion))
			{
				port.dejarDeSeguir(usuarioLogueado.getNickname(), nicknameSeguidor);
				usuarioLogueado.getSeguidos().remove(nicknameSeguidor);
				flag = false;
			}
			sesion.setAttribute("usuarioLogueado", usuarioLogueado);
		} catch (UsuarioNoExisteException_Exception | IOException_Exception | UsuarioNoEstaSeguidoException_Exception | UsuarioYaEstaSeguidoException_Exception e) {
			response.sendError(500, e.getMessage());
			e.printStackTrace();
			return;
			
		}
		request.setAttribute("seguidoOno", flag);
		
		response.setStatus(200);
		return;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		procesarRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}