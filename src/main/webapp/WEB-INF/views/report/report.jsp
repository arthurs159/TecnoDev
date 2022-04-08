<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE>
<html>
<head>
    <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    <link rel='stylesheet' href='/assets/css/report/report.css'>
    <meta content="text/html;charset=UTF-8">
    <title>Relatório</title>
</head>

<div style="padding: 0" class="container">
    <h1>RELATÓRIOS</h1>
</div>
<div class="container">
    <div class="row">
        <h3>Categorias e qtd de cursos</h3>
        <div class="categories-container">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Categoria</th>
                <th scope="col">Quantidade de Cursos</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${report}" var="report">
                <tr>
                    <td>${report.categoryName}</td>
                    <td>${report.quantityCoursesFromCategory}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>

        <div class="instructor-container">
        <h3>Instrutor com mais cursos</h3>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Instrutor</th>
                <th scope="col">Quantidade de Cursos</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                   <td><c:out value="${instructor.instructorName}" /></td>
                   <td><c:out value="${instructor.quantityOfCourses}" /></td>
                </tr>
            </tbody>
        </table>
        </div>

    </div>
</div>

</body>
</html>