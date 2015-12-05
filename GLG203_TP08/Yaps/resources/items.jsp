<%@page import="java.util.Collection"%>
<%@ page import="com.yaps.petstore.common.dto.ProductDTO"%>
<%@ page import="com.yaps.petstore.common.dto.ItemDTO"%>
<html>
<head>
	<title>YAPS Error</title>
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

			<%  Collection<ItemDTO> ItemsDTO  =  (Collection<ItemDTO>) request.getAttribute("ItemsDTO");%> 
              <%  ProductDTO productDTO  =  (ProductDTO) request.getAttribute("ProductDTO");%> 
              <%  for (ItemDTO itemDTO : ItemsDTO) { %> 
              <A href="<%= request.getContextPath() %>/finditem?itemId=<% itemDTO.getId(); %>">itemDTO.getName();</A><BR>
                     <%-- nom de l'item : <% out.println(itemDTO.getName()); %>  --%>
                     description :  <%   out.println(productDTO.getDescription());  %> 
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