<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
	<jsp:attribute name="premiere_bulle">
   <div>

      <h2>Le film no 1</h2>

      Nous avons bien ramené le film ${filmparcle.titre}. Et c'est tout.

  </div>
 </jsp:attribute>
</t:layout>