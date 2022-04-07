<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE>
<html>
<head>
    <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    <link rel='stylesheet' href="/assets/css/subcategory/listSubcategory.css">
    <meta content="text/html;charset=UTF-8">
    <title>Lista de categorias</title>
</head>

<div style="padding: 0" class="container">
    <p><c:out value="${subcategories[0].categoryName}" default="categorie"/></p>
    <h1>Subcategorias</h1>
    <a href="/admin/subcategories/new">
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
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${subcategories}" var="subcat">
                <tr class="col">
                    <td>${subcat.name}</td>
                    <td>${subcat.code}</td>
                    <td>${subcat.active}</td>
                    <td><a href="/admin/courses/${subcat.categoryCode}/${subcat.code}">
                        <button type="button" class="btn btn-link">Cursos</button>
                    </a></td>
                    <td><a href="/admin/subcategories/${subcat.categoryCode}/${subcat.code}">
                        <button type="button" class="btn btn-default">Editar</button>
                    </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>