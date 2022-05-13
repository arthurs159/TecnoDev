package br.com.tecnodev.entities.subCategory;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class SubcategoryPageDTO {

    private String name;
    private Integer orderInSystem;

    public SubcategoryPageDTO(SubCategory subCategory) {
        this.name = subCategory.getName();
        this.orderInSystem = subCategory.getOrderInSystem();
    }

    public static List<SubcategoryPageDTO> toDto(List<SubCategory> subcategories) {
        return subcategories.stream().map(SubcategoryPageDTO::new).toList();
    }

}
