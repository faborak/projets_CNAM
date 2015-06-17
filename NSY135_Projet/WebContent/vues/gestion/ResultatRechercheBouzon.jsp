<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
 <jsp:attribute name="texte_principal">
   <div>
	<h1>Recherche de bouzon dans la base :</h1>
	<hr />
	<form method="post"
		action="${pageContext.request.contextPath}/Gestion">
        <c:forEach items="${requestScope.listebouzons}" var="bouzon">
			 <li> Le bouzon <c:out value="${bouzon.nom}" />  
			  a comme rendement <c:out value="${bouzon.rendement_mensuel}" />  
			  et un revenu de <c:out value="${bouzon.revenu}" /> euros. </li>	
		</c:forEach> 
        
	</form>
	<hr />
	
	</br>
	
	<a href="${pageContext.request.contextPath}/accueil">Retour à l'accueil</a>
	
  </div>
 </jsp:attribute>
</t:layout>