package jdbc.exception;

import java.sql.SQLException;

public class ExceptionSql extends SQLException {

    public ExceptionSql(String message) {
        super(message);
    }
}
