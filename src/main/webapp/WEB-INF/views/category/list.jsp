<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>

<templates:admin-list-template title="Categorias">
    <h1>Categorias</h1>
    <a href="/admin/categories/new">
        <button type="button" class="btn btn-primary btn-lg button">Nova categoria</button>
    </a>
    <div class="table-responsive">
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
            <c:forEach items="${categories}" var="cat">
                <tr class="col" data-cat-id="${cat.id}">
                    <td>${cat.name}</td>
                    <td>${cat.code}</td>
                    <td class="ativo">${cat.active == true ? 'Ativa' : 'Inativa'}</td>
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
                    <c:if test="${cat.active}">
                        <td>
                            <button type="button" class="btn btn-default deactivate-category-button">
                                <span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span> Desativar
                            </button>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <script src="../../../assets/js/jquery.js"></script>
    <script src="../../../assets/js/category/disableCategoryJQuery.js"></script>
</templates:admin-list-template>