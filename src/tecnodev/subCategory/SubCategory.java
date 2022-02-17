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
