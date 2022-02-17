package tecnodev.question;

import tecnodev.activity.Activity;
import tecnodev.section.Section;

import static validator.Validator.isNotEmpty;
import static validator.Validator.isNotNullOrEmpty;

public class Question extends Activity {

    private String statement;
    private QuestionType type = QuestionType.SINGLE_CHOICE;

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
