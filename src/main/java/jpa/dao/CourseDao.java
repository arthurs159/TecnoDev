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
        String jpql = """
                UPDATE FROM Course c
                SET c.visibility = 'PUBLIC'
                WHERE c.visibility = 'PRIVATE'
                """;

        em.createQuery(jpql)
                .executeUpdate();

        System.out.println("Todos os cursos estão publicos!!");
    }

    public List<Course> listAllPublicCourses() {
        String jpql = """
                SELECT c FROM Course c
                JOIN FETCH c.subCategory
                WHERE
                c.visibility = 'PUBLIC'
                """;

        return em.createQuery(jpql, Course.class)
                .getResultList();
    }

}

