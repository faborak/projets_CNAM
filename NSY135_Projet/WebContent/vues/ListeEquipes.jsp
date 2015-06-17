<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

 <t:layout>
 
  <jsp:attribute name="texte_principal">
   <div>
	<h1>Exploitation minière</h1>
	<ul>
		<li><a href="${pageContext.request.contextPath}/accueil?action=ListeGisements">Lister les Gisements</a></li> 
		<li><a href="${pageContext.request.contextPath}/accueil?action=ListeEquipes">Lister les Equipes</a></li>
		<li><a href="${pageContext.request.contextPath}/accueil?action=ListeOuvriers">Lister les Ouvriers</a></li> 
		<li><a href="${pageContext.request.contextPath}/accueil?action=ListeModeles">Lister les Modèles</a></li> 
   </ul>
   
   <p>
		<a href="${pageContext.request.contextPath}/accueil">Retour à l'accueil</a>
	</p>
   
  </div>
 </jsp:attribute>
 
  <jsp:attribute name="colonne_1">
   <div>
<!-- <title>Recherche avec jointure par HQL</title> -->


	<h1>Liste des équipes</h1>
	
	<ul>
		<c:forEach items="${requestScope.ListeEquipe}" var="equipe">
			  <li>L'equipe<c:out value="${equipe.nom}" /> 
			   travaille sur <c:out value="${equipe.gisementEnCours.nom}" />.  
			 
			  Elle est dirigée par <c:out value="${equipe.manager.nom}" />.
			  
			    Elle est composée de : 
			  <c:forEach items="${equipe.personnel}" var="ouvrier">
			  	, <c:out value="${ouvrier.nom}" />
			  </c:forEach>. 
		</c:forEach>
		</li>
	</ul>
	
	 </br>
	
	<p>
		<a href="${pageContext.request.contextPath}/accueil">Retour à l'accueil</a>
	</p>

  </div>
 </jsp:attribute>
</t:layout>