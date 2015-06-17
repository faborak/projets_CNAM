<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
 <jsp:attribute name="texte_principal">
   <div>
	<h1>Recherche d'un robot dans les effectifs :</h1>
	<hr />
	<form method="post"
		action="${pageContext.request.contextPath}/gestion">
		Nom : <input type="text" size="20" name="nom" /> <br/> <br/>
		Numéro de serie compris entre : <input type="text" size="20" name="numero_min" /> <br/> <br/>
		et <input type="text" size="20" name="numero_max" /> <br /> <br/>
		Modèle : <input type="text" size="20" name="modele" /> <br /> <br />
		<input type="submit" value="Rechercher un Robot" />
		
	</form>
	<hr />
	
	</br>
	
	<a href="${pageContext.request.contextPath}/accueil">Retour à l'accueil</a>
	
  </div>
 </jsp:attribute>
</t:layout>