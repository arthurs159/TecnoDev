package br.com.tecnodev.entities.category;

import br.com.tecnodev.entities.subCategory.SubCategory;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

import static br.com.tecnodev.validator.Validator.*;

@Entity
@XmlRootElement
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

    @Deprecated
    public Category() {
    }

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

    public void addSubcategory(SubCategory subCategory) {
        this.subCategories.add(subCategory);
    }

    public void toggleActive() {
        this.active = !isActive();
    }

    public Long getId() {
        return id;
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

    public String getStudyGuide() {
        return studyGuide;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getColorCode() {
        return colorCode;
    }

    public boolean isActive() {
        return active;
    }

    public Integer getOrderInSystem() {
        return orderInSystem;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

//    public static long numberOfCourses(List<SubCategory> listDto, String code){
//        Long count = 0L;
//        for (int i=0; i <= listDto.size(); i++){
//            if(listDto.get(i).getCourses().get(i).getCategoryCode() == code){
//                count++;
//            }
//        }
//        return count;
//    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", studyGuide='" + studyGuide + '\'' +
                ", active=" + active +
                ", orderInSystem=" + orderInSystem +
                ", imageUrl='" + imageUrl + '\'' +
                ", colorCode='" + colorCode + '\'' +
                ", subCategories=" + subCategories +
                '}';
    }
}
