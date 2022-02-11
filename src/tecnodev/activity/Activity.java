package tecnodev.activity;

import tecnodev.section.Section;

public class Activity {

    private String title;
    private String code;
    private boolean active;
    private Integer orderInSystem;
    /*CHECAR O TIPO (Se é pra colocar ou não.*/
    private Section section;

    public Activity(String title, String code, Section section) {
        this.title = title;
        this.code = code;
        this.section = section;
    }

    public Activity(String title, String code, boolean active, int orderInSystem, Section section) {
        this(title, code, section);
        this.active = active;
        this.orderInSystem = orderInSystem;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getOrderInSystem() {
        return orderInSystem;
    }

    public void setOrderInSystem(Integer orderInSystem) {
        this.orderInSystem = orderInSystem;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", active=" + active +
                ", orderInSystem=" + orderInSystem +
                ", section=" + section +
                '}';
    }
}
