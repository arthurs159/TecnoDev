package jdbc.dao;

import jdbc.dao.util.JPAUtil;
import jpa.dao.CategoryDao;

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
    public void beforeEach(){
        this.em = JPAUtil.getEntityManager();
        this.dao = new CategoryDao(em);
    }

    @Test
    void getAllActiveCategory() {
        List<Category> category = this.dao.listAllActive();
        Assertions.assertNotNull(category);
    }

}