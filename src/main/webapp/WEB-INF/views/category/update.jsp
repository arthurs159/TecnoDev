<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    <link rel="stylesheet" href="/assets/css/category/insertAndUpdate.css">
    <meta charset="UTF-8">
    <title>Editar Categoria</title>
</head>
<body>

<div class="container">
    <h1>Editar categoria</h1>
    <form action="/admin/categories/${category.code}" method="post">
        <input type="hidden" name="id" value="${category.id}"/>
        <div class="form-group">
            <label>Nome</label>
            <input type="text" class="form-control" name="name" value="${category.name}">
            <form:errors path="newCategoryFormUpdate.name" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label>Código</label>
            <input type="text" class="form-control" name="code" value="${category.code}">
            <form:errors path="newCategoryFormUpdate.code" cssClass="alert-danger"/>
        </div>

        <div class="form-group ">
            <div class="mb-3 form-check div-checkbox">
                <input type="checkbox" class="form-check-input" id="active"
                       name="active"  ${category.isActive() ? 'checked' : ''}>
                <label class="form-check-label" for="active" class="form-label">
                    <p class="active-category">Categoria Ativa? </p>
                </label>
                <p class="text-muted">
                    Mostre ou deixa de mostrar a categoria na listagem dos alunos, de formações, etc
                </p>
            </div>
        </div>
        <div class="form-group">
            <label>Ordem da categoria</label>
            <input type="text" class="form-control" name="orderInSystem" value="${category.orderInSystem}"
                   placeholder="Enter email">
            <form:errors path="newCategoryFormUpdate.orderInSystem" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label>Guia de estudos</label>
            <textarea class="form-control" name="studyGuide" rows="6">${category.studyGuide}</textarea>
            <form:errors path="newCategoryFormUpdate.studyGuide" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label>Caminho do ícone</label>
            <input type="text" class="form-control" name="imageUrl" value="${category.imageUrl}">
            <form:errors path="newCategoryFormUpdate.imageUrl" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label>Cor</label>
            <input type="text" class="form-control" name="colorCode" value="${category.colorCode}">
            <form:errors path="newCategoryFormUpdate.colorCode" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label>Descrição</label>
            <input type="text" class="form-control" name="description" value="${category.description}">
            <form:errors path="newCategoryFormUpdate.description" cssClass="alert-danger"/>
        </div>
        <button type="submit" class="btn btn-info btn-lg">Enviar</button>
    </form>
</div>

</body>
</html>

