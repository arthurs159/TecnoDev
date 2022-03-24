package com.br.tecnodev.servlet;

import com.br.tecnodev.jpa.dao.CategoryDao;
import com.br.tecnodev.jpa.util.JPAUtil;
import com.br.tecnodev.tecnodev.category.Category;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/categories")
public class ShowCategories extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = JPAUtil.getEntityManager();
        CategoryDao categoryDao = new CategoryDao(em);

        List<Category> categoryList = categoryDao.listAllCategories();
        PrintWriter out = resp.getWriter();

        StringBuilder html = new StringBuilder("""
        <!DOCTYPE html>
                <html>
                    <style>
                    table, th, td {
                    border:1px solid black;
                    border-collapse: collapse;
                    }
                    </style>
                    <head>
                        <title>Cursos</title>
                    </head>
                    <body>
                        <h1>RELATÓRIO TECNODEV<br></h1>
                        <h2>Categorias Com Servlet</h2>
                    <table style="width:100%">
                    <tr>
                        <th>ID</th>
                        <th>NOME</th>
                        <th>CÓDIGO</th>
                        <th>DESCRIÇÃO</th>
                        <th>ATIVO</th>
                        <th>ORDEM</th>
                        <th>URL DA IMAGEM</th>
                        <th>CÓDIGO DA COR</th>
                    </tr>
                """);

        for (Category category : categoryList) {
            html.append("""
                    <tr>
                        <td> %d </td>
                        <td> %s </td>
                        <td> %s </td>
                        <td> %s </td>
                        <td> %s </td>
                        <td> %d </td>
                        <td> <img src="%s" alt="icone" width="150px"> </td>
                        <td style="background-color: %s"></td>
                    </tr>
                    """.formatted(category.getId(), category.getName(), category.getCode(), category.getDescription(),
                    category.isActive(), category.getOrderInSystem(), category.getImageUrl(), category.getColorCode()));
        }

        em.close();
        out.println(html);
    }
}
