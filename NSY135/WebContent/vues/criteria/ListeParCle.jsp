<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
	<jsp:attribute name="premiere_bulle">
   <div>

	<h2>Le film par clé id n° ${filmparcle.id}</h2>
	Nous avons bien ramené le film ${filmparcle.titre}. <br/>
	<h2>Son réalisateur</h2>
	Et son réalisateur est ${filmparcle.realisateur.nom}. <br/>
	<h2>Lequel a également réalisé ...</h2>
	<ul>
		<c:forEach items="${filmparcle.realisateur.filmsRealises}" var="film">
			<li>${film.titre}</li>
		</c:forEach>
	</ul>
	<p>
		<a href="${pageContext.request.contextPath}/Requeteur">Accueil</a>
	</p>

  </div>
 </jsp:attribute>
</t:layout>