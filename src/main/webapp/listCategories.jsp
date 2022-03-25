<%@ page import="com.br.tecnodev.tecnodev.category.Category" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; ISO-8859-1"
         pageEncoding="iso-8859-1" %>

<!DOCTYPE html>
<html>
<style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
    }
</style>
<head>
    <title>Categorias</title>
</head>
<body>
<h1>RELATÓRIO TECNODEV<br></h1>
<h2>Categorias Com Servlet</h2>
<table style="width:100%">
    <tr>
        <th>ID</th>
        <th>NOME</th>
        <th>CÓDIGO</th>
        <th>DESCRIÇÃO</th>
        <th>ATIVO</th>
        <th>ORDEM</th>
        <th>URL DA IMAGEM</th>
        <th>CÓDIGO DA COR</th>
    </tr>

    <%
        List<Category> categoryList = (List<Category>) request.getAttribute("categories");
        for (Category category : categoryList) {
    %>
    <tr>
        <td><%= category.getId() %></td>
        <td><%=category.getName()%></td>
        <td><%=category.getCode()%></td>
        <td><%=category.getDescription()%></td>
        <td><%=category.isActive()%></td>
        <td><%=category.getOrderInSystem()%></td>
        <td><img src=<%=category.getImageUrl()%> alt="icone" width="150px"></td>
        <td style="background-color: <%=category.getColorCode()%>"></td>
    </tr>
    <%}%>

</table>
</body>
</html>
