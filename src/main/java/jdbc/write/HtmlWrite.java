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

        File file = new File("/home/arthur/WorkSpace/Projetos Java/TecnoDev/src/main/resources/htmlSQL.html");

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter writeFile = new FileWriter(file);
        StringBuilder html = new StringBuilder("""
                <!DOCTYPE html>
                <html>
                    <style> table, th, td {border:1px solid black;} </style>
                    <head>
                        <meta charset= UTF-8 />
                        <title>Cursos</title>
                    </head>
                    <body>
                        <h1>Cursos</h1>
                    <table style="width:100%">
                    <tr>
                        <th>ID</th>
                        <th>NOME</th>
                        <th>TEMPO DO CURSO</th>
                        <th>ID SUBCATEGORIA</th>
                        <th>NOME SUBCATEGORIA</th>
                    </tr>
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

            try (ResultSet rs = pstm.getResultSet()) {
                while (rs.next()) {
                    Integer id = rs.getInt(1);
                    String nome = rs.getString(2);
                    int estimatedTime = rs.getInt(3);
                    Long subCategoryId = rs.getLong(4);
                    String subCategoryName = rs.getString(5);

                    html.append("""
                            <tr>
                                <td> %d </td>
                                <td> %s </td>
                                <td> %d Horas</td>
                                <td> %d </td>
                                <td> %s </td>
                            </tr>
                            """.formatted(id, nome, estimatedTime, subCategoryId, subCategoryName));
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            html.append("""
                    
                    </table>
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
