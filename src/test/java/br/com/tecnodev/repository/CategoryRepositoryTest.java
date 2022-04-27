package br.com.tecnodev.repository;

import br.com.tecnodev.entities.category.Category;
import br.com.tecnodev.projections.CategoryReportProjection;
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
public class CategoryRepositoryTest {

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
    public void findAllByActiveTrue() {
        List<Category> allByActiveTrue = categoryRepository.findAllByActiveTrue();

        assertEquals(2, allByActiveTrue.size());
        assertEquals("backend", allByActiveTrue.get(0).getCode());
        assertEquals("frontend", allByActiveTrue.get(1).getCode());
    }

    @Test
    public void findAllByOrderByOrderInSystem() {
        List<Category> allByOrderByOrderInSystem = categoryRepository.findAllByOrderByOrderInSystem();

        assertNotNull(allByOrderByOrderInSystem);
        assertEquals("devops", allByOrderByOrderInSystem.get(0).getCode());
        assertEquals("frontend", allByOrderByOrderInSystem.get(1).getCode());
        assertEquals("backend", allByOrderByOrderInSystem.get(2).getCode());
    }

    @Test
    public void findByCode() {
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
    public void findByCodeShouldReturnAnOptionalEmptyWhenCodeNotExists() {
        String code = "nonExistingCode";
        Optional<Category> category = categoryRepository.findByCode(code);

        assertTrue(category.isEmpty());
    }

    @Test
    public void findCategoryAndNumberOfCourses() {
        List<CategoryReportProjection> categoryAndNumberOfCourses = categoryRepository.findCategoryAndNumberOfCourses();

        assertEquals("Back-End", categoryAndNumberOfCourses.get(0).getCategoryName());
        assertEquals(3, categoryAndNumberOfCourses.get(0).getQuantityCoursesFromCategory());
        assertEquals("Front-end", categoryAndNumberOfCourses.get(1).getCategoryName());
        assertEquals(1, categoryAndNumberOfCourses.get(1).getQuantityCoursesFromCategory());
        assertEquals("DevOps", categoryAndNumberOfCourses.get(2).getCategoryName());
        assertEquals(0, categoryAndNumberOfCourses.get(2).getQuantityCoursesFromCategory());
    }

    @Test
    public void getCategoriesWithSubcategoryActiveAndVisibleCourses() {
        List<Category> categoriesVisibleSubAndCourse = categoryRepository.getCategoriesWithActiveSubcategoryAndPublicCourses();

        assertEquals("frontend", categoriesVisibleSubAndCourse.get(0).getCode());
        assertEquals("backend", categoriesVisibleSubAndCourse.get(1).getCode());
    }
}