package br.com.tecnodev.entities.subCategory;

import br.com.tecnodev.entities.category.Category;
import br.com.tecnodev.entities.course.Course;
import br.com.tecnodev.entities.subCategory.DTO.NewSubCategoryFormUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

import static br.com.tecnodev.validator.Validator.*;

@Entity
@XmlRootElement
@Data
@NoArgsConstructor
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
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();

    public SubCategory(String name, String code, Category category) {
        isNotNullOrEmpty(name, "The name must not be empty or null");
        regexValidatorAndNotEmpty(code, "The code must be lowercase letters or numbers");
        isNotNull(category, "Category should not be null");
        this.name = name;
        this.code = code;
        this.category = category;
        this.category.addSubcategory(this);
    }

    public SubCategory(String name, String code, Integer orderInSystem, String description, boolean active, Category category) {
        this(name, code, category);
        this.orderInSystem = orderInSystem;
        this.description = description;
        this.active = active;
    }
    public void merge(NewSubCategoryFormUpdate dto, Category category) {
        this.name = dto.getName();
        this.code = dto.getCode();
        this.active = dto.isActive();
        this.orderInSystem = dto.getOrderInSystem();
        this.studyGuide = dto.getStudyGuide();
        this.description = dto.getDescription();
        this.category = category;
    }

    public boolean hasDescription() {
        return description != null && !description.isBlank();
    }

    public String getCategoryCode() {
        return category.getCode();
    }

    public void disableActive() {
        this.active = false;
    }
}
