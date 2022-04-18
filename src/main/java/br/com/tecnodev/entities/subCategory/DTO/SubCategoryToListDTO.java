package br.com.tecnodev.entities.subCategory.DTO;

import br.com.tecnodev.entities.subCategory.SubCategory;

public class SubCategoryToListDTO {

    private Long id;
    private String name;
    private String code;
    private boolean active;

    public SubCategoryToListDTO(SubCategory subCategory) {
        this.id = subCategory.getId();
        this.name = subCategory.getName();
        this.code = subCategory.getCode();
        this.active = subCategory.isActive();
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

    public Long getId() {
        return id;
    }
}
