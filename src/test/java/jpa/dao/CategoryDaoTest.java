package jpa.dao;

import jpa.dao.util.JPAUtil;
import jpa.dao.util.builder.CategoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tecnodev.category.Category;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategoryDaoTest {

    private CategoryDao dao;
    private EntityManager em;

    @BeforeEach
    public void beforeEach() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new CategoryDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void afterEach() {
        em.getTransaction().rollback();
    }

    @Test
    void listAllActiveShouldReturnAllActiveCategoriesInOrderFromDatabase() {
        Category frontEndCategory = new CategoryBuilder()
                .withName("Front-End")
                .withCode("frontend")
                .withDescription("Curso front-end")
                .withActive(true)
                .withOrderInSystem(5)
                .withImageUrl("www.google.com.br")
                .withColorCode("#9AEA20")
                .create();

        Category backEndCategory = new CategoryBuilder()
                .withName("Back-End")
                .withCode("backend")
                .withDescription("Curso back-end")
                .withActive(true)
                .withOrderInSystem(4)
                .withImageUrl("www.google.com.br")
                .withColorCode("#9AEA20")
                .create();

        Category devOpsCategory = new CategoryBuilder()
                .withName("DevOps")
                .withCode("devops")
                .withDescription("Curso Devops")
                .withActive(false)
                .withOrderInSystem(5)
                .withImageUrl("www.google.com.br")
                .withColorCode("#9AEA20")
                .create();

        em.persist(frontEndCategory);
        em.persist(backEndCategory);
        em.persist(devOpsCategory);
        List<Category> categories = dao.listAllActive();

        assertNotNull(categories);
        assertEquals(2, categories.size());
        assertEquals(backEndCategory, categories.get(0));
        assertEquals(frontEndCategory, categories.get(1));
    }

}