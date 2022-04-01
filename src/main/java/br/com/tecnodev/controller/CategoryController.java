package br.com.tecnodev.controller;

import br.com.tecnodev.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
//
//    public List<CategoryActiveDto> listAllActiveCategories(){
//    }

    @GetMapping
    public String listAllCategory(Model model){
        model.addAttribute("categoriesDTOList", categoryRepository.findAll());
        return "category/listCategories";
    }
}
