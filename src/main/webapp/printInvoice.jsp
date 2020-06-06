<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 18/02/20
  Time: 4:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Print Invoice</title>
</head>
<body>
<div class="container">
<br><a href="home">
    <button type="button" class="btn btn-dark">Main Menu</button>
</a><br><br>

<f:form action="printInvoice" method="post">
    <label>
        Enter Order Id : <input type="number" name="orderId" required/>
    </label><br><br>
    <button type="submit" class="btn btn-success">Print Invoice</button>
</f:form>
</div>
</body>
</html>
