package tecnodev.subCategory;

import tecnodev.category.Category;

import static validator.Validator.*;

public class SubCategory {

    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private boolean active;
    private Integer orderInSystem;
    private Category category;

    public SubCategory(String name, String code, Category category) {
        isNotNullOrEmpty(name, "The name must not be empty or null");
        regexValidatorAndNotEmpty(code, "The code must be lowercase letters or numbers");
        isNotNull(category, "Category should not be null");
        this.name = name;
        this.code = code;
        this.category = category;
    }

    public SubCategory(String name, String code, Integer orderInSystem, String description, boolean active, Category category) {
        this(name, code, category);
        this.orderInSystem = orderInSystem;
        this.description = description;
        this.active = active;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public Integer getOrderInSystem() {
        return orderInSystem;
    }

    public String getCategoryCode(){
        return this.getCategory().getCode();
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", studyGuide='" + studyGuide + '\'' +
                ", active=" + active +
                ", orderInSystem='" + orderInSystem + '\'' +
                ", category=" + category +
                '}';
    }
}
