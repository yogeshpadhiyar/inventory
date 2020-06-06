<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 18/02/20
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>View Invoice</title>
</head>
<body>
<div class="container">
    <br><a href="home">
    <button type="button" class="btn btn-dark">Main Menu</button>
</a><br>
    <c:set var="count" value="1"/>
    <c:set var="totalPrice" value="0"/>
    <c:choose>
        <c:when test="${fn:contains(order,'order')}">
            <label>Order Id : <c:out value="${order.id}"/></label><br><br>
            <label>Order Date : <c:out value="${order.date}"/></label><br><br>
        </c:when>
        <c:when test="${fn:contains(onDate,'onDate')}">
            <label>Date : <c:out value="${onDate}"/></label>
        </c:when>

        <table border="2px" style="border-collapse: collapse">
            <th>No</th>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Total Price</th>

            <c:forEach items="${invoiceList}" var="invoice" varStatus="count">
                <tr>
                    <td>${count.count}</td>
                    <td>${invoice.productName}</td>
                    <td>${invoice.quantity}</td>
                    <td>${invoice.price}</td>
                    <text style="display: none">${totalPrice = totalPrice + invoice.price}</text>
                </tr>
            </c:forEach>
            <lable>Total Price : ${totalPrice}</lable>
            <br><br>
        </table>

        <c:otherwise>
            No Data Available
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
