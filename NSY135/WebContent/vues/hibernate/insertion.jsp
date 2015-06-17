<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
 <jsp:attribute name="premiere_bulle">
   <div>
	<h1>Vous pouvez insérer une ligne dans la table Pays :</h1>
	<hr />
	<form method="post"
		action="${pageContext.request.contextPath}/Hibernate">
		CodePays: <input type="text" size="20" name="codepays" /> <br />
		Nom: <input type="text" size="20" name="nom" /> <br />
		Langue: <input type="text" size="20" name="langue" /> <br /> <br />
		<input type="submit" value="insérer" />
	</form>
	<hr />
  </div>
 </jsp:attribute>
</t:layout>