package br.com.tecnodev.entities.category.api;

import br.com.tecnodev.entities.course.Course;
import lombok.*;

import java.util.List;

@Getter
public class CourseApiDTO {

    private final String name;
    private final String code;
    private final Integer estimatedTimeInHours;
    private final String developedSkills;

    public CourseApiDTO(Course course) {
        this.name = course.getName();
        this.code = course.getCode();
        this.estimatedTimeInHours = course.getEstimatedTimeInHours();
        this.developedSkills = course.getDevelopedSkills();
    }

    public static List<CourseApiDTO> toDTO(List<Course> courses) {
        return courses.stream().map(CourseApiDTO::new).toList();
    }
}
