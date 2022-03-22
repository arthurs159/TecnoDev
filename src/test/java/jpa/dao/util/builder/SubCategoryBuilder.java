package jpa.dao.util.builder;

import tecnodev.category.Category;
import tecnodev.subCategory.SubCategory;

public class SubCategoryBuilder {

    private String name;
    private String code;
    private String description;
    private boolean active;
    private Integer orderInSystem;
    private Category category;

    public SubCategoryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public SubCategoryBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public SubCategoryBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public SubCategoryBuilder withActive(boolean active) {
        this.active = active;
        return this;
    }

    public SubCategoryBuilder withOrderInSystem(Integer orderInSystem) {
        this.orderInSystem = orderInSystem;
        return this;
    }

    public SubCategoryBuilder withCategory(Category category) {
        this.category = category;
        return this;
    }

    public SubCategory create(){
        return new SubCategory(name,
                code,
                orderInSystem,
                description,
                active,
                category);
    }
}
