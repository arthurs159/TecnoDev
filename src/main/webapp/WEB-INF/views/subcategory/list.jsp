<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE>
<html>
<head>
    <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    <meta content="text/html;charset=UTF-8">
    <title>Lista de categorias</title>
</head>

<div style="padding: 0" class="container">
    <h3><c:out value="${subcategories[0].categoryName}" default="categorie"/></h3>
    <h1>Subcategorias</h1>
    <a href="/admin/subcategories/new">
        <button style="margin-bottom: 15px;" type="button" class="btn btn-primary btn-lg">Nova subcategoria</button>
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
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${subcategories}" var="subcat">
                <tr class="col" data-sub-id="${subcat.id}">
                    <td>${subcat.name}</td>
                    <td>${subcat.code}</td>
                    <td class="ativo">${subcat.active}</td>
                    <td>
                        <a href="/admin/courses/${subcat.categoryCode}/${subcat.code}">
                            <button type="button" class="btn btn-link">Cursos</button>
                        </a>
                    </td>
                    <td>
                        <a href="/admin/subcategories/${subcat.categoryCode}/${subcat.code}">
                            <button type="button" class="btn btn-default">Editar</button>
                        </a>
                    </td>
                    <c:if test="${subcat.active}">
                        <td>
                            <button type="button" class="btn btn-default deactivate-subcategory-button">
                                <span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span> Desativar
                            </button>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="../../../assets/js/jquery.js"></script>
<script src="../../../assets/js/category/disableCategoryJQuery.js"></script>
</body>
</html>