package tecnodev.category;

import tecnodev.course.Course;

import java.util.List;

import static validator.Validator.*;

public class Category {

    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private boolean active;
    private Integer orderInSystem;
    private String imageUrl;
    private String colorCode;

    public Category(String name, String code) {
        isNotNullOrEmpty(name, "The name must not be empty or null");
        regexValidatorAndNotEmpty(code, "The code must be lowercase letters or numbers and not be empty");
        this.name = name;
        this.code = code;
    }

    public Category(String name, String code, Integer orderInSystem, String description, boolean active, String imageUrl, String colorCode) {
        this(name, code);
        hexadecimalValidator(colorCode, "The colorCode should be in Hexadecimal standard");
        this.orderInSystem = orderInSystem;
        this.description = description;
        this.active = active;
        this.imageUrl = imageUrl;
        this.colorCode = colorCode;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getColorCode() {
        return colorCode;
    }

    public boolean isActive() {
        return active;
    }

    public static int numbersOfCourseFromCategory(List<Course> courses, String codeCategory) {
        return (int) courses.stream()
                .filter(course -> course.getCategoryCode().equals(codeCategory)).count();
    }

    public static int quantityHoursFromCategory(List<Course> courses, String categoryCode) {
        return courses.stream()
                .filter(course -> course.getCategoryCode().equals(categoryCode))
                .mapToInt(Course::getEstimatedTimeInHours).sum();
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", studyGuide='" + studyGuide + '\'' +
                ", active=" + active +
                ", orderInSystem=" + orderInSystem +
                ", imageUrl='" + imageUrl + '\'' +
                ", htmlCode='" + colorCode + '\'' +
                '}';
    }
}
