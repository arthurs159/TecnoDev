package tecnodev.section;

import tecnodev.course.Course;
import validator.Validator;

public class Section {

    private String name;
    private String code;
    private Integer orderInSystem;
    private boolean active;
    private boolean isExam;
    private Course course;

    public Section(String name, String code, Course course) {
        this.name = name;
        this.code = code;
        this.course = course;
        Validator.isNotNull(name, "O nome não pode ser vazio ou nulo");
    }

    public Section(String name, String code, Integer orderInSystem, boolean active, boolean IsExam, Course course) {
        this(name, code, course);
        this.orderInSystem = orderInSystem;
        this.active = active;
        this.isExam = IsExam;
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

    public Integer getOrderInSystem() {
        return orderInSystem;
    }

    public void setOrderInSystem(Integer orderInSystem) {
        this.orderInSystem = orderInSystem;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isExam() {
        return isExam;
    }

    public void setExam(boolean exam) {
        this.isExam = exam;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Section{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", orderInSystem='" + orderInSystem + '\'' +
                ", active=" + active +
                ", isExam=" + isExam +
                ", course=" + course +
                '}';
    }
}
