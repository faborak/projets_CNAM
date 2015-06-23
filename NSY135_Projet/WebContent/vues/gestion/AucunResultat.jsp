<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
 <jsp:attribute name="texte_principal">
   <div>
	<h1>Résultat de la recherche :</h1>
	<hr />
	<form method="post"
		action="${pageContext.request.contextPath}/Gestion">

        Désolé, il n'existe pas de correspondance dans la base pour votre recherche.
        
        </br> </br>
        
        <a href="${pageContext.request.contextPath}/accueil">Retour à l'accueil</a>
        
	</form>

  </div>
 </jsp:attribute>
</t:layout>