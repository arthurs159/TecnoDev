package jdbc.dao;

import jdbc.dto.CourseDto;
import jdbc.exception.ExceptionSql;
import tecnodev.category.Category;
import tecnodev.course.Course;
import tecnodev.subCategory.SubCategory;

import javax.persistence.EntityManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDao {

    private static Connection connection;

    private EntityManager em;

    public CourseDao(EntityManager em){
        this.em = em;
    }

//    public CourseDao() throws SQLException {
//        connection = recoveryConnection();
//    } //JDBC

//    public void insertCourse(Course course) throws SQLException {
//        String sql = ("INSERT INTO Course " +
//                "(name, code, estimated_time_in_hours," +
//                "teacher, subcategory_id) " +
//                "VALUES (?, ?, ?, ?, ?)");
//
//        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//            connection.setAutoCommit(false);
//            pstm.setString(1, course.getName());
//            pstm.setString(2, course.getCode());
//            pstm.setInt(3, course.getEstimatedTimeInHours());
//            pstm.setString(4, course.getTeacher());
//            pstm.setLong(5, getSubCategoryId(course));
//
//            pstm.execute();
//
//            try (ResultSet rst = pstm.getGeneratedKeys()) {
//                rst.next();
//                System.out.println(rst.getLong(1));
//            } catch (SQLException e) {
//                throw new ExceptionSql("Error, it was not possible to INSERT a course");
//            }
//            connection.commit();
//        } catch (SQLException e) {
//            connection.rollback();
//            throw new ExceptionSql("Error, it was not possible to INSERT a course");
//        }
//    } //JDBC

    public void insertCourseJPA(Course course){
        this.em.persist(course);
        System.out.println("Generated id: " + course.getId());
    }

    public void deleteCourseJPA(String code){
        String jpql = "DELETE FROM Course c WHERE c.code = :code";

        em.createQuery(jpql)
                .setParameter("code", code)
                        .executeUpdate();

        System.out.println("O curso de código: ( " + code + " ) foi deletado com sucesso!!" );
    }

    public void makeAllCoursesPublicJPA(){
        String jpql = "UPDATE FROM Course c SET c.visibility = 'PUBLIC' WHERE c.visibility = 'PRIVATE'";

        em.createQuery(jpql)
                .executeUpdate();

        System.out.println("Todos os cursos estão publicos!!");
    }

    public List<Course> listAllPublicCourses(){
        String jpql = "SELECT c FROM Course c WHERE c.visibility = 1";

        return em.createQuery(jpql, Course.class)
                .getResultList();
    }


    public void deleteCourse(String code) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement("DELETE FROM Course WHERE CODE = ?")) {
            connection.setAutoCommit(false);
            pst.setString(1, code);
            pst.execute();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new ExceptionSql("Error, it was not possible to DELETE a course");
        }

        System.out.println("Curso do banco de código: ( " + code + " ) deletado");
    } //JDBC

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
            throw new ExceptionSql("Error, it was not possible to SET TO ALL TO PUBLIC");
        }
    } //JDBC

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
                throw new ExceptionSql("Error, it was not possible to GET ID from Subcategory");
            }

        } catch (SQLException e) {
            connection.rollback();
            throw new ExceptionSql("Error, it was not possible to GET ID from Subcategory");
        }
        return subCategoryId;
    } //JDBC

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
            throw new ExceptionSql("Error, it was not possible to Get All Public courses");
        }
        return listCourse;
    } //JDBC

    public Category getCategoryFromDatabase(String code) throws SQLException {
        String sql = "SELECT * FROM Category WHERE `code` = ?";

        Category category = null;
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            pstm.setString(1, code);
            pstm.execute();

            try (ResultSet rs = pstm.getResultSet()) {
                while (rs.next()) {
                    String name = rs.getString(2);
                    String cod = rs.getString(3);

                    category = new Category(name, cod);
                    connection.commit();
                }
            } catch (SQLException e) {
                throw new ExceptionSql("Error, it was not possible to GET category");
            }
        }catch (SQLException e){
            connection.rollback();
            throw new ExceptionSql("Error, it was not possible to GET category");
        }

        return category;
    } //JDBC

    public SubCategory getSubCategoryFromDatabase(String code, Category category) throws SQLException {
        String sql = "SELECT * FROM Subcategory WHERE `code` = ?";
        connection.setAutoCommit(false);
        SubCategory subCategory = null;
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, code);
            pstm.execute();

            try (ResultSet rs = pstm.getResultSet()) {
                while (rs.next()) {
                    String name = rs.getString(2);
                    String cod = rs.getString(3);
                    subCategory = new SubCategory(name, cod, category);
                    connection.commit();
                }
            } catch (SQLException e) {
                throw new ExceptionSql("Error, it was not possible to GET a Subcategory");
            }
        }catch (SQLException e){
            connection.rollback();
            throw new ExceptionSql("Error, it was not possible to GET a Subcategory");
        }
        return subCategory;
    } //JDBC
}

