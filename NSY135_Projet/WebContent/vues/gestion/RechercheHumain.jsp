<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
 <jsp:attribute name="texte_principal">
   <div>
	<h1>Recherche d'un humain dans les effectifs :</h1>
	<hr />
	<form method="post"
		action="${pageContext.request.contextPath}/gestion">
		Nom : <input type="text" size="20" name="nom" /> <br/> <br/>
		Salaire compris entre : <input type="text" size="20" name="salaire_min" /> <br/> <br/>
		et <input type="text" size="20" name="salaire_max" /> <br /> <br/>
		Equipe : <input type="text" size="20" name="equipe" /> <br /> <br />
		<input type="submit" value="Rechercher un employé" />
		
	</form>
	<hr />
	
	</br>
	
	<a href="${pageContext.request.contextPath}/accueil">Retour à l'accueil</a>
	
  </div>
 </jsp:attribute>
</t:layout>