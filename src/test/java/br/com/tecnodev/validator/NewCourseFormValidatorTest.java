package br.com.tecnodev.validator;

import br.com.tecnodev.entities.course.DTO.NewCourseForm;
import br.com.tecnodev.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

class NewCourseFormValidatorTest {

    private CourseRepository repository;
    private NewCourseFormValidator validator;
    private Errors errors;
    private NewCourseForm form;

    @BeforeEach
    void setUp() {
        repository = mock(CourseRepository.class);
        when(repository.existsByName("JQuery")).thenReturn(true);
        when(repository.existsByCode("jquery")).thenReturn(true);
        validator = new NewCourseFormValidator(repository);
        errors = mock(Errors.class);
        form = new NewCourseForm();
    }

    @Test
    void when_name_exists_should_return_an_error() {
        form.setName("JQuery");
        validator.validate(form, errors);

        verify(errors).rejectValue("name", "form.error.same.name");
    }

    @Test
    void when_code_exists_should_return_an_error() {
        form.setCode("jquery");
        validator.validate(form, errors);

        verify(errors).rejectValue("code", "form.error.same.code");
    }

    @Test
    void when_name_do_not_exists_should_not_return_an_error() {
        form.setCode("Nuvem");
        validator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void when_code_do_not_exists_should_not_return_an_error() {
        form.setCode("nuvem");
        validator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }


}