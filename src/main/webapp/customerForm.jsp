<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="https://jakarta.ee/jstl/core" %>
<html><head><title>Customer Form</title></head>
<body>
<h2>${customer == null ? "Add" : "Edit"} Customer</h2>
<form method="post" action="customers">
  <label>Account Number: <input name="accountNumber" value="${customer.accountNumber}" ${customer != null ? "readonly" : ""} required/></label><br/>
  <label>Name: <input name="name" value="${customer.name}" required/></label><br/>
  <label>Address: <input name="address" value="${customer.address}" required/></label><br/>
  <label>Telephone: <input name="telephone" value="${customer.telephone}" required/></label><br/>
  <label>Units Consumed: <input type="number" name="unitsConsumed" value="${customer.unitsConsumed}" required/></label><br/>
  <input type="hidden" name="mode" value="${customer == null ? "create" : "update"}"/>
  <button type="submit">Save</button>
  <a href="customers">Cancel</a>
</form>
</body></html>
