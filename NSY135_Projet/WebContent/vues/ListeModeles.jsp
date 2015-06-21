<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

 <t:layout>
 
  <jsp:attribute name="texte_principal">
   <div>
	<h1>Liste des modèles</h1>
	
	<ul>
		<c:forEach items="${requestScope.listeModele}" var="modele">
			  <li>Le modèle <c:out value="${modele.nom}" />, conçu le <c:out value="${modele.date_conception}" />, 
			  a un cout d'exploitation mensuel de <c:out value="${modele.cout_exploitation_mensuel}"/>. </li> 
		</c:forEach>
	</ul>
	
	<br/>
	
	<p>
		<a href="${pageContext.request.contextPath}/accueil">Retour à l'accueil</a>
	</p>
   
  </div>
 </jsp:attribute>
 
  <jsp:attribute name="colonne_1">
   <div>

<h1>Gestion et calculs :</h1>
	</br>
	<ul>
		<li><a href="${pageContext.request.contextPath}/gestion?action=TrouverHumain">Trouver un humain</a></li> 
		<li><a href="${pageContext.request.contextPath}/gestion?action=TrouverRobot">Trouver un robot</a></li>
		<li><a href="${pageContext.request.contextPath}/gestion?action=TrouverBouzon">Trouver un gisement de Bouzon</a></li> 
		<li><a href="${pageContext.request.contextPath}/gestion?action=TrouverHzk2">Trouver du gaz Hzk2</a></li>  
		<li><a href="${pageContext.request.contextPath}/gestion?action=TrouverEquipe">Trouver une équipe</a></li>  
	</ul>

  </div>
 </jsp:attribute>
</t:layout>