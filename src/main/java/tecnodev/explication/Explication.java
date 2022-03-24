package tecnodev.explication;

import tecnodev.activity.Activity;
import tecnodev.section.Section;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static validator.Validator.isNotNullOrEmpty;

@Entity
@DiscriminatorValue("Explication")
public class Explication extends Activity {

    @Column(columnDefinition = "TEXT")
    private String explication;

    @Deprecated
    public Explication() {}

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
