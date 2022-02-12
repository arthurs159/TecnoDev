package tecnodev.subCategory;

import tecnodev.category.Category;
import validator.Validator;

public class SubCategory {

    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private boolean active;
    private Integer orderInSystem;
    private Category category;

    public SubCategory(String name, String code, Category category) {
        this.name = name;
        this.code = code;
        this.category = category;
    }

    public SubCategory(String name, String code, String description, String studyGuide, boolean active, Integer orderInSystem, Category category) {
        this(name, code, category);
        this.description = description;
        this.studyGuide = studyGuide;
        this.active = active;
        this.orderInSystem = orderInSystem;
        Validator.isNotNull(name, "O nome não pode ser vazio ou nulo");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public void setStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getOrderInSystem() {
        return orderInSystem;
    }

    public void setOrderInSystem(Integer orderInSystem) {
        this.orderInSystem = orderInSystem;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
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
