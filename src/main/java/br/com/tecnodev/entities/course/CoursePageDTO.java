package br.com.tecnodev.entities.course;

public class CoursePageDTO {

    private final String name;
    private final Integer estimatedTimeInHours;

    public CoursePageDTO(Course course) {
        this.name = course.getName();
        this.estimatedTimeInHours = course.getEstimatedTimeInHours();
    }

    public String getName() {
        return name;
    }

    public Integer getEstimatedTimeInHours() {
        return estimatedTimeInHours;
    }
}
