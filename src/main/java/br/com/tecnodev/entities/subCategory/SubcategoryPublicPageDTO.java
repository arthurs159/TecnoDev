package br.com.tecnodev.entities.subCategory;

import br.com.tecnodev.entities.course.CoursePublicPageDTO;
import br.com.tecnodev.entities.course.Status;

import java.util.List;

public class SubcategoryPublicPageDTO {
    private final String name;
    private final Integer orderInSystem;
    List<CoursePublicPageDTO> courses;

    public SubcategoryPublicPageDTO(SubCategory subCategory) {
        this.name = subCategory.getName();
        this.orderInSystem = subCategory.getOrderInSystem();
        this.courses = CoursePublicPageDTO.toDTO(subCategory.getCourses()
                .stream().filter(course -> course.getVisibility().equals(Status.PUBLIC)).toList());
    }

    public String getName() {
        return name;
    }

    public Integer getOrderInSystem() {
        return orderInSystem;
    }

    public List<CoursePublicPageDTO> getCourses() {
        return courses;
    }

    public static List<SubcategoryPageDTO> toDto(List<SubCategory> subcategories) {
        return subcategories.stream().map(SubcategoryPageDTO::new).toList();
    }
}
