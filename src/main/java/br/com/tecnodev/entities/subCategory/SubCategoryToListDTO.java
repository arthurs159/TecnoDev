package br.com.tecnodev.entities.subCategory;

public class SubCategoryToListDTO {

    private String name;
    private String code;
    private boolean active;
    private String categoryName;
    private String categoryCode;

    public SubCategoryToListDTO(SubCategory subCategory) {
        this.name = subCategory.getName();
        this.code = subCategory.getCode();
        this.active = subCategory.isActive();
        this.categoryName = subCategory.getCategory().getName();
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
}
