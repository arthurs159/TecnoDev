package main;

import tecnodev.category.Category;
import tecnodev.course.Course;
import tecnodev.subCategory.SubCategory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static validator.Validator.transformEnumToString;
import static validator.Validator.transformToBoolean;

public class ReadFiles {

    public static void main(String[] args) throws FileNotFoundException {

        FileInputStream categoryFile = new FileInputStream(
                new File("/home/arthur/Alura/planilha-dados-escola - Categoria.csv"));
        FileInputStream subCategoryFile = new FileInputStream(
                new File("/home/arthur/Alura/planilha-dados-escola - Subcategoria.csv"));
        FileInputStream courseFile = new FileInputStream(
                new File("/home/arthur/Alura/planilha-dados-escola - Curso.csv"));

        Scanner readCategoryFile = new Scanner(categoryFile);
        Scanner readSubCategoryFile = new Scanner(subCategoryFile);
        Scanner readCourseFile = new Scanner(courseFile);

        List<Category> categoryList = new ArrayList<>();
        List<SubCategory> subCategoryList = new ArrayList<>();
        List<Course> courseList = new ArrayList<>();

        readCategoryFile.nextLine();
        while (readCategoryFile.hasNextLine()) {
            String line1 = readCategoryFile.nextLine();
            String[] data1 = line1.split(",");

            Category category = new Category(
                    data1[0],
                    data1[1],
                    data1[2].equals("") ? 0 : Integer.parseInt(data1[2]),
                    data1[3],
                    transformToBoolean(data1[4]),
                    data1[5],
                    data1[6]);
            categoryList.add(category);
        }

        readSubCategoryFile.nextLine();
        while (readSubCategoryFile.hasNextLine()) {
            String line2 = readSubCategoryFile.nextLine();
            String[] data2 = line2.split(",");
            SubCategory subCategoria = new SubCategory(
                    data2[0],
                    data2[1],
                    data2[2].equals("") ? 0 : Integer.parseInt(data2[2]),
                    data2[3],
                    transformToBoolean(data2[4]),
                    filterCategoriesByCode(categoryList, data2[5]));

            subCategoryList.add(subCategoria);
        }

        readCourseFile.nextLine();
        while (readCourseFile.hasNextLine()) {

            String line3 = readCourseFile.nextLine();
            String[] data3 = line3.split(",");

            if (data3.length != 7) {

                Course course = new Course(
                        data3[0], //nome
                        data3[1].trim(), // codigo
                        data3[2].equals("") ? 0 : Integer.parseInt(data3[2]), //integer hours
                        transformEnumToString(data3[3]), //visibility enum
                        data3[4], // targetaudience
                        data3[5], // instrutor
                        data3[6],  // description
                        data3[7], // habilidades
                        filterSubCategoryByCode(subCategoryList, data3[8])  // subcategory
                );
                courseList.add(course);
            }
        }

        System.out.println("Categories : ");
        categoryList.forEach(System.out::println);

        System.out.println("SubCategories :");
        subCategoryList.forEach(System.out::println);

        System.out.println("Courses :");
        courseList.forEach(System.out::println);

    }

    static Category filterCategoriesByCode(List<Category> categories, String categoryCode) {
        return categories.stream()
                .filter(category -> category.getCode().equalsIgnoreCase(categoryCode))
                .findFirst().orElse(null);
    }

    static SubCategory filterSubCategoryByCode(List<SubCategory> subCategory, String subCategoryCode) {
        return subCategory.stream()
                .filter(subCat -> subCat.getCode().equalsIgnoreCase(subCategoryCode))
                .findFirst().orElse(null);
    }


}
