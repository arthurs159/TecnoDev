package br.com.tecnodev.entities.course;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Status getVisibility() {
        return visibility;
    }

    public void setVisibility(Status visibility) {
        this.visibility = visibility;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }
}
