<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

 <t:layout>
  <jsp:attribute name="premiere_bulle">
   <div>

	<h1>Liste des rôles présents en base Webscope</h1>
	<ul>
		<c:forEach items="${requestScope.listeroles}" var="role">
			<li>Le rôle <c:out value="${role.nom}" />
			 a été joué par <c:out value="${role.acteur.prenom}" /> <c:out value="${role.acteur.nom}" /> 
			 dans <c:out value="${role.film.titre}" />, en <c:out value="${role.film.annee}" /> 
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