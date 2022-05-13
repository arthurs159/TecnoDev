package br.com.tecnodev.entities.alternative;

import br.com.tecnodev.entities.question.Question;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static br.com.tecnodev.validator.Validator.*;

@Entity
public class Alternative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", name = "alternative_text")
    private String alternativeText;
    @Column(columnDefinition = "SMALLINT", name = "order_in_system")
    private Integer orderInSystem;
    private boolean correct;
    @Column(columnDefinition = "TEXT")
    private String justification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    private Question question;

    public Alternative() {}

    public Alternative(String alternativeText, boolean correct, Question question) {
        isNotNullOrEmpty(alternativeText, "The text must not be empty or null");
        isNotNull(question, "Question should not be null");
        this.alternativeText = alternativeText;
        this.correct = correct;
        this.question = question;
    }
}
