<%@page import="logica.DataTypes.DTOfertaLaboral"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>

<% ArrayList<String> listaKeywords = (ArrayList<String>)session.getAttribute("listaKeywords"); %>


</head>
<body>
	<h1>Keywords</h1>
	<c:forEach var="key" items="${listaKeywords}">
	${key} <br>
	</c:forEach>

	<h1>Ofertas</h1>

	<c:forEach var="oferta" items="${listaOfertasConfirmadas}">

		<h2>Nombre Oferta</h2>
		<a href="/oferta?nombreOferta=${oferta.nombre}">${oferta.nombre}</a>
		<br>
		<h3>Kewords</h3>
		<c:forEach var="keyword" items="${oferta.keywords}">
			<li>${keyword}</li>
		</c:forEach>


		<h2>Descripción</h2>
		${oferta.descripcion}<br>


	</c:forEach>

<a href="/oferta">a ver si anda</a>
</body>
</html>