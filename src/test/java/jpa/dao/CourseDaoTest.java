package jpa.dao;

import jpa.dao.util.JPAUtil;
import jpa.dao.util.builder.CategoryBuilder;
import jpa.dao.util.builder.CourseBuilder;
import jpa.dao.util.builder.SubCategoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tecnodev.category.Category;
import tecnodev.course.Course;
import tecnodev.course.Status;
import tecnodev.subCategory.SubCategory;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CourseDaoTest {

    private CourseDao dao;
    private EntityManager em;

    @BeforeEach
    public void beforeEach() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new CourseDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void afterEach() {
        em.getTransaction().rollback();
    }

    @Test
    void listAllPublicCoursesShouldReturnAllPublicCourses() {
        Category backend = new CategoryBuilder()
                .withName("Back-End")
                .withCode("backend")
                .withDescription("curso back-end")
                .withActive(true)
                .withOrderInSystem(4)
                .withImageUrl("www.google.com.br")
                .withColorCode("#9AEA20")
                .create();
        em.persist(backend);

        SubCategory java = new SubCategoryBuilder()
                .withName("Java")
                .withCode("java")
                .withDescription("Projetos em java")
                .withActive(true)
                .withOrderInSystem(1)
                .withCategory(backend)
                .create();
        em.persist(java);

        Course javaSintax = new CourseBuilder()
                .withName("Java e Sintaxe")
                .withCode("javasintax")
                .withEstimatedTimeInHours(5)
                .withVisibility(Status.PUBLIC)
                .withTargetAudience("Pessoas que gostam de Java")
                .withTeacher("Cleb Paulo")
                .withDevelopedSkills("Aprenda a sintaxe de java")
                .withSubCategory(java)
                .create();

        Course jpa = new CourseBuilder()
                .withName("JPA").withCode("jpa")
                .withEstimatedTimeInHours(5)
                .withVisibility(Status.PUBLIC)
                .withTargetAudience("Pessoas que gostam de Java")
                .withTeacher("Cleb Paulo")
                .withDevelopedSkills("Aprenda JPA")
                .withSubCategory(java)
                .create();

        Course python = new CourseBuilder()
                .withName("Python")
                .withCode("py")
                .withEstimatedTimeInHours(5)
                .withVisibility(Status.PRIVATE)
                .withTargetAudience("Pessoas que gostam de Python")
                .withTeacher("Cleb Paulo")
                .withDevelopedSkills("Aprenda uma nova linguagem")
                .withSubCategory(java)
                .create();

        em.persist(javaSintax);
        em.persist(jpa);
        em.persist(python);

        List<Course> courseList = dao.listAllPublicCourses();

        assertNotNull(courseList);
        Assertions.assertEquals(2, courseList.size());
        assertEquals(javaSintax, courseList.get(0));
        assertEquals(jpa, courseList.get(1));
    }
}
