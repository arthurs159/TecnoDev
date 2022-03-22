package tecnodev.question;

import tecnodev.activity.Activity;
import tecnodev.section.Section;

import javax.persistence.*;

import static validator.Validator.isNotNullOrEmpty;

@Entity
@DiscriminatorValue("Question")
public class Question extends Activity {

    @Column(columnDefinition = "TEXT")
    private String statement;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM", name = "queston_type")
    private QuestionType type = QuestionType.SINGLE_CHOICE;

    public Question() {}

    public Question(String title, String code, Section section, String statement, QuestionType type) {
        super(title, code, section);
        isNotNullOrEmpty(statement, "Statement should not be empty or null");
        this.statement = statement;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Question{" +
                "statement='" + statement + '\'' +
                ", type=" + type +
                "} " + super.toString();
    }
}
