package jpa.dao;

import jpa.dao.util.JPAUtil;
import jpa.dao.util.builder.CategoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tecnodev.category.Category;

import javax.persistence.EntityManager;
import java.util.List;

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
    void listAllActiveShouldReturnAllActiveCategoriesFromDatabase() {
        Category category1 = new CategoryBuilder()
                .name("Back-End")
                .code("backend")
                .description("curso back-end")
                .active(true)
                .orderInSystem(4)
                .imageUrl("www.google.com.br")
                .colorCode("#9AEA20")
                .create();

        Category category2 = new CategoryBuilder()
                .name("Front-End")
                .code("frontend")
                .description("curso front-end")
                .active(true)
                .orderInSystem(5)
                .imageUrl("www.google.com.br")
                .colorCode("#9AEA20")
                .create();

        Category category3 = new CategoryBuilder()
                .name("DevOps")
                .code("devops")
                .description("curso Devops")
                .active(false)
                .orderInSystem(5)
                .imageUrl("www.google.com.br")
                .colorCode("#9AEA20")
                .create();

        em.persist(category1);
        em.persist(category2);
        em.persist(category3);

        List<Category> categories = dao.listAllActive();
        Assertions.assertEquals(2, categories.size());
    }

}