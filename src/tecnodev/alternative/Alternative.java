package tecnodev.alternative;

import tecnodev.question.Question;
import validator.Validator;

public class Alternative {

    private String text;
    private Integer orderInSystem;
    private Boolean isCorrect;
    private String justification;
    private Question question;

    public Alternative(String text, Boolean isCorrect, Question question) {
        Validator.isNotNull(text, "The text must not be empty or null!!!");
        this.text = text;
        this.isCorrect = isCorrect;
        this.question = question;
    }

    public Alternative(String text, Integer orderInSystem, Boolean isCorrect, String justification, Question question) {
        this(text, isCorrect, question);
        this.isCorrect = isCorrect;
        this.justification = justification;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getOrderInSystem() {
        return orderInSystem;
    }

    public void setOrderInSystem(Integer orderInSystem) {
        this.orderInSystem = orderInSystem;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Alternative{" +
                "text='" + text + '\'' +
                ", orderInSystem=" + orderInSystem +
                ", indicative=" + isCorrect +
                ", justification='" + justification + '\'' +
                ", question=" + question +
                '}';
    }
}
