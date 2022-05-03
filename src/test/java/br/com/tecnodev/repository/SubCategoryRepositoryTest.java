package br.com.tecnodev.repository;

import br.com.tecnodev.entities.category.Category;
import br.com.tecnodev.entities.course.Course;
import br.com.tecnodev.entities.course.Status;
import br.com.tecnodev.entities.subCategory.SubCategory;
import br.com.tecnodev.repository.util.Builder.CategoryBuilder;
import br.com.tecnodev.repository.util.Builder.CourseBuilder;
import br.com.tecnodev.repository.util.Builder.SubcategoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class SubCategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void findSubCategoriesByCategory_CodeOrderByOrderInSystem() {
        Category backEnd = CategoryBuilder.categoryBackEnd("Back-End", "backend", true);
        SubCategory subcategoryJava = SubcategoryBuilder.subCategoryJava(backEnd, "Java", "java", true);
        SubCategory subCategoryPython = SubcategoryBuilder.subCategoryPython(backEnd, "Python", "python", false);
        SubCategory subCategoryMobile = SubcategoryBuilder.subCategoryMobile(backEnd, "Mobile", "mobile", true);
        categoryRepository.save(backEnd);
        subCategoryRepository.saveAll(Arrays.asList(subcategoryJava, subCategoryMobile, subCategoryPython));

        String categoryCode = "backend";
        List<SubCategory> subcategoriesByCategoryCodeOrdered = subCategoryRepository.findSubCategoriesByCategory_CodeOrderByOrderInSystem(categoryCode);

        assertEquals("java", subcategoriesByCategoryCodeOrdered.get(0).getCode());
        assertEquals("python", subcategoriesByCategoryCodeOrdered.get(1).getCode());
        assertEquals("mobile", subcategoriesByCategoryCodeOrdered.get(2).getCode());
    }

    @Test
    void getSubcategoryByCategoryCodeOrdered_Should_Return_IsEmpty_True_When_Code_Not_Exists() {
        String nonExistingCode = "nonExistingCode";
        List<SubCategory> subcategoriesByCategoryCodeOrdered = subCategoryRepository.findSubCategoriesByCategory_CodeOrderByOrderInSystem(nonExistingCode);

        assertTrue(subcategoriesByCategoryCodeOrdered.isEmpty());
    }

    @Test
    void findSubcategoryByCategoryAndSubcategoryCode() {
        Category backEnd = CategoryBuilder.categoryBackEnd("Back-End", "backend", true);
        SubCategory subcategoryJava = SubcategoryBuilder.subCategoryJava(backEnd, "Java", "java", true);
        SubCategory subCategoryPython = SubcategoryBuilder.subCategoryPython(backEnd, "Python", "python", false);
        categoryRepository.save(backEnd);
        subCategoryRepository.saveAll(Arrays.asList(subcategoryJava, subCategoryPython));

        String categoryCode = "backend";
        String subcategoryCodePy = "python";
        String subcategoryCodeJava = "java";

        Optional<SubCategory> possibleSubcategoryPython = subCategoryRepository.findSubcategoryByCategoryAndSubcategoryCode(subcategoryCodePy, categoryCode);
        Optional<SubCategory> possibleSubcategoryJava = subCategoryRepository.findSubcategoryByCategoryAndSubcategoryCode(subcategoryCodeJava, categoryCode);

        assertTrue(possibleSubcategoryPython.isPresent());
        assertEquals(subcategoryCodePy, possibleSubcategoryPython.get().getCode());

        assertTrue(possibleSubcategoryJava.isPresent());
        assertEquals(subcategoryCodeJava, possibleSubcategoryJava.get().getCode());
    }

    @Test
    void findSubcategoryByCategoryAndSubcategoryCode__Should_Return_IsEmpty_True_When_CategoryCode_and_Subcategory_Not_Exists() {
        String nonExistingCategoryCode = "nonExistingCategoryCode";
        String nonExistingSubcategoryCode = "nonExistingSubcategoryCode";

        Optional<SubCategory> subcategoryByCategoryAndSubcategoryCodePy = subCategoryRepository.findSubcategoryByCategoryAndSubcategoryCode(nonExistingCategoryCode, nonExistingSubcategoryCode);

        assertTrue(subcategoryByCategoryAndSubcategoryCodePy.isEmpty());
    }

    @Test
    void findSubcategoryByCode() {
        Category backEnd = CategoryBuilder.categoryBackEnd("Back-End", "backend", true);
        SubCategory subcategoryJava = SubcategoryBuilder.subCategoryJava(backEnd, "Java", "java", true);
        SubCategory subCategoryPython = SubcategoryBuilder.subCategoryPython(backEnd, "Python", "python", false);
        categoryRepository.save(backEnd);
        subCategoryRepository.saveAll(Arrays.asList(subcategoryJava, subCategoryPython));

        String javaCode = "java";
        String pythonCode = "python";
        Optional<SubCategory> subcategoryByCodeJava = subCategoryRepository.findSubcategoryByCode(javaCode);
        Optional<SubCategory> subcategoryByCodePython = subCategoryRepository.findSubcategoryByCode(pythonCode);

        assertTrue(subcategoryByCodeJava.isPresent());
        assertTrue(subcategoryByCodePython.isPresent());
        assertEquals(javaCode, subcategoryByCodeJava.get().getCode());
        assertEquals(pythonCode, subcategoryByCodePython.get().getCode());
    }

    @Test
    void findSubcategoryByCode__Should_return_false_when_code_not_exists() {
        String nonExistingCode = "nonExistingCode";
        Optional<SubCategory> subcategoryByCodeJava = subCategoryRepository.findSubcategoryByCode(nonExistingCode);

        assertFalse(subcategoryByCodeJava.isPresent());
    }

    @Test
    void findAllActiveSubcategoryWithCourses() {
        Category backEnd = CategoryBuilder.categoryBackEnd("Back-End", "backend", true);
        Category frontEnd = CategoryBuilder.categoryFrontEnd("Front-End", "frontend", true);
        categoryRepository.saveAll(Arrays.asList(backEnd, frontEnd));
        SubCategory subcategoryJava = SubcategoryBuilder.subCategoryJava(backEnd, "Java", "java", true);
        SubCategory subcategoryJavaScript = SubcategoryBuilder.subCategoryJs(frontEnd, "JavaScript", "javascript", true);
        SubCategory subCategoryMobile = SubcategoryBuilder.subCategoryMobile(backEnd, "Mobile", "mobile", true);
        subCategoryRepository.saveAll(Arrays.asList(subcategoryJava, subcategoryJavaScript, subCategoryMobile));
        Course courseJava = CourseBuilder.courseJava(subcategoryJava, "Java e Sintaxe", "javasintaxe", "Cleb Paulo", Status.PUBLIC);
        Course courseJpa = CourseBuilder.courseJpa(subcategoryJava, "JPA", "jpa", "Cleb Paulo", Status.PUBLIC);
        Course courseAngular = CourseBuilder.courseAngular(subcategoryJavaScript, "Angular", "angular", "Paulo Silva", Status.PUBLIC);
        courseRepository.saveAll(Arrays.asList(courseJava, courseJpa, courseAngular));

        String categoryCodeBack = "backend";
        String categoryCodeFront = "frontend";
        List<SubCategory> activeSubcategoriesWithCoursesByBackCategory = subCategoryRepository.findAllActiveSubcategoryWithCoursesByCategoryCode(categoryCodeBack);
        List<SubCategory> activeSubcategoriesWithCoursesByFrontCategory = subCategoryRepository.findAllActiveSubcategoryWithCoursesByCategoryCode(categoryCodeFront);

        assertFalse(activeSubcategoriesWithCoursesByBackCategory.isEmpty());
        assertFalse(activeSubcategoriesWithCoursesByFrontCategory.isEmpty());
        assertEquals("java", activeSubcategoriesWithCoursesByBackCategory.get(0).getCode());
        assertEquals("javascript", activeSubcategoriesWithCoursesByFrontCategory.get(0).getCode());
    }

    @Test
    void findAllActiveSubcategoryWithCourses__Should_Return_IsEmpty_True_When_Category_Not_Have_Subcategory_With_Courses() {
        Category backEnd = CategoryBuilder.categoryBackEnd("Back-End", "backend", true);
        categoryRepository.save(backEnd);
        SubCategory subCategoryPython = SubcategoryBuilder.subCategoryPython(backEnd, "Python", "python", false);
        subCategoryRepository.save(subCategoryPython);

        String categoryCodeMobile = "backend";
        List<SubCategory> activeSubcategoriesWithCoursesByFrontCategory = subCategoryRepository.findAllActiveSubcategoryWithCoursesByCategoryCode(categoryCodeMobile);

        assertTrue(activeSubcategoriesWithCoursesByFrontCategory.isEmpty());
    }
}