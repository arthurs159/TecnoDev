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

        Category categoria = new Category("Programação", "programacao");
        SubCategory subcategoria = new SubCategory("Java", "java", categoria);
        Course curso = new Course("Javacript primeiro pa", "j-p-pas", 7, "professor", subcategoria);

        try (Connection connection = new ConnectionFactory().recuperarConexao()) {
            CourseDao courseDao = new CourseDao(connection);
//            courseDao.insertCourse();
//            courseDao.deleteCourse("java-primeiros-passos");
//            courseDao.transformCourseToPublic();
//            courseDao.insertCourse2(curso);
        }


    }
}
