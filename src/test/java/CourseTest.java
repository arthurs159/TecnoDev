import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import tecnodev.category.Category;
import tecnodev.course.Course;
import tecnodev.subCategory.SubCategory;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    private SubCategory subCategory;
    private Category category;

    @BeforeEach
    public void instantiateCategory() {
        this.category = new Category("Programação", "programacao");
        this.subCategory = new SubCategory("Java", "java", category);
    }

    @Test
    void courseShouldInstantiateWhenDataIsCorrect() {
        assertDoesNotThrow(() -> new Course("Java", "1234", 12, "Paulo", subCategory));
    }

    @ParameterizedTest
    @EmptySource
    void CourseShouldThrowIllegalExceptionWhenNameCodeAndTeacherAreEmpty(String input) {
        assertThrows(IllegalArgumentException.class, () -> new Course(input, "1234", 12, "Paulo", subCategory));

        assertThrows(IllegalArgumentException.class, () -> new Course("Java", input, 12, "Paulo", subCategory));

        assertThrows(IllegalArgumentException.class, () -> new Course("Java", "java", 12, input, subCategory));
    }

    @ParameterizedTest
    @NullSource
    void CourseShouldThrowIllegalExceptionWhenNameCodeAndTeacherAreNull(String input) {
        assertThrows(NullPointerException.class, () -> new Course(input, "1234", 12, "Paulo", subCategory));

        assertThrows(NullPointerException.class, () -> new Course("Java", input, 12, "Paulo", subCategory));

        assertThrows(NullPointerException.class, () -> new Course("Java", "java", 12, input, subCategory));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 7, 15, 20})
    void courseShouldNotThrowAnythingWhenEstimatedTimeIsBetweenOneAndTwenty(int input) {
        assertDoesNotThrow(() -> new Course("Java", "java", input, "Paulo", subCategory));
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, -1, 21, 25, 30})
    void subCategoryShouldThrowIllegalExceptionWhenEstimatedTimeIsAboveOrBelowRecommendation(int input) {
        assertThrows(IllegalArgumentException.class, () -> new Course("Java", "java", input, "Paulo", subCategory));
    }

    @ParameterizedTest
    @CsvSource({"jává", "JAVA", "j@a&v*", "j a v a"})
    void CourseShouldNotThrowIllegalExceptionWhenCodeDoesNotHaveRegexStandard(String input) {
        assertThrows(IllegalArgumentException.class, () -> new Course("Java", input, 12, "Paulo", subCategory));
    }

    @ParameterizedTest
    @CsvSource({"1234", "12-58z", "java", "j-a-v-a"})
    void courseShouldInstantiateWhenCodeHasRegexStandard(String input) {
        assertDoesNotThrow(() -> new Course("Java", input, 12, "Paulo", subCategory));
    }

    @Test
    void courseShouldThrowIllegalExceptionWhenSubCategoryIsNull() {
        assertThrows(NullPointerException.class, () -> new Course("Java", "java", 20, "Paulo", null));
    }

    @Test
    void getSubCategoryCodeShouldReturnTheCodeFromSubCategory() {
        Course course = new Course("Java", "java", 20, "Paulo", subCategory);
        assertEquals(subCategory.getCode(), course.getSubCategoryCode());
    }

}
