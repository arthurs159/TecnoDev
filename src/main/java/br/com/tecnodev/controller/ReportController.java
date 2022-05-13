package br.com.tecnodev.controller;

import br.com.tecnodev.projections.CategoryReportProjection;
import br.com.tecnodev.projections.InstructorReportProjection;
import br.com.tecnodev.repository.CategoryRepository;
import br.com.tecnodev.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class ReportController {

    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;

    @GetMapping("/admin")
    public String getReportPage() {
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/dashboard")
    public String showCategoriesNumberOfCoursesAndInstructorWithMoreCoursesReportPage(Model model) {
        List<CategoryReportProjection> categoryProjection = categoryRepository.findCategoryAndNumberOfCourses();
        InstructorReportProjection instructorProjection = courseRepository.getInstructorWithMoreCourses();

        model.addAttribute("report", categoryProjection);
        model.addAttribute("instructor", instructorProjection);
        return "report/report";
    }

}
