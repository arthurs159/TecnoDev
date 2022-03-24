package tecnodev.course;

import tecnodev.subCategory.SubCategory;

import javax.persistence.*;

import static validator.Validator.*;

@Entity
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

    @ManyToOne(fetch = FetchType.LAZY)
    private SubCategory subCategory;

    @Deprecated
    public Course() {}

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public String getName() {
        return name;
    }

    public Integer getEstimatedTimeInHours() {
        return estimatedTimeInHours;
    }

    public String getCategoryCode() {
        return this.getSubCategory().getCategoryCode();
    }

    public Status getVisibility() {
        return visibility;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getCode() {
        return code;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public String getDescription() {
        return description;
    }

    public String getDevelopedSkills() {
        return developedSkills;
    }

    public String getSubCategoryCode() {
        return this.getSubCategory().getCode();
    }

    public Long getSubcategoryId(){
        return this.getSubCategory().getId();
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", hours=" + estimatedTimeInHours +
                ", isVisible=" + visibility +
                ", targetAudience='" + targetAudience + '\'' +
                ", teacher='" + teacher + '\'' +
                ", description='" + description + '\'' +
                ", developedAbility='" + developedSkills + '\'' +
                ", subCategory=" + subCategory +
                '}';
    }

}