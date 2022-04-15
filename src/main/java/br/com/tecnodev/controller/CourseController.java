package br.com.tecnodev.controller;

import br.com.tecnodev.entities.course.CourseToListDTO;
import br.com.tecnodev.entities.subCategory.SubCategory;
import br.com.tecnodev.repository.CourseRepository;
import br.com.tecnodev.repository.SubCategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CourseController {

    private final CourseRepository courseRepository;
    private final SubCategoryRepository subCategoryRepository;

    public CourseController(CourseRepository courseRepository, SubCategoryRepository subCategoryRepository) {
        this.courseRepository = courseRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    @GetMapping("/admin/courses/{categoryCode}/{subcategoryCode}")
    public String listCourses(@PathVariable String categoryCode,
                              @PathVariable String subcategoryCode,
                              @PageableDefault(size = 5) Pageable pageable, Model model) {

        SubCategory subCategory = subCategoryRepository.findSubcategoryByCode(subcategoryCode);

        Page<CourseToListDTO> courses = courseRepository.findAllBySubCategory(subCategory, pageable)
                .map(CourseToListDTO::new);

        model.addAttribute("courses", courses);
        model.addAttribute("subcategory", subCategory);
        model.addAttribute("categoryCode", categoryCode);
        return "course/list";
    }

}
