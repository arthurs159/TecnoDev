package utility;

import tecnodev.category.Category;
import tecnodev.course.Course;
import tecnodev.course.Status;
import tecnodev.subCategory.SubCategory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;
import static tecnodev.course.Status.*;

public class Filters {

    public static Category filterCategoriesByCode(List<Category> categories, String categoryCode) {
        return categories.stream()
                .filter(category -> category.getCode().equalsIgnoreCase(categoryCode))
                .findFirst().orElse(null);
    }

    public static SubCategory filterSubCategoryByCode(List<SubCategory> subCategory, String subCategoryCode) {
        return subCategory.stream()
                .filter(subCat -> subCat.getCode().equalsIgnoreCase(subCategoryCode))
                .findFirst().orElse(null);
    }

    public static List<Category> activeCategory(List<Category> categories) {
        return categories.stream()
                .filter(Category::isActive)
                .toList();
    }

    public static List<SubCategory> subcategoryWithoutDescription(List<SubCategory> subCategories) {
        return subCategories.stream()
                .filter(not(SubCategory::hasDescription))
                .toList();
    }

    public static long subcategoryActiveWithDescription(List<SubCategory> subCategories) {
        return subCategories.stream()
                .filter(SubCategory::isActive)
                .filter(SubCategory::hasDescription)
                .count();
    }

    public static void privateCourses(List<Course> courses) {
        courses.stream().filter(course -> course.getVisibility().equals(PRIVATE))
                .findAny()
                .ifPresentOrElse(System.out::println, () -> System.out.println(" == Não há curso(s) privado(s) == "));
    }

    public static Set<String> instructors(List<Course> courses) {
        return courses.stream()
                .map(Course::getTeacher)
                .collect(Collectors.toSet());
    }

    public static long numbersOfCourseFromInstructors(List<Course> courses, String teacher) {
        return courses.stream()
                .filter(course -> course.getTeacher().equals(teacher)).count();
    }

}
