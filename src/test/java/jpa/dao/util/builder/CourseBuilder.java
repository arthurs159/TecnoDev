package jpa.dao.util.builder;

import tecnodev.course.Course;
import tecnodev.course.Status;
import tecnodev.subCategory.SubCategory;

public class CourseBuilder {

    private String name;
    private String code;
    private Integer estimatedTimeInHours;
    private Status visibility = Status.PRIVATE;
    private String targetAudience;
    private String teacher;
    private String description;
    private String developedSkills;
    private SubCategory subCategory;

    public CourseBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CourseBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public CourseBuilder withEstimatedTimeInHours(Integer estimatedTimeInHours) {
        this.estimatedTimeInHours = estimatedTimeInHours;
        return this;
    }

    public CourseBuilder withVisibility(Status visibility) {
        this.visibility = visibility;
        return this;
    }

    public CourseBuilder withTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
        return this;
    }

    public CourseBuilder withTeacher(String teacher) {
        this.teacher = teacher;
        return this;
    }

    public CourseBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public CourseBuilder withDevelopedSkills(String developedSkills) {
        this.developedSkills = developedSkills;
        return this;
    }

    public CourseBuilder withSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
        return this;
    }

    public Course create(){
        return new Course(
                name,
                code,
                estimatedTimeInHours,
                visibility,
                targetAudience,
                teacher,
                description,
                developedSkills,
                subCategory);
    }

}
