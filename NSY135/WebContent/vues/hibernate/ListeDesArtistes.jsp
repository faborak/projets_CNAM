<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

 <t:layout>
  <jsp:attribute name="premiere_bulle">
   <div>

	<h1>Liste des Artistes présents en base Webscope et des films qu'ils ont réalisé</h1>
	<ul>
		<c:forEach items="${requestScope.listeartistes}" var="artiste">
			<li>L'artiste <c:out value="${artiste.prenom}" /> <c:out value="${artiste.nom}" />
			 a réalisé 
		 	 <c:forEach items="${artiste.filmsRealises}" var="film">
			 	 <c:out value="${film.titre}" />, 
			 </c:forEach> 
			<br/> 
			</li>
		</c:forEach>
	</ul>
	<p>
		<a href="${pageContext.request.contextPath}/Hibernate">Accueil</a>
	</p>

  </div>
 </jsp:attribute>
</t:layout>