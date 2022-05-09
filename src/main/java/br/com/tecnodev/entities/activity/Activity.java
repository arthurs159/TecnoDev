package br.com.tecnodev.entities.activity;

import br.com.tecnodev.entities.section.Section;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static br.com.tecnodev.validator.Validator.*;

@Entity
@NoArgsConstructor
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

    public Activity(String title, String code, Section section) {
        isNotEmpty(title, "The title must not be empty!");
        regexValidatorAndNotEmpty(code, "The code must be lowercase letters or numbers and not be empty");
        isNotNull(section, "Section must not be null!!");
        this.title = title;
        this.code = code;
        this.section = section;
    }
}
