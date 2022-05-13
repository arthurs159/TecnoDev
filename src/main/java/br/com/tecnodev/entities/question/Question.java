package br.com.tecnodev.entities.question;

import br.com.tecnodev.entities.activity.Activity;
import br.com.tecnodev.entities.section.Section;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static br.com.tecnodev.validator.Validator.isNotNullOrEmpty;

@Entity
@DiscriminatorValue("Question")
public class Question extends Activity {

    @Column(columnDefinition = "TEXT")
    private String statement;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM", name = "question_type")
    private QuestionType type = QuestionType.SINGLE_CHOICE;

    public Question() {}

    public Question(String title, String code, Section section, String statement, QuestionType type) {
        super(title, code, section);
        isNotNullOrEmpty(statement, "Statement should not be empty or null");
        this.statement = statement;
        this.type = type;
    }
}
