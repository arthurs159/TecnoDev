package br.com.tecnodev.repository;

import br.com.tecnodev.entities.course.Course;
import br.com.tecnodev.entities.subCategory.SubCategory;
import br.com.tecnodev.projections.InstructorReportProjection;
import br.com.tecnodev.repository.util.ProgramDatabaseMotherTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CourseRepositoryTest {

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
    void getInstructorWithMoreCourses() {
        InstructorReportProjection instructorWithMoreCourses = courseRepository.getInstructorWithMoreCourses();

        assertEquals("Cleb Paulo", instructorWithMoreCourses.getInstructorName());
        assertEquals(3, instructorWithMoreCourses.getQuantityOfCourses());
    }

    @Test
    void findAllBySubCategory() {
        String subcategoryCode = "java";
        Optional<SubCategory> subcategoryByCode = subCategoryRepository.findSubcategoryByCode(subcategoryCode);

        Page<Course> allBySubCategoryPaged = courseRepository.findAllBySubCategory(subcategoryByCode.get(), Pageable.unpaged());

        assertEquals(2, allBySubCategoryPaged.getTotalElements());
        assertEquals("javasintax", allBySubCategoryPaged.getContent().get(0).getCode());
        assertEquals("jpa", allBySubCategoryPaged.getContent().get(1).getCode());
    }

    @Test
    void findAllBySubCategory__Should_return_Nothing_When_Subcategory_Has_No_Course() {
        String subcategoryCode = "mobile";
        Optional<SubCategory> subcategoryByCode = subCategoryRepository.findSubcategoryByCode(subcategoryCode);

        Page<Course> allBySubCategoryPaged = courseRepository.findAllBySubCategory(subcategoryByCode.get(), Pageable.unpaged());

        assertEquals(0, allBySubCategoryPaged.getTotalElements());
        assertTrue(allBySubCategoryPaged.isEmpty());
    }

    @Test
    void findByCode() {
        String courseCode = "javasintax";
        Optional<Course> course = courseRepository.findByCode(courseCode);

        assertTrue(course.isPresent());
        assertEquals("javasintax", course.get().getCode());
    }

    @Test
    void findByCode__Should_Return_Nothing_When_Use_NonExisting_Code() {
        String nonExistingCode = "nonExistingCode";
        Optional<Course> course = courseRepository.findByCode(nonExistingCode);

        assertTrue(course.isEmpty());
    }
}