package tecnodev.course;

import tecnodev.subCategory.SubCategory;
import validator.Validator;

public class Course {

    private String name;
    private String code;
    private Integer hours;
    private boolean isVisible;
    private String targetAudience;
    private String teacher;
    private String description;
    private String developedAbility;
    private SubCategory subCategory;

    public Course(String name, String code, int hours, String teacher, SubCategory subCategory) {
        Validator.isNotNull(name, "The name must not be empty or null!!!");
        Validator.isNotNull(code, "The code must not be empty or null!!!");
        Validator.isNotNull(teacher, "The teacher must not be empty or null!!!");
        Validator.betweenOneAndTwenty(hours, "The hours must be between 1 and 20");
        Validator.regexValidator(code, "The code must be lowercase letters or numbers");
        this.name = name;
        this.code = code;
        this.hours = hours;
        this.teacher = teacher;
        this.subCategory = subCategory;
    }

    public Course(String name, String code, int hours, boolean isVisible, String targetAudience, String teacher, String description, String developedAbility, SubCategory subCategory) {
        this(name, code, hours, teacher, subCategory);
        this.isVisible = isVisible;
        this.targetAudience = targetAudience;
        this.description = description;
        this.developedAbility = developedAbility;
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

    public Integer getHours() {
        return hours;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisibility(boolean visibility) {
        this.isVisible = visibility;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDevelopedAbility() {
        return developedAbility;
    }

    public void setDevelopedAbility(String developedAbility) {
        this.developedAbility = developedAbility;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", hours=" + hours +
                ", isVisible=" + isVisible +
                ", targetAudience='" + targetAudience + '\'' +
                ", teacher='" + teacher + '\'' +
                ", description='" + description + '\'' +
                ", developedAbility='" + developedAbility + '\'' +
                ", subCategory=" + subCategory +
                '}';
    }
}