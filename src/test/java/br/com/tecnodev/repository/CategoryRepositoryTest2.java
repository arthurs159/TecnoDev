package br.com.tecnodev.repository;

import br.com.tecnodev.entities.category.Category;
import br.com.tecnodev.repository.util.Builder.ProgramDatabaseMotherTest;
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
public class CategoryRepositoryTest2 {

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
    public void findByCodeShouldReturnAnOptionalEmptyWhenCodeNotExists() {
        String code = "nonExistingCode";
        Optional<Category> category = categoryRepository.findByCode(code);

        assertTrue(category.isEmpty());
    }

//    @Test
//    public void report() {
//    }

    @Test
    public void getCategoriesWithSubcategoryActiveAndVisibleCourses() {
        List<Category> categoriesVisibleSubAndCourse = categoryRepository.getCategoriesWithSubcategoryActiveAndVisibleCourses();
//        System.out.println(categoriesVisibleSubAndCourse);
        assertEquals("backend", categoriesVisibleSubAndCourse.get(0).getCode());
    }
}