package jpa.dao.util.builder;

import br.com.tecnodev.tecnodev.category.Category;
import br.com.tecnodev.tecnodev.subCategory.SubCategory;

public class SubCategoryBuilder {

    private String name;
    private String code;
    private String description;
    private boolean active;
    private Integer orderInSystem;
    private Category category;

    public SubCategoryBuilder(String name, String code, Category category) {
        this.name = name;
        this.code = code;
        this.category = category;
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

    public SubCategory create(){
        return new SubCategory(name,
                code,
                orderInSystem,
                description,
                active,
                category);
    }

    public static SubCategory subCategoryJs(Category category){
        SubCategory javaScript = new SubCategoryBuilder("JavaScript", "javascript", category)
                .withDescription("Projetos em JavaScript")
                .withActive(true)
                .withOrderInSystem(2)
                .create();
        return javaScript;
    }

    public static SubCategory subCategoryJava(Category category) {
        SubCategory java = new SubCategoryBuilder("Java", "java", category)
                .withDescription("Projetos em java")
                .withActive(true)
                .withOrderInSystem(1)
                .create();
        return java;
    }

    public static SubCategory subCategoryPython(Category category) {
        SubCategory python = new SubCategoryBuilder("Python", "python", category)
                .withDescription("Projetos em Python")
                .withActive(false)
                .withOrderInSystem(3)
                .create();
        return python;
    }

    public static SubCategory subCategoryMobile(Category category){
        SubCategory mobile = new SubCategoryBuilder("Mobile", "mobile", category)
                .withDescription("")
                .withActive(true)
                .withOrderInSystem(1)
                .create();

        return mobile;
    }

    public static SubCategory subCategoryCommunication(Category category){
        SubCategory communication = new SubCategoryBuilder("Comunicação", "communication", category)
                .withDescription("")
                .withActive(true)
                .withOrderInSystem(1)
                .create();

        return communication;
    }

}
