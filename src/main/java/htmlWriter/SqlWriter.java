package htmlWriter;

import tecnodev.category.Category;
import tecnodev.course.Course;
import tecnodev.subCategory.SubCategory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SqlWriter {

    public static void script(List<Category> categories, List<SubCategory> subCategories, List<Course> courses) throws IOException {
        File file = new File("/home/arthur/Alura/script.sql");

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter writeFile = new FileWriter(file);

        StringBuilder sqlScript = new StringBuilder();

        for (Category category: categories) {
            sqlScript.append("INSERT INTO Category ")
                    .append("(name, code, order_in_system, description, active, img_url, color_code) ")
                    .append("VALUES (" + category.getName() + " ," + category.getCode() + " ," + category.getDescription() + ", ")
                    .append(category.isActive() + ", " + category.getImageUrl() + ", " + category.getColorCode() + ")\n\n");
        }

        for (SubCategory subCategory: subCategories) {
            sqlScript.append("INSERT INTO Subcategory\n")
                    .append("(name, code, order_in_system, description, active, category_id)\n")
                    .append("VALUES (" + subCategory.getName() + ", " + subCategory.getCode() + ", ")
                    .append(subCategory.getOrderInSystem() + ", " + subCategory.getDescription() + ", ")
                    .append(subCategory.isActive());
        }

        for (Course course: courses) {
            sqlScript.append("INSERT INTO Subcategory\n")
                    .append("(name, code, estimated_time_in_hours, visibility, targetAudience, teacher, description, developed_skills, subcategory_id \n")
                    .append("VALUES (" + course.getName() + ", " + course.getCode() + ", ")
                    .append(course.getEstimatedTimeInHours() + ", " + course.getVisibility() + ", ")
                    .append(course.getTargetAudience() + ", " + course.getTeacher() + ", " + course.getDescription() + ", ")
                    .append(course.getDevelopedSkills());
        }
        writeFile.write(String.valueOf(sqlScript));

        writeFile.flush();
        writeFile.close();
        System.out.println("Arquivo escrito");
    }
}
