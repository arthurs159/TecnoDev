package br.com.tecnodev.controller;

import br.com.tecnodev.projections.CategoryReportProjection;
import br.com.tecnodev.projections.InstructorReportProjection;
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

    @GetMapping("/admin")
    public String getReportPage() {
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/dashboard")
    public String showReportPage(Model model) {
        List<CategoryReportProjection> categoryProjection = categoryRepository.report();
        InstructorReportProjection instructorProjection = courseRepository.getInstructorWithMoreCourses();

        model.addAttribute("report", categoryProjection);
        model.addAttribute("instructor", instructorProjection);
        return "report/report";
    }

}
