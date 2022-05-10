package br.com.tecnodev.entities.category.api;

import br.com.tecnodev.entities.category.Category;
import lombok.*;

import java.util.List;

@Getter
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
        this.subCategories = SubCategoryApiDTO.toDto(category.getActiveSubcategories());
    }
}