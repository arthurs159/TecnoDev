<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/assets/css/category/category.css">
    <title>Categorias</title>
</head>
<body>
<h1>RELATÓRIO TECNODEV<br></h1>
<h2>Categorias Com Servlet</h2>
<a href="/createCategory">
    <button class="button button1" type="button"> CRIAR NOVA CATEGORIA</button>
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
        <th>EDITAR</th>
        <th>ATIV/DESATIV</th>
    </tr>

    <c:forEach items="${categoriesDTOList}" var="categoryDto">
        <tr>
            <td>${categoryDto.id}</td>
            <td>${categoryDto.name}</td>
            <td>${categoryDto.code}</td>
            <td>${categoryDto.description}</td>
            <td>${categoryDto.studyGuide}</td>
            <td class="active">${categoryDto.active}</td>
            <td>${categoryDto.orderInSystem}</td>
            <td><img src=${categoryDto.imageUrl} alt='icone' width="150px"></td>
            <td style="background-color: ${categoryDto.colorCode}"></td>
            <td><a href="/editCategory?id=${categoryDto.id}">EDITAR</a></td>
            <td>
                <button style="height:60px" onclick="disableCategory(${categoryDto.id}, this)" id="desativar">DESATIVAR / ATIVAR</button>
            </td>
        </tr>
    </c:forEach>

    <script src="/assets/js/category/disableCategory.js"></script>

</table>
</body>
</html>
