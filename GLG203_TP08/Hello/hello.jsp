<%@ page import="java.util.Date"%>
<html>
<head>
	<title>Hello PetStore!</title>
</head>
<body>
    <% Date today = new Date();%>
    <%
        for (int i=0; i<5; i++) {
            out.println("Hello Petstore!<BR>");
        }
    %>
    <BR>
    <center><%= today %></center>
</body>