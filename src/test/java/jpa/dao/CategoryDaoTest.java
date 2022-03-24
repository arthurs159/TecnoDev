package jpa.dao;

import com.br.tecnodev.jpa.dao.CategoryDao;
import jpa.dao.util.JPAUtil;
import jpa.dao.util.builder.CategoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.br.tecnodev.tecnodev.category.Category;

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
        Category categoryFrontEnd = CategoryBuilder.categoryFrontEnd();
        Category categoryBackEnd = CategoryBuilder.categoryBackEnd();
        Category categoryDevOps = CategoryBuilder.categoryDevops();

        em.persist(categoryFrontEnd);
        em.persist(categoryBackEnd);
        em.persist(categoryDevOps);
        List<Category> categories = dao.listAllActive();

        assertNotNull(categories);
        assertEquals(2, categories.size());
        assertEquals(categoryBackEnd, categories.get(0));
        assertEquals(categoryFrontEnd, categories.get(1));
    }

}