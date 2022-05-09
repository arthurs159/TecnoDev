package br.com.tecnodev.entities.category.DTO;

import br.com.tecnodev.entities.category.Category;
import lombok.*;

@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CategoryToListDTO {

    private final Long id;
    private final String name;
    private final String code;
    private final boolean active;

    public CategoryToListDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.code = category.getCode();
        this.active = category.isActive();
    }
}
