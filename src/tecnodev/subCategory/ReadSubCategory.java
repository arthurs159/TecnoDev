package tecnodev.subCategory;

import tecnodev.category.Category;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.function.Predicate.*;
import static utility.Filters.filterCategoriesByCode;
import static utility.Parse.transformToBoolean;

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
                    transformToBoolean(subCategoryData[4]),
                    filterCategoriesByCode(categories, subCategoryData[5]));

            subCategoryList.add(subCategory);
        }
        readSubCategoryFile.close();
        subCategoryFile.close();

        return subCategoryList;
    }

    public static List<SubCategory> subcategoryWithoutDescription(List<SubCategory> subCategories){
        return subCategories.stream()
                .filter(not(SubCategory::hasDescription))
                .toList();
    }

    public static long subcategoryActiveWithDescription(List<SubCategory> subCategories){
       return subCategories.stream()
                .filter(SubCategory::isActive)
                .filter(SubCategory::hasDescription)
                .count();
    }
}
