<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 14/02/20
  Time: 6:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Product Management | Edit</title>
</head>
<body>
<div class="container">
    <br><a href="home">
    <button type="button" class="btn btn-dark">Main Menu</button>
</a><br><br>
    <c:set var="product" value="${requestScope.product}"/>
    <%--@elvariable id="product" type="com.neosoft.model.Product"--%>
    <f:form action="update" method="post" modelAttribute="product">
        Product Id : <f:input class="form-control"  path="id" value="${product.id}" readonly="true"/><br><br>
        Product Name : <f:input class="form-control" id="focusedInput" path="name" value="${product.name}"/><br><br>
        Product Category : <f:input class="form-control" id="focusedInput" path="category" value="${product.category}"/><br><br>
        Product Price : <f:input class="form-control" id="focusedInput" path="price" value="${product.price}"/><br><br>
        <button type="submit" name="updateProduct" style="margin-left: 45%" class="btn btn-success">Edit Product</button>
    </f:form>
</div>
</body>
</html>
