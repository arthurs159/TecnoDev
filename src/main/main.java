package main;

import tecnodev.activity.Activity;
import tecnodev.category.Category;
import tecnodev.course.Course;
import tecnodev.section.Section;
import tecnodev.subCategory.SubCategory;

public class main {
    public static void main(String[] args) {


        Course curso = new Course("Java", "1", 8, "João");
        Course curso2 = new Course("HTML", "2", 8, true,  "Front-end", "Carlos professor" ,"Curso pra quem ama front", "Criar um site");
        System.out.println("CURSO com Atributos obrigatórios ----------------->");
        System.out.println(curso);
        System.out.println("*******************************");
        System.out.println("CURSO com Todos Os Atributos ----------------->");
        System.out.println(curso2);
        System.out.println("=================================================================");

        Section secao = new Section("Seção", "1", 1, true, false, curso);
        Section secao2 = new Section("Seção2", "2", 2, true, false, curso2);
        System.out.println("SEÇÃO com Atributos obrigatórios ----------------->");
        System.out.println(secao);
        System.out.println("*******************************");
        System.out.println("SEÇÃO com Todos Os Atributos ----------------->");
        System.out.println(secao2);
        System.out.println("=================================================================");

        Activity atividade1 = new Activity("Exercício1", "1", true, 1, secao);
        Activity atividade2 = new Activity("Exercício2", "2", secao2);
        System.out.println("atividade1 com Atributos obrigatórios ----------------->");
        System.out.println(atividade2);
        System.out.println("*******************************");
        System.out.println("atividade2 com Todos Os Atributos ----------------->");
        System.out.println(atividade1);
        System.out.println("=================================================================");

    /*Alternative alternativa = new Alternative("Qual é o ...?", null );
        System.out.println("Alternativa ----------------->");
        System.out.println(alternativa);
        System.out.println("*******************************");
    */

   /* Category categoria = new Category("DevOps", "1");
        System.out.println("CATEGORIA ----------------->");
        System.out.println(categoria);
        System.out.println("*******************************");

        SubCategory subCategoria = new SubCategory("Subcategoria", "2", categoria);
        System.out.println("SUBCATEGORIA ----------------->");
        System.out.println(subCategoria);
        System.out.println("*******************************");
*/


    }
}
