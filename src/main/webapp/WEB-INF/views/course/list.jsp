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
    <h3>${subcategory.name}</h3>
    <h1>Cursos</h1>
    <a href="/admin/course/new">
        <button style="margin-bottom: 15px;" type="button" class="btn btn-primary btn-lg">Novo curso</button>
    </a>
</div>
<div class="container">
    <div class="row">
        <table class="table table-bordered ">
            <thead>
            <tr class="col">
                <th>Nome</th>
                <th>Código</th>
                <th>Status</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${courses}" var="course">
                <tr class="col">
                    <td>${course.name}</td>
                    <td>${course.code}</td>
                    <td>${course.visibility}</td>
                    <td><a href="/admin/course/${course.code}">
                        <button type="button" class="btn btn-default">Editar</button>
                    </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <nav>
            <ul class="pagination pagination-lg">
                <li class="${first == true ? "disabled" : ""}">
                    <a href="?page=${pageNumber-1}" aria-label="Previous"><span class="disabled" aria-hidden="true">&laquo;</span></a>
                </li>
                    <c:forEach begin="1" end="${totalPages}" varStatus="index">
                <li class="${index.index-1 == pageNumber ? "disabled" : ""}">
                        <a href="?page=${index.index-1}"><span>${index.index}</span></a>
                </li>
                    </c:forEach>

                <li class="${last == true ? "disabled" : ""}">
                    <a href="?page=${pageNumber+1}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
                </li>
            </ul>
        </nav>


    </div>
</div>

</body>
</html>