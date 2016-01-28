<%@ page errorPage="error.jsp"%>
<%@ taglib uri="/WEB-INF/petstore.tld" prefix="petstore" %>
<html>
<head>
	<title>YAPS PetStore</title>
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



           <%--  <jsp:useBean id="customerDTO" class="com.yaps.petstore.common.dto.CustomerDTO" scope="session" /> --%>

           <c:if test="${lastNameFirstChars == null}">
                <P><strong>All Customer</strong></P>
            </c:if>
            <%-- Else --%>
            <c:if test="${lastNameFirstChars != null}">
                <P><strong>Search Results:</strong></P>
                <P>Customers matching last name: ${lastNameFirstChars}</P>
            </c:if>

            <TABLE cellSpacing=0 cellPadding=1 width="100%" border=1>
                <TR>
                    <TD>
                        <TABLE cellSpacing=0 cellPadding=2 width="100%" border=0>
	
							  <P><strong>Name:</strong> <strong>Credit card number:</strong> <strong>Year of birth :</strong></P>
                            <c:forEach items="${customersDTO}" var="customerDTO">
                                <TR>
                                    <TD>${customerDTO.lastname}</TD> <TD>${customerDTO.firstname}</TD>
                                    <TD>${customerDTO.creditCardNumber}</TD> <TD>${customerDTO.yearOfBirth}</TD> 
                                </TR>
                            </c:forEach>

                        </TABLE>
                    </TD>
                </TR>
            </TABLE>

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