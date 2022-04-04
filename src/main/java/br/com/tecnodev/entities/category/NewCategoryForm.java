package br.com.tecnodev.entities.category;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class NewCategoryForm {

    @NotEmpty @NotNull
    private String name;
    @NotEmpty @NotNull @Pattern(regexp = "[a-z0-9-]+", message = "The code must be lowercase letters or numbers and not be empty")
    private String code;
    private String description;
    private String studyGuide;
    private boolean active;
    private Integer orderInSystem;
    private String imageUrl;
    @NotEmpty @NotNull @Pattern(regexp = "^#([a-fA-F0-9]){3}(([a-fA-F0-9]){3})?$", message = "The code must be lowercase letters or numbers and not be empty")
    private String colorCode;

    public NewCategoryForm(String name, String code, String description, String studyGuide, boolean active, Integer orderInSystem, String imageUrl, String colorCode) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.studyGuide = studyGuide;
        this.active = active;
        this.orderInSystem = orderInSystem;
        this.imageUrl = imageUrl;
        this.colorCode = colorCode;
    }

    public void updateCategory(Category category) {
        this.name = category.getName();
        this.code = category.getCode();
        this.description = category.getDescription();
        this.studyGuide = category.getStudyGuide();
        this.active = category.isActive();
        this.orderInSystem = category.getOrderInSystem();
        this.imageUrl = category.getImageUrl();
        this.colorCode = category.getColorCode();
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

    public Category toEntity() {
        return new Category(name, code, orderInSystem, description, studyGuide, active, imageUrl, colorCode);
    }
}
