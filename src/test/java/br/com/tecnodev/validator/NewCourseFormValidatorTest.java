package br.com.tecnodev.validator;

import br.com.tecnodev.entities.course.DTO.NewCourseForm;
import br.com.tecnodev.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NewCourseFormValidatorTest {

    private CourseRepository repository;
    private NewCourseFormValidator validator;
    private Errors errors;
    private NewCourseForm form;

    @BeforeEach
    void setUp() {
        repository = mock(CourseRepository.class);
        validator = new NewCourseFormValidator(repository);
        errors = mock(Errors.class);
        form = new NewCourseForm();
    }

    @Test
    void when_name_exists_should_return_an_error() {
        when(repository.existsByName("JQuery")).thenReturn(true);
        form.setName("JQuery");

        validator.validate(form, errors);
    }

    @Test
    void when_code_exists_should_return_an_error() {
        when(repository.existsByCode("jquery")).thenReturn(true);
        form.setCode("jquery");

        validator.validate(form, errors);
    }

    @Test
    void when_name_do_not_exists_should_not_return_an_error() {
        when(repository.existsByName(".NET para web")).thenReturn(false);
        form.setName(".NET para web");

        validator.validate(form, errors);
    }

    @Test
    void when_code_do_not_exists_should_not_return_an_error() {
        when(repository.existsByCode("dotnet-para-web")).thenReturn(false);
        form.setName("dotnet-para-web");

        validator.validate(form, errors);
    }


}