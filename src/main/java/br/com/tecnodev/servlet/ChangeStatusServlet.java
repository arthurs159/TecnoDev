package br.com.tecnodev.servlet;

import br.com.tecnodev.jpa.dao.CategoryDao;
import br.com.tecnodev.jpa.util.JPAUtil;
import br.com.tecnodev.tecnodev.category.Category;

import javax.persistence.EntityManager;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/changeStatus")
public class ChangeStatusServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        EntityManager em = JPAUtil.getEntityManager();
        CategoryDao categoryDao = new CategoryDao(em);

        Long id = Long.valueOf(request.getParameter("id"));

        Category category = categoryDao.getCategoryById(id);

        category.toggleActive();
        em.getTransaction().begin();
        categoryDao.update(category);
        em.getTransaction().commit();
        em.close();
    }

}
