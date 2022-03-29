package com.br.tecnodev.servlet;

import com.br.tecnodev.jpa.dao.CategoryDao;
import com.br.tecnodev.jpa.util.JPAUtil;
import com.br.tecnodev.tecnodev.category.Category;
import com.br.tecnodev.tecnodev.category.CategoryDTO;
import com.br.tecnodev.tecnodev.category.NewCategoryForm;

import javax.persistence.EntityManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateCategory")
public class UpdateCategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        EntityManager em = JPAUtil.getEntityManager();
        CategoryDao categoryDao = new CategoryDao(em);

        Long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String description = request.getParameter("description");
        String studyGuide = request.getParameter("studyGuide");
        boolean active = Boolean.parseBoolean(request.getParameter("active"));
        Integer orderInSystem = Integer.valueOf(request.getParameter("orderInSystem"));
        String imageUrl = request.getParameter("imageUrl");
        String colorCode = request.getParameter("colorCode");

        NewCategoryForm categoryForm = new NewCategoryForm(name, code, description, studyGuide, active, orderInSystem, imageUrl, colorCode);

        Category category = categoryForm.toEntity();
        em.getTransaction().begin();

        categoryDao.updateCategoryById(id, category);

        response.sendRedirect("/categories");
        em.getTransaction().commit();
        em.close();
    }

}
