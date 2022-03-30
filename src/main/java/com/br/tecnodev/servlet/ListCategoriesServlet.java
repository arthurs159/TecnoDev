package com.br.tecnodev.servlet;

import com.br.tecnodev.jpa.dao.CategoryDao;
import com.br.tecnodev.jpa.util.JPAUtil;
import com.br.tecnodev.tecnodev.category.Category;
import com.br.tecnodev.tecnodev.category.CategoryDTO;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/categories")
public class ListCategoriesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = JPAUtil.getEntityManager();
        CategoryDao categoryDao = new CategoryDao(em);

        List<Category> categoryList = categoryDao.listAllCategories();
        List<CategoryDTO> categoryDTOList = CategoryDTO.fromDto(categoryList);

        request.setAttribute("categoriesDTOList", categoryDTOList);

        RequestDispatcher rd = request.getRequestDispatcher("jsp/category/listCategories.jsp");

        rd.forward(request, response);
    }
}
