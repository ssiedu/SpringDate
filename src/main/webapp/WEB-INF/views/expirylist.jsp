<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Product List Expiring After : ${edate}</h3>
<hr>
<table border="2">
<tr><td>PCode</td><td>Name</td><td>Price</td><td>Expiry</td></tr>
<c:forEach var="product" items="${products}">
<tr>
	<td>${product.pcode}</td>
	<td>${product.pname}</td>
	<td>${product.price}</td>
	<td>${product.expiry}</td>
</tr>
</c:forEach>
</table>
<hr>
<a href="index.jsp">Home</a>
</body>
</html>