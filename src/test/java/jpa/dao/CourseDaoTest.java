package jpa.dao;

import br.com.tecnodev.jpa.dao.CourseDao;
import jpa.dao.util.JPAUtil;
import jpa.dao.util.builder.CategoryBuilder;
import jpa.dao.util.builder.CourseBuilder;
import jpa.dao.util.builder.SubCategoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import br.com.tecnodev.tecnodev.category.Category;
import br.com.tecnodev.tecnodev.course.Course;
import br.com.tecnodev.tecnodev.subCategory.SubCategory;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CourseDaoTest {

    private CourseDao dao;
    private EntityManager em;
    private Category category;
    private SubCategory subCategory;

    @BeforeEach
    public void beforeEach() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new CourseDao(em);
        category = CategoryBuilder.categoryBackEnd();
        subCategory = SubCategoryBuilder.subCategoryJava(category);
        em.getTransaction().begin();
        em.persist(category);
        em.persist(subCategory);
    }

    @AfterEach
    public void afterEach() {
        em.getTransaction().rollback();
    }

    @Test
    void listAllPublicCoursesShouldReturnAllPublicCourses() {
        Course courseJpa = CourseBuilder.courseJpa(subCategory);
        Course courseJava = CourseBuilder.courseJava(subCategory);
        Course coursePython = CourseBuilder.coursePython(subCategory);

        em.persist(courseJpa);
        em.persist(courseJava);
        em.persist(coursePython);

        List<Course> courseList = dao.listAllPublicCourses();

        assertNotNull(courseList);
        Assertions.assertEquals(2, courseList.size());
        assertEquals(courseJpa, courseList.get(0));
        assertEquals(courseJava, courseList.get(1));
    }
}
