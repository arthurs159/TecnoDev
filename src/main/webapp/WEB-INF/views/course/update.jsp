<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    <meta charset="UTF-8">
    <title>Atualizar Curso</title>
</head>
<body>

<div class="container">
    <h1>Atualizar Curso</h1>
    <form:form action="/admin/courses/${categoryCode}/${subcategoryCode}/${course.code}"
               modelAttribute="newCourseFormUpdate" method="post">
        <div class="form-group">
            <label>Nome</label>
            <form:input type="text" class="form-control"
                        value="${course.name}" path="name"/>
            <form:errors path="name" cssClass="alert-danger"/>
        </div>

        <div class="form-group">
            <label>Código</label>
            <form:input type="text" class="form-control"
                        name="code"
                        value="${course.code}" path="code"/>
            <form:errors path="code" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label>Tempo de Finalização</label>
            <form:input type="number" class="form-control"
                        placeholder="por exemplo: categoria de ordem 1 aparece antes da categoria de ordem 2"
                        value="${course.estimatedTimeInHours}" min="1" max="20" path="estimatedTimeInHours"/>
            <form:errors path="estimatedTimeInHours" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label>Visibilidade</label>
            <form:select class="form-control" path="visibility">
                <form:option value="PUBLIC"
                             selected="${course.visibility == 'PUBLIC' ? 'true' : ''}">PUBLICO</form:option>
                <form:option value="PRIVATE"
                             selected="${course.visibility == 'PRIVATE' ? 'true' : ''}">PRIVADO</form:option>
            </form:select>
        </div>

        <div class="form-group">
            <label>Público Alvo</label>
            <form:input type="text" class="form-control" name="targetAudience"
                        value="${course.targetAudience}" path="targetAudience"/>
            <form:errors path="targetAudience" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label>Instrutor</label>
            <form:input type="text" class="form-control" name="teacher"
                        value="${course.teacher}" path="teacher"/>
            <form:errors path="teacher" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label>Descrição</label>
            <form:input type="text" class="form-control" name="description"
                        value="${course.description}" path="description"/>
            <form:errors path="description" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label>Habilidades Desenvolvidas</label>
            <form:input type="text" class="form-control" name="developedSkills"
                        value="${course.developedSkills}" path="developedSkills"/>
            <form:errors path="developedSkills" cssClass="alert-danger"/>
        </div>

        <div class="form-group">
            <label>Subcategorias</label>
            <form:select class="form-control" path="subcategoryId">
                <c:forEach items="${subcategories}" var="subcategory">
                    <form:option value="${subcategory.id}"
                                 selected="${course.subCategory.name == subcategory.name ? 'selected' : ''}">${subcategory.name} </form:option>
                </c:forEach>
                <form:errors path="subcategoryId" cssClass="alert-danger"/>
            </form:select>
        </div>

        <button type="submit" class="btn btn-info btn-lg">Enviar</button>
    </form:form>
</div>

</body>
</html>

