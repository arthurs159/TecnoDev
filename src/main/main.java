package main;

import tecnodev.category.Category;
import tecnodev.category.ReadCategory;
import tecnodev.course.Course;
import tecnodev.course.ReadCourse;
import tecnodev.course.Status;
import tecnodev.subCategory.ReadSubCategory;
import tecnodev.subCategory.SubCategory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static tecnodev.category.Category.*;
import static tecnodev.category.ReadCategory.activeCategory;
import static tecnodev.course.Course.*;
import static tecnodev.course.ReadCourse.*;
import static tecnodev.subCategory.ReadSubCategory.*;
import static tecnodev.subCategory.SubCategory.*;

public class main {
    public static void main(String[] args) throws IOException {

        List<Category> categories = new ArrayList<>(new ReadCategory().CategoryReader("/home/arthur/Alura/planilha-dados-escola - Categoria.csv"));
        List<SubCategory> subCategories = new ArrayList<>(new ReadSubCategory().SubCategoryReader("/home/arthur/Alura/planilha-dados-escola - Subcategoria.csv", categories));
        List<Course> courses = new ArrayList<>(new ReadCourse().CourseReader("/home/arthur/Alura/planilha-dados-escola - Curso.csv", subCategories));

        System.out.println("Categorias ativas: ");
        activeCategory(categories);

        System.out.println("\nSubCategorias que não possuem descrição: ");
        subcategoryWithoutDescription(subCategories);

        System.out.println("\nSe há algum curso privado");
        privateCourses(courses);

        System.out.println("\nInstrutores: ");
        instructors(courses);

        System.out.println("\nSubcategoria ativa que tem descrição");
        subcategoryActiveWithDescription(subCategories);

        System.out.println("\nInstrutor e a quantidade de cursos");
        for (Course course : courses){
                    System.out.println("Instrutor: " + course.getTeacher() + " tem " + numbers(courses, course.getTeacher()) + " Cursos");
            }

//        System.out.println("\nCategories : ");
//        categories.forEach(System.out::println);
//
//        System.out.println("\nSubCategories : ");
//        subCategories.forEach(System.out::println);
//
//        System.out.println("\nCourses : ");
//        courses.forEach(System.out::println);

//        new Writer().htmlWriter(categories, subCategories, courses);

    }
}