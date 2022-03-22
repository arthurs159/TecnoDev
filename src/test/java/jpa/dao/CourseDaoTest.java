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

public class CourseDaoTest {

    private CategoryDao catDao;
    private SubcategoryDao subDao;
    private CourseDao dao;
    private EntityManager em;

    @BeforeEach
    public void beforeEach() {
        this.em = JPAUtil.getEntityManager();
        this.catDao = new CategoryDao(em);
        this.subDao = new SubcategoryDao(em);
        this.dao = new CourseDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void afterEach() {
        em.getTransaction().rollback();
    }

    @Test
    void listAllPublicCoursesShouldReturnAllPublicCourses(){
        Category backend = new CategoryBuilder()
                .name("Back-End").code("backend").description("curso back-end").active(true)
                .orderInSystem(4).imageUrl("www.google.com.br").colorCode("#9AEA20")
                .create();
        em.persist(backend);

        SubCategory java = new SubCategoryBuilder()
                .name("Java").code("java").description("Projetos em java")
                .active(true).orderInSystem(1).category(backend)
                .create();
        em.persist(java);

        Course javaSintax = new CourseBuilder()
                .name("Java e Sintaxe").code("javasintax").estimatedTimeInHours(5)
                .visibility(Status.PUBLIC).targetAudience("Pessoas que gostam de Java").teacher("Cleb Paulo")
                .developedSkills("Aprenda a sintaxe de java").subCategory(java)
                .create();

        Course jpa = new CourseBuilder()
                .name("JPA").code("jpa").estimatedTimeInHours(5)
                .visibility(Status.PUBLIC).targetAudience("Pessoas que gostam de Java")
                .teacher("Cleb Paulo").developedSkills("Aprenda JPA").subCategory(java)
                .create();

        Course python = new CourseBuilder()
                .name("Python").code("py").estimatedTimeInHours(5)
                .visibility(Status.PRIVATE).targetAudience("Pessoas que gostam de Python")
                .teacher("Cleb Paulo").developedSkills("Aprenda uma nova linguagem").subCategory(java)
                .create();

        em.persist(javaSintax);
        em.persist(jpa);
        em.persist(python);

        List<Course> courseList = dao.listAllPublicCourses();

        Assertions.assertEquals(2, courseList.size());
    }
}
