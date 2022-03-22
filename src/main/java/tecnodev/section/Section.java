package tecnodev.section;

import tecnodev.course.Course;

import javax.persistence.*;

import static validator.Validator.*;

@Entity
@Table(name = "Section")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    @Column(name = "order_in_system", columnDefinition = "SMALLINT")
    private Integer orderInSystem;
    private boolean active;
    private boolean exam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public Section() {}

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
