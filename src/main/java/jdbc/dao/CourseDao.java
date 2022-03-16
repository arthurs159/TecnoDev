package jdbc.dao;

import jdbc.connection.ConnectionFactory;
import tecnodev.course.Course;

import java.sql.*;

public class CourseDao {

    private Connection connection;

    public CourseDao(Connection connection) throws SQLException {
        this.connection = new ConnectionFactory().recuperarConexao();
    }

    public void insertCourse(Course course) {
        String sql = ("INSERT INTO Course " +
                "(name, code, estimated_time_in_hours," +
                "teacher, subcategory_id) " +
                "VALUES (?, ?, ?, ?, ?)");

        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            pstm.setString(1, course.getName());
            pstm.setString(2, course.getCode());
            pstm.setInt(3, course.getEstimatedTimeInHours());
            pstm.setString(4, course.getTeacher());
            pstm.setLong(5, getSubCategoryId(course));

            pstm.execute();

            try (ResultSet rst = pstm.getGeneratedKeys()) {
                rst.next();
                System.out.println(rst.getLong(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCourse(String code) {
        try (PreparedStatement pst = connection.prepareStatement("DELETE FROM Course WHERE CODE = ?")) {
            connection.setAutoCommit(false);
            pst.setString(1, code);
            pst.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Curso do banco de código: ( " + code + " ) deletado");
    }

    public void transformCourseToPublic() {
        String sql = "UPDATE Course SET visibility = 'PUBLIC' WHERE visibility = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            pstm.setString(1, "PRIVATE");
            pstm.executeUpdate();
            Integer modifiedLines = pstm.getUpdateCount();
            connection.commit();
            System.out.println("Cursos que foram modificados " + modifiedLines);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Long getSubCategoryId(Course course){
        String sql = "SELECT `id` FROM Subcategory WHERE `code` = ?";
        Long subCategoryId = null;

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, course.getSubCategoryCode());
            pstm.execute();

            try (ResultSet rs = pstm.getResultSet()) {
                while (rs.next()) {
                    subCategoryId = rs.getLong(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subCategoryId;
    }

}

