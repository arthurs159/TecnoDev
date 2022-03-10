package tecnodev.explication;

import tecnodev.activity.Activity;
import tecnodev.section.Section;

import static validator.Validator.isNotNullOrEmpty;

public class Explication extends Activity {

    private String explication;

    public Explication(String title, String code, Section section, String explication) {
        super(title, code, section);
        isNotNullOrEmpty(explication, "Text should not be empty or null");
        this.explication = explication;
    }

    @Override
    public String toString() {
        return "Explication{" +
                "text='" + explication + '\'' +
                "} " + super.toString();
    }
}
