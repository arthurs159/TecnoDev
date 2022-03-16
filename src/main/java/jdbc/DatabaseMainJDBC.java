package jdbc;

import jdbc.dao.CourseDao;
import tecnodev.category.Category;
import tecnodev.course.Course;
import tecnodev.subCategory.SubCategory;

import java.sql.SQLException;

public class DatabaseMainJDBC {

    public static void main(String[] args) throws SQLException {

        Category categoria = new Category("Programação", "programacao");
        SubCategory subcategoria = new SubCategory("Java", "java", categoria);
        Course curso = new Course("Javacript", "js-script", 7, "professor", subcategoria);

        CourseDao courseDao = new CourseDao();
//            courseDao.insertCourse(curso);
//            courseDao.deleteCourse("js-script");
//            courseDao.transformCourseToPublic();

    }
}
