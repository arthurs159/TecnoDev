import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        assertDoesNotThrow(
                () -> new Course("Java", "1234", 12, "Paulo", subCategory));
    }

    @Test
    void subCategoryShouldThrowIllegalExceptionWhenNameIsEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course("", "java", 12, "Paulo", subCategory));
    }

    @Test
    void subCategoryShouldThrowIllegalExceptionWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course(null, "java", 12, "Paulo", subCategory));
    }

    @Test
    void subCategoryShouldThrowIllegalExceptionWhenTeacherIsEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course("Java", "java", 12, "", subCategory));
    }

    @Test
    void subCategoryShouldThrowIllegalExceptionWhenTeacherIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course("Java", "java", 12, null, subCategory));
    }

    @Test
    void subCategoryShouldThrowIllegalExceptionWhenTimeIntervalIsAboveRecommendation() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course("Java", "java", 22, "Paulo", subCategory));
    }

    @Test
    void subCategoryShouldThrowIllegalExceptionWhenTimeIntervalIsBelowRecommendation() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course("Java", "java", -2, "Paulo", subCategory));
    }

    @Test
    void subCategoryShouldThrowNothingWhenTimeIntervalIsBetweenOneAndTwenty() {
        assertDoesNotThrow(
                () -> new Course("Java", "java", 15, "Paulo", subCategory));
    }

    @Test
    void subCategoryShouldThrowIllegalExceptionWhenCodeIsEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course("Java", "", 20, "Paulo", subCategory));
    }

    @Test
    void subCategoryShouldThrowIllegalExceptionWhenCodeHasSpace() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course("Java", "j a v a", 20, "Paulo", subCategory));
    }

    @Test
    void subCategoryShouldThrowIllegalExceptionWhenCodeHasSpecialCharacters() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course("Java", "j@a*v&a", 20, "Paulo", subCategory));
    }

    @Test
    void categoryShouldThrowIllegalExceptionWhenCodeHasAccent() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course("Java", "jává", 20, "Paulo", subCategory));
    }

    @Test
    void categoryShouldNotThrowAnythingWhenCodeHasATrace() {
        assertDoesNotThrow(
                () -> new Course("Java", "j-a-v-a", 20, "Paulo", subCategory));
    }

    @Test
    void subCategoryShouldThrowIllegalExceptionWhenSubCategoryIsNull() {
        assertThrows(NullPointerException.class,
                () -> new Course("Java", "java", 20, "Paulo", null));
    }

    @Test
    void getSubCategoryCodeShouldReturnTheCodeFromSubCategory(){
        Course course = new Course("Java", "java", 20, "Paulo", subCategory);
        assertEquals(subCategory.getCode(), course.getSubCategoryCode());
    }

}
