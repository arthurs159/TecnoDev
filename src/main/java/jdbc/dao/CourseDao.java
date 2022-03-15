package jdbc.dao;

import jdbc.connection.ConnectionFactory;
import tecnodev.course.Course;

import java.sql.*;

public class CourseDao {

    private Connection connection;

    public CourseDao(Connection connection) throws SQLException {
        this.connection = new ConnectionFactory().recuperarConexao();
    }

    public void insertCourse() throws SQLException {

        String name = "Curso SQL";
        String code = "curso-sql";
        int estimatedTime = 10;
        String teacher = "Professor";
        Long subCategoryId = 1L;

        String sql = ("INSERT INTO Course " +
                "(name, code, estimated_time_in_hours," +
                "teacher, subcategory_id) " +
                "VALUES (?, ?, ?, ?, ?)");

        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstm.setString(1, name);
            pstm.setString(2, code);
            pstm.setInt(3, estimatedTime);
            pstm.setString(4, teacher);
            pstm.setLong(5, subCategoryId);

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
            PreparedStatement pst = connection.prepareStatement("DELETE FROM Course WHERE CODE = ?");
            pst.setString(1, code);
            pst.execute();
            System.out.println("Objeto do banco de código: ( " + code + " ) deletado");
        }
    }

    public void transformCourseToPublic() throws SQLException {
        String sql = "UPDATE Course SET visibility = 'PUBLIC' WHERE visibility = 'PRIVATE'";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();
            Integer modifiedLines = pstm.getUpdateCount();

            System.out.println("Cursos que foram modificados " + modifiedLines);
        }
    }

    public Long getSubCategoryId(Course course) throws SQLException {
        Long subCategoryId = null;
        String sql = "SELECT id from Subcategory where code = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, course.getSubCategoryCode());
            pstm.execute();

            try (ResultSet rs = pstm.getResultSet()) {
                while (rs.next()) {
                    subCategoryId = rs.getLong(1);
                }
            }
        }
        return subCategoryId;
    }


}

//    public void List<Course> transformToPublicAndListCourse() throws SQLException{
//

//    public List<Course> courseList() throws SQLException {
//        List<Course> list = new ArrayList<>();
//        String sql = "SELECT id, name, code, estimated_time_in_hours, teacher, subcategory_id FROM Course WHERE visibility = 'PUBLIC'";
//
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.execute();
//        ResultSet result = statement.getResultSet();
//
//        while(result.next()) {
//            Course course = new Course(
//                    result.getLong("id"),
//                    result.getString("name"),
//                    result.getString("code"),
//                    result.getInt("estimated_time_in_hours"),
//                    result.getString("teacher"),
//                    result.getLong("subcategory_id"));
//
//            courseList().add(course);
//        }
//        return list;
//    }

