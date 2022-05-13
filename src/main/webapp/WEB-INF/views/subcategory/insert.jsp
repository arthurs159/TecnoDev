<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>

<templates:admin-insert-update-template title="Inserir Subcategoria">
    <h1>Nova subcategoria</h1>
    <form action="/admin/subcategories" method="post">
        <input type="hidden" name="id"/>
        <div class="form-group">
            <label>Nome</label>
            <input type="text" class="form-control" name="name" placeholder="Digite aqui o nome da subcategoria" value="${subCategoryForm.name}">
            <form:errors path="newSubCategoryForm.name" cssClass="alert-danger"/>
        </div>

        <div class="form-group">
            <label>Código</label>
            <input type="text" class="form-control"
                   name="code" placeholder="por exemplo: java, python (não use letra maiúsculas, acentos ou caracteres especiais)" value="${subCategoryForm.code}"/>
            <form:errors path="newSubCategoryForm.code" cssClass="alert-danger"/>
        </div>

        <div class="form-group ">
            <div class="mb-3 form-check div-checkbox">
                <input type="checkbox" class="form-check-input" id="active"
                       name="active" ${subCategoryForm.isActive() ? 'checked' : ''}>
                <label class="form-check-label" for="active" class="form-label">
                    <p class="active-category">Subcategoria Ativa? </p>
                </label>
                <p class="text-muted">
                    Mostre ou deixa de mostrar a categoria na listagem dos alunos, de formações, etc
                </p>
            </div>
        </div>
        <div class="form-group">
            <label>Ordem da categoria</label>
            <input type="text" class="form-control" name="orderInSystem"
                    placeholder="por exemplo: categoria de ordem 1 aparece antes da categoria de ordem 2" value="${subCategoryForm.orderInSystem}">
            <form:errors path="newSubCategoryForm.orderInSystem" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label>Guia de estudos</label>
            <textarea class="form-control" name="studyGuide" rows="6"
            placeholder="Um texto apontando para formações para ajudar pessoas perdidas">${subCategoryForm.studyGuide}</textarea>
            <form:errors path="newSubCategoryForm.studyGuide" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label>Descrição</label>
            <input type="text" class="form-control" name="description"
                   placeholder="por exemplo: Laravel, CakePHP e CodeIgniter são frameworks que você vai treinar bastante aqui." value="${subCategoryForm.description}">
            <form:errors path="newSubCategoryForm.description" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label>Categoria</label>
            <select name="categoryId" class="form-control">
                <c:forEach items="${category}" var="cat">
                    <option value="${cat.id}">${cat.name} </option>
                </c:forEach>
                <form:errors path="newSubCategoryForm.categoryId" cssClass="alert-danger"/>
            </select>
        </div>
        <button type="submit" class="btn btn-info btn-lg">Enviar</button>
    </form>
</templates:admin-insert-update-template>