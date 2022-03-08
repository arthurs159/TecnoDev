package htmlWriter;

import tecnodev.category.Category;
import tecnodev.course.Course;
import tecnodev.subCategory.SubCategory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class Writer {

    public static void htmlWriter(List<Category> categories, List<SubCategory> subCategories, List<Course> courses) throws IOException {
        subCategories.sort(Comparator.comparing(SubCategory::getOrderInSystem));

        File file = new File("/home/arthur/Alura/htmlPage.html");

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter writeFile = new FileWriter(file);

        StringBuilder openHtml = new StringBuilder();
        openHtml.append("<html>\n")
                .append("<head> <meta charset= UTF-8 /> ")
                .append("<style> div.solid {border-style: solid;}\n ")
                .append("ul { list-style-type:none} </style>")
                .append("</head>\n")
                .append("<body>\n")
                .append("<h1 style=text-align:center>Bem-vindo à Tecnodev &#128513;</h1>\n");

        writeFile.write(String.valueOf(openHtml));

        StringBuilder categoryBuilder = new StringBuilder();
        for (Category category : categories) {
            categoryBuilder.append("<div class=solid><h2 style=text-align:center;background-color:" + category.getColorCode() + ">" + category.getName() + "</h2>\n")
                    .append("<p style=text-align:center>" + category.getDescription() + "</p>\n")
                    .append("<ul>\n")
                    .append("<li style=display:flex;align-items:center> Icone: <img src=" + category.getImageUrl() + " style=width:100px></li>\n ")
                    .append("<li style=background-color:" + category.getColorCode() + ">Código Hexadecimal : " + category.getColorCode() + "</li>\n")
                    .append("<li> Quantidade de cursos: " + Category.numbersOfCourseFromCategory(courses, category.getCode()) + "</li>\n")
                    .append("<li> Quantidade de horas: " + Category.quantityHoursFromCategory(courses, category.getCode()) + "</li>\n")
                    .append("</ul>\n")
                    .append("<h3 style=padding-left:5px> Subcategoria(s) : </h3>\n");
            for (SubCategory subCategory : subCategories) {
                String categoryCode = subCategory.getCategoryCode();
                if (categoryCode.equalsIgnoreCase(category.getCode())) {
                    if (subCategory.isActive()) {
                        categoryBuilder.append("<h3 style=font-size:25px;padding-left:10px>" + subCategory.getName() + "</h3>\n")
                                .append("<p style=padding-left:10px> Descrição : " + subCategory.getDescription() + "</p>\n");
                    }
                    for (Course course : courses) {
                        String subCategoryCode = course.getSubCategoryCode();
                        if (subCategoryCode.equalsIgnoreCase(subCategory.getCode())) {
                            categoryBuilder.append("<p style=padding-left:10px>Cursos: " + course.getName() + "</p>\n");
                        }
                    }
                }
            }
            categoryBuilder.append("</div>");
        }

        StringBuilder closeHtml = new StringBuilder();
        closeHtml.append("</body>")
                .append("</html>");
        writeFile.write(String.valueOf(categoryBuilder));
        writeFile.write(String.valueOf(closeHtml));

        writeFile.flush();
        writeFile.close();
    }
}
