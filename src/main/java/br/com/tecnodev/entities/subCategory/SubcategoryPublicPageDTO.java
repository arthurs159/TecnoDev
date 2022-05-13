package br.com.tecnodev.entities.subCategory;

import br.com.tecnodev.entities.course.Course;
import br.com.tecnodev.entities.course.CoursePublicPageDTO;
import br.com.tecnodev.entities.course.Status;
import lombok.*;

import java.util.List;
@Getter
@Setter
public class SubcategoryPublicPageDTO {
    private final String name;
    private final Integer orderInSystem;
    List<CoursePublicPageDTO> courses;

    public SubcategoryPublicPageDTO(SubCategory subCategory) {
        this.name = subCategory.getName();
        this.orderInSystem = subCategory.getOrderInSystem();
        this.courses = CoursePublicPageDTO.toDTO(subCategory.getCourses()
                .stream().filter(Course::isPublic).toList());
    }

    public static List<SubcategoryPageDTO> toDto(List<SubCategory> subcategories) {
        return subcategories.stream().map(SubcategoryPageDTO::new).toList();
    }
}
