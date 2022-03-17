package jdbc.dao;

import jdbc.connection.ConnectionFactory;
import jdbc.dto.CourseDto;
import tecnodev.category.Category;
import tecnodev.course.Course;
import tecnodev.subCategory.SubCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDao {

    private final Connection connection;

    public CourseDao() throws SQLException {
        this.connection = new ConnectionFactory().recoveryConnection();
    }

    public void insertCourse(Course course) throws SQLException {
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
            connection.rollback();
            throw new RuntimeException(e.getCause());
        }
    }

    public void deleteCourse(String code) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement("DELETE FROM Course WHERE CODE = ?")) {
            connection.setAutoCommit(false);
            pst.setString(1, code);
            pst.execute();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e.getCause());
        }

        System.out.println("Curso do banco de código: ( " + code + " ) deletado");
    }

    public void transformCourseToPublic() throws SQLException {
        String sql = "UPDATE Course SET visibility = 'PUBLIC' WHERE visibility = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            pstm.setString(1, "PRIVATE");
            pstm.executeUpdate();
            int modifiedLines = pstm.getUpdateCount();
            connection.commit();
            System.out.println("Cursos que foram modificados " + modifiedLines);
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e.getCause());
        }
    }

    public Long getSubCategoryId(Course course) throws SQLException {
        String sql = "SELECT `id` FROM Subcategory WHERE `code` = ?";
        Long subCategoryId = null;

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            pstm.setString(1, course.getSubCategoryCode());
            pstm.execute();
            connection.commit();
            try (ResultSet rs = pstm.getResultSet()) {
                while (rs.next()) {
                    subCategoryId = rs.getLong(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e.getCause());
        }
        return subCategoryId;
    }

    public List<CourseDto> getPublicCourses() throws SQLException {
        String sql = """
                SELECT c.id, c.name, c.estimated_time_in_hours, c.subcategory_id, s.name
                FROM Course c
                INNER JOIN Subcategory s
                ON c.subcategory_id = s.id
                WHERE visibility = ?;""";

        List<CourseDto> listCourse = new ArrayList<>();

        connection.setAutoCommit(false);
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, "PUBLIC");
        pstm.execute();

        try (ResultSet rs = pstm.getResultSet()) {
            while (rs.next()) {
                Integer id = rs.getInt(1);
                String nome = rs.getString(2);
                Integer estimatedTime = rs.getInt(3);
                Integer subCategoryId = rs.getInt(4);
                String subCategoryName = rs.getString(5);

                CourseDto curso = new CourseDto(id, nome, estimatedTime, subCategoryId, subCategoryName);
                listCourse.add(curso);
            }
            connection.commit();

        }catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e.getCause());
        }
        return listCourse;
    }

    public Category getCategoryFromDatabase(String code) throws SQLException {
        String sql = "SELECT * FROM Category WHERE `code` = ?";

        Category category = null;
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, code);
            pstm.execute();

            try (ResultSet rs = pstm.getResultSet()) {
                while (rs.next()) {
                    String name = rs.getString(2);
                    String cod = rs.getString(3);

                    category = new Category(name, cod);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e.getCause());
            }
        }

        return category;
    }

    public SubCategory getSubCategoryFromDatabase(String code, Category category) throws SQLException {
        String sql = "SELECT * FROM Subcategory WHERE `code` = ?";

        SubCategory subCategory = null;
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, code);
            pstm.execute();

            try (ResultSet rs = pstm.getResultSet()) {
                while (rs.next()) {
                    String name = rs.getString(2);
                    String cod = rs.getString(3);
                    subCategory = new SubCategory(name, cod, category);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return subCategory;
    }
}

