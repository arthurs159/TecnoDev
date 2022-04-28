package br.com.tecnodev.repository;

import br.com.tecnodev.entities.subCategory.SubCategory;
import br.com.tecnodev.repository.util.ProgramDatabaseMotherTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

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

    @BeforeEach
    public void init() {
        ProgramDatabaseMotherTest programDatabaseMotherTest = new ProgramDatabaseMotherTest(categoryRepository, subCategoryRepository, courseRepository);
        programDatabaseMotherTest.create();
    }

    @Test
    void findSubCategoriesByCategory_CodeOrderByOrderInSystem() {
        String categoryCode = "backend";
        List<SubCategory> subcategoryByCategoryCodeOrdered = subCategoryRepository.findSubCategoriesByCategory_CodeOrderByOrderInSystem(categoryCode);

        assertEquals("java", subcategoryByCategoryCodeOrdered.get(0).getCode());
        assertEquals("python", subcategoryByCategoryCodeOrdered.get(1).getCode());
        assertEquals("mobile", subcategoryByCategoryCodeOrdered.get(2).getCode());
    }

//    @Test
//    void getSubcategoryByCategoryCodeOrdered() {
//        String categoryCode = "backend";
//        List<SubCategory> subcategoryByCategoryCodeOrdered = subCategoryRepository.getSubcategoryByCategoryCodeOrdered(categoryCode);
//
//        assertEquals("java", subcategoryByCategoryCodeOrdered.get(0).getCode());
//        assertEquals("python", subcategoryByCategoryCodeOrdered.get(1).getCode());
//        assertEquals("mobile", subcategoryByCategoryCodeOrdered.get(2).getCode());
//    }

    @Test
    void getSubcategoryByCategoryCodeOrdered_Should_Return_IsEmpty_True_When_Code_Not_Exists() {
        String nonExistingCode = "nonExistingCode";
        List<SubCategory> subcategoryByCategoryCodeOrdered = subCategoryRepository.findSubCategoriesByCategory_CodeOrderByOrderInSystem(nonExistingCode);

        assertTrue(subcategoryByCategoryCodeOrdered.isEmpty());
    }

    @Test
    void findSubcategoryByCategoryAndSubcategoryCode() {
        String categoryCode = "backend";
        String subcategoryCodePy = "python";
        String subcategoryCodeJava = "java";

        Optional<SubCategory> subcategoryByCategoryAndSubcategoryCodePy = subCategoryRepository.findSubcategoryByCategoryAndSubcategoryCode(subcategoryCodePy, categoryCode);
        Optional<SubCategory> subcategoryByCategoryAndSubcategoryCodeJava = subCategoryRepository.findSubcategoryByCategoryAndSubcategoryCode(subcategoryCodeJava, categoryCode);

        assertTrue(subcategoryByCategoryAndSubcategoryCodePy.isPresent());
        assertEquals(subcategoryCodePy, subcategoryByCategoryAndSubcategoryCodePy.get().getCode());

        assertTrue(subcategoryByCategoryAndSubcategoryCodeJava.isPresent());
        assertEquals(subcategoryCodeJava, subcategoryByCategoryAndSubcategoryCodeJava.get().getCode());
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
        String java = "java";
        String python = "python";
        String js = "javascript";
        Optional<SubCategory> subcategoryByCodeJava = subCategoryRepository.findSubcategoryByCode(java);
        Optional<SubCategory> subcategoryByCodePython = subCategoryRepository.findSubcategoryByCode(python);
        Optional<SubCategory> subcategoryByCodeJs = subCategoryRepository.findSubcategoryByCode(js);

        assertTrue(subcategoryByCodeJava.isPresent());
        assertTrue(subcategoryByCodePython.isPresent());
        assertTrue(subcategoryByCodeJs.isPresent());
        assertEquals(java, subcategoryByCodeJava.get().getCode());
        assertEquals(python, subcategoryByCodePython.get().getCode());
        assertEquals(js, subcategoryByCodeJs.get().getCode());
    }

    @Test
    void findSubcategoryByCode__Should_return_false_when_code_not_exists() {
        String nonExistingCode = "nonExistingCode";
        Optional<SubCategory> subcategoryByCodeJava = subCategoryRepository.findSubcategoryByCode(nonExistingCode);

        assertFalse(subcategoryByCodeJava.isPresent());
    }

    @Test
    void findAllActiveSubcategoryWithCourses() {
        String categoryCodeBack = "backend";
        String categoryCodeFront = "frontend";
        List<SubCategory> activeSubcategoryWithCoursesByBackCategory = subCategoryRepository.findAllActiveSubcategoryWithCoursesByCategoryCode(categoryCodeBack);
        List<SubCategory> activeSubcategoryWithCoursesByFrontCategory = subCategoryRepository.findAllActiveSubcategoryWithCoursesByCategoryCode(categoryCodeFront);

        assertFalse(activeSubcategoryWithCoursesByBackCategory.isEmpty());
        assertFalse(activeSubcategoryWithCoursesByFrontCategory.isEmpty());
        assertEquals("java", activeSubcategoryWithCoursesByBackCategory.get(0).getCode());
        assertEquals("javascript", activeSubcategoryWithCoursesByFrontCategory.get(0).getCode());
    }

    @Test
    void findAllActiveSubcategoryWithCourses__Should_Return_IsEmpty_True_When_Category_Not_Have_Subcategory_With_Courses() {
        String categoryCodeMobile = "mobile";
        List<SubCategory> activeSubcategoryWithCoursesByFrontCategory = subCategoryRepository.findAllActiveSubcategoryWithCoursesByCategoryCode(categoryCodeMobile);

        assertTrue(activeSubcategoryWithCoursesByFrontCategory.isEmpty());
    }
}