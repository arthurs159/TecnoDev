package jdbc.dao;

import jdbc.connection.ConnectionFactory;
import tecnodev.course.Course;

import java.sql.*;

public class CourseDao {

    private Connection connection;

    public CourseDao(Connection connection) throws SQLException {
        this.connection = new ConnectionFactory().recuperarConexao();
    }

    public void insertCourse(Course course) throws SQLException {
        String sql = ("INSERT INTO Course " +
                "(name, code, estimated_time_in_hours," +
                "teacher, subcategory_id) " +
                "VALUES (?, ?, ?, ?, ?)");

        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstm.setString(1, course.getName());
            pstm.setString(2, course.getCode());
            pstm.setInt(3, course.getEstimatedTimeInHours());
            pstm.setString(4, course.getTeacher());
            pstm.setLong(5, getSubCategoryId(course));

            pstm.execute();

            try (ResultSet rst = pstm.getGeneratedKeys()) {
                rst.next();
                System.out.println(rst.getLong(1));
            }

        }
    }

    public void deleteCourse(String code) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.recuperarConexao()) {
            connection.setAutoCommit(false);

            try(PreparedStatement pst = connection.prepareStatement("DELETE FROM Course WHERE CODE = ?")){
                pst.setString(1, code);
                pst.execute();
                connection.commit();
            };

            System.out.println("Objeto do banco de código: ( " + code + " ) deletado");
        }
    }

    public void transformCourseToPublic() throws SQLException {
        String sql = "UPDATE Course SET visibility = 'PUBLIC' WHERE visibility = 'PRIVATE'";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.executeUpdate();
            Integer modifiedLines = pstm.getUpdateCount();

            System.out.println("Cursos que foram modificados " + modifiedLines);
        }
    }

    public Long getSubCategoryId(Course course) throws SQLException {
        String sql = "SELECT `id` FROM Subcategory WHERE `code` = ?";
        Long subCategoryId = null;

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, course.getSubCategoryCode());
            pstm.execute();

            try (ResultSet rs = pstm.getResultSet()) {
                while (rs.next()) {
                    subCategoryId = rs.getLong(1);
                }
            }
            return subCategoryId;
        }
    }

}

