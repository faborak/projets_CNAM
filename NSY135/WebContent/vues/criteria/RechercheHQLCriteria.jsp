<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
 <jsp:attribute name="premiere_bulle">
   <div>
	<h1>Recherche d'un film dans la base :</h1>
	<hr />
	<form method="post"
		action="${pageContext.request.contextPath}/Requeteur">
		Titre du film : <input type="text" size="20" name="titre" /> <br />
		Année : <input type="text" size="20" name="annee" /> <br />
		Genre : <input type="text" size="20" name="genre" /> <br /> <br />
		<input type="submit" value="Rechercher par HQL" />
	</form>
	<hr />
  </div>
 </jsp:attribute>
</t:layout>