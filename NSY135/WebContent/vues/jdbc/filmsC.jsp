<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

 <t:layout>
  <jsp:attribute name="body_area">
   <div>

	<h1>Liste des films</h1>
	<ul>
		<c:forEach items="${requestScope.films}" var="film">
			<li><c:out value="${film.titre}" />, <c:out value="${film.annee}" />, <c:out value="${film.id_realisateur}" /></li>
		</c:forEach>
	</ul>
	<p>
		<a href="${pageContext.request.contextPath}/Jdbc">Accueil</a>
	</p>

  </div>
 </jsp:attribute>
</t:layout>