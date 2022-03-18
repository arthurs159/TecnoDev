package jpa;

import jpa.dao.CategoryDao;
import jpa.util.JPAUtil;
import tecnodev.category.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class main {

    public static void main(String[] args) {
        Category category = new Category("Python", "pyython", 3, "curso python", true, "www.google.com.br", "#9AEA20");

        EntityManager em = JPAUtil.getEntityManager();

        CategoryDao catDao = new CategoryDao(em);

        em.getTransaction().begin();
        catDao.insert(category);
        em.getTransaction().commit();
        em.close();
    }

}
