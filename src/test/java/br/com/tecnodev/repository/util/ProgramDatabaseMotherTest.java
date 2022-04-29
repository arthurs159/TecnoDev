package br.com.tecnodev.repository.util;

import br.com.tecnodev.entities.category.Category;
import br.com.tecnodev.entities.course.Course;
import br.com.tecnodev.entities.course.Status;
import br.com.tecnodev.entities.subCategory.SubCategory;
import br.com.tecnodev.repository.CategoryRepository;
import br.com.tecnodev.repository.CourseRepository;
import br.com.tecnodev.repository.SubCategoryRepository;
import br.com.tecnodev.repository.util.Builder.CategoryBuilder;
import br.com.tecnodev.repository.util.Builder.CourseBuilder;
import br.com.tecnodev.repository.util.Builder.SubcategoryBuilder;

import java.util.Arrays;

public class ProgramDatabaseMotherTest {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final CourseRepository courseRepository;

    public ProgramDatabaseMotherTest(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository, CourseRepository courseRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.courseRepository = courseRepository;
    }

    public void createAll() {
        Category backEnd = CategoryBuilder.categoryBackEnd("Back-End", "backend", true);
        Category frontEnd = CategoryBuilder.categoryFrontEnd("Front-End", "frontend", true);
        Category devops = CategoryBuilder.categoryDevops("DevOps", "devops", false);
        categoryRepository.saveAll(Arrays.asList(backEnd, frontEnd, devops));
        SubCategory subcategoryJava = SubcategoryBuilder.subCategoryJava(backEnd, "Java", "java", true);
        SubCategory subcategoryJavaScript = SubcategoryBuilder.subCategoryJs(frontEnd, "JavaScript", "javascript", true);
        SubCategory subCategoryMobile = SubcategoryBuilder.subCategoryMobile(backEnd, "Mobile", "mobile", true);
        SubCategory subCategoryPython = SubcategoryBuilder.subCategoryPython(backEnd, "Python", "python", false);
        subCategoryRepository.saveAll(Arrays.asList(subcategoryJava, subcategoryJavaScript, subCategoryMobile, subCategoryPython));
        Course courseJava = CourseBuilder.courseJava(subcategoryJava, "Java e Sintaxe", "javasintaxe", "Cleb Paulo", Status.PUBLIC);
        Course courseJpa = CourseBuilder.courseJpa(subcategoryJava, "JPA", "jpa", "Cleb Paulo", Status.PUBLIC);
        Course coursePython = CourseBuilder.coursePython(subCategoryPython, "Python", "py", "Cleb Paulo", Status.PRIVATE);
        Course courseAngular = CourseBuilder.courseAngular(subcategoryJavaScript, "Angular", "angular", "Paulo Silva", Status.PUBLIC);
        courseRepository.saveAll(Arrays.asList(courseJava, courseJpa, coursePython, courseAngular));
    }

    public void createActiveCategories(String code) {
        Category backEnd = CategoryBuilder.categoryBackEnd("Back-End", code, true);
        categoryRepository.save(backEnd);
    }

}
