package tecnodev.subCategory;

import tecnodev.category.Category;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static validator.Filters.filterCategoriesByCode;
import static validator.Parse.transformToBoolean;

public class ReadSubCategory {

    public ArrayList<SubCategory> SubCategoryReader(String pathname, ArrayList<Category> categories) throws FileNotFoundException {
        FileInputStream subCategoryFile = new FileInputStream(
                new File(pathname));

        Scanner readSubCategoryFile = new Scanner(subCategoryFile);

        ArrayList<SubCategory> subCategoryList = new ArrayList<>();

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
                    filterCategoriesByCode(categories, data2[5]));

            subCategoryList.add(subCategoria);
        }

        System.out.println("\nSubCategories : ");
        subCategoryList.forEach(System.out::println);

        readSubCategoryFile.close();
        return subCategoryList;
    }
}
