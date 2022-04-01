package br.com.tecnodev.entities.subCategory;

public class SubCategoryDto {

    private String name;
    private String code;
    private String studyGuide;

    public SubCategoryDto(String name, String code, String studyGuide) {
        this.name = name;
        this.code = code;
        this.studyGuide = studyGuide;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getStudyGuide() {
        return studyGuide;
    }
}
