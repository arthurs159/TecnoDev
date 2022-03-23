package jpa.dao;

import jpa.dao.util.JPAUtil;
import jpa.dao.util.builder.CategoryBuilder;
import jpa.dao.util.builder.SubCategoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tecnodev.category.Category;
import tecnodev.subCategory.SubCategory;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SubCategoryDaoTest {

    private SubcategoryDao dao;
    private EntityManager em;
    private Category category;

    @BeforeEach
    public void beforeEach() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new SubcategoryDao(em);
        em.getTransaction().begin();

        category = new CategoryBuilder()
                .withName("Back-End")
                .withCode("backend")
                .withDescription("curso back-end")
                .withActive(true)
                .withOrderInSystem(4)
                .withImageUrl("www.google.com.br")
                .withColorCode("#9AEA20")
                .create();
        em.persist(category);
    }

    @AfterEach
    public void afterEach() {
        em.getTransaction().rollback();
    }

    @Test
    void listAllActiveShouldReturnAllActiveSubcategoryInOrderFromDatabase() {
        SubCategory javaScript = new SubCategoryBuilder()
                .withName("JavaScript")
                .withCode("javascript")
                .withDescription("Projetos em JavaScript")
                .withActive(true)
                .withOrderInSystem(2)
                .withCategory(category)
                .create();

        SubCategory java = new SubCategoryBuilder()
                .withName("Java")
                .withCode("java")
                .withDescription("Projetos em java")
                .withActive(true)
                .withOrderInSystem(1)
                .withCategory(category)
                .create();

        SubCategory python = new SubCategoryBuilder()
                .withName("Python")
                .withCode("python")
                .withDescription("Projetos em Python")
                .withActive(false)
                .withOrderInSystem(3)
                .withCategory(category)
                .create();

        em.persist(javaScript);
        em.persist(python);
        em.persist(java);

        List<SubCategory> subCategoryList = dao.listAllActive();

        assertNotNull(subCategoryList);
        assertEquals(2, subCategoryList.size());
        assertEquals(java, subCategoryList.get(0));
        assertEquals(javaScript, subCategoryList.get(1));
    }

    @Test
    void listAllSubcategoryNameWithoutDescriptionShouldReturnAllSubCategoryNameWithNoName() {
        SubCategory mobile = new SubCategoryBuilder()
                .withName("Mobile")
                .withCode("mobile")
                .withDescription("Conheça nossos cursos de Mobile")
                .withActive(true)
                .withOrderInSystem(1)
                .withCategory(category)
                .create();

        SubCategory communication = new SubCategoryBuilder()
                .withName("Comunicação Assertiva")
                .withCode("communication")
                .withDescription("")
                .withActive(true)
                .withOrderInSystem(2)
                .withCategory(category)
                .create();

        SubCategory aws = new SubCategoryBuilder()
                .withName("Amazon Web Service")
                .withCode("aws")
                .withDescription("")
                .withActive(true)
                .withOrderInSystem(3)
                .withCategory(category)
                .create();

        em.persist(mobile);
        em.persist(communication);
        em.persist(aws);

        List<String> nameList = dao.listAllSubcategoryNameWithoutDescription();

        assertNotNull(nameList);
        assertEquals(2, nameList.size());
        assertEquals(communication.getName(), nameList.get(0));
        assertEquals(aws.getName(), nameList.get(1));
    }
}
