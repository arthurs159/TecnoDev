package jdbc;

import jdbc.dao.CategoryDao;
import jdbc.dao.CourseDao;
import jdbc.dao.SubcategoryDao;
import jdbc.util.JPAUtil;

import javax.persistence.EntityManager;
import java.sql.SQLException;

public class DatabaseMainJDBC {

    public static void main(String[] args) throws SQLException {

        EntityManager em = JPAUtil.getEntityManager();

        SubcategoryDao subcategoryDao = new SubcategoryDao(em);
//        Course curso = new Course("Javacript", "ja-fro", 7, "professor",
//                subcategoryDao.getSubcategoryByCode("python"));

        CourseDao courseDao = new CourseDao(em);
        CategoryDao categoryDao = new CategoryDao(em);

//        List<Category> list = categoryDao.listAllActive();
//        list.forEach(System.out::println);

//        List<SubCategory> subList = subcategoryDao.listAllActive();
//        List<String> subList2 = subcategoryDao.listAllSubcategoryWithoutDescription();
//        subList.forEach(System.out::println);
//        subList2.forEach(System.out::println);

//        Long id = subcategoryDao.getCategoryId("programacao");
        Long subcatId = courseDao.getSubcategoryIdByCode("java");

        System.out.println(subcatId);

//        System.out.println(id);
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