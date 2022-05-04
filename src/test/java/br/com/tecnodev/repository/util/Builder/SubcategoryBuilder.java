package br.com.tecnodev.repository.util.Builder;

import br.com.tecnodev.entities.category.Category;
import br.com.tecnodev.entities.subCategory.SubCategory;

public class SubcategoryBuilder {
    private String name;
    private String code;
    private String description;
    private boolean active;
    private Integer orderInSystem;
    private Category category;

    public SubcategoryBuilder(String name, String code, Category category) {
        this.name = name;
        this.code = code;
        this.category = category;
    }

    public SubcategoryBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public SubcategoryBuilder withActive(boolean active) {
        this.active = active;
        return this;
    }

    public SubcategoryBuilder withOrderInSystem(Integer orderInSystem) {
        this.orderInSystem = orderInSystem;
        return this;
    }

    public SubCategory create() {
        return new SubCategory(name,
                code,
                orderInSystem,
                description,
                active,
                category);
    }


    public static SubCategory subCategoryJs(Category category, String name, String code, boolean active) {
        SubCategory javaScript = new SubcategoryBuilder(name, code, category)
                .withDescription("Projetos em JavaScript")
                .withActive(active)
                .withOrderInSystem(2)
                .create();
        return javaScript;
    }

    public static SubCategory subCategoryJava(Category category, String name, String code, boolean active) {
        SubCategory java = new SubcategoryBuilder("Java", "java", category)
                .withDescription("Projetos em java")
                .withActive(true)
                .withOrderInSystem(1)
                .create();
        return java;
    }

    public static SubCategory subCategoryPython(Category category, String name, String code, boolean active) {
        SubCategory python = new SubcategoryBuilder(name, code, category)
                .withDescription("Projetos em Python")
                .withActive(active)
                .withOrderInSystem(3)
                .create();
        return python;
    }

    public static SubCategory subCategoryMobile(Category category, String name, String code, boolean active) {
        SubCategory mobile = new SubcategoryBuilder(name, code, category)
                .withDescription("")
                .withActive(true)
                .withOrderInSystem(4)
                .create();

        return mobile;
    }

}
