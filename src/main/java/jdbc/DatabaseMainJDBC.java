package jdbc;

import jdbc.dao.CourseDao;
import tecnodev.category.Category;
import tecnodev.course.Course;
import tecnodev.subCategory.SubCategory;

import java.sql.SQLException;

public class DatabaseMainJDBC {

    public static void main(String[] args) throws SQLException {

        CourseDao courseDao = new CourseDao();

        Category category = courseDao.getCategoryFromDatabase("programacao");
        SubCategory subCategory = courseDao.getSubCategoryFromDatabase("java", category);
        Course curso = new Course("Javacript", "ja-front", 7, "professor", subCategory);


            courseDao.insertCourse(curso);
//            courseDao.deleteCourse("js-script");
//            courseDao.transformCourseToPublic();

    }
}