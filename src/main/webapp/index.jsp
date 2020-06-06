<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Index</title>
</head>
<body>
<div class="container">
    <br>
    <h5>Product Management</h5>
    <a href="ProductController/ProductManage">
        <button type="button" class="btn btn-info">Product Management</button>
    </a><br><br>
    <h5>Order Management</h5>
    <a href="OrderController/orderManage">
        <button type="button" class="btn btn-info">Order Management</button>
    </a><br><br>
    <h5>Print Invoice</h5>
    <a href="OrderController/printInvoicePage">
        <button type="button" class="btn btn-info">Print Invoice</button>
    </a><br><br>
    <h5>Report Management</h5>
    <a href="ReportController/reportPage">
        <button type="button" class="btn btn-info">Report Management</button>
    </a><br><br>
</div>
</body>
</html>