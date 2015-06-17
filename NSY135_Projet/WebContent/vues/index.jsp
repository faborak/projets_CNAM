<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
 <jsp:attribute name="texte_principal">
   <div>
	<h1>Parcourir la base :</h1>
	</br>
	<ul>
		<li><a href="${pageContext.request.contextPath}/accueil?action=ListeGisements">Lister les Gisements</a></li> 
		<li><a href="${pageContext.request.contextPath}/accueil?action=ListeEquipes">Lister les Equipes</a></li>
		<li><a href="${pageContext.request.contextPath}/accueil?action=ListeOuvriers">Lister les Ouvriers</a></li> 
		<li><a href="${pageContext.request.contextPath}/accueil?action=ListeModeles">Lister les Modèles</a></li>  
   </ul>
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

 <jsp:attribute name="colonne_2">
   <div>
	<h1>Work in progress</h1>
	<ul>
<%-- 	<li><a href="${pageContext.request.contextPath}/Jdbc?action=connexion">Connexion</a></li>
		<li><a href="${pageContext.request.contextPath}/Jdbc?action=requeteA">RequêteA</a></li>
		<li><a href="${pageContext.request.contextPath}/Jdbc?action=requeteB">RequêteB</a></li>
		<li><a href="${pageContext.request.contextPath}/Jdbc?action=requeteC">RequêteC</a></li> --%>
	</ul>
  </div>
 </jsp:attribute> 
 
  <jsp:attribute name="colonne_3">
   <div>
	<h1>Work in progress</h1>
	<ul>
<%-- 	<li><a href="${pageContext.request.contextPath}/Jdbc?action=connexion">Connexion</a></li>
		<li><a href="${pageContext.request.contextPath}/Jdbc?action=requeteA">RequêteA</a></li>
		<li><a href="${pageContext.request.contextPath}/Jdbc?action=requeteB">RequêteB</a></li>
		<li><a href="${pageContext.request.contextPath}/Jdbc?action=requeteC">RequêteC</a></li> --%>
	</ul>
  </div>
 </jsp:attribute> 
 
</t:layout>