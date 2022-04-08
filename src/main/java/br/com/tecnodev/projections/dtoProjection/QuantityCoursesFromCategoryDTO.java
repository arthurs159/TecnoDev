package br.com.tecnodev.projections.dtoProjection;

import br.com.tecnodev.projections.CategoryReportProjection;

public class QuantityCoursesFromCategoryDTO {

    private String categoryName;
    private Long quantityCoursesFromCategory;

    public QuantityCoursesFromCategoryDTO(String categoryName, Long quantityCoursesFromCategory) {
        this.categoryName = categoryName;
        this.quantityCoursesFromCategory = quantityCoursesFromCategory;
    }

    public QuantityCoursesFromCategoryDTO(CategoryReportProjection projection) {
        this.categoryName = projection.getCategoryName();
        this.quantityCoursesFromCategory = projection.getQuantityCoursesFromCategory();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getQuantityCoursesFromCategory() {
        return quantityCoursesFromCategory;
    }

    public void setQuantityCoursesFromCategory(Long quantityCoursesFromCategory) {
        this.quantityCoursesFromCategory = quantityCoursesFromCategory;
    }
}
