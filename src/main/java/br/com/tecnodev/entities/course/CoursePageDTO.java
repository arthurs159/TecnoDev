package br.com.tecnodev.entities.course;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoursePageDTO {

    private final String name;
    private final Integer estimatedTimeInHours;

    public CoursePageDTO(Course course) {
        this.name = course.getName();
        this.estimatedTimeInHours = course.getEstimatedTimeInHours();
    }
}
