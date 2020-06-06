<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 14/02/20
  Time: 3:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Product Management</title>
</head>
<body>
<div class="container">
    <br>
    <a href="home">
        <button type="button" class="btn btn-dark">Main Menu</button>
    </a>
    <br><br><br>
    <%--@elvariable id="product" type="com.neosoft.model.Product"--%>
    <f:form action="insert" method="post" modelAttribute="product">
        <f:hidden path="id"/>
        Product Name : <f:input class="form-control" id="focusedInput" path="name" /><f:errors path="name" cssClass="error"/><br><br>
        Product Category : <f:input class="form-control" id="focusedInput" path="category" /><f:errors path="category" cssClass="error"/><br><br>
        Product Price : <f:input class="form-control" id="focusedInput" path="price"/><f:errors path="price" cssClass="error"/><br><br>
        <button type="submit" name="addProduct" style="margin-left: 45%" class="btn btn-success" >Add Product</button>
    </f:form>
</div>
</body>
</html>
