package tecnodev.video;

import tecnodev.activity.Activity;
import tecnodev.section.Section;

public class Video extends Activity {

    private String url;
    private int minutes;
    private String transcription;

    public Video(String title, String code, Section section, String url) {
        super(title, code, section);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    @Override
    public String toString() {
        return "Video{" +
                "url='" + url + '\'' +
                ", minutes=" + minutes +
                ", transcription='" + transcription + '\'' +
                '}';
    }
}
