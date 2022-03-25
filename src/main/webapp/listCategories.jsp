<%@ page import="com.br.tecnodev.tecnodev.category.Category" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; ISO-8859-1"
         pageEncoding="iso-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<h1>RELAT�RIO TECNODEV<br></h1>
<h2>Categorias Com Servlet</h2>
<table style="width:100%">
    <tr>
        <th>ID</th>
        <th>NOME</th>
        <th>C�DIGO</th>
        <th>DESCRI��O</th>
        <th>ATIVO</th>
        <th>ORDEM</th>
        <th>URL DA IMAGEM</th>
        <th>C�DIGO DA COR</th>
    </tr>

    <c:forEach items="${categories}" var="category">
        <tr>
            <td>${category.id}</td>
            <td>${category.name}</td>
            <td>${category.code}</td>
            <td>${category.description}</td>
            <td>${category.active}</td>
            <td>${category.orderInSystem}</td>
            <td><img src=${category.imageUrl} alt='icone' width="150px"> </td>
            <td style="background-color: ${category.colorCode}"></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>