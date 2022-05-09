package br.com.tecnodev.entities.category.DTO;

import br.com.tecnodev.entities.category.Category;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class NewCategoryFormUpdate {

    private Long id;
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

    public Category toEntity() {
        Category category = new Category(name, code, orderInSystem, description, studyGuide, active, imageUrl, colorCode);
        category.setId(this.id);
        return category;
    }
}
