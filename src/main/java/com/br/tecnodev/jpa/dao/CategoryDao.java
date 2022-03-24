package com.br.tecnodev.jpa.dao;

import com.br.tecnodev.tecnodev.category.Category;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoryDao {

    private EntityManager em;

    public CategoryDao(EntityManager em) {
        this.em = em;
    }

    public List<Category> listAllActive() {
        String jpql = "SELECT c FROM Category c WHERE c.active = 1 ORDER BY c.orderInSystem";
        return em.createQuery(jpql, Category.class)
                .getResultList();
    }

    public List<Category> listAllCategories(){
        String jpql = "SELECT c FROM Category c";

        return em.createQuery(jpql, Category.class)
                .getResultList();
    }

}
