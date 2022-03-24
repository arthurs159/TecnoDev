package com.br.tecnodev.tecnodev.alternative;

import com.br.tecnodev.tecnodev.question.Question;

import javax.persistence.*;

import static com.br.tecnodev.validator.Validator.*;

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

    @Deprecated
    public Alternative() {}

    public Alternative(String alternativeText, boolean correct, Question question) {
        isNotNullOrEmpty(alternativeText, "The text must not be empty or null");
        isNotNull(question, "Question should not be null");
        this.alternativeText = alternativeText;
        this.correct = correct;
        this.question = question;
    }

    @Override
    public String toString() {
        return "Alternative{" +
                "text='" + alternativeText + '\'' +
                ", orderInSystem=" + orderInSystem +
                ", indicative=" + correct +
                ", justification='" + justification + '\'' +
                ", question=" + question +
                '}';
    }
}
