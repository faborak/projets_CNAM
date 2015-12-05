<%@page import="java.util.Collection"%>
<%@ page import="com.yaps.petstore.common.dto.ProductDTO"%>
<%@ page import="com.yaps.petstore.common.dto.ItemDTO"%>
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

			<%  Collection<ItemDTO> itemsDTO  =  (Collection<ItemDTO>) request.getAttribute("itemsDTO");%> 
              <%  ProductDTO productDTO  =  (ProductDTO) request.getAttribute("productDTO");%> 
                <%  for (ItemDTO itemDTO : itemsDTO) { %> 
              <A href="<%= request.getContextPath() %>/finditem?itemId=<% out.println(itemDTO.getId()); %>"><%out.println(itemDTO.getName());%></A> <font align="right"><%out.println(itemDTO.getUnitCost());%></font><BR>
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