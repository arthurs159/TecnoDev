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

//    public CategoryPageDTO(Category category) {
//        this.name = category.getName();
//        this.code = category.getCode();
//        this.orderInSystem = category.getOrderInSystem();
//        this.imageUrl = category.getImageUrl();
//        this.subCategories = getSubCategories(category);
//    }

    public CategoryPageDTO(Category category) {
        this.name = category.getName();
        this.code = category.getCode();
        this.orderInSystem = category.getOrderInSystem();
        this.imageUrl = category.getImageUrl();
        this.subCategories = category.getSubCategories().stream().map(SubcategoryPageDTO::new).toList();
    }

    private List<SubcategoryPageDTO> getSubCategories(Category category) {
        return SubcategoryPageDTO.toDto(category.getSubCategories().stream()
                .filter(SubCategory::isActive)
                .filter(sub -> sub.getCourses()
                        .stream().anyMatch(course -> course.getVisibility().equals(Status.PUBLIC)))
                .toList());
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
