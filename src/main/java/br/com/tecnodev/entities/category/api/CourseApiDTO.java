package br.com.tecnodev.entities.category.api;

import br.com.tecnodev.entities.course.Course;

import java.util.List;

public class CourseApiDTO {

    private String name;
    private String code;
    private Integer estimatedTimeInHours;
    private String developedSkills;

    public CourseApiDTO() {
    }

    public CourseApiDTO(Course course) {
        this.name = course.getName();
        this.code = course.getCode();
        this.estimatedTimeInHours = course.getEstimatedTimeInHours();
        this.developedSkills = course.getDevelopedSkills();
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Integer getEstimatedTimeInHours() {
        return estimatedTimeInHours;
    }

    public String getDevelopedSkills() {
        return developedSkills;
    }

    public static List<CourseApiDTO> toDTO(List<Course> courses) {
        return courses.stream().map(CourseApiDTO::new).toList();
    }
}
