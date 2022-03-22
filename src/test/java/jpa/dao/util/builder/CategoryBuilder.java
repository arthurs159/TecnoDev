package jpa.dao.util.builder;

import tecnodev.category.Category;

public class CategoryBuilder {

    private String name;
    private String code;
    private String description;
    private boolean active;
    private Integer orderInSystem;
    private String imageUrl;
    private String colorCode;

    public CategoryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CategoryBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public CategoryBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryBuilder withActive(boolean active) {
        this.active = active;
        return this;
    }

    public CategoryBuilder withOrderInSystem(Integer orderInSystem) {
        this.orderInSystem = orderInSystem;
        return this;
    }

    public CategoryBuilder withImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public CategoryBuilder withColorCode(String colorCode) {
        this.colorCode = colorCode;
        return this;
    }

    public Category create() {
        return new Category(name,
                code,
                orderInSystem,
                description,
                active,
                imageUrl,
                colorCode);
    }

}
