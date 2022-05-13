package br.com.tecnodev.entities.course.DTO;

import br.com.tecnodev.entities.course.Course;
import br.com.tecnodev.entities.course.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseToListDTO {

    private String name;
    private String code;
    private Status visibility;
    private String subcategoryName;

    public CourseToListDTO(Course course) {
        this.name = course.getName();
        this.code = course.getCode();
        this.visibility = course.getVisibility();
        this.subcategoryName = course.getSubCategoryName();
    }
}
