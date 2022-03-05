import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tecnodev.category.Category;
import tecnodev.subCategory.SubCategory;

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

    @Test
    void subCategoryShouldThrowIllegalExceptionWhenNameIsEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> new SubCategory("", "java", category));
    }

    @Test
    void subCategoryShouldThrowIllegalExceptionWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new SubCategory(null, "java", category));
    }

    @Test
    void subCategoryShouldInstantiateWhenCodeHasOnlyNumbers() {
        assertDoesNotThrow(
                () -> new SubCategory("Java", "1243", category));
    }

    @Test
    void subCategoryShouldThrowIllegalExceptionWhenCodeHasAccent() {
        assertThrows(IllegalArgumentException.class,
                () -> new SubCategory("Java", "jává", category));
    }

    @Test
    void subCategoryShouldNotThrowAnythingWhenCodeHasATrace() {
        assertDoesNotThrow(
                () -> new SubCategory("Java", "12-58z", category));
    }

    @Test
    void subCategoryShouldThrowIllegalExceptionWhenCodeIsEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> new SubCategory("Java", "", category));
    }

    @Test
    void subCategoryShouldThrowIllegalExceptionWhenCodeHasUpperCaseLetter() {
        assertThrows(IllegalArgumentException.class,
                () -> new SubCategory("Java", "JAVA", category));
    }

    @Test
    void subCategoryShouldThrowIllegalExceptionWhenCodeHasSpecialCharacters() {
        assertThrows(IllegalArgumentException.class,
                () -> new SubCategory("Java", "j@a&v*", category));
    }

    @Test
    void subCategoryShouldThrowIllegalExceptionWhenCodeHasSpace() {
        assertThrows(IllegalArgumentException.class,
                () -> new SubCategory("Java", "j a v a", category));
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

    @Test
    void hasDescriptionShouldReturnFalseIfDescriptionIsNull() {
        SubCategory subcat = new SubCategory("Java", "java", 1, null, true, category);
        assertFalse(subcat.hasDescription());
    }

    @Test
    void hasDescriptionShouldReturnFalseIfDescriptionIsEmpty() {
        SubCategory subcat = new SubCategory("Java", "java", 1, "", true, category);
        assertFalse(subcat.hasDescription());
    }

    @Test
    void getCategoryCodeShouldReturnTheCodeFromCategory() {
        SubCategory subcat = new SubCategory("Java", "java", 1, "descrição", true, category);
        assertEquals(category.getCode(), subcat.getCategoryCode());
    }

}