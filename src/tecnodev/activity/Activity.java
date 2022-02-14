package tecnodev.activity;

import tecnodev.section.Section;
import validator.Validator;

public abstract class Activity {

    private String title;
    private String code;
    private boolean active;
    private Integer orderInSystem;
    private Section section;

    public Activity(String title, String code, Section section) {
        Validator.isNotNull(title, "The title must not be empty or null!!!");
        Validator.regexValidator(code, "The code must be lowercase letters or numbers");
        this.title = title;
        this.code = code;
        this.section = section;
    }

    public Activity(String title, String code, boolean active, Integer orderInSystem, Section section) {
        this(title, code, section);
        this.orderInSystem = orderInSystem;
        this.section = section;
    }

}
