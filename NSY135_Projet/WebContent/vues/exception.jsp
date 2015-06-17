<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
	<jsp:attribute name="texte_principal">
   <div>
	<h1>Exception détectée :</h1>
	<hr />

Vue exception <br />
${requestScope.message}

	<hr />
	
	<p>
	<a href="${pageContext.request.contextPath}/accueil">Accueil</a>
    </p>
  </div>
 </jsp:attribute>
</t:layout>