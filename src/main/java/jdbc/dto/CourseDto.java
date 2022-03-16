package jdbc.dto;

public class CourseDto {

    private Integer id;
    private String name;
    private int estimatedTimeinHours;
    private Integer subCategoryId;
    private String subCategoryName;

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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEstimatedTimeinHours() {
        return estimatedTimeinHours;
    }

    public void setEstimatedTimeinHours(int estimatedTimeinHours) {
        this.estimatedTimeinHours = estimatedTimeinHours;
    }

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }
}
