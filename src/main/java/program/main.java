package program;

import tecnodev.course.ReadCourse;
import writer.SqlWriter;
import tecnodev.category.Category;
import tecnodev.category.ReadCategory;
import tecnodev.course.Course;
//import tecnodev.course.ReadCourse;
import tecnodev.subCategory.ReadSubCategory;
import tecnodev.subCategory.SubCategory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) throws IOException {
        List<Category> categories = new ArrayList<>(new ReadCategory().CategoryReader("/home/arthur/Alura/planilha-dados-escola - Categoria.csv"));
        List<SubCategory> subCategories = new ArrayList<>(new ReadSubCategory().SubCategoryReader("/home/arthur/Alura/planilha-dados-escola - Subcategoria.csv", categories));
        List<Course> courses = new ArrayList<>(new ReadCourse().CourseReader("/home/arthur/Alura/planilha-dados-escola - Curso.csv", subCategories));

        SqlWriter.script(categories, subCategories, courses);
    }
}