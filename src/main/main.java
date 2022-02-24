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

public class main {
    public static void main(String[] args) throws IOException {

//        Category categoria = new Category("teste", "1-teste");
//        SubCategory subcategoria = new SubCategory("teste", "1-2", categoria);
//        Course curso = new Course("Java", "codigo-", 7, "professor", subcategoria);
//        Section secao = new Section("Seção", "1", curso);
//        Question question = new Question("teste", "1", secao, "teste", MULTIPLE_CHOICE);
//        Explication explicacao = new Explication("titulo", "1", secao, "teste");
//        Video video = new Video("teste", "1", secao, "www.alura.com.br");
//        Alternative alternativa = new Alternative("teste", true, question);
//
//        System.out.println(categoria);
//        System.out.println(subcategoria);
//        System.out.println(curso);
//        System.out.println(secao);
//        System.out.println(question);
//        System.out.println(explicacao);
//        System.out.println(video);
//        System.out.println(alternativa);

        ArrayList<Category> categories = new ReadCategory().CategoryReader("/home/arthur/Alura/planilha-dados-escola - Categoria.csv");
        ArrayList<SubCategory> subCategories = new ReadSubCategory().SubCategoryReader("/home/arthur/Alura/planilha-dados-escola - Subcategoria.csv", categories);
        ArrayList<Course> courses = new ReadCourse().CourseReader("/home/arthur/Alura/planilha-dados-escola - Curso.csv", subCategories);

        new Writer().htmlWriter(categories, subCategories, courses);

    }
}