package br.com.tecnodev.entities.course;

import br.com.tecnodev.entities.subCategory.SubCategory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class NewCourseForm {
    @NotBlank(message = "{form.error.name}")
    private String name;
    @NotBlank(message = "{form.error.code}")
    @Pattern(regexp = "[a-z0-9-]+", message = "{form.error.code.regex}")
    private String code;

    //todo COLOCAR O REGEX AQUi!!!!
    //    @Pattern(regexp = "[/^([1]?[0-9]|20)$/]", message = "{form.error.estimatedTime.regex}")
    private Integer estimatedTimeInHours;
    private Status visibility;
    private String targetAudience;
    @NotBlank(message = "{form.error.code}")
    private String teacher;
    private String description;
    private String developedSkills;
    @NotNull(message = "{form.error.subcategory}")
    private SubCategory subCategory;

    private Long subcategoryId;

    public NewCourseForm() {
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

    public Integer getEstimatedTimeInHours() {
        return estimatedTimeInHours;
    }

    public void setEstimatedTimeInHours(Integer estimatedTimeInHours) {
        this.estimatedTimeInHours = estimatedTimeInHours;
    }

    public Status getVisibility() {
        return visibility;
    }

    public void setVisibility(Status visibility) {
        this.visibility = visibility;
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

    public String getDevelopedSkills() {
        return developedSkills;
    }

    public void setDevelopedSkills(String developedSkills) {
        this.developedSkills = developedSkills;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Long getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Long subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public Course toEntity(SubCategory subCategory) {
        return new Course(name, code, estimatedTimeInHours, visibility, targetAudience, teacher, description, developedSkills, subCategory);
    }
}
