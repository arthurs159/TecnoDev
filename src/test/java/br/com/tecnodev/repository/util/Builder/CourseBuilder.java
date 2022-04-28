package br.com.tecnodev.repository.util.Builder;

import br.com.tecnodev.entities.course.Course;
import br.com.tecnodev.entities.course.Status;
import br.com.tecnodev.entities.subCategory.SubCategory;

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

    public CourseBuilder(String name, String code, Integer estimatedTimeInHours, String teacher, SubCategory subCategory) {
        this.name = name;
        this.code = code;
        this.estimatedTimeInHours = estimatedTimeInHours;
        this.teacher = teacher;
        this.subCategory = subCategory;
    }

    public CourseBuilder withVisibility(Status visibility) {
        this.visibility = visibility;
        return this;
    }

    public CourseBuilder withTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
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

    public Course create() {
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

    public static Course courseJava(SubCategory subCategory, String name, String code, String teacher, Status status) {
        Course javaSintax = new CourseBuilder(name, code,
                5, teacher, subCategory)
                .withVisibility(status)
                .withTargetAudience("Pessoas que gostam de Java")
                .withDevelopedSkills("Aprenda a sintaxe de java")
                .create();

        return javaSintax;
    }

    public static Course courseJpa(SubCategory subCategory, String name, String code, String teacher, Status status) {
        Course jpa = new CourseBuilder(name, code, 5,
                teacher, subCategory)
                .withVisibility(status)
                .withTargetAudience("Pessoas que gostam de Java")
                .withDevelopedSkills("Aprenda JPA")
                .create();
        return jpa;
    }

    public static Course coursePython(SubCategory subCategory, String name, String code, String teacher, Status status) {
        Course python = new CourseBuilder(name, code,
                5, teacher, subCategory)
                .withVisibility(status)
                .withTargetAudience("Pessoas que gostam de Python")
                .withDevelopedSkills("Aprenda uma nova linguagem")
                .create();
        return python;
    }

    public static Course courseAngular(SubCategory subCategory, String name, String code, String teacher, Status status) {
        Course angular = new CourseBuilder(name, code,
                5, teacher, subCategory)
                .withVisibility(status)
                .withTargetAudience("Pessoas que gostam de Angular")
                .withDevelopedSkills("Aprenda o framework do momento")
                .create();
        return angular;
    }
}
