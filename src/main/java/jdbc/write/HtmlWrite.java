package jdbc.write;

import jdbc.connection.ConnectionFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HtmlWrite {

    public static void main(String[] args) throws SQLException, IOException {

        File file = new File("/home/arthur/WorkSpace/Projetos Java/TecnoDev/src/main/resources/sqlScript/htmlSQL.html");

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter writeFile = new FileWriter(file);
        StringBuilder html = new StringBuilder("""
                <!DOCTYPE html>
                <html>
                <head>
                <meta charset= UTF-8 />
                <title>Cursos</title>
                </head>
                <body>
                <h1>Cursos</h1>
                """);

        String sql = """
                SELECT c.id, c.name, c.estimated_time_in_hours, c.subcategory_id, s.name
                FROM Course c
                INNER JOIN Subcategory s
                ON c.subcategory_id = s.id
                WHERE visibility = ?;""";

        ConnectionFactory connectionFactory = new ConnectionFactory();

        try (Connection connection = connectionFactory.recuperarConexao()) {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, "PUBLIC");
            pstm.execute();

            try(ResultSet rs = pstm.getResultSet()){
                while (rs.next()) {
                    Integer id = rs.getInt(1);
                    String nome = rs.getString(2);
                    int estimatedTime = rs.getInt(3);
                    Long subCategoryId = rs.getLong(4);
                    String subCategoryName = rs.getString(5);

                    html.append("""
                            <p>ID : %d</p>
                            <p>Nome: %s </p>
                            <p>Tempo Estimado: %d Horas <p/>
                            <p> ID da Subcategoria: %d | Subcategoria : %s</p>
                            <p> --------------------------------------------- </p>
                            """.formatted(id, nome, estimatedTime, subCategoryId, subCategoryName));
                }
            }

            html.append("""
                    </body>
                    </html>
                    """);

            writeFile.write(html.toString());
            writeFile.flush();
            writeFile.close();
            System.out.println("Arquivo criado!!");
        }
    }
}
