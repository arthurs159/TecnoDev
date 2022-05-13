package br.com.tecnodev.entities.subCategory.DTO;

import br.com.tecnodev.entities.category.Category;
import br.com.tecnodev.entities.subCategory.SubCategory;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
@Getter
@Setter
public class NewSubCategoryForm {

    @NotBlank(message = "{form.error.name}")
    private String name;
    @NotBlank(message = "{form.error.code}")
    @Pattern(regexp = "[a-z0-9-]+", message = "{form.error.code.regex}")
    private String code;
    private String description;
    private String studyGuide;
    private boolean active;
    private Integer orderInSystem;
    @NotNull(message = "{form.error.category}")
    private Long categoryId;

    public NewSubCategoryForm() {}

    public SubCategory toEntity(Category category) {
        return new SubCategory(name, code, orderInSystem, description, active, category);
    }
}
