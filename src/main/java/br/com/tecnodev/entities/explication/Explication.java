package br.com.tecnodev.entities.explication;

import br.com.tecnodev.entities.activity.Activity;
import br.com.tecnodev.entities.section.Section;

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
