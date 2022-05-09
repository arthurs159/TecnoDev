package br.com.tecnodev.entities.video;

import br.com.tecnodev.entities.activity.Activity;
import br.com.tecnodev.entities.section.Section;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static br.com.tecnodev.validator.Validator.isNotNullOrEmpty;

@Entity
@NoArgsConstructor
@DiscriminatorValue("Video")
public class Video extends Activity {

    @Column(columnDefinition = "TEXT")
    private String url;
    @Column(name = "estimated_time_in_minutes", columnDefinition = "SMALLINT")
    private int estimatedTimeInMinutes;
    @Column(columnDefinition = "TEXT")
    private String transcription;

    public Video(String title, String code, Section section, String url) {
        super(title, code, section);
        isNotNullOrEmpty(url, "The URL can not be empty or null");
        this.url = url;
    }
}
