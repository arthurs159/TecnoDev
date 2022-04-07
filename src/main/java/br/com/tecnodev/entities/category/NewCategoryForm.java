package br.com.tecnodev.entities.category;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class NewCategoryForm {

    @NotEmpty(message = "{form.error.name}")
    private String name;
    @NotEmpty(message = "{form.error.code}")
    @Pattern(regexp = "[a-z0-9-]+", message = "{form.error.code.regex}")
    private String code;
    private String description;
    private String studyGuide;
    private boolean active;
    private Integer orderInSystem;
    private String imageUrl;
    @Pattern(regexp = "^#([a-fA-F0-9]){3}(([a-fA-F0-9]){3})?$", message = "{form.error.color.regex}")
    private String colorCode;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setOrderInSystem(Integer orderInSystem) {
        this.orderInSystem = orderInSystem;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public Category toEntity() {
        return new Category(name, code, orderInSystem, description, studyGuide, active, imageUrl, colorCode);
    }
}
