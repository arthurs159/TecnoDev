package br.com.tecnodev.controller;

import br.com.tecnodev.entities.subCategory.SubCategory;
import br.com.tecnodev.entities.subCategory.SubCategoryToListDTO;
import br.com.tecnodev.repository.SubCategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SubCategoryController {

    private final SubCategoryRepository subCategoryRepository;

    public SubCategoryController(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    @GetMapping("/admin/subcategories/{code}")
    public String listSubcategories(@PathVariable String code, Model model) {
        List<SubCategory> subCategoryList = subCategoryRepository.findSubCategoriesByCategory_CodeOrderByOrderInSystem(code);
        List<SubCategoryToListDTO> subcategoryDto = subCategoryList.stream().map(SubCategoryToListDTO::new).toList();
        model.addAttribute("subcategories", subcategoryDto);
        return "subcategory/list";
    }
}
