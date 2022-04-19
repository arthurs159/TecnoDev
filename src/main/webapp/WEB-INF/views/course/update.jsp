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
    <form action="/admin/courses/${categoryCode}/${subcategoryCode}/${course.code}" method="post">
        <input type="hidden" name="id"/>
        <div class="form-group">
            <label>Nome</label>
            <input type="text" class="form-control" name="name"
                   value="${course.name}">
            <form:errors path="newCourseFormUpdate.name" cssClass="alert-danger"/>
        </div>

        <div class="form-group">
            <label>Código</label>
            <input type="text" class="form-control"
                   name="code"
                   value="${course.code}"/>
            <form:errors path="newCourseFormUpdate.code" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label>Tempo de Finalização</label>
            <input type="number" class="form-control" name="estimatedTimeInHours"
                   placeholder="por exemplo: categoria de ordem 1 aparece antes da categoria de ordem 2"
                   value="${course.estimatedTimeInHours}" min="1" max="20"/>
            <form:errors path="newCourseForm.estimatedTimeInHours" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label>Visibilidade</label>
            <select class="form-control" name="visibility">
                <option value="PUBLIC">PUBLICO</option>
                <option value="PRIVATE">PRIVADO</option>
            </select>
        </div>
        <div class="form-group">
            <label>Público Alvo</label>
            <input type="text" class="form-control" name="targetAudience"
                   value="${course.targetAudience}">
            <form:errors path="newCourseFormUpdate.targetAudience" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label>Instrutor</label>
            <input type="text" class="form-control" name="teacher"
                   value="${course.teacher}">
            <form:errors path="newCourseFormUpdate.teacher" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label>Descrição</label>
            <input type="text" class="form-control" name="description"
                   value="${course.description}">
            <form:errors path="newCourseFormUpdate.description" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label>Habilidades Desenvolvidas</label>
            <input type="text" class="form-control" name="developedSkills"
                   value="${course.developedSkills}">
            <form:errors path="newCourseFormUpdate.developedSkills" cssClass="alert-danger"/>
        </div>

        <div class="form-group">
            <label>Subcategorias</label>
            <select name="subcategoryId" class="form-control">
                <c:forEach items="${subcategory}" var="sub">
                    <option value="${sub.id}">${sub.name} </option>
                </c:forEach>
                <form:errors path="newCourseFormUpdate.subcategoryId" cssClass="alert-danger"/>
            </select>
        </div>

        <button type="submit" class="btn btn-info btn-lg">Enviar</button>
    </form>
</div>

</body>
</html>

