<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

 <t:layout>
  <jsp:attribute name="body_area">
   <div>
	<h1>Actions JDBC</h1>
	<ul>
		<li><a href="${pageContext.request.contextPath}/Jdbc?action=connexion">Connexion</a></li>
		<li><a href="${pageContext.request.contextPath}/Jdbc?action=requeteA">RequêteA</a></li>
		<li><a href="${pageContext.request.contextPath}/Jdbc?action=requeteB">RequêteB</a></li>
		<li><a href="${pageContext.request.contextPath}/Jdbc?action=requeteC">RequêteC</a></li>
	</ul>
  </div>
 </jsp:attribute>
</t:layout>