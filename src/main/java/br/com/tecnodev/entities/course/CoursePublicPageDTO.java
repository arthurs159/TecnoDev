package br.com.tecnodev.entities.course;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class CoursePublicPageDTO {

    private String name;
    private Integer estimatedTimeInHours;

    public CoursePublicPageDTO(Course course) {
        this.name = course.getName();
        this.estimatedTimeInHours = course.getEstimatedTimeInHours();
    }

    public static List<CoursePublicPageDTO> toDTO(List<Course> courses) {
        return courses.stream().map(CoursePublicPageDTO::new).toList();
    }
}
