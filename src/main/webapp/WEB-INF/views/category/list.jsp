<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE>
<html>
<head>
    <link rel='stylesheet' href='/webjars/bootstrap/3.1.0/css/bootstrap.min.css'>
    <meta content="text/html;charset=UTF-8">
    <title>Lista de categorias</title>
</head>

<div style="padding: 0" class="container">
    <h1>Categorias</h1>
    <a href="/admin/categories/new">
        <button style="margin-bottom: 15px;" type="button" class="btn btn-primary btn-lg">Nova categoria</button>
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
            <c:forEach items="${category}" var="cat">
                <tr class="col">
                    <td>${cat.name}</td>
                    <td>${cat.code}</td>
                    <td>${cat.active}</td>
                    <td>
                        <a href="subcategories/${cat.code}">
                            <button type="button" class="btn btn-link">Subcategorias</button>
                        </a>
                    </td>
                    <td>
                        <a href="/admin/categories/${cat.code}">
                            <button type="button" class="btn btn-default">Editar</button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>