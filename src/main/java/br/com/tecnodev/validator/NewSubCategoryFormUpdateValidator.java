package br.com.tecnodev.validator;

import br.com.tecnodev.entities.subCategory.DTO.NewSubCategoryForm;
import br.com.tecnodev.entities.subCategory.DTO.NewSubCategoryFormUpdate;
import br.com.tecnodev.repository.SubCategoryRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NewSubCategoryFormUpdateValidator implements Validator {

    private final SubCategoryRepository subCategoryRepository;

    public NewSubCategoryFormUpdateValidator(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return NewSubCategoryFormUpdate.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NewSubCategoryFormUpdate form = (NewSubCategoryFormUpdate) target;
        if (subCategoryRepository.existsByNameAndIdNot(form.getName(), form.getId())) {
            errors.rejectValue("name", "form.error.same.name");
        }
    }
}
