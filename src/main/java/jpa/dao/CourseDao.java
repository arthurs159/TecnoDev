package jpa.dao;

import tecnodev.course.Course;

import javax.persistence.EntityManager;
import java.util.List;

public class CourseDao {

    private EntityManager em;

    public CourseDao(EntityManager em) {
        this.em = em;
    }

    public void insertCourseJPA(Course course) {
        this.em.persist(course);
        System.out.println("Generated id: " + course.getId());
    }

    public void deleteCourseJPA(String code) {
        String jpql = "DELETE FROM Course c WHERE c.code = :code";

        em.createQuery(jpql)
                .setParameter("code", code)
                .executeUpdate();

        System.out.println("O curso de código: ( " + code + " ) foi deletado com sucesso!!");
    }

    public void makeAllCoursesPublicJPA() {
        String jpql = "UPDATE FROM Course c SET c.visibility = 'PUBLIC' WHERE c.visibility = 'PRIVATE'";

        em.createQuery(jpql)
                .executeUpdate();

        System.out.println("Todos os cursos estão publicos!!");
    }

    public List<Course> listAllPublicCourses() {
        String jpql = "SELECT c FROM Course c WHERE c.visibility = 1";

        return em.createQuery(jpql, Course.class)
                .getResultList();
    }

    public Long getSubcategoryIdByCode(String code) {
        String jpql = "SELECT c.subCategory.id FROM Course c " +
                "INNER JOIN SubCategory s " +
                "ON s.id = c.subCategory.id " +
                "WHERE s.code = :code " +
                "GROUP BY s.id";

        return em.createQuery(jpql, Long.class)
                .setParameter("code", code)
                .getSingleResult();
    }

}

