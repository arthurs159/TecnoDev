package br.com.tecnodev.validator;

import br.com.tecnodev.entities.category.DTO.NewCategoryFormUpdate;
import br.com.tecnodev.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.*;

class NewCategoryFormUpdateValidatorTest {
    private CategoryRepository repository;
    private NewCategoryFormUpdateValidator validator;
    private Errors errors;
    private NewCategoryFormUpdate formUpdate;

    @BeforeEach
    void setUp() {
        repository = mock(CategoryRepository.class);
        when(repository.existsByNameAndIdNot(eq("Programação"), not(eq(1L)))).thenReturn(true);
        when(repository.existsByCodeAndIdNot(eq("programacao"), not(eq(1L)))).thenReturn(true);
        validator = new NewCategoryFormUpdateValidator(repository);
        errors = mock(Errors.class);
        formUpdate = new NewCategoryFormUpdate();
    }

    @Test
    void should_not_have_error_when_name_exists_and_id_is_the_same() {
        formUpdate.setId(1L);
        formUpdate.setName("Programação");
        validator.validate(formUpdate, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void should_not_have_error_when_code_exists_and_id_is_the_same() {
        formUpdate.setId(1L);
        formUpdate.setCode("programacao");
        validator.validate(formUpdate, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void should_return_error_when_name_exists_and_id_is_different() {
        formUpdate.setId(15L);
        formUpdate.setName("Programação");
        validator.validate(formUpdate, errors);

        verify(errors).rejectValue("name", "form.error.same.name");
    }

    @Test
    void should_return_error_when_code_exists_and_id_is_different() {
        formUpdate.setId(15L);
        formUpdate.setCode("programacao");
        validator.validate(formUpdate, errors);

        verify(errors).rejectValue("code", "form.error.same.code");
    }

    @Test
    void should_not_return_error_when_name_not_exists_for_id() {
        formUpdate.setId(1L);
        formUpdate.setName("Programação");
        validator.validate(formUpdate, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void should_not_return_error_when_code_not_exists_for_id() {
        formUpdate.setId(1L);
        formUpdate.setCode("programacao");
        validator.validate(formUpdate, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void should_not_return_error_when_name_not_exist_and_its_a_different_id() {
        formUpdate.setId(15L);
        formUpdate.setName("DevOps");
        validator.validate(formUpdate, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void should_not_return_error_when_code_not_exist_and_its_a_different_id() {
        formUpdate.setId(15L);
        formUpdate.setCode("devops");
        validator.validate(formUpdate, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}