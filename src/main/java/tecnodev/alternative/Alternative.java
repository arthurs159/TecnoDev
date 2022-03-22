package tecnodev.alternative;

import tecnodev.question.Question;

import javax.persistence.*;

import static validator.Validator.*;

public class Alternative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String alternativeText;
    private Integer orderInSystem;
    private boolean correct;
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
