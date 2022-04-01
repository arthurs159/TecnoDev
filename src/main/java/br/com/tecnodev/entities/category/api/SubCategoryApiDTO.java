package br.com.tecnodev.entities.category.api;

import br.com.tecnodev.entities.course.Course;
import br.com.tecnodev.entities.subCategory.SubCategory;

import java.util.List;

public class SubCategoryApiDTO {

    private String name;
    private String code;
    private String studyGuide;
    private List<CourseApiDTO> courses;

    public SubCategoryApiDTO() {
    }

    public SubCategoryApiDTO(SubCategory subCategory) {
        this.name = subCategory.getName();
        this.code = subCategory.getCode();
        this.studyGuide = subCategory.getStudyGuide();
        this.courses = CourseApiDTO.toDTO(subCategory.getCourses());
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public List<CourseApiDTO> getCourses() {
        return courses;
    }

    public static List<SubCategoryApiDTO> toDto(List<SubCategory> subcategories) {
        return subcategories.stream().map(SubCategoryApiDTO::new).toList();
    }

}
