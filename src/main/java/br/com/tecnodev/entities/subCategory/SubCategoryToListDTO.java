package br.com.tecnodev.entities.subCategory;

public class SubCategoryToListDTO {

    private Long id;
    private String name;
    private String code;
    private boolean active;

    private String categoryName;
    private String categoryCode;

    public SubCategoryToListDTO(SubCategory subCategory) {
        this.id = subCategory.getId();
        this.name = subCategory.getName();
        this.code = subCategory.getCode();
        this.active = subCategory.isActive();
        this.categoryName = subCategory.getCategoryName();
        this.categoryCode = subCategory.getCategoryCode();
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

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public Long getId() {
        return id;
    }
}
