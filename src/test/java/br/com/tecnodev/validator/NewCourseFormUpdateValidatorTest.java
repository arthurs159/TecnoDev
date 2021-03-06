package br.com.tecnodev.validator;

import br.com.tecnodev.entities.course.DTO.NewCourseFormUpdate;
import br.com.tecnodev.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class NewCourseFormUpdateValidatorTest {

    private CourseRepository repository;
    private NewCourseFormUpdateValidator validator;
    private Errors errors;
    private NewCourseFormUpdate form;

    @BeforeEach
    void setUp() {
        repository = mock(CourseRepository.class);
        when(repository.existsByNameAndIdNot(eq("GoLang"), not(eq(1L)))).thenReturn(true);
        when(repository.existsByCodeAndIdNot(eq("golang"), not(eq(1L)))).thenReturn(true);
        validator = new NewCourseFormUpdateValidator(repository);
        errors = mock(Errors.class);
        form = new NewCourseFormUpdate();
    }

    @Test
    void should_not_have_error_when_name_exists_and_id_is_the_same() {
        form.setId(1L);
        form.setName("GoLang");

        validator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void should_not_have_error_when_code_exists_and_id_is_the_same() {
        form.setId(1L);
        form.setCode("golang");

        validator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void should_return_error_when_name_exists_and_id_is_different() {
        form.setId(20L);
        form.setName("GoLang");

        validator.validate(form, errors);

        verify(errors).rejectValue("name", "form.error.same.name");
    }

    @Test
    void should_return_error_when_code_exists_and_id_is_different() {
        form.setId(20L);
        form.setCode("golang");

        validator.validate(form, errors);

        verify(errors).rejectValue("code", "form.error.same.code");
    }

    @Test
    void should_not_return_error_when_name_not_exists_for_id() {
        form.setId(1L);
        form.setName("GoLang");

        validator.validate(form, errors);
        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void should_not_return_error_when_code_not_exists_for_id() {
        form.setId(1L);
        form.setCode("golang");

        validator.validate(form, errors);
        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void should_not_return_error_when_name_not_exist_and_its_a_different_id() {
        form.setId(15L);
        form.setName("Elixir");

        validator.validate(form, errors);
        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void should_not_return_error_when_code_not_exist_and_its_a_different_id() {
        form.setId(15L);
        form.setCode("elixir");

        validator.validate(form, errors);
        verify(errors, never()).rejectValue(anyString(), anyString());
    }

}