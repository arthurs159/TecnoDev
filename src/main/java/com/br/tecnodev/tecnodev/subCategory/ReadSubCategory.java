package com.br.tecnodev.tecnodev.subCategory;

import com.br.tecnodev.tecnodev.category.Category;
import com.br.tecnodev.utility.Filters;
import com.br.tecnodev.utility.Parse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadSubCategory {

    public List<SubCategory> SubCategoryReader(String pathname, List<Category> categories) throws IOException {
        FileInputStream subCategoryFile = new FileInputStream(
                new File(pathname));

        Scanner readSubCategoryFile = new Scanner(subCategoryFile);

        List<SubCategory> subCategoryList = new ArrayList<>();

        readSubCategoryFile.nextLine();
        while (readSubCategoryFile.hasNextLine()) {

            String subCategoryHeader = readSubCategoryFile.nextLine();
            String[] subCategoryData = subCategoryHeader.split(",");

            SubCategory subCategory = new SubCategory(
                    subCategoryData[0],
                    subCategoryData[1],
                    subCategoryData[2].equals("") ? 0 : Integer.parseInt(subCategoryData[2]),
                    subCategoryData[3],
                    Parse.transformToBoolean(subCategoryData[4]),
                    Filters.filterCategoriesByCode(categories, subCategoryData[5]));

            subCategoryList.add(subCategory);
        }
        readSubCategoryFile.close();
        subCategoryFile.close();

        return subCategoryList;
    }
}
