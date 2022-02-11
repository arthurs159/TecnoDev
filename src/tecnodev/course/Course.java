package tecnodev.course;

public class Course {

    private String name;
    private String code;
    private Integer hours;
    private boolean isVisible;
    private String targetAudience;
    private String teacher;
    private String description;
    private String developedAbility;

    public Course(String name, String code, int hours, String teacher) {
        this.name = name;
        this.code = code;
        this.hours = hours;
        this.teacher = teacher;
    }

    public Course(String name, String code, int hours, boolean isVisible, String targetAudience, String teacher, String description, String developedAbility) {
        this(name, code, hours, teacher);
        this.isVisible = isVisible;
        this.targetAudience = targetAudience;
        this.description = description;
        this.developedAbility = developedAbility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getHours() {
        return hours;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisibility(boolean visibility) {
        this.isVisible = visibility;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDevelopedAbility() {
        return developedAbility;
    }

    public void setDevelopedAbility(String developedAbility) {
        this.developedAbility = developedAbility;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", hours=" + hours +
                ", isVisible=" + isVisible +
                ", targetAudience='" + targetAudience + '\'' +
                ", teacher='" + teacher + '\'' +
                ", description='" + description + '\'' +
                ", developedAbility='" + developedAbility + '\'' +
                '}';
    }
}