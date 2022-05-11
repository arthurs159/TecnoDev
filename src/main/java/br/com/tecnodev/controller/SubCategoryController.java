package br.com.tecnodev.controller;

import br.com.tecnodev.entities.category.Category;
import br.com.tecnodev.entities.subCategory.DTO.NewSubCategoryForm;
import br.com.tecnodev.entities.subCategory.DTO.NewSubCategoryFormUpdate;
import br.com.tecnodev.entities.subCategory.DTO.SubCategoryToListDTO;
import br.com.tecnodev.entities.subCategory.SubCategory;
import br.com.tecnodev.repository.CategoryRepository;
import br.com.tecnodev.repository.SubCategoryRepository;
import br.com.tecnodev.validator.NewSubCategoryFormUpdateValidator;
import br.com.tecnodev.validator.NewSubCategoryFormValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class SubCategoryController {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;
    private final NewSubCategoryFormValidator newSubCategoryFormValidator;
    private final NewSubCategoryFormUpdateValidator newSubCategoryFormUpdateValidator;

    @InitBinder("newSubCategoryForm")
    void initBinderNewSubCategoryForm(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(newSubCategoryFormValidator);
    }

    @InitBinder("newSubCategoryFormUpdate")
    void initBinderNewSubCategoryFormUpdate(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(newSubCategoryFormUpdateValidator);
    }

    @GetMapping("/admin/subcategories/{code}")
    public String listSubcategories(@PathVariable String code, Model model) {
        Category category = categoryRepository.findByCode(code).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<SubCategory> subCategoryList = subCategoryRepository.findSubCategoriesByCategory_CodeOrderByOrderInSystem(code);
        List<SubCategoryToListDTO> subcategoryDto = subCategoryList.stream().map(SubCategoryToListDTO::new).toList();
        model.addAttribute("category", category);
        model.addAttribute("subcategories", subcategoryDto);
        return "subcategory/list";
    }

    @GetMapping("/admin/subcategories/new")
    public String getSubcategoryForm(NewSubCategoryForm newSubCategoryForm, Model model) {
        List<Category> categories = categoryRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Category::getName))
                .toList();

        model.addAttribute("category", categories);
        model.addAttribute("subCategoryForm", newSubCategoryForm);
        return "subcategory/insert";
    }

    @PostMapping("/admin/subcategories")
    public String insertSubcategory(@Valid NewSubCategoryForm newSubCategoryForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return getSubcategoryForm(newSubCategoryForm, model);
        }
        Category category = categoryRepository.findById(newSubCategoryForm.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        subCategoryRepository.save(newSubCategoryForm.toEntity(category));
        return listSubcategories(category.getCode(), model);
    }

    @GetMapping("/admin/subcategories/{categoryCode}/{subcategoryCode}")
    public String getSubcategoryUpdateForm(@PathVariable String categoryCode, @PathVariable String subcategoryCode, NewSubCategoryFormUpdate subCategoryFormUpdate, Model model) {
        SubCategory subCategory = subCategoryRepository.findSubcategoryByCategoryAndSubcategoryCode(subcategoryCode, categoryCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<Category> categories = categoryRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Category::getName))
                .toList();

        model.addAttribute("subcategory", subCategory);
        model.addAttribute("category", categories);

        return "subcategory/update";
    }

    @PostMapping("/admin/subcategories/{categoryCode}/{subcategoryCode}")
    public String updateSubcategory(@PathVariable String categoryCode, @PathVariable String subcategoryCode, @Valid NewSubCategoryFormUpdate subCategoryFormUpdate, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return getSubcategoryUpdateForm(categoryCode, subcategoryCode, subCategoryFormUpdate, model);
        }

        SubCategory subCategory = subCategoryRepository.findSubcategoryByCategoryAndSubcategoryCode(subcategoryCode, categoryCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Category category = categoryRepository.findById(subCategoryFormUpdate.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        subCategory.merge(subCategoryFormUpdate, category);

        subCategoryRepository.save(subCategory);
        return "redirect:/admin/subcategories/" + category.getCode();
    }

    @PostMapping("/changeSubcategoryStatus/{id}")
    public ResponseEntity<Void> changeSubcategoryStatus(@PathVariable Long id) {
        SubCategory subCategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        subCategory.disableActive();
        subCategoryRepository.save(subCategory);
        return ResponseEntity.ok().build();
    }

}
