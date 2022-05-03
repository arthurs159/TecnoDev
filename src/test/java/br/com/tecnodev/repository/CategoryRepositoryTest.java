package br.com.tecnodev.repository;

import br.com.tecnodev.entities.category.Category;
import br.com.tecnodev.entities.course.Course;
import br.com.tecnodev.entities.course.Status;
import br.com.tecnodev.entities.subCategory.SubCategory;
import br.com.tecnodev.projections.CategoryReportProjection;
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
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void findAllByActiveTrue() {
        Category backEnd = CategoryBuilder.categoryBackEnd("Back-End", "backend", true);
        Category frontEnd = CategoryBuilder.categoryFrontEnd("Front-End", "frontend", true);
        Category devops = CategoryBuilder.categoryDevops("Dev-Ops", "devops", false);
        categoryRepository.saveAll(Arrays.asList(backEnd, frontEnd, devops));

        List<Category> activeCategories = categoryRepository.findAllByActiveTrue();

        assertEquals(2, activeCategories.size());
        assertEquals("backend", activeCategories.get(0).getCode());
        assertEquals("frontend", activeCategories.get(1).getCode());
    }

    @Test
    public void findAllByActiveTrue__Should_Return_An_Empty_Category_List_When_There_Is_No_Category_Active() {
        Category devops = CategoryBuilder.categoryDevops("Dev-Ops", "devops", false);
        categoryRepository.save(devops);

        List<Category> activeCategories = categoryRepository.findAllByActiveTrue();

        assertEquals(0, activeCategories.size());
        assertTrue(activeCategories.isEmpty());
    }

    @Test
    public void findAllByOrderByOrderInSystem() {
        Category backEnd = CategoryBuilder.categoryBackEnd("Back-End", "backend", true);
        Category frontEnd = CategoryBuilder.categoryFrontEnd("Front-End", "frontend", true);
        Category devops = CategoryBuilder.categoryDevops("Dev-Ops", "devops", false);
        categoryRepository.saveAll(Arrays.asList(backEnd, frontEnd, devops));

        List<Category> allSubcategoryOrderedByOrderInSystem = categoryRepository.findAllByOrderByOrderInSystem();

        assertNotNull(allSubcategoryOrderedByOrderInSystem);
        assertEquals("devops", allSubcategoryOrderedByOrderInSystem.get(0).getCode());
        assertEquals(1, allSubcategoryOrderedByOrderInSystem.get(0).getOrderInSystem());
        assertEquals("frontend", allSubcategoryOrderedByOrderInSystem.get(1).getCode());
        assertEquals(2, allSubcategoryOrderedByOrderInSystem.get(1).getOrderInSystem());
        assertEquals("backend", allSubcategoryOrderedByOrderInSystem.get(2).getCode());
        assertEquals(3, allSubcategoryOrderedByOrderInSystem.get(2).getOrderInSystem());
    }

    @Test
    public void findAllByOrderByOrderInSystem__Should_Return_An_Empty_List_When_There_Is_No_Category() {
        List<Category> categories = categoryRepository.findAllByOrderByOrderInSystem();

        assertEquals(0, categories.size());
        assertTrue(categories.isEmpty());
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

        List<CategoryReportProjection> categoriesAndNumberOfCourses = categoryRepository.findCategoryAndNumberOfCourses();

        assertEquals("Back-End", categoriesAndNumberOfCourses.get(0).getCategoryName());
        assertEquals(3, categoriesAndNumberOfCourses.get(0).getQuantityCoursesFromCategory());
        assertEquals("Front-End", categoriesAndNumberOfCourses.get(1).getCategoryName());
        assertEquals(1, categoriesAndNumberOfCourses.get(1).getQuantityCoursesFromCategory());
        assertEquals("DevOps", categoriesAndNumberOfCourses.get(2).getCategoryName());
        assertEquals(0, categoriesAndNumberOfCourses.get(2).getQuantityCoursesFromCategory());
    }

    @Test
    public void getCategoriesWithSubcategoryActiveAndVisibleCourses() {
        Category backEnd = CategoryBuilder.categoryBackEnd("Back-End", "backend", true);
        Category frontEnd = CategoryBuilder.categoryFrontEnd("Front-End", "frontend", true);
        categoryRepository.saveAll(Arrays.asList(backEnd, frontEnd));
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

        List<Category> categoriesWithActiveSubcategoryAndPublicCourses = categoryRepository.getCategoriesWithActiveSubcategoryAndPublicCourses();

        assertEquals("frontend", categoriesWithActiveSubcategoryAndPublicCourses.get(0).getCode());
        assertEquals("backend", categoriesWithActiveSubcategoryAndPublicCourses.get(1).getCode());
    }

    @Test
    public void getCategoriesWithSubcategoryActiveAndVisibleCourses__Should_Return_An_Empty_Array_When_There_Is_No_Category() {
        List<Category> categoriesWithActiveSubcategoryAndPublicCourses = categoryRepository.getCategoriesWithActiveSubcategoryAndPublicCourses();

        assertEquals(0, categoriesWithActiveSubcategoryAndPublicCourses.size());
        assertTrue(categoriesWithActiveSubcategoryAndPublicCourses.isEmpty());
    }

    @Test
    public void getCategoriesWithSubcategoryActiveAndVisibleCourses__Should_Return_An_Empty_Array_When_There_Is_Category_With_Inactive_Subcategory_And_Private_Course() {
        Category backEnd = CategoryBuilder.categoryBackEnd("Back-End", "backend", true);
        categoryRepository.save(backEnd);
        SubCategory subcategoryJava = SubcategoryBuilder.subCategoryJava(backEnd, "Java", "java", false);
        subCategoryRepository.save(subcategoryJava);
        Course courseJava = CourseBuilder.courseJava(subcategoryJava, "Java e Sintaxe", "javasintaxe", "Cleb Paulo", Status.PRIVATE);
        courseRepository.save(courseJava);

        List<Category> categoriesWithActiveSubcategoryAndPublicCourses = categoryRepository.getCategoriesWithActiveSubcategoryAndPublicCourses();
        assertEquals(0, categoriesWithActiveSubcategoryAndPublicCourses.size());
        assertTrue(categoriesWithActiveSubcategoryAndPublicCourses.isEmpty());
    }

    @Test
    public void getCategoriesWithSubcategoryActiveAndVisibleCourses__Should_Return_An_Empty_Array_When_There_Is_Category_With_Active_Subcategory_And_Private_Course() {
        Category backEnd = CategoryBuilder.categoryBackEnd("Back-End", "backend", true);
        categoryRepository.save(backEnd);
        SubCategory subcategoryJava = SubcategoryBuilder.subCategoryJava(backEnd, "Java", "java", true);
        subCategoryRepository.save(subcategoryJava);
        Course courseJava = CourseBuilder.courseJava(subcategoryJava, "Java e Sintaxe", "javasintaxe", "Cleb Paulo", Status.PRIVATE);
        courseRepository.save(courseJava);

        List<Category> categoriesWithActiveSubcategoryAndPublicCourses = categoryRepository.getCategoriesWithActiveSubcategoryAndPublicCourses();
        assertEquals(0, categoriesWithActiveSubcategoryAndPublicCourses.size());
        assertTrue(categoriesWithActiveSubcategoryAndPublicCourses.isEmpty());
    }

}