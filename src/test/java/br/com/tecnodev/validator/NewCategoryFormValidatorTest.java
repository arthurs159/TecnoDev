package br.com.tecnodev.validator;

import br.com.tecnodev.entities.category.DTO.NewCategoryForm;
import br.com.tecnodev.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NewCategoryFormValidatorTest {

    private CategoryRepository repository;
    private NewCategoryFormValidator validator;
    private Errors errors;
    private NewCategoryForm form;


    @BeforeEach
    void setUp() {
        repository = mock(CategoryRepository.class);
        validator = new NewCategoryFormValidator(repository);
        errors = mock(Errors.class);
        form = new NewCategoryForm();
    }

    @Test
    void when_name_exists_should_return_an_error() {
        when(repository.existsByName("Programação")).thenReturn(true);
        form.setName("Programação");

        validator.validate(form, errors);
    }

    @Test
    void when_code_exists_should_return_an_error() {
        when(repository.existsByCode("programacao")).thenReturn(true);
        form.setCode("programacao");

        validator.validate(form, errors);
    }

    @Test
    void when_name_do_not_exists_should_not_return_an_error() {
        when(repository.existsByName("UI/UX")).thenReturn(false);
        form.setName("UI/UX");

        validator.validate(form, errors);
    }

    @Test
    void when_code_do_not_exists_should_not_return_an_error() {
        when(repository.existsByCode("ui-ux")).thenReturn(false);
        form.setName("ui-ux");

        validator.validate(form, errors);
    }

}