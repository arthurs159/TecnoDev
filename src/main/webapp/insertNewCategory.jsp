<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/createCategory" var="createACategory"/>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <title>Criar Categoria</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<div class="container">
    <h2>Adicionar nova Categoria</h2>
    <form action="${createACategory}" method="post">
        <div class="form-group">
            <input type="hidden" class="form-control" name="id" >
        </div>
        <div class="form-group">
            <label >Nome:</label>
            <input type="text" class="form-control" name="name" >
        </div>
        <div class="form-group">
            <label >Código:</label>
            <input type="text" class="form-control" name="code" >
        </div>
        <div class="form-group">
            <label >Descrição</label>
            <input type="text" class="form-control" name="description" >
        </div>
        <div class="form-group">
            <label >Guia de Estudo</label>
            <input type="text" class="form-control" name="studyGuide" >
        </div>
        <div class="form-group">
            <label >Ativo:</label>
            <input type="text" class="form-control" name="active" >
        </div>
        <div class="form-group">
            <label >Ordem:</label>
            <input type="text" class="form-control" name="orderInSystem" >
        </div>
        <div class="form-group">
            <label >Url da Imagem:</label>
            <input type="text" class="form-control" name="imageUrl" >
        </div>
        <div class="form-group">
            <label >Código da cor:</label>
            <input type="text" class="form-control" name="colorCode" >
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

</body>
</html>

