<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 17/02/20
  Time: 3:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Order Management | View Cart</title>
</head>
<body>
<div class="container">
<br><a href="home">
    <button type="button" class="btn btn-dark">Main Menu</button>
</a><br><br><br>
<c:choose>
    <c:when test="${fn:length(productList) gt 0}">
        <%--@elvariable id="product" type="com.neosoft.modelhb.Product"--%>
        <%--@elvariable id="List<product>" type=""--%>
        <f:form action="placeOrder" method="post">
            <table border="2px" style="border-collapse: collapse" class="table table-striped">
                <th scope="col">Product No</th>
                <th scope="col">Product Name</th>
                <th scope="col">Product Category</th>
                <th scope="col">Product Quantity</th>
                <th scope="col">Product Price</th>

                <c:set var="count" value="1"/>

                <c:forEach items="${productList}" var="product" varStatus="count">
                    <tr>
                        <td>${count.count}</td>
                        <td>${product.name}</td>
                        <td>${product.category}</td>

                        <c:forEach items="${cart}" var="cart">
                            <c:if test="${cart.key == product.id}">
                                <td>${cart.value}</td>
                                <td>${cart.value * product.price}</td>
                            </c:if>
                        </c:forEach>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="5"></td>
                </tr>
                <tr>
                    <td colspan="2"><b>Total Price </b></td>
                    <td colspan="3"><b><c:out value="${totalPrice}"/></b></td>
                </tr>
            </table>
            <br><br>

            <h4>Want to place Order?</h4>
            <a href="placeOrder"><button type="submit" class="btn btn-success">confirm</button></a>

        </f:form>
    </c:when>
    <c:otherwise>
        <div class="alert alert-warning"><h3>No product in cart</h3></div>
    </c:otherwise>
</c:choose>
</div>
</body>
</html>
