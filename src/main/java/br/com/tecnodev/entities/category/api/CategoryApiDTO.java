package br.com.tecnodev.entities.category.api;

import br.com.tecnodev.entities.category.Category;
import br.com.tecnodev.entities.subCategory.SubCategory;

import java.util.List;

public class CategoryApiDTO {

    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private boolean active;
    private Integer orderInSystem;
    private String imageUrl;
    private String colorCode;
    private Long totalCourse;
    private List<SubCategoryApiDTO> subCategories;

    public CategoryApiDTO() {
    }

    public CategoryApiDTO(Category category) {
        this.name = category.getName();
        this.code = category.getCode();
        this.description = category.getDescription();
        this.studyGuide = category.getStudyGuide();
        this.active = category.isActive();
        this.orderInSystem = category.getOrderInSystem();
        this.imageUrl = category.getImageUrl();
        this.colorCode = category.getColorCode();
        this.totalCourse = category.getTotalCoursesFromCategory();
        this.subCategories = getSubCategories(category);
    }

    private List<SubCategoryApiDTO> getSubCategories(Category category) {
        return SubCategoryApiDTO.toDto(category.getSubCategories().stream()
                .filter(SubCategory::isActive)
                .toList());
    }

    public void setTotalCourse(Long totalCourse) {
        this.totalCourse = totalCourse;
    }

    public Long getTotalCourse() {
        return totalCourse;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public boolean isActive() {
        return active;
    }

    public Integer getOrderInSystem() {
        return orderInSystem;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getColorCode() {
        return colorCode;
    }

    public List<SubCategoryApiDTO> getSubCategories() {
        return subCategories;
    }


}
