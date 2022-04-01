package br.com.tecnodev.entities.category;

import java.util.List;

public class CategoryDTO {

    private final Long id;
    private final String name;
    private final String code;
    private final String description;
    private final String studyGuide;
    private final boolean active;
    private final Integer orderInSystem;
    private final String imageUrl;
    private final String colorCode;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.code = category.getCode();
        this.description = category.getDescription();
        this.studyGuide = category.getStudyGuide();
        this.active = category.isActive();
        this.orderInSystem = category.getOrderInSystem();
        this.imageUrl = category.getImageUrl();
        this.colorCode = category.getColorCode();
    }

    public Long getId() {
        return id;
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

    public static List<CategoryDTO> fromEntityToDTO(List<Category> categoryList){
        return categoryList.stream().map(CategoryDTO::new).toList();
    }

    public static CategoryDTO fromEntityToDTO(Category category) {
        return new CategoryDTO(category);
    }
}
