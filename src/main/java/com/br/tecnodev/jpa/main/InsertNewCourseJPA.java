package com.br.tecnodev.jpa.main;

import com.br.tecnodev.jpa.dao.CourseDao;
import com.br.tecnodev.jpa.dao.SubcategoryDao;
import com.br.tecnodev.jpa.util.JPAUtil;
import com.br.tecnodev.tecnodev.course.Course;

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