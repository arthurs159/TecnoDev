package br.com.tecnodev.validator;

import br.com.tecnodev.entities.course.DTO.NewCourseFormUpdate;
import br.com.tecnodev.repository.CourseRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NewCourseFormUpdateValidator implements Validator {

    private final CourseRepository courseRepository;

    public NewCourseFormUpdateValidator(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return NewCourseFormUpdate.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NewCourseFormUpdate form = (NewCourseFormUpdate) target;
        if (courseRepository.existsByNameAndIdNot(form.getName(), form.getId())) {
            errors.rejectValue("name", "form.error.same.name");
        }
    }
}
