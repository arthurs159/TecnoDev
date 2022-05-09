package br.com.tecnodev.entities.course;

import br.com.tecnodev.entities.course.DTO.NewCourseFormUpdate;
import br.com.tecnodev.entities.subCategory.SubCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;

import static br.com.tecnodev.validator.Validator.*;

@Entity
@XmlRootElement
@Data
@NoArgsConstructor
public class Course {
    private static final int MINIMUM_TIME = 1;
    private static final int MAXIMUM_TIME = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;

    @Column(columnDefinition = "SMALLINT", name = "estimated_time_in_hours")
    private Integer estimatedTimeInHours;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM")
    private Status visibility = Status.PRIVATE;
    @Column(name = "target_audience", columnDefinition = "TEXT")
    private String targetAudience;
    private String teacher;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name = "developed_skills", columnDefinition = "TEXT")
    private String developedSkills;

    @ManyToOne
    private SubCategory subCategory;

    public Course(String name, String code, int estimatedTimeInHours, String teacher, SubCategory subCategory) {
        isNotNullOrEmpty(name, "The name must not be empty or null!!!");
        isNotNullOrEmpty(teacher, "The teacher must not be empty or null!!");
        timeInterval(estimatedTimeInHours, MINIMUM_TIME, MAXIMUM_TIME, "The hours should be between 1 and 20");
        regexValidatorAndNotEmpty(code, "The code should be lowercase letters or numbers and be not empty");
        isNotNull(subCategory, "Subcategory should be not null");
        this.name = name;
        this.code = code;
        this.estimatedTimeInHours = estimatedTimeInHours;
        this.teacher = teacher;
        this.subCategory = subCategory;
    }


    public Course(String name, String code, Integer estimatedTimeInHours, Status visibility, String targetAudience, String teacher, String description, String developedSkills, SubCategory subCategory) {
        this(name, code, estimatedTimeInHours, teacher, subCategory);
        this.visibility = visibility;
        this.targetAudience = targetAudience;
        this.description = description;
        this.developedSkills = developedSkills;
    }

    public void merge(NewCourseFormUpdate dto, SubCategory subcategory) {
        this.name = dto.getName();
        this.code = dto.getCode();
        this.estimatedTimeInHours = dto.getEstimatedTimeInHours();
        this.visibility = dto.getVisibility();
        this.targetAudience = dto.getTargetAudience();
        this.teacher = dto.getTeacher();
        this.description = dto.getDescription();
        this.developedSkills = dto.getDevelopedSkills();
        this.subCategory = subcategory;
    }

    public String getCategoryCode() {
        return this.getSubCategory().getCategoryCode();
    }

    public String getSubCategoryCode() {
        return this.getSubCategory().getCode();
    }

    public Long getSubcategoryId() {
        return this.getSubCategory().getId();
    }

    public String getSubCategoryName() {
        return this.getSubCategory().getName();
    }
    
    public boolean isPublic(){
        return this.getVisibility().equals(Status.PUBLIC);
    }

}