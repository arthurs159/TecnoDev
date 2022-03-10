package tecnodev.activity;

import tecnodev.section.Section;

import static validator.Validator.*;

public abstract class Activity {

    private String title;
    private String code;
    private boolean active;
    private Integer orderInSystem;
    private Section section;

    public Activity(String title, String code, Section section) {
        isNotEmpty(title, "The title must not be empty!");
        regexValidatorAndNotEmpty(code, "The code must be lowercase letters or numbers and not be empty");
        isNotNull(section, "Section must not be null!!");
        this.title = title;
        this.code = code;
        this.section = section;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", active=" + active +
                ", orderInSystem=" + orderInSystem +
                ", section=" + section +
                '}';
    }
}
