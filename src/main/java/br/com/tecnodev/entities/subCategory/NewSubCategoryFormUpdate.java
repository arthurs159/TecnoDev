package br.com.tecnodev.entities.subCategory;

import br.com.tecnodev.entities.category.Category;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class NewSubCategoryFormUpdate {

    private Long id;
    @NotEmpty(message = "{form.error.name}")
    private String name;
    @NotEmpty(message = "{form.error.code}")
    @Pattern(regexp = "[a-z0-9-]+", message = "{form.error.code.regex}")
    private String code;
    private String description;
    private String studyGuide;
    private boolean active;
    private Integer orderInSystem;

    public NewSubCategoryFormUpdate() {
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public boolean isActive() {
        return active;
    }

    public Integer getOrderInSystem() {
        return orderInSystem;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setOrderInSystem(Integer orderInSystem) {
        this.orderInSystem = orderInSystem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SubCategory toEntity(Category category) {
        SubCategory subcategory = new SubCategory(name, code, orderInSystem, description, active, category);
        subcategory.setId(this.id);
        return subcategory;
    }
}
