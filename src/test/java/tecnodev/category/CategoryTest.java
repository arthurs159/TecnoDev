package tecnodev.category;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import br.com.tecnodev.tecnodev.category.Category;
import br.com.tecnodev.tecnodev.course.Course;
import br.com.tecnodev.tecnodev.subCategory.SubCategory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static br.com.tecnodev.tecnodev.category.Category.numbersOfCourseFromCategory;
import static br.com.tecnodev.tecnodev.category.Category.quantityHoursFromCategory;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoryTest {

    private Category programmingCategory;
    private SubCategory javaSubcategory;
    private Course jpaCourse;
    private Course mavenCourse;

    private Category mobileCategory;
    private SubCategory androidSubcategory;
    private Course beginningCourse;

    private List<Course> courseList;

    @BeforeAll
    void setUp() {
        programmingCategory = new Category("Programação", "programacao");
        javaSubcategory = new SubCategory("Java", "java", programmingCategory);
        jpaCourse = new Course("java persistência", "java", 12, "Paulo", javaSubcategory);
        mavenCourse = new Course("Maven", "java", 12, "Rodrigo", javaSubcategory);

        mobileCategory = new Category("Mobile", "mobile");
        androidSubcategory = new SubCategory("Android", "android", mobileCategory);
        beginningCourse = new Course("Iniciando com Mobile", "flutter", 10, "Caio", androidSubcategory);
        courseList = List.of(jpaCourse, mavenCourse, beginningCourse);
    }

    @Test
    void categoryShouldInstantiateWhenDataIsCorrect() {
        assertDoesNotThrow(
                () -> new Category("Programação", "programacao"));
    }

    @ParameterizedTest
    @EmptySource
    void categoryShouldThrowIllegalExceptionWhenNameOrCodeAreEmpty(String input) {
        assertThrows(IllegalArgumentException.class,
                () -> new Category(input, "programacao"));

        assertThrows(IllegalArgumentException.class,
                () -> new Category("Programação", input));
    }

    @ParameterizedTest
    @NullSource
    void categoryShouldThrowNullPointerWhenNameOrCodeAreNull(String input) {
        assertThrows(NullPointerException.class,
                () -> new Category(input, "programacao"));

        assertThrows(NullPointerException.class,
                () -> new Category("Programação", input));
    }

    @ParameterizedTest
    @CsvSource({"ProGrAMaCaO", "pro grama cao", "pr*og&ra%ma#cao", "prógrámáçãó"})
    void categoryShouldNotInstantiateWhenCodeDoesNotHaveRegexStandard(String input) {
        assertThrows(IllegalArgumentException.class,
                () -> new Category("Programação", input));
    }

    @ParameterizedTest
    @CsvSource({"1234", "12-58z", "codigo", "co-di-go"})
    void categoryShouldInstantiateWhenCodeHasRegexStandard(String input) {
        assertDoesNotThrow(
                () -> new Category("Java", input));
    }

    @ParameterizedTest
    @CsvSource({"321", "frts", "f4g5", "FFF"})
    void categoryShouldThrowIllegalExceptionWhenColorCodeDoesNotHaveHexadecimalStandard(String input) {
        assertThrows(IllegalArgumentException.class,
                () -> new Category("Programação", "programacao", 1,
                        "descrição", true, "google.com", input));
    }

    @ParameterizedTest
    @CsvSource({"#527AB0", "#133E79", "#13F500", "#9AEA20"})
    void categoryShouldThrowNothingWhenColorCodeHasHexadecimalStandard(String input) {
        assertDoesNotThrow(
                () -> new Category("Programação", "programacao", 1,
                        "descrição", true, "google.com", input));
    }

    @Test
    void numbersOfCourseFromCategory__should_return_the_number_of_course_from_category() {
        assertEquals(2, numbersOfCourseFromCategory(courseList, javaSubcategory.getCategoryCode()));
        assertEquals(1, numbersOfCourseFromCategory(courseList, androidSubcategory.getCategoryCode()));
    }

    @Test
    void quantityHoursFromCategory__should_return_quantity_of_hours_from_category() {
        assertEquals(24, quantityHoursFromCategory(courseList, javaSubcategory.getCategoryCode()));
        assertEquals(10, quantityHoursFromCategory(courseList, androidSubcategory.getCategoryCode()));
    }

}