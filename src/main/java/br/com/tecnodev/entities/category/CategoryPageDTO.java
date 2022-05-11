package br.com.tecnodev.entities.category;

import br.com.tecnodev.entities.course.Status;
import br.com.tecnodev.entities.subCategory.SubCategory;
import br.com.tecnodev.entities.subCategory.SubcategoryPageDTO;

import java.util.List;

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

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Integer getOrderInSystem() {
        return orderInSystem;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<SubcategoryPageDTO> getSubCategories() {
        return subCategories;
    }
}
