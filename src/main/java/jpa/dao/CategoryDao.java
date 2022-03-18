package jpa.dao;

import tecnodev.category.Category;

import javax.persistence.EntityManager;

public class CategoryDao {

    private EntityManager em;

    public CategoryDao(EntityManager em){
        this.em = em;
    }

    public void insert(Category category){
        this.em.persist(category);
    }

}
