package com.br.tecnodev.utility;

import com.br.tecnodev.tecnodev.category.Category;
import com.br.tecnodev.tecnodev.course.Course;
import com.br.tecnodev.tecnodev.subCategory.SubCategory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;
import static com.br.tecnodev.tecnodev.course.Status.PRIVATE;

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

    public static List<Category> findActiveCategories(List<Category> categories) {
        return categories.stream()
                .filter(Category::isActive)
                .toList();
    }

    public static List<SubCategory> findSubcategoriesWithoutDescription(List<SubCategory> subCategories) {
        return subCategories.stream()
                .filter(not(SubCategory::hasDescription))
                .toList();
    }

    public static long countActiveSubcategoriesWithDescription(List<SubCategory> subCategories) {
        return subCategories.stream()
                .filter(SubCategory::isActive)
                .filter(SubCategory::hasDescription)
                .count();
    }

    public static void findPrivateCourses(List<Course> courses) {
        courses.stream().filter(course -> course.getVisibility().equals(PRIVATE))
                .findAny()
                .ifPresentOrElse(System.out::println, () -> System.out.println(" == Não há curso(s) privado(s) == "));
    }

    public static Set<String> findInstructors(List<Course> courses) {
        return courses.stream()
                .map(Course::getTeacher)
                .collect(Collectors.toSet());
    }

    public static long numbersOfCourseFromInstructors(List<Course> courses, String teacher) {
        return courses.stream()
                .filter(course -> course.getTeacher().equals(teacher)).count();
    }

}