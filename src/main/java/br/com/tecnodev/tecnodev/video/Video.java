package br.com.tecnodev.tecnodev.video;

import br.com.tecnodev.tecnodev.activity.Activity;
import br.com.tecnodev.tecnodev.section.Section;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static br.com.tecnodev.validator.Validator.isNotNullOrEmpty;

@Entity
@DiscriminatorValue("Video")
public class Video extends Activity {

    @Column(columnDefinition = "TEXT")
    private String url;
    @Column(name = "estimated_time_in_minutes", columnDefinition = "SMALLINT")
    private int estimatedTimeInMinutes;
    @Column(columnDefinition = "TEXT")
    private String transcription;

    @Deprecated
    public Video() {}

    public Video(String title, String code, Section section, String url) {
        super(title, code, section);
        isNotNullOrEmpty(url, "The URL can not be empty or null");
        this.url = url;
    }

    @Override
    public String toString() {
        return "Video{" +
                "url='" + url + '\'' +
                ", estimatedTimeInMinutes=" + estimatedTimeInMinutes +
                ", transcription='" + transcription + '\'' +
                '}';
    }
}
