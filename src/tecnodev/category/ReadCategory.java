package tecnodev.category;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static utility.Parse.transformToBoolean;

public class ReadCategory {

    public ArrayList<Category> CategoryReader(String pathname) throws FileNotFoundException {

        FileInputStream categoryFile = new FileInputStream(new File(pathname));

        Scanner readCategoryFile = new Scanner(categoryFile);
        ArrayList<Category> categoryList = new ArrayList<>();

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
        readCategoryFile.close();

        System.out.println("\nCategories : ");
        categoryList.forEach(System.out::println);

        return categoryList;
    }
}
