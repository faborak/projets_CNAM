<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

 <t:layout>
  <jsp:attribute name="premiere_bulle">
   <div>
<!-- <title>Recherche avec jointure par HQL</title> -->


	<h1>Liste des vidéos</h1>
	<ul>
		<c:forEach items="${requestScope.listevideos}" var="video">
			<li>
			La video <c:out value="${video.titre}" /> est sortie en 
			<c:out value="${video.annee}" />. 
			</li>
		</c:forEach>
	</ul>
	<p>
		<a href="${pageContext.request.contextPath}/Hibernate">Accueil</a>
	</p>

  </div>
 </jsp:attribute>
</t:layout>