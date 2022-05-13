package br.com.tecnodev.entities.category.DTO;

import br.com.tecnodev.entities.category.Category;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
@Getter
@Setter
@AllArgsConstructor
public class NewCategoryForm {

    @NotBlank(message = "{form.error.name}")
    private String name;
    @NotBlank(message = "{form.error.code}")
    @Pattern(regexp = "[a-z0-9-]+", message = "{form.error.code.regex}")
    private String code;
    private String description;
    private String studyGuide;
    private boolean active;
    private Integer orderInSystem;
    private String imageUrl;
    @Pattern(regexp = "^#([a-fA-F0-9]){3}(([a-fA-F0-9]){3})?$", message = "{form.error.color.regex}")
    private String colorCode;

    public NewCategoryForm() {}

    public Category toEntity() {
        return new Category(name, code, orderInSystem, description, studyGuide, active, imageUrl, colorCode);
    }
}
