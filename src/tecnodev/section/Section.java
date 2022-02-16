package tecnodev.section;

import tecnodev.course.Course;

import static validator.Validator.*;

public class Section {

    private String name;
    private String code;
    private Integer orderInSystem;
    private boolean active;
    private boolean exam;
    private Course course;

    public Section(String name, String code, Course course) {
        isNotNullOrEmpty(name, "The name must not be empty or null");
        regexValidatorAndNotEmpty(code, "The code must be lowercase letters or numbers and not be empty");
        isNotNull(course, "Course must not be null");
        this.name = name;
        this.code = code;
        this.course = course;
    }

    @Override
    public String toString() {
        return "Section{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", orderInSystem='" + orderInSystem + '\'' +
                ", active=" + active +
                ", isATest=" + exam +
                ", course=" + course +
                '}';
    }
}
