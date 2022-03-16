package jdbc;

import jdbc.connection.ConnectionFactory;
import jdbc.dao.CourseDao;
import tecnodev.category.Category;
import tecnodev.course.Course;
import tecnodev.subCategory.SubCategory;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseMainJDBC {

    public static void main(String[] args){

        Category categoria = new Category("Programação", "programacao");
        SubCategory subcategoria = new SubCategory("Java", "java", categoria);
        Course curso = new Course("Javacript", "js-script", 7, "professor", subcategoria);

        try (Connection connection = new ConnectionFactory().recuperarConexao()) {
            CourseDao courseDao = new CourseDao(connection);
//            courseDao.insertCourse(curso);
            courseDao.deleteCourse("js-script");
//            courseDao.transformCourseToPublic();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
