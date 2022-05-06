package br.com.tecnodev.validator;

import br.com.tecnodev.entities.subCategory.DTO.NewSubCategoryForm;
import br.com.tecnodev.repository.SubCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NewSubCategoryFormValidatorTest {

    private SubCategoryRepository repository;
    private NewSubCategoryFormValidator validator;
    private Errors errors;
    private NewSubCategoryForm form;

    @BeforeEach
    void setUp() {
        repository = mock(SubCategoryRepository.class);
        validator = new NewSubCategoryFormValidator(repository);
        errors = mock(Errors.class);
        form = new NewSubCategoryForm();
    }

    @Test
    void when_name_exists_should_return_an_error() {
        when(repository.existsByName("Java")).thenReturn(true);
        form.setName("Java");

        validator.validate(form, errors);
    }

    @Test
    void when_code_exists_should_return_an_error() {
        when(repository.existsByCode("java")).thenReturn(true);
        form.setCode("java");

        validator.validate(form, errors);
    }

    @Test
    void when_name_do_not_exists_should_not_return_an_error() {
        when(repository.existsByName("Programação orientada a objetos")).thenReturn(false);
        form.setName("Programação orientada a objetos");

        validator.validate(form, errors);
    }

    @Test
    void when_code_do_not_exists_should_not_return_an_error() {
        when(repository.existsByCode("prog-orientada")).thenReturn(false);
        form.setName("prog-orientada");

        validator.validate(form, errors);
    }
}