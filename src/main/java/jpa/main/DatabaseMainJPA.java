package jpa.main;


import jpa.dao.CategoryDao;
import jpa.dao.CourseDao;
import jpa.dao.SubcategoryDao;
import jpa.util.JPAUtil;
import tecnodev.category.Category;
import tecnodev.course.Course;
import tecnodev.subCategory.SubCategory;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class DatabaseMainJPA {

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
//        subList.forEach(System.out::println);


//        List<String> subList2 = subcategoryDao.listAllSubcategoryWithoutDescription();
//        subList2.forEach(System.out::println);

//        for (String string: subList2) {
//            System.out.println(string);
//        }

//        Long id = subcategoryDao.getCategoryId("programacao");
//        Long subcatId = courseDao.getSubcategoryIdByCode("java");
//
//        System.out.println(subcatId);

//        System.out.println(id);
        List<Course> courseList = courseDao.listAllPublicCourses();
        courseList.forEach(System.out::println);


    }
}