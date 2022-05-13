package br.com.tecnodev.validator;

import br.com.tecnodev.entities.category.DTO.NewCategoryForm;
import br.com.tecnodev.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

class NewCategoryFormValidatorTest {

    private CategoryRepository repository;
    private NewCategoryFormValidator validator;
    private Errors errors;
    private NewCategoryForm form;

    @BeforeEach
    void setUp() {
        repository = mock(CategoryRepository.class);
        when(repository.existsByName("Programação")).thenReturn(true);
        when(repository.existsByCode("programacao")).thenReturn(true);
        validator = new NewCategoryFormValidator(repository);
        errors = mock(Errors.class);
        form = new NewCategoryForm();
    }

    @Test
    void when_name_exists_should_return_an_error() {
        form.setName("Programação");
        validator.validate(form, errors);
        verify(errors).rejectValue("name", "form.error.same.name");
    }

    @Test
    void when_code_exists_should_return_an_error() {
        form.setCode("programacao");
        validator.validate(form, errors);
        verify(errors).rejectValue("code", "form.error.same.code");
    }

    @Test
    void when_name_do_not_exists_should_not_return_an_error() {
        form.setName("Devops");
        validator.validate(form, errors);
        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void when_code_do_not_exists_should_not_return_an_error() {
        form.setCode("devops");
        validator.validate(form, errors);
        verify(errors, never()).rejectValue(anyString(), anyString());
    }

}