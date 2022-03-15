package jdbc;

import jdbc.connection.ConnectionFactory;
import jdbc.dao.CourseDao;
import tecnodev.category.Category;
import tecnodev.course.Course;
import tecnodev.subCategory.SubCategory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TesteBancoJDBC{

    public static void main(String[] args) throws SQLException {

        try (Connection connection = new ConnectionFactory().recuperarConexao()) {
            CourseDao courseDao = new CourseDao(connection);
//            courseDao.insertCourse();
//            courseDao.deleteCourse("java-primeiros-passos");
            courseDao.transformCourseToPublic();
        }


    }
}
