package br.com.tecnodev.entities.category;

import br.com.tecnodev.utility.Parse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadCategory {

    public List<Category> CategoryReader(String pathname) throws IOException {

        FileInputStream categoryFile = new FileInputStream(new File(pathname));

        Scanner readCategoryFile = new Scanner(categoryFile);
        List<Category> categoryList = new ArrayList<>();

        readCategoryFile.nextLine();
        while (readCategoryFile.hasNextLine()) {

            String categoryHeader = readCategoryFile.nextLine();
            String[] categoryData = categoryHeader.split(",");

            Category category = new Category(
                    categoryData[0],
                    categoryData[1],
                    categoryData[2].equals("") ? 0 : Integer.parseInt(categoryData[2]),
                    categoryData[3],
                    Parse.transformToBoolean(categoryData[4]),
                    categoryData[5],
                    categoryData[6]);
            categoryList.add(category);
        }
        readCategoryFile.close();
        categoryFile.close();

        return categoryList;
    }
}
