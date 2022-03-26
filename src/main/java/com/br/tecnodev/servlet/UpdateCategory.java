package com.br.tecnodev.servlet;

import com.br.tecnodev.jpa.dao.CategoryDao;
import com.br.tecnodev.jpa.util.JPAUtil;
import com.br.tecnodev.tecnodev.category.Category;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/updateCategory")
public class UpdateCategory extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = JPAUtil.getEntityManager();
        CategoryDao categoryDao = new CategoryDao(em);

        Long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String description = request.getParameter("description");
        boolean active = Boolean.parseBoolean(request.getParameter("active"));
        Integer orderInSystem = Integer.valueOf(request.getParameter("orderInSystem"));
        String imageUrl = request.getParameter("imageUrl");
        String colorCode = request.getParameter("colorCode");

        em.getTransaction().begin();

        categoryDao.updateCategoryById(id,name, code, description, active, orderInSystem, imageUrl, colorCode);

        response.sendRedirect("/categories");
        em.getTransaction().commit();
        em.close();
    }
}
