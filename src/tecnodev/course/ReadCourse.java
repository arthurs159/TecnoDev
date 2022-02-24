package tecnodev.course;

import tecnodev.subCategory.SubCategory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static tecnodev.course.Status.from;
import static utility.Filters.filterSubCategoryByCode;

public class ReadCourse {

    public ArrayList<Course> CourseReader(String pathname, ArrayList<SubCategory> subcategory) throws FileNotFoundException {
        FileInputStream courseFile = new FileInputStream(
                new File("/home/arthur/Alura/planilha-dados-escola - Curso.csv"));

        Scanner readCourseFile = new Scanner(courseFile);
        ArrayList<Course> courseList = new ArrayList<>();

        readCourseFile.nextLine();
        while (readCourseFile.hasNextLine()) {

            String line3 = readCourseFile.nextLine();
            String[] data3 = line3.split(",");

            if (data3.length == 9) {
                Course course = new Course(
                        data3[0],
                        data3[1].trim(),
                        data3[2].equals("") ? 0 : Integer.parseInt(data3[2]),
                        from(data3[3]),
                        data3[4],
                        data3[5],
                        data3[6],
                        data3[7].equals("") ? "Empty field" : data3[7],
                        filterSubCategoryByCode(subcategory, data3[8])
                );
                courseList.add(course);
            }
        }
        System.out.println("\nCourses : ");
        courseList.forEach(System.out::println);

        readCourseFile.close();

        return courseList;
    }
}
