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

import static tecnodev.category.ReadCategory.activeCategory;
import static tecnodev.course.ReadCourse.*;
import static tecnodev.subCategory.ReadSubCategory.*;

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

        System.out.println("\nQuantidade de Subcategoria ativa que tem descrição");
        System.out.println(subcategoryActiveWithDescription(subCategories));

        System.out.println("\nInstrutor e a quantidade de cursos");
//        for (Course course : courses){
//                    System.out.println("Instrutor: " + course.getTeacher() + " tem " + numbersOfCourseFromTeacher(courses, course.getTeacher()) + " Cursos");
//        }
        instructorsWithNumbersOfCourses(courses);
    }
}