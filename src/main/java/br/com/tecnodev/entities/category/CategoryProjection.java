package br.com.tecnodev.entities.category;

public interface CategoryProjection {

    String getName();
    String getCode();
    Integer getOrderInSystem();
    String getColorCode();
    String getStudyGuide();
    Long getTotalCourses();
}
