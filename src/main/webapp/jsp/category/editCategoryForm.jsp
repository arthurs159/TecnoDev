<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/updateCategory" var="editACategory"/>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Editar Categoria</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<div class="container">
    <h2>Formulário para editar a Categoria de ID: ${category.id}</h2>
    <form action="${editACategory}" method="post">
        <div class="form-group">
            <label></label>
            <input type="hidden" class="form-control" name="id" value="${category.id}">
        </div>
        <div class="form-group">
            <label>Nome:</label>
            <input type="text" class="form-control" name="name" value="${category.name}">
        </div>
        <div class="form-group">
            <label>Código:</label>
            <input type="text" class="form-control" name="code" value="${category.code}">
        </div>
        <div class="form-group">
            <label>Descrição</label>
            <input type="text" class="form-control" name="description" value="${category.description}">
        </div>
        <div class="form-group">
            <label>Guia Estudo</label>
            <input type="text" class="form-control" name="studyGuide" value="${category.studyGuide}">
        </div>

        <div class="form-group">
            <label>Status da Categoria:</label>
                <select name="active" class="custom-select mb-3">
                    <option value="true" ${category.active == true ? "selected" : ""}>ATIVO</option>
                    <option value="false" ${category.active == false ? "selected" : ""}>INATIVO</option>
                </select>
        </div>

        <div class="form-group">
            <label>Ordem:</label>
            <input type="text" class="form-control" name="orderInSystem" value="${category.orderInSystem}">
        </div>
        <div class="form-group">
            <label>Url da Imagem:</label>
            <input type="text" class="form-control" name="imageUrl" value="${category.imageUrl}">
        </div>
        <div class="form-group">
            <label>Código da cor:</label>
            <input type="text" class="form-control" name="colorCode" value="${category.colorCode}">
        </div>
        <button type="submit" class="btn btn-primary">Enviar</button>
    </form>
</div>

</body>
</html>

