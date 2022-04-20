<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    <meta charset="UTF-8">
    <title>Inserir Curso</title>
</head>
<body>

<div class="container">
    <h1>Novo Curso</h1>
    <form:form action="/admin/courses" method="post"
               modelAttribute="newCourseForm">
        <input type="hidden" name="id"/>
        <div class="form-group">
            <label>Nome</label>
            <form:input type="text" class="form-control" placeholder="Digite aqui o nome da subcategoria"
                   value="${course.name}" path="name"/>
            <form:errors path="name" cssClass="alert-danger"/>
        </div>

        <div class="form-group">
            <label>Código</label>
            <form:input type="text" class="form-control"
                   placeholder="por exemplo: java, python (não use letra maiúsculas, acentos ou caracteres especiais)"
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
                <form:option value="PUBLIC">PUBLICO</form:option>
                <form:option value="PRIVATE">PRIVADO</form:option>
            </form:select>
        </div>
        <div class="form-group">
            <label>Público Alvo</label>
            <form:input type="text" class="form-control" name="targetAudience"
                   placeholder="por exemplo: Laravel, CakePHP e CodeIgniter são frameworks que você vai treinar bastante aqui."
                   value="${course.targetAudience}" path="targetAudience"/>
            <form:errors path="targetAudience" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label>Instrutor</label>
            <form:input type="text" class="form-control"
                   placeholder="por exemplo: Laravel, CakePHP e CodeIgniter são frameworks que você vai treinar bastante aqui."
                   value="${course.teacher}" path="teacher"/>
            <form:errors path="teacher" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label>Descrição</label>
            <form:input type="text" class="form-control"
                   placeholder="por exemplo: Laravel, CakePHP e CodeIgniter são frameworks que você vai treinar bastante aqui."
                   value="${course.description}" path="description"/>
            <form:errors path="description" cssClass="alert-danger"/>
        </div>
        <div class="form-group">
            <label>Habilidades Desenvolvidas</label>
            <form:input type="text" class="form-control"
                   placeholder="por exemplo: Laravel, CakePHP e CodeIgniter são frameworks que você vai treinar bastante aqui."
                   value="${course.developedSkills}" path="developedSkills"/>
            <form:errors path="developedSkills" cssClass="alert-danger"/>
        </div>

        <div class="form-group">
            <label>Subcategorias</label>
            <form:select class="form-control" path="subcategoryId">
                <c:forEach items="${subcategory}" var="sub">
                    <option value="${sub.id}">${sub.name} </option>
                </c:forEach>
                <form:errors path="subcategoryId" cssClass="alert-danger"/>
            </form:select>
        </div>

        <button type="submit" class="btn btn-info btn-lg">Enviar</button>
    </form:form>
</div>

</body>
</html>

