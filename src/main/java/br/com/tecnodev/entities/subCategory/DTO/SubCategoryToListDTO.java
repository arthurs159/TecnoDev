package br.com.tecnodev.entities.subCategory.DTO;

import br.com.tecnodev.entities.subCategory.SubCategory;
import lombok.*;

@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class SubCategoryToListDTO {

    private Long id;
    private String name;
    private String code;
    private boolean active;

    public SubCategoryToListDTO(SubCategory subCategory) {
        this.id = subCategory.getId();
        this.name = subCategory.getName();
        this.code = subCategory.getCode();
        this.active = subCategory.isActive();
    }
}
