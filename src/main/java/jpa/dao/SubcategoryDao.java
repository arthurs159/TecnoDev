package jpa.dao;

import tecnodev.subCategory.SubCategory;

import javax.persistence.EntityManager;
import java.util.List;

public class SubcategoryDao {

    private EntityManager em;

    public SubcategoryDao(EntityManager em) {
        this.em = em;
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
                WHERE s.active = 1 ORDER BY s.orderInSystem
                """;

        return em.createQuery(jpql, SubCategory.class)
                .getResultList();
    }

    public List<String> listAllSubcategoryNameWithoutDescription() {
        String jpql = """
                SELECT s.name FROM SubCategory s
                WHERE s.description = ''
                """;

        return em.createQuery(jpql, String.class)
                .getResultList();
    }

}
