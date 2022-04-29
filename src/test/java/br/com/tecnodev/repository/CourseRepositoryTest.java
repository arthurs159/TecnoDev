package br.com.tecnodev.repository;

import br.com.tecnodev.entities.category.Category;
import br.com.tecnodev.entities.course.Course;
import br.com.tecnodev.entities.course.Status;
import br.com.tecnodev.entities.subCategory.SubCategory;
import br.com.tecnodev.projections.InstructorReportProjection;
import br.com.tecnodev.repository.util.Builder.CategoryBuilder;
import br.com.tecnodev.repository.util.Builder.CourseBuilder;
import br.com.tecnodev.repository.util.Builder.SubcategoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
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

    @Test
    void getInstructorWithMoreCourses() {
        Category backEnd = CategoryBuilder.categoryBackEnd("Back-End", "backend", true);
        Category frontEnd = CategoryBuilder.categoryFrontEnd("Front-End", "frontend", true);
        categoryRepository.saveAll(Arrays.asList(backEnd, frontEnd));
        SubCategory subcategoryJava = SubcategoryBuilder.subCategoryJava(backEnd, "Java", "java", true);
        SubCategory subcategoryJavaScript = SubcategoryBuilder.subCategoryJs(frontEnd, "JavaScript", "javascript", true);
        SubCategory subCategoryPython = SubcategoryBuilder.subCategoryPython(backEnd, "Python", "python", false);
        subCategoryRepository.saveAll(Arrays.asList(subcategoryJava, subcategoryJavaScript, subCategoryPython));
        Course courseJava = CourseBuilder.courseJava(subcategoryJava, "Java e Sintaxe", "javasintaxe", "Cleb Paulo", Status.PUBLIC);
        Course courseJpa = CourseBuilder.courseJpa(subcategoryJava, "JPA", "jpa", "Cleb Paulo", Status.PUBLIC);
        Course coursePython = CourseBuilder.coursePython(subCategoryPython, "Python", "py", "Cleb Paulo", Status.PRIVATE);
        Course courseAngular = CourseBuilder.courseAngular(subcategoryJavaScript, "Angular", "angular", "Paulo Silva", Status.PUBLIC);
        courseRepository.saveAll(Arrays.asList(courseJava, courseJpa, coursePython, courseAngular));

        InstructorReportProjection instructorWithMoreCourses = courseRepository.getInstructorWithMoreCourses();

        assertEquals("Cleb Paulo", instructorWithMoreCourses.getInstructorName());
        assertEquals(3, instructorWithMoreCourses.getQuantityOfCourses());
    }

    @Test
    void findAllBySubCategory() {
        Category backEnd = CategoryBuilder.categoryBackEnd("Back-End", "backend", true);
        categoryRepository.save(backEnd);
        SubCategory subcategoryJava = SubcategoryBuilder.subCategoryJava(backEnd, "Java", "java", true);
        SubCategory subCategoryPython = SubcategoryBuilder.subCategoryPython(backEnd, "Python", "python", false);
        subCategoryRepository.saveAll(Arrays.asList(subcategoryJava, subCategoryPython));
        Course courseJava = CourseBuilder.courseJava(subcategoryJava, "Java e Sintaxe", "javasintaxe", "Cleb Paulo", Status.PUBLIC);
        Course courseJpa = CourseBuilder.courseJpa(subcategoryJava, "JPA", "jpa", "Cleb Paulo", Status.PUBLIC);
        Course coursePython = CourseBuilder.coursePython(subCategoryPython, "Python", "py", "Cleb Paulo", Status.PRIVATE);
        courseRepository.saveAll(Arrays.asList(courseJava, courseJpa, coursePython));

        String subcategoryCode = "java";
        PageRequest pageable = PageRequest.of(0, 5);

        Optional<SubCategory> subcategoryByCode = subCategoryRepository.findSubcategoryByCode(subcategoryCode);

        Page<Course> allBySubCategoryPaged = courseRepository.findAllBySubCategory(subcategoryByCode.get(), pageable);

        assertEquals(2, allBySubCategoryPaged.getTotalElements());
        assertEquals("javasintaxe", allBySubCategoryPaged.getContent().get(0).getCode());
        assertEquals("jpa", allBySubCategoryPaged.getContent().get(1).getCode());
    }

    @Test
    void findAllBySubCategory__Should_return_Nothing_When_Subcategory_Has_No_Course() {
        Category backEnd = CategoryBuilder.categoryBackEnd("Back-End", "backend", true);
        categoryRepository.save(backEnd);
        SubCategory subCategoryMobile = SubcategoryBuilder.subCategoryMobile(backEnd, "Mobile", "mobile", true);
        subCategoryRepository.save(subCategoryMobile);

        String subcategoryCode = "mobile";
        Optional<SubCategory> subcategoryByCode = subCategoryRepository.findSubcategoryByCode(subcategoryCode);

        Page<Course> allBySubCategoryPaged = courseRepository.findAllBySubCategory(subcategoryByCode.get(), Pageable.unpaged());

        assertEquals(0, allBySubCategoryPaged.getTotalElements());
        assertTrue(allBySubCategoryPaged.isEmpty());
    }

    @Test
    void findByCode() {
        Category backEnd = CategoryBuilder.categoryBackEnd("Back-End", "backend", true);
        categoryRepository.save(backEnd);
        SubCategory subcategoryJava = SubcategoryBuilder.subCategoryJava(backEnd, "Java", "java", true);
        subCategoryRepository.save(subcategoryJava);
        Course courseJava = CourseBuilder.courseJava(subcategoryJava, "Java e Sintaxe", "javasintaxe", "Cleb Paulo", Status.PUBLIC);
        courseRepository.save(courseJava);

        String courseCode = "javasintaxe";
        Optional<Course> course = courseRepository.findByCode(courseCode);

        assertTrue(course.isPresent());
        assertEquals(courseCode, course.get().getCode());
    }

    @Test
    void findByCode__Should_Return_Nothing_When_Use_NonExisting_Code() {
        String nonExistingCode = "nonExistingCode";
        Optional<Course> course = courseRepository.findByCode(nonExistingCode);

        assertTrue(course.isEmpty());
    }
}