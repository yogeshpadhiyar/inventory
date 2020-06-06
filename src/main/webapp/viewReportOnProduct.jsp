<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 18/02/20
  Time: 3:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Report Management | On Product </title>
</head>
<body>
<div class="container">
    <br><a href="home">
    <button type="button" class="btn btn-dark">Main Menu</button>
</a><br><br>
<c:choose>
    <c:when test="${invoice.quantity gt 0}">
    <label>Start Date : <c:out value="${startDate}"/></label><br>
    <label>End Date : <c:out value="${endDate}"/></label><br>
    <label>Product Id : <c:out value="${productId}"/></label><br><br>
    <table border="2px" style="border-collapse: collapse" class="table table-striped">
        <th scope="col">Product Name</th>
        <th scope="col">Product Quantity</th>
        <th scope="col">Total Price</th>

            <tr>
                <td>${invoice.productName}</td>
                <td>${invoice.quantity}</td>
                <td>${invoice.price}</td>
            </tr>
    </table>
        <label><b>Grand Total : ${invoice.price}</b></label>
    </c:when>
    <c:otherwise>
        <div class="alert alert-warning"><h3>Nothing sale on this date</h3></div>
    </c:otherwise>
</c:choose>
</div>
</body>
</html>
