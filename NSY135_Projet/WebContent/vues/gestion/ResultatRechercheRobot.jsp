<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
 <jsp:attribute name="texte_principal">
   <div>
	<h1>Recherche d'un robot dans la base :</h1>
	<hr />
	<form method="post"
		action="${pageContext.request.contextPath}/Gestion">
        <c:forEach items="${requestScope.listerobots}" var="robot">
			 <li> Le robot <c:out value="${robot.nom}" />  
			 a comme numéro de série <c:out value="${robot.numero_serie}" /> 
			  et un cout de <c:out value="${robot.cout}" /> euros. </li>	
		</c:forEach> 
        
	</form>
	<hr />
	
	</br>
	
	<a href="${pageContext.request.contextPath}/accueil">Retour à l'accueil</a>
	
  </div>
 </jsp:attribute>
</t:layout>