package jdbc;

import jdbc.dao.CourseDao;
import jdbc.dao.SubcategoryDao;
import jdbc.util.JPAUtil;
import tecnodev.category.Category;
import tecnodev.course.Course;
import tecnodev.subCategory.SubCategory;

import javax.persistence.EntityManager;
import java.sql.SQLException;

public class DatabaseMainJDBC {

    public static void main(String[] args) throws SQLException {

        EntityManager em = JPAUtil.getEntityManager();

        SubcategoryDao subcategoryDao = new SubcategoryDao(em);
        Course curso = new Course("Javacript", "ja-fro", 7, "professor",
                subcategoryDao.getSubcategoryByCode("python"));

        CourseDao courseDao = new CourseDao(em);

        em.getTransaction().begin();
//        courseDao.insertCourseJPA(curso);
//        courseDao.deleteCourseJPA("ja-fro");
        courseDao.makeAllCoursesPublicJPA();
        em.getTransaction().commit();
        em.close();

    }
}