<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 14/02/20
  Time: 5:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Product Management</title>
</head>
<body>
<div  class="container">
    <br>
    <a href="home">
        <button type="button" class="btn btn-dark">Main Menu</button>
    </a>
    <br><br>
    <c:choose>
        <c:when test="${fn:length(productList) gt 0}">
            <table border="2px" style="border-collapse: collapse" class="table table-striped">
            <th scope="col">Product Id</th>
            <th scope="col">Product Name</th>
            <th scope="col">Product Category</th>
            <th scope="col">Product Price</th>
            <th scope="col">Edit Product</th>
            <th scope="col">Delete Product</th>
            <c:forEach var="invoice" items="${productList}" >
                <tr>
                    <td>${invoice.id}</td>
                    <td>${invoice.name}</td>
                    <td>${invoice.category}</td>
                    <td>${invoice.price}</td>
                    <td><a href="edit?productId=${invoice.id}"  class="btn btn-success">EDIT</a></td>
                    <td><a href="delete?productId=${invoice.id}"  class="btn btn-danger">DELETE</a></td>
                </tr>

            </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            No Product Available
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
