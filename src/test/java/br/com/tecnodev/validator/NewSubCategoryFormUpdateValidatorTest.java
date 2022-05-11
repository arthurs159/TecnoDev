package br.com.tecnodev.validator;

import br.com.tecnodev.entities.subCategory.DTO.NewSubCategoryFormUpdate;
import br.com.tecnodev.repository.SubCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class NewSubCategoryFormUpdateValidatorTest {

    private SubCategoryRepository repository;
    private NewSubCategoryFormUpdateValidator validator;
    private Errors errors;
    private NewSubCategoryFormUpdate formUpdate;

    @BeforeEach
    void setUp() {
        repository = mock(SubCategoryRepository.class);
        when(repository.existsByNameAndIdNot(eq("Java"), not(eq(1L)))).thenReturn(true);
        when(repository.existsByCodeAndIdNot(eq("java"), not(eq(1L)))).thenReturn(true);
        validator = new NewSubCategoryFormUpdateValidator(repository);
        errors = mock(Errors.class);
        formUpdate = new NewSubCategoryFormUpdate();
    }

    @Test
    void should_not_have_error_when_name_exists_and_id_is_the_same() {
        formUpdate.setId(1L);
        formUpdate.setName("Java");

        validator.validate(formUpdate, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void should_not_have_error_when_code_exists_and_id_is_the_same() {
        formUpdate.setId(1L);
        formUpdate.setCode("java");

        validator.validate(formUpdate, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void should_return_error_when_name_exists_and_id_is_different() {
        formUpdate.setId(20L);
        formUpdate.setName("Java");

        validator.validate(formUpdate, errors);

        verify(errors).rejectValue("name", "form.error.same.name");
    }

    @Test
    void should_return_error_when_code_exists_and_id_is_different() {
        formUpdate.setId(20L);
        formUpdate.setCode("java");

        validator.validate(formUpdate, errors);

        verify(errors).rejectValue("code", "form.error.same.code");
    }

    @Test
    void should_not_return_error_when_name_not_exists_for_id() {
        formUpdate.setId(1L);
        formUpdate.setName("JavaScript");

        validator.validate(formUpdate, errors);
        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void should_not_return_error_when_code_not_exists_for_id() {
        formUpdate.setId(1L);
        formUpdate.setCode("javascript");

        validator.validate(formUpdate, errors);
        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void should_not_return_error_when_name_not_exist_and_its_a_different_id() {
        formUpdate.setId(15L);
        formUpdate.setName("Python");

        validator.validate(formUpdate, errors);
        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void should_not_return_error_when_code_not_exist_and_its_a_different_id() {
        formUpdate.setId(15L);
        formUpdate.setCode("python");

        validator.validate(formUpdate, errors);
        verify(errors, never()).rejectValue(anyString(), anyString());
    }

}