package tecnodev.question;

import tecnodev.ENUM.QuestionType;
import tecnodev.activity.Activity;
import tecnodev.section.Section;

public class Question extends Activity {

    private String statement;
    private QuestionType type = QuestionType.ONLY_ANSWER;

    public Question(String title, String code, Section section, String statement, QuestionType type) {
        super(title, code, section);
        this.statement = statement;
        this.type = type;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Question{" +
                "statement='" + statement + '\'' +
                ", type=" + type +
                '}';
    }
}
