package br.com.tecnodev.validator;

import br.com.tecnodev.entities.category.DTO.NewCategoryFormUpdate;
import br.com.tecnodev.repository.CategoryRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NewCategoryFormUpdateValidator implements Validator {

    private final CategoryRepository categoryRepository;

    public NewCategoryFormUpdateValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return NewCategoryFormUpdate.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NewCategoryFormUpdate form = (NewCategoryFormUpdate) target;
        if (categoryRepository.existsByNameAndIdNot(form.getName(), form.getId())) {
            errors.rejectValue("name", "form.error.same.name");
        }
    }
}
