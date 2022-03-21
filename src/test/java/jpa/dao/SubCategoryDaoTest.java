package jpa.dao;

import jpa.dao.util.JPAUtil;
import jpa.dao.util.builder.CategoryBuilder;
import jpa.dao.util.builder.SubCategoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tecnodev.category.Category;
import tecnodev.subCategory.SubCategory;

import javax.persistence.EntityManager;
import java.util.List;

public class SubCategoryDaoTest {

    private CategoryDao catDao;
    private SubcategoryDao dao;
    private EntityManager em;

    @BeforeEach
    public void beforeEach() {
        this.em = JPAUtil.getEntityManager();
        this.catDao = new CategoryDao(em);
        this.dao = new SubcategoryDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void afterEach() {
        em.getTransaction().rollback();
    }

    @Test
    void listAllActiveShouldReturnAllActiveSubcategoryFromDatabase() {
        Category category = new CategoryBuilder()
                .name("Back-End")
                .code("backend")
                .description("curso back-end")
                .active(true)
                .orderInSystem(4)
                .imageUrl("www.google.com.br")
                .colorCode("#9AEA20")
                .create();
        em.persist(category);

        SubCategory java = new SubCategoryBuilder()
                .name("Java")
                .code("java")
                .description("Projetos em java")
                .active(true)
                .orderInSystem(1)
                .category(category)
                .create();

        SubCategory javaScript = new SubCategoryBuilder()
                .name("JavaScript")
                .code("javascript")
                .description("Projetos em JavaScript")
                .active(true)
                .orderInSystem(2)
                .category(category)
                .create();

        SubCategory python = new SubCategoryBuilder()
                .name("Python")
                .code("python")
                .description("Projetos em Python")
                .active(false)
                .orderInSystem(3)
                .category(category)
                .create();


        em.persist(java);
        em.persist(javaScript);
        em.persist(python);

        List<SubCategory> subCategoryList = dao.listAllActive();

        Assertions.assertEquals(2, subCategoryList.size());
    }
}
