<%@tag language="java" pageEncoding="UTF-8" %>

<%@attribute name="title" required="true"%>
<!DOCTYPE>
<html>
<head>
    <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    <meta content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
</head>
<body>

<div class="container">
    <jsp:doBody />
</div>
<script src="../../../assets/js/jquery.js"></script>
<script src="../../../assets/js/category/disableCategoryJQuery.js"></script>
</body>
</html>