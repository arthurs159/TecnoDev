package jdbc.dao.util.builder;

import tecnodev.category.Category;

public class CategoryBuilder {

    private String name;
    private String code;

    public CategoryBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CategoryBuilder code(String name) {
        this.code = code;
        return this;
    }

    public Category create() {
        return new Category(name, code);
    }

}
