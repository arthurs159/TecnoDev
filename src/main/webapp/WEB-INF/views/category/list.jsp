<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE>
<html>
<head>
    <link rel='stylesheet' href='/webjars/bootstrap/3.1.0/css/bootstrap.min.css'>
    <meta content="text/html;charset=UTF-8">
    <title>Lista de categorias</title>
</head>

<div class="container">
    <h1>Categorias</h1>
    <a href="/admin/categories/new">
        <button type="button" class="btn btn-primary">Nova categoria</button>
    </a>
</div>
<div class="container">
    <div class="row">
        <table class="table table-bordered ">
            <thead>
            <tr class="col">
                <th>Nome</th>
                <th>Código</th>
                <th>Ativo</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${category}" var="c">
                <tr class="col">
                    <td>${c.name}</td>
                    <td>${c.code}</td>
                    <td>${c.active}</td>
                    <td>
                        <button type="button" class="btn btn-link">Subcategorias</button>
                    </td>
                    <td>
                        <button type="button" class="btn btn-default">Editar</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>