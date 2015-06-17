<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
 <jsp:attribute name="premiere_bulle">
   <div>
	<h1>Actions Hibernate à partir du chapitre 10</h1>
	<ul>
		<li><a href="${pageContext.request.contextPath}/Requeteur?action=LectureParCle">Lecture par cle</a></li> 
		<li><a href="${pageContext.request.contextPath}/Requeteur?action=LectureParTitre">Lecture par titre</a></li> 
		<li><a href="${pageContext.request.contextPath}/Requeteur?action=RechercheHQLCriteria">Lecture par HQL/Criteria</a></li> 
		<li><a href="${pageContext.request.contextPath}/Requeteur?action=ChargementParLot">ChargementParLot</a></li> 
				<li><a href="${pageContext.request.contextPath}/Requeteur?action=DeleteGravity">Delete de Gravity et des acteurs</a></li> 
		<li><a href="${pageContext.request.contextPath}/Requeteur?action=DynamiqueDesObjetsPersistants">Dynamique Des Objets Persistants</a></li> 
		<li><a href="${pageContext.request.contextPath}/Requeteur?action=InsertionEnCascade">Insertion en Cascade</a></li> 
   </ul>
  </div>
 </jsp:attribute>

 <jsp:attribute name="deuxieme_bulle">
   <div>
	<h1>Actions Hibernate, jusqu'au chapitre 9</h1>
	<ul>
		<li><a href="${pageContext.request.contextPath}/Hibernate?action=connexion">Connexion</a></li>
		<li><a href="${pageContext.request.contextPath}/Hibernate?action=insertion">InsertionPays</a></li>
		<li><a href="${pageContext.request.contextPath}/Hibernate?action=lecture">Lecture</a></li>
		<li><a href="${pageContext.request.contextPath}/Hibernate?action=lectureHQL">LectureHQL</a></li>
		<li><a href="${pageContext.request.contextPath}/Hibernate?action=ListeDeFilms">Liste de Films</a></li>
		<li><a href="${pageContext.request.contextPath}/Hibernate?action=ListeInternautes">Liste des Internautes</a></li>
		<li><a href="${pageContext.request.contextPath}/Hibernate?action=InsertionGravity">Insertion du film Gravity</a></li>
		<li><a href="${pageContext.request.contextPath}/Hibernate?action=AffichageArtistes">Affichage des artistes et des films qu'ils ont réalisé</a></li>
		<li><a href="${pageContext.request.contextPath}/Hibernate?action=AffichageRoles">Affichage de tous les rôles de la base Webscope</a></li>
        <li><a href="${pageContext.request.contextPath}/Hibernate?action=AffichageVideos">Affichage de vidéos (héritage)</a></li>	
	</ul>
  </div>
 </jsp:attribute>

 <jsp:attribute name="troisieme_bulle">
   <div>
	<h1>Actions JBDC</h1>
	<ul>
		<li><a href="${pageContext.request.contextPath}/Jdbc?action=connexion">Connexion</a></li>
		<li><a href="${pageContext.request.contextPath}/Jdbc?action=requeteA">RequêteA</a></li>
		<li><a href="${pageContext.request.contextPath}/Jdbc?action=requeteB">RequêteB</a></li>
		<li><a href="${pageContext.request.contextPath}/Jdbc?action=requeteC">RequêteC</a></li>
	</ul>
  </div>
 </jsp:attribute> 
</t:layout>