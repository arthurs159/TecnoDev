package tecnodev.alternative;

import tecnodev.question.Question;

public class Alternative {

    private String text;
    private Integer orderInSystem;
    private Boolean indicative;
    private String justification;
    private Question question;

    public Alternative(String text, Boolean indicative, Question question) {
        this.text = text;
        this.indicative = indicative;
        this.question = question;
    }

    public Alternative(String text, Integer orderInSystem, Boolean indicative, String justification, Question question) {
        this(text, indicative, question);
        this.indicative = indicative;
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

    public Boolean getIndicative() {
        return indicative;
    }

    public void setIndicative(Boolean indicative) {
        this.indicative = indicative;
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
                ", indicative=" + indicative +
                ", justification='" + justification + '\'' +
                ", question=" + question +
                '}';
    }
}
