package main;

import tecnodev.category.Category;
import tecnodev.subCategory.SubCategory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static validator.Validator.transformToBoolean;

public class ReadFiles {

    public static void main(String[] args) throws FileNotFoundException {

        FileInputStream categoryFile = new FileInputStream(
                new File("/home/arthur/Alura/planilha-dados-escola - Categoria.csv"));
        FileInputStream subCategoryFile = new FileInputStream(
                new File("/home/arthur/Alura/planilha-dados-escola - Subcategoria.csv"));

        Scanner readCategoryFile = new Scanner(categoryFile);
        Scanner readSubCategoryFile = new Scanner(subCategoryFile);

        List<Category> categoryList = new ArrayList<>();
        List<SubCategory> subCategoryList = new ArrayList<>();

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
                    categoryList.get(0)
            );
            subCategoryList.add(subCategoria);
        }


        System.out.println("Categories : ");
        for (Category category : categoryList) {
            System.out.println(category);
        }

        System.out.println("SubCategories :");
        for (SubCategory subCategory : subCategoryList) {
            System.out.println(subCategory);
        }

    }
}
