<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 18/02/20
  Time: 12:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Report Management</title>
</head>
<body>
<br>
<div class="container">
    <br><a href="home">
    <button type="button" class="btn btn-dark">Main Menu</button>
</a><br><br>
    <h4 class="label label-info">Report On Specific Date</h4><br>
    <f:form action="onDate" method="post">
        <label>Enter Date :
            <input type="date" name="onDate" required>
        </label><br><br>
        <button type="submit" class="btn btn-success">Report On Specific Date</button>
    </f:form>
</div>
<div class="container">
    <br><br>
    <h4 class="label label-info">Report On Specific Product And Between Date</h4><br>
    <f:form action="btwDate" method="post">
        <label>Start Date :<input type="date" name="startDate" required></label><br><br>
        <label>End Date :<input type="date" name="endDate" required></label><br><br>
        <label>Product Id :<input type="number" name="productId" required></label><br><br>
        <button type="submit" class="btn btn-success" >Report On Specific Product And Between Date</button>
    </f:form>
</div>
</body>
</html>
