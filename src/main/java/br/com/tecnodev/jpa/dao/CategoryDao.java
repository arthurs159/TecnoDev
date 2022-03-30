package br.com.tecnodev.jpa.dao;

import br.com.tecnodev.tecnodev.category.Category;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoryDao {

    private EntityManager em;

    public CategoryDao(EntityManager em) {
        this.em = em;
    }

    public List<Category> listAllActive() {
        String jpql = "SELECT c FROM Category c WHERE c.active = true ORDER BY c.orderInSystem";
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

    public void insertCategory(Category category) {
        this.em.persist(category);
        System.out.println("Generated id: " + category.getId());
    }

    public int updateCategoryById(Long id, Category category) {
        String jqpl = """
                UPDATE Category c SET c.name = :name, c.code = :code, c.description = :description, c.studyGuide = :studyGuide,
                c.active = :active, c.orderInSystem = :orderInSystem, c.imageUrl = :imageUrl, c.colorCode = :colorCode
                WHERE c.id = :id
                """;

        return em.createQuery(jqpl)
                .setParameter("id", id)
                .setParameter("name", category.getName())
                .setParameter("code", category.getCode())
                .setParameter("description", category.getDescription())
                .setParameter("studyGuide", category.getStudyGuide())
                .setParameter("active", category.isActive())
                .setParameter("orderInSystem", category.getOrderInSystem())
                .setParameter("imageUrl", category.getImageUrl())
                .setParameter("colorCode", category.getColorCode())
                .executeUpdate();
    }

    public void update(Category category) {
        try{
            this.em.merge(category);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
