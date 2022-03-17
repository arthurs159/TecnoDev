package jdbc.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    private static DataSource dataSource;

    private ConnectionFactory() {}

    public static Connection recoveryConnection() throws SQLException {
        if(dataSource == null){
            connectPool();
        }

        return dataSource.getConnection();
    }

    private static void connectPool(){
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/TecnoDev?useTimezone=true&serverTimezone=UTC");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("");

        dataSource = comboPooledDataSource;
    }

}
