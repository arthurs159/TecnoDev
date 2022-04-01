package br.com.tecnodev.entities.category;

public class NewCategoryForm {

    private final String name;
    private final String code;
    private final String description;
    private final String studyGuide;
    private final boolean active;
    private final Integer orderInSystem;
    private final String imageUrl;
    private final String colorCode;

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
