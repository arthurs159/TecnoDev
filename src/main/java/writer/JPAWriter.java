package writer;


import jpa.dao.CategoryDao;
import jpa.dao.CourseDao;
import jpa.dao.SubcategoryDao;
import jpa.util.JPAUtil;
import tecnodev.category.Category;
import tecnodev.course.Course;
import tecnodev.subCategory.SubCategory;

import javax.persistence.EntityManager;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JPAWriter {

    public static void main(String[] args) throws IOException {
        File file = new File("/home/arthur/WorkSpace/Projetos Java/TecnoDev/src/main/resources/JPAHtml.html");

        EntityManager em = JPAUtil.getEntityManager();

        FileWriter fileWriter = new FileWriter(file);

        StringBuilder html = new StringBuilder("""
                <!DOCTYPE html>
                <html>
                    <style>
                    table, th, td {
                    border:1px solid black;
                    border-collapse: collapse;
                    }
                    </style>
                    <head>
                        <meta charset= UTF-8 />
                        <title>Cursos</title>
                    </head>
                    <body>
                        <h1>RELATÓRIO TECNODEV <br></h1>
                        <h2>Categorias Ativas</h2>
                    <table style="width:100%">
                    <tr>
                        <th>ID</th>
                        <th>NOME</th>
                        <th>CÓDIGO</th>
                        <th>DESCRIÇÃO</th>
                        <th>ATIVO</th>
                        <th>ORDEM</th>
                        <th>URL DA IMAGEM</th>
                        <th>CÓDIGO DA COR</th>
                    </tr>
                """);

        CategoryDao categoryDao = new CategoryDao(em);
        List<Category> categoryList = categoryDao.listAllActive();

        for (Category category : categoryList) {
            html.append("""
                    <tr>
                        <td> %d </td>
                        <td> %s </td>
                        <td> %s </td>
                        <td> %s </td>
                        <td> %s </td>
                        <td> %d </td>
                        <td> <img src="%s" alt="icone" width="150px"> </td>
                        <td style="background-color: %s"></td>
                    </tr>
                    """.formatted(category.getId(), category.getName(), category.getCode(), category.getDescription(),
                    category.isActive(), category.getOrderInSystem(), category.getImageUrl(), category.getColorCode()));
        }

        html.append("""
                </table>
                                
                <h2>Subcategorias Ativas</h2>
                                
                <table style="width:100%">
                <tr>
                        <th>ID</th>
                        <th>NOME</th>
                        <th>CÓDIGO</th>
                        <th>DESCRIÇÃO</th>
                        <th>ATIVO</th>
                        <th>ORDEM</th>
                        <th>ID CATEGORIA</th>
                </tr>
                """);

        SubcategoryDao subcategoryDao = new SubcategoryDao(em);
        List<SubCategory> subCategoryList = subcategoryDao.listAllActive();

        for (SubCategory subCategory : subCategoryList) {
            html.append("""
                    <tr>
                        <td> %d </td>
                        <td> %s </td>
                        <td> %s </td>
                        <td> %s </td>
                        <td> %s </td>
                        <td> %d </td>
                        <td> %d </td>
                    </tr>
                    """.formatted(subCategory.getId() ,subCategory.getName(), subCategory.getCode(), subCategory.getDescription(),
                    subCategory.isActive(), subCategory.getOrderInSystem(), subCategory.getCategory().getId()));
        }

        html.append("""
                </table>
                                
                <h2>Cursos Públicos</h2>
                                
                <table style="width:100%">
                <tr>
                        <th>ID</th>
                        <th>NOME</th>
                        <th>CÓDIGO</th>
                        <th>TEMPO DE CURSO</th>
                        <th>VISIBILIDADE</th>
                        <th>PÚBLICO ALVO</th>
                        <th>INSTRUTOR</th>
                        <th>DESCRIÇÃO</th>
                        <th>HABILIDADES DESENVOLVIDAS</th>
                        <th>ID DA SUBCATEGORIA</th>
                </tr>
                """);

        CourseDao courseDao = new CourseDao(em);
        List<Course> courseList = courseDao.listAllPublicCourses();

        for (Course course : courseList) {
            html.append("""
                    <tr>
                        <td> %d </td>
                        <td> %s </td>
                        <td> %s </td>
                        <td> %d Horas</td>
                        <td> %s </td>
                        <td> %s </td>
                        <td> %s </td>
                        <td> %s </td>
                        <td> %s </td>
                        <td style=text-align:center;> %d </td>

                    </tr>
                    """.formatted(course.getId(), course.getName(), course.getCode(), course.getEstimatedTimeInHours(),
                    course.getVisibility(), course.getTargetAudience(), course.getTeacher(), course.getDescription(),
                    course.getDevelopedSkills(), course.getSubCategory().getId()));
        }

        List<String> names = subcategoryDao.listAllSubcategoryNameWithoutDescription();

        html.append("""
                </table>
                                
                <h2>Nomes das Subcategorias sem descrição</h2>
                                
                <table style="width:50%">
                <tr>
                        <th>NOME</th>
                </tr>
                """);

        for (String string:names) {
            html.append("""
                    <tr>
                        <td> %s </td>
                    </tr>
                    """.formatted(string));
        }

        html.append("""
                </table>
                </body>
                </html>
                """);

        fileWriter.write(html.toString());
        fileWriter.flush();
        fileWriter.close();
        System.out.println("Arquivo criado!!");
    }
}



