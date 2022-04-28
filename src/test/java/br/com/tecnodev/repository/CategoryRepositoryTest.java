package br.com.tecnodev.repository;

import br.com.tecnodev.entities.category.Category;
import br.com.tecnodev.entities.course.Course;
import br.com.tecnodev.entities.course.Status;
import br.com.tecnodev.entities.subCategory.SubCategory;
import br.com.tecnodev.projections.CategoryReportProjection;
import br.com.tecnodev.repository.util.Builder.CategoryBuilder;
import br.com.tecnodev.repository.util.Builder.CourseBuilder;
import br.com.tecnodev.repository.util.Builder.SubcategoryBuilder;
import br.com.tecnodev.repository.util.ProgramDatabaseMotherTest;
import org.junit.jupiter.api.BeforeEach;
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
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private CourseRepository courseRepository;

//    @BeforeEach
//    public void init() {
//        ProgramDatabaseMotherTest programDatabaseMotherTest = new ProgramDatabaseMotherTest(categoryRepository, subCategoryRepository, courseRepository);
//        programDatabaseMotherTest.create();
//    }

    @Test
    public void findAllByActiveTrue() {
        Category backEnd = CategoryBuilder.categoryBackEnd("Back-End", "backend", true);
        Category frontEnd = CategoryBuilder.categoryFrontEnd("Front-End", "frontend", true);
        Category devops = CategoryBuilder.categoryDevops("Dev-Ops", "devops", false);
        categoryRepository.saveAll(Arrays.asList(backEnd, frontEnd, devops));

        List<Category> allByActiveTrue = categoryRepository.findAllByActiveTrue();

        assertEquals(2, allByActiveTrue.size());
        assertEquals("backend", allByActiveTrue.get(0).getCode());
        assertEquals("frontend", allByActiveTrue.get(1).getCode());
    }

    @Test
    public void findAllByActiveTrue__Should_Return_An_Empty_Category_List_When_There_Is_No_Category_Active() {
        Category devops = CategoryBuilder.categoryDevops("Dev-Ops", "devops", false);
        categoryRepository.save(devops);

        List<Category> allByActiveTrue = categoryRepository.findAllByActiveTrue();

        assertEquals(0, allByActiveTrue.size());
        assertTrue(allByActiveTrue.isEmpty());
    }

    @Test
    public void findAllByOrderByOrderInSystem() {
        Category backEnd = CategoryBuilder.categoryBackEnd("Back-End", "backend", true);
        Category frontEnd = CategoryBuilder.categoryFrontEnd("Front-End", "frontend", true);
        Category devops = CategoryBuilder.categoryDevops("Dev-Ops", "devops", false);
        categoryRepository.saveAll(Arrays.asList(backEnd, frontEnd, devops));

        List<Category> allByOrderByOrderInSystem = categoryRepository.findAllByOrderByOrderInSystem();

        assertNotNull(allByOrderByOrderInSystem);
        assertEquals("devops", allByOrderByOrderInSystem.get(0).getCode());
        assertEquals("frontend", allByOrderByOrderInSystem.get(1).getCode());
        assertEquals("backend", allByOrderByOrderInSystem.get(2).getCode());
    }

    @Test
    public void findAllByOrderByOrderInSystem__Should_Return_An_Empty_List_When_There_Is_No_Category() {
        List<Category> allByOrderByOrderInSystem = categoryRepository.findAllByOrderByOrderInSystem();

        assertEquals(0, allByOrderByOrderInSystem.size());
        assertTrue(allByOrderByOrderInSystem.isEmpty());
    }

    @Test
    public void findByCode() {
        Category backEnd = CategoryBuilder.categoryBackEnd("Back-End", "backend", true);
        categoryRepository.save(backEnd);

        String code = "backend";
        Optional<Category> category = categoryRepository.findByCode(code);

        assertTrue(category.isPresent());
        assertEquals(code, category.get().getCode());
    }

    @Test
    public void findByCode__Should_Return_Empty_True_When_Code_Not_Exists() {
        String nonExistingCode = "nonExistingCode";
        Optional<Category> category = categoryRepository.findByCode(nonExistingCode);

        assertTrue(category.isEmpty());
    }

    @Test
    public void findCategoryAndNumberOfCourses() {
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

        List<CategoryReportProjection> categoryAndNumberOfCourses = categoryRepository.findCategoryAndNumberOfCourses();

        assertEquals("Back-End", categoryAndNumberOfCourses.get(0).getCategoryName());
        assertEquals(3, categoryAndNumberOfCourses.get(0).getQuantityCoursesFromCategory());
        assertEquals("Front-End", categoryAndNumberOfCourses.get(1).getCategoryName());
        assertEquals(1, categoryAndNumberOfCourses.get(1).getQuantityCoursesFromCategory());
        assertEquals("DevOps", categoryAndNumberOfCourses.get(2).getCategoryName());
        assertEquals(0, categoryAndNumberOfCourses.get(2).getQuantityCoursesFromCategory());
    }

    @Test
    public void getCategoriesWithSubcategoryActiveAndVisibleCourses() {
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

        List<Category> categoriesVisibleSubAndCourse = categoryRepository.getCategoriesWithActiveSubcategoryAndPublicCourses();

        assertEquals("frontend", categoriesVisibleSubAndCourse.get(0).getCode());
        assertEquals("backend", categoriesVisibleSubAndCourse.get(1).getCode());
    }

    @Test
    public void getCategoriesWithSubcategoryActiveAndVisibleCourses__Should_Return_An_Empty_Array_When_There_Is_No_Category() {
        List<Category> categoriesVisibleSubAndCourse = categoryRepository.getCategoriesWithActiveSubcategoryAndPublicCourses();

        assertEquals(0, categoriesVisibleSubAndCourse.size());
        assertTrue(categoriesVisibleSubAndCourse.isEmpty());
    }

    @Test
    public void getCategoriesWithSubcategoryActiveAndVisibleCourses__Should_Return_An_Empty_Array_When_There_Is_Category_With_Inactive_Subcategory_And_Private_Course() {
        Category backEnd = CategoryBuilder.categoryBackEnd("Back-End", "backend", true);
        SubCategory subcategoryJava = SubcategoryBuilder.subCategoryJava(backEnd, "Java", "java", false);
        Course courseJava = CourseBuilder.courseJava(subcategoryJava, "Java e Sintaxe", "javasintaxe", "Cleb Paulo", Status.PRIVATE);

        List<Category> categoriesVisibleSubAndCourse = categoryRepository.getCategoriesWithActiveSubcategoryAndPublicCourses();
        assertEquals(0, categoriesVisibleSubAndCourse.size());
        assertTrue(categoriesVisibleSubAndCourse.isEmpty());
    }
}