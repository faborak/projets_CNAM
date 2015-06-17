<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
	<jsp:attribute name="premiere_bulle">
   <div>

<h2>Les films</h2>

Voici les films ramenés:

<ul>
        <c:forEach items="${films}" var="film">
                <li>Nous avons bien ramené le film ${film.titre} avec les roles suivants :
                <c:forEach items="${film.roles}" var="role">
			 	 	<c:out value="${role.nom}" />, 
				 </c:forEach> .
                </li>
        </c:forEach>
</ul>

	<p>
	<a href="${pageContext.request.contextPath}/Requeteur">Accueil</a>

  </div>
 </jsp:attribute>
</t:layout>