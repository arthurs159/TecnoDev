package br.com.tecnodev.controller;

import br.com.tecnodev.entities.course.Course;
import br.com.tecnodev.entities.course.CourseToListDTO;
import br.com.tecnodev.repository.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/admin/courses/{catCode}/{subCode}")
    public String listCourses(@PathVariable String catCode,
                              @PathVariable String subCode, Model model, Pageable pageable) {
        Page<Course> courseList = courseRepository.getCourseBySubCategoryCodeAndCategoryCode(subCode, catCode, pageable);
        Page<CourseToListDTO> courseToListDTOPage = courseList.map(CourseToListDTO::new);
        model.addAttribute("courses", courseToListDTOPage);
        return "course/list";
    }
}
