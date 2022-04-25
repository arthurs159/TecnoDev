package br.com.tecnodev.controller;

import br.com.tecnodev.entities.category.CategoryPageDTO;
import br.com.tecnodev.entities.subCategory.SubcategoryPublicPageDTO;
import br.com.tecnodev.repository.CategoryRepository;
import br.com.tecnodev.repository.SubCategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class CategoryPublicController {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    public CategoryPublicController(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    @GetMapping("/category/{categoryCode}")
    public String loadCategoryPublicPage(@PathVariable String categoryCode, Model model) {
        CategoryPageDTO categoryPageDTO = new CategoryPageDTO(categoryRepository.findByCode(categoryCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));

        List<SubcategoryPublicPageDTO> subcategoryPublicPageDTO = subCategoryRepository.findAllActiveSubcategoryWithCourses(categoryCode)
                .stream().map(SubcategoryPublicPageDTO::new).toList();

        model.addAttribute("category", categoryPageDTO);
        model.addAttribute("subcategory", subcategoryPublicPageDTO);
        return "/publicPage/category";
    }
}
