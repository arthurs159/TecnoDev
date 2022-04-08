package br.com.tecnodev.controller;

import br.com.tecnodev.projections.CategoryReportProjection;
import br.com.tecnodev.projections.InstructorReportProjection;
import br.com.tecnodev.projections.dtoProjection.CoursesInstructorDTO;
import br.com.tecnodev.projections.dtoProjection.QuantityCoursesFromCategoryDTO;
import br.com.tecnodev.repository.CategoryRepository;
import br.com.tecnodev.repository.CourseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ReportController {

    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;

    public ReportController(CategoryRepository categoryRepository, CourseRepository courseRepository) {
        this.categoryRepository = categoryRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/admin/dashboard")
    public String showReportPage(Model model){
        List<CategoryReportProjection> categoryProjection = categoryRepository.report();
        List<QuantityCoursesFromCategoryDTO> quantityCoursesDto = categoryProjection.stream().map(QuantityCoursesFromCategoryDTO::new).toList();
        model.addAttribute("report", quantityCoursesDto);

        InstructorReportProjection instructorProjection = courseRepository.getInstructorWithMoreCourses();
        CoursesInstructorDTO coursesInstructorDTO = new CoursesInstructorDTO(instructorProjection);
        model.addAttribute("instructor", coursesInstructorDTO);
        return "report/report";
    }

//    @GetMapping("/report")
//    public String resultList(){
////        List<CategoryReportProjection> projection = categoryRepository.report();
//        return showReportPage();
//    }
}
