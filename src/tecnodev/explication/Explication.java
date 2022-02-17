package tecnodev.explication;

import tecnodev.activity.Activity;
import tecnodev.section.Section;

import static validator.Validator.isNotEmpty;
import static validator.Validator.isNotNullOrEmpty;

public class Explication extends Activity {

    private String text;

    public Explication(String title, String code, Section section, String text) {
        super(title, code, section);
        isNotNullOrEmpty(text, "Text should not be empty or null");
        this.text = text;
    }

    @Override
    public String toString() {
        return "Explication{" +
                "text='" + text + '\'' +
                "} " + super.toString();
    }
}
