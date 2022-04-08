package br.com.tecnodev.controller;

import br.com.tecnodev.entities.category.Category;
import br.com.tecnodev.entities.category.CategoryToListDTO;
import br.com.tecnodev.entities.subCategory.NewSubCategoryForm;
import br.com.tecnodev.entities.subCategory.SubCategory;
import br.com.tecnodev.entities.subCategory.SubCategoryToListDTO;
import br.com.tecnodev.repository.CategoryRepository;
import br.com.tecnodev.repository.SubCategoryRepository;
import javassist.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SubCategoryController {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    public SubCategoryController(SubCategoryRepository subCategoryRepository, CategoryRepository categoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/admin/subcategories/{code}")
    public String listSubcategories(@PathVariable String code, Model model) {
        List<SubCategory> subCategoryList = subCategoryRepository.findSubCategoriesByCategory_CodeOrderByOrderInSystem(code);
        List<SubCategoryToListDTO> subcategoryDto = subCategoryList.stream().map(SubCategoryToListDTO::new).toList();
        model.addAttribute("subcategories", subcategoryDto);
        return "subcategory/list";
    }

    @GetMapping("/admin/subcategories/new")
    public String getSubcategoryForm(NewSubCategoryForm newSubCategoryForm, Model model) {
        List<Category> categoryList = categoryRepository.findAll();
//        List<CategoryToListDTO> categoriesDto = categoryList.stream().map(CategoryToListDTO::new).toList();
        model.addAttribute("category", categoryList);
        model.addAttribute("subCategoryForm", newSubCategoryForm == null ? new NewSubCategoryForm() : newSubCategoryForm);
        return "subcategory/insert";
    }

    @PostMapping("/admin/subcategories")
    public String insertSubcategory(@Valid NewSubCategoryForm newSubCategoryForm, BindingResult result, Model model) {
        Category category = categoryRepository.findById(newSubCategoryForm.getCategoryId()).orElseThrow(RuntimeException::new);
        if (result.hasErrors()){
            return getSubcategoryForm(newSubCategoryForm, model);
        }
        subCategoryRepository.save(newSubCategoryForm.toEntity(category));
        return listSubcategories(category.getCode(), model);
    }


}
