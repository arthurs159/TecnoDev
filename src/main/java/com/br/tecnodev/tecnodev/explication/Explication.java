package com.br.tecnodev.tecnodev.explication;

import com.br.tecnodev.tecnodev.activity.Activity;
import com.br.tecnodev.tecnodev.section.Section;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static com.br.tecnodev.validator.Validator.isNotNullOrEmpty;

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
