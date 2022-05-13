package br.com.tecnodev.controller;

import br.com.tecnodev.entities.course.Course;
import br.com.tecnodev.entities.course.DTO.CourseToListDTO;
import br.com.tecnodev.entities.course.DTO.NewCourseForm;
import br.com.tecnodev.entities.course.DTO.NewCourseFormUpdate;
import br.com.tecnodev.entities.subCategory.SubCategory;
import br.com.tecnodev.repository.CourseRepository;
import br.com.tecnodev.repository.SubCategoryRepository;
import br.com.tecnodev.validator.NewCourseFormUpdateValidator;
import br.com.tecnodev.validator.NewCourseFormValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;

@Controller
@AllArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final NewCourseFormValidator newCourseFormValidator;
    private final NewCourseFormUpdateValidator newCourseFormUpdateValidator;

    @InitBinder("newCourseForm")
    void initBinderNewCategoryForm(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(newCourseFormValidator);
    }

    @InitBinder("newCourseFormUpdate")
    void initBinderNewCategoryFormUpdate(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(newCourseFormUpdateValidator);
    }

    @GetMapping("/admin/courses/{categoryCode}/{subcategoryCode}")
    public String listCourses(@PathVariable String categoryCode,
                              @PathVariable String subcategoryCode,
                              @PageableDefault(size = 5) Pageable pageable, Model model) {

        SubCategory subCategory = subCategoryRepository.findSubcategoryByCode(subcategoryCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Page<CourseToListDTO> courses = courseRepository.findAllBySubCategory(subCategory, pageable)
                .map(CourseToListDTO::new);

        model.addAttribute("courses", courses);
        model.addAttribute("subcategory", subCategory);
        model.addAttribute("categoryCode", categoryCode);
        return "course/list";
    }

    @GetMapping("/admin/courses/new")
    public String getCourseForm(NewCourseForm newCourseForm, Model model){
        List<SubCategory> subcategories = subCategoryRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(SubCategory::getName))
                .toList();

        model.addAttribute( "course", newCourseForm);
        model.addAttribute( "subcategory", subcategories);
        return "course/insert";
    }

    @PostMapping("/admin/courses")
    public String insertNewCourse(@Valid NewCourseForm newCourseForm, BindingResult result, Model model){
        if(result.hasErrors()){
            return getCourseForm(newCourseForm, model);
        }
        SubCategory subcategory = subCategoryRepository.findById(newCourseForm.getSubcategoryId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        courseRepository.save(newCourseForm.toEntity(subcategory));
        return "redirect:/admin/courses/" + subcategory.getCategory().getCode() + "/" + subcategory.getCode() ;
    }

    @GetMapping("/admin/courses/{categoryCode}/{subcategoryCode}/{courseCode}")
    public String getUpdateCourseForm(@PathVariable String categoryCode,
                                      @PathVariable String subcategoryCode,
                                      @PathVariable String courseCode, NewCourseFormUpdate newCourseFormUpdate, Model model){
        Course course = courseRepository.findByCode(courseCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<SubCategory> subcategories = subCategoryRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(SubCategory::getName))
                .toList();

        model.addAttribute("course", course);
        model.addAttribute("subcategories", subcategories);
        model.addAttribute("categoryCode", course.getCategoryCode());
        model.addAttribute("subcategoryCode", course.getSubCategoryCode());
        return "course/update";
    }
    @PostMapping("/admin/courses/{categoryCode}/{subcategoryCode}/{courseCode}")
    public String updateCourse(@PathVariable String categoryCode,
                               @PathVariable String subcategoryCode,
                               @PathVariable String courseCode, @Valid NewCourseFormUpdate newCourseFormUpdate, BindingResult result, Model model){
        if(result.hasErrors()){
            return getUpdateCourseForm(categoryCode, subcategoryCode, courseCode, newCourseFormUpdate, model);
        }

        Course course = courseRepository.findByCode(courseCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        SubCategory subcategory = subCategoryRepository.findById(newCourseFormUpdate.getSubcategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        course.merge(newCourseFormUpdate, subcategory);
        courseRepository.save(course);
        return "redirect:/admin/courses/" + subcategory.getCategory().getCode() + "/" + subcategory.getCode() ;
    }


}
