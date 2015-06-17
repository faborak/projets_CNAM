<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recherche de type B</title>
</head>
<body>
	<h1>Liste des films</h1>
	<ul>
		<c:forEach items="${requestScope.films}" var="film">
			<li><c:out value="${film.titre}" />, <c:out value="${film.genre}" /></li>
		</c:forEach>
	</ul>
	<p>
		<a href="${pageContext.request.contextPath}/Jdbc">Accueil</a>
	</p>
</body>
</html>