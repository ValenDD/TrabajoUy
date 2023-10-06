<%@page import="logica.datatypes.Dtusuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Consulta de Usuarios</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resource/css/general.css">
    <jsp:include page="../include/Head.jsp"/>
</head>
<body>
    <jsp:include page="../include/NavBar.jsp"/>
    <main class="container pt-5">
        <div class="row">
            <jsp:include page="../include/Menu.jsp"/>
            <div class="col-8">
                <section>
                    <%
                    ArrayList<Dtusuario> listaUsuarios = (ArrayList<Dtusuario>) request.getAttribute("listaUsuarios");
                    String tipoEsperadoVar = (String) request.getAttribute("tipoEsperado");
                    for (Dtusuario usuario : listaUsuarios) {
                        String tipoUser = usuario.getClass().getSimpleName();
                    %>
                        <div class="card">
                            <div class="row g-0">
                                <div class="col-md-3 justify-content-center align-items-center d-flex">
                                    <img
                                        src="<%= usuario.getImagen() %>"
                                        class="img-fluid rounded-start"
                                        alt="Imagen PerfilUsuario"
                                    />
                                </div>
                                <div class="col-md-9">
                                    <div class="card-body">
                                        <h5 class="card-header p-0 border-0 bg-white text-start">
                                            <%= usuario.getNickname() %>
                                        </h5>
                                        <%
                                        if (tipoUser.equals(tipoEsperadoVar)) {
                                        %>
                                            <p class="card-text">Tipo: Postulante</p>
                                        <%
                                        } else {
                                        %>
                                            <p class="card-text">Tipo: Empresa</p>
                                        <%
                                        }
                                        %>
                                    </div>
                                    <div class="card-footer border-0 bg-white text-end">
                                    <%
                                    String contextPath = request.getContextPath();
                                    %>
                                       <a href="<%= contextPath %>/perfil?nicknameUsuario=<%= usuario.getNickname() %>" class="btn btn-primary">Perfil</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                    <%
                    }
                    %>
                </section>
            </div>
        </div>
    </main>
</body>
</html>