package com.br.tecnodev.servlet;

import com.br.tecnodev.jpa.dao.CategoryDao;
import com.br.tecnodev.jpa.util.JPAUtil;
import com.br.tecnodev.tecnodev.category.Category;

import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/editCategory")
public class GetCategoryById extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = JPAUtil.getEntityManager();
        CategoryDao categoryDao = new CategoryDao(em);

        String paramId = request.getParameter("id");
        Long id = Long.valueOf(paramId);

        Category category = categoryDao.getCategoryById(id);

        request.setAttribute("category", category);
        RequestDispatcher rd = request.getRequestDispatcher("/editCategoryForm.jsp");
        rd.forward(request, response);
    }

}
