package br.com.tecnodev.jpa.main;

import br.com.tecnodev.jpa.dao.CourseDao;
import br.com.tecnodev.jpa.dao.SubcategoryDao;
import br.com.tecnodev.jpa.util.JPAUtil;
import br.com.tecnodev.tecnodev.course.Course;

import javax.persistence.EntityManager;

public class InsertNewCourseJPA {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        SubcategoryDao subcategoryDao = new SubcategoryDao(em);
        CourseDao courseDao = new CourseDao(em);

        Course curso = new Course("Java Spring",
                "jaspring",
                7,
                "professor",
                subcategoryDao.getSubcategoryByCode("java"));

        em.getTransaction().begin();

        courseDao.insertCourseJPA(curso);

        em.getTransaction().commit();
        em.close();
    }
}
