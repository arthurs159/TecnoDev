package jpa.dao.util.builder;

import br.com.tecnodev.tecnodev.category.Category;

public class CategoryBuilder {

    private String name;
    private String code;
    private String description;
    private boolean active;
    private Integer orderInSystem;
    private String imageUrl;
    private String colorCode;

    public CategoryBuilder(String name, String code) {
        this.name = name;
        this.code = code;
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

    public static Category categoryFrontEnd() {
            Category frontEndCategory = new CategoryBuilder("Front-end", "frontend")
                .withDescription("Curso front-end")
                .withActive(true)
                .withOrderInSystem(5)
                .withImageUrl("www.google.com.br")
                .withColorCode("#9AEA20")
                .create();
        return frontEndCategory;
    }

    public static Category categoryBackEnd(){
        Category backEndCategory = new CategoryBuilder("Back-End", "backend")
                .withDescription("Curso back-end")
                .withActive(true)
                .withOrderInSystem(4)
                .withImageUrl("www.google.com.br")
                .withColorCode("#9AEA20")
                .create();
        return backEndCategory;
    }

    public static Category categoryDevops(){
        Category devOpsCategory = new CategoryBuilder("DevOps", "devops")
                .withDescription("Curso Devops")
                .withActive(false)
                .withOrderInSystem(5)
                .withImageUrl("www.google.com.br")
                .withColorCode("#9AEA20")
                .create();
        return devOpsCategory;
    }

}
