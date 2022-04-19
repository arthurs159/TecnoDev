<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='stylesheet' href='../../../assets/css/category/publicPage.css'>
    <title>Programação | Cursos online de tecnologia</title>
</head>
<body>
<section class="category-banner__wrapper">
    <div class="container category-banner">
            <span class="category-banner__icon">
                <img src="${category.imageUrl}">
            </span>
        <h1 class="category-banner__title">${category.name}</h1>
    </div>
</section>
<main class="container">
    <div class="subcategories">
        <p class="subcategories__label">O que você quer aprender?</p>
        <ul class="subcategories__list">
            <c:forEach items="${subcategory}" var="subcategory">
                <li class="subcategories__item">
                    <a href="#${subcategory.name}" class="subcategories__link">
                        <span class="subcategories__name">${subcategory.name}</span>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <c:forEach items="${subcategory}" var="subcategory">
        <div class="subcategory">
            <h2 id="${subcategory.name}" class="subcategory__name">${subcategory.name}</h2>
            <ul class="courses__list">
                <c:forEach items="${subcategory.courses}" var="courses">
                    <li class="course-card">
                        <h3 class="course-card__name">${courses.name}</h3>
                        <p class="course-card__hours">${courses.estimatedTimeInHours}</p>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </c:forEach>
</main>
</body>
</html>