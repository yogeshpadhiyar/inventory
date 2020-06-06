<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 17/02/20
  Time: 12:54 PM
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
    <title>Order Management | Add to cart</title>
    <script>
        function checkUserInput() {
            var checkboxs = document.getElementsByName("productIds");
            var flag = false;
            for (var i = 0, l = checkboxs.length; i < l; i++) {
                if (checkboxs[i].checked) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                alert("Please check a checkbox")
                return false;
            }
            ;
        }
    </script>
</head>
<body>
<div class="container">
    <br><a href="home">
    <button type="button" class="btn btn-dark">Main Menu</button>
</a><br><br><br>
    <c:choose>
        <c:when test="${fn:length(productList) gt 0}">
<%--@elvariable id="order" type="com.neosoft.model.Product"--%>
<f:form action="addToCart" method="post" onsubmit="return checkUserInput()">
    <table border="2px" style="border-collapse: collapse" class="table table-striped">
        <th scope="col">Product Id</th>
        <th scope="col">Product Name</th>
        <th scope="col">Product Category</th>
        <th scope="col">Product Price</th>
        <th scope="col">Check Here</th>
        <th scope="col">Enter Product Quantity</th>
        <c:forEach items="${productList}" var="invoice">
            <tr>
                <td>${invoice.id}</td>
                <td>${invoice.name}</td>
                <td>${invoice.category}</td>
                <td>${invoice.price}</td>
                <td><input class="form-control"  type="checkbox" name="productIds" value="${invoice.id}" onclick="document.getElementById('${invoice.id}').disabled = !this.checked; "/></td>
                <td><input class="form-control" type="number" id="${invoice.id}" name="quantity" min="1" step="1" value="0" disabled="true"/></td>
            </tr>
        </c:forEach>
    </table>
    <br><br>
    <button id="addCart" type="submit" style="margin-left: 45%" class="btn btn-success" >Add To Cart</button>
</f:form>
        </c:when>
        <c:otherwise>
            <div class="alert alert-warning"><h3>No Available Product for sale</h3></div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
