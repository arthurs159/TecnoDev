package br.com.tecnodev.servlet;

import br.com.tecnodev.jpa.dao.CategoryDao;
import br.com.tecnodev.jpa.util.JPAUtil;
import br.com.tecnodev.tecnodev.category.CategoryDTO;
import br.com.tecnodev.tecnodev.category.Category;

import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/editCategory")
public class CategoryByIdServet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = JPAUtil.getEntityManager();
        CategoryDao categoryDao = new CategoryDao(em);

        String paramId = request.getParameter("id");
        Long id = Long.valueOf(paramId);

        Category category = categoryDao.getCategoryById(id);
        CategoryDTO categoryDTO = CategoryDTO.fromEntityToDTO(category);

        request.setAttribute("category", categoryDTO);

        RequestDispatcher rd = request.getRequestDispatcher("jsp/category/editCategoryForm.jsp");
        rd.forward(request, response);
    }

}
