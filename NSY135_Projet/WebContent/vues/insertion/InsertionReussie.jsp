<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
 <jsp:attribute name="texte_principal">
   <div>
	<h1>Insertion d'un humain dans les effectifs :</h1>
	<hr />

    Insertion réussie, l'humain est visible dans les listes sur la <a href="${pageContext.request.contextPath}/accueil">page d'accueil</a>

	<hr />
	
	</br>
	
	
	
  </div>
 </jsp:attribute>
</t:layout>