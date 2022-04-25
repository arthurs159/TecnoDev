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

    public static SubCategory subCategoryJs(Category category) {
        SubCategory javaScript = new SubcategoryBuilder("JavaScript", "javascript", category)
                .withDescription("Projetos em JavaScript")
                .withActive(true)
                .withOrderInSystem(2)
                .create();
        return javaScript;
    }

    public static SubCategory subCategoryJava(Category category) {
        SubCategory java = new SubcategoryBuilder("Java", "java", category)
                .withDescription("Projetos em java")
                .withActive(true)
                .withOrderInSystem(1)
                .create();
        return java;
    }

    public static SubCategory subCategoryPython(Category category) {
        SubCategory python = new SubcategoryBuilder("Python", "python", category)
                .withDescription("Projetos em Python")
                .withActive(false)
                .withOrderInSystem(3)
                .create();
        return python;
    }

    public static SubCategory subCategoryMobile(Category category) {
        SubCategory mobile = new SubcategoryBuilder("Mobile", "mobile", category)
                .withDescription("")
                .withActive(true)
                .withOrderInSystem(1)
                .create();

        return mobile;
    }

    public static SubCategory subCategoryCommunication(Category category) {
        SubCategory communication = new SubcategoryBuilder("Comunicação", "communication", category)
                .withDescription("")
                .withActive(true)
                .withOrderInSystem(1)
                .create();

        return communication;
    }
}
