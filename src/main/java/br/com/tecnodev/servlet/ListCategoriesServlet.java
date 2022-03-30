package br.com.tecnodev.servlet;

import br.com.tecnodev.jpa.dao.CategoryDao;
import br.com.tecnodev.jpa.util.JPAUtil;
import br.com.tecnodev.tecnodev.category.CategoryDTO;
import br.com.tecnodev.tecnodev.category.Category;

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
        List<CategoryDTO> categoryDTOList = CategoryDTO.fromEntityToDTO(categoryList);

        request.setAttribute("categoriesDTOList", categoryDTOList);

        RequestDispatcher rd = request.getRequestDispatcher("jsp/category/listCategories.jsp");

        rd.forward(request, response);
    }
}
