package jpa.main;

import jpa.dao.CourseDao;
import jpa.util.JPAUtil;

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
