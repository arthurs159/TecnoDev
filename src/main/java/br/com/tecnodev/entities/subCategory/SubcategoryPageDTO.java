package br.com.tecnodev.entities.subCategory;

import br.com.tecnodev.entities.category.api.SubCategoryApiDTO;

import java.util.List;

public class SubcategoryPageDTO {

    private String name;
    private Integer orderInSystem;

    public SubcategoryPageDTO(SubCategory subCategory) {
        this.name = subCategory.getName();
        this.orderInSystem = subCategory.getOrderInSystem();
    }

    public String getName() {
        return name;
    }

    public Integer getOrderInSystem() {
        return orderInSystem;
    }

    public static List<SubcategoryPageDTO> toDto(List<SubCategory> subcategories) {
        return subcategories.stream().map(SubcategoryPageDTO::new).toList();
    }

}
