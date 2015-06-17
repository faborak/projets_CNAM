<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
 <jsp:attribute name="texte_principal">
   <div>
	<h1>Recherche d'un gisement de bouzon sur le site de production :</h1>
	<hr />
	<form method="post"
		action="${pageContext.request.contextPath}/gestion">
		Nom du gisement : <input type="text" size="20" name="nom" /> <br/> <br/>
		Rendement compris entre : <input type="text" size="20" name="rendement_min" /> <br/> <br/>
		et <input type="text" size="20" name="rendement_max" /> <br /> <br/>
		Date de mise en service : <input type="text" size="20" name="date_mise_en_service" /> <br /> <br />
		<input type="submit" value="Rechercher un Bouzon" />
		
	</form>
	<hr />
	
	</br>
	
	<a href="${pageContext.request.contextPath}/accueil">Retour à l'accueil</a>
	
  </div>
 </jsp:attribute>
</t:layout>