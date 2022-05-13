package br.com.tecnodev.entities.category;

import br.com.tecnodev.entities.subCategory.SubcategoryPageDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class CategoryPageDTO {
    private final String name;
    private final String code;
    private final Integer orderInSystem;
    private final String imageUrl;
    private final List<SubcategoryPageDTO> subCategories;

    public CategoryPageDTO(Category category) {
        this.name = category.getName();
        this.code = category.getCode();
        this.orderInSystem = category.getOrderInSystem();
        this.imageUrl = category.getImageUrl();
        this.subCategories = category.getSubCategories().stream().map(SubcategoryPageDTO::new).toList();
    }
}
