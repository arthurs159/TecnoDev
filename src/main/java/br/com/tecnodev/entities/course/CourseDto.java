package br.com.tecnodev.entities.course;

public class CourseDto {

    private String name;
    private String code;
    private Integer estimatedTimeInHours;
    private String developedSkills;

    public CourseDto(String name, String code, Integer estimatedTimeInHours, String developedSkills) {
        this.name = name;
        this.code = code;
        this.estimatedTimeInHours = estimatedTimeInHours;
        this.developedSkills = developedSkills;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Integer getEstimatedTimeInHours() {
        return estimatedTimeInHours;
    }

    public String getDevelopedSkills() {
        return developedSkills;
    }
}