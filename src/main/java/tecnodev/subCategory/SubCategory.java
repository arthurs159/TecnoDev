package tecnodev.subCategory;

import tecnodev.category.Category;

import javax.persistence.*;

import static validator.Validator.*;

@Entity
@Table(name = "Subcategory")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT", name = "study_guide")
    private String studyGuide;
    private boolean active;
    @Column(columnDefinition = "SMALLINT", name = "order_in_system")
    private Integer orderInSystem;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    public SubCategory() {
    }

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

    public Long getId() {
        return id;
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

    public boolean hasDescription() {
        return description != null && !description.isBlank();
    }

    public boolean isActive() {
        return active;
    }

    public Integer getOrderInSystem() {
        return orderInSystem;
    }

    public String getCategoryCode() {
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
