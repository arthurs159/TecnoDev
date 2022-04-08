package br.com.tecnodev.projections.dtoProjection;

import br.com.tecnodev.projections.InstructorReportProjection;

public class CoursesInstructorDTO {

    private String instructorName;
    private Long quantityOfCourses;

    public CoursesInstructorDTO(String instructorName, Long quantityOfCourses) {
        this.instructorName = instructorName;
        this.quantityOfCourses = quantityOfCourses;
    }

    public CoursesInstructorDTO(InstructorReportProjection projection) {
        this.instructorName = projection.getInstructorName();
        this.quantityOfCourses = projection.getQuantityOfCourses();
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public Long getQuantityOfCourses() {
        return quantityOfCourses;
    }

    public void setQuantityOfCourses(Long quantityOfCourses) {
        this.quantityOfCourses = quantityOfCourses;
    }
}
