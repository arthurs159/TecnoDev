package br.com.tecnodev.entities.category;

import br.com.tecnodev.entities.category.DTO.NewCategoryFormUpdate;
import br.com.tecnodev.entities.subCategory.SubCategory;
import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static br.com.tecnodev.validator.Validator.*;

@Entity
@XmlRootElement
@Data       //TODO COLOCAR SÓ OS GETTERS? A aplicação usa setter do ID
@NoArgsConstructor
public class Category {

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
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "color_code")
    private String colorCode;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<SubCategory> subCategories = new ArrayList<>();

    public Category(String name, String code) {
        isNotNullOrEmpty(name, "The name must not be empty or null");
        regexValidatorAndNotEmpty(code, "The code must be lowercase letters or numbers and not be empty");
        this.name = name;
        this.code = code;
    }

    public Category(String name, String code, Integer orderInSystem, String description, boolean active, String imageUrl, String colorCode) {
        this(name, code);
        hexadecimalValidator(colorCode, "The colorCode should be in Hexadecimal standard");
        this.orderInSystem = orderInSystem;
        this.description = description;
        this.active = active;
        this.imageUrl = imageUrl;
        this.colorCode = colorCode;
    }

    public Category(String name, String code, Integer orderInSystem, String description, String studyGuide, boolean active, String imageUrl, String colorCode) {
        this(name, code, orderInSystem, description, active, imageUrl, colorCode);
        this.studyGuide = studyGuide;
    }

    public void update(NewCategoryFormUpdate dto) {
        this.name = dto.getName();
        this.code = dto.getCode();
        this.description = dto.getDescription();
        this.studyGuide = dto.getStudyGuide();
        this.active = dto.isActive();
        this.orderInSystem = dto.getOrderInSystem();
        this.imageUrl = dto.getImageUrl();
        this.colorCode = dto.getColorCode();
    }

    public void addSubcategory(SubCategory subCategory) {
        this.subCategories.add(subCategory);
    }

    public void disableActive() {
        this.active = false;
    }

    public List<SubCategory> getActiveSubcategories() {
        return subCategories.stream().filter(SubCategory::isActive).toList();
    }

    public Long getTotalCoursesFromCategory() {
        return getActiveSubcategories().stream()
                .map(SubCategory::getCourses)
                .flatMap(Collection::stream)
                .count();
    }
}
