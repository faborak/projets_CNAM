<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
 <jsp:attribute name="texte_principal">
   <div>
	<h1>Recherche d'un être humain dans la base :</h1>
	<hr />
	<form method="post"
		action="${pageContext.request.contextPath}/Gestion">
        <c:forEach items="${requestScope.listehumains}" var="humain">
			 <li> L'humain <c:out value="${humain.nom}" />  
			 a comme salaire <c:out value="${humain.salaire}" /> euros,
			  et un cout de <c:out value="${humain.cout}" /> euros. </li>	
		</c:forEach> 
        
	</form>
	<hr />
	
	</br>
	
	<a href="${pageContext.request.contextPath}/accueil">Retour à l'accueil</a>
	
  </div>
 </jsp:attribute>
</t:layout>