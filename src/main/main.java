package main;

import tecnodev.category.Category;
import tecnodev.category.ReadCategory;
import tecnodev.course.Course;
import tecnodev.course.ReadCourse;
import tecnodev.subCategory.ReadSubCategory;
import tecnodev.subCategory.SubCategory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static utility.Filters.*;

public class main {
    public static void main(String[] args) throws IOException {

        List<Category> categories = new ArrayList<>(new ReadCategory().CategoryReader("/home/arthur/Alura/planilha-dados-escola - Categoria.csv"));
        List<SubCategory> subCategories = new ArrayList<>(new ReadSubCategory().SubCategoryReader("/home/arthur/Alura/planilha-dados-escola - Subcategoria.csv", categories));
        List<Course> courses = new ArrayList<>(new ReadCourse().CourseReader("/home/arthur/Alura/planilha-dados-escola - Curso.csv", subCategories));

        System.out.println("Categorias ativas: ");
        activeCategory(categories).forEach(s -> System.out.println(s));

        System.out.println("\nSubCategorias que não possuem descrição: ");
        subcategoryWithoutDescription(subCategories).forEach(s -> System.out.println(s));

        System.out.println("\nSe há algum curso privado");
        privateCourses(courses);

        System.out.println("\nInstrutores: ");
        instructors(courses).forEach(System.out::println);

        System.out.println("\nQuantidade de Subcategoria ativa que tem descrição");
        System.out.println(subcategoryActiveWithDescription(subCategories) + "\n");

        for (String instructor : instructors(courses)) {
            System.out.println("Instrutor: " + instructor + " tem " + numbersOfCourseFromInstructors(courses, instructor) + " Curso(s)");
        }
    }
}