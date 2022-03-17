package jdbc.dto;

public class CourseDto {

    private final Integer id;
    private final String name;
    private final int estimatedTimeinHours;
    private final Integer subCategoryId;
    private final String subCategoryName;

    public CourseDto(Integer id, String name, Integer estimatedTimeinHours, Integer subCategoryId, String subCategoryName) {
        this.id = id;
        this.name = name;
        this.estimatedTimeinHours = estimatedTimeinHours;
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getEstimatedTimeinHours() {
        return estimatedTimeinHours;
    }

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }
}
