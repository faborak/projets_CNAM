<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu JDBC</title>
</head>
<body>
	<h1>Actions JDBC</h1>
	<ul>
		<li><a
			href="${pageContext.request.contextPath}/Jdbc?action=connexion">Connexion</a>
		<li><a
			href="${pageContext.request.contextPath}/Jdbc?action=requeteA">RequêteA</a></li>
		<li><a
			href="${pageContext.request.contextPath}/Jdbc?action=requeteB">RequêteB</a></li>
		<li><a
			href="${pageContext.request.contextPath}/Jdbc?action=requeteC">RequêteC</a></li>
	</ul>
</body>
</html>