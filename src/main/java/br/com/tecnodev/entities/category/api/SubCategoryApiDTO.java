package br.com.tecnodev.entities.category.api;

import br.com.tecnodev.entities.subCategory.SubCategory;
import lombok.*;

import java.util.List;
@Getter
public class SubCategoryApiDTO {

    private final String name;
    private final String code;
    private final String studyGuide;
    private final List<CourseApiDTO> courses;

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
