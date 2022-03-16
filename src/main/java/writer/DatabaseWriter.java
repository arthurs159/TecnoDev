package writer;

import jdbc.dao.CourseDao;
import jdbc.dto.CourseDto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DatabaseWriter {

    public static void main(String[] args) throws IOException, SQLException {

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

        CourseDao dao = new CourseDao();
        List<CourseDto> courseDtos = dao.getPublicCourses();

        for (CourseDto course : courseDtos) {
            html.append("""
                    <tr>
                        <td> %d </td>
                        <td> %s </td>
                        <td> %d Horas</td>
                        <td> %d </td>
                        <td> %s </td>
                    </tr>
                    """.formatted(course.getId(), course.getName(), course.getEstimatedTimeinHours(),
                    course.getSubCategoryId(), course.getSubCategoryName()));
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

