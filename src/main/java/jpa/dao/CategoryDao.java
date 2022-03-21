package jpa.dao;

import tecnodev.category.Category;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoryDao {

    private EntityManager em;

    public CategoryDao(EntityManager em) {
        this.em = em;
    }

    public List<Category> listAllActive() {
        String jpql = "SELECT c FROM Category c WHERE c.active = 1 ORDER BY c.name";
        return em.createQuery(jpql, Category.class)
                .getResultList();
    }

}
