import org.junit.jupiter.api.Test;
import tecnodev.category.Category;
import tecnodev.course.Course;
import tecnodev.subCategory.SubCategory;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static tecnodev.category.Category.*;

public class CategoryTest {
    // TODO    CHECAR SE É PARA ADICIONAR NO VALIDATOR O NULL DO CODE!!!!
    @Test
    void categoryShouldInstantiateWhenDataIsCorrect() {
        assertDoesNotThrow(
                () -> new Category("Programação", "programacao"));
    }

    @Test
    void categoryShouldThrowIllegalExceptionWhenNameIsEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> new Category("", "programacao"));
    }

    @Test
    void categoryShouldThrowIllegalExceptionWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Category(null, "programacao"));
    }

    @Test
    void categoryShouldThrowIllegalExceptionWhenCodeIsEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> new Category("Programação", ""));
    }

    @Test
    void categoryShouldInstantiateWhenCodeHasOnlyNumbers() {
        assertDoesNotThrow(
                () -> new Category("Java", "1243"));
    }

    @Test
    void categoryShouldThrowIllegalExceptionWhenCodeHasUppercaseLetter() {
        assertThrows(IllegalArgumentException.class,
                () -> new Category("Programação", "ProGrAMaCaO"));
    }

    @Test
    void categoryShouldThrowIllegalExceptionWhenCodeHasSpace() {
        assertThrows(IllegalArgumentException.class,
                () -> new Category("Programação", "pro grama cao"));
    }

    @Test
    void categoryShouldThrowIllegalExceptionWhenCodeHasSpecialCharacters() {
        assertThrows(IllegalArgumentException.class,
                () -> new Category("Programação", "pr*og&ra%ma#cao"));
    }

    @Test
    void categoryShouldThrowIllegalExceptionWhenCodeHasAccent() {
        assertThrows(IllegalArgumentException.class,
                () -> new Category("Programação", "prógrámáçãó"));
    }

    @Test
    void categoryShouldNotThrowAnythingWhenCodeHasHyphen() {
        assertDoesNotThrow(
                () -> new Category("Programação", "12-58z"));
    }

    @Test
    void categoryShouldThrowIllegalExceptionWhenColorCodeDoesNotHaveHexadecimalStandard() {
        assertThrows(IllegalArgumentException.class,
                () -> new Category("Programação", "programacao", 1,
                        "descrição", true, "google.com", "321"));
    }

    @Test
    void categoryShouldThrowNothingWhenColorCodeHasHexadecimalStandard() {
        assertDoesNotThrow(
                () -> new Category("Programação", "programacao", 1,
                "descrição", true, "google.com", "#0B499D"));
    }

    @Test
    void categoryShouldReturnTheNumberOfCourseFromCategory() {
        Category cat = new Category("Programação", "programacao");
        SubCategory subCat = new SubCategory("Java", "java", cat);
        Course course = new Course("java persistência", "java", 12, "Paulo",  subCat);
        List<Course> list = Arrays.asList(course);

        assertEquals(1, numbersOfCourseFromCategory(list, subCat.getCategoryCode()));
    }

    @Test
    void categoryShouldReturnQuantityOfHoursFromCategory() {
        Category cat = new Category("Programação", "programacao");
        SubCategory subCat = new SubCategory("Java", "java", cat);
        Course course = new Course("java persistência", "java", 12, "Paulo",  subCat);
        List<Course> list = Arrays.asList(course);

        assertEquals(course.getEstimatedTimeInHours(), quantityHoursFromCategory(list, subCat.getCategoryCode()));
    }

}
