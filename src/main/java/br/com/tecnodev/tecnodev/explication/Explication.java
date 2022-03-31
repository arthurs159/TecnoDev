package br.com.tecnodev.tecnodev.explication;

import br.com.tecnodev.tecnodev.activity.Activity;
import br.com.tecnodev.tecnodev.section.Section;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static br.com.tecnodev.validator.Validator.isNotNullOrEmpty;

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
