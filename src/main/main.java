package main;

import htmlWriter.Writer;
import tecnodev.category.Category;
import tecnodev.category.ReadCategory;
import tecnodev.course.Course;
import tecnodev.course.ReadCourse;
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

        System.out.println("\nCategories : ");
        categories.forEach(System.out::println);

        System.out.println("\nSubCategories : ");
        subCategories.forEach(System.out::println);

        System.out.println("\nCourses : ");
        courses.forEach(System.out::println);

        new Writer().htmlWriter(categories, subCategories, courses);

    }
}