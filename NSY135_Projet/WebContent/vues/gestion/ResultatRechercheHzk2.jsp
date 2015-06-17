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
        <c:forEach items="${requestScope.listehzk2}" var="hzk2">
			 <li> Le hzk2 <c:out value="${hzk2.nom}" />  
			 a comme rendement <c:out value="${hzk2.rendement_mensuel}" /> 
			  et un revenu de <c:out value="${hzk2.revenu}" />, arrondi à deux décimales. </li>	
		</c:forEach> 
        
	</form>
	<hr />
	
	</br>
	
	<a href="${pageContext.request.contextPath}/accueil">Retour à l'accueil</a>
	
  </div>
 </jsp:attribute>
</t:layout>