package tecnodev.course;

import tecnodev.subCategory.SubCategory;

import static validator.Validator.*;

public class Course {

    private static final int MINIMUM_TIME = 1;
    private static final int MAXIMUM_TIME = 20;

    private String name;
    private String code;
    private Integer estimatedTimeInHours;
    private Status visibility = Status.PRIVATE;
    private String targetAudience;
    private String teacher;
    private String description;
    private String developedSkills;
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

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public String getName() {
        return name;
    }

    public Integer getEstimatedTimeInHours() {
        return estimatedTimeInHours;
    }

    public String getCategoryCode(){
        return this.getSubCategory().getCategoryCode();
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