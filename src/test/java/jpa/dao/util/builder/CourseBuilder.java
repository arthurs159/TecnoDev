package jpa.dao.util.builder;

import tecnodev.course.Course;
import tecnodev.course.Status;
import tecnodev.subCategory.SubCategory;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class CourseBuilder {

    private Long id;
    private String name;
    private String code;
    private Integer estimatedTimeInHours;
    private Status visibility = Status.PRIVATE;
    private String targetAudience;
    private String teacher;
    private String description;
    private String developedSkills;
    private SubCategory subCategory;

    public CourseBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CourseBuilder code(String code) {
        this.code = code;
        return this;
    }

    public CourseBuilder estimatedTimeInHours(Integer estimatedTimeInHours) {
        this.estimatedTimeInHours = estimatedTimeInHours;
        return this;
    }

    public CourseBuilder visibility(Status visibility) {
        this.visibility = visibility;
        return this;
    }

    public CourseBuilder targetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
        return this;
    }

    public CourseBuilder teacher(String teacher) {
        this.teacher = teacher;
        return this;
    }

    public CourseBuilder description(String description) {
        this.description = description;
        return this;
    }

    public CourseBuilder developedSkills(String developedSkills) {
        this.developedSkills = developedSkills;
        return this;
    }

    public CourseBuilder subCategory(SubCategory subCategory) {
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
