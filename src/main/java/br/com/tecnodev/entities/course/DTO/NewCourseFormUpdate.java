package br.com.tecnodev.entities.course.DTO;

import br.com.tecnodev.entities.course.Status;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class NewCourseFormUpdate {
    private Long id;
    @NotBlank(message = "{form.error.name}")
    private String name;
    @NotBlank(message = "{form.error.code}")
    @Pattern(regexp = "[a-z0-9-]+", message = "{form.error.code.regex}")
    private String code;
    @NotNull(message = "{form.error.estimatedTime.empty}")
    @Range(min = 1, max = 20, message = "{form.error.estimatedTime.regex}")
    private Integer estimatedTimeInHours;
    private Status visibility;
    private String targetAudience;
    @NotBlank(message = "{form.error.code}")
    private String teacher;
    private String description;
    private String developedSkills;
    @NotNull(message = "{form.error.subcategory}")
    private Long subcategoryId;

    public NewCourseFormUpdate() {}

}
