package tecnodev.activity;

import tecnodev.section.Section;

import javax.persistence.*;

import static validator.Validator.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="activity_type",
        discriminatorType = DiscriminatorType.STRING)
public abstract class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String title;
    private String code;
    private boolean active;
    @Column(name = "order_in_system", columnDefinition = "SMALLINT")
    private Integer orderInSystem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private Section section;

    @Deprecated
    public Activity() {}

    public Activity(String title, String code, Section section) {
        isNotEmpty(title, "The title must not be empty!");
        regexValidatorAndNotEmpty(code, "The code must be lowercase letters or numbers and not be empty");
        isNotNull(section, "Section must not be null!!");
        this.title = title;
        this.code = code;
        this.section = section;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", active=" + active +
                ", orderInSystem=" + orderInSystem +
                ", section=" + section +
                '}';
    }
}
