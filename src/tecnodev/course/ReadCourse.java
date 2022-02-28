package tecnodev.course;

import tecnodev.subCategory.SubCategory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static tecnodev.course.Status.from;
import static utility.Filters.filterSubCategoryByCode;

public class ReadCourse {

    public List<Course> CourseReader(String pathname, List<SubCategory> subcategory) throws IOException {
        FileInputStream courseFile = new FileInputStream(
                new File(pathname));

        Scanner readCourseFile = new Scanner(courseFile);
        List<Course> courseList = new ArrayList<>();

        readCourseFile.nextLine();
        while (readCourseFile.hasNextLine()) {

            String courseHeader = readCourseFile.nextLine();
            String[] courseData = courseHeader.split(",");

            if (courseData.length == 9) {
                Course course = new Course(
                        courseData[0],
                        courseData[1].trim(),
                        courseData[2].equals("") ? 0 : Integer.parseInt(courseData[2]),
                        from(courseData[3]),
                        courseData[4],
                        courseData[5],
                        courseData[6],
                        courseData[7].equals("") ? "Empty field" : courseData[7],
                        filterSubCategoryByCode(subcategory, courseData[8])
                );
                courseList.add(course);
            }
        }
        readCourseFile.close();
        courseFile.close();

        return courseList;
    }

    public static void privateCourses(List<Course> courses) {
        courses.stream().filter(privateCourse -> privateCourse.getVisibility() == Status.PRIVATE)
                .findAny()
                .ifPresentOrElse(course -> System.out.println(course), () -> System.out.println(" == Não há curso(s) privado(s) == "));
    }

    public static void instructors(List<Course> courses) {
        courses.stream().map(Course::getTeacher)
                .collect(Collectors.toSet())
                .forEach(instructor -> System.out.println(instructor));
    }

    public static int numbers(List<Course> courses, String teacher) {
        return (int) courses.stream()
                .filter(course -> course.getTeacher().equals(teacher)).count();
    }

}