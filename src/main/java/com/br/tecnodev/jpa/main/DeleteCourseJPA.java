package com.br.tecnodev.jpa.main;

import com.br.tecnodev.jpa.dao.CourseDao;
import com.br.tecnodev.jpa.util.JPAUtil;

import javax.persistence.EntityManager;

public class DeleteCourseJPA {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        CourseDao courseDao = new CourseDao(em);

        em.getTransaction().begin();

        courseDao.deleteCourseJPA("jaspring");

        em.getTransaction().commit();
        em.close();
    }
}
