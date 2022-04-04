<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <link rel='stylesheet' href='/webjars/bootstrap/3.1.0/css/bootstrap.min.css'>
    <meta charset="UTF-8">
    <title>Editar Categoria</title>
</head>
<body>

<div class="container">
    <form action="/admin/categories/${category.code}" method="post">
            <input type="hidden" name="id" value="${category.id}"/>
        <div class="form-group">
            <label>Nomes</label>
            <input type="text" class="form-control" name="name" value="${category.name}"
                   placeholder="Digite aqui o nome da categoria">
        </div>
        <div class="form-group">
            <label>Código</label>
            <input type="text" class="form-control" name="code" value="${category.code}" placeholder="por exemplo....">
        </div>
        <div class="form-group">
            <label>Descrição</label>
            <input type="text" class="form-control" name="description" value="${category.description}"
                   placeholder="Enter email">
        </div>
        <%--        <div class="form-group">--%>
        <%--            <label>Ativo</label>--%>
        <%--            <input type="text" class="form-control" name="active" value="${category.active}" placeholder="Enter email">--%>
        <%--        </div>--%>
        <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" id="active"
                   name="active"  ${category.isActive() ? 'checked' : ''}
                   placeholder="Mostra ou deixa de mostrar a categoria na listagem dos alunos">
            <label style="margin-top: 5px;" class="form-check-label" for="active" class="form-label">
                Categoria Ativa ?
            </label>
        </div>

        <div class="form-group">
            <label>Ordem da categoria</label>
            <input type="text" class="form-control" name="orderInSystem" value="${category.orderInSystem}"
                   placeholder="Enter email">
        </div>
        <div class="form-group">
            <label>Guia de estudos</label>
            <input type="text" class="form-control" name="studyGuide" value="${category.studyGuide}"
                   placeholder="Enter email">
        </div>
        <div class="form-group">
            <label>Caminho do ícone</label>
            <input type="text" class="form-control" name="imageUrl" value="${category.imageUrl}"
                   placeholder="Enter email">
        </div>
        <div class="form-group">
            <label>Cor</label>
            <input type="text" class="form-control" name="colorCode" value="${category.colorCode}"
                   placeholder="Enter email">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>

</body>
</html>

