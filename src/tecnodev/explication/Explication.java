package tecnodev.explication;

import tecnodev.activity.Activity;
import tecnodev.section.Section;
import validator.Validator;

public class Explication extends Activity {

    private String text;

    public Explication(String title, String code, Section section, String text) {
        super(title, code, section);
        this.text = text;
    }

    public Explication(String title, String code, boolean active, int orderInSystem, Section section, String text) {
        super(title, code, active, orderInSystem, section);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Explication{" +
                "text='" + text + '\'' +
                '}';
    }

}
