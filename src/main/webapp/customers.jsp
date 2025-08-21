<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="https://jakarta.ee/jstl/core" %>
<html><head><title>Customers</title></head>
<body>
<h2>Customers</h2>
<a href="customers?action=new">Add Customer</a> |
<a href="billing">Calculate Bill</a> |
<a href="help">Help</a> |
<a href="index.jsp">Home</a>
<table border="1" cellpadding="6">
<tr><th>Account</th><th>Name</th><th>Address</th><th>Telephone</th><th>Units</th><th>Actions</th></tr>
<c:forEach items="${customers}" var="c">
<tr>
<td>${c.accountNumber}</td>
<td>${c.name}</td>
<td>${c.address}</td>
<td>${c.telephone}</td>
<td>${c.unitsConsumed}</td>
<td>
  <a href="customers?action=edit&account=${c.accountNumber}">Edit</a>
  <a href="customers?action=delete&account=${c.accountNumber}" onclick="return confirm('Delete?')">Delete</a>
</td>
</tr>
</c:forEach>
</table>
</body></html>
