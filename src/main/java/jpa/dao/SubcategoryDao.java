package jpa.dao;

import tecnodev.subCategory.SubCategory;

import javax.persistence.EntityManager;
import java.util.List;

public class SubcategoryDao {

    private final EntityManager em;

    public SubcategoryDao(EntityManager em) {
        this.em = em;
    }

    public List<SubCategory> getSubcategory() {
        String sql = "SELECT s FROM SubCategory s";
        return em.createQuery(sql, SubCategory.class)
                .getResultList();
    }

    public SubCategory getSubcategoryByCode(String code) {
        String jpql = """
                SELECT s FROM SubCategory s 
                WHERE s.code = :code
                """;

        return em.createQuery(jpql, SubCategory.class)
                .setParameter("code", code)
                .getSingleResult();
    }

    public List<SubCategory> listAllActive() {
        String jpql = """
                SELECT s FROM SubCategory s
                JOIN FETCH s.category
                WHERE s.active = 1 ORDER BY s.name
                """;

        return em.createQuery(jpql, SubCategory.class)
                .getResultList();
    }

    public List<String> listAllSubcategoryWithoutDescription() {
        String jpql = """
                SELECT s.name FROM SubCategory s 
                WHERE s.description = ''
                """;

        return em.createQuery(jpql, String.class)
                .getResultList();
    }

}
