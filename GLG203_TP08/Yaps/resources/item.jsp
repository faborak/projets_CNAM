<%@page import="java.util.Collection"%>
<%@page import="java.util.List"%>
<%@ page import="com.yaps.petstore.common.dto.ItemDTO"%>
<%@ page import="com.yaps.petstore.common.dto.ProductDTO"%>
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

              <%  ItemDTO itemDTO =  (ItemDTO) request.getAttribute("Item");%> 
                     <% out.println(itemDTO.getName()); %> 
                     <img src=" <%itemDTO.getImagePath();%> " /> 
                     Unit Cost :  <%   out.println(itemDTO.getUnitCost());  %> 

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