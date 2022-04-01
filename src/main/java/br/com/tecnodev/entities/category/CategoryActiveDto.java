package br.com.tecnodev.entities.category;

import br.com.tecnodev.entities.course.CourseDto;
import br.com.tecnodev.entities.subCategory.SubCategoryDto;

import java.util.ArrayList;
import java.util.List;

public class CategoryActiveDto {

    private String name;
    private String code;
    private Integer orderInSystem;
    private String colorCode;
    private String studyGuide;
    private Long totalCourses;

    private List<SubCategoryDto> subCategories;
//    private List<CourseDto> courseDto;

    public CategoryActiveDto(String name, String code, Integer orderInSystem, String colorCode, String studyGuide, Long totalCourses) {
        this.name = name;
        this.code = code;
        this.orderInSystem = orderInSystem;
        this.colorCode = colorCode;
        this.studyGuide = studyGuide;
        this.totalCourses = totalCourses;
    }

    public CategoryActiveDto(CategoryProjection projection){
        name = projection.getName();
        code = projection.getCode();
        orderInSystem = projection.getOrderInSystem();
        colorCode = projection.getColorCode();
        studyGuide = projection.getStudyGuide();
        totalCourses = projection.getTotalCourses();
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

    public Long getTotalCourses() {
        return totalCourses;
    }

    public String getName() {
        return name;
    }

}
