<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
 <jsp:attribute name="premiere_bulle">
   <div>
	<h1>Résultat de la recherche</h1>
	<ul>
		<c:forEach items="${requestScope.listefilms}" var="film">
			<li><c:out value="${film.titre}" />, <c:out value="${film.annee}" />, <c:out value="${film.pays.nom}"/>. 
			<br/> par <c:out value="${film.realisateur.prenom}"/> <c:out value="${film.realisateur.nom}" />
			<br/> 
			avec le/les personnage(s) de : <br/> 
				<c:forEach items="${film.roles}" var="role">
					- <c:out value="${role.nom}"/>, joué par <c:out value="${role.acteur.prenom}"/><c:out value="${role.acteur.nom}"/><br/>
				</c:forEach> 
			</li>
		</c:forEach>
	</ul>
	<p>
		<a href="${pageContext.request.contextPath}/Hibernate">Accueil</a>
	</p>
  </div>
 </jsp:attribute>
</t:layout>