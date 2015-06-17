<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
	<jsp:attribute name="premiere_bulle">
   <div>
	<h1>Fin de l'insertion :</h1>
	<hr />

Insertion réussie.

	<hr />
	
	<p>
	<a href="${pageContext.request.contextPath}/Hibernate">Accueil</a>
    </p>
  </div>
 </jsp:attribute>
</t:layout>