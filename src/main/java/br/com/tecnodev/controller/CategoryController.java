package br.com.tecnodev.controller;

import br.com.tecnodev.entities.category.Category;
import br.com.tecnodev.entities.category.DTO.CategoryToListDTO;
import br.com.tecnodev.entities.category.DTO.NewCategoryForm;
import br.com.tecnodev.entities.category.DTO.NewCategoryFormUpdate;
import br.com.tecnodev.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/admin/categories")
    public String listActiveCategories(Model model) {
        List<Category> categoryList = categoryRepository.findAllByOrderByOrderInSystem();
        List<CategoryToListDTO> categoryDTO = categoryList.stream().map(CategoryToListDTO::new).toList();
        model.addAttribute("categories", categoryDTO);
        return "category/list";
    }

    @GetMapping("/admin/categories/new")
    public String showFormNewCategory(NewCategoryForm NewCategoryFormDto, Model model) {
        model.addAttribute("category", NewCategoryFormDto);
        return "category/insert";
    }

    @PostMapping("/admin/categories")
    public String insertCategory(@Valid NewCategoryForm newCategoryFormDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return showFormNewCategory(newCategoryFormDto, model);
        }

        categoryRepository.save(newCategoryFormDto.toEntity());
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/{code}")
    public String getCategoryByCode(@PathVariable String code, Model model) {
        Category category = categoryRepository.findByCode(code).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("category", category);
        return "category/update";
    }

    @PostMapping("/admin/categories/{code}")
    public String updateCategoryByCode(@PathVariable String code, @Valid NewCategoryFormUpdate NewCategoryFormUpdateDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return getCategoryByCode(code, model);
        }

        Category category = categoryRepository.findByCode(code).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        category.update(NewCategoryFormUpdateDto);
        categoryRepository.save(category);
        return "redirect:/admin/categories";
    }

    @PostMapping("/changeCategoryStatus/{id}")
    public ResponseEntity<Void> changeCategoryStatus(@PathVariable Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        category.disableActive();
        categoryRepository.save(category);
        return ResponseEntity.ok().build();
    }
}