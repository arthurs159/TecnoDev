<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype html>
<html lang="pt-BR">
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <title>Adicionar</title>
</head>
<body>

<h1>Adicionar Categoria</h1>

<form action="/admin/categories" method="post">
    <div class="form-group">
        <label>Nome</label>
        <input type="text" name="name" class="form-control" />
    </div>
    <div class="form-group">
        <label>Código</label>
        <input type="text" name="code" class="form-control" />
    </div>
    <div class="form-group">
        <label>Descrição</label>
        <input type="text" name="description" class="form-control" />
    </div>
    <div class="form-group">
        <label>Guia de Estudo</label>
        <input type="text" name="studyGuide" class="form-control" />
    </div>
    <div class="form-group">
        <label>Ativo</label>
        <input type="text" name="active" class="form-control" />
    </div>
    <div class="form-group">
        <label>Ordem no Sistema</label>
        <input type="text" name="orderInSystem" class="form-control" />
    </div>
    <div class="form-group">
        <label>URL da imagem</label>
        <input type="text" name="imageUrl" class="form-control" />
    </div>
    <div class="form-group">
        <label>Código da cor</label>
        <input type="text" name="colorCode" class="form-control" />
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
</form>

</body>
</html>