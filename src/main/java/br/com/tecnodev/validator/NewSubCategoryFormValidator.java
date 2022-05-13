package br.com.tecnodev.validator;

import br.com.tecnodev.entities.subCategory.DTO.NewSubCategoryForm;
import br.com.tecnodev.repository.SubCategoryRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NewSubCategoryFormValidator implements Validator {

    private final SubCategoryRepository subCategoryRepository;

    public NewSubCategoryFormValidator(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return NewSubCategoryForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NewSubCategoryForm form = (NewSubCategoryForm) target;
        if (subCategoryRepository.existsByName(form.getName())) {
            errors.rejectValue("name", "form.error.same.name");
        }

        if (subCategoryRepository.existsByCode(form.getCode())) {
            errors.rejectValue("code", "form.error.same.code");
        }
    }
}
