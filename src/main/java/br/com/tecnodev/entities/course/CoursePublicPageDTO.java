package br.com.tecnodev.entities.course;

import java.util.List;

public class CoursePublicPageDTO {

    private String name;
    private Integer estimatedTimeInHours;

    public CoursePublicPageDTO(Course course) {
        this.name = course.getName();
        this.estimatedTimeInHours = course.getEstimatedTimeInHours();
    }

    public String getName() {
        return name;
    }

    public Integer getEstimatedTimeInHours() {
        return estimatedTimeInHours;
    }

    public static List<CoursePublicPageDTO> toDTO(List<Course> courses) {
        return courses.stream().map(CoursePublicPageDTO::new).toList();
    }
}
