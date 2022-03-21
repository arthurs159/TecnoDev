package jpa.dao.util.builder;

import tecnodev.category.Category;

import javax.persistence.Column;

public class CategoryBuilder {

    private String name;
    private String code;
    private String description;
    private boolean active;
    private Integer orderInSystem;
    private String imageUrl;
    private String colorCode;

    public CategoryBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CategoryBuilder code(String code) {
        this.code = code;
        return this;
    }

    public CategoryBuilder description(String description) {
        this.description = description;
        return this;
    }

    public CategoryBuilder active(boolean active) {
        this.active = active;
        return this;
    }

    public CategoryBuilder orderInSystem(Integer orderInSystem) {
        this.orderInSystem = orderInSystem;
        return this;
    }

    public CategoryBuilder imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public CategoryBuilder colorCode(String colorCode) {
        this.colorCode = colorCode;
        return this;
    }

    public Category create() {
        return new Category(name, code, orderInSystem,
                description, active, imageUrl, colorCode);
    }

}
