package br.com.tecnodev.validator;

import br.com.tecnodev.entities.subCategory.DTO.NewSubCategoryForm;
import br.com.tecnodev.repository.SubCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

class NewSubCategoryFormValidatorTest {

    private SubCategoryRepository repository;
    private NewSubCategoryFormValidator validator;
    private Errors errors;
    private NewSubCategoryForm form;

    @BeforeEach
    void setUp() {
        repository = mock(SubCategoryRepository.class);
        when(repository.existsByName("Java")).thenReturn(true);
        when(repository.existsByCode("java")).thenReturn(true);
        validator = new NewSubCategoryFormValidator(repository);
        errors = mock(Errors.class);
        form = new NewSubCategoryForm();
    }

    @Test
    void when_name_exists_should_return_an_error() {
        form.setName("Java");
        validator.validate(form, errors);

        verify(errors).rejectValue("name", "form.error.same.name");
    }

    @Test
    void when_code_exists_should_return_an_error() {
        form.setCode("java");
        validator.validate(form, errors);

        verify(errors).rejectValue("code", "form.error.same.code");
    }

    @Test
    void when_name_do_not_exists_should_not_return_an_error() {
        validator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void when_code_do_not_exists_should_not_return_an_error() {
        validator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}