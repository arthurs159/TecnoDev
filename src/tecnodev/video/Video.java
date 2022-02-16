package tecnodev.video;

import tecnodev.activity.Activity;
import tecnodev.section.Section;

import static validator.Validator.isNotEmpty;
import static validator.Validator.isNotNullOrEmpty;

public class Video extends Activity {

    private String url;
    private int estimatedTimeInMinutes;
    private String transcription;

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
