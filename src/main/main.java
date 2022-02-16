package main;

import tecnodev.alternative.Alternative;
import tecnodev.category.Category;
import tecnodev.course.Course;
import tecnodev.explication.Explication;
import tecnodev.question.Question;
import tecnodev.section.Section;
import tecnodev.subCategory.SubCategory;
import tecnodev.video.Video;

import static tecnodev.question.QuestionType.MULTIPLE_CHOICE;

public class main {
    public static void main(String[] args) {

        Category categoria = new Category("teste", "1-teste");
        SubCategory subcategoria = new SubCategory("teste", "1-2", categoria);
        Course curso = new Course("Java", "codigo-", 7, "professor", subcategoria);
        Section secao = new Section("Seção", "1", curso);
        Question question = new Question("teste", "1", secao, "teste", MULTIPLE_CHOICE);
        Explication explicacao = new Explication("titulo", "1", secao, "teste");
        Video video = new Video("teste", "1", secao, "www.alura.com.br");
        Alternative alternativa = new Alternative("teste", true, question);

        System.out.println(categoria);
        System.out.println(subcategoria);
        System.out.println(curso);
        System.out.println(secao);
        System.out.println(question);
        System.out.println(explicacao);
        System.out.println(video);
        System.out.println(alternativa);
    }
}