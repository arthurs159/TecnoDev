package writer;

import tecnodev.category.Category;
import tecnodev.course.Course;
import tecnodev.subCategory.SubCategory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SqlWriter {

    public static void script(List<Category> categories, List<SubCategory> subCategories, List<Course> courses) throws IOException {
        File file = new File("/home/arthur/WorkSpace/Projetos Java/TecnoDev/src/main/resources/sqlScript/insertClasses.sql");

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter writeFile = new FileWriter(file);

        String sqlCategory = "";
        for (Category category : categories) {
            sqlCategory += String.format("""
                    INSERT INTO Category
                    (name, code, order_in_system, description, active, image_url, color_code)
                    VALUES 
                    ('%s', '%s', %d, '%s', %s, '%s', '%s');\n
                    """.formatted(category.getName(), category.getCode(), category.getOrderInSystem(), category.getDescription(), category.isActive(), category.getImageUrl(), category.getColorCode()));
        }

        String sqlSubCategory = "";
        for (SubCategory subCategory : subCategories) {
            sqlSubCategory += String.format("""
                    INSERT INTO Subcategory
                    (name, code, order_in_system, description, active, category_id)
                    VALUES
                    ('%s', '%s', %d, '%s', %s, (SELECT id from Category WHERE code = '%s'));\n
                    """.formatted(subCategory.getName(), subCategory.getCode(), subCategory.getOrderInSystem(), subCategory.getDescription(), subCategory.isActive(), subCategory.getCategoryCode()));
        }

        String sqlCourse = "";
        for (Course course : courses) {
            sqlCourse += """
                    INSERT INTO Course
                    (name, code, estimated_time_in_hours, visibility, target_audience, teacher, description, developed_skills, subcategory_id)
                    VALUES
                    ("%s", "%s", "%d", "%s", "%s", "%s", "%s", "%s", (SELECT id from Subcategory WHERE code = "%s"));\n
                    """.formatted(course.getName(), course.getCode(), course.getEstimatedTimeInHours(), course.getVisibility(),
                    course.getTargetAudience(), course.getTeacher(), course.getDescription(), course.getDevelopedSkills(), course.getSubCategoryCode());
        }

        writeFile.write(sqlCategory);
        writeFile.write(sqlSubCategory);
        writeFile.write(sqlCourse);

        writeFile.flush();
        writeFile.close();
        System.out.println("Arquivo escrito");
    }
}