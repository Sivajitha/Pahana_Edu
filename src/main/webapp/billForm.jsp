<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="https://jakarta.ee/jstl/core" %>
<html><head><title>Calculate Bill</title></head>
<body>
<h2>Calculate Bill</h2>
<form method="post" action="billing">
  <label>Account Number: <input name="accountNumber" value="${customer.accountNumber}" required/></label><br/>
  <label>Units: <input type="number" name="units" value="${customer.unitsConsumed}" required/></label><br/>
  <button type="submit">Calculate & Print</button>
  <a href="customers">Back</a>
</form>
</body></html>
