<%@ page errorPage="error.jsp"%>
<%@ taglib uri="/WEB-INF/petstore.tld" prefix="petstore" %>
<html>
<head>
	<title>YAPS PetStore - Create Customer</title>
</head>
<body>

<table cellspacing="0" cellpadding="5" width="100%">
    <%--HEADER--%>
	<tr>
		<td colspan="3">
			<jsp:include page="common/header.jsp"/>
		</td>
	</tr>

	<tr>
        <%--NAVIGATION--%>
        <td valign="top" width="20%">
    		<jsp:include page="common/navigation.jsp"/>
    	</td>

        <td align="left" width="60%">
        <%--CENTRAL BODY--%>

                <h2>Your order is complete</h2>
 				</br> Your order is ${orderId}
 				</br> Thank your for shopping with the YAPS Pet Store

    <%--FOOTER--%>
    	</td>
        <td></td>
    </tr>
    <tr>
    	<td colspan="3">
    		<jsp:include page="common/footer.jsp"/>
    	</td>
    </tr>
</table>
</body>
</html>