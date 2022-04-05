package br.com.tecnodev.controller;

import br.com.tecnodev.entities.category.Category;
import br.com.tecnodev.entities.category.CategoryToListDTO;
import br.com.tecnodev.entities.category.NewCategoryForm;
import br.com.tecnodev.entities.category.NewCategoryFormUpdate;
import br.com.tecnodev.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String listActiveCategories(Model model) {
        List<Category> categoryList = categoryRepository.findAllByOrderByOrderInSystem();
        List<CategoryToListDTO> categoryDTO = categoryList.stream().map(CategoryToListDTO::new).toList();
        model.addAttribute("category", categoryDTO);
        return "category/list";
    }

    @RequestMapping("/new")
    public String showFormNewCategory() {
        return "category/insert";
    }

    @PostMapping
    public String insertCategory(@Valid NewCategoryForm dto, BindingResult result) {
        if (result.hasErrors()) {
            return "category/insert";
        }

        categoryRepository.save(dto.toEntity());
        return "redirect:/admin/categories";
    }

    @GetMapping("{code}")
    public String getCategoryByCode(@PathVariable String code, Model model) {
        Category category = categoryRepository.findByCode(code);
        model.addAttribute("category", category);
        return "category/update";
    }

    @PostMapping("{code}")
    public String updateCategoryById(String code, @Valid NewCategoryFormUpdate dto) {
        Category category = dto.toEntity();
        category.update(dto);
        categoryRepository.save(category);
        return "redirect:/admin/categories";
    }

}
