package tecnodev.category;

import static validator.Validator.*;

public class Category {

    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private boolean active;
    private Integer orderInSystem;
    private String imageUrl;
    private String colorCode;

    public Category(String name, String code) {
        isNotNullOrEmpty(name, "The name must not be empty or null");
        regexValidatorAndNotEmpty(code, "The code must be lowercase letters or numbers and not be empty");
        this.name = name;
        this.code = code;
    }

    public Category(String name, String code, Integer orderInSystem, String description, boolean active, String imageUrl, String colorCode) {
        this(name, code);
        this.orderInSystem = orderInSystem;
        this.description = description;
        this.active = active;
        this.imageUrl = imageUrl;
        this.colorCode = colorCode;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", studyGuide='" + studyGuide + '\'' +
                ", active=" + active +
                ", orderInSystem=" + orderInSystem +
                ", imageUrl='" + imageUrl + '\'' +
                ", htmlCode='" + colorCode + '\'' +
                '}';
    }
}
