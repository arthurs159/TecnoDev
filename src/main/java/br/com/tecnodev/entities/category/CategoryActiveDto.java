package br.com.tecnodev.entities.category;

import br.com.tecnodev.entities.course.CourseDto;
import br.com.tecnodev.entities.subCategory.SubCategoryDto;

import java.util.List;

public class CategoryActiveDto {

    private String name;
    private String code;
    private Integer orderInSystem;
    private String colorCode;
    private String studyGuide;
    private String description;
    private Integer totalCourses;

    private List<SubCategoryDto> subCategoryDto;
    private List<CourseDto> courseDto;

    public CategoryActiveDto(String name, String code, Integer orderInSystem, String colorCode, String studyGuide, String description, Integer totalCourses, List<SubCategoryDto> subCategoryDto, List<CourseDto> courseDto) {
        this.name = name;
        this.code = code;
        this.orderInSystem = orderInSystem;
        this.colorCode = colorCode;
        this.studyGuide = studyGuide;
        this.description = description;
        this.totalCourses = totalCourses;
        this.subCategoryDto = subCategoryDto;
        this.courseDto = courseDto;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Integer getOrderInSystem() {
        return orderInSystem;
    }

    public String getColorCode() {
        return colorCode;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public String getDescription() {
        return description;
    }

    public Integer getTotalCourses() {
        return totalCourses;
    }

    public List<SubCategoryDto> getSubCategoryDto() {
        return subCategoryDto;
    }

    public List<CourseDto> getCourseDto() {
        return courseDto;
    }
}
