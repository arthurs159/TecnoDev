package jdbc.dao;

import jdbc.connection.ConnectionFactory;
import tecnodev.category.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    private Connection connection;

    public CategoryDao() throws SQLException {
        connection = new ConnectionFactory().recuperarConexao();
    }

//    public void salvar() {
//        String sql = "INSERT INTO Category"
//    }

    public List<Category> categoryList() throws SQLException {
        List<Category> list = new ArrayList<>();
//        String sql = "SELECT * FROM Course WHERE visibility = 'PUBLIC'";
//
//        PreparedStatement statement = connection.prepareStatement(sql);
//        ResultSet result = statement.executeQuery();
//
//        while(result.next()) {
//
//        }
        return list;
    }
}
