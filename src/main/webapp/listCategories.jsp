<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<h1>RELATÓRIO TECNODEV<br></h1>
<h2>Categorias Com Servlet</h2>
<a href="/createCategory">
<button type="button"> CRIAR NOVA CATEGORIA</button>
</a>
<table style="width:100%">
    <tr>
        <th>ID</th>
        <th>NOME</th>
        <th>CÓDIGO</th>
        <th>DESCRIÇÃO</th>
        <th>GUIA ESTUDO</th>
        <th>ATIVO</th>
        <th>ORDEM</th>
        <th>URL DA IMAGEM</th>
        <th>CÓDIGO DA COR</th>
        <th>EDITAR CATEGORIA</th>
    </tr>

    <c:forEach items="${categoriesDTOList}" var="categoryDto">
        <tr>
            <td>${categoryDto.id}</td>
            <td>${categoryDto.name}</td>
            <td>${categoryDto.code}</td>
            <td>${categoryDto.description}</td>
            <td>${categoryDto.studyGuide}</td>
            <td>${categoryDto.active}</td>
            <td>${categoryDto.orderInSystem}</td>
            <td><img src=${categoryDto.imageUrl} alt='icone' width="150px"> </td>
            <td style="background-color: ${categoryDto.colorCode}"></td>
            <td><a href="/editCategory?id=${categoryDto.id}">EDITAR</a></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
