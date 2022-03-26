package com.br.tecnodev.jpa.dao;

import com.br.tecnodev.tecnodev.category.Category;
import com.br.tecnodev.tecnodev.course.Course;

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

    public List<Category> listAllCategories() {
        String jpql = "SELECT c FROM Category c";

        return em.createQuery(jpql, Category.class)
                .getResultList();
    }

    public Category getCategoryById(Long id) {
        String jpql = "SELECT c FROM Category c WHERE c.id = :id";

        return em.createQuery(jpql, Category.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public int updateCategoryById(Long id, String name, String code, String description, boolean active,
                                  Integer orderInSystem, String imageUrl, String colorCode) {
        String jqpl = """
                UPDATE Category c SET c.name = :name, c.code = :code, c.description = :description,
                c.active = :active, c.orderInSystem = :orderInSystem, c.imageUrl = :imageUrl, c.colorCode = :colorCode
                WHERE c.id = :id
                """;

        return em.createQuery(jqpl)
                .setParameter("id", id)
                .setParameter("name", name)
                .setParameter("code", code)
                .setParameter("description", description)
                .setParameter("active", active)
                .setParameter("orderInSystem", orderInSystem)
                .setParameter("imageUrl", imageUrl)
                .setParameter("colorCode", colorCode)
                .executeUpdate();
    }

}
