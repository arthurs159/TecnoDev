package br.com.tecnodev.validator;

import br.com.tecnodev.entities.course.DTO.NewCourseForm;
import br.com.tecnodev.repository.CourseRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NewCourseFormValidator implements Validator {

    private final CourseRepository courseRepository;

    public NewCourseFormValidator(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return NewCourseForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NewCourseForm form = (NewCourseForm) target;
        if (courseRepository.existsByName(form.getName())) {
            errors.rejectValue("name", "form.error.same.name");
        }

        if (courseRepository.existsByCode(form.getCode())) {
            errors.rejectValue("code", "form.error.same.code");
        }
    }
}
