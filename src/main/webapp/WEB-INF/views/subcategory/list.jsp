<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>

<templates:admin-list-template title="Subcategorias">
    <h3>${category.name}</h3>
    <h1>Subcategorias</h1>
    <a href="/admin/subcategories/new">
        <button type="button" class="btn btn-primary btn-lg button">Nova subcategoria</button>
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
            <c:forEach items="${subcategories}" var="subcat">
                <tr class="col" data-sub-id="${subcat.id}">
                    <td>${subcat.name}</td>
                    <td>${subcat.code}</td>
                    <td class="ativo">${subcat.active == true ? "Ativa" : "Inativa"}</td>
                    <td>
                        <a href="/admin/courses/${category.code}/${subcat.code}">
                            <button type="button" class="btn btn-link">Cursos</button>
                        </a>
                    </td>
                    <td>
                        <a href="/admin/subcategories/${category.code}/${subcat.code}">
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
    <script src="../../../assets/js/jquery.js"></script>
    <script src="../../../assets/js/category/disableCategoryJQuery.js"></script>
</templates:admin-list-template>