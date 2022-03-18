package jdbc;

import jdbc.dao.CategoryDao;
import jdbc.dao.CourseDao;
import jdbc.dao.SubcategoryDao;
import jdbc.util.JPAUtil;
import tecnodev.category.Category;
import tecnodev.course.Course;
import tecnodev.subCategory.SubCategory;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class DatabaseMainJDBC {

    public static void main(String[] args) throws SQLException {

        EntityManager em = JPAUtil.getEntityManager();

        SubcategoryDao subcategoryDao = new SubcategoryDao(em);
        Course curso = new Course("Javacript", "ja-fro", 7, "professor",
                subcategoryDao.getSubcategoryByCode("python"));

        CourseDao courseDao = new CourseDao(em);
        CategoryDao categoryDao = new CategoryDao(em);

//        List<Category> list = categoryDao.listAllActive();
//        list.forEach(System.out::println);

//        List<SubCategory> subList = subcategoryDao.listAllActive();
//        List<SubCategory> subList2 = subcategoryDao.listAllSubcategoryWithoutDescription();
//        subList.forEach(System.out::println);
//        subList2.forEach(System.out::println);

//        List<Course> courseList = courseDao.listAllPublicCourses();
//        courseList.forEach(System.out::println);

//        em.getTransaction().begin();
//        courseDao.insertCourseJPA(curso);
//        courseDao.deleteCourseJPA("ja-fro");
//        courseDao.makeAllCoursesPublicJPA();
//        em.getTransaction().commit();
//        em.close();

    }
}