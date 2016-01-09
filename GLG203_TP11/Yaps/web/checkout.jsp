<%@ page errorPage="error.jsp"%>
<html>
<head>
	<title>Sign Off</title>
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

        <td align="center" width="60%">
        <%--CENTRAL BODY--%>

                <p><strong>Your Order is Complete.</strong></p>

                <p>Your Order id is {$orderId}.</p>

                <p>Thank you for shopping with the YAPS Pet Store.</p>

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
