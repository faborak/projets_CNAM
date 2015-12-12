<%@ taglib uri="/WEB-INF/petstore.tld" prefix="hp" %>

<html>
<head>
    <title>Hello PetStore!</title>
</head>
<body>
    <jsp:useBean id="today" class="java.util.Date"/>
    <hp:hello/>
    <BR>
    <center>${today}</center>
</body>
</html>
