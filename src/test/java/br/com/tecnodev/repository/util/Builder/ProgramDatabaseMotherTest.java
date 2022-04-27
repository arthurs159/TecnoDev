package br.com.tecnodev.repository.util.Builder;

import br.com.tecnodev.entities.category.Category;
import br.com.tecnodev.entities.course.Course;
import br.com.tecnodev.entities.subCategory.SubCategory;
import br.com.tecnodev.repository.CategoryRepository;
import br.com.tecnodev.repository.CourseRepository;
import br.com.tecnodev.repository.SubCategoryRepository;

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

    public void create() {
        Category backEnd =  CategoryBuilder.categoryBackEnd();
        Category frontEnd = CategoryBuilder.categoryFrontEnd();
        Category devops = CategoryBuilder.categoryDevops();
        categoryRepository.saveAll(Arrays.asList(backEnd, frontEnd, devops));

        SubCategory subcategoryJava = SubcategoryBuilder.subCategoryJava(backEnd);
        SubCategory subcategoryJavaScript = SubcategoryBuilder.subCategoryJs(frontEnd);
        SubCategory subCategoryMobile = SubcategoryBuilder.subCategoryMobile(backEnd);
        SubCategory subCategoryPython = SubcategoryBuilder.subCategoryPython(backEnd);
        subCategoryRepository.saveAll(Arrays.asList(subcategoryJava, subcategoryJavaScript, subCategoryMobile, subCategoryPython));

        Course courseJava = CourseBuilder.courseJava(subcategoryJava);
        Course courseJpa = CourseBuilder.courseJpa(subcategoryJava);
        Course coursePython = CourseBuilder.coursePython(subCategoryPython);
        Course courseAngular = CourseBuilder.courseAngular(subcategoryJavaScript);
        courseRepository.saveAll(Arrays.asList(courseJava, courseJpa, coursePython, courseAngular));
    }

}
