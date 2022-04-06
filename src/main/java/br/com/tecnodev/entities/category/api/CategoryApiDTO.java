package br.com.tecnodev.entities.category.api;

import br.com.tecnodev.entities.category.Category;
import br.com.tecnodev.entities.subCategory.SubCategory;

import java.util.List;

public class CategoryApiDTO {

    private final String name;
    private final String code;
    private final Integer orderInSystem;
    private final String colorCode;
    private final String studyGuide;
    private final Long totalCourse;
    private final List<SubCategoryApiDTO> subCategories;


    public CategoryApiDTO(Category category) {
        this.name = category.getName();
        this.code = category.getCode();
        this.orderInSystem = category.getOrderInSystem();
        this.colorCode = category.getColorCode();
        this.studyGuide = category.getStudyGuide();
        this.totalCourse = category.getTotalCoursesFromCategory();
        this.subCategories = getSubCategories(category);
    }

    private List<SubCategoryApiDTO> getSubCategories(Category category) {
        return SubCategoryApiDTO.toDto(category.getSubCategories().stream()
                .filter(SubCategory::isActive)
                .toList());
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

    public String getStudyGuide() {
        return studyGuide;
    }

    public Integer getOrderInSystem() {
        return orderInSystem;
    }

    public String getColorCode() {
        return colorCode;
    }

    public List<SubCategoryApiDTO> getSubCategories() {
        return subCategories;
    }

}
