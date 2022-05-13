package br.com.tecnodev.validator;

import br.com.tecnodev.entities.category.DTO.NewCategoryForm;
import br.com.tecnodev.repository.CategoryRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NewCategoryFormValidator implements Validator {

    private final CategoryRepository categoryRepository;

    public NewCategoryFormValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return NewCategoryForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NewCategoryForm form = (NewCategoryForm) target;
        if(categoryRepository.existsByName(form.getName())) {
            errors.rejectValue("name", "form.error.same.name");
        }

        if(categoryRepository.existsByCode(form.getCode())) {
            errors.rejectValue("code", "form.error.same.code");
        }
    }
}
