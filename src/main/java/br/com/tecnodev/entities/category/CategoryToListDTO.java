package br.com.tecnodev.entities.category;

public class CategoryToListDTO {

    private final String name;
    private final String code;
    private final boolean active;

    public CategoryToListDTO(Category category) {
        this.name = category.getName();
        this.code = category.getCode();
        this.active = category.isActive();
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public boolean isActive() {
        return active;
    }

}
