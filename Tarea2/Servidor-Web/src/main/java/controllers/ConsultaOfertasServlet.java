package controllers;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.webservices.DtOfertaLaboral;
import logica.webservices.DtUsuario;
import logica.webservices.IOException_Exception;
import logica.webservices.PublicadorService;
import logica.webservices.UsuarioNoExisteException_Exception;

/**
 * Servlet implementation class OfertasServlet
 */
@WebServlet("/consultaOfertas")
public class ConsultaOfertasServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  /**
   * @see HttpServlet#HttpServlet()
   */
  public ConsultaOfertasServlet() {
    super();
    // TODO Auto-generated constructor stub
  }
  
  private void procesarRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    PublicadorService publicadorService = new PublicadorService();
    logica.webservices.Publicador cliente = publicadorService.getPublicadorPort();
    
    String nicknameEmpresa = request.getParameter("nicknameEmpresa");
    String keyword = request.getParameter("keyword");
    String userAgent = request.getHeader("User-Agent");
    
    if (userAgent != null && userAgent.toLowerCase().contains("mobile")) {
      if (nicknameEmpresa == null && keyword == null) {
        ArrayList<String> listaNickEmpresas = (ArrayList<String>) cliente.listarEmpresas()
            .getItem();
        
        ArrayList<DtUsuario> listaEmpresas = new ArrayList<DtUsuario>();
        
        for (String nickEmpresa : listaNickEmpresas) {
          try {
            DtUsuario empresa = cliente.obtenerDtusuario(nickEmpresa);
            listaEmpresas.add(empresa);
          } catch (IOException_Exception | UsuarioNoExisteException_Exception e) {
            request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
            e.printStackTrace();
          }
        }
        
        request.setAttribute("listaEmpresas", listaEmpresas);
        String forwardPath = "/WEB-INF/mobile/consultas/listarEmpresasMobile.jsp";
        request.getRequestDispatcher(forwardPath).forward(request, response);
        return;
      }
    } else {
      if (nicknameEmpresa == null && keyword == null) {
        ArrayList<String> listaNickEmpresas = (ArrayList<String>) cliente.listarEmpresas()
            .getItem();
        ArrayList<DtUsuario> listaEmpresas = new ArrayList<DtUsuario>();
        
        for (String nickEmpresa : listaNickEmpresas) {
          try {
            DtUsuario empresa = cliente.obtenerDtusuario(nickEmpresa);
            listaEmpresas.add(empresa);
          } catch (IOException_Exception | UsuarioNoExisteException_Exception e) {
            request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
            e.printStackTrace();
          }
        }
        
        request.setAttribute("listaEmpresas", listaEmpresas);
        String forwardPath = "/WEB-INF/consultas/listarEmpresas.jsp";
        request.getRequestDispatcher(forwardPath).forward(request, response);
        return;
      }
    }
    if (userAgent != null && userAgent.toLowerCase().contains("mobile")) {
      ArrayList<DtOfertaLaboral> ofertas = null;
      
      if (nicknameEmpresa != null) {
        try {
          ofertas = (ArrayList<DtOfertaLaboral>) cliente
              .obtenerDtofertasConfirmadasDeEmpresa(nicknameEmpresa).getItem();
        } catch (IOException_Exception | UsuarioNoExisteException_Exception e) {
          request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
          e.printStackTrace();
          return;
        }
      } else if (keyword != null) {
        try {
          ofertas = (ArrayList<DtOfertaLaboral>) cliente.obtenerDtofertasPorKeyword(keyword)
              .getItem();
        } catch (IOException_Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      
      request.setAttribute("listaOfertas", ofertas);
      request.getRequestDispatcher("/WEB-INF/mobile/consultas/ConsultaOfertasMobile.jsp")
          .forward(request, response);
    } else {
      ArrayList<DtOfertaLaboral> ofertas = null;
      
      if (nicknameEmpresa != null) {
        try {
          ofertas = (ArrayList<DtOfertaLaboral>) cliente
              .obtenerDtofertasConfirmadasDeEmpresa(nicknameEmpresa).getItem();
        } catch (IOException_Exception | UsuarioNoExisteException_Exception e) {
          request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
          e.printStackTrace();
          return;
        }
      } else if (keyword != null) {
        try {
          ofertas = (ArrayList<DtOfertaLaboral>) cliente.obtenerDtofertasPorKeyword(keyword)
              .getItem();
        } catch (IOException_Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      
      request.setAttribute("listaOfertas", ofertas);
      request.getRequestDispatcher("/WEB-INF/consultas/ConsultaOfertas.jsp").forward(request,
          response);
    }
  }
  
  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
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
    procesarRequest(request, response);
  }
  
}
