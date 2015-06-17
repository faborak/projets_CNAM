<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recherche avec API Criteria et HQL</title>
</head>
<body>

	<h1>Liste des pays</h1>
	<ul>
		<c:forEach items="${requestScope.listepays}" var="pays">
			<li><c:out value="${pays.code}" />, <c:out value="${pays.nom}" />, <c:out value="${pays.langue}" /></li>
		</c:forEach>
	</ul>
	<p>
		<a href="${pageContext.request.contextPath}/Hibernate">Accueil</a>
	</p>

</body>
</html>