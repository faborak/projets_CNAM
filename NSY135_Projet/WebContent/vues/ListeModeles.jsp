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


	<h1>Liste des modèles</h1>
	
	<ul>
		<c:forEach items="${requestScope.listeModele}" var="modele">
			  <li><c:out value="${modele.nom}" />, conçu le <c:out value="${modele.date_conception}" />, 
			  avec un cout d'exploitation mensuel de <c:out value="${modele.cout_exploitation_mensuel}"/> </li> 
		</c:forEach>
	</ul>
	
	<br/>
	
	<p>
		<a href="${pageContext.request.contextPath}/accueil">Retour à l'accueil</a>
	</p>

  </div>
 </jsp:attribute>
</t:layout>