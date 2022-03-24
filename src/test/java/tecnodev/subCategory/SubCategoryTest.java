package tecnodev.subCategory;

import com.br.tecnodev.tecnodev.subCategory.SubCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import com.br.tecnodev.tecnodev.category.Category;

import static org.junit.jupiter.api.Assertions.*;

public class SubCategoryTest {

    private Category category;

    @BeforeEach
    public void instantiateCategory() {
        this.category = new Category("Programação", "programacao");
    }

    @Test
    void subCategoryShouldInstantiateWhenDataIsCorrect() {
        assertDoesNotThrow(() -> new SubCategory("Java", "java", category));
    }

    @ParameterizedTest
    @EmptySource
    void subCategoryShouldThrowIllegalExceptionWhenNameAndCodeAreEmpty(String input) {
        assertThrows(IllegalArgumentException.class,
                () -> new SubCategory(input, "java", category));

        assertThrows(IllegalArgumentException.class,
                () -> new SubCategory("Java", input, category));
    }

    @ParameterizedTest
    @NullSource
    void subCategoryShouldThrowNullPointerWhenNameCodeAndCategoryAreNull(String input) {
        assertThrows(NullPointerException.class,
                () -> new SubCategory(input, "java", category));

        assertThrows(NullPointerException.class,
                () -> new SubCategory("Programação", input, category));
    }

    @ParameterizedTest
    @CsvSource({"1234", "12-58z", "codigo", "co-di-go"})
    void subCategoryShouldInstantiateWhenCodeHasRegexStandard(String input) {
        assertDoesNotThrow(
                () -> new SubCategory("Java", input, category));
    }

    @ParameterizedTest
    @CsvSource({"jává", "JAVA", "j@a&v*", "j a v a"})
    void subCategoryShouldNotInstantiateWhenCodeDoesNotHaveRegexStandard(String input) {
        assertThrows(IllegalArgumentException.class,
                () -> new SubCategory("Java", input, category));
    }

    @Test
    void subCategoryShouldThrowIllegalExceptionWhenCategoryIsNull() {
        assertThrows(NullPointerException.class,
                () -> new SubCategory("Java", "java", null));
    }

    @Test
    void hasDescriptionShouldReturnTrueIfDescriptionIsNotNullAndNotEmpty() {
        SubCategory subcat = new SubCategory("Java", "java", 1, "Descrição não vazia", true, category);
        assertTrue(subcat.hasDescription());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void hasDescriptionShouldReturnFalseIfDescriptionIsNullOrEmpty(String input) {
        SubCategory subcat = new SubCategory("Java", "java", 1, input, true, category);
        assertFalse(subcat.hasDescription());
    }

    @Test
    void getCategoryCodeShouldReturnTheCodeFromCategory() {
        SubCategory subcat = new SubCategory("Java", "java", 1, "descrição", true, category);
        assertEquals(category.getCode(), subcat.getCategoryCode());
    }

}