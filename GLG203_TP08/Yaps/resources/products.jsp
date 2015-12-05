<%@page import="java.util.Collection"%>
<%@page import="java.util.List"%>
<%@ page import="com.yaps.petstore.common.dto.ProductDTO"%>
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

        <td align="left" valign="top" width="60%">
        <%--CENTRAL BODY--%>

              <%  Collection<ProductDTO> productsDTO  =  (Collection<ProductDTO>) request.getAttribute("productsDTO");%> 
              <%  for (ProductDTO productDTO : productsDTO) { %> 
                     <A href="<%= request.getContextPath() %>/finditems?productId=<%out.println(productDTO.getId());%>"><%out.println(productDTO.getName()); %></A><BR>
                     <%   out.println(productDTO.getDescription());  %> 
                     </br>
               <%     } %>
               
               

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