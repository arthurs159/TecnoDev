package jpa.dao;

import com.br.tecnodev.jpa.dao.SubcategoryDao;
import jpa.dao.util.JPAUtil;
import jpa.dao.util.builder.CategoryBuilder;
import jpa.dao.util.builder.SubCategoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.br.tecnodev.tecnodev.category.Category;
import com.br.tecnodev.tecnodev.subCategory.SubCategory;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SubCategoryDaoTest {

    private SubcategoryDao dao;
    private EntityManager em;
    private Category categoryBackEnd;

    @BeforeEach
    public void beforeEach() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new SubcategoryDao(em);
        em.getTransaction().begin();
        this.categoryBackEnd = CategoryBuilder.categoryBackEnd();
        em.persist(this.categoryBackEnd);
    }

    @AfterEach
    public void afterEach() {
        em.getTransaction().rollback();
    }

    @Test
    void listAllActiveShouldReturnAllActiveSubcategoryInOrderFromDatabase() {
        SubCategory subCategoryJava = SubCategoryBuilder.subCategoryJava(categoryBackEnd);
        SubCategory subCategoryJs = SubCategoryBuilder.subCategoryJs(categoryBackEnd);
        SubCategory subCategoryPython = SubCategoryBuilder.subCategoryPython(categoryBackEnd);

        em.persist(subCategoryJs);
        em.persist(subCategoryPython);
        em.persist(subCategoryJava);

        List<SubCategory> subCategoryList = dao.listAllActive();

        assertNotNull(subCategoryList);
        assertEquals(2, subCategoryList.size());
        assertEquals(subCategoryJava, subCategoryList.get(0));
        assertEquals(subCategoryJs, subCategoryList.get(1));
    }

    @Test
    void listAllSubcategoryNameWithoutDescriptionShouldReturnAllSubCategoryNameWithNoName() {
        SubCategory subCategoryCommunication = SubCategoryBuilder.subCategoryCommunication(categoryBackEnd);
        SubCategory subCategoryJs = SubCategoryBuilder.subCategoryJs(categoryBackEnd);
        SubCategory subCategoryMobile = SubCategoryBuilder.subCategoryMobile(categoryBackEnd);

        em.persist(subCategoryMobile);
        em.persist(subCategoryJs);
        em.persist(subCategoryCommunication);

        List<String> nameList = dao.listAllSubcategoryNameWithoutDescription();

        assertNotNull(nameList);
        assertEquals(2, nameList.size());
        assertEquals(subCategoryMobile.getName(), nameList.get(0));
        assertEquals(subCategoryCommunication.getName(), nameList.get(1));
    }
}
