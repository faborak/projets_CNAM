<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

 <t:layout>
  <jsp:attribute name="premiere_bulle">
   <div>

	<h1>Liste des internautes</h1>
	<ul>
		<c:forEach items="${requestScope.listeinternautes}" var="internaute">
			<li><c:out value="${internaute.prenom}" />, <c:out value="${internaute.nom}" />  <%-- <c:out value="${internaute.email}"/> --%>  
 			<br/> habite à  <c:out value="${internaute.adresse.adresse}"/>, <c:out value="${internaute.adresse.codePostal}" /> <c:out value="${internaute.adresse.ville}"/>  
			<br/>
			et travaille à <c:out value="${internaute.adressePro.adresse}"/>, <c:out value="${internaute.adressePro.codePostal}" /> <c:out value="${internaute.adressePro.ville}"/>
			
			</li> 
		</c:forEach>
	</ul>
	<p>
		<a href="${pageContext.request.contextPath}/Hibernate">Accueil</a>
	</p>

  </div>
 </jsp:attribute>
</t:layout>