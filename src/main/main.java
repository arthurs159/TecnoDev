package main;

import jdk.swing.interop.SwingInterOpUtils;
import tecnodev.ENUM.QuestionType;
import tecnodev.activity.Activity;
import tecnodev.alternative.Alternative;
import tecnodev.category.Category;
import tecnodev.course.Course;
import tecnodev.explication.Explication;
import tecnodev.question.Question;
import tecnodev.section.Section;
import tecnodev.subCategory.SubCategory;
import tecnodev.video.Video;

import static tecnodev.ENUM.QuestionType.MULTIPLE_CHOICE;
import static tecnodev.ENUM.QuestionType.ONLY_ANSWER;

public class main {
    public static void main(String[] args) {


        Course curso = new Course("Java", "1", 8, "João");
        Course curso2 = new Course("HTML", "2", 8, true,  "Front-end", "Carlos professor" ,"Curso pra quem ama front", "Criar um site");
        System.out.println(curso);
        System.out.println(curso2);
        System.out.println("=================================================================");

        Section secao = new Section("Seção", "1", 1, true, false, curso);
        Section secao2 = new Section("Seção2", "2", 2, true, false, curso2);
        System.out.println(secao);
        System.out.println(secao2);
        System.out.println("=================================================================");

        Activity atividade1 = new Activity("Exercício1", "1", true, 1, secao);
        Activity atividade2 = new Activity("Exercício2", "2", secao2);
        System.out.println(atividade2);
        System.out.println(atividade1);
        System.out.println("=================================================================");

        Explication explicacao = new Explication("titulo", "1", true, 2, secao, "teste");
        Explication explicacao2 = new Explication("titulo", "1", secao, "teste");
        System.out.println(explicacao);
        System.out.println(explicacao2);
        System.out.println("================");

        Video video = new Video("teste", "1", secao, "teste");
        Video video2 = new Video("teste", "1", true, 1, secao, "teste", 12, "teste");
        System.out.println(video);
        System.out.println(video2);
        System.out.println("================================");

        Category categoria = new Category("teste", "1");
        Category categoria2 = new Category("teste", "1", "teste", "teste", true, 1, "teste", "teste");
        System.out.println(categoria);
        System.out.println(categoria2);
        System.out.println("=================================");

        SubCategory subcategoria = new SubCategory("teste", "1", categoria);
        SubCategory subcategoria2 = new SubCategory("teste", "1", "teste", "teste", true, 1, categoria);
        System.out.println(subcategoria);
        System.out.println(subcategoria2);
        System.out.println("================================");

        Question question = new Question("teste", "1", secao, "teste", MULTIPLE_CHOICE);
        System.out.println(question);
        System.out.println("================================");
        // Checar se joga a exceção correta.

        Alternative alternativa = new Alternative("", true, question );
        System.out.println("Alternativa ----------------->");
        System.out.println(alternativa);
        System.out.println("*******************************");


    }
}
